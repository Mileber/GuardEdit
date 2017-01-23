package net.pearapple.guardedit.cmd;

import java.util.List;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pearapple.guardedit.GuardEdit;

public class CmdHelp extends GECmd{
	
	public CmdHelp(){
		name = permission = "help";
		minParams = 0;
		maxParams = 10;
		
		addCmdExample(nameEmphasized() + "[命令] - 帮你看看这命令是干嘛的");
	}

	@Override
	public void cmdStatus(CommandSender sender){
		String commands = GuardEdit.geCommandExecutor.getCommandNames().toString().replace(", ", C_DESC + ", " + C_CMD);
		sender.sendMessage(C_HEAD + "命令: " + C_CMD + commands.substring(1, commands.length() - 1));
		sender.sendMessage("比如，要查看set命令的: " + cmd(sender) + nameEmphasized() + C_CMD + "set");
		sender.sendMessage(C_HEAD + "如果你要看完整的列表，只要运行没参数的" + cmd(sender) + C_HEAD + "就好啦~");
	}
	
	@Override
	public void execute(CommandSender sender, Player player, List<String> params, String worldName) {
		if(params.isEmpty()){
			sendCmdHelp(sender);
			return;
		}
		
		Set<String> commands = GuardEdit.geCommandExecutor.getCommandNames();
		for(String param : params){
			if(commands.contains(param.toLowerCase())){
				GuardEdit.geCommandExecutor.subCommands.get(param.toLowerCase()).sendCmdHelp(sender);
				return;
			}
		}
		sendErrorAndHelp(sender, "没识别到命令哟，是不是打错了喵？");
	}

}
