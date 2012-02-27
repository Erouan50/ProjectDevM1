package org.youfood.module;

import com.google.inject.AbstractModule;
import org.youfood.integrationtest.dao.ArticleDaoIntegrationTest;
import org.youfood.integrationtest.dao.MenuDaoIntegrationTest;
import org.youfood.integrationtest.dao.OrderDaoIntegrationTest;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        requestStaticInjection(MenuDaoIntegrationTest.class);
        requestStaticInjection(OrderDaoIntegrationTest.class);
        requestStaticInjection(ArticleDaoIntegrationTest.class);
        install(new JPAModule("YouFood-PU-Test"));
        install(new CoreModule());
    }
}
