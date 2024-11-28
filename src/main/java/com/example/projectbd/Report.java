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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Report implements Initializable {
    @FXML
    TextField AnnualSales = new TextField();


    @FXML
    private TableColumn<Bulan, String> NamaBulan;

    @FXML
    private TableView<Bulan> DataTV;

    @FXML
    private TableColumn<Bulan, Integer> SalesBulan;

    @FXML
    private Button gen;

    DatabaseConnection connectNow;

    Connection connectDB;

    {
        connectNow = new DatabaseConnection();
        connectDB = connectNow.getConnection();
    }

    ObservableList<Bulan> bln = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
// Initialize Table Column with the name of the Column
        try {
            GenerateSales();
            showAnnualSales();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static  ArrayList<String>daftarBulan = new ArrayList<>();
    public static void ArrayListAdded() {
        daftarBulan.add("Januari");
        daftarBulan.add("Februari");
        daftarBulan.add("Maret");
        daftarBulan.add("April");
        daftarBulan.add("Mei");
        daftarBulan.add("Juni");
        daftarBulan.add("Juli");
        daftarBulan.add("Agustus");
        daftarBulan.add("September");
        daftarBulan.add("Oktober");
        daftarBulan.add("November");
        daftarBulan.add("Desember");
    }
    @FXML
    protected  void GenerateSales() throws SQLException {
        NamaBulan.setCellValueFactory(new PropertyValueFactory<Bulan, String>("bulan"));
        SalesBulan.setCellValueFactory(new PropertyValueFactory<Bulan, Integer>("Sales"));

        ArrayListAdded();
        for (int i = 0; i < daftarBulan.size() ; i++) {
            try {
                bln.add(new Bulan(daftarBulan.get(i),findSales(i)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            DataTV.setItems(bln);
        }
    }
    @FXML
    protected void showAnnualSales() throws SQLException {
        String sql = "SELECT SUM(Nominal) From transaksi";

        Statement statement = connectDB.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            String annual = resultSet.getString(1);
            AnnualSales.setText(annual);
        } else {
            AnnualSales.setText("0");
        }
    }
    @FXML
    protected void RefreshSales() throws SQLException {
        showAnnualSales();
        for (int i = 0; i < daftarBulan.size() ; i++) {
            try {
                bln.set(i,new Bulan(daftarBulan.get(i),findSales(i)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            DataTV.setItems(bln);
        }
    }

    @FXML
    protected void Kembali() {
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);

    }
    @FXML
    protected int findSales(int i) throws SQLException {
        String sql = "SELECT SUM(Nominal) FROM transaksi WHERE " +
                "EXTRACT(MONTH FROM Tanggal_pembayaran)=0"+(i+1);

        Statement statement = connectDB.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }
}
