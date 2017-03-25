package sudonym.anggertugas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.value;
import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://floating-garden-85536.herokuapp.com/";
    private ProgressDialog progress;

    @BindView(R.id.editTextNama) EditText editTextNama;
    @BindView(R.id.editTextAlamat) EditText editTextAlamat;

    @OnClick(R.id.buttonDaftar) void daftar() {
        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        //mengambil data dari edittext
        final String nama = editTextNama.getText().toString();
        final String alamat = editTextAlamat.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.daftar(nama, alamat);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String message = "cek data " + nama + " " + alamat;
                progress.dismiss();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(MainActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.buttonLihat) void lihat() {
        startActivity(new Intent(MainActivity.this, ViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}