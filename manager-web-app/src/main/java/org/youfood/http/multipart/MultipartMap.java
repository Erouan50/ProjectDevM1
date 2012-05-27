package org.youfood.http.multipart;

import javax.servlet.MultipartConfigElement;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.*;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class MultipartMap extends HashMap<String, Object> {

    private static final String ATTRIBUTE_NAME = "parts";
    private static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String CONTENT_DISPOSITION_FILENAME = "filename";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final int DEFAULT_BUFFER_SIZE = 10240;

    private String encoding;
    private String location;
    private boolean multipartConfigured;

    public MultipartMap(HttpServletRequest multipartRequest, Servlet servlet) throws IOException, ServletException {
        this(multipartRequest, new MultipartConfigElement(servlet.getClass().getAnnotation(MultipartConfig.class)).getLocation(), true);
    }

    public MultipartMap(HttpServletRequest multipartRequest, String location) throws IOException, ServletException {
        this(multipartRequest, location, false);
    }

    private MultipartMap(HttpServletRequest multipartRequest, String location, boolean multipartConfigured) throws IOException, ServletException {
        multipartRequest.setAttribute(ATTRIBUTE_NAME, this);

        this.encoding = multipartRequest.getCharacterEncoding();
        if (this.encoding == null) {
            multipartRequest.setCharacterEncoding(this.encoding = DEFAULT_ENCODING);
        }
        this.location = location;
        this.multipartConfigured = multipartConfigured;
        for (Part part : multipartRequest.getParts()) {
            String filename = getFilename(part);
            if (filename == null) {
                processTextPart(part);
            } else {
                processFilePart(part, filename);
            }
        }
    }

    @Override
    public Object get(Object key) {
        Object value = super.get(key);
        if (value instanceof String[]) {
            String[] values = (String[]) value;
            return values.length == 1 ? values[0] : Arrays.asList(values);
        } else {
            return value;
        }
    }

    public String getParameter(String name) {
        Object value = super.get(name);
        if (value instanceof File) {
            return ((File)value).getName();
        }
        String[] values = (String[]) value;
        return values != null ? values[0] : null;
    }

    public String[] getParameterValues(String name) {
        Object value = super.get(name);
        if (value instanceof File) {
            return new String[] {((File)value).getName()};
        }
        return (String[]) value;
    }

    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(keySet());
    }

    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = new HashMap<String, String[]>();
        for (Map.Entry<String, Object> entry : entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String[]) {
                map.put(entry.getKey(), (String[]) value);
            } else {
                map.put(entry.getKey(), new String[] {((File)value).getName()});
            }
        }
        return map;
    }

    public File getFile(String name) {
        Object value = super.get(name);
        if (value instanceof String[]) {
            throw new IllegalArgumentException("This is e Text field. User #getParameter() instead.");
        }
        return (File) value;
    }

    private String getFilename(Part part) {
        for (String cd : part.getHeader(CONTENT_DISPOSITION).split(";")) {
            if (cd.trim().startsWith(CONTENT_DISPOSITION_FILENAME)) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private String getValue(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), encoding));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        for (int length = 0; (length = reader.read(buffer)) > 0; ) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }

    private void processTextPart(Part part) throws IOException {
        String name = part.getName();
        String[] values = (String[]) super.get(name);

        if (values == null) {
            put(name, new String[]{getValue(part)});
        } else {
            int length = values.length;
            String[] newValues = new String[length + 1];
            System.arraycopy(values, 0, newValues, 0, length);
            newValues[length] = getValue(part);
            put(name, newValues);
        }
    }

    private void processFilePart(Part part, String filename) throws IOException {
        filename = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
        String prefix = filename;
        String suffix = "";
        if (filename.contains(".")) {
            prefix = filename.substring(0, filename.lastIndexOf("."));
            suffix = filename.substring(filename.lastIndexOf("."));
        }
        File file = File.createTempFile(prefix + "_", suffix, new File(location));
        if (multipartConfigured) {
            part.write(file.getName());
        } else {
            InputStream input = null;
            OutputStream output = null;
            try {
                input = new BufferedInputStream(part.getInputStream(), DEFAULT_BUFFER_SIZE);
                output = new BufferedOutputStream(new FileOutputStream(file), DEFAULT_BUFFER_SIZE);
            } finally {
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
            }
        }
        put(part.getName(), file);
        part.delete();
    }
}
