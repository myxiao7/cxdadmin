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
 * 待接单Adapter
 * Created by dell on 2016/11/22.
 */

public class SellerAdapter extends BaseAdapter {
    private List<SellerEntity> list;
    private Context context;

    public SellerAdapter(Context context, List<SellerEntity> list) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_seller, parent, false);
            holder = new ViewHolder();
            holder.iconImg = (ImageView) convertView.findViewById(R.id.seller_list_item_icon_img);
            holder.nameTv = (TextView) convertView.findViewById(R.id.seller_list_item_name_tv);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.seller_list_item_phone_tv);
            holder.phoneBtn = (ImageView) convertView.findViewById(R.id.seller_list_item_phone_img);
            holder.dateTv = (TextView) convertView.findViewById(R.id.seller_list_item_date_tv);
            holder.verifyTv = (TextView) convertView.findViewById(R.id.seller_list_item_verify_tv);
            holder.giveTv = (TextView) convertView.findViewById(R.id.seller_list_item_give_tv);
            holder.countTv = (TextView) convertView.findViewById(R.id.seller_list_item_count_tv);
            holder.moneyTv = (TextView) convertView.findViewById(R.id.seller_list_item_money_tv);
            holder.cardTv = (TextView) convertView.findViewById(R.id.seller_list_item_card_tv);
            holder.addTv = (TextView) convertView.findViewById(R.id.seller_list_item_add_tv);
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
        //审核状态
        switch (entity.getIspass()){
            case 0:
                holder.verifyTv.setText("审核状态：未通过");
                break;
            case 1:
                holder.verifyTv.setText("审核状态：已通过");
                break;
            case 2:
                holder.verifyTv.setText("审核状态：待审核");
                break;
        }


        holder.giveTv.setText(entity.getIsaceptorders() == 0 ?"派单状态：不派单":"派单状态：正常");

        if (!TextUtils.isEmpty(entity.getOrdernum())) {
            //完成数量
            holder.countTv.setText("完成订单数量：" +entity.getOrdernum());
        }
        if (!TextUtils.isEmpty(entity.getOrderamount())) {
            //完成金额
            holder.moneyTv.setText("完成订单金额：" +entity.getOrderamount());
        }
        if (!TextUtils.isEmpty(entity.getCardno())) {
            //银行卡号
            holder.cardTv.setText("银行卡号：" +entity.getCardno());
        }
        if (!TextUtils.isEmpty(entity.getLocation())) {
            //地址
            holder.addTv.setText("所在地：" +entity.getLocation());
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
        TextView verifyTv;//审核状态
        TextView giveTv;//派单状态
        TextView countTv;//完成数量
        TextView moneyTv;//完成金额
        TextView cardTv;//卡号
        TextView addTv;//地址

    }
}
