package com.dev.illiaKa;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by sonicmaster on 07.09.16.
 */
public class ManagerScene extends Application{

    Button addProductButton;
    Button saveButton;

    // product info textfields
    TextField productTypeTextFiled;
    TextField productPriceTextFiled;
    TextField productAmountTextFiled;

    // denominations
    TextField denom5TextFiled;
    TextField denom2TextFiled;
    TextField denom1TextFiled;
    TextField denom05TextFiled;
    TextField denom02TextFiled;
    TextField denom01TextFiled;

    Label totalAmountLabel;


    private static final String PRIMARY_STAGE_TITLE = "Vending Machine Manager Behavior";

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {

        // primary Scene implementation
        // markup file location
        String fxmlSceneMarkupFile = "/scenes/primaryScene.fxml";


        // loads scene from fxml file in resources
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlSceneMarkupFile));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(PRIMARY_STAGE_TITLE);

        // TODO:FIX_ME; NPE
        //scene.getStylesheets().add(ManagerScene.class.getResource("style.css").toExternalForm());


        // assign controls from layout to actual variables
        findControls(scene);



        addProductButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });


        primaryStage.show();
    }

    private void findControls(Scene scene) {

        productTypeTextFiled = (TextField) scene.lookup("#product_type");
        productPriceTextFiled = (TextField) scene.lookup("#product_price");
        productAmountTextFiled = (TextField) scene.lookup("#product_amount");

        addProductButton  =(Button) scene.lookup("#add_product_button");
        saveButton  =(Button) scene.lookup("#save_button");

        denom5TextFiled = (TextField) scene.lookup("#denom_5");
        denom2TextFiled = (TextField) scene.lookup("#denom_2");
        denom1TextFiled = (TextField) scene.lookup("#denom_1");
        denom05TextFiled = (TextField) scene.lookup("#denom_05");
        denom02TextFiled = (TextField) scene.lookup("#denom_02");
        denom01TextFiled = (TextField) scene.lookup("#denom_01");

        totalAmountLabel = (Label) scene.lookup("#total_amount");
    }
}
