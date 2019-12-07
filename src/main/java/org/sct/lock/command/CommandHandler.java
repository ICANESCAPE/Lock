package org.sct.lock.command;

import com.google.common.collect.Maps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import org.sct.lock.Lock;
import org.sct.lock.command.sub.Help;
import org.sct.lock.command.sub.Reload;
import org.sct.lock.enumeration.LangType;
import org.sct.lock.file.Lang;
import org.sct.lock.util.SubCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author SCT_Alchemy
 * @date 2018/12/21 12:46 PM
 */

public class CommandHandler implements CommandExecutor, TabCompleter {
    protected static final String cmds = "lock";
    private Map<String, SubCommand> subCommandMap = Maps.newHashMap();

    public CommandHandler() {
        subCommandMap.put("reload",new Reload());
        subCommandMap.put("help",new Help());
    }

    public void registerSubCommand(String commandName, SubCommand command) {
        if (subCommandMap.containsKey(commandName)) {
            Lock.getInstance().getLogger().warning("发现重复子命令注册!");
        }
        subCommandMap.put(commandName, command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmds.equalsIgnoreCase(cmd.getName())) {
            if(args.length == 0) {//如果命令没有参数
                if(!(sender instanceof Player)) {
                    subCommandMap.get("admin").execute(sender, args);
                    return true;
                }
                return true;
            }

            SubCommand subCommand = subCommandMap.get(args[0]);
            if (subCommand == null) {//如果参数不正确
                sender.sendMessage(Lang.getString(LangType.LANG_NOPERMISSION));
                return true;
            }
            subCommand.execute(sender, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase(cmds)) {
            if (args.length == 1) {
                completions.add("reload");
                completions.add("help");
                return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
            }
        }
        return completions;
    }
}


