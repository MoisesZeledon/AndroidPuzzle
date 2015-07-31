package com.example.usuario.pantallauno;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;


public class PantallaUno  extends Activity implements OnClickListener {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_uno);

        //Inicializamos variables
        View entrar = findViewById(R.id.entrar);
        View salir = findViewById(R.id.btsalir);
        entrar.setOnClickListener(this);
        salir.setOnClickListener(this);



    }

    public void onClick(View vista) {

        if (vista.getId() == findViewById(R.id.entrar).getId()) {

            //codgo pra pasar a la siguiente ventana
            Intent j = new Intent(this, pantalla2activity.class);
            startActivity(j);
        }


        if (vista.getId() == findViewById(R.id.btsalir).getId()) {

            //codigo para cerrar
            finish();


        }

    }

}
