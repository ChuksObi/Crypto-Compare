package com.andela.chuks.cryptocompare;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class Conversion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mSpinner;

    private TextView mText;
    private EditText mEditText;

    private ImageView mImageView;

    private String fsym;

    private CurrencyService currencyService;

    private double mAmount = 0.0;
    private double mValue = 0.0;
    private double mSpace =  0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);


        //XML to Java Link
        mImageView = (ImageView) findViewById(R.id.imageView);
        mSpinner = (Spinner) findViewById(R.id.spinner_convert);
        mEditText = (EditText) findViewById(R.id.edit_currency);
        mText = (TextView) findViewById(R.id.text_set_convert);

        //Spinner Click Listener
        mSpinner.setOnItemSelectedListener(this);

        //Spinner List Categories
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

        //Creating a Spinner adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerCategories);

        //Setting Spinner layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting Drop down icon to White
        mSpinner.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

        // Attaching data adapter to spinner
        mSpinner.setAdapter(dataAdapter);

        //Getting the String Value either Bitcoin or Ethereum
        if (getIntent() != null) {
           fsym = getIntent().getStringExtra("fsym");
            if (!(fsym.matches("BTC"))){
                mImageView.setImageResource(R.drawable.final_ethereum);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Setting Text to white on the Drop
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

        //Getting the index value of the item Selected
        Long item = parent.getItemIdAtPosition(position);
        final int itemPosition = Integer.parseInt(item.toString());

        //TextWatcher for Immediate Onscreen Conversion
        TextWatcher watcher = new TextWatcher() {
            public void afterTextChanged(Editable s) {


                try{
                    mValue = Double.parseDouble(mEditText.getText().toString().trim());
                    currencyService = ApiClient.getClient().create(CurrencyService.class);
                    String array[] = {"NGN", "USD", "EUR", "CHF", "GBP", "CNY", "JPY", "INR", "SGD", "TWD", "AUD", "RUB", "ZAR", "MXN",
                            "ILS", "MYR", "NZD", "SEK", "NOK", "BRL", "TRY"};

                    Call<Currency> call = currencyService.getCurrencyInfo(fsym, array[itemPosition]);



                    call.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {


                switch (itemPosition) {
                    case 0:
                        mSpace = response.body().getNGN();
                        mAmount = mValue / mSpace;

                        break;
                    case 1:
                        mSpace = response.body().getUSD();
                        mAmount = mValue / mSpace;

                        break;
                    case 2:
                        mSpace = response.body().getEUR();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("€ " + nf.format(EUR));
                        break;
                    case 3:
                        mSpace = response.body().getCHF();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("Fr. " + nf.format(CHF));
                        break;
                    case 4:
                        mSpace = response.body().getGBP();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("£ " + nf.format(GBP));
                        break;
                    case 5:
                        mSpace = response.body().getCNY();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("¥ " + nf.format(CNY));
                        break;
                    case 6:
                        mSpace = response.body().getJPY();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("¥ " + nf.format(JPY));
                        break;
                    case 7:
                        mSpace = response.body().getINR();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("₹ " + nf.format(INR));
                        break;
                    case 8:
                        mSpace = response.body().getSGD();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("S$ " + nf.format(SGD));
                        break;
                    case 9:
                        mSpace = response.body().getTWD();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("NT$ " + nf.format(TWD));
                        break;
                    case 10:
                        mSpace = response.body().getAUD();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("A$ " + nf.format(AWD));
                        break;
                    case 11:
                        mSpace = response.body().getRUB();
                        mAmount = mValue / mSpace;
                        break;
                    case 12:
                        mSpace = response.body().getZAR();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("R " + nf.format(ZAR));
                        break;
                    case 13:
                        mSpace = response.body().getMXN();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("$ " + nf.format(MXN));
                        break;
                    case 14:
                        mSpace = response.body().getILS();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("₪ " + nf.format(ILS));
                        break;
                    case 15:
                        mSpace = response.body().getMYR();
                        mAmount = mSpace / mValue;
                        //mTextSet.setText("RM " + nf.format(MYR));
                        break;
                    case 16:
                        mSpace = response.body().getNZD();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("NZ$ " + nf.format(NZD));
                        break;
                    case 17:
                        mSpace = response.body().getSEK();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("kr " + nf.format(SEK));
                        break;
                    case 18:
                        mSpace = response.body().getNOK();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("kr " + nf.format(NOK));
                        break;
                    case 19:
                        mSpace = response.body().getBRL();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("R$ " + nf.format(BRL));
                        break;
                    case 20:
                        mSpace = response.body().getTRY();
                        mAmount = mValue / mSpace;
                        //mTextSet.setText("₺ " + nf.format(TRY));
                        break;
                    default:
                        //nothing
                        break;

                }
                NumberFormat numberFormatter = NumberFormat.getNumberInstance();
                mText.setText(numberFormatter.format(mAmount));
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(Conversion.this, "Uh oh, Internet Issues: Please try again later! ", Toast.LENGTH_SHORT).show();

            }
        });
                }
                catch(NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        };
        mEditText.addTextChangedListener(watcher);
    }

            @Override
    public void onNothingSelected(AdapterView<?> parent) {
                //When Nothing is Selected
    }

    public void createCard(View view){

        Intent intent = new Intent(Conversion.this, CardActivity.class);
        intent.putExtra("fsym", fsym);

        String space = Double.toString(mSpace);
        intent.putExtra("cypto",space);

        String amount = Double.toString(mAmount);
        intent.putExtra("value", amount);

        startActivity(intent);

    }
    }
