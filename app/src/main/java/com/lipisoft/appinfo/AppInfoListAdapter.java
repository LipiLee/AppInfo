package com.lipisoft.appinfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppInfoListAdapter extends RecyclerView.Adapter<AppInfoView> {
    private Context mContext;
    private int mAppItems;

    public AppInfoListAdapter(int numberOfItems) {
        mAppItems = numberOfItems;
    }

    public AppInfoView onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.app_info, parent, false);

        return new AppInfoView(view);
    }

    public void onBindViewHolder(AppInfoView appInfoViewHolder, int position) {
        if (mContext instanceof MainActivity) {
            MainActivity main = (MainActivity) mContext;
            ApplicationInfo item = main.getListApplicationInfo().get(position);
            String version = main.getPackageVersion().get(item.packageName);
            PackageManager pm = mContext.getPackageManager();
            appInfoViewHolder.bind(item.loadIcon(pm), item.loadLabel(pm).toString(), item.packageName, version);
        } else {
            Log.d("App Info", "mContext is not MainActivity.");
        }
    }

    public int getItemCount() {
        return mAppItems;
    }
}
