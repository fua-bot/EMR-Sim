package hospital;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * PatientTableController controls the main patient table page
 * @author Sofia
 */

public class PatientTableController implements Initializable {

    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    
    private ObservableList<Patient> patientList = FXCollections.observableArrayList();
    private ObservableList<String> choices = FXCollections.observableArrayList("Name", "MRN");
    private String searchType = "Name";
    @FXML
    private TableView<Patient> tableview;
    @FXML
    private TableColumn<Patient, String> id, surname, first_name, initial, dob, mrn;
    @FXML
    private ChoiceBox<String> searchBy;
    @FXML
    private TextField searchBox;
    
    /**
     * itialize method sets default value for search, calls buildData, and sets cell properties
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        searchBy.setValue("Name");
        searchBy.getItems().addAll(choices);
        searchBy.setOnAction(this::setSearchType);

        surname.setCellValueFactory(cellData -> cellData.getValue().surnameProp());
        first_name.setCellValueFactory(cellData -> cellData.getValue().nameProp());
        initial.setCellValueFactory(cellData -> cellData.getValue().initialProp());
        dob.setCellValueFactory(cellData -> cellData.getValue().dobProp());
        mrn.setCellValueFactory(cellData -> cellData.getValue().mrnProp());
        buildData();
    }

    public PatientTableController() {

    }

    /**
     * buildData method gets full list of patients from database to display
     */
    public void buildData(){

        connection = App.connect();
        ResultSet rs = null;

        try {
            String q = "SELECT * FROM patients";
            preparedStatement = connection.prepareStatement(q);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                
                patientList.add(new Patient(  
                    rs.getString("surname"), 
                    rs.getString("first_name"), 
                    rs.getString("initial"),
                    rs.getString("dob"), 
                    rs.getString("mrn")));
                    
            }

            //obsList.add(row);
            tableview.setItems(patientList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * addPatientScreen opens add patient screen 
     */       
    public void addPatientScreen() {
        Scene scene;
        try {
            scene = new Scene(loadFXML("addPatient"), 355, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }  
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void remove() {
        connection = App.connect();

        Patient patient = tableview.getSelectionModel().getSelectedItem();
        String sqlString = "DELETE FROM patients WHERE mrn = ?";
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, Integer.parseInt(patient.mrnProp().get()));
            preparedStatement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        refresh();
    }
    /**
     * view opens a view page for the selected patient, shows history
     * @throws IOException
     */
    public void view() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Patient patient = tableview.getSelectionModel().getSelectedItem();
        viewController vc = new viewController(
                            Integer.parseInt(patient.mrnProp().get()), 
                            patient.nameProp().get(),
                            patient.surnameProp().get(),
                            patient.initialProp().get());
        fxmlLoader.setController(vc);
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void setSearchType(ActionEvent event) {
        searchType = searchBy.getValue();
        System.out.println(searchBy.getValue());
    }
    /**
     * searches through patient database to filter tableview where patient names matches any entered name
     */
    public void search() {

        connection = App.connect();
        ResultSet rs = null;
        ObservableList<Patient> filteredList = FXCollections.observableArrayList();
        PreparedStatement ps = null;

        switch(searchType) {
            case "Name":
                String name = searchBox.getText();
                String[] nameArray = name.split(" ");
                String q = "SELECT * FROM PATIENTS WHERE first_name = ? OR surname = ?";
                for (int i = 1; i < nameArray.length; i++) {
                    q = q + " OR first_name = ? OR surname = ?";
                }
                try {
                    ps = connection.prepareStatement(q);
                    for (int i = 0; i < nameArray.length; i++) {
                        ps.setString(2 * i + 1, nameArray[i]);
                        ps.setString(2 * i + 2, nameArray[i]);
                    }
                    System.out.println(nameArray[1]);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        filteredList.add(new Patient(  
                            rs.getString("surname"), 
                            rs.getString("first_name"), 
                            rs.getString("initial"),
                            rs.getString("dob"), 
                            rs.getString("mrn")));
                            
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "MRN":
                String mrn = searchBox.getText();
                q = "SELECT * FROM PATIENTS WHERE mrn = ?";

                try {
                    ps = connection.prepareStatement(q);
                    ps.setInt(1, Integer.parseInt(mrn));
                    rs = ps.executeQuery();
                    System.out.println(ps);
                    while (rs.next()) {
                            filteredList.add(new Patient(  
                                rs.getString("surname"), 
                                rs.getString("first_name"), 
                                rs.getString("initial"),
                                rs.getString("dob"), 
                                rs.getString("mrn")));
                            
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
                
        }
        tableview.setItems(filteredList);
    }

//clears and then rebuilds data, this is inefficient so to be changed
    public void refresh() {
        searchBox.clear();
        tableview.getItems().clear();
        buildData();
        tableview.getItems().clear();
        buildData();
    }

}
