package com.github.uuidcode.core.config;

import java.beans.PropertyEditorSupport;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.github.uuidcode.core.util.CoreUtil;

@ControllerAdvice
public class BinderConfiguration {
    public static class StringEditor extends PropertyEditorSupport {
        public StringEditor() {
            super();
        }

        public StringEditor(Object source) {
            super(source);
        }

        public void setAsText(String value) {
            if (CoreUtil.isEmpty(value)) {
                this.setValue(null);
            } else {
                setValue(value.trim());
            }
        }

        public String getAsText() {
            Object value = this.getValue();

            if (value == null) {
                return null;
            }

            return value.toString();
        }
    }

    @InitBinder
    public void binder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringEditor());
    }
}
