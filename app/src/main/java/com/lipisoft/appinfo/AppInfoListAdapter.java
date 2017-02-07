package com.lipisoft.appinfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppInfoListAdapter extends RecyclerView.Adapter<AppInfoView> {
    private int mAppItems;
    private Context mContext;
    private PackageManager pm;

    public AppInfoListAdapter(int numberOfItems, Context context, PackageManager packageManager) {
        mAppItems = numberOfItems;
        mContext = context;
        this.pm = packageManager;
    }

    public AppInfoView onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.app_info, parent, false);

        return new AppInfoView(view);
    }

    public void onBindViewHolder(AppInfoView appInfoViewHolder, int position) {
        final MainActivity main = (MainActivity) mContext;
        final ApplicationInfo item = main.getListApplicationInfo().get(position);
        final String version = main.getPackageVersion().get(item.packageName);
        final Drawable icon = item.loadIcon(pm);
        final String name = item.loadLabel(pm).toString();

        appInfoViewHolder.bind(icon, name, item.packageName, version);
    }

    public int getItemCount() {
        return mAppItems;
    }
}
