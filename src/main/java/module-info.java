module local.bbpos.security.authentication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens local.bbpos.security.authentication to javafx.fxml;
    exports local.bbpos.security.authentication;
    requires com.fasterxml.uuid;
    requires log4j.api;
}
