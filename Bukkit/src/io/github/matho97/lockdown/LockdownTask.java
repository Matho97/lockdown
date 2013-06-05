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
    	String sx = plugin.getConfig().getString("Lockdown.Location 2.X");
		String sy = plugin.getConfig().getString("Lockdown.Location 2.Y");
		String sz = plugin.getConfig().getString("Lockdown.Location 2.Z");
		String spitch = plugin.getConfig().getString("Lockdown.Location 2.Pitch");
		String syaw = plugin.getConfig().getString("Lockdown.Location 2.Yaw");
		
		double x = Double.parseDouble(sx);
		double y = Double.parseDouble(sy);
		double z = Double.parseDouble(sz);
		float pitch = Float.parseFloat(spitch);
		float yaw = Float.parseFloat(syaw);
		
		for(Player players : Bukkit.getOnlinePlayers()){
			Location teleportloc = new Location(players.getWorld(), x, y, z);
			
			if(!(players.hasPermission("lockdown.lockdown.immune"))){
				players.teleport(teleportloc);
				players.getPlayer().getLocation().setPitch(pitch);
				players.getPlayer().getLocation().setYaw(yaw);
			}
		}
        plugin.getServer().broadcastMessage(lockdown + ChatColor.RED + "The prison lockdown is now over!");
    }
 
}