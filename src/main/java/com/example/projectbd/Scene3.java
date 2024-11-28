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
import java.util.ArrayList;
import java.util.List;
public class Scene3{
    @FXML
    private TextField IdKategori;
    @FXML
    private TextField Nama_Kategori;
    @FXML
    private TableView<Kategori> dataTV;
    @FXML
    private TableColumn<Kategori, String> agesCol;
    @FXML
    private TableColumn<Kategori, String> agesCol2;
    @FXML
    private TableColumn<Kategori, String> agesCol3;
    Kategori baru;

    DatabaseConnection connectNow;

    @FXML
    TextField tambahan;
    ObservableList<Kategori> data = FXCollections.observableArrayList();

    Connection connectDB;
    {
        connectNow = new DatabaseConnection();
        connectDB =connectNow.getConnection();
    };
    @FXML
    protected void initialize() {
        refreshTable();
    }


    public void refreshTable(){
        String query = "SELECT * FROM kategori_barang";
        ObservableList<Kategori> kategoris = FXCollections.observableArrayList();
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                String name = queryOutput.getString("Kategori_id");
                String age = queryOutput.getString("Nama_kategori");
                String age2 = queryOutput.getString("Promo_id");
                kategoris.add(new Kategori(name,age,age2));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataTV.setItems(kategoris);

        agesCol.setCellValueFactory(new PropertyValueFactory<Kategori, String>("Kategori_id"));
        agesCol2.setCellValueFactory(new PropertyValueFactory<Kategori, String>("Nama_Kategori"));
        agesCol3.setCellValueFactory(new PropertyValueFactory<Kategori, String>("Promo_id"));
    }


    public void addData() throws SQLException {
        String name = Nama_Kategori.getText();
        String tambah = tambahan.getText();

        String query = String.format("INSERT INTO `kategori_barang`(`Nama_kategori`,`Promo_id`) VALUES ('%s','%s')",
                name,tambah);

        if(!name.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
        refreshTable();
        Nama_Kategori.setText("");
        IdKategori.setText("");



    }
    @FXML
    protected void TombolButton(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);

    }

    protected void tombolset(String teks){
        tambahan.setText(teks);

    }
    @FXML
    Button promo;
    @FXML
    protected void TombolPromo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene6.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) promo.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void getRowTable() throws SQLException {
        for (Kategori person : dataTV.getSelectionModel().getSelectedItems()) {
            IdKategori.setText(person.getKategori_id());
            Nama_Kategori.setText(person.getNama_Kategori());
            tambahan.setText(person.getPromo_id());
            baru = new Kategori(person.getKategori_id(), person.getNama_Kategori(),person.getPromo_id());
        }

    }
    @FXML
    public void UpdateData() throws SQLException {
        String name = IdKategori.getText();
        String name2 = Nama_Kategori.getText();
        String update = tambahan.getText();
        String query = String.format("UPDATE `kategori_barang` SET `Kategori_id`='%s',`Nama_kategori`='%s',`Promo_id`='%s' WHERE Kategori_id ='%s' AND Nama_kategori ='%s' AND Promo_id ='%s' ",
                name,name2,update,baru.getKategori_id(),baru.getNama_Kategori(),baru.getPromo_id());

        if(!name2.equals("") )
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Data berhasil di Update");
                alert.show();
            }
            catch (Exception e) {
                e.printStackTrace();

            }


    }@FXML
    public void DeleteData(){
        String name = IdKategori.getText();
        String query = String.format("DELETE FROM `kategori_barang`WHERE Kategori_id ='%s' ",
                name);

        if(!name.equals("") )
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                statement.close();
                refreshTable();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Berhasil dihapus");
                alert.show();}
            catch (Exception e) {
                e.printStackTrace();

            }
    }}
