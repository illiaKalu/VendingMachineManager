package com.dev.illiaKa;

import com.sun.xml.internal.bind.v2.TODO;
import com.sun.xml.internal.ws.util.StringUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sonicmaster on 07.09.16.
 */
public class ManagerScene extends Application{

    private static final String PRIMARY_STAGE_TITLE = "Vending Machine Manager Behavior";
    private static final String DIALOG_TITLE = "Save configuration for a vending machine";
    // TODO: create some non-stupid dialogs
    private static final String DIALOG_ARE_YOU_SURE = "Save?";

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

    // List of products, which was added by manager and need to be saved
    List<Product> products;

    // List of denominations TextFields.
    List<TextField> denominations;

    // array represents denominations.
    // TODO: make good explanation
    // 5$ - 0 index
    // 2$ - 1 index
    // ...
    // 0.1$ - 5 index
    String[] denominationCountArray = new String[6];

    private boolean productValid;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {

        products = new ArrayList<Product>(6);
        denominations = new ArrayList<TextField>(6);

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

                if (isProductValid()) {
                    products.add(new Product(productTypeTextFiled.getText(),
                            productPriceTextFiled.getText(), productAmountTextFiled.getText()));

                    // make fields empty
                    productTypeTextFiled.setText("");
                    productPriceTextFiled.setText("");
                    productAmountTextFiled.setText("");
                }else {
                    // TODO: make invalid input looks normal
                    productTypeTextFiled.setText("INVALID INPUT");
                }

            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                boolean isDenominationsValid = true;

                if (denominations.isEmpty()) {
                    // TODO: make invalid input looks normal
                    productTypeTextFiled.setText("you didn't add products :(");
                } else {

                    // reading denominations section
                    for (int i = 0; i < denominations.size(); i++) {

                        if (!denominations.get(i).getText().isEmpty() && denominations.get(i).getText().matches("-?\\d+")) {
                            denominationCountArray[i] = denominations.get(i).getText();

                        } else {
                            isDenominationsValid = false;
                            // TODO: make invalid input looks normal
                            productTypeTextFiled.setText("INVALID INPUT");
                        }

                    }

                    if (isDenominationsValid) showAlertDialog();

                }

            }


        });


        primaryStage.show();
    }

    private void showAlertDialog() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(DIALOG_TITLE);
        alert.setHeaderText(DIALOG_ARE_YOU_SURE);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            // make JSON


            // Send request to GAE


            saveButton.setDisable(true);
        } else {
            alert.close();
        }
    }

    private void findControls(Scene scene) {

        productTypeTextFiled = (TextField) scene.lookup("#product_type");
        productPriceTextFiled = (TextField) scene.lookup("#product_price");
        productAmountTextFiled = (TextField) scene.lookup("#product_amount");

        addProductButton  =(Button) scene.lookup("#add_product_button");
        saveButton  =(Button) scene.lookup("#save_button");

        denominations.add(denom5TextFiled = (TextField) scene.lookup("#denom_5"));
        denominations.add(denom2TextFiled = (TextField) scene.lookup("#denom_2"));
        denominations.add(denom1TextFiled = (TextField) scene.lookup("#denom_1"));
        denominations.add(denom05TextFiled = (TextField) scene.lookup("#denom_05"));
        denominations.add(denom02TextFiled = (TextField) scene.lookup("#denom_02"));
        denominations.add(denom01TextFiled = (TextField) scene.lookup("#denom_01"));

        totalAmountLabel = (Label) scene.lookup("#total_amount");
    }


    public boolean isProductValid() {
        return ( productTypeTextFiled.getText().length() > 0 ) && (productPriceTextFiled.getText().matches("-?\\d+(\\.\\d+)?") )
                && ( productAmountTextFiled.getText().matches("-?\\d+") );
    }
}
