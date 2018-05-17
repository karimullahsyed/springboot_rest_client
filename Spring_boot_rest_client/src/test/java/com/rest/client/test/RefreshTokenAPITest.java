package com.rest.client.test;

import java.util.ArrayList;
import java.util.List;

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

public class RefreshTokenAPITest {
 
   static final String url = "http://192.168.0.36:9553/oauth/token";
   
   public static void main(String[] args) throws Exception {
 
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type","application/x-www-form-urlencoded");
		post.setHeader(HttpHeaders.AUTHORIZATION, "Basic dHJ1c3RlZC1hcHA6cGFzc3dvcmQ=");
		
	    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();  
	    urlParameters.add(new BasicNameValuePair("grant_type", "refresh_token"));  
	    urlParameters.add(new BasicNameValuePair("refresh_token", ""+JsonUtil.jsonFileRead("refresh_token") +""));
	    post.setEntity(new UrlEncodedFormEntity(urlParameters));   
		
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(post);
		
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		if(statusCode == 200) {
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("RefreshTokenAPITest Start :::::::::::::::::::::::::::::::::::::::::::::::::::"+"\n");
			System.out.println(responseBody+"\n");
			System.out.println("RefreshTokenAPITest End :::::::::::::::::::::::::::::::::::::::::::::::::::"+"\n");
		}
   }
}