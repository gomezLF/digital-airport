package userInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Airport;
import model.Flight;

import java.io.IOException;

public class AirportScreenController {

    private Airport airport;


    @FXML
    private TableView<Flight> dataTable;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> timeColumn;

    @FXML
    private TableColumn<?, ?> airlineColumn;

    @FXML
    private TableColumn<?, ?> flightColumn;

    @FXML
    private TableColumn<?, ?> destinationColumn;

    @FXML
    private TableColumn<?, ?> gateColumn;

    @FXML
    private TextField seeker;

    @FXML
    private ComboBox<?> criteriaBox;

    @FXML
    private Label timeLabel;


    @FXML
    void initialize() {
        airport = new Airport();
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @FXML
    void aboutClicked(ActionEvent event) {

    }

    @FXML
    void closeClicked(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void instructionsClicked(ActionEvent event) {

    }

    @FXML
    void newListClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("NewListScreen.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.setTitle("New List Screen");
        stage.show();

        NewListScreenController ns= loader.getController();
        ns.setAirport(this.airport);
        ns.setStage(stage);
    }

    @FXML
    void nextClicked(ActionEvent event) {

    }

    @FXML
    void previousClicked(ActionEvent event) {

    }

    @FXML
    void searchClicked(ActionEvent event) {

    }

    @FXML
    void sortByAirline(ActionEvent event) {

    }

    @FXML
    void sortByCity(ActionEvent event) {

    }

    @FXML
    void sortByFlight(ActionEvent event) {

    }

    @FXML
    void sortByGate(ActionEvent event) {

    }

    @FXML
    void sortBySchedule(ActionEvent event) {

    }

    @FXML
    void sortByTime(ActionEvent event) {

    }

}
