package ext.autoid.values;

import java.util.ArrayList;

public class fossidValues {

    private static final fossidValues FOSSID_LOGIN_VALUES = new fossidValues();

    public fossidValues() {

    }

    public static fossidValues getInstance() {
        return FOSSID_LOGIN_VALUES;
    }

    private static String serverUri;
    private static String username;
    private static String apikey;
    private static String scancode;
    private static String extensions;
    private static String identifier;
    private static String component;
    private static String version;
    private static String select;

	private ArrayList<String> markedIdlist = new ArrayList<String>();

	public ArrayList<String> getmarkedIdlist() {
		return markedIdlist;
	}
	public void setmarkedIdlist(String markedIdlist) {
		this.markedIdlist.add(markedIdlist);
	}
	

    public String getServerUri() {
        return serverUri;
    }

    public void setServerUri(String serverUri) {
    	fossidValues.serverUri = serverUri;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
    	fossidValues.username = username;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
    	fossidValues.apikey = apikey;
    }
    
    public String getScanCode() {
        return scancode;
    }

    public void setScanCode(String scancode) {
    	fossidValues.scancode = scancode;
    }
    
    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
    	fossidValues.extensions = extensions;
    }

    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
    	fossidValues.identifier = identifier;
    }
    
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
    	fossidValues.component = component;
    }
    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
    	fossidValues.version = version;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
    	fossidValues.select = select;
    }

}
