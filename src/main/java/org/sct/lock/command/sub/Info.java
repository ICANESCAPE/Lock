package org.sct.lock.command.sub;

import org.bukkit.command.CommandSender;
import org.sct.lock.Lock;
import org.sct.lock.util.SubCommand;

public class Info implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§7┌ §ePlugin§7:§b Lock");
        sender.sendMessage("§7├ §eAuthor§7:§b 冰星");
        sender.sendMessage("§7├ §eVersion§7:§b " + Lock.getInstance().getDescription().getVersion());
        sender.sendMessage("§7└ §eLink§7:§b https://www.mcbbs.net/thread-932739-1-1.html");

        return true;
    }
}
