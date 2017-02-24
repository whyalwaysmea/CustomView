package com.whyalwaysmea.myview.shoppingcar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whyalwaysmea.myview.R;
import com.whyalwaysmea.myview.shoppingcar.bean.Menu;

import java.util.List;

/**
 * Created by HelloWorld on 2017/2/24.
 */

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Menu> mMenus;

    public MenuAdapter(List<Menu> menus) {
        this.mMenus = menus;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MenuViewHolder menuViewHolder = (MenuViewHolder) holder;
        Menu menu = mMenus.get(position);
        menuViewHolder.name.setText(menu.getName());
    }

    @Override
    public int getItemCount() {
        return mMenus.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public MenuViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.menu_name);

        }
    }
}
