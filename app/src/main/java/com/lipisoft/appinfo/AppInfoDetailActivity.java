package com.lipisoft.appinfo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.StringBuilderPrinter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AppInfoDetailActivity extends AppCompatActivity {
    private ApplicationInfo applicationInfo;
    private StringBuilder appInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appinfo_detail);

        final TextView detailTextView = (TextView) findViewById(R.id.textView);
        final Intent intent = getIntent();
        final String packageName = intent.getStringExtra(MainActivity.DETAIL_VIEW);
        final PackageInfo packageInfo;
        try {
            packageInfo = AppInfoPackageManager.getPackageInfo(this, packageName);
        } catch (NullPointerException e) {
            Toast.makeText(this, packageName + " " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        applicationInfo = packageInfo.applicationInfo;

        detailTextView.setText(getAppInformation());

//        final ImageView banner = (ImageView) findViewById(R.id.banner);
        final ImageView icon = (ImageView) findViewById(R.id.icon);
//        final ImageView logo = (ImageView) findViewById(R.id.logo);
//        final ImageView unbadgedIcon = (ImageView) findViewById(R.id.unbadged_icon);

        final PackageManager packageManager = getPackageManager();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
//            banner.setImageDrawable(applicationInfo.loadBanner(packageManager));
//        }

        icon.setImageDrawable(applicationInfo.loadIcon(packageManager));

//        logo.setImageDrawable(applicationInfo.loadLogo(packageManager));
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
//            unbadgedIcon.setImageDrawable(applicationInfo.loadUnbadgedIcon(packageManager));
//        }
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(applicationInfo.loadLabel(packageManager).toString());

    }

    private void addAppInfoItem(int resId, String name) {
        appInfo.append(getString(resId));
        addDelimiter(appInfo);
        appInfo.append(name);
        appInfo.append("\n");
    }

    private String getBackupAgentName() {
        if (applicationInfo.backupAgentName != null) {
            return applicationInfo.backupAgentName;
        }
        return getString(R.string.not_available);
    }

    private String getClassName() {
        if (applicationInfo.className != null) {
            return applicationInfo.className;
        }
        return getString(R.string.not_available);
    }

    private String getCompatibleWidthLimitDp() {
        return Integer.toString(applicationInfo.compatibleWidthLimitDp);
    }

    private String getDataDirectory() {
        if (applicationInfo.dataDir != null) {
            return applicationInfo.dataDir;
        }
        return getString(R.string.not_available);
    }

    private String getDescriptionResourceId() {
        return Integer.toString(applicationInfo.descriptionRes);
    }

    private String getDeviceProtectedDataDirectory() {
        if (applicationInfo.deviceProtectedDataDir != null) {
            return applicationInfo.deviceProtectedDataDir;
        }
        return getString(R.string.not_available);
    }

    private String getEnabled() {
        return String.valueOf(applicationInfo.enabled);
    }

    private String getAppInformation() {
        appInfo = new StringBuilder();
        final StringBuilderPrinter appInfoPrinter = new StringBuilderPrinter(appInfo);
        applicationInfo.dump(appInfoPrinter, "");
/*
        addAppInfoItem(R.string.backup_agent_name, getBackupAgentName());

        addAppInfoItem(R.string.class_name, getClassName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            addAppInfoItem(R.string.compatible_width_limit_dp, getCompatibleWidthLimitDp());
        }

        addAppInfoItem(R.string.data_directory, getDataDirectory());

        addAppInfoItem(R.string.description_resource_id, getDescriptionResourceId());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            addAppInfoItem(R.string.device_protected_data_directory, getDeviceProtectedDataDirectory());
        }

        addAppInfoItem(R.string.enabled, getEnabled());

        appInfo.append("Flag: ")
                .append(addListString(getFlags(applicationInfo.flags)))
                .append("\n");

        appInfo.append("Largest Width Limit DP: ")
                .append(String.valueOf(applicationInfo.largestWidthLimitDp))
                .append("\n");

        appInfo.append("Manage Space Activity Name: ");
        if (applicationInfo.manageSpaceActivityName != null) {
            appInfo.append(applicationInfo.manageSpaceActivityName);
        }
        appInfo.append("\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            appInfo.append("Minimum SDK Version: ")
                    .append(String.valueOf(applicationInfo.minSdkVersion))
                    .append("\n");
        }

        appInfo.append("Native Library Directory: ");
        if (applicationInfo.nativeLibraryDir != null) {
            appInfo.append(applicationInfo.nativeLibraryDir);
        }
        appInfo.append("\n");

        appInfo.append("Permission: ");
        if (applicationInfo.permission != null) {
            appInfo.append(applicationInfo.permission);
        }
        appInfo.append("\n");

        appInfo.append("Process Name: ");
        if (applicationInfo.processName != null) {
            appInfo.append(applicationInfo.processName);
        }
        appInfo.append("\n");

        appInfo.append("Public Source Directory: ");
        if (applicationInfo.publicSourceDir != null) {
            appInfo.append(applicationInfo.publicSourceDir);
        }
        appInfo.append("\n");

        appInfo.append("Requires Smallest Width DP: ")
                .append(String.valueOf(applicationInfo.requiresSmallestWidthDp))
                .append("\n");

        appInfo.append("Shared Library Files: ");
        if (applicationInfo.sharedLibraryFiles != null) {
            for (String libraryFile : applicationInfo.sharedLibraryFiles) {
                if (libraryFile != null) {
                    appInfo.append(libraryFile).append(" ");
                }
            }
        }
        appInfo.append("\n");

        appInfo.append("Source Directory: ");
        if (applicationInfo.sourceDir != null) {
            appInfo.append(applicationInfo.sourceDir);
        }
        appInfo.append("\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appInfo.append("Split Public Source Directories: ");
            if (applicationInfo.splitPublicSourceDirs != null) {
                for (String dir : applicationInfo.splitPublicSourceDirs) {
                    appInfo.append(dir);
                }
            }
            appInfo.append("\n");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appInfo.append("Split Source Directories: ");
            if (applicationInfo.splitSourceDirs != null) {
                for (String dir : applicationInfo.splitSourceDirs) {
                    appInfo.append(dir);
                }
            }
            appInfo.append("\n");
        }

        appInfo.append("Target SDK Version: ")
                .append(String.valueOf(applicationInfo.targetSdkVersion))
                .append("\n");

        appInfo.append("Task Affinity: ").append(applicationInfo.taskAffinity)
                .append("\n");

        appInfo.append("Theme: ").append(String.valueOf(applicationInfo.theme))
                .append("\n");

        appInfo.append("UI Options: ").append(String.valueOf(applicationInfo.uiOptions)).append("\n");

        appInfo.append("uid: ").append(String.valueOf(applicationInfo.uid)).append("\n");

        appInfo.append("\n").append("PackageItemInfo").append("\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            appInfo.append("Banner: ").append(String.valueOf(applicationInfo.banner)).append("\n");
        }

        appInfo.append("Icon: ").append(String.valueOf(applicationInfo.icon)).append("\n");

        appInfo.append("Label Resource: ").append(String.valueOf(applicationInfo.labelRes)).append("\n");

        appInfo.append("Logo: ").append(String.valueOf(applicationInfo.logo)).append("\n");

        // Skip Bundle metaData

        appInfo.append("Name: ");
        if (applicationInfo.name != null) {
            appInfo.append(applicationInfo.name);
        }
        appInfo.append("\n");

        appInfo.append("Non Localized Label: ");
        if (applicationInfo.nonLocalizedLabel != null) {
            appInfo.append(applicationInfo.nonLocalizedLabel);
        }
        appInfo.append("\n");

        appInfo.append("Package Name: ");
        if (applicationInfo.packageName != null) {
            appInfo.append(applicationInfo.packageName);
        }
        appInfo.append("\n");
*/

        return appInfo.toString();
    }

    private List<String> getFlags(int flags) {
        final List<String> flagsList = new ArrayList<>();

        // https://developer.android.com/reference/android/content/pm/ApplicationInfo.html#flags
        if ((flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
            flagsList.add("FLAG_SYSTEM");
        }

        if ((flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            flagsList.add("FLAG_DEBUGGABLE");
        }

        if ((flags & ApplicationInfo.FLAG_HAS_CODE) != 0) {
            flagsList.add("FLAG_HAS_CODE");
        }

        if ((flags & ApplicationInfo.FLAG_PERSISTENT) != 0) {
            flagsList.add("FLAG_PERSISTENT");
        }

        if ((flags & ApplicationInfo.FLAG_FACTORY_TEST) != 0) {
            flagsList.add("FLAG_FACTORY_TEST");
        }

        if ((flags & ApplicationInfo.FLAG_ALLOW_TASK_REPARENTING) != 0) {
            flagsList.add("FLAG_ALLOW_TASK_REPARENTING");
        }

        if ((flags & ApplicationInfo.FLAG_ALLOW_CLEAR_USER_DATA) != 0) {
            flagsList.add("FLAG_ALLOW_CLEAR_USER_DATA");
        }

        if ((flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
            flagsList.add("FLAG_UPDATED_SYSTEM_APP");
        }

        if ((flags & ApplicationInfo.FLAG_TEST_ONLY) != 0) {
            flagsList.add("FLAG_TEST_ONLY");
        }

        if ((flags & ApplicationInfo.FLAG_SUPPORTS_SMALL_SCREENS) != 0) {
            flagsList.add("FLAG_SUPPORTS_SMALL_SCREENS");
        }

        if ((flags & ApplicationInfo.FLAG_SUPPORTS_NORMAL_SCREENS) != 0) {
            flagsList.add("FLAG_SUPPORTS_NORMAL_SCREENS");
        }

        if ((flags & ApplicationInfo.FLAG_SUPPORTS_LARGE_SCREENS) != 0) {
            flagsList.add("FLAG_SUPPORTS_LARGE_SCREENS");
        }

        if ((flags & ApplicationInfo.FLAG_SUPPORTS_XLARGE_SCREENS) != 0) {
            flagsList.add("FLAG_SUPPORTS_XLARGE_SCREENS");
        }

        if ((flags & ApplicationInfo.FLAG_RESIZEABLE_FOR_SCREENS) != 0) {
            flagsList.add("FLAG_RESIZEABLE_FOR_SCREENS");
        }

        if ((flags & ApplicationInfo.FLAG_SUPPORTS_SCREEN_DENSITIES) != 0) {
            flagsList.add("FLAG_SUPPORTS_SCREEN_DENSITIES");
        }

        if ((flags & ApplicationInfo.FLAG_VM_SAFE_MODE) != 0) {
            flagsList.add("FLAG_VM_SAFE_MODE");
        }

        if ((flags & ApplicationInfo.FLAG_ALLOW_BACKUP) != 0) {
            flagsList.add("FLAG_ALLOW_BACKUP");
        }

        if ((flags & ApplicationInfo.FLAG_KILL_AFTER_RESTORE) != 0) {
            flagsList.add("FLAG_KILL_AFTER_RESTORE");
        }

        if ((flags & ApplicationInfo.FLAG_RESTORE_ANY_VERSION) != 0) {
            flagsList.add("FLAG_RESTORE_ANY_VERSION");
        }

        if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
            flagsList.add("FLAG_EXTERNAL_STORAGE");
        }

        if ((flags & ApplicationInfo.FLAG_LARGE_HEAP) != 0) {
            flagsList.add("FLAG_LARGE_HEAP");
        }

        if ((flags & ApplicationInfo.FLAG_STOPPED) != 0) {
            flagsList.add("FLAG_STOPPED");
        }

        if ((flags & ApplicationInfo.FLAG_SUPPORTS_RTL) != 0) {
            flagsList.add("FLAG_SUPPORTS_RTL");
        }

        if ((flags & ApplicationInfo.FLAG_INSTALLED) != 0) {
            flagsList.add("FLAG_INSTALLED");
        }

        if ((flags & ApplicationInfo.FLAG_IS_DATA_ONLY) != 0) {
            flagsList.add("FLAG_IS_DATA_ONLY");
        }

        if ((flags & ApplicationInfo.FLAG_IS_GAME) != 0) {
            flagsList.add("FLAG_IS_GAME");
        }

        if ((flags & ApplicationInfo.FLAG_FULL_BACKUP_ONLY) != 0) {
            flagsList.add("FLAG_FULL_BACKUP_ONLY");
        }

        if ((flags & ApplicationInfo.FLAG_USES_CLEARTEXT_TRAFFIC) != 0) {
            flagsList.add("FLAG_USES_CLEARTEXT_TRAFFIC");
        }

        if ((flags & ApplicationInfo.FLAG_MULTIARCH) != 0) {
            flagsList.add("FLAG_MULTIARCH");
        }

        return flagsList;
    }

    private String addListString(List<String> list) {
        StringBuilder sb = new StringBuilder();

        for (String string: list) {
            sb.append("\t").append(string).append("\n");
        }
        return sb.toString();
    }

    private void addDelimiter(StringBuilder sb) {
        sb.append(": ");
    }
}
