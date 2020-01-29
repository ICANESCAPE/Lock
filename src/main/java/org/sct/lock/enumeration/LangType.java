package org.sct.lock.enumeration;

import lombok.Getter;

public enum LangType {

    /**
     * 语言文件的路径
     */
    LANGUAGE_COMMANDHELP("Language.CommandHelp"),
    LANG_NOPERMISSION("Language.NoPermission"),
    LANG_RELOAD("Language.Reload"),
    LANG_CREATE("Language.Create"),
    LANG_COMMANDERROR("Language.CommandError"),
    LANG_ENTER("Language.Enter"),
    LANG_LEAVE("Language.Leave"),
    LANG_NOTENOUGHMONEY("Language.NotEnoughMoney"),
    LANG_BANREDSTONE("Language.BanRedstone");

    @Getter String path;

    LangType(String path) {
        this.path = path;
    }

}
