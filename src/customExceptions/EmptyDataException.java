package customExceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class EmptyDataException extends Exception {

    public EmptyDataException() {
        super("To perform a new search, you must fill in all the fields.");
    }

    public void message(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Caused by \n" + "The fields to carry out the search are not properly filled out.", ButtonType.CLOSE);
        alert.setHeaderText(super.getMessage());
        alert.show();
    }
}
