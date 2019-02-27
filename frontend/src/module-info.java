module frontend {
    exports ui_public;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;

    requires backend;
}