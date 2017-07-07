package com.vaadin.addon.locale.demo;

import com.vaadin.addon.locale.LocaleComboBox;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.Arrays;
import java.util.Locale;

@Theme("demo")
@Title("LocaleComboBox Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        Locale.setDefault(Locale.CANADA);
        final String status = "Current Locale : ";

        final LocaleComboBox flagComboBox = new LocaleComboBox(status + Locale.getDefault().getDisplayCountry(), Arrays.asList(Locale.CANADA,Locale.KOREA));
        Arrays.stream(Locale.getAvailableLocales()).forEach(locale -> flagComboBox.setVisibleLocales(locale, Locale.getAvailableLocales()));
        flagComboBox.setIconStyle(LocaleComboBox.IconStyle.SHINY);

        flagComboBox.addValueChangeListener(item -> {
            final Locale locale = item.getValue();
            Locale.setDefault(locale);
            flagComboBox.setCaption(status + locale.getDisplayCountry());
            flagComboBox.onCurrentLocaleChanged();
        });

        flagComboBox.setVisibleLocales(Locale.KOREA, new Locale("en","GR"));

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.addComponent(flagComboBox);
        layout.setComponentAlignment(flagComboBox, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }
}
