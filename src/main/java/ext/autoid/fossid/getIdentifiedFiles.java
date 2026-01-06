package ext.autoid.fossid;

import static ext.autoid.values.AllValues.allValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ext.autoid.values.AllValues;


public class getIdentifiedFiles {

	public static void getidfiles(PrintWriter writer) throws IOException {

		setLicenseId setlicenseid = new setLicenseId();
		setComponentId setcomponentid = new setComponentId();
		
		JSONObject dataObject = new JSONObject();
		dataObject.put("username", allValues.fossidvalues.getUsername());
	    dataObject.put("key", allValues.fossidvalues.getApikey());
        dataObject.put("scan_code", allValues.fossidvalues.getScanCode());
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "get_identified_files");
		rootObject.put("data", dataObject);
		
		
		HttpPost httpPost = new HttpPost(allValues.fossidvalues.getServerUri());
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				
		
		int total_int = 0;
		long total_long = 0;
		
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

			JSONParser jsonParser = new JSONParser();  
	        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
	        String temp = jsonObj1.get("data").toString();
	        JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
	        
	        Iterator iter1 = null;
	        iter1 = jsonObj2.keySet().iterator();
	        
	        Iterator iter2 = null;
	        iter2 = jsonObj2.keySet().iterator();
	        
	        String[] extensions = allValues.fossidvalues.getExtensions().split(",");
	        String filepath = "";
	        String extension = "";
	        
	        int count= 0;
	        
	        System.out.println();
	        writer.write("\n");
	        
	        System.out.println("### Following files are matched with the dedicated extensions ###");
	        writer.write("### Following files are matched with the dedicated extensions ###" + "\n");

	        
	        while(iter1.hasNext()) {	 
        		
            	// set key value to key
            	String key = (String) iter1.next();         
            	// get values from key
            	JSONObject tempObj = (JSONObject) jsonObj2.get(key);
            		
            		filepath = tempObj.get("file_path").toString();
                    int index = filepath.lastIndexOf(".");
             
                    if (index > 0) {
                    	
                    	// 파일 확장자만 분류 
                        extension = filepath.substring(index + 1);

                        for(String str : extensions) {
                        	
                        	// 사용자가 정의한 확장자와 동일한 경우
                        	if(str.equals(extension)) {
                        		//System.out.println(filepath);
                        		
                        		if(allValues.fossidvalues.getmarkedIdlist().contains(filepath)) {
                        		} else {
                        			count++;	
                        			System.out.println(count + ". " + filepath);
                        			 writer.write(count + ". " + filepath + "\n");
                        		}
                        	}
                        }
                    }
        	}
	        
	        
	        System.out.println();
	        writer.write("\n");
	        
	        if(AllValues.allValues.fossidvalues.getSelect().equals("license")) {
		        System.out.println("### Following files are assigned to [ " + allValues.fossidvalues.getIdentifier() + " ] ###");
		        writer.write("### Following files are assigned to [ " + allValues.fossidvalues.getIdentifier() + " ] ###" + "\n");
     	       } else if(AllValues.allValues.fossidvalues.getSelect().equals("component")) {
   		        System.out.println("### Following files are assigned to [ " + allValues.fossidvalues.getComponent() 
   		        	+ " / " + allValues.fossidvalues.getVersion() + "] ###");
   		        writer.write("### Following files are assigned to [ " + allValues.fossidvalues.getComponent()
   		        	+ " / " + allValues.fossidvalues.getVersion() + "] ###"  + "\n");
     	    }
	        
	        count = 0;
	        
        	while(iter2.hasNext()) {	 
        		
            	// set key value to key
            	String key = (String) iter2.next();         
            	// get values from key
            	JSONObject tempObj = (JSONObject) jsonObj2.get(key);
            	            	
            	// 조회된 파일에서 매치 후보군 리스트가 0 = 없을 경우 
            	if(Integer.parseInt(tempObj.get("kb_matches").toString()) == 0 || tempObj.get("ignore_reason").toString().equals("invlid response")
            			|| tempObj.get("match_type").toString().equals("error") || tempObj.get("match_type").toString().equals("none") 
            			|| tempObj.get("match_type").toString().equals("ignored") || tempObj.get("match_type").toString().equals("failed"))  {
            		
            		//System.out.println(tempObj.get("kb_matches"));
            		
            		filepath = tempObj.get("file_path").toString();
                    int index = filepath.lastIndexOf(".");
             
                    if (index > 0) {
                    	
                    	// 파일 확장자만 분류 
                        extension = filepath.substring(index + 1);

                        for(String str : extensions) {
                        	
                        	// 사용자가 정의한 확장자와 동일한 경우
                        	if(str.equals(extension)) {
                        		//System.out.println(filepath);
                        		
                        		if(allValues.fossidvalues.getmarkedIdlist().contains(filepath)) {
                        		} else {
                        			if(AllValues.allValues.fossidvalues.getSelect().equals("license")) {
                                   		count++;
                                   		setlicenseid.setlicenseId(filepath, count, writer);
                             	       } else if(AllValues.allValues.fossidvalues.getSelect().equals("component")) {
                                   		count++;
                                   		setcomponentid.setcomponentId(filepath, count, writer);
                             	       }                        			
                        		}
                        	}
                        }
                    }
            	}
        	}
        	
        	if(count == 0) {
            	System.out.println();            	
            	System.out.println("===== There are no files to be assigned =====");
            	System.out.println("DEBUG: " + result.toString());
            	System.out.println();
            	
            	writer.write("\n");
            	writer.write("===== There are no files to be assigned =====" + "\n");
            	//writer.write("DEBUG: " + result.toString() + "\n");
            	writer.write("\n");            	
        	} else {
            	System.out.println();
            	System.out.println();
            	System.out.println("### Total file count expacted: " + count);
            	System.out.println("### Total file count assigned: " + allValues.commonvalues.getCount());
            	System.out.println();
            	
            	writer.write("\n");
            	writer.write("\n");
            	writer.write("### Total file count expacted: " + count + "\n");
            	writer.write("### Total file count assigned: " + allValues.commonvalues.getCount() + "\n");
            	writer.write("\n");
        	}
        	
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		httpClient.close();
	
	}
}
