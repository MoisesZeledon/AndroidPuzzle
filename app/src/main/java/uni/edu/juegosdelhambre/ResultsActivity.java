package uni.edu.juegosdelhambre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int mScore = extras.getInt("score");

            TextView scoreTextView = (TextView) findViewById(R.id.tvScoreResult);
            scoreTextView.setText(Integer.toString(mScore));
        }
    }

    @OnClick(R.id.btnReplay) public void Replay() {
        Intent intent = new Intent(this, WordSearchActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btnReturnMenu) public void Return() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
