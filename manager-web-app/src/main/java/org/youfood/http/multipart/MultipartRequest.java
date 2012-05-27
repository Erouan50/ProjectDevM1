package org.youfood.http.multipart;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class MultipartRequest extends HttpServletRequestWrapper {

    private MultipartMap multipartMap;

    public MultipartRequest(HttpServletRequest request, String location) throws IOException, ServletException {
        super(request);
        this.multipartMap = new MultipartMap(request, location);
    }

    @Override
    public String getParameter(String name) {
        String result = super.getParameter(name);
        if (result == null) {
            result = multipartMap.getParameter(name);
        }
        return result;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] results = super.getParameterValues(name);
        if (results == null) {
            results = multipartMap.getParameterValues(name);
        }
        return results;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Enumeration<String> result = super.getParameterNames();
        if (result == null) {
            result = multipartMap.getParameterNames();
        }
        return result;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> result = multipartMap.getParameterMap();
        if (result == null) {
            result = multipartMap.getParameterMap();
        }
        return result;
    }

    public File getFile(String name) {
        return multipartMap.getFile(name);
    }
}
