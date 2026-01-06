package ext.autoid.fossid;

import static ext.autoid.values.AllValues.allValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class getMarkedasIdentifiedFiles {
	
	public static void getmarkedasidentified() throws IOException {
		
		JSONObject dataObject = new JSONObject();
		dataObject.put("username", allValues.fossidvalues.getUsername());
	    dataObject.put("key", allValues.fossidvalues.getApikey());
        dataObject.put("scan_code", allValues.fossidvalues.getScanCode());
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "get_marked_as_identified_files");
		rootObject.put("data", dataObject);
		
		
		HttpPost httpPost = new HttpPost(allValues.fossidvalues.getServerUri());
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		try {

			StringEntity entity = new StringEntity(rootObject.toString());
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);			
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);			
			
			
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException(
						"Failed : HTTP error code : " + httpClientResponse.getStatusLine().getStatusCode());
			}	
								
			
            //System.out.println(httpClientResponse.getStatusLine().getStatusCode());


			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();	
			
			System.out.println();
			JSONParser jsonParser = new JSONParser();  
	        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
	        String keyToCheck = "message";	        

	        if (jsonObj1.containsKey(keyToCheck)) {
	        	System.out.println();
	        	System.out.println("There are no files that have been marked as identified yet.");
	        	System.out.println();
	        } else {
	 
		        String temp = jsonObj1.get("data").toString();
		        JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
		        
		        Iterator iter = null;
		        iter = jsonObj2.keySet().iterator();
		        
	        	while(iter.hasNext()) {
	        		
	        		// set key value to key
	            	String key = (String) iter.next();         
	            	// get values from key
	            	JSONObject tempObj = (JSONObject) jsonObj2.get(key);
	            	
	            	allValues.fossidvalues.setmarkedIdlist(tempObj.get("file_path").toString());
	        		
	        	}

	        }
	        	
	        
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		httpClient.close();
		
	}
	

}
