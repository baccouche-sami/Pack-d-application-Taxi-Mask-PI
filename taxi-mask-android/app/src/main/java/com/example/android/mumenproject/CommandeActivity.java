package com.example.android.mumenproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http2.ErrorCode;

public class CommandeActivity extends AppCompatActivity {
    //Spinner paysSpinner;
    TextView btn;
    EditText prenom, nom, nomEntreprise, pays, adresse, codePostal, ville, mobile, password, cpassword, email;
    String p, cp;
    JSONObject jsonObject;
    OkHttpClient client;
    private static final String TAG = "Response";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);
       // AndroidNetworking.initialize(this);
        prenom = findViewById(R.id.prenom);
        nom = findViewById(R.id.nom);
        nomEntreprise = findViewById(R.id.nomEntreprise);
        pays = findViewById(R.id.pays);
        adresse = findViewById(R.id.adresse);
        codePostal = findViewById(R.id.codePostal);
        ville = findViewById(R.id.ville);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        p = password.getText().toString();
        cp = cpassword.getText().toString();


        btn = findViewById(R.id.suiv);
        client = new OkHttpClient();
        jsonObject = new JSONObject();
        try {
            jsonObject.put("prenom", prenom.getText().toString());
            jsonObject.put("nom", nom.getText().toString());
            jsonObject.put("nomEntreprise", nomEntreprise.getText().toString());
            jsonObject.put("pays", pays.getText().toString());
            jsonObject.put("adresse", adresse.getText().toString());
            jsonObject.put("codePostale", codePostal.getText());
            jsonObject.put("ville", ville.getText().toString());
            jsonObject.put("mobile", mobile.getText());
            jsonObject.put("email", email.getText().toString());
            jsonObject.put("password", password.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p.equals(cp)) {

                    try {
                        postRequest();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    /*try {
                        post("http://192.168.43.194/clients",jsonObject.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

/*
                    client = new OkHttpClient();
                    new OkHttpClient.Builder()
                            .addNetworkInterceptor(new StethoInterceptor())
                            .build();
                    RequestBody body = RequestBody.create(jsonObject.toString(),JSON);

                    Request request = new Request.Builder()
                            .url("http://localhost:8080/clients")
                            .post(body)
                            .build();


                    client.newCall(request).enqueue(new Callback() {
                                                        @Override
                                                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                                            Log.i("test", "Working Perfectly");
                                                            Log.i("test", response.body().toString());
                                                        }

                                                        @Override
                                                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                                            Log.e("test", "Not Working ");
                                                        }
                                                    });



*/

                        /*AndroidNetworking.post("http://localhost:8080/clients")
                                .addJSONObjectBody(jsonObject) // posting json
                                .setTag("test")
                                .setPriority(Priority.MEDIUM)
                                .build()
                                .getAsJSONArray(new JSONArrayRequestListener() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        Log.i("test","Working Perfectly");
                                        Log.i("test",response.toString());
                                    }
                                    @Override
                                    public void onError(ANError error) {
                                        Log.e("test","Not Working ");
                                    }
                                });*/
                        Intent pro = new Intent(view.getContext(), ProfileActivity.class);
                    pro.putExtra("email", email.getText().toString());
                    pro.putExtra("prenom", prenom.getText());
                    pro.putExtra("nom", nom.getText());
                    pro.putExtra("nomEntreprise", nomEntreprise.getText());
                    pro.putExtra("pays", pays.getText());
                    pro.putExtra("codePostal", codePostal.getText());
                    pro.putExtra("ville", ville.getText());
                    pro.putExtra("mobile", mobile.getText());
                    pro.putExtra("password", password.getText());
                    startActivity(pro);

                } else {
                    Toast.makeText(view.getContext(), "Confirmez votre mot de password", Toast.LENGTH_LONG).show();
                }
            }
        });



        /*
        paysSpinner = (Spinner) findViewById(R.id.pays);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.pays_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        paysSpinner.setAdapter(staticAdapter);*/
    }
    public void postRequest() throws IOException{

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "http://localhost:8080/clients";

        OkHttpClient client = new OkHttpClient();



        RequestBody body = RequestBody.create(MEDIA_TYPE, jsonObject.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String mMessage = response.body().string();
                Log.e(TAG, mMessage);
            }
        });
    }
}