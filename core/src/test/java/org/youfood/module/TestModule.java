package org.youfood.module;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.youfood.integrationtest.MenuIntegrationTest;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        requestStaticInjection(MenuIntegrationTest.class);
        install(new JPAModule("YouFood-PU-Test"));
    }
}
