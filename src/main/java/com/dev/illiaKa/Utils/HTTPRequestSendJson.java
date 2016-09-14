package com.dev.illiaKa.Utils;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sonicmaster on 08.09.16.
 */
public class HTTPRequestSendJson {

    public static int sendPost(String JSONStringToSend) throws Exception {

        // TODO : real url needed here
        URL url = new URL("http://vendingmachinestorage-142810.appspot.com/demo");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        String urlParameters = JSONStringToSend;

        // write json to data stream
        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeBytes(urlParameters);

        dos.flush();
        dos.close();


        // get response
        return connection.getResponseCode();


    }

}
