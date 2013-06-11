package io.github.matho97.lockdown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
 
public class LockdownTimer extends BukkitRunnable {

	private final JavaPlugin plugin;
    
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

    public int delay;
    public int autoDelay;
    public boolean ldTimer;
    public boolean ldTimerAuto;
    public boolean time;
	private int count = 0;
 
    public LockdownTimer(JavaPlugin plugin) {
        this.plugin = plugin;
    }
 
    public void run() {
    	delay = plugin.getConfig().getInt("Lockdown.Delay");
    	autoDelay = plugin.getConfig().getInt("Lockdown.Auto.Delay");
    	ldTimer = plugin.getConfig().getBoolean("Lockdown.On");
    	ldTimerAuto = plugin.getConfig().getBoolean("Lockdown.Auto.On");
    	
    	if(ldTimer == true || ldTimerAuto == true){
    		if(ldTimer == true){
	    		if (count == 0){
	    			count = delay;
	    		}
    		} else if (ldTimerAuto == true){
	    		if (count == 0){
	    			count = autoDelay;
	    		}
    		}
    		
        	time = plugin.getConfig().getBoolean("Lockdown.Time");
	    	if (time == true) {
	    		String sender = plugin.getConfig().getString("Lockdown.Sender"); 
	        	
	        	for(Player players:Bukkit.getOnlinePlayers()){
	        		if(players.getName().equalsIgnoreCase(sender)){
	        			if(count >= 60){
	        				players.sendMessage(lockdown + green + "Timeleft: " + gold + count / 60 + " minute(s).");
	        			} else if (count < 60){
	        				players.sendMessage(lockdown + green + "Timeleft: " + gold + count + " second(s).");
	        			}
	    	        	//Bukkit.broadcastMessage(Integer.toString(count));
	        		}
	        	}
	        	
				plugin.getConfig().set("Lockdown.Time", false);
				plugin.saveConfig();
	    		//this.cancel();
				return;
	    	}
			count--;
	    	
	    	/*if(1 == delay){
	    		
	    	}*/
	    	//End game
    	} else {
    		//Bukkit.broadcastMessage("awdawdwd");
    		this.cancel();
    	}
   	}
}