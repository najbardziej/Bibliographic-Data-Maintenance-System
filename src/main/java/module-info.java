module com.bibliographicdatamaintenance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires poi.ooxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires jackson.annotations;

    opens com.bibliographicdatamaintenance to com.fasterxml.jackson.databind, javafx.fxml, javafx.graphics, javafx.base;
}