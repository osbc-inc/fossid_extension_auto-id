package ext.autoid.values;

public class commonValues {

    private static final commonValues COMMON_VALUES = new commonValues();

    public commonValues() {

    }

    public static commonValues getInstance() {
        return COMMON_VALUES;
    }


    private static String edomain;
    private static String restrictChars;
    private static String logFile;
    private static int count;
    
    public String geteDomain() {
        return edomain;
    }

    public void seteDomain(String edomain) {
    	commonValues.edomain = edomain;
    }


    public String getresrictChars() {
        return restrictChars;
    }

    public void setresrictChars(String restrictChars) {
    	commonValues.restrictChars = restrictChars;
    }

    public int getCount() {
        return count;
    }

    public void setCount() {
    	count++;
    }

    public String getlogFile() {
        return logFile;
    }

    public void setlogFile(String logfile) {
    	logFile = logfile;
    }

    
}
