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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Scene6 implements Initializable{
    DatabaseConnection connectNow;
    @FXML
    private TableView<Promo> dataTV;
    @FXML
    private TableColumn<Promo, String> id;
    @FXML
    private TableColumn<Promo, String> nama;
    @FXML
    private TableColumn<Promo, String> start;
    @FXML
    private TableColumn<Promo, String> end;
    @FXML
    private TableColumn<Promo, Integer> potongan;
    @FXML
     TextField coba;


    Connection connectDB;
    {
        connectNow = new DatabaseConnection();
        connectDB =connectNow.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();
        }
    public void refreshTable(){
        id.setCellValueFactory(new PropertyValueFactory<Promo, String>("id"));
        nama.setCellValueFactory(new PropertyValueFactory<Promo, String>("nama"));
        start.setCellValueFactory(new PropertyValueFactory<Promo, String>("start"));
        end.setCellValueFactory(new PropertyValueFactory<Promo, String>("end"));
        potongan.setCellValueFactory(new PropertyValueFactory<Promo,Integer>("potongan"));

        String query = "SELECT * FROM promo";

        ObservableList<Promo> personList = FXCollections.observableArrayList();

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                String name = queryOutput.getString("Promo_id");
                String age = queryOutput.getString("Nama_promo");
                String mulai = queryOutput.getString("Promo_start");
                String akhir = queryOutput.getString("Promo_end");
                int harga = Integer.parseInt(queryOutput.getString("Potongan_harga"));
                personList.add(new Promo(name,age,mulai,akhir,harga));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        dataTV.setItems(personList);
    }
    @FXML
    protected void Kembali(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene3 = app.getScene3();
        primaryStage.setScene(scene3);

        Scene3 scenecontroler3 = app.getSceneController3();
        refreshTable();
        String btext = coba.getText();
        scenecontroler3.tombolset(btext);

    }
    public void getRowTable(){
        for (Promo person : dataTV.getSelectionModel().getSelectedItems()) {
            coba.setText(person.getId());
        }
    }

    public void setTextFieldFromScene1(TextField textField) {
        for (Promo person : dataTV.getSelectionModel().getSelectedItems()) {
            textField.setText(person.getId());
        }

    }

}
