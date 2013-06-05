package io.github.matho97.lockdown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
 
public class LockdownScheduler extends BukkitRunnable {
 
    @SuppressWarnings("unused")
	private final JavaPlugin plugin;
	public String lockdown = ChatColor.RED + "[" + ChatColor.GOLD + "LockDown" + ChatColor.RED + "] " + ChatColor.WHITE;
	private LockdownCommandExecutor ldst;
 
    public LockdownScheduler(JavaPlugin plugin) {
        this.plugin = plugin;
    }
	private int count = 0;
 
    public void run() {
    	if (ldst.ldscheduler == true){
    		if (count == ldst.delay) {
    			
    			
		    	this.cancel();
		    	return;
	    	}
	    	//End game
    		Bukkit.broadcastMessage(Integer.toString(count));
	    	count++;
	    		 
    	} 
   	}
}