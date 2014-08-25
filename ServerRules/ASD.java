import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ASD {
	
	public static Plugin p;
	public static String[] rule_lines;

public static void load() throws Exception{
		
		File datafolder = new File("plugins" + File.separator + "GodEmpireRules");
	
		if(!datafolder.exists()){
			Bukkit.getLogger().log(Level.SEVERE, "Plugin data folder not found , creating one for you!");
			datafolder.mkdir();
		}

		File f = new File(datafolder.toPath().toString() + File.separator + "rules.txt");
		
		if(!f.exists()){
			Bukkit.getLogger().log(Level.SEVERE, "rules.txt not found , creating one for you!");
			f.createNewFile();
		}
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		String file_s = "";
		while ((line = br.readLine()) != null) {
		   //Bukkit.getServer().broadcastMessage(line);
		//Bukkit.getLogger().log(Level.SEVERE, line);
		   file_s += line + "\n";
		}
		br.close();
		
		//Bukkit.getLogger().log(Level.SEVERE, file_s);
		
		rule_lines = file_s.split("\n");

		
	}
	
	
}
