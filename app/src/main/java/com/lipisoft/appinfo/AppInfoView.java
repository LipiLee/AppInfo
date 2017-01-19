package com.lipisoft.appinfo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AppInfoView extends LinearLayout {
    private Context mContext;
    private ImageView mIcon;
    private TextView mAppName;
    private TextView mPackageName;
    private TextView mVersionName;

    public AppInfoView(Context context, AppInfoItem aItem) {
        super(context);
        mContext = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.app_info, this, true);

        mIcon = (ImageView) findViewById(R.id.iconItem);

        mIcon.getLayoutParams().height = getIconSize();
        mIcon.getLayoutParams().width = getIconSize();
        mIcon.requestLayout();
        mIcon.setImageDrawable(aItem.getIcon());

        mAppName = (TextView) findViewById(R.id.app_name);
        mAppName.setText(aItem.getAppName());

        mPackageName = (TextView) findViewById(R.id.package_name);
        mPackageName.setText(aItem.getPackageName());

        mVersionName = (TextView) findViewById(R.id.version_name);
        mVersionName.setText(aItem.getVersionName());

    }

    public void setAppName(String appName) {
        mAppName.setText(appName);
    }

    public void setPackageName(String packageName) {
        mPackageName.setText(packageName);
    }

    public void setVersionName(String versionName) {
        mVersionName.setText(versionName);
    }

    public void setIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
    }

    protected int getIconSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((MainActivity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // https://developer.android.com/guide/practices/ui_guidelines/icon_design_launcher.html#size
        if (metrics.densityDpi <= DisplayMetrics.DENSITY_LOW)
            return 36;
        else if (metrics.densityDpi <= DisplayMetrics.DENSITY_MEDIUM)
            return 48;
        else if (metrics.densityDpi <= DisplayMetrics.DENSITY_HIGH)
            return 72;
        else if (metrics.densityDpi <= DisplayMetrics.DENSITY_XHIGH)
            return 96;
        else if (metrics.densityDpi <= DisplayMetrics.DENSITY_XXHIGH)
            return 144;
        else if (metrics.densityDpi <= DisplayMetrics.DENSITY_XXXHIGH)
            return 192;

        return 288;
    }

}
