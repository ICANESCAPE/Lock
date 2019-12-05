package org.sct.lock.command.sub;

import org.bukkit.command.CommandSender;

import org.sct.lock.Lock;
import org.sct.lock.enumeration.LangType;
import org.sct.lock.file.Lang;
import org.sct.lock.util.SubCommand;

public class Reload implements SubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Lang.getString(LangType.LANG_NOPERMISSION));
        }

        Lang.loadLang();
        Lock.getInstance().saveDefaultConfig();
        Lock.getInstance().saveConfig();

        sender.sendMessage(Lang.getString(LangType.LANG_RELOAD));
        return true;
    }

}
