package sudonym.anggertugas;

/**
 * Created by sudonym on 23/03/17.
 */

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static sudonym.anggertugas.R.string.nama;

public interface RegisterAPI {

    @GET("/ShowUsers")
    Call<Value> view();

    @FormUrlEncoded
    @POST("/AddUsers")
    Call<Value> daftar(@Field("nama") String nama,
                       @Field("alamat") String alamat);
}
