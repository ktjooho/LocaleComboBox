LocaleComboBox Add-on for Vaadin 8
=============
A Server-Side combo-box component which shows png-image based flag icon with country name. 

![juho](http://i.imgur.com/CgAuHlE.png)

## Notice
<p> This component is used with <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Locale.html">Locale</a> (pure Java class). </p>
<p> Please make sure that your Locale object uses <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2#Current_codes">ISO 3166 2-letter country code</a>. </p>
<p> It displays locales that are mapped with default locale.</p>

## Run Demo
```bash
git clone https://github.com/ktjooho/LocaleComboBox
mvn clean install
cd locale-combobox
mvn jetty:run
```
To see the demo, navigate to http://localhost:8000/

## Usage

``` java
final LocaleComboBox comboBox = new LocaleComboBox("Locale ComboBox");
comboBox.setVisibleLocales(Locale.KOREA, Locale.getAvailableLocales());
comboBox.setVisibleLocales(Locale.CHINA, Locale.FRANCE, Locale.CANADA, Locale.JAPAN);
comboBox.setVisibleLocales(Locale.CANADA, new Locale("en","GR"));
```

## Styling
<p> There are two icon styles ; flat, shiny.</p>
<p> Default style is shiny. </p>

``` java
  final LocaleComboBox comboBox = new LocaleComboBox("Locale ComboBox");
  comboBox.setIconStyle(LocaleComboBox.IconStyle.FLAT);
  comboBox.setIconStyle(LocaleComboBox.IconStyle.SHINY);
```

## License
Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.
