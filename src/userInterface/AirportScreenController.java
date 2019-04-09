package userInterface;

import threads.GUIUpdateControlThread;
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
import java.util.List;

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
        if (airport.getFlightList().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "There is no list created to search for an item.", ButtonType.OK);
            alert.setHeaderText("Please, create a new List to search an item");
            alert.show();

        }else {
            Flight f = null;
            try {
                switch (criteriaBox.getValue()){
                    case "Search by Airline":
                        searchAirline();
                        break;
                    case "Search by Date":
                        searchDate();
                        break;
                    case "Search by Destination city":
                        searchCity();
                        break;
                    case "Search by Flight Number":
                        searchFlightNumber();
                        break;
                    case "Search by Gate":
                        searchGate();
                        break;
                    case "Search by Time":
                        searchTime();
                        break;
                }
            }catch (NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Caused by \n" + "Criteria fild is empty", ButtonType.CLOSE);
                alert.setHeaderText("To search an element in the list, fill all the fields");
                alert.show();
            }
        }
    }

    @FXML
    void sortByAirline(ActionEvent event) {
        airport.sortByAirline();
        dataTable.getItems().clear();
        addInformation(airport.getFlightList());
    }

    @FXML
    void sortByCity(ActionEvent event) {
        airport.sortByDestination();
        dataTable.getItems().clear();
        addInformation(airport.getFlightList());
    }

    @FXML
    void sortByFlight(ActionEvent event) {
        airport.sortByFlightNumber();
        dataTable.getItems().clear();
        addInformation(airport.getFlightList());
    }

    @FXML
    void sortByGate(ActionEvent event) {
        airport.sortByGate();
        dataTable.getItems().clear();
        addInformation(airport.getFlightList());
    }

    @FXML
    void sortByDate(ActionEvent event) {
        airport.sortByDate();
        dataTable.getItems().clear();
        addInformation(airport.getFlightList());
    }

    @FXML
    void sortByTime(ActionEvent event) {
        airport.sortByTime();
        dataTable.getItems().clear();
        addInformation(airport.getFlightList());
    }

    private void searchAirline(){
        try {
            Flight f = airport.searchByAirline(seeker.getText());
            dataTable.getItems().clear();
            dataTable.getItems().add(f);
        } catch (EmptyDataException e) {
            e.message();
        }
    }

    private void searchDate(){
        try {
            Flight f = airport.searchByDate(seeker.getText());
            dataTable.getItems().clear();
            dataTable.getItems().add(f);
        } catch (EmptyDataException e) {
            e.message();
        }
    }

    private void searchCity(){
        try {
            Flight f = airport.searchByCity(seeker.getText());
            dataTable.getItems().clear();
            dataTable.getItems().add(f);
        } catch (EmptyDataException e) {
            e.message();
        }
    }

    private void searchFlightNumber(){
        try {
            Flight f = airport.searchByFlightNumber(seeker.getText());
            dataTable.getItems().clear();
            dataTable.getItems().add(f);
        } catch (EmptyDataException e) {
            e.message();
        }
    }

    private void searchGate(){
        try {
            Flight f = airport.searchByGate(Integer.parseInt(seeker.getText()));
            dataTable.getItems().clear();
            dataTable.getItems().add(f);
        } catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Caused by: \n" + "The field to search an element is empty or an invalid character has been entered.", ButtonType.CLOSE);
            a.setHeaderText("Please, to search the gate, enter a valid gate number");
            a.show();
        }
    }

    private void searchTime(){
        try {
            Flight f = airport.searchByTime(seeker.getText());
            dataTable.getItems().clear();
            dataTable.getItems().add(f);
        } catch (EmptyDataException e) {
            e.message();
        }
    }

    public void addInformation(List<Flight> list){
        ObservableList<Flight> data = FXCollections.observableList(list);
        dataTable.getItems().addAll(data);
    }

    public void updateTime(){
        Calendar calendar = Calendar.getInstance();

        String hour = "" +  calendar.get(Calendar.HOUR);
        String minute = "" +  calendar.get(Calendar.MINUTE);
        String second = "" +   calendar.get(Calendar.SECOND);

        if (Integer.parseInt(hour) < 10){
            hour = "0" + hour;
        }
        if (Integer.parseInt(minute) < 10){
            minute = "0" + minute;
        }
        if (Integer.parseInt(second) < 10){
            second = "0" + second;
        }
        if (hour.equals("00")){
            hour = "12";
        }

        timeLabel.setText(hour + ":" + minute + ":" + second);
    }
}
