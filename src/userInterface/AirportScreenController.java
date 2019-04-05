package userInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Airport;
import model.Flight;

import java.io.IOException;

public class AirportScreenController {

    private Airport airport;


    @FXML
    private TableView<Flight> dataTable;

    @FXML
    private TableColumn<Flight, String> dateColumn;

    @FXML
    private TableColumn<Flight, String> timeColumn;

    @FXML
    private TableColumn<Flight, String> airlineColumn;

    @FXML
    private TableColumn<Flight, String> flightColumn;

    @FXML
    private TableColumn<Flight, String> destinationColumn;

    @FXML
    private TableColumn<Flight, String> gateColumn;

    @FXML
    private TextField seeker;

    @FXML
    private ComboBox<String> criteriaBox;

    @FXML
    private Label timeLabel;


    @FXML
    void initialize() {
        airport = new Airport();

        criteriaBox.getItems().addAll("Search by Airline", "Search by Date", "Search by Destination city", "Search by Flight Number", "Search by Gate", "Search by Time");

        dateColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        airlineColumn.setCellValueFactory(new PropertyValueFactory<>("airline"));
        flightColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destinationCity"));
        gateColumn.setCellValueFactory(new PropertyValueFactory<>("gate"));
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
        ns.setAsc(this);
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

    public void addInformation(){

        ObservableList<Flight> data = FXCollections.observableList(airport.getFlightList());
        dataTable.setItems(data);

    }

}
