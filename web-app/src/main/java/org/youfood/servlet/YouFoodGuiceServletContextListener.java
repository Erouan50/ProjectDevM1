package org.youfood.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.util.Modules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youfood.module.JPAModuleServlet;
import org.youfood.module.JerseyModule;
import org.youfood.module.VaadinModule;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class YouFoodGuiceServletContextListener extends GuiceServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(YouFoodGuiceServletContextListener.class);

    @Override
    protected Injector getInjector() {
        LOG.info("Init servlet context");
        Set<Module> modules = new LinkedHashSet<Module>();
        modules.add(new JerseyModule());
        modules.add(new JPAModuleServlet());
        modules.add(new VaadinModule());
        return Guice.createInjector(Modules.combine(modules));
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        JULConfigurer.configureJULtoSLF4J(servletContext);
        super.contextInitialized(servletContextEvent);
    }
}