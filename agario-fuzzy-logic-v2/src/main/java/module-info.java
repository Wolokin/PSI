module com.example.agariofuzzylogicv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires jFuzzyLogic;

    opens agariofuzzylogic to javafx.fxml;
    exports agariofuzzylogic;
}