package com.lipisoft.appinfo;

import android.graphics.drawable.Drawable;

class AppInfoItem {
    private final Drawable mIcon;
    private final String mAppName;
    private final String mPackageName;
    private final String mVersionName;

    AppInfoItem(Drawable icon, String appName, String packageName, String versionName) {
        mIcon = icon;
        mAppName = appName;
        mPackageName = packageName;
        mVersionName = versionName;
    }

    String getAppName() {
        return mAppName;
    }

    String getPackageName() {
        return mPackageName;
    }

    String getVersionName() {
        return mVersionName;
    }

    public Drawable getIcon() {
        return mIcon;
    }

}
