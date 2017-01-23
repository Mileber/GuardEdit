package net.pearapple.guardedit.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class GECmd {
	
	//命令名称，权限；一般情况下一致
	public String name = "";
	public String permission = null;
	
	public boolean hasWorldNameInput = false;
	public boolean consoleRequiresWorldName = true;
	
	//可接受参数的最小和最大值
	public int minParams = 0;
	public int maxParams = 9999;
	
	public String helpText = null;
	
	//需要在子类中被重载，命令运行
	public abstract void execute(CommandSender sender, Player player, List<String> params, String worldName);
	
	//可选重载
	public void cmdStatus(CommandSender sender){}
	
	//字符串颜色
	public final static String C_CMD = ChatColor.AQUA.toString();
	public final static String C_DESC = ChatColor.WHITE.toString();
	public final static String C_ERR = ChatColor.RED.toString();
	public final static String C_HEAD = ChatColor.YELLOW.toString();
	public final static String C_OPT = ChatColor.DARK_GREEN.toString();
	public final static String C_REQ = ChatColor.GREEN.toString();
	
	public final static String CMD_C = C_CMD + "wb ";
	public final static String CMD_P = C_CMD + "/wb";
	
	//显示命令例子的列表，通过addCmdExample函数间接的在子类构造函数中添加
	public List<String> cmdExamplePlayer = new ArrayList<String>();
	public List<String> cmdExampleConsole = new ArrayList<String>();
	
	//和上面类似，用于在/ge 命令显示命令列表，暂定16个
	public final static List<String> cmdExamplesConsole = new ArrayList<String>(16);
	public final static List<String> cmdExamplesPlayer = new ArrayList<String>(16);
	
	public void addCmdExample(String example){
		addCmdExample(example, true, true, true);
	}
	
	public void addCmdExample(String example, boolean forPlayer, boolean forConsole, boolean prefix){
		
		//给要求参数"<>"，可选参数"[]"，额外命令和描述换颜色
		example = example.replace("<", C_REQ + "<").replace("[", C_OPT + "[").replace("^", C_CMD).replace("- ", C_DESC + "- ");
		
		//所有的"{}"对玩家替换为"[]"（可选的），对后台为"<>"（必要的）
		if(forPlayer){
			String exampleP = (prefix ? CMD_P : "") + example.replace("{", C_OPT + "[").replace("- ", C_DESC + "- ");
			cmdExamplePlayer.add(exampleP);
			cmdExamplesPlayer.add(exampleP);
		}
		if(forConsole){
			String exampleC = (prefix ? CMD_C : "") + example.replace("{", C_REQ + "<").replace("}", ">");
			cmdExampleConsole.add(exampleC);
			cmdExamplesConsole.add(exampleC);
		}
	}
	
	//根据sender，为玩家或后台返回格式化的根指令
	public String cmd(CommandSender sender){
		return (sender instanceof Player)? CMD_P : CMD_C;
	}
	
	//格式化文字，用于标记命令名
	public String commandEmphasized(String text){
		return C_CMD + ChatColor.UNDERLINE + text + ChatColor.RESET + " ";
	}
	
	//返回绿色的“enabled”或者红色的“disabled”
	public String enabledColored(boolean enabled){
		return enabled ? C_REQ + "enabled" : C_ERR + "disabled";
	}
	
	//格式化并给文字上色，选择性的加上前缀："[world]"(玩家)/"<world>"(后台)
	public String nameEmphasized(){
		return commandEmphasized(name);
	}
	
	public String nameEmphasizedW(){
		return "{world}" + nameEmphasized();
	}
	
	//发送命令样式信息和其他帮助信息
	public void sendCmdHelp(CommandSender sender){
		for(String example : ((sender instanceof Player) ? cmdExamplePlayer : cmdExampleConsole)){
			sender.sendMessage(example);
		}
		cmdStatus(sender);
		if(helpText != null && !helpText.isEmpty()){
			sender.sendMessage(C_DESC + helpText);
		}
	}
	
	//在命令例子信息后发送错误信息
	public void sendErrorAndHelp(CommandSender sender, String error){
		sender.sendMessage(C_ERR + error);
		sendCmdHelp(sender);
	}
	
	public boolean strAsBool(String str)
	{
		str = str.toLowerCase();
		return str.startsWith("y") || str.startsWith("t") || str.startsWith("on") || str.startsWith("+") || str.startsWith("1");
	}
}











