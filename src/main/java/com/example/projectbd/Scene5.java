package com.example.projectbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class Scene5  implements Initializable{
    @FXML
    private Promo personTemp;
    @FXML
    private DatePicker oi ;
    @FXML
    private DatePicker oi2 ;

    @FXML
    private TextField idpromo;

    @FXML
    private TextField namapromo;

    @FXML
    private TextField potongan;

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
    private TableColumn<Promo, Integer> harga;
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
    public void addData(){
        String name2 = namapromo.getText();
        LocalDate date = oi.getValue();
        LocalDate date1 = oi2.getValue();
        Integer pot = Integer.valueOf(potongan.getText());

        String query = String.format("INSERT INTO `promo`(`Nama_promo`,`Promo_start`,`Promo_end`,`Potongan_harga`) VALUES ('%s','%s','%s','%d')",
                name2,date,date1,pot);

        if(!name2.equals("") )
             try{
                 if (oi2.getValue().isBefore(oi.getValue())) {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("Warning");
                     alert.setHeaderText("Salah input tanggal");
                     alert.show();}
                 else if(oi2.getValue().isEqual(oi.getValue())){
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                     alert.setTitle("Error");
                     alert.setHeaderText("Tanggal mulai dan selesai tidak boleh sama");
                     alert.show();}
                 else {
                     Statement statement = connectDB.createStatement();
                     statement.execute(query);
                     refreshTable();
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Success");
                     alert.setHeaderText("Data berhasil di input");
                     alert.show();


                 }
            } catch (Exception e) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("ERROR");
                 alert.setHeaderText("ERROR coba lagi");
                 alert.show();

    }refreshTable();
    }
    @FXML
    protected void TombolBack(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);
        refreshTable();
    }
    public void refreshTable(){
        id.setCellValueFactory(new PropertyValueFactory<Promo, String>("id"));
        nama.setCellValueFactory(new PropertyValueFactory<Promo, String>("nama"));
        start.setCellValueFactory(new PropertyValueFactory<Promo, String>("start"));
        end.setCellValueFactory(new PropertyValueFactory<Promo, String>("end"));
        harga.setCellValueFactory(new PropertyValueFactory<Promo,Integer>("Potongan"));

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
    public void getRowTable(){
        for (Promo person : dataTV.getSelectionModel().getSelectedItems()) {
            idpromo.setText(person.getId());
            namapromo.setText(person.getNama());
            oi.setValue(LocalDate.parse(person.getStart()));
            oi2.setValue(LocalDate.parse(person.getEnd()));
            potongan.setText(String.valueOf(person.getPotongan()));

            personTemp = new Promo(person.getId(), person.getNama(),person.getStart(),person.getEnd(), person.getPotongan());
        }
    }
    @FXML
    public void UpdateData(){
        String name = idpromo.getText();
        String name2 = namapromo.getText();
        LocalDate date = oi.getValue();
        LocalDate date1 = oi2.getValue();
        Integer pot = Integer.valueOf(potongan.getText());

        String query = String.format("UPDATE `promo` SET `Promo_id`='%s',`Nama_promo`='%s',`Promo_start`='%s',`Promo_end`='%s',`Potongan_harga`='%d' WHERE Promo_id ='%s' AND Nama_promo ='%s' AND Promo_start ='%s' AND Promo_end ='%s' AND Potongan_harga='%d'",
                name,name2,date,date1,pot, personTemp.getId(), personTemp.getNama(),personTemp.getStart(),personTemp.getEnd(),personTemp.getPotongan());

        if(!name.equals("") )
            try{
                if (oi2.getValue().isBefore(oi.getValue())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Salah input tanggal");
                    alert.show();}
                else if(oi2.getValue().isEqual(oi.getValue())){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Tanggal mulai dan selesai tidak boleh sama");
                    alert.show();}
                else {
                    Statement statement = connectDB.createStatement();
                    statement.execute(query);
                    refreshTable();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Data berhasil di Update");
                    alert.show();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Primary key Sudah ada");
                alert.show();}

            }
    @FXML
    public void DeleteData(){
        String name = idpromo.getText();
        String name2 = namapromo.getText();
        LocalDate date = oi.getValue();
        LocalDate date1 = oi2.getValue();
        Integer pot = Integer.valueOf(potongan.getText());

        String query = String.format("DELETE FROM `promo` WHERE Promo_id ='%s' AND Nama_promo ='%s' AND Promo_start ='%s' AND Promo_end ='%s'  AND Potongan_harga ='%d' ",
                name,name2,date,date1,pot);

        if(!name.equals("") )
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Berhasil dihapus");
                alert.show();}
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Delete ERROR");
                alert.show();}


    }}





