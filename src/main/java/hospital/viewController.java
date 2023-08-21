package hospital;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * viewController class, controls patient view page
 */
public class viewController implements Initializable {

    private int mrn;
    private String firstName, surname, initial;
    private String view;

    private ObservableList<String> medHistory = FXCollections.observableArrayList();
    private ObservableList<String> socHistory = FXCollections.observableArrayList();
    private ObservableList<String> surgHistory = FXCollections.observableArrayList();
    private ObservableList<String> medication = FXCollections.observableArrayList();
    private ObservableList<String> vitalSigns = FXCollections.observableArrayList();
    private ObservableList<String> allergies = FXCollections.observableArrayList();

    @FXML
    private ListView<String> historyList = null; 
    @FXML
    private Label title;
    @FXML
    private TextArea addText;
    /**
     * viewController constructor
     * @param mrn
     * @param firstName
     * @param surname
     * @param initial
     */
    public viewController(int mrn, String firstName, String surname, String initial) {
        this.mrn = mrn;
        this.firstName = firstName;
        this.surname = surname;
        this.initial = initial;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        title.setText("Record for " + surname + ", " + firstName + " " + initial + ".");

    }

    public void setView(String view) {
        this.view = view;
    }
    /**
     * uses patient info to display corresponding medical history
     */
    public void buildData() {
        medHistory.clear();
        historyList.getItems().clear();
        Connection connection = App.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlString = null;


        try {
            sqlString = "SELECT item FROM " + view + " WHERE mrn = ?";
            ps = connection.prepareStatement(sqlString);
            ps.setInt(1, mrn);
            rs = ps.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                medHistory.add(rs.getString(1));
            }
            historyList.getItems().addAll(medHistory);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void buildAllergies() {
        title.setText("Allergies for " + surname + ", " + firstName + " " + initial + ".");
        setView("allergy");
        buildData();
    }

    public void buildMedHistory() {
        title.setText("Medical History for " + surname + ", " + firstName + " " + initial + ".");
        setView("med_history");
        buildData();
    }

    public void buildVitalSigns() {
        title.setText("Vital Signs for " + surname + ", " + firstName + " " + initial + ".");
        setView("vitals");
        buildData();
    }

    public void buildSurgicalHistory() {
        title.setText("Surgical History for " + surname + ", " + firstName + " " + initial + ".");
        setView("surg_history");
        buildData();
    }

    public void buildSocialHistory() {
        title.setText("Social History for " + surname + ", " + firstName + " " + initial + ".");
        setView("soc_history");
        buildData();
    }

    public void buildMedication() {
        title.setText("Medication for " + surname + ", " + firstName + " " + initial + ".");
        setView("medication");
        buildData();
    }

    public void add() {
        Connection connection = App.connect();
        PreparedStatement ps = null;
        String sqlString = null;
        
        try {
            sqlString = "INSERT INTO " + view + " (mrn, item) VALUES (?, ?)";
            ps = connection.prepareStatement(sqlString);
            //ps.setString(1, view);
            ps.setInt(1, mrn);
            ps.setString(2, addText.getText());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        addText.clear();
        refresh();
    }

    public void refresh() {
        historyList.getItems().clear();
        buildData();
    }
 
    public void remove() {
        Connection connection = App.connect();
        PreparedStatement ps = null;
        String item = historyList.getSelectionModel().getSelectedItem();
        String sqlString = "DELETE FROM " + view + " WHERE item = ?";
        try {
            ps = connection.prepareStatement(sqlString);
            ps.setString(1, item);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        refresh();
    }

}
