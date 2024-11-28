package com.example.projectbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Scene8{

    Pegawai peg;

    @FXML
    private TextField job;
    @FXML
    private TextField Salary;

    @FXML
    private TableView<Pegawai> dataTV;

    @FXML
    private Button delete;

    @FXML
    private TextField firstName;

    @FXML
    private DatePicker hireDate;

    @FXML
    private TextField id;
    @FXML
    private TextField baru;

    @FXML
    private TextField lastName;

    @FXML
    private TextField noTelp;

    @FXML
    private TableColumn<Pegawai, String> tbl_firstName;

    @FXML
    private TableColumn<Pegawai, String> tbl_hireDate;

    @FXML
    private TableColumn<Pegawai, String> tbl_idPegawai;

    @FXML
    private TableColumn<Pegawai, String> tbl_lastName;

    @FXML
    private TableColumn<Pegawai, String> tbl_noTelp;

    @FXML
    private TableColumn<Pegawai, String> tbl_salary;

    @FXML
    private TableColumn<Pegawai, String> tbl_job;

    @FXML
    private TextField IdPeg;


    DatabaseConnection connectNow;
    Connection connectDB;
    {
        connectNow = new DatabaseConnection();
        connectDB =connectNow.getConnection();
    }
    @FXML
    public void initialize() {
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
    public void addData() throws SQLException {
        String name = firstName.getText();
        String name2 = lastName.getText();
        Integer alamat1 = Integer.valueOf(Salary.getText());
        String nowa = noTelp.getText();
        LocalDate hire = hireDate.getValue();
        String hai = baru.getText();
        String query = String.format("INSERT INTO `pegawai`(`First_name`, `last_name`,`Hire_date`,`No_telp`,`Salary`,`Job_id`) VALUES ('%s','%s','%s','%s','%d','%s')",
                name,name2,hire,nowa,alamat1,hai);

        if(!name2.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    protected void tombolset(String teks){
        baru.setText(teks);

    }


    public void UpdateData() throws SQLException {
        String id2 = IdPeg.getText();
        String name = firstName.getText();
        String name2 = lastName.getText();
        Integer alamat1 = Integer.valueOf(Salary.getText());
        String nowa = noTelp.getText();
        LocalDate hire = hireDate.getValue();
        String oi = baru.getText();

        String query = String.format("UPDATE `pegawai` SET `Pegawai_id`='%s',`First_name`='%s',`last_name`='%s',`Hire_date`='%s',`No_telp`='%s',`Salary`='%d',`Job_id`='%s' WHERE Pegawai_id='%s'AND First_name='%s'AND `last_name`='%s' AND Hire_date='%s' AND No_telp='%s' AND Salary='%d' AND Job_id='%s'", id2,name,name2,hire,nowa,alamat1,oi,peg.getPegawai_id(),peg.getFirst_name(),peg.getLastname(),peg.getHire_date(),peg.getNotelp(),peg.getSalary(),peg.getJob_id());

        if(!name.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void DeleteData(){
        String IDp = IdPeg.getText();

        String name2 = lastName.getText();
        String query = String.format("DELETE FROM `pegawai` WHERE Pegawai_id='%s'" ,
                IDp);

        if(!name2.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void getRowTable() throws SQLException {
        for (Pegawai person : dataTV.getSelectionModel().getSelectedItems()) {
            IdPeg.setText((person.getPegawai_id()));
            firstName.setText(person.getFirst_name());
            lastName.setText(person.getLastname());
            hireDate.setValue(LocalDate.parse(person.getHire_date()));
            noTelp.setText(person.getNotelp());
            Salary.setText(String.valueOf(person.getSalary()));
            baru.setText(person.getJob_id());
            peg = new Pegawai(person.getPegawai_id(), person.getFirst_name(),person.getLastname(), person.getHire_date(), person.getNotelp(), person.getSalary(), person.getJob_id() );
        }
    }
    public void refreshTable(){
        tbl_idPegawai.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("Pegawai_id"));
        tbl_firstName.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("First_name"));
        tbl_lastName.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("lastname"));
        tbl_hireDate.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("hire_date"));
        tbl_noTelp.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("notelp"));
        tbl_salary.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("Salary"));
        tbl_job.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("job_id"));


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
        dataTV.setItems(personList);
    }
    @FXML
    Button jobi;
    @FXML
    protected void Tombolkejob() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchJob.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) jobi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
