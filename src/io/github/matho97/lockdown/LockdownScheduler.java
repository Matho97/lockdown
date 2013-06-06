package io.github.matho97.lockdown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
 
public class LockdownScheduler extends BukkitRunnable {
 
	private final JavaPlugin plugin;
	public String lockdown = ChatColor.RED + "[" + ChatColor.GOLD + "LockDown" + ChatColor.RED + "] " + ChatColor.WHITE;
	@SuppressWarnings("unused")
	private LockdownCommandExecutor ldst;
 
    public LockdownScheduler(JavaPlugin plugin) {
        this.plugin = plugin;
    }
	private int count = 0;
	private int delay = 0;
	private boolean auto = true;
 
    public void run() {
    	delay = plugin.getConfig().getInt("Lockdown.Auto delay.Timer");
    	auto = plugin.getConfig().getBoolean("Lockdown.Auto delay.On");

    	if (auto == true){
        	Bukkit.broadcastMessage(Integer.toString(count));
	    	if(count == 0){
				Double sx = plugin.getConfig().getDouble("Lockdown.Location 1.X");
		    	Double sy = plugin.getConfig().getDouble("Lockdown.Location 1.Y");
		    	Double sz = plugin.getConfig().getDouble("Lockdown.Location 1.Z");
		    	
		    	Double spitch = plugin.getConfig().getDouble("Lockdown.Location 1.Pitch");
		    	Double syaw = plugin.getConfig().getDouble("Lockdown.Location 1.Yaw");
		
			    Double spawnX = plugin.getConfig().getDouble("Lockdown.Spawn 1.X");
			    Double spawnY = plugin.getConfig().getDouble("Lockdown.Spawn 1.Y");
			    Double spawnZ = plugin.getConfig().getDouble("Lockdown.Spawn 1.Z");
		
				Float pitch = spitch.floatValue();
				Float yaw = syaw.floatValue();
				
				/*double x = Double.parseDouble(sx);
				double y = Double.parseDouble(sy);
				double z = Double.parseDouble(sz);
				float pitch = Float.parseFloat(spitch);
				float yaw = Float.parseFloat(syaw);*/
				
				for(Player players : Bukkit.getOnlinePlayers()){
					Location teleportloc = new Location(players.getWorld(), sx, sy, sz, yaw, pitch);
					
					if((players.hasPermission("lockdown.immune"))){
						players.setBedSpawnLocation(new Location(players.getWorld(), spawnX, spawnY, spawnZ), true);
						players.teleport(teleportloc);
					}
				}
				plugin.getServer().broadcastMessage(lockdown + ChatColor.YELLOW + "The prison is now under lockdown, you will not be able to leave this area!");
	    	} else if (count == delay) {
	    		Double px = plugin.getConfig().getDouble("Lockdown.Location 2.X");
	    	   	Double py = plugin.getConfig().getDouble("Lockdown.Location 2.Y");
	    	   	Double pz = plugin.getConfig().getDouble("Lockdown.Location 2.Z");
	    	   	
	    	   	Double ppitch = plugin.getConfig().getDouble("Lockdown.Location 2.Pitch");
	    	   	Double pyaw = plugin.getConfig().getDouble("Lockdown.Location 2.Yaw");
	
	    	   	Double spawnX2 = plugin.getConfig().getDouble("Lockdown.Spawn 2.X");
	   		    Double spawnY2 = plugin.getConfig().getDouble("Lockdown.Spawn 2.Y");
	   		    Double spawnZ2 = plugin.getConfig().getDouble("Lockdown.Spawn 2.Z");
	   	
	   			Float pitch2 = ppitch.floatValue();
	   			Float yaw2 = pyaw.floatValue();
	    			
	    		/*double x = Double.parseDouble(sx);
	    		double y = Double.parseDouble(sy);
	    		double z = Double.parseDouble(sz);
	    		float pitch = Float.parseFloat(spitch);
	    		float yaw = Float.parseFloat(syaw);*/
	    			
	    		for(Player players : Bukkit.getOnlinePlayers()){
	    			Location teleportloc = new Location(players.getWorld(), px, py, pz, yaw2, pitch2);
	    			
	    			if((players.hasPermission("lockdown.immune"))){
	    				players.setBedSpawnLocation(new Location(players.getWorld(), spawnX2, spawnY2, spawnZ2), true);
	    				players.teleport(teleportloc);
	    			}
	    		}
	    		plugin.getServer().broadcastMessage(lockdown + ChatColor.GREEN + "The prison lockdown is now over!");
	    		plugin.getServer().broadcast(lockdown, lockdown);
	    			
			    //this.cancel();
	    		count = 1 - delay;
			    return;
		    }
	    //End game
	    count++;
	    		 
    	} else {
    		plugin.getServer().broadcastMessage("Not on!");
    		this.cancel();
    	}
   	}
}