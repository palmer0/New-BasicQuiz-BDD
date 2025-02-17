package es.ulpgc.eite.da.basicquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StatsActivity extends AppCompatActivity {

    public static final String EXTRA_TOTAL_QUESTIONS = "EXTRA_TOTAL_QUESTIONS";
    public static final String EXTRA_CORRECT_ANSWERS = "EXTRA_CORRECT_ANSWERS";
    public static final String EXTRA_EXIT = "EXTRA_EXIT";
    public static final String EXTRA_RESET_QUIZ = "EXTRA_RESET_QUIZ";

    private TextView statsText;
    private Button restartButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        setTitle(R.string.stats_screen_title);

        statsText = findViewById(R.id.statsText);
        restartButton = findViewById(R.id.restartButton);
        exitButton = findViewById(R.id.exitButton);

        // Obtener valores pasados desde pantalla "Question"
        int totalQuestions = getIntent().getIntExtra(EXTRA_TOTAL_QUESTIONS, 0);
        int correctAnswers = getIntent().getIntExtra(EXTRA_CORRECT_ANSWERS, 0);

        // Mostrar resultados
        statsText.setText(getString(R.string.stats_message, correctAnswers, totalQuestions));

        // Reiniciar Quiz
        restartButton.setOnClickListener(v -> onRestartButtonClicked());
        // Finalizar app
        exitButton.setOnClickListener(v -> onExitButtonClicked());
        //exitButton.setOnClickListener(v -> finishAffinity());
    }

    private void onExitButtonClicked() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_EXIT, true);
        setResult(RESULT_OK, resultIntent);
        finish(); // Finalizar pantalla "Stats"
    }

    private void onRestartButtonClicked() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_RESET_QUIZ, true);
        setResult(RESULT_OK, resultIntent);
        finish(); // Finalizar pantalla "Stats"
    }

    /*
    private void onRestartButtonClicked() {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    */
}