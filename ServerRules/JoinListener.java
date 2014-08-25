import org.bukkit.Bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener{

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e){
		Bukkit.getScheduler().scheduleSyncDelayedTask(ASD.p, new Runnable(){
			
			public void run(){
				Bukkit.getServer().dispatchCommand(e.getPlayer(), "rules");
			}
			
		}, 60L);
	}
	
}
