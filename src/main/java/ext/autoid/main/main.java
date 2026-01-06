package ext.autoid.main;

import static ext.autoid.values.AllValues.allValues;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ext.autoid.fossid.getIdentifiedFiles;
import ext.autoid.fossid.getMarkedasIdentifiedFiles;

public class main {

	 public static void main(String[] args) throws Exception {
	 
		 try {
			 
			printInfo printinfo = new printInfo();
			printinfo.start();
			
			if (args.length > 1) {
	            for (int i = 0; i < args.length; i++) {
					if(args[i].equals("--url")) {
						allValues.fossidvalues.setServerUri(args[i + 1] + "/api.php");
					}
					
					if(args[i].equals("--username")) {
						allValues.fossidvalues.setUsername(args[i + 1]);
					}
					
					if(args[i].equals("--apikey")) {
                    	allValues.fossidvalues.setApikey(args[i + 1]);						
					}

					if(args[i].equals("--scancode")) {
						allValues.fossidvalues.setScanCode(args[i + 1]);						
					}
					
					
					if(args[i].equals("--identifier")) {
                    	allValues.fossidvalues.setIdentifier(args[i + 1]);
					}
					
					if(args[i].equals("--component")) {
						allValues.fossidvalues.setComponent(args[i + 1]);
					}
	                    	
					if(args[i].equals("--version")) {
						allValues.fossidvalues.setVersion(args[i + 1]);
					}   
					
					if(args[i].equals("--select")) {
						allValues.fossidvalues.setSelect(args[i + 1]);
					}     
	                    	
					if(args[i].equals("--extensions")) {
                    	allValues.fossidvalues.setExtensions(args[i + 1]);
					}  
					
	            }
	        }
			getConfigProperties getVlues = new getConfigProperties();
			getVlues.getValues();
						
			createLogfile create = new createLogfile();
			create.createlogfile();
			
			File file = new File(allValues.commonvalues.getlogFile());
			System.out.println(allValues.commonvalues.getlogFile());
	        FileWriter fw = new FileWriter(file);;
	        PrintWriter writer = new PrintWriter(fw);
			
			printinfo.loginInfo(writer);
			
			getMarkedasIdentifiedFiles getidlist = new getMarkedasIdentifiedFiles();
			getidlist.getmarkedasidentified();
			
			getIdentifiedFiles getid = new getIdentifiedFiles();
			getid.getidfiles(writer);

			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 }

	
}
