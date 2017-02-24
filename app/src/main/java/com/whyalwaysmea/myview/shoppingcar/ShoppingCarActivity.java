package com.whyalwaysmea.myview.shoppingcar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.whyalwaysmea.myview.R;
import com.whyalwaysmea.myview.shoppingcar.adapter.DishesApapter;
import com.whyalwaysmea.myview.shoppingcar.adapter.MenuAdapter;
import com.whyalwaysmea.myview.shoppingcar.bean.Dishes;
import com.whyalwaysmea.myview.shoppingcar.bean.Menu;
import com.whyalwaysmea.myview.shoppingcar.itemdecoration.StickyDecoration;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCarActivity extends AppCompatActivity {

    private String[] Sichuan_Cuisine = {"棒棒鸡", "泡椒凤爪", "灯影牛肉","口水鸡","酸辣土豆丝","香辣虾","尖椒炒牛肉","四川火锅","麻辣香水鱼",
            "板栗烧鸡","酸辣海蜇头","辣子鸡","香辣小龙虾","椒麻浸鲈鱼","麻辣鳝鱼","麻辣牛柳","香辣虾"};

    private String[] Cantonese_Cuisine = {"大良双皮奶","陈村粉","大良蹦砂","白切鸡","深井烧鹅","烤乳猪","梅菜扣肉",
            "蜜汁叉烧","东江盐焗鸡","白灼虾","广州文昌鸡","煲仔饭","广式烧填鸭","隆江猪脚饭","豉汁蒸排骨","鱼头豆腐汤","菠萝咕噜肉","香煎芙蓉蛋"};

    private String[] Hunan_cuisine = {"剁椒鱼头","辣椒炒肉","湘西外婆菜","吉首酸肉","牛肉粉","郴州鱼粉","东安鸡","金鱼戏莲","永州血鸭","腊味合蒸","姊妹团子","宁乡口味蛇","岳阳姜辣蛇"};

    private String[] Shandong_cuisine = {"一品豆腐","葱烧海参","三丝鱼翅","白扒四宝","糖醋黄河鲤鱼","九转大肠","油爆双脆","扒原壳鲍鱼","油焖大虾","醋椒鱼","糟熘鱼片","温炝鳜鱼片"};

    private String[] menus = {"川菜","粤菜", "湘菜", "鲁菜"};
    private List<Menu> menuList;

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
        List<Dishes> hunanDishes = new ArrayList<>();
        for (int i = 0; i < Hunan_cuisine.length; i++) {
            hunanDishes.add(new Dishes(Hunan_cuisine[i], i));
        }
        List<Dishes> ShandongDishes = new ArrayList<>();
        for (int i = 0; i < Shandong_cuisine.length; i++) {
            ShandongDishes.add(new Dishes(Shandong_cuisine[i], i));
        }


        List<Dishes> allDishes = new ArrayList<>();
        allDishes.addAll(sichuanDishes);
        allDishes.addAll(yueDishes);
        allDishes.addAll(hunanDishes);
        allDishes.addAll(ShandongDishes);
        DishesApapter dishesApapter = new DishesApapter(allDishes);
        dishesRecyclerView.addItemDecoration(new StickyDecoration(this));
        dishesRecyclerView.setAdapter(dishesApapter);

        // 菜系
        menuList = new ArrayList<>();
        for (int i = 0; i < menus.length; i++) {
            menuList.add(new Menu(menus[i], 0));
        }

        RecyclerView menuRecyclerView = (RecyclerView) findViewById(R.id.menu_list);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuRecyclerView.setAdapter(new MenuAdapter(menuList));

    }
}
