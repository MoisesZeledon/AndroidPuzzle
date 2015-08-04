package uni.edu.juegosdelhambre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import chrisjluc.onesearch.wordSearchGenerator.framework.WordSearchManager;
import uni.edu.juegosdelhambre.config.GameConfig;

public class HandicapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handicap);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WordSearchManager.nullify();
    }

    @OnClick(R.id.btnEasy) protected void Easy() {
        WordSearchCreator(GameConfig.Easy);
    }

    @OnClick(R.id.btnMedium) protected void Medium() {
        WordSearchCreator(GameConfig.Medium);
    }

    @OnClick(R.id.btnHard) protected void Hard() {
        WordSearchCreator(GameConfig.Hard);
    }

    private void WordSearchCreator(String gameDifficulty) {
        // Create a Unique Instance
        WordSearchManager wordSearchManager = WordSearchManager.getInstance();
        wordSearchManager.Initialize(gameDifficulty, this);

        wordSearchManager.buildWordSearches();

        Intent intent = new Intent(getApplicationContext(), WordSearchActivity.class);
        startActivity(intent);
    }

}
