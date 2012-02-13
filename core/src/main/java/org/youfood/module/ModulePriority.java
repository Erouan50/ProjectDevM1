package org.youfood.module;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public enum ModulePriority {

    HIGH(2), NORMAL(1), LOW(0);

    private int priorityLevel;

    private ModulePriority(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }
}
