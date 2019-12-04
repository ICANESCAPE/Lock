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
    SETTING_ENTER("Setting.Enter"),
    SETTING_LEAVE("Setting.Leave"),
    SETTING_LINETHREE("Setting.LineThree");


    @Getter String path;

    ConfigType(String path) {
        this.path = path;
    }

}
