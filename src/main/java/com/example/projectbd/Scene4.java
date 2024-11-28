package com.example.projectbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Scene4  {
    @FXML
    private TextField item_id;
    @FXML
    private TextField nama_item;
    @FXML
    private TextField tulisanharga;
    @FXML
    private TextField kategori;
    @FXML
    private TextField size;
    @FXML
    private TableColumn<Item, String> item;
    @FXML
    private TableColumn<Item, String> namaitem;
    @FXML
    private TableColumn<Item, Integer> harga;
    @FXML
    private TableColumn<Item, Integer> id;
    @FXML
    private TableColumn<Item, String> sez;
    @FXML
    private TableView<Item> dataTV;
    Item baru;

    DatabaseConnection connectNow;

    Connection connectDB;

    {
        connectNow = new DatabaseConnection();
        connectDB =connectNow.getConnection();
    }
    protected void tombolset(String teks){
        kategori.setText(teks);

    }
    public void addData() throws SQLException {

        String name = nama_item.getText();
        int promo = Integer.parseInt(tulisanharga.getText());
        int oi = Integer.parseInt(kategori.getText());
        String siz = size.getText();
        String query = String.format("INSERT INTO `item`(`Nama_item`,`Harga`,`Size`,`Kategori_id`) VALUES ('%s','%d','%s','%d')",
                name,promo,siz,oi);

        if(!name.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Data berhasil di Input");
                alert.show();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR coba lagi");
                alert.show();
            }
    }
    public void initialize() {
        refreshTable();
    }

    public void refreshTable(){
        item.setCellValueFactory(new PropertyValueFactory<Item, String>("item"));
        namaitem.setCellValueFactory(new PropertyValueFactory<Item, String>("namaitem"));
        harga.setCellValueFactory(new PropertyValueFactory<Item, Integer>("harga"));
        sez.setCellValueFactory(new PropertyValueFactory<Item,String>("size"));
        id.setCellValueFactory(new PropertyValueFactory<Item, Integer>("idkategori"));

        String query = "SELECT * FROM item";

        ObservableList<Item> kategoris = FXCollections.observableArrayList();

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                String name = queryOutput.getString("Item_id");
                String age = queryOutput.getString("Nama_item");
                int age2 = Integer.parseInt(queryOutput.getString("Harga"));
                String age3 = queryOutput.getString("Size");
                String age4 = String.valueOf(Integer.parseInt(queryOutput.getString("Kategori_id")));
                kategoris.add(new Item(name,age,age2,age3,age4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        dataTV.setItems(kategoris);

    }
    public void getRowTable() throws SQLException {
        for (Item person : dataTV.getSelectionModel().getSelectedItems()) {
            item_id.setText(person.getItem());
            nama_item.setText(person.getNamaitem());
            tulisanharga.setText(String.valueOf(person.getHarga()));
            size.setText(person.getSize());
            kategori.setText(person.getIdkategori());
            baru = new Item(person.getItem(), person.getNamaitem(),person.getHarga(), person.getSize(), person.getIdkategori());
        }


    }  @FXML
        protected void Tombolbackitem(){
            HelloApplication app = HelloApplication.getApplicationInstance();
            Stage primaryStage = app.getPrimaryStage();
            Scene scene2 = app.getScene2();
            primaryStage.setScene(scene2);
}
    public void Updatedata() throws SQLException {
        String eh = item_id.getText();
        String name = nama_item.getText();
        int promo = Integer.parseInt(tulisanharga.getText());
        String siz = size.getText();
        String hm = kategori.getText();
        String query = String.format("UPDATE `item` SET `Item_id`='%s',`Nama_item`='%s',`Harga`='%d',`Size`='%s',`Kategori_id`='%s' " +
                        "WHERE Item_id='%s' AND Nama_item='%s' AND Harga='%d' AND Size='%s' AND Kategori_id='%s' ",
                eh,name,promo,siz,hm,baru.getItem(),baru.getNamaitem(),baru.getHarga(),baru.getSize(),baru.getIdkategori());

        if(!name.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Data berhasil di Update");
                alert.show();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Update ERROR");
                alert.show();
            }
    }
    public void Deletedata() throws SQLException {
        String eh = item_id.getText();
        String name = nama_item.getText();
        int promo = Integer.parseInt(tulisanharga.getText());
        String siz = size.getText();
        String hm = kategori.getText();
        String query = String.format("DELETE FROM `item`  WHERE Item_id='%s' AND Nama_item='%s' AND Harga='%d' AND Size='%s' AND Kategori_id='%s' ",
                eh,name,promo,siz,hm);

        if(!name.equals(""))
            try{
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Data berhasil di Hapus");
                alert.show();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Delete ERROR");
                alert.show();
            }
    }
    @FXML
    Button kateg;
    @FXML
    protected void TombolKategori(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchCat.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) kateg.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
