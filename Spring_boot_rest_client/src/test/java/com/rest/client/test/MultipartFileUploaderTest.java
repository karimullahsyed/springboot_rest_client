package com.rest.client.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MultipartFileUploaderTest {
 
    public static void main(String[] args) {
        String charset = "UTF-8";
        File uploadFile1 = new File("C:\\Users\\karim\\Desktop\\user_headers.PNG");
        String requestURL = "http://localhost:9171/emiTxnProcess/saveEmiTxnProcessDetails";
        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);
            
            multipart.addFilePart("file", uploadFile1);
            
            multipart.addFormField("emiTxnProcessRequest", "");
 
            List<String> response = multipart.finish();
            System.out.println("SERVER REPLIED:");
             
            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}