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

public class Scene2 {


    @FXML
    private TableView<Pesanan> DataView;

    @FXML
    private Button Select;

    @FXML
    private TextField custID;

    @FXML
    private Button delete;

    @FXML
    private TextField ItemID;

    @FXML
    private TextField kondisi;

    @FXML
    private ComboBox<String> layanan;

    @FXML
    private ComboBox<String> pencucian;

    @FXML
    private TextField pic;

    @FXML
    private ComboBox<String> radius;

    @FXML
    private Button save;

    @FXML
    private ComboBox<String> status;

    @FXML
    private TableColumn<Pesanan, String> tblPIC;

    @FXML
    private TableColumn<Pesanan, String> tblambil;

    @FXML
    private TableColumn<Pesanan, String> tblcond;

    @FXML
    private TableColumn<Pesanan, String> tblcuci;

    @FXML
    private TableColumn<Pesanan, String> tblcust;

    @FXML
    private TableColumn<Pesanan, String> tblharga;

    @FXML
    private TableColumn<Pesanan, String> tblitem;

    @FXML
    private TableColumn<Pesanan, String> tbllayanan;

    @FXML
    private TableColumn<Pesanan, String> tblpes;

    @FXML
    private TableColumn<Pesanan, String> tblpesan;

    @FXML
    private TableColumn<Pesanan, String> tblrad;

    @FXML
    private TableColumn<Pesanan, String> tblstatus;

    @FXML
    private DatePicker tgl_ambil;

    @FXML
    private DatePicker tgl_pesan;

    @FXML
    private Button update;

    Pesanan baru;
    DatabaseConnection connectNow;
    Connection connectDB;
    {
        connectNow = new DatabaseConnection();
        connectDB =connectNow.getConnection();
    }
    protected void tombolset(String teks){
        custID.setText(teks);

    }

