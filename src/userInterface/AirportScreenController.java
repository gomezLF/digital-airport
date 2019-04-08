package userInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Airport;
import model.Flight;
import model.Time;

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
        timeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("time"));
        airlineColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("airline"));
        flightColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightNumber"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("destinationCity"));
        gateColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("gate"));
    }

    @FXML
    void closeClicked(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void newListClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setResizable(false);
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
        airport.sortByAirline();
        addInformation();
    }

    @FXML
    void sortByCity(ActionEvent event) {
        airport.sortByDestination();
        addInformation();
    }

    @FXML
    void sortByFlight(ActionEvent event) {
        airport.sortByFlightNumber();
        addInformation();
    }

    @FXML
    void sortByGate(ActionEvent event) {

    }

    @FXML
    void sortByDate(ActionEvent event) {
        airport.sortByDate();
        addInformation();
    }

    @FXML
    void sortByTime(ActionEvent event) {
        airport.sortByTime();
        addInformation();
    }

    public void addInformation(){
        ObservableList<Flight> data = FXCollections.observableList(airport.getFlightList());
        dataTable.setItems(data);
    }






}
