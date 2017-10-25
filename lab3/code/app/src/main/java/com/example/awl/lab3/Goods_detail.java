package com.example.awl.lab3;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by AWL on 2017/10/25.
 */

public class Goods_detail extends AppCompatActivity {
    private ImageView back;
    private ImageView buy;
    private Goods_data goods;
    private TextView info;
    private TextView name;
    private ListView operationList;
    private TextView price;
    private ImageView star;
    private ImageView topBack;
    private TextView type;

    private void findView()
    {
        setContentView(R.layout.goods_details);
        this.topBack = ((ImageView)findViewById(R.id.top_back));
        this.back = ((ImageView)findViewById(R.id.back));
        this.star = ((ImageView)findViewById(R.id.star));
        this.name = ((TextView)findViewById(R.id.name));
        this.price = ((TextView)findViewById(R.id.price));
        this.type = ((TextView)findViewById(R.id.type));
        this.info = ((TextView)findViewById(R.id.info));
        this.operationList = ((ListView)findViewById(R.id.operation_list));
        this.buy = ((ImageView)findViewById(R.id.buy));
    }

    private void initialData()
    {
        Resources localResources = getResources();
        this.goods = ((Goods_data) getIntent().getExtras().get("goods"));
        if (this.goods != null)
        {
            this.topBack.setImageResource(this.goods.getImageId());
            this.name.setText(this.goods.getName());
            this.price.setText(this.goods.getPrice());
            this.type.setText(this.goods.getType());
            this.info.setText(this.goods.getInfomation());
        }
        String[] arrayOfString = new String[4];
        arrayOfString[0] = localResources.getString(R.string.item1);
        arrayOfString[1] = localResources.getString(R.string.item2);
        arrayOfString[2] = localResources.getString(R.string.item3);
        arrayOfString[3] = localResources.getString(R.string.item4);
        ArrayAdapter localArrayAdapter = new ArrayAdapter<>(this, R.layout.detail_list_item, arrayOfString);
        this.operationList.setAdapter(localArrayAdapter);
        this.star.setTag("0");
    }

    private void setListener()
    {
        this.back.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Goods_detail.this.finish();
            }
        });
        this.star.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if ((v.getTag()) == "0")
                {
                    v.setBackgroundResource(R.drawable.full_star);
                    v.setTag("1");
                    return;
                }
                v.setBackgroundResource(R.drawable.empty_star);
                v.setTag("0");
            }
        });
        this.buy.setClickable(true);
        this.buy.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(Goods_detail.this, Goods_list.class);
                Bundle localBundle = new Bundle();
                localBundle.putSerializable("goods",Goods_detail.this.goods);
                localIntent.putExtras(localBundle);
                Goods_detail.this.setResult(1,localIntent);
                Toast.makeText(Goods_detail.this.getApplicationContext(), "商品已加到购物车", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        findView();
        initialData();
        setListener();
    }
}
