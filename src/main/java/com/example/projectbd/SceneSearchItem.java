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
import java.sql.Statement;
import java.util.ResourceBundle;

public class SceneSearchItem implements Initializable {
    @FXML
    private TableView<Item> dua;
    @FXML
    private TableColumn<Item,String> tbl_itemid;
    @FXML
    private TableColumn<Item ,String > tbl_nama;
    @FXML
    private TableColumn<Item ,Integer > tbl_harga;
    @FXML
    private TableColumn<Item ,String > tbl_size;
    @FXML
    private TableColumn<Item ,String > tbl_kategorid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshTable();
    }
    DatabaseConnection connectNow;
    Connection connectDB;
    {
        connectNow = new DatabaseConnection();
        connectDB =connectNow.getConnection();
    }
    public void refreshTable() {
        tbl_itemid.setCellValueFactory(new PropertyValueFactory<Item,String>("item"));
        tbl_nama.setCellValueFactory(new PropertyValueFactory<Item ,String>("namaitem"));
        tbl_harga.setCellValueFactory(new PropertyValueFactory<Item ,Integer>("harga"));
        tbl_size.setCellValueFactory(new PropertyValueFactory<Item ,String>("size"));
        tbl_kategorid.setCellValueFactory(new PropertyValueFactory<Item ,String>("idkategori"));

        String query = "SELECT * FROM `item`";

        ObservableList<Item> personList = FXCollections.observableArrayList();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()) {
                String metot = queryOutput.getString("Item_id");
                String name = queryOutput.getString("Nama_item");
                int metot1 = queryOutput.getInt("Harga");
                String name1 = queryOutput.getString("Size");
                String oi = queryOutput.getString("Kategori_id");

                personList.add(new Item(metot,name,metot1,name1,oi));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        dua.setItems(personList);
    }
    @FXML
    private TextField dipilih;

    public void getRowTable(){
        for (Item person : dua.getSelectionModel().getSelectedItems()) {
            dipilih.setText(person.getItem());
        }
    }
    public void repress(){
        refreshTable();
    }
    @FXML
    protected void Kembali(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene2 = app.getScene2();
        primaryStage.setScene(scene2);

        Scene2 scenecontroler2 = app.getSceneController2();
        refreshTable();
        String btext = dipilih.getText();
        scenecontroler2.tombolsetitem(btext);
    }




}
