package com.example.awl.lab3;

import android.support.design.widget.FloatingActionButton;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ListView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;


public class Goods_list extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        findAllViews();
        load_data();
        set_Listener();
    }

    private ListView shoppingList;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFAButton;
    private AlertDialog.Builder mbuilder;
    private List<Goods_data> data = new ArrayList<>();
    private List<Goods_data> shopping_list = new ArrayList<>();
    final ShoppingListAdapter mAdapter = new ShoppingListAdapter(data, Goods_list.this);
    final ShoppingCarListAdapter CarAdapter = new ShoppingCarListAdapter(shopping_list,Goods_list.this);

    final String[] product_name = new String[] {"Enchated Forest","Arla Milk","Devondale Milk","Kindle Oassis","waitrose 早餐麦片","Mcvitie's 饼干","Ferrero Rocher","Maltesers","Lindt","Borggreve"};
    final String[] product_price = new String[] {"¥ 5.00","¥ 59.00","¥ 79.00","¥ 2399.00","¥ 179.00","¥ 14.90","¥ 132.59","¥ 141.43","¥ 139.43","¥ 28.90"};
    final String[] product_info_type = new String[] {"作者","产地","产地","版本","重量","产地","重量","重量","重量","重量"};
    final String[] product_info = new String[] {"   Johanna Basford","   德国","   澳大利亚","   8GB","   2Kg","   英国","   300g","   118g","   249g","   640g"};
    final int[] imageID = new int[]{R.drawable.enchatedforest,R.drawable.arla,R.drawable.devondale,R.drawable.kindle,R.drawable.waitrose,R.drawable.mcvitie,R.drawable.ferrero,R.drawable.maltesers,R.drawable.lindt,R.drawable.borggreve};



    private void findAllViews() {
        setContentView(R.layout.home_page);
        this.shoppingList = ((ListView)findViewById(R.id.shoppinglist));
        this.mRecyclerView = ((RecyclerView)findViewById(R.id.recycler_view));
        this.mbuilder = new AlertDialog.Builder(this);
        this.mFAButton = ((FloatingActionButton)findViewById(R.id.shoplist));

    }

    private void load_data()
    {
        for(int i=0;i<9;i++) {
            this.data.add(new Goods_data(product_name[i],product_price[i],product_info_type[i],product_info[i],imageID[i]));
        }
        Goods_data temp = new Goods_data("购物车", "价格  ", null, null, 0);
        shopping_list.add(temp);
        shoppingList.setAdapter(CarAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnItemClickListener(new ShoppingListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                  Intent intent = new Intent(Goods_list.this, Goods_detail.class);
                  Goods_data deliver = new Goods_data(data.get(position).getName(),data.get(position).getPrice(),data.get(position).getType(),data.get(position).getInfomation(),data.get(position).getImageId());
                  Bundle localBundle = new Bundle();
                  localBundle.putSerializable("goods", deliver);
                  intent.putExtras(localBundle);
                  Goods_list.this.startActivityForResult(intent, 1);
            }
            @Override
            public void onLongClick(int position) {
                Toast.makeText(Goods_list.this,"移除第"+String.valueOf(position+1)+"个商品",Toast.LENGTH_SHORT).show();
                data.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
        //mRecyclerView.setAdapter(mAdapter);
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(mAdapter);
        animationAdapter.setDuration(1000);
        mRecyclerView.setAdapter(animationAdapter);
        mRecyclerView.setItemAnimator(new OvershootInLeftAnimator());
    }

    private void set_Listener(){
        this.mFAButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (Goods_list.this.mRecyclerView.getVisibility() == View.INVISIBLE)
                {
                    Goods_list.this.mRecyclerView.setVisibility(View.VISIBLE);
                    Goods_list.this.shoppingList.setVisibility(View.INVISIBLE);
                    Goods_list.this.mFAButton.setImageResource(R.drawable.shoplist);
                    return;
                }
                Goods_list.this.mRecyclerView.setVisibility(View.INVISIBLE);
                Goods_list.this.shoppingList.setVisibility(View.VISIBLE);
                Goods_list.this.mFAButton.setImageResource(R.drawable.mainpage);
            }
        });
        this.mbuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface AnonymousDialogInterface, int paramAnonymousInt) {
                Toast.makeText(getApplicationContext(), "你选择了[取消]", Toast.LENGTH_SHORT).show();
            }
        });

        this.shoppingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?>  AdapterView, View view, int i, long l) {
                if (i == 0)
                    return;
                Intent localIntent = new Intent(Goods_list.this, Goods_detail.class);
                Bundle localBundle = new Bundle();
                localBundle.putSerializable("goods", (Serializable)Goods_list.this.shopping_list.get(i));
                localIntent.putExtras(localBundle);
                Goods_list.this.startActivityForResult(localIntent, 1);
            }
        });
        this.shoppingList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            public boolean onItemLongClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, final int i, long paramAnonymousLong) {
                if (i == 0)
                    return true;
                Goods_list.this.mbuilder.setMessage("从购物车移除" + (Goods_list.this.shopping_list.get(i)).getName() + "？");
                Goods_list.this.mbuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {
                        Goods_list.this.shopping_list.remove(i);
                        CarAdapter.notifyDataSetChanged();
                    }
                }).create().show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 1 && resultCode ==1){
            Goods_data localGoods = (Goods_data)intent.getExtras().get("goods");
            this.shopping_list.add(localGoods);
            CarAdapter.notifyDataSetChanged();
        }
    }

}
