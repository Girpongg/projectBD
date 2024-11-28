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

public class SceneSearchPeg implements Initializable {
    @FXML
    private TableView<Pegawai> data;

    @FXML
    private TableColumn<Pegawai, String> tbl_first;

    @FXML
    private TableColumn<Pegawai, String> tbl_hire;

    @FXML
    private TableColumn<Pegawai, String> tbl_Pegawai;

    @FXML
    private TableColumn<Pegawai, String> tbl_last;

    @FXML
    private TableColumn<Pegawai, String> tbl_no;

    @FXML
    private TableColumn<Pegawai, String> tbl_salar;

    @FXML
    private TableColumn<Pegawai, String> tbl_jobb;

    DatabaseConnection connectNow;
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
        tbl_Pegawai.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("Pegawai_id"));
        tbl_first.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("First_name"));
        tbl_last.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("lastname"));
        tbl_hire.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("hire_date"));
        tbl_no.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("notelp"));
        tbl_salar.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("Salary"));
        tbl_jobb.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("job_id"));


        String query = "SELECT * FROM pegawai";

        ObservableList<Pegawai> personList = FXCollections.observableArrayList();

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                String ID = queryOutput.getString("Pegawai_id");
                String name1 = queryOutput.getString("First_name");
                String name2= queryOutput.getString("last_name");
                String hire= queryOutput.getString("Hire_date");
                String Nowa= queryOutput.getString("No_telp");
                int gaji = Integer.parseInt(queryOutput.getString("Salary"));
                String age2 = queryOutput.getString("Job_id");
                personList.add(new Pegawai(ID,name1,name2,hire,Nowa,gaji,age2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        data.setItems(personList);
    }
    @FXML
    private TextField pilihan;
    @FXML
    protected void Kembali(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);

        Scene2 scenecontroler2 = app.getSceneController2();
        refreshTable();
        String btext = pilihan.getText();
        scenecontroler2.tombolpegawai(btext);
    }
    public void getRowTable(){
        for (Pegawai person : data.getSelectionModel().getSelectedItems()) {
            pilihan.setText(person.getPegawai_id());
        }
    }
    public void repress(){
        refreshTable();
    }



}
