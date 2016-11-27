package com.zh.cxdadmin.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zh.cxdadmin.R;
import com.zh.cxdadmin.entity.SellerEntity;
import com.zh.cxdadmin.utils.ImageLoaderHelper;

import java.util.List;

/**
 * 派单列表Adapter
 * Created by dell on 2016/11/22.
 */

public class CusSellerAdapter extends BaseAdapter {
    private List<SellerEntity> list;
    private Context context;

    public CusSellerAdapter(List<SellerEntity> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cus_seller, parent, false);
            holder = new ViewHolder();
            holder.iconImg = (ImageView) convertView.findViewById(R.id.cus_seller_list_item_icon_img);
            holder.nameTv = (TextView) convertView.findViewById(R.id.cus_seller_list_item_name_tv);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.cus_seller_list_item_phone_tv);
            holder.dateTv = (TextView) convertView.findViewById(R.id.cus_seller_list_item_date_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final SellerEntity entity = list.get(position);
        //头像
        if (!TextUtils.isEmpty(entity.getAvatar())) {
            ImageLoaderHelper.getInstance().loadCirPic(holder.iconImg, entity.getAvatar());
        }
        //昵称 + 姓名
        holder.nameTv.setText(entity.getName());
        if (!TextUtils.isEmpty(entity.getMobile())) {
            //手机号码
            holder.phoneTv.setText(entity.getMobile());
        }
        if (!TextUtils.isEmpty(entity.getJoinustime())) {
            //加入时间
            holder.dateTv.setText(entity.getJoinustime());
        }
        return convertView;
    }

    /**
     * 移除列表项目
     * @param position
     */
    public void removeItem(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

    class ViewHolder {
        ImageView iconImg;//头像
        TextView nameTv;//姓名
        TextView phoneTv;//电话
        TextView dateTv;//注册日期
    }
}
