package org.youfood.module;

import com.google.inject.AbstractModule;
import org.youfood.dao.MenuDao;
import org.youfood.dao.jpa.MenuDaoJPA;
import org.youfood.services.MenuService;
import org.youfood.services.impl.DefaultMenuService;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MenuDao.class).to(MenuDaoJPA.class);
        bind(MenuService.class).to(DefaultMenuService.class);
    }
}
