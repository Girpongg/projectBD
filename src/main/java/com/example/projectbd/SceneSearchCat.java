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
import java.sql.Statement;
import java.util.ResourceBundle;

public class SceneSearchCat implements Initializable {

    DatabaseConnection connectNow;
    @FXML
    private TableColumn<Kategori, String> ColID;

    @FXML
    private TableColumn<Kategori, String> ColKat;

    @FXML
    private TableColumn<Kategori, String> ColPromo;

    @FXML
    private TableView<Kategori> DataTV;


    @FXML
    TextField Select;
    Kategori halo;


    Connection connectDB;
    {
        connectNow = new DatabaseConnection();
        connectDB =connectNow.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();
    }
    public void refreshTable(){
        ColID.setCellValueFactory(new PropertyValueFactory<Kategori, String>("Kategori_id"));
        ColKat.setCellValueFactory(new PropertyValueFactory<Kategori, String>("Nama_Kategori"));
        ColPromo.setCellValueFactory(new PropertyValueFactory<Kategori, String>("Promo_id"));


        String query = "SELECT * FROM kategori_barang";

        ObservableList<Kategori> personList = FXCollections.observableArrayList();

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                String name = queryOutput.getString("Kategori_id");
                String age = queryOutput.getString("Nama_Kategori");
                String mulai = queryOutput.getString("Promo_id");
                personList.add(new Kategori(name,age,mulai));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        DataTV.setItems(personList);
    }

    @FXML
    protected void Kembali(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene4 = app.getScene4();
        primaryStage.setScene(scene4);

        Scene4 scenecontroler4 = app.getSceneController4();
        refreshTable();
        String btext = Select.getText();
        scenecontroler4.tombolset(btext);
    }
    public void getRowTable(){
        for (Kategori person : DataTV.getSelectionModel().getSelectedItems()) {
            Select.setText(person.getKategori_id());
        }
    }}





