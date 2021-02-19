package com.example.android.mumenproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

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

public class ConnexionActivity extends AppCompatActivity {

    private static final  String TAG = "ConnexionActivity";

    LinearLayout suivant;
    Button creation;
    String URL = "http://192.168.43.194:8080";

    EditText editText1, editText2;
    String password, name, res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);


        suivant = findViewById(R.id.connexion);
        creation = findViewById(R.id.compt);

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                editText1 =(EditText) findViewById(R.id.password);
                password = editText1.getText().toString();
                String hiddenPass = "";
                for(int i = 0; i < password.length(); i++){
                    hiddenPass += "*";
                }
                editText1.setText(hiddenPass);

                editText2 =(EditText) findViewById(R.id.name);
                name = editText2.getText().toString();


                if(name.length() > 0 & password.length() > 0) {

                    MediaType MEDIA_TYPE = MediaType.parse("application/json");

                    JSONObject user = new JSONObject();
                    try {
                        user.put("username", name);
                        user.put("password", password);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }

                    RequestBody requestBody = RequestBody.create(MEDIA_TYPE, user.toString());

                    OkHttpClient client = new OkHttpClient.Builder()
                            .build();
                    Request request = new Request.Builder()
                            .url(URL +"/api/auth/signin")
                            .post(requestBody)
                            .build();
                    try {
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                Log.i("Failure request search", e.getMessage());
                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                                res = response.body().string();
                                int code = response.code();


                                Log.i(TAG, res);
                                Log.i(TAG, String.valueOf(code));

                                if (code > 199 & code < 300) {
                                    Intent pro = new Intent(view.getContext(), ProfileActivity.class);

                                    pro.putExtra("name", name);

                                    startActivity(pro);
                                }
                                else {
                                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout), res, Snackbar.LENGTH_LONG);
                                    mySnackbar.show();
                                }
                            }
                        });


                    }
                    catch (Exception e) {
                        Log.v(TAG,"Erreur reseau"+e);

                    }
                }
                else {
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout), "il n'y a rien écrit dans nom ou mot de passe", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                }
            }

        });
        creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creation = new Intent(view.getContext(), CreatProActivity.class);
                startActivity(creation);
            }
        });
    }
}