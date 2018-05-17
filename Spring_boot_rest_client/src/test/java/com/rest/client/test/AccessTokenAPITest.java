package com.rest.client.test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;

import com.rest.client.util.JsonUtil;

public class AccessTokenAPITest {
 
   static final String url = "http://192.168.0.36:9553/oauth/token";
   static String username = "trusted-app";
   static String password = "password";
   
   public static void main(String[] args) throws Exception {
 
		HttpPost post = new HttpPost(url);
		
		String authString = username + ":" + password;
		System.out.println("auth string: " + authString);
		byte[] encodedAuth = Base64.encodeBase64(authString.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		post.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
		
	    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();  
	    urlParameters.add(new BasicNameValuePair("grant_type", "password"));  
	    urlParameters.add(new BasicNameValuePair("username", "admin"));
	    urlParameters.add(new BasicNameValuePair("password", "Admin@123"));
	    post.setEntity(new UrlEncodedFormEntity(urlParameters));   
		
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(post);
		
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		if(statusCode == 200) {
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("AccessTokenAPITest Start :::::::::::::::::::::::::::::::::::::::::::::::::::"+"\n");
			JsonUtil.jsonFileWrite(responseBody);
			System.out.println(responseBody+"\n");
			System.out.println("AccessTokenAPITest End :::::::::::::::::::::::::::::::::::::::::::::::::::"+"\n");
		}
   }
}