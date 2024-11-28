package com.example.projectbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class SceneSearchMet2 implements Initializable {

    @FXML
    private TableView<Metode> DataTV;

    @FXML
    private Button OK;

    @FXML
    private Button Repres;

    DatabaseConnection connectNow;
    @FXML
    private TextField Select;

    @FXML
    private TableColumn<Metode, String> tb_id;

    @FXML
    private TableColumn<Metode, String> tbl_metod;

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
        tb_id.setCellValueFactory(new PropertyValueFactory<Metode, String>("metode"));
        tbl_metod.setCellValueFactory(new PropertyValueFactory<Metode, String>("nama"));
        String query = "SELECT * FROM metode_pembayaran";

        ObservableList <Metode> personList = FXCollections.observableArrayList();

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                String name = queryOutput.getString("Metode_id");
                String age = queryOutput.getString("Nama_metode");
                personList.add(new Metode(name,age));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        DataTV.setItems(personList);
    }

    @FXML
    protected void Kembali(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);
        refreshTable();

        Scene2 scenecontroler2= app.getSceneController2();
        refreshTable();
        String btext = Select.getText();
        scenecontroler2.tombolset1(btext);
    }
    public void getRowTable(){
        for (Metode metod : DataTV.getSelectionModel().getSelectedItems()) {
            Select.setText(metod.getMetode());
        }
    }

    public void repress(){
        refreshTable();
    }
}