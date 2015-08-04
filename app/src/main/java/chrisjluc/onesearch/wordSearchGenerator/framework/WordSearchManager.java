package chrisjluc.onesearch.wordSearchGenerator.framework;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Random;

import chrisjluc.onesearch.wordSearchGenerator.generators.WordSearchGenerator;
import chrisjluc.onesearch.wordSearchGenerator.models.FillType;
import uni.edu.juegosdelhambre.config.GameConfig;

/**
 * @author Jeans K. Real, 8/3/15.
 */
public class WordSearchManager {

    public final static int EASY_MIN_WORDLENGTH = 3;
    public final static int EASY_MAX_WORDLENGTH = 3;
    public final static int EASY_MIN_DIMENSION_OFFSET = 0;
    public final static int EASY_MAX_DIMENSION_OFFSET = 0;

    public final static int MEDIUM_MIN_WORDLENGTH = 4;
    public final static int MEDIUM_MAX_WORDLENGTH = 5;
    public final static int MEDIUM_MIN_DIMENSION_OFFSET = 1;
    public final static int MEDIUM_MAX_DIMENSION_OFFSET = 1;

    public final static int HARD_MIN_WORDLENGTH = 6;
    public final static int HARD_MAX_WORDLENGTH = 7;
    public final static int HARD_MIN_DIMENSION_OFFSET = 3;
    public final static int HARD_MAX_DIMENSION_OFFSET = 3;

    private final static int SIZE = 2;
    private static WordSearchManager mInstance;

    /**
     * Dimension offset is the size of the word search relative to the chosen word length
     */
    private int mMinDimensionOffset;
    private int mMaxDimensionOffset;
    private Random mRandom;
    private WordSearchGenerator[] mWordSearchArray;
    private WordProvider mWordProvider;

    private WordSearchManager() {
        mRandom = new Random();
        mWordSearchArray = new WordSearchGenerator[SIZE];
    }

    // Singleton
    public static WordSearchManager getInstance() {
        if (mInstance == null) {
            mInstance = new WordSearchManager();
        }
        return mInstance;
    }

    /**
     * Kill a Word Search Instance.
     */
    public static void nullify() {
        mInstance = null;
    }

    public void buildWordSearches() {
        new BuildWordSearchArrayTask().execute();
    }

    public WordSearchGenerator getWordSearch(final int i) {
        if (i < 0) {
            return buildWordSearch();
        }

        WordSearchGenerator ret = mWordSearchArray[i % SIZE];
        new BuildWordSearchTask(i % SIZE).execute();
        return ret;
    }

    private WordSearchGenerator buildWordSearch() {
        String word = null;
        while (word == null) {
            word = mWordProvider.getWord();
        }
        int dimen = word.length() + mMinDimensionOffset;
        int offsetDifference = mMaxDimensionOffset - mMinDimensionOffset;
        if (offsetDifference > 0) {
            dimen += mRandom.nextInt(offsetDifference + 1);
        }

        WordSearchGenerator gen = new WordSearchGenerator(dimen, dimen, word, FillType.CharactersOfTheWord);
        gen.build();
        return gen;
    }

    public void Initialize(String gameDifficulty, Context context) {

        switch (gameDifficulty) {
            case GameConfig.Easy:
                mWordProvider = new WordProvider(context, EASY_MIN_WORDLENGTH, EASY_MAX_WORDLENGTH);
                mMinDimensionOffset = EASY_MIN_DIMENSION_OFFSET;
                mMaxDimensionOffset = EASY_MAX_DIMENSION_OFFSET;
            break;
            case GameConfig.Medium:
                mWordProvider = new WordProvider(context, MEDIUM_MIN_WORDLENGTH, MEDIUM_MAX_WORDLENGTH);
                mMinDimensionOffset = MEDIUM_MIN_DIMENSION_OFFSET;
                mMaxDimensionOffset = MEDIUM_MAX_DIMENSION_OFFSET;
            break;
            case GameConfig.Hard:
                mWordProvider = new WordProvider(context, HARD_MIN_WORDLENGTH, HARD_MAX_WORDLENGTH);
                mMinDimensionOffset = HARD_MIN_DIMENSION_OFFSET;
                mMaxDimensionOffset = HARD_MAX_DIMENSION_OFFSET;
            break;
        }
    }

    private class BuildWordSearchArrayTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < SIZE; i++) {
                mWordSearchArray[i] = buildWordSearch();
            }
            return null;
        }
    }

    private class BuildWordSearchTask extends AsyncTask<Void, Void, Void> {
        private int i;

        public BuildWordSearchTask(int i) {
            this.i = i;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordSearchArray[i] = buildWordSearch();
            return null;
        }
    }

}
