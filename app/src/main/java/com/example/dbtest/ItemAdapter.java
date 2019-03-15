package com.example.dbtest;

import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ItemAdapter extends BaseAdapter {

    String[] items;
    String[] description;
    String[] price;
    String[] id;
    MarketplaceFragment marketplaceFragment;


    public ItemAdapter(MarketplaceFragment marketplaceFragment, String[] items, String[] description, String[] price, String[] id){
        this.marketplaceFragment = marketplaceFragment;
        this.items = items;
        this.description = description;
        this.price = price;
        this.id = id;
    }
    @Override
    public int getCount() {
        return items.length ;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = marketplaceFragment.getLayoutInflater().inflate(R.layout.marketplace_listview_detail,null);

        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
        TextView priceTextView = (TextView) view.findViewById(R.id.priceTextView);
        TextView idTextView = (TextView) view.findViewById(R.id.idTextView);

        nameTextView.setText(items[i]);
        descriptionTextView.setText(description[i]);
        priceTextView.setText(price[i]);
        idTextView.setText(id[i]);



        return view;
    }
}
