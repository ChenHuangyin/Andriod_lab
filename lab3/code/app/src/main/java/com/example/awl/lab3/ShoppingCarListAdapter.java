package com.example.awl.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.awl.lab3.Goods_data;


import java.util.List;

public class ShoppingCarListAdapter extends BaseAdapter
{
    private Context context;
    private List<Goods_data> dataList;

    public ShoppingCarListAdapter(List<Goods_data> paramList,Context paramContext)
    {
        this.context = paramContext;
        this.dataList = paramList;
    }

    private boolean isNUll()
    {
        return this.dataList == null;
    }

    public int getCount()
    {
        if (isNUll())
            return 0;
        return this.dataList.size();
    }

    public List<Goods_data> getDataList()
    {
        return this.dataList;
    }

    public Object getItem(int pos)
    {
        if (isNUll())
            return null;
        return this.dataList.get(pos);
    }

    public long getItemId(int paramInt)
    {
        return paramInt;
    }

    public View getView(int pos, View view, ViewGroup paramViewGroup)
    {
        View localView;
        ViewHolder localViewHolder;
        if (view == null)
        {
            localView = LayoutInflater.from(this.context).inflate(R.layout.shopping_car, null);
            localViewHolder = new ViewHolder();
            localViewHolder.image = ((TextView)localView.findViewById(R.id.first));
            localViewHolder.name = ((TextView)localView.findViewById(R.id.shopcar_name));
            localViewHolder.price = ((TextView)localView.findViewById(R.id.price));
            localView.setTag(localViewHolder);
        }
        else
        {
            localView = view;
            localViewHolder = (ViewHolder)localView.getTag();
        }
        localViewHolder.image.setText(dataList.get(pos).getName().substring(0,1).toUpperCase());
        if(dataList.get(pos).getName() == "购物车") {
            localViewHolder.image.setText("*");
        }
        localViewHolder.name.setText(dataList.get(pos).getName());
        localViewHolder.price.setText(dataList.get(pos).getPrice());
        return localView;
    }

    private class ViewHolder
    {
        public TextView image;
        public TextView name;
        public TextView price;

        private ViewHolder(){

        }
    }
}