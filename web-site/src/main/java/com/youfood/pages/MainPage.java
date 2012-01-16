package com.youfood.pages;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import java.util.Date;

/**
 * @author: Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class MainPage extends Application {
    @Override
    public void init() {
        final Window window = new Window("YouFood");
        Label label = new Label("Hello world !");
        window.addComponent(label);
        window.addComponent(new Button("What time is it?", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                window.showNotification("The time is " + new Date());
            }
        }));
        setMainWindow(window);

        GridLayout labelgrid = new GridLayout(2, 1);

        labelgrid.addComponent(new Label("CONTENT_DEFAULT"));
        labelgrid.addComponent(
                new Label("This is a label in default mode: <plain text>",
                        Label.CONTENT_DEFAULT));

        labelgrid.addComponent(new Label("CONTENT_PREFORMATTED"));
        labelgrid.addComponent(
                new Label("This is a preformatted label.\n" +
                        "The newline character \\n breaks the line.",
                        Label.CONTENT_PREFORMATTED));

        labelgrid.addComponent(new Label("CONTENT_RAW"));
        labelgrid.addComponent(
                new Label("This is a label in raw mode.<br>It can contain, " +
                        "for example, unbalanced markup.",
                        Label.CONTENT_RAW));

        labelgrid.addComponent(new Label("CONTENT_TEXT"));
        labelgrid.addComponent(
                new Label("This is a label in (plain) text mode",
                        Label.CONTENT_TEXT));

        labelgrid.addComponent(new Label("CONTENT_XHTML"));
        labelgrid.addComponent(
                new Label("<i>This</i> is an <b>XHTML</b> formatted label",
                        Label.CONTENT_XHTML));

        labelgrid.addComponent(new Label("CONTENT_XML"));
        labelgrid.addComponent(
                new Label("This is an <myelement>XML</myelement> " +
                        "formatted label",
                        Label.CONTENT_XML));

        window.addComponent(labelgrid);
    }
}
