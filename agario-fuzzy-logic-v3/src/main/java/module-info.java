module agario.agariofuzzylogicv3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens agario.agariofuzzylogicv3 to javafx.fxml;
    exports agario.agariofuzzylogicv3;
}