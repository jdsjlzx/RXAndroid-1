package com.rocky.rxandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目名称：RXAndroid
 * 类描述：
 * 创建人：y7un
 * 创建时间：2016/5/19 9:47
 * 修改人：y7un
 * 修改时间：2016/5/19 9:47
 * 修改备注：
 */
public class Apps {
    public int groupId;
    public
    @SerializedName("apps")
    List<App> apps;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }
}
