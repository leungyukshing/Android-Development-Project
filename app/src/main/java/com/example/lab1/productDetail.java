package com.example.lab1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class productDetail extends AppCompatActivity {
    TextView name;
    TextView detail;
    TextView type;
    ImageView back;
    RelativeLayout topLayout;
    ListView operationList;
    ImageView star;
    ImageView collect;
    int index;
    boolean isStar;
    boolean buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        name = (TextView)findViewById(R.id.productName);
        detail = (TextView)findViewById(R.id.subtitle);
        type = (TextView)findViewById(R.id.type);
        back = (ImageView)findViewById(R.id.back);
        topLayout = (RelativeLayout)findViewById(R.id.top);
        operationList = (ListView)findViewById(R.id.operationList);
        star = (ImageView)findViewById(R.id.star);
        collect = (ImageView)findViewById(R.id.collect);

        final Bundle product = getIntent().getExtras();
        name.setText(product.getString("name"));
        detail.setText("富含 " + product.getString("nutrition"));
        type.setText(product.getString("type"));
        topLayout.setBackgroundColor(Color.parseColor(product.getString("color")));
        index = product.getInt("index");
        isStar = product.getBoolean("star");
        buy = false;

        if (isStar) {
            star.setImageDrawable(getResources().getDrawable(R.drawable.full_star));
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDetail.this.finish();
            }
        });

        star.setTag(isStar);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean tag = (boolean) v.getTag();
                isStar = !isStar;
                if (!tag) {
                    Intent intent = new Intent(v.getContext(), ShopList.class);
                    intent.putExtra("index", index);
                    intent.putExtra("star", isStar);
                    intent.putExtra("productName", name.getText().toString());
                    intent.putExtra("index", index);
                    intent.putExtra("buy", buy);
                    star.setImageDrawable(getResources().getDrawable(R.drawable.full_star));
                    v.setTag(true);
                    System.out.println("1");
                    setResult(0, intent);
                }
                else {
                    Intent intent = new Intent(v.getContext(), ShopList.class);
                    intent.putExtra("index", index);
                    intent.putExtra("star", isStar);
                    intent.putExtra("productName", name.getText().toString());
                    intent.putExtra("index", index);
                    intent.putExtra("buy", buy);
                    star.setImageDrawable(getResources().getDrawable(R.drawable.empty_star));
                    v.setTag(false);
                    System.out.println("2");
                    setResult(0, intent);

                }
            }
        });

        String[] operations = {"分享信息", "不感兴趣", "查看更多信息", "出错反馈"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.operation, operations);
        operationList.setAdapter(arrayAdapter);

        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(productDetail.this, "已收藏", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ShopList.class);
                intent.putExtra("index", index);
                intent.putExtra("buy", true);
                buy = true;
                intent.putExtra("productName", name.getText().toString());
                intent.putExtra("star", isStar);
                setResult(0, intent);
            }
        });
    }
}
