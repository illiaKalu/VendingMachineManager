package com.dev.illiaKa;

import com.dev.illiaKa.Utils.HTTPRequestSendJson;
import com.dev.illiaKa.Utils.JsonCreator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rx.observables.JavaFxObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sonicmaster on 07.09.16.
 * class represents Maneger behavior
 * stores denominations and products and send it to GAE servers.
 */
public class ManagerScene extends Application{

    private static final String PRIMARY_STAGE_TITLE = "Vending Machine Manager Behavior";
    private static final String DIALOG_TITLE = "Save configuration for a vending machine";
    private static final String INVALID_INPUT = "Your input is NOT correct, please follow the patterns";
    private static final String DIALOG_ARE_YOU_SURE = "Save?";
    private static final String NO_PRODUCTS_ADDED = "You didn't add any products";
    private static final String SUCCESSFULY_SENDED = "Configuration was successfully saved";
    private static final String ERROR_MESSAGE = "System failure, try again";

    private Button addProductButton;
    private Button saveButton;

    // product info textfields
    private TextField productTypeTextFiled;
    private TextField productPriceTextFiled;
    private TextField productAmountTextFiled;
    private Label messagesLabel;

    // List of products, which was added by manager and need to be saved
    private List<Product> products;

    // List of denominations TextFields.
    private List<TextField> denominations;

    // array represents denominations.
    // indexes are shown below
    // 5$ - 0 index
    // 2$ - 1 index
    // ...
    // 0.1$ - 5 index
    private String[] denominationCountArray = new String[6];

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        products = new ArrayList<>(6);
        denominations = new ArrayList<>(6);

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

        /**
         * set styles to scene
         * @see /resources/style.css
         */
        scene.getStylesheets().add("style.css");

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
                   messagesLabel.setText(INVALID_INPUT);
                }

            }
        });

        Observable<ActionEvent> bttnEvents =
                JavaFxObservable.fromActionEvents(saveButton);

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                boolean isDenominationsValid = true;

                if (products.isEmpty()) {
                    messagesLabel.setText(NO_PRODUCTS_ADDED);
                } else {

                    // reading denominations section
                    for (int i = 0; i < denominations.size(); i++) {

                        if (!denominations.get(i).getText().isEmpty() && denominations.get(i).getText().matches("-?\\d+")) {
                            denominationCountArray[i] = denominations.get(i).getText();

                        } else {
                            isDenominationsValid = false;
                            messagesLabel.setText(INVALID_INPUT);
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

            saveButton.setDisable(true);
            addProductButton.setDisable(true);

            // make JSON
            String jsonToSend = JsonCreator.createJSON( (ArrayList) products, denominationCountArray);

            // send JSON
            int responseCode;
            try{
                responseCode = HTTPRequestSendJson.sendPost(jsonToSend);

                if (responseCode != 200){
                    throw new Exception();
                }else{
                    messagesLabel.setText(SUCCESSFULY_SENDED);
                }

            }catch (Exception e){
                e.printStackTrace();
                messagesLabel.setText(ERROR_MESSAGE);
            }
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

        denominations.add((TextField) scene.lookup("#denom_5"));
        denominations.add((TextField) scene.lookup("#denom_2"));
        denominations.add((TextField) scene.lookup("#denom_1"));
        denominations.add((TextField) scene.lookup("#denom_05"));
        denominations.add((TextField) scene.lookup("#denom_02"));
        denominations.add((TextField) scene.lookup("#denom_01"));

        messagesLabel = (Label) scene.lookup("#message_label");
    }

    private boolean isProductValid() {
        return ( productTypeTextFiled.getText().length() > 0 ) && (productPriceTextFiled.getText().matches("-?\\d+(\\.\\d)?") )
                && ( productAmountTextFiled.getText().matches("-?\\d+") );
    }
}
