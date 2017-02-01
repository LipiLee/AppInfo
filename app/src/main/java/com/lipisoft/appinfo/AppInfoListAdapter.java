package com.lipisoft.appinfo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

class AppInfoListAdapter extends BaseAdapter {
    private Context mContext;

    private List<AppInfoItem> mItems = new ArrayList<>();

    AppInfoListAdapter(Context context) {
        mContext = context;
    }

    void addItem(AppInfoItem it) {
        mItems.add(it);
    }

    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final AppInfoView itemView;
        if (convertView == null) {
            itemView = new AppInfoView(mContext, mItems.get(position));
        } else {
            itemView = (AppInfoView) convertView;

            itemView.setIcon(mItems.get(position).getIcon());
            itemView.setAppName(mItems.get(position).getAppName());
            itemView.setPackageName(mItems.get(position).getPackageName());
            itemView.setVersionName(mItems.get(position).getVersionName());
        }

        return itemView;
    }

}
