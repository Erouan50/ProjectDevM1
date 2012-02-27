package org.youfood.module;

import com.google.inject.AbstractModule;
import org.youfood.dao.ArticleDao;
import org.youfood.dao.MenuDao;
import org.youfood.dao.OrderDao;
import org.youfood.dao.jpa.ArticleDaoJpa;
import org.youfood.dao.jpa.MenuDaoJpa;
import org.youfood.dao.jpa.OrderDaoJpa;
import org.youfood.services.ArticleService;
import org.youfood.services.MenuService;
import org.youfood.services.OrderService;
import org.youfood.services.impl.DefaultArticleService;
import org.youfood.services.impl.DefaultMenuService;
import org.youfood.services.impl.DefaultOrderService;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MenuDao.class).to(MenuDaoJpa.class);
        bind(OrderDao.class).to(OrderDaoJpa.class);
        bind(ArticleDao.class).to(ArticleDaoJpa.class);

        bind(MenuService.class).to(DefaultMenuService.class);
        bind(OrderService.class).to(DefaultOrderService.class);
        bind(ArticleService.class).to(DefaultArticleService.class);
    }
}
