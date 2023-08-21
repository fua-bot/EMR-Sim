package hospital;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * patient class
 * @author Sofia
 */
public class Patient {

    private StringProperty surname, firstName, initial, dob, mrn;
    /**
     * Patient constructor
     * @param surname
     * @param firstName
     * @param initial
     * @param dob
     * @param mrn
     */
    public Patient(String surname, String firstName, String initial, String dob, String mrn) {
        this.surname = new SimpleStringProperty((String) surname);
        this.firstName = new SimpleStringProperty((String) firstName);
        this.initial = new SimpleStringProperty((String) initial);
        this.dob = new SimpleStringProperty((String) dob);
        this.mrn = new SimpleStringProperty((String) mrn);
    }


    public StringProperty surnameProp() {
        return surname;
    }

    public StringProperty nameProp() {
        return firstName;
    }

    public StringProperty initialProp() {
        return initial;
    }

    public StringProperty dobProp() {
        return dob;
    }

    public StringProperty mrnProp() {
        return mrn;
    }
}