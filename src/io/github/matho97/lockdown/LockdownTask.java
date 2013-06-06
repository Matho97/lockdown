package io.github.matho97.lockdown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
 
public class LockdownTask extends BukkitRunnable {
 
    private final JavaPlugin plugin;
	private LockdownCommandExecutor ldce;
	
	// Easier chat coloring during string broadcasts and such. Seeing as we do it so much in here ;)
	public ChatColor
	aqua = ChatColor.AQUA,
	black = ChatColor.BLACK,
	blue = ChatColor.BLUE,
	bold = ChatColor.BOLD,
	darkaqua = ChatColor.DARK_AQUA,
	darkblue = ChatColor.DARK_BLUE,
	darkgray = ChatColor.DARK_GRAY,
	darkgreen = ChatColor.DARK_GREEN,
	darkpurple = ChatColor.DARK_PURPLE,
	darkred = ChatColor.DARK_RED,
	gold = ChatColor.GOLD,
	gray = ChatColor.GRAY,
	green = ChatColor.GREEN,
	italic = ChatColor.ITALIC,
	magic = ChatColor.MAGIC,
	purple = ChatColor.LIGHT_PURPLE,
	red = ChatColor.RED,
	reset = ChatColor.RESET,
	strike = ChatColor.STRIKETHROUGH,
	underline = ChatColor.UNDERLINE,
	white = ChatColor.WHITE,
	yellow = ChatColor.YELLOW
	;
    
    public String lockdown = red + "[" + gold + "LockDown" + red + "] " + white;
	
	public boolean ldtask;
 
    public LockdownTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }
 
    public void run() {
    	ldtask = true;
    	if (ldtask == true){
	    	Double px = plugin.getConfig().getDouble("Lockdown.Location 2.X");
	    	Double py = plugin.getConfig().getDouble("Lockdown.Location 2.Y");
	    	Double pz = plugin.getConfig().getDouble("Lockdown.Location 2.Z");
	    	
	    	Double ppitch = plugin.getConfig().getDouble("Lockdown.Location 2.Pitch");
	    	Double pyaw = plugin.getConfig().getDouble("Lockdown.Location 2.Yaw");

		    Double spawnX = plugin.getConfig().getDouble("Lockdown.Spawn 2.X");
		    Double spawnY = plugin.getConfig().getDouble("Lockdown.Spawn 2.Y");
		    Double spawnZ = plugin.getConfig().getDouble("Lockdown.Spawn 2.Z");
	
			Float pitch = ppitch.floatValue();
			Float yaw = pyaw.floatValue();
			
			for(Player players : Bukkit.getOnlinePlayers()){
				Location teleportloc = new Location(players.getWorld(), px, py, pz, yaw, pitch);
				
				if(!(players.hasPermission("lockdown.immune"))){
					players.setBedSpawnLocation(new Location(players.getWorld(), spawnX, spawnY, spawnZ), true);
					players.teleport(teleportloc);
				}
			}
	        plugin.getServer().broadcastMessage(lockdown + green + "The prison lockdown is now over!");
	        //ldce.ldtask = false;
    	} if (ldce.ldtask == false){
    		plugin.getServer().broadcastMessage(lockdown + green + "Canceled!");
    	}
    }
}