    @FXML
    public void initialize() {
        ObservableList<String> dropRadius = FXCollections.observableArrayList("0 - 5 KM", "5 - 10 KM","> 10 KM","-");
        radius.setItems(dropRadius);
        ObservableList<String> dropLayanan = FXCollections.observableArrayList("Pick up","Antar","Antar(2x)");
        layanan.setItems(dropLayanan);
        ObservableList<String> dropPencucian = FXCollections.observableArrayList("Laundry", "Dry clean");
        pencucian.setItems(dropPencucian);
        ObservableList<String> dropStatus = FXCollections.observableArrayList("Selesai", "Belum selesai");
        status.setItems(dropStatus);


        refreshTable();
    }
    @FXML
    Button custom;
    @FXML
    protected void TombolCustomer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchCust.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) custom.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    protected void tombolpegawai(String teks){
        pic.setText(teks);

    }
    @FXML
    Button pegi;
    @FXML
    protected void Tombolpeg() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchPeg.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) pegi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    TextField mtd;
    protected void tombolset1(String teks){
        mtd.setText(teks);
    }

    @FXML
    Button met2;
    @FXML
    protected void Tombolmtd() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchMet2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) met2.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    protected void tombolsetitem(String teks){
        ItemID.setText(teks);
    }

    @FXML
    Button itom;
    @FXML
    protected void Tombolitem() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchItem.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) itom.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void TombolReport(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene sceneRep = app.getSceneRep();
        primaryStage.setScene(sceneRep);
    }

    @FXML
    private TextField harga;
    @FXML
    protected void lihatharga() throws SQLException {
        int simpan = Integer.parseInt(ItemID.getText());
        LocalDate tanggal = tgl_pesan.getValue();
        PreparedStatement getIdStatement = connectDB.prepareStatement("SELECT Potongan_harga FROM promo p join kategori_barang k on p.Promo_id = k.Promo_id JOIN item i on i.Kategori_id = k.Kategori_id WHERE Promo_start <= ? AND Promo_end >= ? AND Item_id = ?;");
        getIdStatement.setDate(1, Date.valueOf(tanggal));
        getIdStatement.setDate(2, Date.valueOf(tanggal));
        getIdStatement.setInt(3,simpan);
        ResultSet idResultSet = getIdStatement.executeQuery();
        int promo = 0;

        if (idResultSet.next()) {
            promo = idResultSet.getInt("Potongan_harga");
        }
        getIdStatement.close();


        PreparedStatement getIdStatement1 = connectDB.prepareStatement("SELECT Harga FROM item WHERE Item_id = ?");
        getIdStatement1.setInt(1,simpan);
        ResultSet idResultSet1 = getIdStatement1.executeQuery();
        int idProduct = 0;

        if (idResultSet1.next()) {
            idProduct = idResultSet1.getInt("Harga");
        }
        getIdStatement.close();

        int total = idProduct - (idProduct*promo/100);



        harga.setText(String.valueOf(total));
    }
    @FXML
    TextField pesananid;

    @FXML
    public void DeleteData(){
        String name = pesananid.getText();
        String query = String.format("DELETE FROM `pesanan` WHERE Pesanan_id ='%s' ",
                name);

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
    }
    @FXML
    public void addData() throws SQLException {
        String name = custID.getText();
        String name2 = ItemID.getText();
        String kond = kondisi.getText();
        String lay = layanan.getValue();
        String penc = pencucian.getValue();
        String rad = radius.getValue();
        LocalDate psn =tgl_pesan.getValue();
        LocalDate abl = tgl_ambil.getValue();
        String stats = status.getValue();
        int hrga = Integer.parseInt(harga.getText());
        String pc = pic.getText();
        String query = String.format("insert into `pesanan` (`Jenis_layanan`,`Radius`,`Kondisi`,`Pencucian`,`Status`,`Customer_id`,`Pegawai_id`,`Item_id`,`Tanggal_pesan`,`Tanggal_Pengembalian`,`Total_harga`)" +
                "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%d')",lay,rad,kond,penc,stats,name,pc,name2,psn,abl,hrga);

        if(!name2.equals("") )
            try{
                if (tgl_ambil.getValue().isBefore(tgl_pesan.getValue())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Salah input tanggal");
                    alert.show();}
                else if(tgl_pesan.getValue().isEqual(tgl_ambil.getValue())){
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

            }
    }

    public void UpdateData(){
        String yei = pesananid.getText();
        String name = custID.getText();
        String name2 = ItemID.getText();
        String kond = kondisi.getText();
        String lay = layanan.getValue();
        String penc = pencucian.getValue();
        String rad = radius.getValue();
        LocalDate psn =tgl_pesan.getValue();
        LocalDate abl = tgl_ambil.getValue();
        String stats = status.getValue();
        int hrga = Integer.parseInt(harga.getText());
        String pc = pic.getText();
        String query = String.format(
                "update `pesanan` SET `Pesanan_id` ='%s',`Jenis_layanan`='%s',`Radius`='%s',`Kondisi`='%s',`Pencucian`='%s', `Status` = '%s' , `Customer_id`='%s',`Pegawai_id`='%s',`Item_id`='%s',`Tanggal_pesan`='%s',`Tanggal_Pengembalian`='%s',`Total_harga`='%d'" +
                "WHERE Pesanan_id='%s' AND Jenis_layanan='%s'AND Radius='%s'AND Kondisi='%s' AND Pencucian='%s' AND Status='%s' AND Customer_id ='%s' AND Pegawai_id='%s' AND Item_id='%s' AND Tanggal_pesan='%s' AND Tanggal_Pengembalian='%s' AND Total_harga='%d'",
                yei,lay,rad,kond,penc,stats,name,pc,name2,psn,abl,hrga,
                baru.getPesanan_id(),baru.getLayanan(),baru.getRadius(),baru.getKondisi(),baru.getPencucian(),baru.getStatus(),baru.getCustomer_id(),baru.getPegawai_id(),baru.getItem_id(),baru.getTgl_pesan(),baru.getTgl_kembali(),baru.getTotal_harga());

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
                e.printStackTrace();
            }
    }

    @FXML
    TextField total;

    public void getRowTable() throws SQLException {
        for (Pesanan pesan : DataView.getSelectionModel().getSelectedItems()) {
            pesananid.setText(String.valueOf(pesan.getPesanan_id()));
            custID.setText(String.valueOf(pesan.getCustomer_id()));
            ItemID.setText(String.valueOf(pesan.getItem_id()));
            kondisi.setText(pesan.getKondisi());
            layanan.setValue(pesan.getLayanan());
            pencucian.setValue(pesan.getPencucian());
            radius.setValue(pesan.getRadius());
            tgl_pesan.setValue(LocalDate.parse(pesan.getTgl_pesan()));
            tgl_ambil.setValue(LocalDate.parse(pesan.getTgl_kembali()));
            status.setValue(pesan.getStatus());
            harga.setText(String.valueOf(pesan.getTotal_harga()));
            pic.setText(String.valueOf(pesan.getPegawai_id()));

            String uid = custID.getText();
            LocalDate tglpesen = tgl_pesan.getValue();
            PreparedStatement getIdStatement1 = connectDB.prepareStatement("Select sum(Total_harga) from pesanan where Customer_id = ? and Tanggal_pesan = ?");
            getIdStatement1.setString(1, (uid));
            getIdStatement1.setString(2, String.valueOf(tglpesen));

            ResultSet idResultSet1 = getIdStatement1.executeQuery();
            int idProduct = 0;

            if (idResultSet1.next()) {
                idProduct = idResultSet1.getInt("sum(Total_harga)");
            }
            getIdStatement1.close();

            String jlayanan = layanan.getValue();
            String rhadius = radius.getValue();

            if (idProduct<150000){
                if (rhadius.equals("0 - 5 KM")&& jlayanan.equals("Antar")){
                idProduct+=10000;
                }
                else if (rhadius.equals("0 - 5 KM")&& jlayanan.equals("Antar(2x)")){
                idProduct+=20000;
                }}

                if (rhadius.equals("5 - 10 KM")&& jlayanan.equals("Antar")){
                idProduct+=20000;
                }
                if (rhadius.equals("5 - 10 KM")&& jlayanan.equals("Antar(2x)")){
                idProduct+=40000;
                 }
                if (rhadius.equals("> 10 KM")&& jlayanan.equals("Antar")){
                idProduct+=30000;
                }
                if (rhadius.equals("> 10 KM")&& jlayanan.equals("Antar(2x)")){
                idProduct+=60000;
                }

            total.setText(String.valueOf(idProduct));
            baru = new Pesanan(pesan.getPesanan_id(), pesan.getCustomer_id(), pesan.getPegawai_id(), pesan.getItem_id(), pesan.getTotal_harga(), pesan.getRadius(), pesan.getLayanan(), pesan.getKondisi(), pesan.getPencucian(), pesan.getStatus(),pesan.getTgl_pesan(),pesan.getTgl_kembali());
        }
}


