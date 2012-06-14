package org.youfood.resources.async;

import org.youfood.services.NotificationService;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@WebServlet(urlPatterns = {"/notifications"}, asyncSupported = true)
public class OrderNotification extends HttpServlet {

    private Map<String, AsyncContext> asyncContexts = new ConcurrentHashMap<String, AsyncContext>();
    private BlockingQueue<String> messages;
    private Thread notifier = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    String message = messages.take();
                    for (AsyncContext asyncContext : asyncContexts.values()) {
                        try {
                            sendMessage(asyncContext.getResponse().getWriter(), message);
                        } catch (Exception e) {
                            asyncContexts.values().remove(asyncContext);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    });

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        messages = new LinkedBlockingQueue<String>();
        NotificationService notificationService = new NotificationService();
        notificationService.setMessages(messages);
        notificationService.start();
        notifier.start();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Acces-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();
        final String id = UUID.randomUUID().toString();
        writer.print(id);
        writer.print(';');
        // Padding
        for (int i = 0; i < 1024; i++) {
            writer.print(' ');
        }
        writer.print(';');
        writer.flush();
        final AsyncContext ac = request.startAsync();
        ac.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent event) throws IOException {
                asyncContexts.remove(id);
            }

            public void onTimeout(AsyncEvent event) throws IOException {
                asyncContexts.remove(id);
            }

            public void onError(AsyncEvent event) throws IOException {
                asyncContexts.remove(id);
            }

            public void onStartAsync(AsyncEvent event) throws IOException {
            }
        });
        asyncContexts.put(id, ac);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext ac = asyncContexts.get(request.getParameter("metadata.id"));
        if ("close".equals(request.getParameter("metadata.type"))) {
            ac.complete();
            return;
        }
    }

    private void sendMessage(PrintWriter writer, String message) throws IOException {
        // default message format is message-size ; message-data ;
        writer.print(message.length());
        writer.print(";");
        writer.print(message);
        writer.print(";");
        writer.flush();
    }

    @Override
    public void destroy() {
        messages.clear();
        asyncContexts.clear();
        notifier.interrupt();
    }
}
