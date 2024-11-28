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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Scene9 implements Initializable {
    @FXML
    private TextField First_name;

    @FXML
    private TextField Last_name;

    @FXML
    private TextField idud;

    @FXML
    private TextField No_WA;

    @FXML
    private TextField alamat;
    @FXML
    private TableView<Customer> satu;
    @FXML
    private TableColumn<Customer,String> tbl_idcus;
    @FXML
    private TableColumn<Customer ,String > tbl_firstcus;
    @FXML
    private TableColumn<Customer ,String > tbl_lastcus;

    @FXML
    private TableColumn<Customer ,String > tbl_alamat;

    @FXML
    private TableColumn<Customer ,Integer > tbl_nowa;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshTable();
    }


    public void refreshTable() {
        tbl_idcus.setCellValueFactory(new PropertyValueFactory<Customer,String>("id"));
        tbl_firstcus.setCellValueFactory(new PropertyValueFactory<Customer ,String>("First_name"));
        tbl_lastcus.setCellValueFactory(new PropertyValueFactory<Customer ,String>("Last_name"));
        tbl_alamat.setCellValueFactory(new PropertyValueFactory<Customer ,String>("alamat"));
        tbl_nowa.setCellValueFactory(new PropertyValueFactory<Customer ,Integer>("no_wa"));

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



    DatabaseConnection connectNow;
    Connection connectDB;
    {
        connectNow = new DatabaseConnection();
        connectDB =connectNow.getConnection();
    }
    @FXML
    public void addData(){
        String name = First_name.getText();
        String name2 = Last_name.getText();
        String alamat1 = alamat.getText();
        String nowa = No_WA.getText();

        String query = String.format("INSERT INTO `customer`(`First_name`, `Last_name`,`Alamat`,`No_telp`) VALUES ('%s','%s','%s','%s')",
                name,name2,alamat1,nowa);

        if(!name.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
        refreshTable();
    }
    Customer cus;
    public void getRowTable(){
        for (Customer person : satu.getSelectionModel().getSelectedItems()) {
            idud.setText(person.getId());
            First_name.setText(person.getFirst_name());
            Last_name.setText(person.getLast_name());
            No_WA.setText(String.valueOf(person.getNo_wa()));
            alamat.setText(person.getAlamat());

            cus = new Customer(person.getId(), person.getFirst_name(), person.getLast_name(), person.getAlamat(), person.getNo_wa());
        }
    }
    @FXML
    protected void Tombolkedua(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);
    }

    public void DeleteData() throws SQLException {
        String id1 = idud.getText();
        String nama = First_name.getText();

        String query = String.format("DELETE FROM`customer` WHERE Customer_id ='%s'" ,
                id1);
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();


}public void UpdateData(){
        String id = idud.getText();
        String name = First_name.getText();
        String name2 = Last_name.getText();
        String alamat1 = alamat.getText();
        String nowa = No_WA.getText();

        String query = String.format("UPDATE`customer` SET `Customer_id`='%s',`First_name`='%s',`Last_name`='%s',`Alamat`='%s',`No_telp`='%s'  WHERE `Customer_id`='%s' AND `First_name`='%s' AND `Last_name`='%s' AND `Alamat`='%s' AND `No_telp`='%s'" ,
                id,name,name2,alamat1,nowa,cus.getId(),cus.getFirst_name(),cus.getLast_name(),cus.getAlamat(),cus.getNo_wa());

        if(!name.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
        refreshTable();
    }



}
