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
import com.zh.cxdadmin.entity.CustomerEntity;
import com.zh.cxdadmin.entity.SellerEntity;
import com.zh.cxdadmin.utils.ImageLoaderHelper;

import java.util.List;

/**
 * 客户Adapter
 * Created by dell on 2016/11/22.
 */

public class CusAdapter extends BaseAdapter {
    private List<CustomerEntity> list;
    private Context context;

    public CusAdapter(Context context, List<CustomerEntity> list) {
        this.list = list;
        this.context = context;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_customer, parent, false);
            holder = new ViewHolder();
            holder.iconImg = (ImageView) convertView.findViewById(R.id.cus_list_item_icon_img);
            holder.nameTv = (TextView) convertView.findViewById(R.id.cus_list_item_name_tv);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.cus_list_item_phone_tv);
            holder.phoneBtn = (ImageView) convertView.findViewById(R.id.cus_list_item_phone_img);
            holder.dateTv = (TextView) convertView.findViewById(R.id.cus_list_item_date_tv);
            holder.typeTv = (TextView) convertView.findViewById(R.id.cus_list_item_type_tv);
            holder.colorTv = (TextView) convertView.findViewById(R.id.cus_list_item_color_tv);
            holder.moneyTv = (TextView) convertView.findViewById(R.id.cus_list_item_money_tv);
            holder.countTv = (TextView) convertView.findViewById(R.id.cus_list_item_count_tv);
            holder.lastTv = (TextView) convertView.findViewById(R.id.cus_list_item_last_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final CustomerEntity entity = list.get(position);
        //头像
        if (!TextUtils.isEmpty(entity.getAvatar())) {
            ImageLoaderHelper.getInstance().loadCirPic(holder.iconImg, entity.getAvatar());
        }
        //昵称 + 姓名
        holder.nameTv.setText(entity.getName());
        if (!TextUtils.isEmpty(entity.getMobile())) {
            //手机号码
            holder.phoneTv.setText(entity.getMobile());
            holder.phoneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + entity.getMobile());
                    context.startActivity(new Intent(Intent.ACTION_DIAL, uri));
                }
            });
        }
        if (!TextUtils.isEmpty(entity.getJoinustime())) {
            //加入时间
            holder.dateTv.setText(entity.getJoinustime());
        }

        if (!TextUtils.isEmpty(entity.getCarbrand())) {
            //车型
            holder.typeTv.setText(entity.getCarbrand());
        }
        if (!TextUtils.isEmpty(entity.getCarcolor())) {
            //颜色和车牌
            holder.colorTv.setText(entity.getCarcolor()+" " + entity.getCarnum());
        }
        if (!TextUtils.isEmpty(entity.getAccount())) {
            //余额
            holder.moneyTv.setText("账户余额：" +entity.getAccount());
        }
        if (!TextUtils.isEmpty(entity.getPreferencebeans())) {
            //红包数量
            holder.countTv.setText("红包数量：" +entity.getPreferencebeans());
        }
        if (!TextUtils.isEmpty(entity.getClickbeanstime())) {
            //红包最后点击时间
            holder.lastTv.setText("最后点击红包时间：" +entity.getClickbeanstime());
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
        ImageView phoneBtn;//打电话
        TextView dateTv;//注册日期
        TextView typeTv;//车型
        TextView colorTv;//颜色和车牌
        TextView moneyTv;//账号余额
        TextView countTv;//红包数量
        TextView lastTv;//红包最后点击时间

    }
}
