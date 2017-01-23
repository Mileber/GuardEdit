package net.pearapple.guardedit;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Config {
	
	//类里面要用到的参数
	private static GuardEdit plugin;
	private static FileConfiguration cfg = null;
	private static Logger geLog = null;
	public static volatile DecimalFormat coord = new DecimalFormat("0.0");
	private static int borderTask = -1;
	private static Runtime rt = Runtime.getRuntime();
	
	//实际可修改的参数
	private static Map<String, BorderData> borders = Collections.synchronizedMap(new LinkedHashMap<String, BorderData>());
	private static Set<UUID> bypassPlayers = Collections.synchronizedSet(new LinkedHashSet<UUID>());
	private static String message;
	private static String messageFmt;
	private static String messageClean;
	private static double knockBack = 3.0;
	private static int timerTicks = 4;
	/*
	private static boolean whooshEffect = true;
	private static boolean portalRedirection = true;
	private static boolean dynmapEnable = true;
	private static String dynmapMessage;
	private static int remountDelayTicks = 0;
	*/
	private static boolean killPlayer = false;
	private static boolean denyEnderpearl = false;
	private static boolean preventBlockPlace = false;
	private static boolean preventMobSpawn = false;
	
	public static long Now(){
		return System.currentTimeMillis();
	}
	
	public static void setBorder(String name, BorderData border, boolean logIt){
		borders.put(name, border);
		if(logIt){
			log("屏障已设置～" + BorderDescription(name));
		}
		save(true);
	}
	
	public static void setBorder(String name, BorderData border){
		setBorder(name, border);
	}
	
	public static void setBorder(String name, double x_first, double y_first, double z_first, double x_second, double y_second, double z_second){
		setBorder(name, new BorderData(x_first, y_first, z_first, x_second, y_second, z_second));
	}
	
	public static void removeBorder(String name){
		borders.remove(name);
		log("已移除屏障：" + name);
		save(true);
	}
	
	public static void removeAllBorders(){
		borders.clear();
		log("已清除所有屏障");
		save(true);
	}
	
	public static String BorderDescription(String name){
		BorderData border = borders.get(name);
		if(border == null){
			return "没有叫做 \"" + name + "\" 的屏障";
		}else{
			return "屏障 \"" + name + "\" 存在 " + border.toString();
		}
	}
	
	public static Set<String> BorderDescriptions(){
		Set<String> output = new HashSet<String>();
		for(String borderName : borders.keySet()){
			output.add(BorderDescription(borderName));
		}
		
		return output;
	}
	
	public static BorderData Border(String name){
		return borders.get(name);
	}
	
	public static Map<String, BorderData> getBorders(){
		return new LinkedHashMap<String, BorderData>(borders);
	}
	
	public static void setMessage(String msg){
		updateMessage(msg);
		save(true);
	}
	
	public static void updateMessage(String msg){
		message = msg;
		messageFmt = replaceAmpColors(msg);
		messageClean = stripAmpColors(msg);
	}
	
	public static String Message(){
		return messageFmt;
	}
	
	public static String MessageRaw(){
		return message;
	}
	
	public static String MessageClean(){
		return messageClean;
	}
	
	public static void setPreventBlockPlace(boolean enable){
		if(preventBlockPlace != enable){
			GuardEdit.plugin.enableBlockPlaceListener(enable);
		}
		preventBlockPlace = enable;
		log("禁止放置方块功能 " + (enable ? "启用" : "停用"));
		save(true);
	}
	
	public static void setPreventMobSpawn(boolean enable){
		if(preventMobSpawn != enable){
			GuardEdit.plugin.enableMobSpawnListener(enable);
		}
		preventMobSpawn = enable;
		log("禁止怪物生成 " + (enable ? "启用" : "停用"));
		save(true);
	}
	
	public static boolean preventBlockPlace()
	{
		return preventBlockPlace;
	}

	public static boolean preventMobSpawn()
	{
		return preventMobSpawn;
	}

	public static boolean getIfPlayerKill()
	{
		return killPlayer;
	}

	public static boolean getDenyEnderpearl()
	{
		return denyEnderpearl;
	}

	public static void setDenyEnderpearl(boolean enable)
	{
		denyEnderpearl = enable;
		log("通过末影珍珠直接穿越屏障 " + (enable ? "启用" : "停用") + ".");
		save(true);
	}
	
	public static void setKnockBack(double numBlocks)
	{
		knockBack = numBlocks;
		log("触碰后向屏障内退回 " + knockBack + " 个方块");
		save(true);
	}

	public static double KnockBack()
	{
		return knockBack;
	}
	
	public static void setPlayerBypass(UUID player, boolean bypass)
	{
		if (bypass)
			bypassPlayers.add(player);
		else
			bypassPlayers.remove(player);
		save(true);
	}

	public static boolean isPlayerBypassing(UUID player)
	{
		return bypassPlayers.contains(player);
	}

	public static ArrayList<UUID> getPlayerBypassList()
	{
		return new ArrayList(bypassPlayers);
	}

	// for converting bypass UUID list to/from String list, for storage in config
	private static void importBypassStringList(List<String> strings)
	{
		for (String string: strings)
		{
			bypassPlayers.add(UUID.fromString(string));
		}
	}
	private static ArrayList<String> exportBypassStringList()
	{
		ArrayList<String> strings = new ArrayList<String>();
		for (UUID uuid: bypassPlayers)
		{
			strings.add(uuid.toString());
		}
		return strings;
	}
	
	public static int AvailableMemory()
	{
		return (int)((rt.maxMemory() - rt.totalMemory() + rt.freeMemory()) / 1048576);  // 1024*1024 = 1048576 (bytes in 1 MB)
	}

	public static boolean AvailableMemoryTooLow()
	{
		return AvailableMemory() < fillMemoryTolerance;
	}


	public static boolean HasPermission(Player player, String request)
	{
		return HasPermission(player, request, true);
	}
	public static boolean HasPermission(Player player, String request, boolean notify)
	{
		if (player == null)				// console, always permitted
			return true;

		if (player.hasPermission("worldborder." + request))	// built-in Bukkit superperms
			return true;

		if (notify)
			player.sendMessage("You do not have sufficient permissions.");

		return false;
	}


	public static String replaceAmpColors (String message)
	{
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	// adapted from code posted by Sleaker
	public static String stripAmpColors (String message)
	{
		return message.replaceAll("(?i)&([a-fk-or0-9])", "");
	}


	public static void log(Level lvl, String text)
	{
		wbLog.log(lvl, text);
	}
	public static void log(String text)
	{
		log(Level.INFO, text);
	}
	public static void logWarn(String text)
	{
		log(Level.WARNING, text);
	}
	public static void logConfig(String text)
	{
		log(Level.INFO, "[CONFIG] " + text);
	}


	private static final int currentCfgVersion = 11;


}
