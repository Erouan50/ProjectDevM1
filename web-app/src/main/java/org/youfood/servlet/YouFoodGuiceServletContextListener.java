package org.youfood.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.youfood.module.JerseyModule;
import org.youfood.module.VaadinModule;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class YouFoodGuiceServletContextListener extends GuiceServletContextListener {

    private static final Logger LOG = Logger.getLogger(YouFoodGuiceServletContextListener.class);

    @Override
    protected Injector getInjector() {
        BasicConfigurator.configure();
        LOG.info("Init servlet context");
        Set<Module> modules = new HashSet<Module>();
        modules.add(new JerseyModule());
        modules.add(new VaadinModule());
        return Guice.createInjector(modules);
    }
}