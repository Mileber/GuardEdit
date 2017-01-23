package net.pearapple.guardedit;

import org.bukkit.plugin.java.JavaPlugin;

public class GuardEdit extends JavaPlugin{
	
	public static volatile GuardEdit plugin = null;
	public static volatile GECommandExecutor geCommandExecutor = null;

	@Override
	public void onDisable() {
		// 插件卸载时要执行的代码
		super.onDisable();
		getLogger().info("onDisable has been invoked!");
		getCommand("ge").setExecutor(new GECommandExecutor());
	}

	@Override
	public void onEnable() {
		// 插件载入时要执行的代码
		if(plugin == null){
			plugin = this;
		}
		if(geCommandExecutor == null){
			geCommandExecutor = new GECommandExecutor();
		}
		getLogger().info("onEnable has been invoked!");
	}

}
