package org.youfood.module;

import java.util.Comparator;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class YouFoodModuleComparator implements Comparator<YouFoodModule> {
    @Override
    public int compare(YouFoodModule youFoodModule, YouFoodModule youFoodModule1) {
        if (youFoodModule.getPriorityLevel() == youFoodModule1.getPriorityLevel()) {
            return 0;
        }
        return youFoodModule.getPriorityLevel().getPriorityLevel() > youFoodModule1.getPriorityLevel().getPriorityLevel() ? -1 : 1;
    }
}
