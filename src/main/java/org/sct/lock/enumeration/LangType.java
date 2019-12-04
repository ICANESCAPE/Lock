package org.sct.lock.enumeration;

import lombok.Getter;

public enum LangType {

    /**
     * 语言文件的路径
     */
    LANGUAGE_COMMANDHELP("Language.CommandHelp"),
    LANG_NOPERMISSION("Language.NoPermission"),
    LANG_RELOAD("Language.Reload"),
    LANG_CREATE("Language.Create");

    @Getter String path;

    LangType(String path) {
        this.path = path;
    }

}
