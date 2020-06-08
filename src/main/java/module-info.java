module com.bibliographicdatamaintenance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires poi.ooxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;

    exports com.bibliographicdatamaintenance to com.fasterxml.jackson.databind, javafx.fxml;
}