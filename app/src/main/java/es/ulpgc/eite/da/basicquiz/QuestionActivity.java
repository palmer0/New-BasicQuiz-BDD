package es.ulpgc.eite.da.basicquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    public static final String TAG = "Quiz.QuestionActivity";

    public final static String KEY_INDEX = "KEY_INDEX";
    //public final static String KEY_PRESSED = "KEY_PRESSED";
    public final static String KEY_RESULT = "KEY_RESULT";
    public final static String KEY_ENABLED = "KEY_ENABLED";
    public static final int CHEAT_REQUEST = 1;
    public static final int STATS_REQUEST = 2;

    private Button falseButton, trueButton, cheatButton, nextButton;
    private TextView questionField, resultField;

    private String[] questionsArray;
    private int questionIndex = 0;
    private int[] answersArray;
    private boolean nextButtonEnabled;
    //private boolean trueButtonPressed;

    private String resultText;

    private int correctAnswers = 0;
    private int totalQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setTitle(R.string.question_screen_title);

        Log.d(TAG, "onCreate");

        initLayoutData();
        linkLayoutComponents();

        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt(KEY_INDEX);
            resultText = savedInstanceState.getString(KEY_RESULT);
            //trueButtonPressed = savedInstanceState.getBoolean(KEY_PRESSED);
            nextButtonEnabled = savedInstanceState.getBoolean(KEY_ENABLED);
        }

        updateLayoutContent();
        initLayoutButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState");

        outState.putInt(KEY_INDEX, questionIndex);
        outState.putString(KEY_RESULT, resultText);
        //outState.putBoolean(KEY_PRESSED, trueButtonPressed);
        outState.putBoolean(KEY_ENABLED, nextButtonEnabled);
    }

    private void initLayoutButtons() {

        trueButton.setOnClickListener(v -> onTrueButtonClicked());
        falseButton.setOnClickListener(v -> onFalseButtonClicked());
        nextButton.setOnClickListener(v -> onNextButtonClicked());
        cheatButton.setOnClickListener(v -> onCheatButtonClicked());
    }


    private void initLayoutData() {
        questionsArray = getResources().getStringArray(R.array.questions_array);
        answersArray = getResources().getIntArray(R.array.answers_array);
    }

    private void linkLayoutComponents() {
        falseButton = findViewById(R.id.falseButton);
        trueButton = findViewById(R.id.trueButton);
        cheatButton = findViewById(R.id.cheatButton);
        nextButton = findViewById(R.id.nextButton);

        questionField = findViewById(R.id.questionField);
        resultField = findViewById(R.id.resultField);
    }

    /*
    private void updateLayoutContent() {
        questionField.setText(questionsArray[questionIndex]);

        if (trueButtonPressed) {
            if (answersArray[questionIndex] == 1) {
                resultField.setText(R.string.correct_text);
            } else {
                resultField.setText(R.string.incorrect_text);
            }

        } else {

            if (!nextButtonEnabled) {
                resultField.setText(R.string.empty_text);
            } else {
                if (answersArray[questionIndex] == 0) {
                    resultField.setText(R.string.correct_text);
                } else {
                    resultField.setText(R.string.incorrect_text);
                }
            }
        }

        nextButton.setEnabled(nextButtonEnabled);
        cheatButton.setEnabled(!nextButtonEnabled);
        falseButton.setEnabled(!nextButtonEnabled);
        trueButton.setEnabled(!nextButtonEnabled);
    }


    private void onTrueButtonClicked() {

        if (answersArray[questionIndex] == 1) {
            resultField.setText(R.string.correct_text);
        } else {
            resultField.setText(R.string.incorrect_text);
        }

        nextButtonEnabled = true;
        trueButtonPressed = true;
        updateLayoutContent();
    }

    private void onFalseButtonClicked() {

        if (answersArray[questionIndex] == 0) {
            resultField.setText(R.string.correct_text);
        } else {
            resultField.setText(R.string.incorrect_text);
        }

        nextButtonEnabled = true;
        trueButtonPressed = false;
        updateLayoutContent();
    }
    */

    private void updateLayoutContent() {
        questionField.setText(questionsArray[questionIndex]);

        if (!nextButtonEnabled) {
            resultText = getString(R.string.empty_text);
        }

        resultField.setText(resultText);

        nextButton.setEnabled(nextButtonEnabled);
        cheatButton.setEnabled(!nextButtonEnabled);
        falseButton.setEnabled(!nextButtonEnabled);
        trueButton.setEnabled(!nextButtonEnabled);
    }

