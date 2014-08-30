package com.rfslabs.kas.rpg.skills;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable(){
		getCommand("castskill").setExecutor(new SkillCastClass(this));
		Bukkit.getServer().getPluginManager().registerEvents(new SkillCastClass(this), this);//ik ik
	}
	
}
