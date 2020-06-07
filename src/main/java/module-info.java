module sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.dataformat.xml;
    requires poi.ooxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens sample to com.fasterxml.jackson.databind, javafx.fxml;
    exports sample;
}