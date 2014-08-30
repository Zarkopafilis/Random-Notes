package com.rfslabs.kas.rpg.skills;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class SkillCastClass implements CommandExecutor , Listener{
	
	@SuppressWarnings("unused")
	private Plugin p;
	
	private int snowballAmount;
	private int snowballDamage;
	
	public SkillCastClass(Plugin p){
		this.p = p;
		//ik ik
		snowballAmount = p.getConfig().getInt("snowball-amount");
		snowballDamage = p.getConfig().getInt("snowball-damage");
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		if(sender instanceof Player){
			
			Player p = (Player) sender;
			
			if(lbl.equals("castskill")){
				if(args.length == 1){
					String skillType = args[0];
					
					if(skillType.equals("throw-snowball")){
						
						for(int i = 0 ; i < snowballAmount ; i++){
							
							Snowball s = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
							s.setShooter(p);
							s.setVelocity(p.getLocation().getDirection().multiply(1.5));  
							
						}
						
					}
					
				}
			}
			
		}
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSnowballHit(EntityDamageByEntityEvent e){
		e.setDamage(0);
		e.setCancelled(true);
		if(snowballDamage <= 0){
			return;
		}
		
		if(e.getDamager().getType() == EntityType.SNOWBALL){
			
			Snowball s = (Snowball) e.getDamager();
			
			if(s.getShooter() instanceof Player){
				
				if(e.getEntity() instanceof LivingEntity){
					
					LivingEntity victim = (LivingEntity) e.getEntity();
					
					victim.damage(snowballDamage , s.getShooter());
					
				}
			}
			
		}
		
	}
	

}
