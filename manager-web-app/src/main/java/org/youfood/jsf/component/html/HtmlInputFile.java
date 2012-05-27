package org.youfood.jsf.component.html;

import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@FacesComponent(value = "HtmlInputFile")
public class HtmlInputFile extends HtmlInputText{

    @Override
    public String getRendererType() {
        return "javax.faces.File";
    }
}
