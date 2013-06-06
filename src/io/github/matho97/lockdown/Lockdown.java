package io.github.matho97.lockdown;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Lockdown extends JavaPlugin{

	public String location1 = "Lockdown.Location 1";
	public String location2 = "Lockdown.Location 2";
	public String spawn1 = "Lockdown.Spawn 1";
	public String spawn2 = "Lockdown.Spawn 2";
	@Override
	public void onEnable(){
		loadConfiguration();
		getLogger().info(ChatColor.DARK_RED + "Lockdown has been enabled");
		getCommand("lockdown").setExecutor(new LockdownCommandExecutor(this));
		//new PluginListener(this);
	}
	
	public void loadConfiguration(){
	     //See "Creating you're defaults"
		getConfig().addDefault(location1, "");
		getConfig().addDefault(location2, "");
		getConfig().addDefault(spawn1, "");
		getConfig().addDefault(spawn2, "");
		getConfig().addDefault("Lockdown.Auto delay", "");
		getConfig().addDefault("Lockdown.Auto delay.On", true);
	    getConfig().options().copyDefaults(true); // NOTE: You do not have to use "plugin." if the class extends the java plugin
	    //Save the config whenever you manipulate it
	    saveConfig();
	}
	
	public void reloadConfiguration(){
		reloadConfig();
	}
	
	/*public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("testplugin")){ // If the player typed /basic then do the following...
			
			sender.sendMessage("Hello World");
			return true;
		}
		 //If this has happened the function will return true. 
	        // If this hasn't happened the a value of false will be returned.
		return false; 
	}*/
	
	@Override
	public void onDisable(){
		getLogger().info("Lockdown has been disabled");
	}
}
