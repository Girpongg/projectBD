package com.example.projectbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class Scene11 implements Initializable {
    Metode metode;
    @FXML
    private TextField id;
    @FXML
    private TableView<Metode> data;
    @FXML
    private TextField namaMetode;
    @FXML
    private TableColumn<Metode,String> tbl_idMetode;
    @FXML
    private TableColumn<Metode ,String > tbl_namaMetode;

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
    protected void Tombolkembali(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);
    }

    @FXML
    public void addData() {
        String nameTod = namaMetode.getText();

        String query = String.format("INSERT INTO `metode_pembayaran`(`Nama_metode`) VALUES ('%s')",
                nameTod);

        if (!nameTod.equals(""))
            try {
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @FXML
    public void UpdateData() {
        String idd = id.getText();
        String name = namaMetode.getText();

        String query = String.format("UPDATE `metode_pembayaran` SET `Metode_id`='%s', `Nama_metode`='%s' WHERE Metode_id ='%s' AND Nama_metode ='%s'",
                idd, name, metode.getMetode(), metode.getNama());

        if (!name.equals(""))
            try {
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Data berhasil di Update");
                alert.show();
            } catch (Exception e) {
                e.printStackTrace();

            }
        refreshTable();
    }

    @FXML
    public void DeleteData() {
        String var = id.getText();

        String query = String.format("DELETE FROM `metode_pembayaran` WHERE Metode_id ='%s'",
                var);

        if (!var.equals(""))
            try {
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Berhasil dihapus");
                alert.show();
            } catch (Exception e) {
                e.printStackTrace();

            }
    }

    public void refreshTable() {
        tbl_idMetode.setCellValueFactory(new PropertyValueFactory<Metode,String>("metode"));
        tbl_namaMetode.setCellValueFactory(new PropertyValueFactory<Metode ,String>("nama"));

        String query = "SELECT * FROM `metode_pembayaran`";

        ObservableList<Metode> personList = FXCollections.observableArrayList();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()) {
                String metot = queryOutput.getString("Metode_id");
                String name = queryOutput.getString("Nama_metode");
                personList.add(new Metode(metot,name));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        data.setItems(personList);
    }

    public void getRowTable() {
        for (Metode person : data.getSelectionModel().getSelectedItems()) {
            id.setText((person.getMetode()));
            namaMetode.setText(person.getNama());
            metode = new Metode(person.getMetode(), person.getNama());
        }
    }
}
