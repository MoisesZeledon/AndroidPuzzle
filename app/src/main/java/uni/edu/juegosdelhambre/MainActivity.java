package uni.edu.juegosdelhambre;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Dialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnEnter) void enter() {
        Intent intent = new Intent(this, PuzzleActivity.class);
        this.startActivity(intent);
    }

    @OnClick(R.id.btnExit) void exit() {
        finish();
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
        if (id == R.id.action_help) {
            dialog = new Dialog(this);

            dialog.setTitle("Ayuda");
            dialog.setContentView(R.layout.dialog_help);

            dialog.show();

        } else if (id == R.id.action_about) {
            dialog = new Dialog(this);

            dialog.setTitle("Acerca de");
            dialog.setContentView(R.layout.dialog_about);

            dialog.show();
        } else if (id == R.id.action_exit) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
