module com.example.spotify {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires mysql.connector.j;
    requires java.sql;

    opens com.example.spotify.controlador.controlador to javafx.fxml, javafx.controls, javafx.base, mysql.connector.j, java.sql;

    exports com.example.spotify.controlador.controlador;
    exports com.example.spotify.controlador.modelo;
    opens com.example.spotify.controlador.modelo to java.sql, javafx.base, javafx.controls, javafx.fxml, mysql.connector.j;
}