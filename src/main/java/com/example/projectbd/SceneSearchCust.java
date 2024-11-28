package com.example.projectbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SceneSearchCust implements Initializable {
    @FXML
    private TableView<Customer> satu;
    @FXML
    private TableColumn<Customer,String> tbl_cus;
    @FXML
    private TableColumn<Customer ,String > tbl_first;
    @FXML
    private TableColumn<Customer ,String > tbl_last;
    @FXML
    private TableColumn<Customer ,String > tbl_alamatt;
    @FXML
    private TableColumn<Customer ,Integer > tbl_nowaa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshTable();
    }
    DatabaseConnection connectNow;
    Connection connectDB;
    {
        connectNow = new DatabaseConnection();
        connectDB =connectNow.getConnection();
    }

    public void refreshTable() {
        tbl_cus.setCellValueFactory(new PropertyValueFactory<Customer,String>("id"));
        tbl_first.setCellValueFactory(new PropertyValueFactory<Customer ,String>("First_name"));
        tbl_last.setCellValueFactory(new PropertyValueFactory<Customer ,String>("Last_name"));
        tbl_alamatt.setCellValueFactory(new PropertyValueFactory<Customer ,String>("alamat"));
        tbl_nowaa.setCellValueFactory(new PropertyValueFactory<Customer ,Integer>("no_wa"));

        String query = "SELECT * FROM `customer`";

        ObservableList<Customer> personList = FXCollections.observableArrayList();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()) {
                String metot = queryOutput.getString("Customer_id");
                String name = queryOutput.getString("First_name");
                String metot1 = queryOutput.getString("Last_name");
                String name1 = queryOutput.getString("Alamat");
                int oi = queryOutput.getInt("No_telp");

                personList.add(new Customer(metot,name,metot1,name1,oi));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        satu.setItems(personList);
    }
    @FXML
    private TextField Puji;
    @FXML
    protected void Kembali(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);

        Scene2 scenecontroler2 = app.getSceneController2();
        refreshTable();
        String btext = Puji.getText();
        scenecontroler2.tombolset(btext);
    }
    public void getRowTable(){
        for (Customer person : satu.getSelectionModel().getSelectedItems()) {
            Puji.setText(person.getId());
        }
    }
    public void repress(){
        refreshTable();
    }


}

