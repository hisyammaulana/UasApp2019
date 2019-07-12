package id.plugin.uasapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

import id.plugin.uasapp.Api.ApiRequest;
import id.plugin.uasapp.Api.Retroserver;
import id.plugin.uasapp.Model.ResponsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormActivity extends AppCompatActivity {
    EditText editNIK, editNama, editJam;
    Button btnSimpan, btnLihat;
    ProgressDialog pd;

    private Spinner sPilihan;
    String[] pilihan = {"A", "B", "C", "D"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editNIK = findViewById(R.id.txtnik);
        editNama = findViewById(R.id.txtnama);
        editJam = findViewById(R.id.txtjam);
        sPilihan = findViewById(R.id.spinnerpilihan);

        btnSimpan = findViewById(R.id.btnsimpan);
        btnLihat = findViewById(R.id.btnlihat);

        Intent data = getIntent();
        final String iddata = data.getStringExtra("id");
        if(iddata != null) {
            btnSimpan.setVisibility(View.GONE);
            btnLihat.setVisibility(View.GONE);
            editNIK.setText(data.getStringExtra("nik"));
            editNama.setText(data.getStringExtra("nama"));
            editJam.setText(data.getStringExtra("jam"));
            sPilihan.getSelectedItem().toString().trim();

        }


        ArrayAdapter adapter = new ArrayAdapter(FormActivity.this, R.layout.support_simple_spinner_dropdown_item, pilihan);
        sPilihan.setAdapter(adapter);


        pd = new ProgressDialog(this);

        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent godata = new Intent(FormActivity.this, HasilActivity.class);
                startActivity(godata);
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("send data ... ");
                pd.setCancelable(false);
                pd.show();

                String snik = editNIK.getText().toString();
                String snama = editNama.getText().toString();
                String skelas = sPilihan.getSelectedItem().toString().trim();
                String sjam = editJam.getText().toString();
                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

                Call<ResponsModel> sendbio = api.sendBiodata(snik,snama,skelas,sjam);
                sendbio.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getNik();

                        if(kode.equals("1"))
                        {
                            Toast.makeText(FormActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(FormActivity.this, "Data Error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });
    }

    }
