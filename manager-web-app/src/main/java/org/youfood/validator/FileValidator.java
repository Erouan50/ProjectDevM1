package org.youfood.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.io.File;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@FacesValidator(value = "fileValidator")
public class FileValidator implements Validator {

    private static final long MAX_FILE_SIZE = 10485760L;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        File file = (File) o;
        if (file != null && file.length() > MAX_FILE_SIZE) {
            file.delete();
            throw new ValidatorException(new FacesMessage(String.format("File exceeds maximum permitted size od %d bytes.", MAX_FILE_SIZE)));
        }
    }
}
