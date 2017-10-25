package com.example.awl.lab3;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;
/**
 * Created by AWL on 2017/10/24.
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListHolder>{
    private List<Goods_data> mdata;
    private OnItemClickListener mOnItemClickListener = null;
    private Context context;

    public ShoppingListAdapter(List<Goods_data> mdata,Context context){
        this.context = context;
        this.mdata = mdata;
    }

    @Override
    public int getItemCount() {
        return this.mdata.size();
    }

    public ShoppingListHolder onCreateViewHolder(ViewGroup viewGroup, int View_type){
        View view= LayoutInflater.from(context).inflate(R.layout.editable_item,viewGroup,false);
        return new ShoppingListHolder(view);
    }

    public  void onBindViewHolder(final ShoppingListHolder holder, final int pos){
        holder.first.setText(mdata.get(pos).getName().substring(0,1).toUpperCase());
        holder.name.setText(mdata.get(pos).getName());
        if (this.mOnItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View V) {
                    mOnItemClickListener.onClick(holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View V) {
                    mOnItemClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    class ShoppingListHolder extends RecyclerView.ViewHolder{
        TextView first,name;
        public ShoppingListHolder(View view){
            super(view);
            first=(TextView)view.findViewById(R.id.first1);
            name=(TextView)view.findViewById(R.id.list_name);
        }
    }

    public void setOnItemClickListener(OnItemClickListener ItemClickListener)
    {
        this.mOnItemClickListener = ItemClickListener;
    }

    public static abstract interface OnItemClickListener {
        public abstract void onClick(int paramInt);
        public abstract void onLongClick(int paramInt);
    }
}
