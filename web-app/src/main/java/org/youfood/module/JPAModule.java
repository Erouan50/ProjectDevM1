package org.youfood.module;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import org.apache.log4j.Logger;
import org.youfood.service.MenuService;
import org.youfood.service.impl.MenuServiceJPA;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class JPAModule extends ServletModule {

    private final static Logger LOGGER = Logger.getLogger(JPAModule.class);

    @Override
    protected void configureServlets() {
        LOGGER.info("Init JPAModule");
        bind(MenuService.class).to(MenuServiceJPA.class);

        install(new JpaPersistModule("YouFood-PU"));
        filter("/*").through(PersistFilter.class);
    }
}
