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
import com.zh.cxdadmin.config.HttpPath;
import com.zh.cxdadmin.entity.OrderEntity;
import com.zh.cxdadmin.utils.ImageLoaderHelper;

import java.util.List;

/**
 * 已完成Adapter
 * Created by dell on 2016/11/22.
 */

public class OrderFinishAdapter extends BaseAdapter{
    private List<OrderEntity> list;
    private Context context;

    public OrderFinishAdapter(Context context, List<OrderEntity> list) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_orderfinish, parent, false);
            holder = new ViewHolder();
            holder.iconImg = (ImageView) convertView.findViewById(R.id.orderfinish_list_item_icon_img);
            holder.nameTv = (TextView) convertView.findViewById(R.id.orderfinish_list_item_name_tv);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.orderfinish_list_item_phone_tv);
            holder.phoneBtn = (ImageView) convertView.findViewById(R.id.orderfinish_list_item_phone_img);
            holder.orderTv = (TextView) convertView.findViewById(R.id.orderfinish_list_item_ordernum_tv);
            holder.typeTv = (TextView) convertView.findViewById(R.id.orderfinish_list_item_type_tv);
            holder.giveTimeTv = (TextView) convertView.findViewById(R.id.orderfinish_list_item_givetime_tv);
            holder.getTimeTv = (TextView) convertView.findViewById(R.id.orderfinish_list_item_gettime_tv);
            holder.finishTimeTv = (TextView) convertView.findViewById(R.id.orderfinish_list_item_finishtime_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final OrderEntity entity = list.get(position);
        //头像
        if (!TextUtils.isEmpty(entity.getOperavartar())) {
            ImageLoaderHelper.getInstance().loadCirPic(holder.iconImg, HttpPath.HOST + entity.getOperavartar());
        }
        //昵称 + 姓名
        holder.nameTv.setText(entity.getOperator());
        if (!TextUtils.isEmpty(entity.getOperator())) {
            //手机号码
            holder.phoneTv.setText(entity.getOpmobile());
            holder.phoneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + entity.getOpmobile());
                    context.startActivity(new Intent(Intent.ACTION_DIAL, uri));
                }
            });
        }

        if (!TextUtils.isEmpty(entity.getOrderid())) {
            //订单号码
            holder.orderTv.setText(entity.getOrderid());
        }
        if (!TextUtils.isEmpty(entity.getServicetypename())) {
            //洗车类型
            holder.typeTv.setText(entity.getServicetypename());
        }
        //下单时间
        if (!TextUtils.isEmpty(entity.getOrderdate())) {
            holder.giveTimeTv.setText(entity.getOrderdate());
        }
        //接单时间
        if (!TextUtils.isEmpty(entity.getAcceptdate())) {
            holder.getTimeTv.setText(entity.getAcceptdate());
        }
        //完成时间
        if (!TextUtils.isEmpty(entity.getFinishdate())) {
            holder.finishTimeTv.setText(entity.getFinishdate());
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
        TextView orderTv;//订单号
        TextView typeTv;//类型
        TextView giveTimeTv;//下单时间
        TextView getTimeTv;//接单时间
        TextView finishTimeTv;//完成时间
    }
}
