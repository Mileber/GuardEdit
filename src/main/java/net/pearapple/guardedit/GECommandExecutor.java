package net.pearapple.guardedit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class GECommandExecutor implements CommandExecutor {
	
	private GuardEdit plugin;
	
	public GECommandExecutor(GuardEdit plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ge")){ //如果玩家输入/ge就会发生以下事情...
            if (sender instanceof Player){
            	Player player = (Player)sender;
            	if(args.length > 4){
            		//参数为
            		sender.sendMessage("");
            	}
            }else{
            	sender.sendMessage("该指令只能由玩家执行哟");
            	return false;
            }
    } //如果事件发生就会返回true
       //没发生就会返回false
		return false;
	}
	
	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent evt){
		if(evt.getPlayer().getItemInHand().getType() == Material.FISHING_ROD){
			//maximal distance between player and thunder is 200 blocks
			evt.getPlayer().getWorld().strikeLightning(evt.getPlayer().getTargetBlock(null,200).getLocation());
		}else if(evt.getPlayer().getItemInHand().getType() == Material.ARROW){
			Location target_location = evt.getPlayer().getEyeLocation();
			evt.getPlayer().sendMessage("当前目标方块坐标为(" + target_location.getBlockX() + ", " 
					+ target_location.getBlockY() + ", " + target_location.getBlockZ() + ")");
		}
	}

}


