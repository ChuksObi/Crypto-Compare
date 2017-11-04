package com.andela.chuks.cryptocompare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void bitcoin(View view) {
        Intent intent = new Intent(this, BitcoinEthereum.class);
        intent.putExtra("fsym", "BTC");
        startActivity(intent);
    }

    public void ethereum(View view) {
        Intent intent = new Intent(this, BitcoinEthereum.class);
        intent.putExtra("fsym", "ETH");
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




}

