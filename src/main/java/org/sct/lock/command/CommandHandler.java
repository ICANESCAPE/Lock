package org.sct.lock.command;

import com.google.common.collect.Maps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.sct.lock.Lock;

import org.sct.lock.util.SubCommand;

import java.util.Map;

/**
 * @author SCT_Alchemy
 * @date 2018/12/21 12:46 PM
 */

public class CommandHandler implements CommandExecutor  {

    protected static final String cmds = "lock";
    private Map<String, SubCommand> subCommandMap = Maps.newHashMap();

    public CommandHandler() {

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
            if(args.length == 0) {
                if(!(sender instanceof Player)) {
                    subCommandMap.get("admin").execute(sender, args);
                    return true;
                }
                return true;
            }

            SubCommand subCommand = subCommandMap.get(args[0]);
            if (subCommand == null) {
                sender.sendMessage("§c未知指令!");
                return true;
            }
            subCommand.execute(sender, args);
        }
        return false;
    }

}


