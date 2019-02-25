module control {
    exports backendapi;
    exports frontendapi;
    exports frontendapi.rotate_angle_calls;
    exports frontendapi.move_to_position_calls;
    exports frontendapi.move_distance_calls;
    exports frontendapi.query_calls;
    exports frontendapi.turtle_visibility_calls;
    exports frontendapi.set_pen_calls;
    requires backend;
    requires frontend;
    requires javafx.graphics;
}