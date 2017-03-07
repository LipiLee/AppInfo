package com.lipisoft.appinfo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AppInfoDetailActivity extends AppCompatActivity {
    private ApplicationInfo applicationInfo;
    private StringBuilder appInfo;
    private ListView listView;
    private List<String> applicationItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appinfo_detail);

        listView = (ListView) findViewById(R.id.listView);

        final Intent intent = getIntent();
        final String packageName = intent.getStringExtra(AppInfoView.DETAIL_VIEW);
        final PackageInfo packageInfo;
        try {
            packageInfo = AppInfoPackageManager.getPackageInfo(this, packageName);
        } catch (NullPointerException e) {
            Toast.makeText(this, packageName + " " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        applicationInfo = packageInfo.applicationInfo;

//        detailTextView.setText(getAppInformation());
        listView = (ListView) findViewById(R.id.listView);
        applicationItems = new ArrayList<>();

        getAppInformation();

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
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(applicationInfo.loadLabel(packageManager).toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, applicationItems);
        listView.setAdapter(adapter);

    }

//    private void addAppInfoItem(int resId, String name) {
//        appInfo.append(getString(resId));
//        addDelimiter(appInfo);
//        appInfo.append(name);
//        appInfo.append("\n");
//    }

    private void addAppInfoItem(int resId, String name) {

        StringBuilder aItem = new StringBuilder();
        aItem.append(getString(resId)).append(": ").append(name);

        applicationItems.add(aItem.toString());

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

    private void getAppInformation() {
        appInfo = new StringBuilder();
//        final StringBuilderPrinter appInfoPrinter = new StringBuilderPrinter(appInfo);
//        applicationInfo.dump(appInfoPrinter, "");

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
        // TODO handle flags
//        addAppInfoItem("Flag: ", getFlags(applicationInfo.flags));

        addAppInfoItem(R.string.largest_width_limit_dp, String.valueOf(applicationInfo.largestWidthLimitDp));

        if (applicationInfo.manageSpaceActivityName != null) {
            addAppInfoItem(R.string.manage_space_activity_name, applicationInfo.manageSpaceActivityName);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            addAppInfoItem(R.string.minimum_sdk_version, String.valueOf(applicationInfo.minSdkVersion));
        }

        if (applicationInfo.nativeLibraryDir != null) {
            addAppInfoItem(R.string.native_library_directory, applicationInfo.nativeLibraryDir);
        }

        if (applicationInfo.permission != null) {
            addAppInfoItem(R.string.permission, applicationInfo.permission);
        }


        if (applicationInfo.processName != null) {
            addAppInfoItem(R.string.process_name, applicationInfo.processName);
        }

        if (applicationInfo.publicSourceDir != null) {
            addAppInfoItem(R.string.public_source_directory, applicationInfo.publicSourceDir);
        }

        addAppInfoItem(R.string.requires_smallest_width_dp, String.valueOf(applicationInfo.requiresSmallestWidthDp));

        // TODO shared library files
//        appInfo.append("Shared Library Files: ");
//        if (applicationInfo.sharedLibraryFiles != null) {
//            for (String libraryFile : applicationInfo.sharedLibraryFiles) {
//                if (libraryFile != null) {
//                    appInfo.append(libraryFile).append(" ");
//                }
//            }
//        }
//        appInfo.append("\n");

        if (applicationInfo.sourceDir != null) {
            addAppInfoItem(R.string.source_directory, applicationInfo.sourceDir);
        }

        // TODO split public source directories
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            appInfo.append("Split Public Source Directories: ");
//            if (applicationInfo.splitPublicSourceDirs != null) {
//                for (String dir : applicationInfo.splitPublicSourceDirs) {
//                    appInfo.append(dir);
//                }
//            }
//            appInfo.append("\n");
//        }

        // TODO split source directories
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            appInfo.append("Split Source Directories: ");
//            if (applicationInfo.splitSourceDirs != null) {
//                for (String dir : applicationInfo.splitSourceDirs) {
//                    appInfo.append(dir);
//                }
//            }
//            appInfo.append("\n");
//        }

        addAppInfoItem(R.string.target_sdk_version, String.valueOf(applicationInfo.targetSdkVersion));

        if (applicationInfo.taskAffinity != null) {
            addAppInfoItem(R.string.task_affinity, applicationInfo.taskAffinity);
        }

        addAppInfoItem(R.string.theme, String.valueOf(applicationInfo.theme));

        addAppInfoItem(R.string.ui_options, String.valueOf(applicationInfo.uiOptions));

        addAppInfoItem(R.string.uid, String.valueOf(applicationInfo.uid));

        // TODO packageItemInfo
//        addAppInfoItem(R.string.packageiteminfo, );
//        appInfo.append("\n").append("PackageItemInfo").append("\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            addAppInfoItem(R.string.banner, String.valueOf(applicationInfo.banner));
        }

        addAppInfoItem(R.string.icon, String.valueOf(applicationInfo.icon));

        addAppInfoItem(R.string.label_resource, String.valueOf(applicationInfo.labelRes));

        addAppInfoItem(R.string.logo, String.valueOf(applicationInfo.logo));

        // TODO Skip Bundle metaData

        if (applicationInfo.name != null) {
            addAppInfoItem(R.string.name, applicationInfo.name);
        }

        if (applicationInfo.nonLocalizedLabel != null) {
            addAppInfoItem(R.string.non_localized_label, applicationInfo.nonLocalizedLabel.toString());
        }

        if (applicationInfo.packageName != null) {
            addAppInfoItem(R.string.package_name, applicationInfo.packageName);
        }

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
