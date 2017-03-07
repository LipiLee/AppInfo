package com.lipisoft.appinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AppInfoView extends RecyclerView.ViewHolder implements View.OnClickListener{
    public static final String DETAIL_VIEW = "com.lipisoft.appinfo.DETAIL";

    private Context mContext;
    private ImageView mIcon;
    private TextView mAppName;
    private TextView mPackageName;
    private TextView mVersionName;

    public AppInfoView(View itemView) {
        super(itemView);
        mContext = itemView.getContext();

        mIcon = (ImageView) itemView.findViewById(R.id.iconItem);
        mAppName = (TextView) itemView.findViewById(R.id.app_name);
        mPackageName = (TextView) itemView.findViewById(R.id.package_name);
        mVersionName = (TextView) itemView.findViewById(R.id.version_name);

        itemView.setOnClickListener(this);
    }

    void bind(Drawable icon, String appName, String packageName, String versionName) {
        mIcon.getLayoutParams().width = getIconSize();
        mIcon.getLayoutParams().height = getIconSize();
        mIcon.requestLayout();
        mIcon.setImageDrawable(icon);

        mAppName.setText(appName);
        mPackageName.setText(packageName);
        mVersionName.setText(versionName);
    }

    private int getIconSize() {
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

    public void onClick(View v) {
        Intent intent = new Intent(mContext, AppInfoDetailActivity.class);
        intent.putExtra(DETAIL_VIEW, mPackageName.getText().toString());
        MainActivity mainActivity = (MainActivity) mContext;
        mainActivity.startActivity(intent);
    }

}
