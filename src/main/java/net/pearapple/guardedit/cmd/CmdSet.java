package net.pearapple.guardedit.cmd;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSet extends GECmd {
	
	public CmdSet(){
		name = permission = "set";
		//hasWorldNameInput = true;
		consoleRequiresWorldName = false;
		minParams = 1;
		maxParams = 4;
		
		addCmdExample(nameEmphasized() + "one <x> <y> <z> - 设置第一个点");
		addCmdExample(nameEmphasized() + "two <x> <y> <z> - 设置第二个点");
		addCmdExample(nameEmphasized() + "one ^player <name> - 将玩家视线所指的点作为第一点");
		addCmdExample(nameEmphasized() + "two ^player <name> - 将玩家视线所指的点作为第二点");
		helpText = "根据一些参数选取屏障的第一点和第二点";
	}

	@Override
	public void execute(CommandSender sender, Player player, List<String> params, String worldName) {
		if ((params.size() == 1) && player == null){
			sendErrorAndHelp(sender, "你输入的参数是不是太少了呀～");
			return;
		}
		
		if(player == null){
			if(!params.get(params.size() - 2).equalsIgnoreCase("player")){
				sendErrorAndHelp(sender, "出错啦～");
				return;
			}
			player = Bukkit.getPlayer(params.get(params.size() - 1));
			if(player == null || !player.isOnline()){
				sendErrorAndHelp(sender, "玩家(\"" + params.get(params.size() -1) + "\")看起来不在线哦～");
				return;
			}
			worldName = player.getWorld().getName();
		}
		
		double x_first = 0;
		double y_first = 0;
		double z_first = 0;
		double x_second = 0;
		double y_second = 0;
		double z_second = 0;
		boolean isFirstSet = false;
		boolean isSecondSet = false;
		int locationCount = params.size() - 1;
		try{
			if(params.size() > 3 && params.get(params.size() - 2).equalsIgnoreCase("player")){
				//player名指定
				Player playerT = Bukkit.getPlayer(params.get(params.size() - 1));
				if(playerT == null || !playerT.isOnline()){
					sendErrorAndHelp(sender, "玩家(\"" + params.get(params.size() -1) + "\")看起来不在线哦～");
					return;
				}
				worldName = playerT.getWorld().getName();
				if(params.get(params.size() - 3).equalsIgnoreCase("one")){
					x_first = playerT.getEyeLocation().getX();
					y_first = playerT.getEyeLocation().getY();
					z_first = playerT.getEyeLocation().getZ();
					locationCount -= 3;
					isFirstSet = true;
				}else if(params.get(params.size() - 3).equalsIgnoreCase("two")){
					if (!isFirstSet){
						sendErrorAndHelp(sender, "请先设置第一个点哦～");
						return;
					}
					x_second = playerT.getEyeLocation().getX();
					y_second = playerT.getEyeLocation().getY();
					z_second = playerT.getEyeLocation().getZ();
					locationCount -= 3;
					isSecondSet = true;
				}
			}else{
				if(player == null || locationCount > 3){
					if(params.get(params.size() - 3).equalsIgnoreCase("one")){
						x_first = Double.parseDouble(params.get(params.size() - 3));
						y_first = Double.parseDouble(params.get(params.size() - 2));
						z_first = Double.parseDouble(params.get(params.size() - 1));
						locationCount -= 3;
						isFirstSet = true;
					}else if(params.get(params.size() - 3).equalsIgnoreCase("two")){
						if (!isFirstSet){
							sendErrorAndHelp(sender, "请先设置第一个点哦～");
							return;
						}
						x_second = Double.parseDouble(params.get(params.size() - 3));
						y_second = Double.parseDouble(params.get(params.size() - 2));
						z_second = Double.parseDouble(params.get(params.size() - 1));
						locationCount -= 3;
						isSecondSet = true;
					}
				}else{
					if(params.get(params.size() - 1).equalsIgnoreCase("one")){
						x_first = player.getLocation().getX();
						y_first = player.getLocation().getY();
						z_first = player.getLocation().getZ();
						isFirstSet = true;
					}else if(params.get(params.size() - 1).equalsIgnoreCase("two")){
						if (!isFirstSet){
							sendErrorAndHelp(sender, "请先设置第一个点哦～");
							return;
						}
						x_second = player.getLocation().getX();
						y_second = player.getLocation().getY();
						z_second = player.getLocation().getZ();
						isSecondSet = true;
					}
				}
			}
		}catch(NumberFormatException ex){
			sendErrorAndHelp(sender, "x, y, z坐标请输入数字哟～");
			return;
		}
		
		if(isSecondSet){
			//Config.setBorder(x_first, y_first, z_first, x_second, y_second, z_second);
			sender.sendMessage("屏障即将生成！屏障向量的第一个点为(" + x_first + ", " + y_first + ", " + z_first + ")，第二个点为(" 
					+ x_second + ", " + y_second + ", " + z_second + ")。");
		}
	}

}
