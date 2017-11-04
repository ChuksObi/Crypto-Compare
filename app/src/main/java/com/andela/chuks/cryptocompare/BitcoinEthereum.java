package com.andela.chuks.cryptocompare;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import Interface.CurrencyService;
import Model.Currency;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BitcoinEthereum extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private TextView mTextView;
    private ImageView mImage;
    private TextView mTextSet;

    private String fsym;

    private CurrencyService currencyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin_ethereum);

        spinner = (Spinner) findViewById(R.id.spinner);
        mTextSet = (TextView) findViewById(R.id.textSet);
        mImage = (ImageView) findViewById(R.id.imageView);
        mTextView = (TextView) findViewById(R.id.view);


        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Categories
        List<String> spinnerCategories = new ArrayList<>();
        spinnerCategories.add("Nigerian Naira (NGN)");
        spinnerCategories.add("United States Dollar (USD)");
        spinnerCategories.add("Euro (EUR)");
        spinnerCategories.add("Swiss Franc (CHF)");
        spinnerCategories.add("British Pound (GBP)");
        spinnerCategories.add("Chinese Yuan (CNY)");

        spinnerCategories.add("Japanese Yen (JPY)");
        spinnerCategories.add("Indian Rupee (INR)");
        spinnerCategories.add("Singapore Dollar (SGD)");
        spinnerCategories.add("New Taiwan Dollar (TWD)");
        spinnerCategories.add("Australian Dollar (AUD)");
        spinnerCategories.add("Russian Ruble (RUB)");

        spinnerCategories.add("South African Rand (ZAR)");
        spinnerCategories.add("Mexican Peso (MXN)");
        spinnerCategories.add("Israeli New Shekel (ILS)");
        spinnerCategories.add("Malaysian Ringgit (MYR)");
        spinnerCategories.add("New Zealand Dollar (NZD)");
        spinnerCategories.add("Swedish Krona (SEK)");

        spinnerCategories.add("Norwegian Krone (NOK)");
        spinnerCategories.add("Brazilian Real (BRL)");
        spinnerCategories.add("Turkish Lira (TRY)");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerCategories);

        // Drop down layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting Drop down icon to White
        spinner.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //Getting the String Resource either BitcoinEthereum or Ethereum
        if (getIntent() != null) {
            fsym = getIntent().getStringExtra("fsym");
            if (!(fsym.matches("BTC"))){
                mImage.setImageResource(R.drawable.final_ethereum);
               mTextView.setText(R.string.view_bitcoin);
            }

        }
    }

    public void convertEthereum(View view){
        mTextView = (TextView) findViewById(R.id.view);
        //Intent intent = new Intent(BitcoinEthereum.this, Ethereum.class);
       // startActivity(intent);
    }

    public void convert(View view){
        Intent intent = new Intent(this, Conversion.class);
        intent.putExtra("fsym", fsym);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

        //Setting Text to white on the Drop
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

        //Passing the currency Symbols in an array
        String array[] = { "NGN", "USD", "EUR", "CHF", "GBP", "CNY", "JPY", "INR", "SGD", "TWD", "AUD", "RUB", "ZAR", "MXN",
                        "ILS", "MYR", "NZD", "SEK", "NOK", "BRL", "TRY"};

        //Getting the index value of the item Selected
        Long item = parent.getItemIdAtPosition(position);
        final int itemPosition = Integer.parseInt(item.toString());

        //Got the String from the intent and passed as the first variable string and the second one from the array
        currencyService = ApiClient.getClient().create(CurrencyService.class);
        Call<Currency> call = currencyService.getCurrencyInfo(fsym , array[itemPosition]);

        final NumberFormat nf = NumberFormat.getNumberInstance();

        call.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {

                switch (itemPosition) {
                    case 0:
                        double NGN = response.body().getNGN();
                        mTextSet.setText("₦ " + nf.format(NGN));
                        break;
                    case 1:
                        double USD = response.body().getUSD();
                        mTextSet.setText("$ " + nf.format(USD));
                        break;
                    case 2:
                        double EUR = response.body().getEUR();
                        mTextSet.setText("€ " + nf.format(EUR));
                        break;
                    case 3:
                        double CHF = response.body().getCHF();
                        mTextSet.setText("Fr. " + nf.format(CHF));
                        break;
                    case 4:
                        double GBP = response.body().getGBP();
                        mTextSet.setText("£ " + nf.format(GBP));
                        break;
                    case 5:
                        double CNY = response.body().getCNY();
                        mTextSet.setText("¥ " + nf.format(CNY));
                        break;
                    case 6:
                        double JPY = response.body().getJPY();
                        mTextSet.setText("¥ " + nf.format(JPY));
                        break;
                    case 7:
                        double INR = response.body().getINR();
                        mTextSet.setText("₹ " + nf.format(INR));
                        break;
                    case 8:
                        double SGD = response.body().getSGD();
                        mTextSet.setText("S$ " + nf.format(SGD));
                        break;
                    case 9:
                        double TWD = response.body().getTWD();
                        mTextSet.setText("NT$ " + nf.format(TWD));
                        break;
                    case 10:
                        double AWD = response.body().getAUD();
                        mTextSet.setText("A$ " + nf.format(AWD));
                        break;
                    case 11:
                        double RUB = response.body().getRUB();
                        mTextSet.setText("руб " + nf.format(RUB));
                        break;
                    case 12:
                        double ZAR = response.body().getZAR();
                        mTextSet.setText("R " + nf.format(ZAR));
                        break;
                    case 13:
                        double MXN = response.body().getMXN();
                        mTextSet.setText("$ " + nf.format(MXN));
                        break;
                    case 14:
                        double ILS = response.body().getILS();
                        mTextSet.setText("₪ " + nf.format(ILS));
                        break;
                    case 15:
                        double MYR = response.body().getMYR();
                        mTextSet.setText("RM " + nf.format(MYR));
                        break;
                    case 16:
                        double NZD = response.body().getNZD();
                        mTextSet.setText("NZ$ " + nf.format(NZD));
                        break;
                    case 17:
                        double SEK = response.body().getSEK();
                        mTextSet.setText("kr " + nf.format(SEK));
                        break;
                    case 18:
                        double NOK = response.body().getNOK();
                        mTextSet.setText("kr " + nf.format(NOK));
                        break;
                    case 19:
                        double BRL = response.body().getBRL();
                        mTextSet.setText("R$ " + nf.format(BRL));
                        break;
                    case 20:
                        double TRY = response.body().getTRY();
                        mTextSet.setText("₺ " + nf.format(TRY));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Oh try again later: This really what happened ...lOl:  " + t, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
