package com.andela.chuks.cryptocompare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.RecyclerAdapter;
import Model.Card;

public class CardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private List<Card> card;
    String value;
    String fsym;
    String crypto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        card = new ArrayList<>();

        if (getIntent() != null){
            fsym = getIntent().getExtras().getString("fsym");
            value = getIntent().getExtras().getString("value");
            crypto = getIntent().getExtras().getString("cypto");

        }

        for (int i = 0; i <= 1; i++){
            Card card = new Card(value,crypto);
            this.card.add(card);
        }

        adapter = new RecyclerAdapter(card, this);
        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);
    }
}
