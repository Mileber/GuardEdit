package net.pearapple.guardedit.cmd;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pearapple.guardedit.GuardEdit;

public class CmdCommands extends GECmd {
	
	private static int pageSize = 8; //10行可用，1行header，1行footer
	
	public CmdCommands(){
		name = "commands";
		permission = "help";
		hasWorldNameInput = false;
	}

	@Override
	public void execute(CommandSender sender, Player player, List<String> params, String worldName) {
		//决定在看哪一页
		int page = (player == null) ? 0 : 1;
		if(!params.isEmpty()){
			try{
				page = Integer.parseInt(params.get(0));
			}catch(NumberFormatException ignored){}
		}
		
		//根据正在展示的页面，决定可获取的页数
		List<String> examples = (player == null) ? cmdExamplesConsole : cmdExamplesPlayer;
		int pageCount = (int)Math.ceil(examples.size() / (double)pageSize);
		
		//如果提供的页码小于0或者大于总页数，默认回到第一页
		if(page < 0 || page > pageCount){
			page = (player == null) ? 0 : 1;
		}
		
		//发送列表header
		sender.sendMessage(C_HEAD + GuardEdit.plugin.getDescription().getFullName() + " - key: " +
				commandEmphasized("command") + C_REQ + "<required> " + C_OPT + "[optional]" );
		
		if(page > 0){
			//发送该页的命令例子
			int first = ((page - 1) * pageSize);
			int count = Math.min(pageSize, examples.size() - first);
			for(int i = first; i < first + count; i++){
				sender.sendMessage(examples.get(i));
			}
			
			String footer = C_HEAD + " (Page " + page + "/" + pageCount + ")              " + cmd(sender);
			if(page < pageCount){
				sender.sendMessage(footer + Integer.toString(page + 1) + C_DESC + " - 查看下一页");
			}else if(page > 1){
				sender.sendMessage(footer + C_DESC + " - 查看第一页");
			}
		}else{
			//page 0，发送所有
			for(String example : examples){
				sender.sendMessage(example);
			}
		}
	}

}
