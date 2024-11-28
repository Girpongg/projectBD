package com.example.projectbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class Scene10 {
    @FXML
    private TextField transaksi;


    @FXML
    private TableView<Transaksi> dataTV;

    @FXML
    private TextField id_metode;

    @FXML
    private TextField nominal;

    @FXML
    private DatePicker tanggal;

    @FXML
    private TableColumn<Transaksi, String> tbl_idMetode;

    @FXML
    private TableColumn<Transaksi, Integer> tbl_idTransaksi;

    @FXML
    private TableColumn<Transaksi, Integer> tbl_nominal;

    @FXML
    private TableColumn<Transaksi, String> tbl_tglPembayaran;
    DatabaseConnection connectNow;
    Connection connectDB;

    {
        connectNow = new DatabaseConnection();
        connectDB = connectNow.getConnection();
    }
    ObservableList<Transaksi> data = FXCollections.observableArrayList();


    public void initialize() {
        refreshTable();
    }



    @FXML
    protected void TombolBalik() {
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);
    }

    @FXML
    public void UpdateData() throws SQLException {
        int oi = Integer.parseInt(transaksi.getText());
        int nom = Integer.parseInt(nominal.getText());
        String tgl = String.valueOf(tanggal.getValue());
        int hm = Integer.parseInt(id_metode.getText());
        String query = String.format("UPDATE `transaksi` SET `Transaksi_id`='%d',`Nominal`='%d', `Tanggal_pembayaran`='%s', `Metode_id`='%d' WHERE Transaksi_id='%d' AND Nominal='%d' AND Tanggal_pembayaran='%s' AND Metode_id='%s'",
                oi,nom, tgl,hm,baru.getTransaksi_id(), baru.getNominal(), baru.getTanggalPembayaran(), baru.getMetodeId());

        if (!tgl.equals(""))
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Data berhasil di Update");
                alert.show();
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();

            }
    }

    @FXML
    public void DeleteData() throws SQLException {
        int var = Integer.parseInt(transaksi.getText());
        String query = String.format("DELETE FROM `transaksi` WHERE Transaksi_id ='%d'",
                var);
        Statement statement = connectDB.createStatement();
        statement.execute(query);
        refreshTable();
    }

    public void refreshTable() {
        tbl_idTransaksi.setCellValueFactory(new PropertyValueFactory<Transaksi, Integer>("transaksi_id"));
        tbl_nominal.setCellValueFactory(new PropertyValueFactory<Transaksi, Integer>("nominal"));
        tbl_tglPembayaran.setCellValueFactory(new PropertyValueFactory<Transaksi, String>("tanggalPembayaran"));
        tbl_idMetode.setCellValueFactory(new PropertyValueFactory<Transaksi, String>("metodeId"));

        String query = "SELECT * FROM transaksi";

        ObservableList<Transaksi> personList = FXCollections.observableArrayList();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()) {
                int idTransaksi = queryOutput.getInt("Transaksi_id");
                int nominal = queryOutput.getInt("Nominal");
                String tanggal = queryOutput.getString("Tanggal_pembayaran");
                String todId = queryOutput.getString("Metode_id");
                personList.add(new Transaksi(idTransaksi,nominal,tanggal,todId));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        dataTV.setItems(personList);
    }
    Transaksi baru;

    public void getRowTable() throws SQLException {
        for (Transaksi person : dataTV.getSelectionModel().getSelectedItems()) {
            transaksi.setText(String.valueOf(person.getTransaksi_id()));
            nominal.setText(String.valueOf(person.getNominal()));
            tanggal.setValue(LocalDate.parse(person.getTanggalPembayaran()));
            id_metode.setText(person.getMetodeId());
            baru = new Transaksi(person.getTransaksi_id(),person.getNominal(), person.getTanggalPembayaran(), person.getMetodeId());
        }
    }

    protected void tombolset(String teks){
       id_metode.setText(teks);

    }
    @FXML
    Button methh;
    @FXML
    protected void TombolkeMetode() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchMet.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)methh.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



}

