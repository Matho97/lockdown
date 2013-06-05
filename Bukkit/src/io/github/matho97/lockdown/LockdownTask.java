package io.github.matho97.lockdown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
 
public class LockdownTask extends BukkitRunnable {
 
    private final JavaPlugin plugin;
	public String lockdown = ChatColor.RED + "[" + ChatColor.GOLD + "LockDown" + ChatColor.RED + "] " + ChatColor.WHITE;
 
    public LockdownTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }
 
    public void run() {
    	Double px = plugin.getConfig().getDouble("Lockdown.Location 2.X");
    	Double py = plugin.getConfig().getDouble("Lockdown.Location 2.Y");
    	Double pz = plugin.getConfig().getDouble("Lockdown.Location 2.Z");
    	Double ppitch = plugin.getConfig().getDouble("Lockdown.Location 2.Pitch");
    	Double pyaw = plugin.getConfig().getDouble("Lockdown.Location 2.Yaw");

		Float pitch = ppitch.floatValue();
		Float yaw = pyaw.floatValue();
		
		/*double x = Double.parseDouble(sx);
		double y = Double.parseDouble(sy);
		double z = Double.parseDouble(sz);
		float pitch = Float.parseFloat(spitch);
		float yaw = Float.parseFloat(syaw);*/
		
		for(Player players : Bukkit.getOnlinePlayers()){
			Location teleportloc = new Location(players.getWorld(), px, py, pz, yaw, pitch);
			
			if((players.hasPermission("lockdown.immune"))){
				players.teleport(teleportloc);
			}
		}
        plugin.getServer().broadcastMessage(lockdown + ChatColor.RED + "The prison lockdown is now over!");
    }
 
}