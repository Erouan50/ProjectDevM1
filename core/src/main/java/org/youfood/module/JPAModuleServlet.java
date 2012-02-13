package org.youfood.module;

import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import org.apache.log4j.Logger;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class JPAModuleServlet extends ServletModule {

    private final static Logger LOGGER = Logger.getLogger(JPAModuleServlet.class);

    @Override
    protected void configureServlets() {
        LOGGER.info("Init JPAModuleServlet");
        install(new JPAModule());
        filter("/*").through(PersistFilter.class);
    }
}
