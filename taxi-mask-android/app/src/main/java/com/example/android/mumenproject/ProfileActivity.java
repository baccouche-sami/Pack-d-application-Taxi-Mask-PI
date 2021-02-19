package com.example.android.mumenproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends MainActivity {

    private String name;
    Button menu;
    Button deco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        menu = findViewById(R.id.Com);
        deco = findViewById(R.id.deco);

        Intent connexion = getIntent();
        if(connexion != null){
            if(connexion.hasExtra("name")){
                name = connexion.getStringExtra("name");
            }
        }

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.putExtra("name", name);

                startActivity(intent);
            }
        });
        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

