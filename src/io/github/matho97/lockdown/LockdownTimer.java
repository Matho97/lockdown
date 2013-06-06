package io.github.matho97.lockdown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
 
public class LockdownTimer extends BukkitRunnable {
 
    @SuppressWarnings("unused")
	private final JavaPlugin plugin;
	private Colors c;
	
	public String lockdown = c.red + "[" + c.gold + "LockDown" + c.red + "] " + ChatColor.WHITE;
	private LockdownCommandExecutor ldce;
 
    public LockdownTimer(JavaPlugin plugin) {
        this.plugin = plugin;
    }
	private int count = 0;
 
    public void run() {
    	if (ldce.ldtimer == true){
    		if (count == ldce.delay) {
    			
    			
		    	this.cancel();
		    	return;
	    	}
	    	//End game
    		Bukkit.broadcastMessage(Integer.toString(count));
	    	count++;
	    		 
    	} 
   	}
}