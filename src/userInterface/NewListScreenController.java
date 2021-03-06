package userInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Airport;

import java.io.IOException;

public class NewListScreenController {

    private AirportScreenController asc;
    private Stage stage;
    private Airport airport;

    @FXML
    private TextField newListSize;

    @FXML
    void initialize(){

    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public void setAsc(AirportScreenController asc) {
        this.asc = asc;
    }

    @FXML
    void createClicked(ActionEvent event) {
        int size;

        try{
            size = Integer.parseInt(newListSize.getText());
            airport.createData(size, Airport.AIRPORT_AIRLINE_DATA, Airport.AIRPORT_CITY_DATA);
            airport.sortByTime();

            asc.addInformation();

            stage.close();
        }catch (NumberFormatException  | NegativeArraySizeException e){
            Alert a = new Alert(Alert.AlertType.ERROR, "Caused by: \n" + "The field to create a new list is empty or an invalid character has been entered.", ButtonType.CLOSE);
            a.setHeaderText("Please, to create a new list, enter a valid number");
            a.show();
        }catch (IOException e){
            Alert a = new Alert(Alert.AlertType.ERROR, "Caused by: \n" + "The files where the data is, has been deleted or moved to another destination");
            a.setHeaderText("Files not found");
            a.show();
        }
    }
}
