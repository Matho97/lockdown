package io.github.matho97.lockdown;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class LockdownCommandExecutor implements CommandExecutor{

	private Lockdown plugin;	
	
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
	public String notenough = lockdown + yellow + "Not enough arguments!";
	public String toomany = lockdown + yellow + "Too many arguments!";

	public boolean ldTask = false;
	public boolean ldTimer = false;
	public boolean ldTimerAuto = false;
	public boolean ldscheduler = false;
	
	public int delay;
	public int count = 0;
	
	public LockdownCommandExecutor(Lockdown plugin) {
		this.plugin = plugin;
	}
	
	@Override	
	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args){
		/**
		 * Stores the command methods
		 */
		if (cmd.getName().equalsIgnoreCase("lockdown")){
				if (args.length == 0){
					//Player player = (Player) sender;
					sender.sendMessage(gold + "----------------- " + lockdown + white + " Help Page " + gold + "----------------");
					sender.sendMessage("/lockdown" + yellow + " - Shows this help page.");
					sender.sendMessage("/lockdown set <1|2>" + yellow + " - Sets the 2 warp points, 1 is the prison, 2 is when it's over.");
					sender.sendMessage("/lockdown reload" + yellow + " - Reloads the configuration files.");
					sender.sendMessage("/lockdown on <amount of time> <s|m>" + yellow + " - Sets the prison into lockdown mode, s = seconds, m = minutes.");
					sender.sendMessage("/lockdown off" + yellow + " - Cancels the lockdown.");
					sender.sendMessage("/lockdown auto <time|off> <s|m>" + yellow + " - Enables and disabled automatick lockdown, s = seconds, m = minutes.");
					sender.sendMessage("/lockdown info" + yellow + " - Outputs the version number.");
					sender.sendMessage("/lockdown time" + yellow + " - Outputs how much time there is left of a lockdown.");
					//sender.sendMessage("");
					/*if(player.getInventory().contains(new ItemStack(Material.STICK))){
						sender.sendMessage(darkred + "hello");
					}*/
					return true;
				}
				/**
				 * Sets the two locations needed for the teleportation
				 */
				if (args[0].equalsIgnoreCase("set")){
					if(sender.hasPermission("lockdown.set")){
					
						Player player = sender.getServer().getPlayer(sender.getName());
						if(args.length == 1){
							sender.sendMessage(notenough);
							sender.sendMessage(lockdown + "Usage: /lockdown set <1|2>");
							return true;
						} else if (args.length == 3){
							sender.sendMessage(toomany);
							sender.sendMessage(lockdown + "Usage: /lockdown set <1|2>");
							return true;
						}
						/**
						 * Sets location one
						 */
						if (args[1].equalsIgnoreCase("1")){
							
							Double x = player.getLocation().getX();
							Double y = player.getLocation().getY();
							Double z = player.getLocation().getZ();
							
							Float pitch = player.getLocation().getPitch();
							Float yaw = player.getLocation().getYaw();
							
							//Location spawn = player.getBedSpawnLocation();
							
							plugin.getConfig().set(plugin.location1 + ".X", x);
							plugin.getConfig().set(plugin.location1 + ".Y", y);
							plugin.getConfig().set(plugin.location1 + ".Z", z);
							
							plugin.getConfig().set(plugin.location1 + ".Pitch", pitch);
							plugin.getConfig().set(plugin.location1 + ".Yaw", yaw);
							
							/*plugin.getConfig().set(plugin.spawn1 + ".X", x);
							plugin.getConfig().set(plugin.spawn1 + ".Y", y);
							plugin.getConfig().set(plugin.spawn1 + ".Z", z);*/
							
							plugin.saveConfig();

							String posX = x.toString();
							String posY = y.toString();
							String posZ = z.toString();
							
							sender.sendMessage(lockdown + purple + "Location 1 has been set at " + green + posX.substring(0, 3) + ", " + posY.substring(0, 3) + ", " + posZ.substring(0, 3));
							
							return true;
						} else 
						/**
						 * Sets location two
						 */
						if (args[1].equalsIgnoreCase("2")){
							//World world = player.getWorld();
							Double x = (player.getLocation().getX());
							Double y = (player.getLocation().getY());
							Double z = (player.getLocation().getZ());
							
							Float pitch = (player.getLocation().getPitch());
							Float yaw = (player.getLocation().getYaw());
							
							/*Location spawn = world.getSpawnLocation();
							Double spawnX = spawn.getX();
							Double spawnY = spawn.getY();
							Double spawnZ = spawn.getZ();*/
							
							plugin.getConfig().set(plugin.location2 + ".X", x);
							plugin.getConfig().set(plugin.location2 + ".Y", y);
							plugin.getConfig().set(plugin.location2 + ".Z", z);
							
							plugin.getConfig().set(plugin.location2 + ".Pitch", pitch);
							plugin.getConfig().set(plugin.location2 + ".Yaw", yaw);
							
							/*plugin.getConfig().set(plugin.spawn2 + ".X", spawnX);
							plugin.getConfig().set(plugin.spawn2 + ".Y", spawnY);
							plugin.getConfig().set(plugin.spawn2 + ".Z", spawnZ);*/
							
							plugin.saveConfig();
	
							String posX = x.toString();
							String posY = y.toString();
							String posZ = z.toString();
								
							sender.sendMessage(lockdown + purple + "Location 2 has been set at " + green + posX.substring(0, 3) + ", " + posY.substring(0, 3) + ", " + posZ.substring(0, 3));
							return true;
						}
					}
				} else
				/**
				 * Reloads the configuration file
				 */
				if (args[0].equalsIgnoreCase("reload")){
					if(sender.hasPermission("lockdown.reload")){
						if (args.length == 2){
							sender.sendMessage(toomany);
							sender.sendMessage(lockdown + "Usage: /lockdown reload");
							return true;
						}
						plugin.reloadConfig();
						sender.sendMessage(lockdown + green + "Configuration has been reloaded successfully!");
						return true;
					}
				} else
				/**
				 * Turns on lockdown	
				 */
				if (args[0].equalsIgnoreCase("on")){
					if(sender.hasPermission("lockdown.execute")){
						if (args.length <= 2){
							sender.sendMessage(notenough);
							sender.sendMessage(lockdown + "Usage: /lockdown on <amount of time> <s|m>");
							return true;
						} else if (args.length >= 4){
							sender.sendMessage(toomany);
							sender.sendMessage(lockdown + "Usage: /lockdown on <amount of time> <s|m>");
							return true;
						} else if (args[2].equalsIgnoreCase("s")|| args[2].equalsIgnoreCase("m")){
							/**
							 * Sets the teleport coordinates and teleports all online players
							 */
						    Double sx = plugin.getConfig().getDouble(plugin.location1 + ".X");
						    Double sy = plugin.getConfig().getDouble(plugin.location1 + ".Y");
						    Double sz = plugin.getConfig().getDouble(plugin.location1 + ".Z");
						    
						    Double spitch = plugin.getConfig().getDouble(plugin.location1 + ".Pitch");
						    Double syaw = plugin.getConfig().getDouble(plugin.location1 + ".Yaw");
						    
						    /*Double spawnX = plugin.getConfig().getDouble(plugin.spawn1 + ".X");
						    Double spawnY = plugin.getConfig().getDouble(plugin.spawn1 + ".Y");
						    Double spawnZ = plugin.getConfig().getDouble(plugin.spawn1 + ".Z");*/
						    
						    Float pitch = spitch.floatValue();
							Float yaw = syaw.floatValue();
							
							Double px = plugin.getConfig().getDouble(plugin.location2 + ".X");
							Double py = plugin.getConfig().getDouble(plugin.location2 + ".Y");
							Double pz = plugin.getConfig().getDouble(plugin.location2 + ".Z");
							
							if(sx == 0.0||sy == 0.0||sz == 0.0 ||px == 0.0||py == 0.0||pz == 0.0){
								sender.sendMessage(red + "--------------------- " + yellow + lockdown + red + "---------------------");
								sender.sendMessage(lockdown + "You have not set all of the teleportation points!");
								sender.sendMessage(lockdown + "Do /lockdown set 1 and /lockdown set 2, to set the 2 teleportation points.");
								sender.sendMessage(lockdown + "===== Also, Remember: =====");
								sender.sendMessage(lockdown + "Point 1 is where users tp to during lockdown!");
								sender.sendMessage(lockdown + "Point 2 is where users tp to " + red + "AFTER " + white +  "lockdown!");
								return true;
							} else {
							
								for(Player players : Bukkit.getOnlinePlayers()){
									Location teleportloc = new Location(players.getWorld(), sx, sy, sz, yaw, pitch);
									
									if(!(players.hasPermission("lockdown.immune"))){
										//players.setBedSpawnLocation(new Location(players.getWorld(), spawnX, spawnY, spawnZ), true);
										players.teleport(teleportloc);
									}
									players.sendMessage(lockdown + yellow + "The prison is now under lockdown, you will not be able to leave this area!");
								}
							
								/***
								 * Sets the delay and message before executing the second teleportation 
								 */
								if (args[2] == null){
									sender.sendMessage(lockdown + "You need to choose if you want the delay in seconds or minutes! s or m.");
									return true;
								} else if (args[2].equalsIgnoreCase("m")){
									delay = Integer.parseInt(args[1]);
									for(Player players : Bukkit.getOnlinePlayers()){
										players.sendMessage(lockdown + gray + "Server has been put in lockdown for " + delay + " minute(s).");
									}
									plugin.getConfig().set("Lockdown.On", true);
									plugin.getConfig().set("Lockdown.Delay", delay * 60);
									plugin.saveConfig();
									@SuppressWarnings("unused")
									BukkitTask task = new LockdownTask(plugin).runTaskLater(plugin, delay * 1200);
									
									@SuppressWarnings("unused")
									BukkitTask timeTask = new LockdownTimer(plugin).runTaskTimer(plugin, 0L, 20L);
									return true;
								} else if (args[2].equalsIgnoreCase("s")){
									delay = Integer.parseInt(args[1]);
									for(Player players : Bukkit.getOnlinePlayers()){
										players.sendMessage(lockdown + gray + "Server has been put in lockdown for " + delay + " second(s).");
									}
									plugin.getConfig().set("Lockdown.On", true);
									plugin.getConfig().set("Lockdown.Delay", delay);
									plugin.saveConfig();
									@SuppressWarnings("unused")
									BukkitTask task = new LockdownTask(plugin).runTaskLater(plugin, delay * 20);
									
									@SuppressWarnings("unused")
									BukkitTask timeTask = new LockdownTimer(plugin).runTaskTimer(plugin, 0L, 20L);
									//ldt.ldtask = false;
									return true;
								}
							}
					} else {						
						sender.sendMessage(lockdown + "The argument " + "'" + args[2] + "'" + " is not accepted!");
						sender.sendMessage(lockdown + "Use 's' for seconds and 'm' for minutes");
						return true;
						}
					}
				} else 
				/**
				 * Turn the lockdown automatic 
				 */
				if(args[0].equalsIgnoreCase("auto")){
					//BukkitTask autoTask = new LockdownScheduler(plugin);
					
					Double sx = plugin.getConfig().getDouble(plugin.location1 + ".X");
				    Double sy = plugin.getConfig().getDouble(plugin.location1 + ".Y");
				    Double sz = plugin.getConfig().getDouble(plugin.location1 + ".Z");
					
					Double px = plugin.getConfig().getDouble(plugin.location2 + ".X");
					Double py = plugin.getConfig().getDouble(plugin.location2 + ".Y");
					Double pz = plugin.getConfig().getDouble(plugin.location2 + ".Z");
					
					if (args.length == 1){
						sender.sendMessage(notenough);
						sender.sendMessage("Usage: /lockdown auto <time|off> <s|m>");
						return true;
					} else if (args.length >= 4){
						sender.sendMessage(toomany);
						sender.sendMessage("Usage: /lockdown auto <time|off> <s|m>");
						return true;
					}
					if (args[1].equalsIgnoreCase("off")){
						/*int d = 0;
						
						for(int i = 0; i < 10; i++){
							d++;
							sender.sendMessage(Integer.toString(d));
						}
						int left = 10 - d;
						sender.sendMessage(Integer.toString(left));*/
						
						plugin.getConfig().set("Lockdown.Auto.On", false);
						plugin.saveConfig();
						
						//lds.count = 0;

						for (Player players : Bukkit.getServer().getOnlinePlayers()){
							players.sendMessage(lockdown + "Automatic lockdown has been canceled by " + red + sender.getName());
							return true;
						}
						return true;
					} else if (args[2].equalsIgnoreCase("s")|| args[2].equalsIgnoreCase("m")){
						
						if(sx == 0.0||sy == 0.0||sz == 0.0 ||px == 0.0||py == null||pz == 0.0){
							sender.sendMessage(red + "--------------------- " + yellow + lockdown + red + "---------------------");
							sender.sendMessage(lockdown + "You have not set all of the teleportation points!");
							sender.sendMessage(lockdown + "Do /lockdown set 1 and /lockdown set 2, to set the 2 teleportation points.");
							sender.sendMessage(lockdown + "===== Also, Remember: =====");
							sender.sendMessage(lockdown + "Point 1 is where users tp to during lockdown!");
							sender.sendMessage(lockdown + "Point 2 is where users tp to " + red + "AFTER " + white +  "lockdown!");
							return true;
						} else {
							/***
							 * Sets the delay and message before executing the second teleportation 
							 */
							int autoDelay = Integer.parseInt(args[1]);
							
							if (args[2] == null){
								sender.sendMessage(lockdown + "You need to choose if you want the delay in seconds or minutes! s or m.");
								return true;
							} else if (args[2].equalsIgnoreCase("m")){
								
								plugin.getConfig().set("Lockdown.Auto.On", true);
								plugin.getConfig().set("Lockdown.Auto.Delay", autoDelay * 60);
								plugin.saveConfig();
								
								@SuppressWarnings("unused")
								BukkitTask autoTask = new LockdownScheduler(plugin).runTaskTimer(plugin, 0L, 20L);
								
								@SuppressWarnings("unused")
								BukkitTask timeTask = new LockdownTimer(plugin).runTaskTimer(plugin, 0L, 20L);
								
								/*for(Player players : Bukkit.getOnlinePlayers()){
									players.sendMessage(lockdown + gray + "Server has been put in lockdown for " + autoDelay + " minute(s).");
								}*/
								return true;
							} else if (args[2].equalsIgnoreCase("s")){
								
								plugin.getConfig().set("Lockdown.Auto.On", true);
								plugin.getConfig().set("Lockdown.Auto.Delay", autoDelay);
								plugin.saveConfig();
								
								@SuppressWarnings("unused")
								BukkitTask autoTask = new LockdownScheduler(plugin).runTaskTimer(plugin, 0L, 20L);
								
								@SuppressWarnings("unused")
								BukkitTask timeTask = new LockdownTimer(plugin).runTaskTimer(plugin, 0L, 20L);
								
								/*for(Player players : Bukkit.getOnlinePlayers()){
									players.sendMessage(lockdown + gray + "Server has been put in lockdown for " + autoDelay + " second(s).");
								}*/
								//ldt.ldtask = false;
								return true;
							}
							
							/*plugin.getConfig().set("Lockdown.Auto.Timer", autoDelay);
							plugin.getConfig().set("Lockdown.Auto.On", true);
							plugin.saveConfig();
							
							@SuppressWarnings("unused")
							BukkitTask autoTask = new LockdownScheduler(plugin).runTaskTimer(plugin, 0L, 20L);
							return true;*/
						}
					} else {					
						sender.sendMessage(lockdown + "The argument " + "'" + args[2] + "'" + " is not accepted!");
						sender.sendMessage(lockdown + "Use 's' for seconds and 'm' for minutes");
						return true;
					}
				} else
				/**
				 * Turns off lockdown
				 */
				if(args[0].equalsIgnoreCase("off")){
					if(sender.hasPermission("lockdown.execute")){
						if(args.length == 1){
					    	ldTask = plugin.getConfig().getBoolean("Lockdown.On");
					    	
							if (ldTask == true){
								plugin.getConfig().set("Lockdown.On", false);
								plugin.saveConfig();
								
								for (Player players : Bukkit.getServer().getOnlinePlayers()){
									players.sendMessage(lockdown + "Lockdown has been canceled by " + red + sender.getName());
									return true;
								}
							} else
							sender.sendMessage(lockdown + "The prison is not in lockdown!");
							return true;
						} else
						sender.sendMessage(toomany);
						sender.sendMessage("Usage: /lockdown off");
						return true;
					}
					return false;
				} else 
					
				/**
				 * Timeleft command.	
				 */
				if (args[0].equalsIgnoreCase("time")){
			    	ldTimer = plugin.getConfig().getBoolean("Lockdown.On");
			    	ldTimerAuto = plugin.getConfig().getBoolean("Lockdown.Auto.On");
			    	
			    	if (args.length == 1){
						if (ldTimer == true||ldTimerAuto == true){
							plugin.getConfig().set("Lockdown.Time", true);
							plugin.getConfig().set("Lockdown.Sender", sender.getName());
							plugin.saveConfig();
						} else {
							sender.sendMessage(lockdown + "The prison is not in lockdown!");
						}
						return true;
					} else {
						sender.sendMessage(toomany);
						sender.sendMessage(lockdown + "Usage: /lockdown time");
						return true;
					}
						
				} else
					
				/**
				 * Output plugin information.
				 */
				if(args[0].equalsIgnoreCase("info")){
					if(args.length == 1){
						String version = plugin.getDescription().getVersion();
						List<String> authors = plugin.getDescription().getAuthors();
						String site = plugin.getDescription().getWebsite();
						
						sender.sendMessage(red + "----------- " + yellow + lockdown + red + "-----------");
						sender.sendMessage(lockdown + yellow + "Version: " + red + version);
						sender.sendMessage(lockdown + yellow + "Authors: " + red + authors.toString().replace("[", "").replace("]", ""));
						sender.sendMessage(lockdown + yellow + "Website: " + red + site);
					} else {
						sender.sendMessage(toomany);
						sender.sendMessage("Usage: /lockdown version");
					}
					return true;
				}
				
			return false;
		} //end of lockdown command
		 //If this has happened the function will return true. 
	        // If this hasn't happened the a value of false will be returned.
		return false; 
	}

}
