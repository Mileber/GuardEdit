package net.pearapple.guardedit;

import org.bukkit.plugin.java.JavaPlugin;

public class GuardEdit extends JavaPlugin{

	@Override
	public void onDisable() {
		// 插件卸载时要执行的代码
		super.onDisable();
		getLogger().info("onDisable has been invoked!");
		getCommand("ge").setExecutor(new GECommandExecutor(this));
	}

	@Override
	public void onEnable() {
		// 插件载入时要执行的代码
		super.onEnable();
		getLogger().info("onEnable has been invoked!");
	}

}