//    private void onTrueButtonClicked() {
//
//        if (answersArray[questionIndex] == 1) {
//            resultText =  getString(R.string.correct_text);
//        } else {
//            resultText = getString(R.string.incorrect_text);
//        }
//
//        nextButtonEnabled = true;
//        updateLayoutContent();
//    }
//
//    private void onFalseButtonClicked() {
//
//        if (answersArray[questionIndex] == 0) {
//            resultText = getString(R.string.correct_text);
//        } else {
//            resultText = getString(R.string.incorrect_text);
//        }
//
//        nextButtonEnabled = true;
//        updateLayoutContent();
//    }


    private void onTrueButtonClicked() {
        if (answersArray[questionIndex] == 1) {
            resultText = getString(R.string.correct_text);
            correctAnswers++;
        } else {
            resultText = getString(R.string.incorrect_text);
        }

        totalQuestions++;
        nextButtonEnabled = true;
        updateLayoutContent();
    }

    private void onFalseButtonClicked() {
        if (answersArray[questionIndex] == 0) {
            resultText = getString(R.string.correct_text);
            correctAnswers++;
        } else {
            resultText = getString(R.string.incorrect_text);
        }

        totalQuestions++;
        nextButtonEnabled = true;
        updateLayoutContent();
    }

    @SuppressWarnings("ALL")
    private void onCheatButtonClicked() {

        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra(CheatActivity.EXTRA_ANSWER, answersArray[questionIndex]);
        startActivityForResult(intent, CHEAT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        Log.d(TAG, "onActivityResult");

        /*
        if (requestCode == STATS_REQUEST && resultCode == RESULT_OK) {
            boolean shouldExit =
                intent.getBooleanExtra(StatsActivity.EXTRA_EXIT, false);

            // Finalizar pantalla "Question" cuando pantalla "Stats" lo indique
            if (shouldExit) {
                finish();
            }
        }
        */

        if (requestCode == STATS_REQUEST && resultCode == RESULT_OK && intent != null) {
            if (intent.getBooleanExtra(StatsActivity.EXTRA_EXIT, false)) {
                finish(); // Finalizar app

            } else if (intent.getBooleanExtra(StatsActivity.EXTRA_RESET, false)) {
                resetQuiz(); // Reiniciar Quiz

            } else if (intent.getBooleanExtra(StatsActivity.EXTRA_BACK, false)) {
                nextButtonEnabled = false;
                nextButton.setEnabled(nextButtonEnabled);

            }
        }

        if (requestCode == CHEAT_REQUEST && resultCode == RESULT_OK && intent != null) {

            boolean answerCheated = intent.getBooleanExtra(
                CheatActivity.EXTRA_CHEATED, false
            );

            //Log.d(TAG, "answerCheated: " + answerCheated);

            /*
            Si eliminamos "nextButtonEnabled = true",
            nextButton quedará deshabilitado, y el usuario quedará atascado
            sin poder avanzar después de ver la respuesta correcta.
            */
            if (answerCheated) {
                nextButtonEnabled = true;
                onNextButtonClicked();
            }

        }

    }


    private void resetQuiz() {
        questionIndex = 0;
        correctAnswers = 0;
        totalQuestions = 0;

        nextButtonEnabled = false;

        updateLayoutContent();
    }

//    private void onNextButtonClicked() {
//        Log.d(TAG, "onNextButtonClicked");
//
//        nextButtonEnabled = false;
//        questionIndex++;
//
//        checkQuizCompletion();
//
//        if (questionIndex < questionsArray.length) {
//            //trueButtonPressed = false;
//            updateLayoutContent();
//        }
//
//    }
//
//    private void checkQuizCompletion() {
//
//        if (questionIndex == questionsArray.length) {
//            questionIndex = 0;
//        }
//
//    }

    private void onNextButtonClicked() {
        nextButtonEnabled = false;
        questionIndex++;

        if (questionIndex < questionsArray.length) {
            updateLayoutContent();
        } else {
            openStatsScreen();
        }
    }

    @SuppressWarnings("ALL")
    private void openStatsScreen() {
        Intent intent = new Intent(this, StatsActivity.class);
        intent.putExtra(StatsActivity.EXTRA_QUESTIONS, totalQuestions);
        intent.putExtra(StatsActivity.EXTRA_ANSWERS, correctAnswers);
        startActivityForResult(intent, STATS_REQUEST); // Código de solicitud
    }


    /*
    private void openStatsScreen() {
        Intent intent = new Intent(this, StatsActivity.class);
        intent.putExtra(StatsActivity.EXTRA_TOTAL_QUESTIONS, totalQuestions);
        intent.putExtra(StatsActivity.EXTRA_CORRECT_ANSWERS, correctAnswers);
        startActivity(intent);
        finish();
    }
    */

}
