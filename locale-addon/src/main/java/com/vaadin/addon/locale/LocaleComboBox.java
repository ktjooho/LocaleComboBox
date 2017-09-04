package com.vaadin.addon.locale;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ApplicationConstants;
import com.vaadin.ui.ComboBox;


import java.util.*;
import java.util.stream.Collectors;

public class LocaleComboBox extends ComboBox<Locale> {

   private IconStyle iconStyle = IconStyle.SHINY;

    public IconStyle getIconStyle() {
        return iconStyle;
    }

    public void setIconStyle(IconStyle iconStyle) {
        this.iconStyle = iconStyle;
        setIcons();
    }
    private void setIcons() {
        setItemIconGenerator(locale -> new FlagIcon(locale, IconSize.SIZE_24_24, iconStyle));
    }

    public void setVisibleLocales(Locale... locales) {
        setVisibleLocales(Arrays.asList(locales));
    }
    public void setVisibleLocales(Collection<Locale> locales) {
        final int ISO_CODE_LENGTH = 2;
        Locale defaultLocale = Locale.getDefault();

        LinkedList<Locale> availableLocaleList = new LinkedList<>(
                locales.stream()
                .filter(l -> l.getCountry().length() == ISO_CODE_LENGTH || l.equals(defaultLocale))
                .distinct()
                .sorted(Comparator.comparing(Locale::getDisplayCountry,String::compareTo))
                .collect(Collectors.toList())
        );
        availableLocaleList.addFirst(defaultLocale);
        setItems(availableLocaleList);
        setSelectedItem(defaultLocale);
    }

    public LocaleComboBox(String caption) {
        super(caption);
        init();
    }

    public LocaleComboBox(String caption, Collection<Locale> options) {
        super(caption, options);
        init(options);
    }

    public LocaleComboBox() {
        super();
        init();
    }

    private void init() {
        init(Arrays.asList());
    }
    private void init(Collection<Locale> locales) {
        setIcons();
        setItemCaptionGenerator(Locale::getDisplayCountry);
        setVisibleLocales(locales);
    }
    private static final String ICON_PATH = ApplicationConstants.VAADIN_PROTOCOL_PREFIX + "addons/locale/flags-iso/";

    public class FlagIcon extends ExternalResource {
        public FlagIcon(Locale locale, IconSize iconSize, IconStyle iconStyle) {
            super(ICON_PATH + iconStyle + "/"
                            + iconSize.getValue() + "/"
                            + locale.getCountry().toUpperCase() + ".png"
                    ,null);
        }
        @Override
        public String getMIMEType() {
            return "image/png";
        }
    }

    public enum IconStyle {
        FLAT("flat"), SHINY("shiny");
        private String value;
        IconStyle(String value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum IconSize {
        SIZE_16_16(16), SIZE_24_24(24),
        SIZE_48_48(48), SIZE_32_32(32),
        SIZE_64_64(64);
        private int value;
        public int getValue() {
            return value;
        }
        IconSize(int value) {
            this.value = value;
        }
    }
}