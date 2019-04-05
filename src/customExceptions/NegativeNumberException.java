package customExceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class NegativeNumberException extends Exception {

    public NegativeNumberException() {
        super("Please, to create a new list, enter a valid number");
    }

    public void message(){
        Alert a = new Alert(Alert.AlertType.ERROR, "Caused by: \n" + "The size of the new List is a negative number", ButtonType.CLOSE);
        a.setHeaderText(super.getMessage());
        a.show();
    }
}
