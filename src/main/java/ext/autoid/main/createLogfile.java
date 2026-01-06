package ext.autoid.main;

import static ext.autoid.values.AllValues.allValues;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class createLogfile {
	
	public static void createlogfile() {
		
		File checklogPath = null;
		Path logpath = null;
		
		LocalDateTime  now = LocalDateTime.now();		
        
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();                
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        
        
        File logfile = null;
        
        String logfilepath = "";
			
		if(osValidator.isWindows()) {
			checklogPath = new File("logs");			
			logpath = Paths.get("logs");
			logfilepath =  "logs\\" + allValues.fossidvalues.getScanCode()  + "_" + year+month+day + "_" + hour+minute+second + ".log";
			logfile = new File(logfilepath);
			
		} else if (osValidator.isMac() || osValidator.isSolaris() || osValidator.isUnix()){
			checklogPath = new File("logs");			
			logpath = Paths.get("logs");
			logfilepath =  "logs/" + allValues.fossidvalues.getScanCode()  + "_" + year+month+day + "_" + hour+minute+second + ".log";
			logfile = new File(logfilepath);			
		}
		
		allValues.commonvalues.setlogFile(logfilepath);
		
		try {				
			if(checklogPath.exists()) {				
			} else {				
				Files.createDirectory(logpath);			
			}	
			
			logfile.createNewFile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
