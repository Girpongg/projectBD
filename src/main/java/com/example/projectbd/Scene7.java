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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Scene7 implements Initializable {
    job job;
    @FXML
    private TextField jobId;
    @FXML
    private TextField namaJob;
    @FXML
    private TableView<job> dataTV;
    @FXML
    private TableColumn<job, String> tbl_jobId;
    @FXML
    private TableColumn<job, String> tbl_namaJob;
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
    @FXML
    protected void TombolBalik(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);
    }
    @FXML
    public void addData(){
        String name2 = namaJob.getText();

        String query = String.format("INSERT INTO `job`(`Nama_job`) VALUES ('%s')",
                name2);

        if(!name2.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void UpdateData(){
        String name = jobId.getText();
        String name2 = namaJob.getText();

        String query = String.format("UPDATE`job` SET `Job_id`='%s',`Nama_job`='%s'  WHERE Job_id='%s' AND " +
                        "Nama_job='%s'" ,
                name,name2,job.getJobid(),job.getNamajob());

        if(!name2.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void DeleteData(){
        String name = jobId.getText();
        String name2 = namaJob.getText();

        String query = String.format("DELETE FROM`job` WHERE Job_id='%s'AND Nama_job='%s'" ,
                name,name2);

        if(!name2.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void getRowTable(){
        for (job person : dataTV.getSelectionModel().getSelectedItems()) {
            jobId.setText(person.getJobid());
            namaJob.setText(person.getNamajob());

            job = new job(person.getJobid(), person.getNamajob());
        }
    }
    public void refreshTable(){
        tbl_jobId.setCellValueFactory(new PropertyValueFactory<job, String>("jobid"));
        tbl_namaJob.setCellValueFactory(new PropertyValueFactory<job, String>("namajob"));


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
        dataTV.setItems(personList);
    }

}
