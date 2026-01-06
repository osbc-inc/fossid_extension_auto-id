package ext.autoid.fossid;

import static ext.autoid.values.AllValues.allValues;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class setLicenseId {
	
	public static void setlicenseId(String filepath, int count, PrintWriter writer) {
		
		changeFilestatus changestatus = new changeFilestatus();
		
        String encodedStr = Base64.getEncoder().encodeToString(filepath.getBytes());
				
		JSONObject dataObject = new JSONObject();
		dataObject.put("username", allValues.fossidvalues.getUsername());
	    dataObject.put("key", allValues.fossidvalues.getApikey());
        dataObject.put("scan_code", allValues.fossidvalues.getScanCode());
        dataObject.put("path",encodedStr);
        dataObject.put("license_identifier", allValues.fossidvalues.getIdentifier());
        dataObject.put("identification_on", "file");
        dataObject.put("is_directory", "0");
        
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "files_and_folders");
        rootObject.put("action", "add_license_identification");
		rootObject.put("data", dataObject);
		
		
		HttpPost httpPost = new HttpPost(allValues.fossidvalues.getServerUri());
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		try {

			StringEntity entity = new StringEntity(rootObject.toString());
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);			
						
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException(
						"Failed : HTTP error code : " + httpClientResponse.getStatusLine().getStatusCode());
			}	
								
		
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();	
								
            //System.out.println(result.toString());
            
            JSONParser jsonParser = new JSONParser();  
	        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
	        String status = jsonObj1.get("status").toString();
	        
	        if(status.equals("1")) {
	        	changestatus.changeSatus(filepath, count, writer);
	        } else {
            	System.out.println();
	        	System.out.println(count + ". ERROR: " + result.toString());
            	System.out.println();
            	
            	writer.write("\n");
				writer.write(count + ". ERROR: " + result.toString() + "\n");
				writer.write("\n");
	        }
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}
				
	}
	
	
}
