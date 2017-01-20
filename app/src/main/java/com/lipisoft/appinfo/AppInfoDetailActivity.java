package com.lipisoft.appinfo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AppInfoDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appinfo_detail);

        final TextView detailTextView = (TextView) findViewById(R.id.textView);
        final Intent intent = getIntent();
        final String packageName = intent.getStringExtra(MainActivity.DETAIL_VIEW);
        final PackageInfo packageInfo = AppInfoPackageManager.getPackageInfo(this, packageName);

        if (packageInfo == null) {
            return;
        }
        final ApplicationInfo applicationInfo = packageInfo.applicationInfo;

        final StringBuilder sb = new StringBuilder();

        sb.append("Backup Agent Name: ");
        if (applicationInfo.backupAgentName != null) {
            sb.append(applicationInfo.backupAgentName);
        }
        sb.append("\n");

        sb.append("Class Name: ");
        if (applicationInfo.className != null) {
            sb.append(applicationInfo.className);
        }
        sb.append("\n");

        sb.append("Compatible Width Limit DP: ")
                .append(String.valueOf(applicationInfo.compatibleWidthLimitDp))
                .append("\n");

        sb.append("Description Resource ID: ")
                .append(String.valueOf(applicationInfo.descriptionRes))
                .append("\n");

        sb.append("Data Directory: ");
        if (applicationInfo.dataDir != null) {
            sb.append(applicationInfo.dataDir);
        }
        sb.append("\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sb.append("Device Protected Data Directory: ");
            if (applicationInfo.deviceProtectedDataDir != null) {
                sb.append(applicationInfo.deviceProtectedDataDir);
            }
            sb.append("\n");
        }

        sb.append("Enable: ").append(String.valueOf(applicationInfo.enabled))
                .append("\n");

        sb.append("Flag: ")
                .append(addListString(getFlags(applicationInfo.flags)))
                .append("\n");

        sb.append("Largest Width Limit DP: ")
                .append(String.valueOf(applicationInfo.largestWidthLimitDp))
                .append("\n");

        sb.append("Manage Space Activity Name: ");
        if (applicationInfo.manageSpaceActivityName != null) {
            sb.append(applicationInfo.manageSpaceActivityName);
        }
        sb.append("\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sb.append("Minimum SDK Version: ")
                    .append(String.valueOf(applicationInfo.minSdkVersion))
                    .append("\n");
        }

        sb.append("Native Library Directory: ");
        if (applicationInfo.nativeLibraryDir != null) {
            sb.append(applicationInfo.nativeLibraryDir);
        }
        sb.append("\n");

        sb.append("Permission: ");
        if (applicationInfo.permission != null) {
            sb.append(applicationInfo.permission);
        }
        sb.append("\n");

        sb.append("Process Name: ");
        if (applicationInfo.processName != null) {
            sb.append(applicationInfo.processName);
        }
        sb.append("\n");

        sb.append("Public Source Directory: ");
        if (applicationInfo.publicSourceDir != null) {
            sb.append(applicationInfo.publicSourceDir);
        }
        sb.append("\n");

        sb.append("Requires Smallest Width DP: ")
                .append(String.valueOf(applicationInfo.requiresSmallestWidthDp))
                .append("\n");

        sb.append("Shared Library Files: ");
        if (applicationInfo.sharedLibraryFiles != null) {
            for (String libraryFile : applicationInfo.sharedLibraryFiles) {
                if (libraryFile != null) {
                    sb.append(libraryFile).append(" ");
                }
            }
        }
        sb.append("\n");

        sb.append("Source Directory: ");
        if (applicationInfo.sourceDir != null) {
            sb.append(applicationInfo.sourceDir);
        }
        sb.append("\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sb.append("Split Public Source Directories: ");
            if (applicationInfo.splitPublicSourceDirs != null) {
                for (String dir : applicationInfo.splitPublicSourceDirs) {
                    sb.append(dir);
                }
            }
            sb.append("\n");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sb.append("Split Source Directories: ");
            if (applicationInfo.splitSourceDirs != null) {
                for (String dir : applicationInfo.splitSourceDirs) {
                    sb.append(dir);
                }
            }
            sb.append("\n");
        }

        sb.append("Target SDK Version: ")
                .append(String.valueOf(applicationInfo.targetSdkVersion))
                .append("\n");

        sb.append("Task Affinity: ").append(applicationInfo.taskAffinity)
                .append("\n");

        sb.append("Theme: ").append(String.valueOf(applicationInfo.theme))
                .append("\n");

        sb.append("UI Options: ").append(String.valueOf(applicationInfo.uiOptions)).append("\n");

        sb.append("uid: ").append(String.valueOf(applicationInfo.uid)).append("\n");

        sb.append("\n").append("PackageItemInfo").append("\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            sb.append("Banner: ").append(String.valueOf(applicationInfo.banner)).append("\n");
        }

        sb.append("Icon: ").append(String.valueOf(applicationInfo.icon)).append("\n");

        sb.append("Label Resource: ").append(String.valueOf(applicationInfo.labelRes)).append("\n");

        sb.append("Logo: ").append(String.valueOf(applicationInfo.logo)).append("\n");

        // Skip Bundle metaData

        sb.append("Name: ");
        if (applicationInfo.name != null) {
            sb.append(applicationInfo.name);
        }
        sb.append("\n");

        sb.append("Non Localized Label: ");
        if (applicationInfo.nonLocalizedLabel != null) {
            sb.append(applicationInfo.nonLocalizedLabel);
        }
        sb.append("\n");

        sb.append("Package Name: ");
        if (applicationInfo.packageName != null) {
            sb.append(applicationInfo.packageName);
        }
        sb.append("\n");


        detailTextView.setText(sb.toString());
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
}
