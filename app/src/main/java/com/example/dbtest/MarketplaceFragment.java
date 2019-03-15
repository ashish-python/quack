package com.example.dbtest;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MarketplaceFragment extends Fragment {

    ListView marketplaceListView;
    String[] items;
    String[] description;
    String[] price;
    String[] id;
    LayoutInflater inflater;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_marketplace,container,false);
        this.inflater = inflater;

        Resources res = getResources();

        marketplaceListView = (ListView) view.findViewById(R.id.marketplaceListView);

        items = res.getStringArray(R.array.marketplace_items);
        description = res.getStringArray(R.array.marketplace_description);
        price = res.getStringArray(R.array.marketplace_prices);
        id = res.getStringArray(R.array.marketplace_id);



        ItemAdapter itemAdapter = new ItemAdapter(this,items,description,price,id);
        marketplaceListView.setAdapter(itemAdapter);

        marketplaceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent showListingDetails = new Intent(getContext(),MarketplaceListing.class);
                showListingDetails.putExtra("com.example.dbtest.LISTING_ID",id[i]);
                startActivity(showListingDetails);
            }
        });

        return view;



    }
}
