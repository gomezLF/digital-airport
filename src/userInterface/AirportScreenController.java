package userInterface;

import Threads.GUIUpdateControlThread;
import Threads.TimeThread;
import customExceptions.EmptyDataException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Airport;
import model.Flight;

import java.io.IOException;
import java.util.Calendar;

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

        GUIUpdateControlThread gut = new GUIUpdateControlThread(this);
        gut.setDaemon(true);
        gut.start();

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
        switch (criteriaBox.getValue()){
            case "Search by Airline":

                break;

            case "Search by Date":
                try {
                    airport.searchByDate(seeker.getText());
                } catch (EmptyDataException e) {
                    e.message();
                }catch (NullPointerException e){
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "The previously searched flight does not exist, please search again with existing values.", ButtonType.OK);
                    a.setHeaderText("Flight not found");
                    a.show();
                }
                break;

            case "Search by Destination city":

                break;

            case "Search by Flight Number":

                break;

            case "Search by Gate":

                break;

            case "Search by Time":

                break;
        }
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
        airport.sortByGate();
        addInformation();
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

    public void updateTime(){
        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second =  calendar.get(Calendar.SECOND);

        timeLabel.setText(hour + ":" + minute + ":" + second);
    }
}
