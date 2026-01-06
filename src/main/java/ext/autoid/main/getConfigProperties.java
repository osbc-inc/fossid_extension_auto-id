package ext.autoid.main;

import static ext.autoid.values.AllValues.allValues;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class getConfigProperties {

	
	public void getValues() throws IOException {
		
		String propsPath = "";
		
		if(osValidator.isWindows()) {
			propsPath = System.getProperty("user.dir") + "\\config.properties";
		} else if(osValidator.isUnix() || osValidator.isSolaris() || osValidator.isMac() ){
			propsPath = System.getProperty("user.dir") + "/config.properties";
		}
		
		FileReader resources = null;
		
		try {
			resources = new FileReader(propsPath);
			Properties props = new Properties();
			props.load(resources);

			if(allValues.fossidvalues.getServerUri() == null || allValues.fossidvalues.getServerUri().equals("")) {
				allValues.fossidvalues.setServerUri(props.getProperty("fossid.domain") + "/api.php");
			}
			
			if(allValues.fossidvalues.getUsername() == null || allValues.fossidvalues.getUsername().equals("")) {
				allValues.fossidvalues.setUsername(props.getProperty("fossid.username"));
			}
			
			if(allValues.fossidvalues.getApikey() == null || allValues.fossidvalues.getApikey().equals("")) {
					allValues.fossidvalues.setApikey(props.getProperty("fossid.apikey"));				
			}
			
			if(allValues.fossidvalues.getScanCode() == null || allValues.fossidvalues.getScanCode().equals("")) {				
				allValues.fossidvalues.setScanCode(props.getProperty("fossid.scancode"));
			}
			
			if(allValues.fossidvalues.getExtensions() == null || allValues.fossidvalues.getExtensions().equals("")) {				
				allValues.fossidvalues.setExtensions(props.getProperty("fossid.extensions"));
			}
			
			if(allValues.fossidvalues.getIdentifier() == null || allValues.fossidvalues.getIdentifier().equals("")) {				
				allValues.fossidvalues.setIdentifier(props.getProperty("fossid.identifier"));
			}
			
			if(allValues.fossidvalues.getComponent() == null || allValues.fossidvalues.getComponent().equals("")) {				
				allValues.fossidvalues.setComponent(props.getProperty("fossid.component"));
			}
			
			if(allValues.fossidvalues.getVersion() == null || allValues.fossidvalues.getVersion().equals("")) {
				allValues.fossidvalues.setVersion(props.getProperty("fossid.version"));
			}
			
			if(allValues.fossidvalues.getSelect() == null || allValues.fossidvalues.getSelect().equals("")) {
				allValues.fossidvalues.setSelect(props.getProperty("fossid.select"));
			}
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
