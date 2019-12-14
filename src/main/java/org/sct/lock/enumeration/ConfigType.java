package org.sct.lock.enumeration;

import lombok.Getter;

public enum ConfigType {

    SETTING_ISOTHERALLOWEDOPEN("Setting.IsOtherAllowedOpen"),
    SETTING_ISOTHERALLOWEDBREAK("Setting.IsOtherAllowedBreak"),
    SETTING_WORLDS("Setting.Worlds"),
    SETTING_TAXALLOWED("Setting.TaxAllowed"),
    SETTING_TAXPERCENT("Setting.TaxPercent"),
    SETTING_VIPALLOWED("Setting.VipAllowed"),
    SETTING_TAXCANCELABLE("Setting.TaxCancelable"),
    SETTING_LOCKSYMBOL("Setting.LockSymbol"),
    SETTING_SYMBOLREPLACE("Setting.SymbolReplace"),
    SETTING_FLAGENTER("Setting.FlagEnter"),
    SETTING_FLAGLEAVE("Setting.FLagLeave"),
    SETTING_ENTERREPLACE("Setting.EnterReplace"),
    SETTING_LEAVEREPLACE("Setting.LeaveReplace"),
    SETTING_DOORTYPE("Setting.DoorType"),
    SETTING_SIGNTYPE("Setting.SignType"),
    SETTING_CHARGE("Setting.Charge"),
    SETTING_ENTERDELAY("Setting.EnterDelay");


    @Getter String path;

    ConfigType(String path) {
        this.path = path;
    }

}
