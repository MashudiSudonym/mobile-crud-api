package sudonym.anggertugas;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import static android.os.Build.ID;
import static sudonym.anggertugas.R.string.alamat;
import static sudonym.anggertugas.R.string.id;
import static sudonym.anggertugas.R.string.nama;

public class UpdateActivity extends AppCompatActivity {

    public static final String URL = "https://floating-garden-85536.herokuapp.com/";
    private ProgressDialog progress;

    @BindView(R.id.editTextId) EditText editTextId;
    @BindView(R.id.editTextNama) EditText editTextNama;
    @BindView(R.id.editTextAlamat) EditText editTextAlamat;

    @OnClick(R.id.buttonUbah) void ubah() {
        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        //mengambil data dari edittext

        Integer id = Integer.valueOf(editTextId.getText().toString());
        final String nama = editTextNama.getText().toString();
        final String alamat = editTextAlamat.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.ubah(id, nama, alamat);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String message = "cek data " + nama + alamat;
                progress.dismiss();
                Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(UpdateActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ubah Data");

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String alamat = intent.getStringExtra("alamat");

        editTextId.setText(id);
        editTextNama.setText(nama);
        editTextAlamat.setText(alamat);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_delete:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Peringatan");
                alertDialogBuilder
                        .setMessage("Apakah Anda yakin ingin mengapus data ini?")
                        .setCancelable(false)
                        .setPositiveButton("Hapus",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int Id) {

                                Integer id = Integer.valueOf(editTextId.getText().toString());
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                RegisterAPI api = retrofit.create(RegisterAPI.class);
                                Call<Value> call = api.hapus(id);
                                call.enqueue(new Callback<Value>() {
                                    @Override
                                    public void onResponse(Call<Value> call, Response<Value> response) {
                                        String message = "DATA TERHAPUS";
                                        Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Value> call, Throwable t) {
                                        t.printStackTrace();
                                        Toast.makeText(UpdateActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }
}
