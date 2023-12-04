module com.example.spotify {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires mysql.connector.j;
    requires jlayer;

    opens com.example.spotify to java.sql, javafx.base, javafx.controls, javafx.fxml, mysql.connector.j;
    opens com.example.spotify.modelo to java.sql, javafx.base, javafx.controls, javafx.fxml, mysql.connector.j;
    opens com.example.spotify.controlador to javafx.fxml, javafx.controls, javafx.base, mysql.connector.j, java.sql;

    exports com.example.spotify;
    exports com.example.spotify.modelo;
    exports com.example.spotify.controlador;
}