import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import org.bukkit.Bukkit;

public class Data {

	private Data (){}
	
	public static Properties p;
	
	public static void loadAliases() {
		
		p = new Properties();
		
		File f = null;
		File folder = null;
		
		try {
			
			
			folder = new File("plugins" + File.separator + "GodEmpireCommandSigns");
			
			f = new File(folder.toString() + File.separator + "config.properties");
			
			if(!folder.exists()){
				Bukkit.getLogger().log(Level.SEVERE, "No plugin folder found , creating one for you!");
				folder.mkdir();
			}
			
			if(!f.exists()){
				Bukkit.getLogger().log(Level.SEVERE, "No config.properties found , creating one for you!");
				f.createNewFile();
			}
			
			p.load(new FileInputStream(f));
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Something went wrong while loading config.properties!");
		}
		
	}
	
}
