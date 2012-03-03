package org.youfood.cook.components;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import org.youfood.cook.events.ButtonListEvent;
import org.youfood.cook.listeners.ButtonListListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class ButtonList extends CustomComponent implements Button.ClickListener {

    private VerticalLayout verticalLayout;
    private List<ButtonListListener> buttonListListeners;

    public ButtonList() {
        buttonListListeners = new ArrayList<ButtonListListener>();
        verticalLayout = new VerticalLayout();
        initLayout();
        setCompositionRoot(verticalLayout);
    }

    public ButtonList(Map<Long, String> values) {
        this();
        initButtons(values);
    }

    private void initLayout() {
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);
    }

    private void initButtons(Map<Long, String> values) {
        for (Map.Entry<Long, String> value : values.entrySet()) {
            Button button = createButton(value.getKey(), value.getValue());
            verticalLayout.addComponent(button);
        }
    }

    public void addButton(Long id, String value) {
        Button button = createButton(id, value);
        verticalLayout.addComponentAsFirst(button);
    }

    public void addButtonListListener(ButtonListListener buttonListListener) {
        buttonListListeners.add(buttonListListener);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Long id = (Long) event.getButton().getData();
        String value = event.getButton().getCaption();
        ButtonListEvent listEvent = new ButtonListEvent(id, value);
        for (ButtonListListener buttonListListener : buttonListListeners) {
            buttonListListener.buttonListClick(listEvent);
        }
    }

    private Button createButton(Long id, String value) {
        Button button = new Button(value);
        button.setData(id);
        button.setWidth(100, UNITS_PERCENTAGE);
        button.setHeight(60, UNITS_PIXELS);
        button.addListener(this);
        return button;
    }
}
