package com.lipisoft.appinfo;

import android.graphics.drawable.Drawable;

public class AppInfoItem {
    private final Drawable mIcon;
    private final String mAppName;
    private final String mPackageName;
    private final String mVersionName;

    public AppInfoItem(Drawable icon, String appName, String packageName, String versionName) {
        mIcon = icon;
        mAppName = appName;
        mPackageName = packageName;
        mVersionName = versionName;
    }

    public String getAppName() {
        return mAppName;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public String getVersionName() {
        return mVersionName;
    }

    public Drawable getIcon() {
        return mIcon;
    }

}
