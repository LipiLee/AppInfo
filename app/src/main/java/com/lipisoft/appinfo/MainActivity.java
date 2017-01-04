package com.lipisoft.appinfo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.text);
        final ListViewAutoScrollHelper listViewAutoScrollHelper;

        final StringBuilder stringBuilder = new StringBuilder();

        final PackageManager packageManager = getPackageManager();
        final List<ApplicationInfo> listApplicationInfo =
                packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo: listApplicationInfo) {
            final String applicationName = applicationInfo.loadLabel(packageManager).toString();
            final Drawable drawable = applicationInfo.loadIcon(packageManager);

            stringBuilder.append(applicationName).append("(")
                    .append(applicationInfo.packageName).append(")")
                    .append("\n");
        }

        textView.setText(stringBuilder.toString());
    }
}
