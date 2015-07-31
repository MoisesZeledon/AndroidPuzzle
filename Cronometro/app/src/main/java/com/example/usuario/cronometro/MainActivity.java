package com.example.usuario.cronometro;

import android.app.Activity;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;


public class MainActivity extends Activity{
    Chronometer crono;
    Button ini, dete;
    ImageView imagen;
    //long elapsedTime = 0;
    //String currentTime = "";
    Boolean activity = false;

    //Declaracion de las imagenes
    int[]fotoId={

            R.drawable.imagen1,
            R.drawable.imagen2,
            R.drawable.imagen3,
            R.drawable.imagen4


    };

    int i = 0;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ini = (Button) (findViewById(R.id.iniciar));

       // button.setOnClickListener(this);
        imagen = (ImageView)(findViewById(R.id.imalinux));
        crono = (Chronometer)findViewById(R.id.cronometro);
        ini = (Button) findViewById(R.id.iniciar);
        dete = (Button) findViewById(R.id.detener);

        ini.setEnabled(true);
        dete.setEnabled(false);
        total = fotoId.length;


        // Metodo del boton Iniciar
        ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = v.getId();
                if(id == R.id.iniciar) {
                    i++;

                    if (i == total)
                        i = 0;
                }
                imagen.setImageResource(fotoId[i]);
                ini.setEnabled(false);
                dete.setEnabled(true);

                if (!activity) {
                    crono.setBase(SystemClock.elapsedRealtime());
                    crono.start();
                }
            }
        });


        // Metodo del boton detener
        dete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ini.setEnabled(true);
                dete.setEnabled(false);
                crono.stop();
                activity = true;


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
