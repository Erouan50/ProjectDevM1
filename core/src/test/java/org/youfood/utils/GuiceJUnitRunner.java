package org.youfood.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import java.lang.annotation.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class GuiceJUnitRunner extends BlockJUnit4ClassRunner {

    private Injector injector;

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    public @interface GuiceModules {
        Class<?>[] value();
    }

    public GuiceJUnitRunner(Class<?> klass) throws Exception {
        super(klass);
        Class<?>[] classes = getModulesFor(klass);
        injector = createInjectorFor(classes);
    }

    @Override
    protected Object createTest() throws Exception {
        Object o = super.createTest();
        injector.injectMembers(o);
        return o;
    }

    private Injector createInjectorFor(Class<?>[] classes) throws Exception {
        Set<Module> modules = new HashSet<Module>(classes.length);
        for (Class aClass : classes) {
            try {
                modules.add((Module) aClass.newInstance());
            } catch (InstantiationException e) {
                throw new Exception(e);
            } catch (IllegalAccessException e) {
                throw new Exception(e);
            }
        }
        return Guice.createInjector(modules);
    }

    private Class<?>[] getModulesFor(Class<?> klass) throws InitializationError {
        GuiceModules annotations = klass.getAnnotation(GuiceModules.class);
        if (annotations == null) {
            throw new InitializationError("Missing @GuiceModules annotations for unit test");
        }
        return annotations.value();
    }
}
