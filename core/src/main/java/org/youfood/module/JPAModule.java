package org.youfood.module;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.youfood.service.MenuService;
import org.youfood.service.impl.MenuServiceJPA;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class JPAModule extends AbstractModule {

    private String persistenceName;

    public JPAModule() {
        persistenceName = "YouFood-PU";
    }

    public JPAModule(String persistenceName) {
        this.persistenceName = persistenceName;
    }

    @Override
    protected void configure() {
        bind(MenuService.class).to(MenuServiceJPA.class);
        install(new JpaPersistModule(persistenceName));
    }
}