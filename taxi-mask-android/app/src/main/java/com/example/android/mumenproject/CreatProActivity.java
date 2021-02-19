package com.example.android.mumenproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreatProActivity extends AppCompatActivity {

    private static final  String TAG = "CreatProActivity";

    LinearLayout suivant;
    String URL = "http://192.168.43.194:8080";

    EditText editText1, editText2, editText3, editText4;
    String  name, email, password, checkPassword, res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creatpro);


        suivant = findViewById(R.id.connexion);

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                editText1 =(EditText) findViewById(R.id.name);
                name = editText1.getText().toString();

                editText2 = (EditText) findViewById(R.id.email);
                email = editText2.getText().toString();

                editText3 =(EditText) findViewById(R.id.password);
                password = editText3.getText().toString();

                editText4 =(EditText) findViewById(R.id.checkPassword);
                checkPassword = editText4.getText().toString();


                if(name.length() > 5 & password.length() > 5 & password.equals(checkPassword)) {

                    MediaType MEDIA_TYPE = MediaType.parse("application/json");

                    JSONArray role = new JSONArray();
                    role.put("user");


                    JSONObject user = new JSONObject();
                    try {
                        user.put("username", name);
                        user.put("email", email);
                        user.put("password", password);
                        user.put("role", role);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }

                    RequestBody requestBody = RequestBody.create(MEDIA_TYPE, user.toString());

                    OkHttpClient client = new OkHttpClient.Builder()
                            .build();
                    Request request = new Request.Builder()
                            .url(URL +"/api/auth/signup")
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
                else if (name.length() < 6){
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout), "le nom doit comporter plus de 6 caractères", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                }
                else if (password.length() < 6){
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout), "le mot de passe doit comporter plus de 6 caractères", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                }
                else {
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout), "la vérification de mot de passe n'est bonne", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                }
            }

        });
    }
}