package com.example.projectbd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.reflect.Method;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SceneAnalisis implements Initializable {

    @FXML
    private Button Kembali;

    @FXML
    private TextField maxItem;

    @FXML
    private TextField Method;

    @FXML
    private TextField no1;

    @FXML
    private TextField no2;

    @FXML
    private TextField no3;

    DatabaseConnection connectNow;

    Connection connectDB;

    {
        connectNow = new DatabaseConnection();
        connectDB = connectNow.getConnection();
    }

    @FXML
    protected void KembaliMain() {
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);

    }
    @FXML
    protected void Generate() throws SQLException {
        maxMethod();
        maxitem();
        top3cust();
    }



    @FXML
    protected void maxMethod() throws SQLException {
        String sql = "SELECT mp.Nama_metode " +
                "FROM transaksi t " +
                "JOIN metode_pembayaran mp ON t.Metode_id = mp.Metode_id " +
                "GROUP BY mp.Nama_metode " +
                "ORDER BY COUNT(*) DESC " +
                "LIMIT 1";

        Statement statement = connectDB.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            String namaMetode = resultSet.getString(1);
            Method.setText(namaMetode);
        } else {
            Method.setText("No data");
        }
    }

    @FXML
    protected void maxitem() throws SQLException {
        String sql = "Select Nama_item FROM item i JOIN pesanan p on i.Item_id = p.Item_id\n" +
                "GROUP BY Nama_item ORDER BY COUNT(*) DESC LIMIT 1";

        Statement statement = connectDB.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            String namaMetode = resultSet.getString(1);
            maxItem.setText(namaMetode);
        } else {
            maxItem.setText("No data");
        }
    }

    @FXML
    protected void top3cust() throws SQLException {
        String sql = "Select CONCAT(First_name,' ',Last_name) FROM customer c JOIN pesanan p on c.Customer_id = p.Customer_id GROUP BY First_name ORDER BY COUNT(*) DESC LIMIT 3;";

        Statement statement = connectDB.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        StringBuilder namesBuilder = new StringBuilder();
        while (resultSet.next()) {
            String fullName = resultSet.getString("CONCAT(First_name,' ',Last_name)");
            namesBuilder.append(fullName).append("  ");
        }

        String names = namesBuilder.toString().trim();

        // Menampilkan hasil di dalam text field
        no1.setText(names);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            top3cust();
            maxitem();
            maxMethod();
            Generate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
