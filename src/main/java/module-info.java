module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires json.simple;

    opens application to javafx.fxml;
    exports application;
}