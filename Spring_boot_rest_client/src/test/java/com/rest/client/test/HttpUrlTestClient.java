package com.rest.client.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUrlTestClient {

	 public static void main(String[] args) {
	        try {
	            URL url = new URL ("http://localhost:9171/emiTxnProcess/saveEmiTxnProcessDetails");
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("POST");
	            connection.setDoOutput(true);
	            connection.setRequestProperty("Authorization", "Bearer eb539111-ad30-42ab-85d9-fad36e8ef384");
	            connection.setRequestProperty("Content-Type", "application/json"); //$NON-NLS-1$ //$NON-NLS-2$
	            
	            InputStream content = (InputStream)connection.getInputStream();
	            BufferedReader in   =  new BufferedReader (new InputStreamReader (content));
	            String line;
	            while ((line = in.readLine()) != null) {
	                System.out.println(line);
	            }
	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	    }
}
