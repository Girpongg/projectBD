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

public class SceneSearchJob implements Initializable {

    DatabaseConnection connectNow;
    @FXML
    private TableView<job> DataTV;

    @FXML
    private Button OK;

    @FXML
    private Button Refresh;

    @FXML
    private TextField Select;

    @FXML
    private TableColumn<Kategori, String> tbl_jobId;

    @FXML
    private TableColumn<Kategori, String> tbl_jobnama;


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
        tbl_jobId.setCellValueFactory(new PropertyValueFactory<Kategori, String>("jobid"));
        tbl_jobnama.setCellValueFactory(new PropertyValueFactory<Kategori, String>("namajob"));


        String query = "SELECT * FROM job";

        ObservableList<job> personList = FXCollections.observableArrayList();

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                String name = queryOutput.getString("Job_id");
                String age = queryOutput.getString("Nama_job");
                personList.add(new job(name,age));
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
        Scene scene8 = app.getScene8();
        primaryStage.setScene(scene8);
        refreshTable();

        Scene8 scenecontroler8 = app.getSceneController8();
        refreshTable();
        String btext = Select.getText();
        scenecontroler8.tombolset(btext);
    }
    public void getRowTable(){
        for (job person : DataTV.getSelectionModel().getSelectedItems()) {
            Select.setText(person.getJobid());
        }
    }}
