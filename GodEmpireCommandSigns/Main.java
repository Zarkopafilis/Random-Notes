import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable(){
		
		Data.loadAliases();
		
		Bukkit.getServer().getPluginManager().registerEvents(new SignStuff(), this);
		
	}
	
}
