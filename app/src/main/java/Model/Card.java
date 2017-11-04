package Model;

/**
 * Created by chuks on 10/26/2017.
 */

public class Card {

    String cyrptoValue;
    String currencyValue;

    public Card(String cyrptoValue, String currencyValue) {
        this.cyrptoValue = cyrptoValue;
        this.currencyValue = currencyValue;
    }

    public String getCyrptoValue() {
        return cyrptoValue;
    }

    public String getCurrencyValue() {
        return currencyValue;
    }
}
