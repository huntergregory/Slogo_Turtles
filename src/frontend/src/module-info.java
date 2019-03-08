module frontend {
    exports ui;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;

    requires backend;
    requires state_manager;
}