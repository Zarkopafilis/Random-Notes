
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable(){
		try {
			ASD.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ASD.p = this;
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getCommand("rules").setExecutor(new RulesCommand());
		
	}
	
	
	
}
