package com.rest.client.test;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.client.model.EmiDetails;
import com.rest.client.model.EmiTxnProcess;
import com.rest.client.util.JsonUtil;
 
public class CaptureAPITest {
 
   static final String url = "http://192.168.0.36:9553/emiTxnProcess/saveEmiTxnProcessDetails";
   
   static File file1 = new File("C:\\Users\\karim\\Desktop\\signature.PNG");
   static File file2= new File("C:\\Users\\karim\\Desktop\\takesnap.PNG");
   static File file3 = new File("C:\\Users\\karim\\Desktop\\attachfile.PNG");
 
   
   public static void main(String[] args) throws Exception {
 
	   HttpPost post = new HttpPost(url);
	   post.setHeader("Authorization", "Bearer" + "" + JsonUtil.jsonFileRead("access_token"));
	   
	   HttpClient client = HttpClientBuilder.create().build();
	   
	   MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	   builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
	   
	   EmiTxnProcess emiTxn = new EmiTxnProcess();
	   EmiDetails emDetails = new EmiDetails();
	   emDetails.setCashBackAmount("0");
	   emDetails.setEmiTenureCode("TR01");
	   emDetails.setEmiTenureDisplayName("3 Months");
	   emDetails.setEmiTenureMonth("3");
	   emDetails.setFinalPayout("11000");
	   emDetails.setIssuerRateOfInterest("10");
	   emDetails.setIssuerSchemeProcessingFee("0");
	   emDetails.setMontlyInstallments("1234");
	   emDetails.setProductPrice("10000");
	   emDetails.setTotalInterestAmount("1111");
	   emiTxn.setEmiDetails(emDetails);
	   emiTxn.setPrimId("Karim1241234321431243389");
	   emiTxn.setUserId(1);
	   emiTxn.setIssuerBankCode(5);
	   emiTxn.setIssuerSchemeCode("SCHM00000001");
	   emiTxn.setTxnAmount("000000100000");
	   Date date = new Date();
	   emiTxn.setTxnDateTime(new Timestamp(date.getTime()));
	   emiTxn.setCardHolderName("Karimullah Syed");
	   emiTxn.setMobileNo("9123456789");
	   emiTxn.setMaskCardNumber("486269XXXXXX1234");
	   emiTxn.setEncryptedCardNumber("xdfdfdsgSdfadsf1fdsfsa");
	   emiTxn.setInvoiceNumber("123456");
	   emiTxn.setRrnNumber("1234567894");
	   emiTxn.setApprovalCode("100111");
	   
	   String json = new ObjectMapper().writeValueAsString(emiTxn);
	  
	   //File1
	   builder.addBinaryBody("files", file1, ContentType.DEFAULT_BINARY, file1.getName());
	   builder.setBoundary("---Content Boundary");
	   
	   //File2
	   builder.addBinaryBody("files", file2, ContentType.DEFAULT_BINARY, file2.getName());
	   builder.setBoundary("---Content Boundary");
	   
	   //File3
	   builder.addBinaryBody("files", file3, ContentType.DEFAULT_BINARY, file3.getName());
	   builder.setBoundary("---Content Boundary");
	   
	   
	   builder.addTextBody("jsonEmiTxnProcessRequest",json, ContentType.DEFAULT_BINARY);
	   builder.setBoundary("---Content Boundary");
	   
	   HttpEntity entity = builder.build();
	   post.setEntity(entity);
	   
	   HttpResponse response = client.execute(post);
	   
	   int statusCode = response.getStatusLine().getStatusCode();		
		System.out.println(statusCode);
		if (statusCode == 200) {
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("CaptureAPITest Start :::::::::::::::::::::::::::::::::::::::::::::::::::" + "\n");
			System.out.println(responseBody+"\n");
			System.out.println("CaptureAPITest End :::::::::::::::::::::::::::::::::::::::::::::::::::" + "\n");
		}
    
   }
 
}