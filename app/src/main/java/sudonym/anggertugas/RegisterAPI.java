package sudonym.anggertugas;

/**
 * Created by sudonym on 23/03/17.
 */

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static sudonym.anggertugas.R.string.nama;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/AddUsers")
    Call<Value> daftar(@Field("nama") String nama,
                       @Field("alamat") String alamat);

    @GET("/ShowUsers")
    Call<Value> view();

    @Multipart
    @POST("/UpdateUsers/{id}")
    Call<Value> ubah(@Path("id") String id,
                     @Field("nama") String nama,
                     @Field("alamat") String alamat);

}
