package hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * addPatient Controller class, controls add patient screen
 */
public class addPatientController {

    private Connection connection = null;
    private PreparedStatement ps;

    @FXML
    private TextField surname_txt, first_txt, initial_txt, dob_txt, mrn_txt;
    @FXML
    private Button submit;

    @FXML
    public void submit() {
        connection = App.connect();
        try {
            String sqlString = "INSERT INTO patients (surname, first_name, initial, dob) VALUES (?, ?, ?, ?)";
            ps = connection.prepareStatement(sqlString);
            ps.setString(1, surname_txt.getText());
            ps.setString(2, first_txt.getText());
            ps.setString(3, initial_txt.getText());
            ps.setString(4, dob_txt.getText());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }    

}