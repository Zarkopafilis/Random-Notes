import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.nossr50.api.ExperienceAPI;

public class Promotioner implements Listener {

	@EventHandler
	public void onPlaceSign(SignChangeEvent e){
		if(e.getLine(0).equalsIgnoreCase("[Promote]")){
			
			if(e.getPlayer().isOp()){
				e.setLine(0, ChatColor.GOLD + e.getLine(0));
			}else{
				e.getBlock().breakNaturally();
			}
			
		}
	}
	
	@EventHandler
	public void promoteInteract(PlayerInteractEvent e){
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			
			if(e.getClickedBlock().getState() instanceof Sign){
				
				Sign s = (Sign) e.getClickedBlock().getState();
				
				if(s.getLine(0) == null || s.getLine(1) == null || s.getLine(2) == null || s.getLine(3) == null){
					return;
				}
				
				if(s.getLine(0).equalsIgnoreCase(ChatColor.GOLD + "[Promote]")){
					
					String rank = s.getLine(1);
					int pow = Integer.parseInt(s.getLine(2).replace(" LVL", ""));
					int bux = Integer.parseInt(s.getLine(3).replace("$", ""));
					
					if(Data.economy.getBalance(e.getPlayer()) >= bux){
						
						if(ExperienceAPI.getPowerLevel(e.getPlayer()) >= pow){
							
							Data.economy.withdrawPlayer(e.getPlayer(), bux);
							
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + e.getPlayer().getName() + " " + rank);
							
							e.getPlayer().sendMessage(ChatColor.RED + "" + bux + " $ have been removed from your account!");
							
							e.getPlayer().sendMessage(ChatColor.GREEN + "You have been promoted to " + rank + "!");
							
						}else{
							e.getPlayer().sendMessage(ChatColor.RED + "Not enough mcMMO levels!");
						}
						
					}else{
						e.getPlayer().sendMessage(ChatColor.RED + "Not enough $!");
					}
					
				}
				
			}
			
		}
		
	}
	
}
