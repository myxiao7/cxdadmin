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
 * 待服务Adapter
 * Created by dell on 2016/11/22.
 */

public class OrderServiceAdapter extends BaseAdapter{
    private List<OrderEntity> list;
    private Context context;
    private boolean isFinish;

    public OrderServiceAdapter(Context context, List<OrderEntity> list, boolean isFinish) {
        this.list = list;
        this.context = context;
        this.isFinish = isFinish;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_orderservice, parent, false);
            holder = new ViewHolder();
            holder.iconImg = (ImageView) convertView.findViewById(R.id.orderservice_list_item_icon_img);
            holder.nameTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_name_tv);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_phone_tv);
            holder.phoneBtn = (ImageView) convertView.findViewById(R.id.orderservice_list_item_phone_img);
            holder.orderTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_order_tv);
            holder.typeTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_type_tv);
            holder.addTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_add_tv);
            holder.timeTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_time_tv);
            holder.carTypeTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_cartype_tv);
            holder.colorTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_color_tv);
            holder.numTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_num_tv);
            holder.priceTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_price_tv);
            holder.dateTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_date_tv);
            holder.dateDesTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_datedes_tv);
            holder.remarkTv = (TextView) convertView.findViewById(R.id.orderservice_list_item_remark_tv);

            holder.iconImg2 = (ImageView) convertView.findViewById(R.id.orderfinish_list_item_icon_img);
            holder.nameTv2 = (TextView) convertView.findViewById(R.id.orderfinish_list_item_name_tv);
            holder.phoneTv2 = (TextView) convertView.findViewById(R.id.orderfinish_list_item_phone_tv);
            holder.phoneBtn2 = (ImageView) convertView.findViewById(R.id.orderfinish_list_item_phone_img);
            holder.orderTv2 = (TextView) convertView.findViewById(R.id.orderfinish_list_item_ordernum_tv);
            holder.typeTv2 = (TextView) convertView.findViewById(R.id.orderfinish_list_item_type_tv);
            holder.giveTimeTv = (TextView) convertView.findViewById(R.id.orderfinish_list_item_givetime_tv);
            holder.getTimeTv = (TextView) convertView.findViewById(R.id.orderfinish_list_item_gettime_tv);
            holder.finishTimeTv = (TextView) convertView.findViewById(R.id.orderfinish_list_item_finishtime_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final OrderEntity entity = list.get(position);
        //头像
        if (!TextUtils.isEmpty(entity.getAvartar())) {
            ImageLoaderHelper.getInstance().loadCirPic(holder.iconImg, entity.getAvartar());
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

       /* if (!TextUtils.isEmpty(entity.getOrderid())) {
            //订单号码
            holder.orderTv.setText("订单号:" + entity.getOrderid());
        }*/
        if (!TextUtils.isEmpty(entity.getServicetypename())) {
            //洗车类型
            holder.typeTv.setText(entity.getServicetypename());
        }
        if (!TextUtils.isEmpty(entity.getLocation())) {
            //地址
            holder.addTv.setText(entity.getLocation());
        }
        if (!TextUtils.isEmpty(entity.getAppointment())) {
            //预约时间
            holder.timeTv.setText(entity.getAppointment());
        }
        if (!TextUtils.isEmpty(entity.getCarbrank())) {
            //车型
            holder.carTypeTv.setText(entity.getCarbrank());
        }
       /* if (!TextUtils.isEmpty(entity.getCarcolor())) {
            //颜色
            holder.colorTv.setText(entity.getCarcolor());
        }*/
       /* if (!TextUtils.isEmpty(entity.getCarno())) {
            //车牌
            holder.numTv.setText(entity.getCarno());
        }*/
        if (!TextUtils.isEmpty(entity.getOrderamount() + "")) {
            //价格
            holder.priceTv.setText("￥" + entity.getOrderamount() + "");
        }
       /* if (!TextUtils.isEmpty(entity.getOrderid())) {
            //订单号码
            holder.orderTv.setText("订单号:"+entity.getOrderid()+"");
        }*/
      /*  //下单时间
        if (!TextUtils.isEmpty(entity.getOrderdate())) {
            holder.dateTv.setText(entity.getOrderdate());
        }
*/
        /*if (!TextUtils.isEmpty(entity.getRemark())) {
            //备注
            holder.remarkTv.setText(entity.getRemark());
        }*/

        //头像
        if (!TextUtils.isEmpty(entity.getOperavartar())) {
            ImageLoaderHelper.getInstance().loadCirPic(holder.iconImg2, HttpPath.HOST + entity.getOperavartar());
        }
        //昵称 + 姓名
        holder.nameTv2.setText(entity.getOperator());
        if (!TextUtils.isEmpty(entity.getOperator())) {
            //手机号码
            holder.phoneTv2.setText(entity.getOpmobile());
            holder.phoneBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + entity.getOpmobile());
                    context.startActivity(new Intent(Intent.ACTION_DIAL, uri));
                }
            });
        }

        if (!TextUtils.isEmpty(entity.getOrderid())) {
            //订单号码
            holder.orderTv2.setText(entity.getOrderid());
        }
        //下单时间
        if (!TextUtils.isEmpty(entity.getOrderdate())) {
            holder.giveTimeTv.setText(entity.getOrderdate());
        }
        //接单时间
        if (!TextUtils.isEmpty(entity.getAcceptdate())) {
            holder.getTimeTv.setText(entity.getAcceptdate());
        }
        if(isFinish){
            //完成时间
            if (!TextUtils.isEmpty(entity.getFinishdate())) {
                holder.finishTimeTv.setText(entity.getFinishdate());
            }
        }else {
            holder.finishTimeTv.setText("未完成");
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
        TextView addTv;//地址
        TextView timeTv;//时间
        TextView carTypeTv;//车型
        TextView colorTv;//颜色
        TextView numTv;//车牌
        TextView priceTv;//价格
        TextView dateTv;//接单时间
        TextView dateDesTv;//接单描述
        TextView remarkTv;//接单描述

        ImageView iconImg2;//头像
        TextView nameTv2;//姓名
        TextView phoneTv2;//电话
        ImageView phoneBtn2;//打电话
        TextView orderTv2;//订单号
        TextView typeTv2;//类型
        TextView giveTimeTv;//下单时间
        TextView getTimeTv;//接单时间
        TextView finishTimeTv;//完成时间
    }
}
