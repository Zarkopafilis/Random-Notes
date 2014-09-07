import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignStuff implements Listener {

	@EventHandler
	public void onPlace(SignChangeEvent e){
		if(e.getLine(0).equalsIgnoreCase("[GECS]") || e.getLine(0).equalsIgnoreCase(ChatColor.BLUE + "[GECS]")){
			if(e.getPlayer().isOp()){
				e.setLine(0, ChatColor.BLUE + e.getLine(0));
			}else{
				e.getBlock().breakNaturally();
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			
			if(e.getClickedBlock().getState() instanceof Sign){
				Sign s = (Sign) e.getClickedBlock().getState();
				
				if(s.getLine(0).equalsIgnoreCase(ChatColor.BLUE + "[GECS]")){
					
					String alias = s.getLine(1);
					
					String cmd = Data.p.getProperty(alias);
					
					if(cmd == null){
						return;
					}
					
					
					
					if(cmd.contains("$p"))
					cmd = cmd.replaceAll("$p", e.getPlayer().getName());
					
					if(s.getLine(2) != null && s.getLine(2).equals("Console")){
					    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
					}else{
					    Bukkit.getServer().dispatchCommand(e.getPlayer(), cmd);
					}
					
				}
				
			}
			
		}
	}
	
}
