package com.lipisoft.appinfo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AppInfo";
    private final AppInfoListAdapter mAdapter = new AppInfoListAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list);

        final PackageManager packageManager = getPackageManager();
        final List<PackageInfo> listPackageInfo = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);

        for (PackageInfo packageInfo: listPackageInfo) {
            final ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            final Drawable icon = applicationInfo.loadIcon(packageManager);

            final String applicationName = applicationInfo.loadLabel(packageManager).toString();
            final String packageName = applicationInfo.packageName;
            final String versionName = packageInfo.versionName;

            mAdapter.addItem(new AppInfoItem(icon, applicationName, packageName, versionName));
        }

        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final AppInfoItem curItem = (AppInfoItem) mAdapter.getItem(position);
                final String appName = curItem.getAppName();

                Toast.makeText(getApplicationContext(), "Selected : " + appName, Toast.LENGTH_LONG).show();
            }
        });
    }}
