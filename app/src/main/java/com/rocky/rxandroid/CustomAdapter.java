package com.rocky.rxandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rocky.rxandroid.model.App;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：RXAndroid
 * 类描述：
 * 创建人：y7un
 * 创建时间：2016/5/19 11:46
 * 修改人：y7un
 * 修改时间：2016/5/19 11:46
 * 修改备注：
 */
public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<App> appList;

    public CustomAdapter(Context context, List<App> appList) {
        this.context = context;
        this.appList = appList;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (null != appList && appList.size() > 0)
            ret = appList.size();
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null != convertView) {
            holder = ((ViewHolder) convertView.getTag());
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        App app = appList.get(position);
        //picasso
//        Picasso.with(context).load(app.logoUrl).error(R.mipmap.ic_launcher).into(holder.itemsIcon);
        //Glide
        Glide.with(context).load(app.logoUrl).into(holder.itemsIcon);
        holder.itemsName.setText(app.name);
        holder.itemsDes.setText(app.singleWord);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.items_icon)
        ImageView itemsIcon;
        @Bind(R.id.items_name)
        TextView itemsName;
        @Bind(R.id.items_des)
        TextView itemsDes;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
