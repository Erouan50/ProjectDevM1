package org.youfood.module;

import com.google.inject.Module;

import javax.inject.Singleton;
import java.util.*;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Singleton
public class YouFoodModuleLoader {

    private ServiceLoader<YouFoodModule> loader;

    public YouFoodModuleLoader() {
        this.loader = ServiceLoader.load(YouFoodModule.class);
    }

    public Set<Module> getYouFoodModules() {
        Set<YouFoodModule> youFoodModules = new TreeSet<YouFoodModule>(new YouFoodModuleComparator());
        Set<Module> modules = new LinkedHashSet<Module>();
        for (YouFoodModule youFoodModule : loader) {
            youFoodModules.add(youFoodModule);
        }
        for (YouFoodModule youFoodModule : youFoodModules) {
            for (Module module : youFoodModule.getModules()) {
                modules.add(module);
            }
        }
        return modules;
    }
}
