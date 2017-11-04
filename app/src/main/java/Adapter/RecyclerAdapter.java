package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andela.chuks.cryptocompare.R;

import java.util.List;

import Model.Card;

/**
 * Created by chuks on 10/20/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.CardListViewHolder>{

    private List<Card> cardList;
    private Context mContext;

    public RecyclerAdapter(List<Card> cardList, Context mContext) {
        this.cardList = cardList;
        this.mContext = mContext;
    }

    @Override
    public CardListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new CardListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardListViewHolder holder, int position) {

        Card card = cardList.get(position);

        holder.cryptoValue.setText(card.getCyrptoValue());
        holder.currencyValue.setText(card.getCurrencyValue());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class CardListViewHolder extends RecyclerView.ViewHolder {
        private TextView cryptoValue;
        private TextView currencyValue;

        public CardListViewHolder(View itemView) {
            super(itemView);

            cryptoValue = (TextView) itemView.findViewById(R.id.card_cypto);
            currencyValue = (TextView) itemView.findViewById(R.id.card_currency);
        }
    }
}
