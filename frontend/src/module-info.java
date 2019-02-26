module frontend {
    exports ui_public;
    exports ui_public.api.move_to_position_calls;
    exports ui_public.api.query_calls;
    exports ui_public.api.rotate_angle_calls;
    exports ui_public.api.move_distance_calls;
    requires externalAPICall;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
}