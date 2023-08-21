package hospital;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JavaFX App, main class
 * @author Sofia
 */
public class App extends Application {

    private static Scene scene;
    private static Connection connection = null;
    

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("patientTable"), 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    /**
     * connect method to connect to mysql database
     * @return connection
     */
    public static Connection connect() {
        String path = "jdbc:mysql://localhost:3306/hospital";
        String username = "root";
        String password = "sJj95-JWhw96-dMM97-SV98-d99";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(path, username, password);

            return connection;
        } catch (Exception e) {
            System.out.println(e);
        } 
        return connection;
    }

    public static void main(String[] args) {
        launch();

    }

}