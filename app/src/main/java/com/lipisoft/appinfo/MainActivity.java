package com.lipisoft.appinfo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String DETAIL_VIEW = "com.lipisoft.appinfo.DETAIL";
    private List<ApplicationInfo> listApplicationInfo;
    private Map<String, String> packageVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView listView = (RecyclerView) findViewById(R.id.list);

        final PackageManager packageManager = getPackageManager();
        final List<PackageInfo> listPackageInfo = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
        listApplicationInfo = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        Collections.sort(listApplicationInfo, new ApplicationInfo.DisplayNameComparator(packageManager));

        packageVersion = new ArrayMap<>();
        for (PackageInfo packageInfo: listPackageInfo) {
            packageVersion.put(packageInfo.packageName, packageInfo.versionName);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listView.getContext(), layoutManager.getOrientation());
        listView.addItemDecoration(dividerItemDecoration);
        final AppInfoListAdapter mAdapter = new AppInfoListAdapter(listApplicationInfo.size(), this, packageManager);

        listView.setAdapter(mAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                final AppInfoItem curItem = (AppInfoItem) mAdapter.getItem(position);
//
//                final Intent intent = new Intent(MainActivity.this, AppInfoDetailActivity.class);
//                intent.putExtra(DETAIL_VIEW, curItem.getPackageName());
//                startActivity(intent);
//            }
//        });
    }

    public List<ApplicationInfo> getListApplicationInfo() {
        return listApplicationInfo;
    }

    public Map<String, String> getPackageVersion() {
        return packageVersion;
    }
}
