package org.youfood.module;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.vaadin.Application;
import org.apache.log4j.Logger;
import org.youfood.configuration.GuiceApplicationServlet;
import org.youfood.pages.MainPage;

/**
 * @author: Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class VaadinModule extends ServletModule {

    private static final Logger LOGGER = Logger.getLogger(VaadinModule.class);

    @Override
    protected void configureServlets() {
//        serveRegex("^/.*", "!^/resources/.*").with(GuiceApplicationServlet.class);
        serve("/*").with(GuiceApplicationServlet.class);
        bind(Application.class).to(MainPage.class);
        bindConstant().annotatedWith(Names.named("welcome")).to("Main page");
        LOGGER.info(getServletContext().getContextPath());
    }
}
