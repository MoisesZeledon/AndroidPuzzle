package com.example.usuario.pantallauno;

/**
 * Created by Usuario on 20/07/2015.
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.view.View;


public class pantalla2activity extends Activity implements OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);

        View acerca = findViewById(R.id.acerka);
        acerca.setOnClickListener(this);

        View ayuda = findViewById(R.id.button);
        ayuda.setOnClickListener(this);
}

    public void onClick(View vista) {

        if (vista.getId() == findViewById(R.id.acerka).getId()) {


        Intent i = new Intent (this,acercadeactivity.class);
        startActivity(i);}


        if (vista.getId() == findViewById(R.id.button).getId()) {


        Intent h = new Intent (this,ayudactivity.class);
        startActivity(h);}

    }
}
