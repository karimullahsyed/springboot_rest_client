package com.rest.client.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.rest.client.util.JsonUtil;

public class CategoryAPITest {

	static final String url = "http://192.168.0.36:9553/category/fetchCategoryList/1/5/MAN0000000001";

	public static void main(String[] args) throws Exception {

		HttpGet get = new HttpGet(url);
		get.setHeader("Authorization", "Bearer" + "" + JsonUtil.jsonFileRead("access_token"));
		get.setHeader("Content-Type", "application/json");
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse response = client.execute(get);
		
		int statusCode = response.getStatusLine().getStatusCode();		
		System.out.println(statusCode);
		if (statusCode == 200) {
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("CategoryAPITest API Start :::::::::::::::::::::::::::::::::::::::::::::::::::" + "\n");
			System.out.println(responseBody+"\n");
			System.out.println("CategoryAPITest API End :::::::::::::::::::::::::::::::::::::::::::::::::::" + "\n");
		}
	}
}