@FXML
DatePicker tgl_pembayaran;
    @FXML
    public void addData2() throws SQLException {
        int nom = Integer.parseInt(total.getText());
        String tgl = String.valueOf(tgl_pembayaran.getValue());
        int hm = Integer.parseInt(mtd.getText());
        String query = String.format("INSERT INTO `transaksi`(`Nominal`, `Tanggal_pembayaran`, `Metode_id`) VALUES ('%d','%s','%d')",
                nom,tgl,hm);

        if (!tgl.equals(""))
            try {
                Statement statement = connectDB.createStatement();
                statement.execute(query);
                refreshTable();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    public void refreshTable(){
        tblpes.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("Pesanan_id"));
        tblcust.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("Customer_id"));
        tblitem.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("Item_id"));
        tblcond.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("kondisi"));
        tbllayanan.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("layanan"));
        tblcuci.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("pencucian"));
        tblrad.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("radius"));
        tblpesan.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("tgl_pesan"));
        tblambil.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("tgl_kembali"));
        tblstatus.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("status"));
        tblharga.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("Total_harga"));
        tblPIC.setCellValueFactory(new PropertyValueFactory<Pesanan, String>("Pegawai_id"));



        String query = "SELECT * FROM pesanan";

        ObservableList<Pesanan> personList = FXCollections.observableArrayList();

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                int ID = Integer.parseInt(queryOutput.getString("Pesanan_id"));
                String lay = queryOutput.getString("Jenis_layanan");
                String rad= queryOutput.getString("Radius");
                String kon= queryOutput.getString("Kondisi");
                String cuci= queryOutput.getString("Pencucian");
                String status= queryOutput.getString("Status");
                int cust= Integer.parseInt(queryOutput.getString("Customer_id"));
                int peg= Integer.parseInt(queryOutput.getString("Pegawai_id"));
                int Item= Integer.parseInt(queryOutput.getString("Item_id"));
                String tglpesan= queryOutput.getString("Tanggal_pesan");
                String tglpengembalian= queryOutput.getString("Tanggal_Pengembalian");
                int harga = Integer.parseInt(queryOutput.getString("Total_harga"));
                personList.add(new Pesanan(ID,cust,peg,Item,harga,rad,lay,kon,cuci,status,tglpesan,tglpengembalian));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        DataView.setItems(personList);
    }
    @FXML
    protected void TombolButton(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene3 = app.getScene3();
        primaryStage.setScene(scene3);

    }
    @FXML
    protected void TombolItem(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene4 = app.getScene4();
        primaryStage.setScene(scene4);
    }

    @FXML
    protected void TomboAnalisis() throws SQLException {
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene sceneAn = app.getSceneAn();
        primaryStage.setScene(sceneAn);
    }
    @FXML
    protected void TombolPromo(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene5 = app.getScene5();
        primaryStage.setScene(scene5);
    }
    @FXML
    protected void Tombollogout(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene1 = app.getScene1();
        primaryStage.setScene(scene1);
    }
    @FXML
    protected void Tomboljob(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene7 = app.getScene7();
        primaryStage.setScene(scene7);
    }

    @FXML
    protected void Tomboldelapan(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene8 = app.getScene8();
        primaryStage.setScene(scene8);
    }
    @FXML
    protected void Tombolkemethode(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene11 = app.getScene11();
        primaryStage.setScene(scene11);
    }


    @FXML
    protected void Tombolkesepuluh(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene10 = app.getScene10();
        primaryStage.setScene(scene10);
    }

    @FXML
    protected void Tombolkesembilan(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene9 = app.getScene9();
        primaryStage.setScene(scene9);
    }






}
