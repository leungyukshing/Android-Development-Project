package com.example.lab1;

import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ShopList extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatBtn;
    Boolean isShopList = false;
    ListView cart;
    LinearLayout linearLayout;
    List<Map<String, Object>> cartList = new ArrayList<>();
    SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_list);

        floatBtn = (FloatingActionButton)findViewById(R.id.btn);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        cart = (ListView)findViewById(R.id.cart);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new myListViewAdapter(getData()));

        // Add Animation
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        //defaultItemAnimator.setAddDuration(100);
        defaultItemAnimator.setRemoveDuration(10);
        recyclerView.setItemAnimator(defaultItemAnimator);

        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShopList = !isShopList;
                if (isShopList) {
                    recyclerView.setVisibility(View.INVISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    floatBtn.setImageDrawable(getResources().getDrawable(R.drawable.mainpage));
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.INVISIBLE);
                    floatBtn.setImageDrawable(getResources().getDrawable(R.drawable.collect));
                }
            }
        });

        // Cart
        /*
        Map<String, Object> temp = new LinkedHashMap<>();
        temp.put("label", "饮");
        temp.put("name", "奶茶");
        temp.put("type", "饮料");
        temp.put("nutrition", "蛋白质");
        temp.put("color", "#C48D30");
        cartList.add(temp);
        */
        simpleAdapter = new SimpleAdapter(this, cartList, R.layout.shop_list_item, new String[] {"label", "name"}, new int[] {R.id.label, R.id.name});
        cart.setAdapter(simpleAdapter);

        cart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), productDetail.class);
                Bundle data = new Bundle();
                HashMap<String, Object> m = (HashMap<String, Object>) cartList.get(position);
                data.putString("name", (String)m.get("name"));
                data.putString("label", (String)m.get("label"));
                data.putString("type", (String)m.get("type"));
                data.putString("nutrition", (String)m.get("nutrition"));
                data.putString("color", (String)m.get("color"));
                data.putInt("index", position);
                data.putBoolean("star", (boolean)m.get("star"));

                intent.putExtras(data);
                ((Activity)view.getContext()).startActivityForResult(intent, 0);
            }
        });

        cart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ShopList.this);
                dialogBuilder.setTitle("删除")
                        .setMessage("确定删除" + cartList.get(position).get("name") + "?").setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cartList.remove(position);
                        simpleAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                return true;
            }
        });
    }

    private ArrayList<Product> getData() {
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("大豆", "粮", "粮食", "蛋白质", "#BB4C3B"));
        list.add(new Product("十字花科蔬菜", "蔬", "蔬菜", "维生素", "#C48D30"));
        list.add(new Product("牛奶", "饮", "饮品", "钙", "#4469B0"));
        list.add(new Product("海鱼", "肉", "肉食", "蛋白质", "#20A17B"));
        list.add(new Product("菌菇类", "蔬", "蔬菜", "微量元素", "#BB4C3B"));
        list.add(new Product("番茄", "蔬", "蔬菜", "番茄红素", "#4469B0"));
        list.add(new Product("胡萝卜", "蔬", "蔬菜", "胡萝卜素", "#20A17B"));
        list.add(new Product("荞麦", "粮","粮食", "膳食纤维", "#BB4C3B"));
        list.add(new Product("鸡蛋", "杂", "杂", "几乎所有营养物质", "#C48D30"));
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if (requestCode == 0) {
            if (resultCode == 0) {
                boolean isStared = data.getBooleanExtra("star", false);
                int index = data.getIntExtra("index", 0);
                String name = data.getStringExtra("productName");
                ((myListViewAdapter)recyclerView.getAdapter()).setItem(name, isStared);
                for (int i = 0; i < cartList.size(); i++) {
                    if (cartList.get(i).get("name").equals(name)) {
                        cartList.get(i).put("star", isStared);
                        System.out.println("Modify: " + cartList.get(i).get("star"));
                    }
                }

                if (data.getBooleanExtra("buy", false)) {
                    Map<String, Object> temp = new LinkedHashMap<>();
                    Product product = ((myListViewAdapter)recyclerView.getAdapter()).getItem(data.getIntExtra("index", 0));
                    temp.put("label", product.getLabel());
                    temp.put("name", product.getName());
                    temp.put("type", product.getType());
                    temp.put("nutrition", product.getNutrition());
                    temp.put("color", product.getColor());
                    temp.put("star", product.getIsStar());

                    boolean existed = false;

                    for (int i = 0; i < cartList.size(); i++) {
                        System.out.println("hello: " + cartList.get(i).get("name") + ", ss: "+ data.getStringExtra("productName"));
                        if (cartList.get(i).get("name").equals(data.getStringExtra("productName"))) {
                            existed = true;
                            break;
                        }
                    }
                    if (!existed) {
                        cartList.add(temp);
                        simpleAdapter.notifyDataSetChanged();
                    }
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}