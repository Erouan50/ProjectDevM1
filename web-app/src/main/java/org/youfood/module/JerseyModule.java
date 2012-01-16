package org.youfood.module;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.apache.log4j.Logger;
import org.youfood.resources.HelloWorld;

/**
 * @author: Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class JerseyModule extends JerseyServletModule {

    private static final Logger LOG = Logger.getLogger(JerseyModule.class);

    @Override
    protected void configureServlets() {
        LOG.info("Init Jersey module");
        bind(HelloWorld.class);
        serve("/resources/*").with(GuiceContainer.class);
    }
}