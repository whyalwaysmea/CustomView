package com.whyalwaysmea.myview.shoppingcar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.whyalwaysmea.myview.R;
import com.whyalwaysmea.myview.shoppingcar.adapter.DishesApapter;
import com.whyalwaysmea.myview.shoppingcar.bean.Dishes;
import com.whyalwaysmea.myview.shoppingcar.itemdecoration.StickyDecoration;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCarActivity extends AppCompatActivity {

    private String[] Sichuan_Cuisine = {"棒棒鸡", "泡椒凤爪", "灯影牛肉","口水鸡","酸辣土豆丝","香辣虾","尖椒炒牛肉","四川火锅","麻辣香水鱼",
            "板栗烧鸡","酸辣海蜇头","辣子鸡","香辣小龙虾","椒麻浸鲈鱼","麻辣鳝鱼","麻辣牛柳","香辣虾"};

    private String[] Cantonese_Cuisine = {"大良双皮奶","陈村粉","大良蹦砂","白切鸡","深井烧鹅","烤乳猪","梅菜扣肉",
            "蜜汁叉烧","东江盐焗鸡","白灼虾","广州文昌鸡","煲仔饭","广式烧填鸭","隆江猪脚饭","豉汁蒸排骨","鱼头豆腐汤","菠萝咕噜肉","香煎芙蓉蛋"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);

        RecyclerView dishesRecyclerView = (RecyclerView) findViewById(R.id.dishes_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        dishesRecyclerView.setLayoutManager(linearLayoutManager);

        List<Dishes> sichuanDishes = new ArrayList<>();
        for (int i = 0; i < Sichuan_Cuisine.length; i++) {
            sichuanDishes.add(new Dishes(Sichuan_Cuisine[i], i));
        }
        List<Dishes> yueDishes = new ArrayList<>();
        for (int i = 0; i < Cantonese_Cuisine.length; i++) {
            yueDishes.add(new Dishes(Cantonese_Cuisine[i], i));
        }

        List<Dishes> allDishes = new ArrayList<>();
        allDishes.addAll(sichuanDishes);
        allDishes.addAll(yueDishes);
        DishesApapter dishesApapter = new DishesApapter(allDishes);
        dishesRecyclerView.addItemDecoration(new StickyDecoration(this));
        dishesRecyclerView.setAdapter(dishesApapter);
    }
}
