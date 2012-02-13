package org.youfood.module;

import com.google.inject.Module;

import java.util.Set;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public interface YouFoodModule {

    Set<Module> getModules();

    ModulePriority getPriorityLevel();
}
