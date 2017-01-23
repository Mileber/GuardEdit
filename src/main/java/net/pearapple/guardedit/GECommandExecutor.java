package net.pearapple.guardedit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import net.pearapple.guardedit.cmd.CmdCommands;
import net.pearapple.guardedit.cmd.CmdHelp;
import net.pearapple.guardedit.cmd.GECmd;

public class GECommandExecutor implements CommandExecutor {
	
	public Map<String, GECmd> subCommands = new LinkedHashMap<String, GECmd>();
	
	private Set<String> subCommandsWithWorldNames = new LinkedHashSet<String>();
	
	//构造函数
	public GECommandExecutor(){
		addCmd(new CmdHelp());
		
		//默认命令，显示命令例子页面
		addCmd(new CmdCommands());
	}
	
	private void addCmd(GECmd cmd){
		subCommands.put(cmd.name, cmd);
		if(cmd.hasWorldNameInput){
			subCommandsWithWorldNames.add(cmd.name);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
		Player player = (sender instanceof Player) ? (Player)sender : null;
		
		//如果world名字在引用符号中被传递，处理它，并获取List<String>而不是String[]
		List<String> params = concatenateQuotedWorldName(split);
		
		String worldName = null;
		
		//判断第二个参数是否为命令，第一个参数是否为world名字。如果world名字在引用符号里定义它
		if (wasWorldQuotation || (params.size() > 1 && !subCommands.containsKey(params.get(0)) && subCommandsWithWorldNames.contains(params.get(1))))
			worldName = params.get(0);

		// no command specified? show command examples / help
		if (params.isEmpty())
			params.add(0, "commands");

		// determined the command name
		String cmdName = (worldName == null) ? params.get(0).toLowerCase() : params.get(1).toLowerCase();

		// remove command name and (if there) world name from front of param array
		params.remove(0);
		if (worldName != null)
			params.remove(0);

		// make sure command is recognized, default to showing command examples / help if not; also check for specified page number
		if (!subCommands.containsKey(cmdName))
		{
			int page = (player == null) ? 0 : 1;
			try
			{
				page = Integer.parseInt(cmdName);
			}
			catch(NumberFormatException ignored)
			{
				sender.sendMessage(GECmd.C_ERR + "无法识别你的命令，以下为命令列表。");
			}
			cmdName = "commands";
			params.add(0, Integer.toString(page));
		}

		GECmd subCommand = subCommands.get(cmdName);

		// check permission
		/*
		if (!Config.HasPermission(player, subCommand.permission))
			return true;
		*/

		// if command requires world name when run by console, make sure that's in place
		if (player == null && subCommand.hasWorldNameInput && subCommand.consoleRequiresWorldName && worldName == null)
		{
			sender.sendMessage(GECmd.C_ERR + "这个命令需要提供world名字哦～");
			subCommand.sendCmdHelp(sender);
			return true;
		}

		// make sure valid number of parameters has been provided
		if (params.size() < subCommand.minParams || params.size() > subCommand.maxParams)
		{
			if (subCommand.maxParams == 0)
				sender.sendMessage(GECmd.C_ERR + "这个命令没有参数呀～");
			else
				sender.sendMessage(GECmd.C_ERR + "你输入的命令参数数量不对哦～");
			subCommand.sendCmdHelp(sender);
			return true;
		}

		// execute command
		subCommand.execute(sender, player, params, worldName);

		return true;
	}

	private boolean wasWorldQuotation = false;

	// if world name is surrounded by quotation marks, combine it down and flag wasWorldQuotation if it's first param.
	// also return List<String> instead of input primitive String[]
	private List<String> concatenateQuotedWorldName(String[] split)
	{
		wasWorldQuotation = false;
		List<String> args = new ArrayList<String>(Arrays.asList(split));

		int startIndex = -1;
		for (int i = 0; i < args.size(); i++)
		{
			if (args.get(i).startsWith("\""))
			{
				startIndex = i;
				break;
			}
		}
		if (startIndex == -1)
			return args;

		if (args.get(startIndex).endsWith("\""))
		{
			args.set(startIndex, args.get(startIndex).substring(1, args.get(startIndex).length() - 1));
			if (startIndex == 0)
				wasWorldQuotation = true;
		}
		else
		{
			List<String> concat = new ArrayList<String>(args);
			Iterator<String> concatI = concat.iterator();

			// skip past any parameters in front of the one we're starting on
			for (int i = 1; i < startIndex + 1; i++)
			{
				concatI.next();
			}

			StringBuilder quote = new StringBuilder(concatI.next());
			while (concatI.hasNext())
			{
				String next = concatI.next();
				concatI.remove();
				quote.append(" ");
				quote.append(next);
				if (next.endsWith("\""))
				{
					concat.set(startIndex, quote.substring(1, quote.length() - 1));
					args = concat;
					if (startIndex == 0)
						wasWorldQuotation = true;
					break;
				}
			}
		}
		return args;
	}

	public Set<String> getCommandNames()
	{
		// using TreeSet to sort alphabetically
		Set<String> commands = new TreeSet(subCommands.keySet());
		// removing default "commands" command as it's not normally shown or run like other commands
		commands.remove("commands");
		return commands;
	}

}


