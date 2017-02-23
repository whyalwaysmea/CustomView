package com.whyalwaysmea.myview.shoppingcar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whyalwaysmea.myview.R;
import com.whyalwaysmea.myview.shoppingcar.bean.Dishes;

import java.util.List;

/**
 * Created by HelloWorld on 2017/2/23.
 */

public class DishesApapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Dishes> mDishesList;

    public DishesApapter(List<Dishes> dishesList) {
        this.mDishesList = dishesList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dishes, parent, false);
        return new DishesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DishesViewHolder dishesViewHolder = (DishesViewHolder) holder;
        Dishes dishes = mDishesList.get(position);
        dishesViewHolder.name.setText(dishes.getName());
        dishesViewHolder.price.setText("" + dishes.getPrice());
    }

    @Override
    public int getItemCount() {
        return mDishesList.size();
    }

    class DishesViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView price;

        public DishesViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_dishes_name);
            price = (TextView) itemView.findViewById(R.id.item_dishes_price);
        }
    }
}
