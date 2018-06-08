package com.wangguang.model.enums;

/**
 * 代理商配置KEY
 *
 * @author Demon
 * @since 23/03/2018
 */
public enum EnumAgentConfigKey {
    SHARE_TITLE("share_title", "分享标题"),
    SHARE_DESC("share_desc", "分享描述"),
    SHARE_ICON("share_icon", "分享图标"),
    SHARE_LINK("share_link", "分享链接")
    ;

    public String value;
    public String name;

    EnumAgentConfigKey(String value, String name) {
        this.value = value;
        this.name = name;
    }


}
