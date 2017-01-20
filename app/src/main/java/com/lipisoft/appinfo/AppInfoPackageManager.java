package com.lipisoft.appinfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

class AppInfoPackageManager {
    @Nullable
    static PackageInfo getPackageInfo(@NonNull Context context, @NonNull String packageName) {
        final PackageManager packageManager = context.getPackageManager();
//        final List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
        final List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_SHARED_LIBRARY_FILES);
        for (PackageInfo packageInfo: packageInfoList) {
            if (packageName.equals(packageInfo.packageName)) {
                return packageInfo;
            }
        }
        return null;
    }
}
