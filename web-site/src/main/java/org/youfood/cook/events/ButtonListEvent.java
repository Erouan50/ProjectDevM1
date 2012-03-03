package org.youfood.cook.events;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class ButtonListEvent {

    private Long id;
    private String value;

    public ButtonListEvent(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
