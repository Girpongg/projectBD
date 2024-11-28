package com.example.projectbd;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloApplication extends Application {
    private Stage primaryStage;
    private Scene scene1;
    private Scene1 sceneController1;
    private Scene scene2;
    private Scene2 sceneController2;
    private Scene scene3;
    private Scene3 sceneController3;

    private Scene scene4;
    private Scene4 sceneController4;

    private Scene scene5;
    private Scene5 sceneController5;

    private Scene scene6;
    private Scene6 sceneController6;

    private Scene scene7;
    private Scene7 sceneController7;

    private Scene sceneAn;
    private SceneAnalisis sceneAnController;

    private Scene sceneCat;
    private SceneSearchCat sceneControllerCat;
    private Scene scene8;
    private Scene8 sceneController8;
    private Scene scene11;
    private Scene11 sceneController11;

    private Scene sceneRep;
    private Report sceneRepController;
    private Scene sceneJob;

    private Scene scene10;
    private Scene10 sceneController10;

    private Scene scene9;
    private Scene9 sceneController9;

    private Scene scenecust;
    private SceneSearchCust controlercust;

    private Scene sceneitem;
    private SceneSearchItem controleritem;

    private Scene sceneSearchMet;
    private SceneSearchMet sceneSearchMetController;

    private Scene scenepeg;
    private SceneSearchPeg controlerpeg;

    private Scene scenemet2;
    private SceneSearchMet2 control2;


    private SceneSearchJob sceneControllerJob;

    public Scene7 getSceneController7() {
        return sceneController7;
    }

    public void setSceneController7(Scene7 sceneController7) {
        this.sceneController7 = sceneController7;
    }

    public HelloApplication(){
        applicationInstance = this;
    }
    private static HelloApplication applicationInstance;
    public static HelloApplication getApplicationInstance(){
        return applicationInstance;
    }
    public Scene getScene3() {
        return scene3;
    }
    public void setScene3(Scene scene3) {
        this.scene3 = scene3;
    }
    public Scene3 getSceneController3() {
        return sceneController3;
    }
    public void setSceneController3(Scene3 sceneController3) {
        this.sceneController3 = sceneController3;
    }
    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;

        Image appIcon = new Image("file:5532465"); // Replace "icon.png" with your icon file's name
        primaryStage.getIcons().add(appIcon);
        primaryStage.setScene(new Scene(new StackPane(), 400, 300)); // Replace with your UI scene

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene1 =  new Scene(fxmlLoader.load(), 930, 625);
        sceneController1 = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene2.fxml"));
        scene2 = new Scene(fxmlLoader.load(), 1310, 600);
        sceneController2 =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene3.fxml"));
        scene3 = new Scene(fxmlLoader.load(), 560, 400);
        sceneController3 =  fxmlLoader.getController();fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene4.fxml"));
        scene4 = new Scene(fxmlLoader.load(), 520, 390);
        sceneController4 =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene5.fxml"));
        scene5 = new Scene(fxmlLoader.load(), 540, 450);
        sceneController5 =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene6.fxml"));
        scene6 = new Scene(fxmlLoader.load(), 550, 275);
        sceneController6 =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene7.fxml"));
        scene7 = new Scene(fxmlLoader.load(), 420, 400);
        sceneController7 =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchCat.fxml"));
        sceneCat = new Scene(fxmlLoader.load(), 460, 310);
        sceneControllerCat =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene8.fxml"));
        scene8 = new Scene(fxmlLoader.load(), 670, 400);
        sceneController8 =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene11.fxml"));
        scene11 = new Scene(fxmlLoader.load(), 310, 400);
        sceneController11 =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchJob.fxml"));
        sceneJob = new Scene(fxmlLoader.load(), 345, 230);
        sceneControllerJob =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene10.fxml"));
        scene10 = new Scene(fxmlLoader.load(), 470, 400);
        sceneController10 =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene9.fxml"));
        scene9 = new Scene(fxmlLoader.load(), 600, 400);
        sceneController9 =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchCust.fxml"));
        scenecust = new Scene(fxmlLoader.load(), 600, 290);
        controlercust =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchItem.fxml"));
        sceneitem = new Scene(fxmlLoader.load(), 600, 290);
        controleritem =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchMet.fxml"));
        sceneSearchMet = new Scene(fxmlLoader.load(), 345, 400);
        sceneSearchMetController =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchPeg.fxml"));
        scenepeg = new Scene(fxmlLoader.load(), 600, 330);
        controlerpeg =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneSearchMet2.fxml"));
        scenemet2 = new Scene(fxmlLoader.load(), 340, 310);
        control2 =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneAnalisis.fxml"));
        sceneAn = new Scene(fxmlLoader.load(), 630, 400);
        sceneAnController =  fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Report.fxml"));
        sceneRep = new Scene(fxmlLoader.load(), 590, 400);
        sceneRepController =  fxmlLoader.getController();

        stage.setTitle("Aplikasi Pendataan Was Wis Wus Laundry");
        stage.setScene(scene1);
        stage.show();
    }

    public Scene getScenepeg() {
        return scenepeg;
    }

    public Scene getScenemet2() {
        return scenemet2;
    }

    public void setScenemet2(Scene scenemet2) {
        this.scenemet2 = scenemet2;
    }

    public SceneSearchMet2 getControl2() {
        return control2;
    }

    public void setControl2(SceneSearchMet2 control2) {
        this.control2 = control2;
    }

    public void setScenepeg(Scene scenepeg) {
        this.scenepeg = scenepeg;
    }

    public SceneSearchPeg getControlerpeg() {
        return controlerpeg;
    }

    public void setControlerpeg(SceneSearchPeg controlerpeg) {
        this.controlerpeg = controlerpeg;
    }

    public Scene getSceneSearchMet() {
        return sceneSearchMet;
    }

    public void setSceneSearchMet(Scene sceneSearchMet) {
        this.sceneSearchMet = sceneSearchMet;
    }

    public SceneSearchMet getSceneSearchMetController() {
        return sceneSearchMetController;
    }

    public void setSceneSearchMetController(SceneSearchMet sceneSearchMetController) {
        this.sceneSearchMetController = sceneSearchMetController;
    }

    public Scene getSceneitem() {
        return sceneitem;
    }

    public void setSceneitem(Scene sceneitem) {
        this.sceneitem = sceneitem;
    }

    public SceneSearchItem getControleritem() {
        return controleritem;
    }

    public void setControleritem(SceneSearchItem controleritem) {
        this.controleritem = controleritem;
    }

    public Scene getScenecust() {
        return scenecust;
    }

    public void setScenecust(Scene scenecust) {
        this.scenecust = scenecust;
    }

    public SceneSearchCust getControlercust() {
        return controlercust;
    }

    public void setControlercust(SceneSearchCust controlercust) {
        this.controlercust = controlercust;
    }

    public Scene getScene9() {
        return scene9;
    }

    public void setScene9(Scene scene9) {
        this.scene9 = scene9;
    }

    public Scene9 getSceneController9() {
        return sceneController9;
    }

    public void setSceneController9(Scene9 sceneController9) {
        this.sceneController9 = sceneController9;
    }

    public Scene getScene10() {
        return scene10;
    }

    public void setScene10(Scene scene10) {
        this.scene10 = scene10;
    }

    public Scene10 getSceneController10() {
        return sceneController10;
    }

    public void setSceneController10(Scene10 sceneController10) {
        this.sceneController10 = sceneController10;
    }

    public Scene getSceneAn() {
        return sceneAn;
    }

    public void setSceneAn(Scene sceneAn) {
        this.sceneAn = sceneAn;
    }

    public SceneAnalisis getSceneAnController() {
        return sceneAnController;
    }

    public void setSceneAnController(SceneAnalisis sceneAnController) {
        this.sceneAnController = sceneAnController;
    }

    public Scene getSceneJob() {
        return sceneJob;
    }

    public void setSceneJob(Scene sceneJob) {
        this.sceneJob = sceneJob;
    }

    public SceneSearchJob getSceneControllerJob() {
        return sceneControllerJob;
    }

    public void setSceneControllerJob(SceneSearchJob sceneControllerJob) {
        this.sceneControllerJob = sceneControllerJob;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public Scene getScene1() {return scene1;
    }

    public Scene getScene8() {
        return scene8;
    }

    public void setScene8(Scene scene8) {
        this.scene8 = scene8;
    }

    public Scene8 getSceneController8() {
        return sceneController8;
    }

    public void setSceneController8(Scene8 sceneController8) {
        this.sceneController8 = sceneController8;
    }

    public Scene1 getSceneController1() {
        return sceneController1;
    }

    public Scene getScene2() {
        return scene2;
    }

    public Scene2 getSceneController2() {
        return sceneController2;
    }

    public Scene getScene4() {
        return scene4;
    }

    public void setScene4(Scene scene4) {
        this.scene4 = scene4;
    }

    public Scene4 getSceneController4() {
        return sceneController4;
    }

    public Scene getScene5() {
        return scene5;
    }

    public void setScene5(Scene scene5) {
        this.scene5 = scene5;
    }

    public Scene5 getSceneController5() {
        return sceneController5;
    }

    public void setSceneController5(Scene5 sceneController5) {
        this.sceneController5 = sceneController5;
    }

    public void setSceneController4(Scene4 sceneController4) {
        this.sceneController4 = sceneController4;
    }

    public static void main(String[] args) {
        launch();
    }

    public Scene getScene6() {
        return scene6;
    }

    public void setScene6(Scene scene6) {
        this.scene6 = scene6;
    }

    public Scene6 getSceneController6() {
        return sceneController6;
    }

    public void setSceneController6(Scene6 sceneController6) {
        this.sceneController6 = sceneController6;
    }

    public Scene getScene7() {
        return scene7;
    }

    public void setScene7(Scene scene7) {
        this.scene7 = scene7;
    }

    public Scene getSceneCat() {
        return sceneCat;
    }

    public void setSceneCat(Scene sceneCat) {
        this.sceneCat = sceneCat;
    }

    public SceneSearchCat getSceneControllerCat() {
        return sceneControllerCat;
    }

    public void setSceneControllerCat(SceneSearchCat sceneControllerCat) {
        this.sceneControllerCat = sceneControllerCat;
    }

    public Scene getScene11() {
        return scene11;
    }

    public void setScene11(Scene scene11) {
        this.scene11 = scene11;
    }

    public Scene11 getSceneController11() {
        return sceneController11;
    }

    public void setSceneController11(Scene11 sceneController11) {
        this.sceneController11 = sceneController11;
    }

    public Scene getSceneRep() {
        return sceneRep;
    }

    public void setSceneRep(Scene sceneRep) {
        this.sceneRep = sceneRep;
    }

    public Report getSceneRepController() {
        return sceneRepController;
    }

    public void setSceneRepController(Report sceneRepController) {
        this.sceneRepController = sceneRepController;
    }
}