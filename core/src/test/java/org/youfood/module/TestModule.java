package org.youfood.module;

import com.google.inject.AbstractModule;
import org.youfood.integrationtest.dao.MenuIntegrationTest;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        requestStaticInjection(MenuIntegrationTest.class);
        install(new JPAModule("YouFood-PU-Test"));
        install(new CoreModule());
    }
}
