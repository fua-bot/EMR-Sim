module hospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens hospital to javafx.fxml;
    exports hospital;
}
