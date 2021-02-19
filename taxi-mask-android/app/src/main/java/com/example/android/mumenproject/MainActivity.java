package com.example.android.mumenproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout btn;
    LinearLayout connexion;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         btn = findViewById(R.id.circle);
         connexion = findViewById(R.id.connexion);


         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Commande(view);
             }
         });

         connexion.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Profile(view);
             }
         });
    }

    private void Commande (View view){
        Name();
        if(name == null){
            Intent co = new Intent(view.getContext(), ConnexionActivity.class);
            startActivity(co);
        }
        else {
            Intent commande2 = new Intent(view.getContext(), Commande2Activity.class);
            startActivity(commande2);
        }
    }

    private void Name(){
        Intent connex = getIntent();
        if(connex != null){
            name = connex.getStringExtra("name");
        }
        else {
            name = null;
        }
    }

    private void Profile (View view){
        Name();
        if (name != null){
            Intent pro = new Intent(view.getContext(), ProfileActivity.class);
            pro.putExtra("name", name);
            startActivity(pro);
        }
        else {
            Intent co = new Intent(view.getContext(), ConnexionActivity.class);
            startActivity(co);
        }
    }


}