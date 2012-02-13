package org.youfood.module;

import com.google.inject.Module;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class YouFoodWebSiteModule implements YouFoodModule {
    @Override
    public Set<Module> getModules() {
        Set<Module> modules = new LinkedHashSet<Module>();
        modules.add(new VaadinModule());
        return modules;
    }

    @Override
    public ModulePriority getPriorityLevel() {
        return ModulePriority.LOW;
    }
}
