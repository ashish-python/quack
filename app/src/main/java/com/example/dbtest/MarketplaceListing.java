package com.example.dbtest;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MarketplaceListing extends AppCompatActivity {

    private String[] imageUrls = new String[]{"https://s7d2.scene7.com/is/image/Hay/test_adobesupport?$image_src=Hay/AAC_22_defaultfront&$b2c_1600x1200_jpeg$",
    "https://s7d2.scene7.com/is/image/Hay/test_adobesupport?$image_src=Hay/AAC_22_100127954_Front&$b2c_1600x1200_jpeg$"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace_listing);

        Intent in = getIntent();
        String listingID = in.getStringExtra("com.example.dbtest.LISTING_ID");

        TextView usernameEditText = (TextView) findViewById(R.id.usernameTextView);
        usernameEditText.setText(listingID);

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this,imageUrls);
        viewPager.setAdapter(adapter);
    }
}
