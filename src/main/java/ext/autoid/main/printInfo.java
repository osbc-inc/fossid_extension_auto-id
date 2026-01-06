package ext.autoid.main;

import java.io.PrintWriter;

import ext.autoid.values.AllValues;

public class printInfo {

	  public static void start() {
	       System.out.println();
	       System.out.println(" ******     ******     ******        ******");
	       System.out.println("*      *   *           *     *      *      ");
	       System.out.println("*      *   *           *      *    *       ");
	       System.out.println("*      *   *           *     *    *        ");
	       System.out.println("*      *    ******     ******     *        ");
	       System.out.println("*      *          *    *     *    *        ");
	       System.out.println("*      *          *    *      *    *       ");
	       System.out.println("*      *          *    *     *      *      ");
	       System.out.println(" ******     ******     ******        ******");
	       System.out.println();
	    }

	
	  	public static void loginInfo(PrintWriter writer) {
	       System.out.println();
	       System.out.println();
	       System.out.println("===== FossID Login Info =====");
	       System.out.println("FossID Server URL: " + AllValues.allValues.fossidvalues.getServerUri());
	       System.out.println("FossID Server UserName: " + AllValues.allValues.fossidvalues.getUsername());
	       System.out.println("FossID Server APIKey: *****");
	       System.out.println("FossID Server ScanCode: " + AllValues.allValues.fossidvalues.getScanCode());
	       System.out.println();
	       System.out.println();
	       System.out.println("===== Configuration =====");
	       if(AllValues.allValues.fossidvalues.getSelect().equals("license")) {
		       System.out.println("License Identifier: " + AllValues.allValues.fossidvalues.getIdentifier());
	       } else if(AllValues.allValues.fossidvalues.getSelect().equals("component")) {
		       System.out.println("Component Name: " + AllValues.allValues.fossidvalues.getComponent());
		       System.out.println("Component Version: " + AllValues.allValues.fossidvalues.getVersion());
	       }
	       System.out.println("Extensions: " + AllValues.allValues.fossidvalues.getExtensions());
	       System.out.println();
	       System.out.println();
	       
	       writer.write("\n");
	       writer.write("\n");
	       writer.write("===== FossID Login Info ====="  + "\n");
	       writer.write("FossID Server URL: " + AllValues.allValues.fossidvalues.getServerUri() + "\n");
	       writer.write("FossID Server UserName: " + AllValues.allValues.fossidvalues.getUsername() + "\n");
	       writer.write("FossID Server APIKey: *****"  + "\n");
	       writer.write("FossID Server ScanCode: " + AllValues.allValues.fossidvalues.getScanCode()  + "\n");
	       writer.write("\n");
	       writer.write("\n");
	       writer.write("===== Configuration ====="  + "\n");
	       if(AllValues.allValues.fossidvalues.getSelect().equals("license")) {		       
		       writer.write("License Identifier: " + AllValues.allValues.fossidvalues.getIdentifier() + "\n");
	       } else if(AllValues.allValues.fossidvalues.getSelect().equals("component")) {		       
		       writer.write("Component Name: " + AllValues.allValues.fossidvalues.getComponent()  + "\n");		       
		       writer.write("Component Version: " + AllValues.allValues.fossidvalues.getVersion()  + "\n");
	       }
	       writer.write("Extensions: " + AllValues.allValues.fossidvalues.getExtensions()  + "\n");
	       writer.write("\n");
	       writer.write("\n");	       
	  	} 	
	 
}
