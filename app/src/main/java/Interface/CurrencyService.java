package Interface;

import Model.Currency;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chuks on 10/20/2017.
 */

public interface CurrencyService {

    @GET("data/price")
    Call<Currency> getCurrencyInfo(@Query("fsym") String fsym, @Query("tsyms") String tsyms);
}
