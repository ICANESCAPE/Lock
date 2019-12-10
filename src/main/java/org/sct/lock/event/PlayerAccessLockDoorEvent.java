package org.sct.lock.event;

import lombok.Getter;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author alchemy
 * @since 2019/12/9/17:13:13
 */
public class PlayerAccessLockDoorEvent extends Event {

    @Getter private static final HandlerList handlers = new HandlerList();

    @Getter Player payer;
    @Getter Player owner;
    @Getter Sign sign;

    /**
     * 构造函数
     *
     * @param payer 支付费用使用收费门的玩家
     * @param owner 收费门的所有者
     * @param sign 收费门上面的木牌，用于判断信息
     */
    public PlayerAccessLockDoorEvent(Player payer, Player owner, Sign sign) {
        this.payer = payer;
        this.owner = owner;
        this.sign = sign;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
