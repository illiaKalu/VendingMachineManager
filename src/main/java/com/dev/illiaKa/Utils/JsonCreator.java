package com.dev.illiaKa.Utils;

import com.dev.illiaKa.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by sonicmaster on 07.09.16.
 */
public class JsonCreator {


    public static String createJSON(ArrayList<Product> products, String[] denominations) {

        String PRODUCTS = "products";
        String DENOMINATIONS = "denominations";

        JSONObject productsAndDenominationsJSONObj = new JSONObject();
        JSONObject tempJSONObject;
        JSONArray tempJSONArray = new JSONArray();


        try {

            for (Product product : products) {
                tempJSONObject = new JSONObject();
                tempJSONObject.put("type", product.getType());
                tempJSONObject.put("price", product.getPrice());
                tempJSONObject.put("amount", product.getAmount());

                tempJSONArray.put(tempJSONObject);
            }

            // add products as jsonArray to JSON Object
            productsAndDenominationsJSONObj.put(PRODUCTS, tempJSONArray);


            // put denominations into JSON

            tempJSONArray = new JSONArray(denominations);
            productsAndDenominationsJSONObj.put(DENOMINATIONS, tempJSONArray);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productsAndDenominationsJSONObj.toString();

    }


}
