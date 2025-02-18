package es.ulpgc.eite.da.basicquiz;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class QuizStatsTest {

    @Rule
    public ActivityScenarioRule<QuestionActivity> scenarioRule =
        new ActivityScenarioRule<>(QuestionActivity.class);

    @Test
    public void test() {

        /*
        // Obtener lista de preguntas y respuestas desde archivo "strings.xml"
        String[] questionsArray = ApplicationProvider
            .getApplicationContext().getResources()
            .getStringArray(R.array.questions_array);
        String[] answersArray = ApplicationProvider
            .getApplicationContext().getResources()
            .getStringArray(R.array.answers_array);

        // Obtener textos de botones desde archivo "strings.xml"
        String trueButtonLabel = ApplicationProvider
            .getApplicationContext().getResources()
            .getString(R.string.true_button_label);
        String nextButtonLabel = ApplicationProvider
            .getApplicationContext().getResources()
            .getString(R.string.next_button_label);
        String cheatButtonLabel = ApplicationProvider
            .getApplicationContext().getResources()
            .getString(R.string.cheat_button_label);
        String statsButtonLabel = ApplicationProvider
            .getApplicationContext().getResources()
            .getString(R.string.stats_button_label);
        String yesButtonLabel = ApplicationProvider
            .getApplicationContext().getResources()
            .getString(R.string.yes_button_label);
        String restartButtonLabel = ApplicationProvider
            .getApplicationContext().getResources()
            .getString(R.string.restart_button_label);

        // Obtener textos a mostrar desde archivo "strings.xml"
        String emptyText = ApplicationProvider
            .getApplicationContext().getResources()
            .getString(R.string.empty_text);
        String totalQuestionsText = ApplicationProvider
            .getApplicationContext().getResources()
            .getString(R.string.total_questions_text);
        String correctAnswersText = ApplicationProvider
            .getApplicationContext().getResources()
            .getString(R.string.correct_answers_text);

        */

        // Estamos en pantalla "Question" y respondemos a primera pregunta
        ViewInteraction button1 =
            onView(allOf(withId(R.id.trueButton), withText("True"), isEnabled()));
        button1.perform(click());

        // Estamos en pantalla "Question" y pasamos a segunda pregunta
        ViewInteraction button12 =
            onView(allOf(withId(R.id.nextButton), withText("Next"), isEnabled()));
        button12.perform(click());

        // Estamos en pantalla "Question" y respondemos a segunda pregunta
        ViewInteraction button13 =
            onView(allOf(withId(R.id.trueButton), withText("True"), isEnabled()));
        button13.perform(click());

        // Estamos en pantalla "Question" y pasamos a tercera y ultima pregunta
        ViewInteraction button14 =
            onView(allOf(withId(R.id.nextButton), withText("Next"), isEnabled()));
        button14.perform(click());

        // Estamos en pantalla "Question" y verificamos estado boton "Stats"
        ViewInteraction button2 =
            onView(allOf(withId(R.id.statsButton), withText("Stats"), isDisplayed()));
        button2.check(matches(not(isEnabled())));

        // Estamos en pantalla "Question" y pasamos a pantalla "Cheat"
        ViewInteraction button15 =
            onView(allOf(withId(R.id.cheatButton), withText("Cheat"), isEnabled()));
        button15.perform(click());

        // Estamos en pantalla "Cheat" y vemos respuesta a tercera y ultima pregunta
        ViewInteraction button16 =
            onView(allOf(withId(R.id.yesButton), withText("Yes"), isEnabled()));
        button16.perform(click());


        // Estamos en pantalla "Cheat" y volvemos a pantalla "Question"
        pressBack();


        // Estamos en pantalla "Question" y verificamos estado boton "True"
        ViewInteraction button8 =
            onView(allOf(withId(R.id.trueButton), withText("True"), isDisplayed()));
        button8.check(matches(not(isEnabled())));



        // Estamos en pantalla "Question" y verificamos tercera pregunta
        ViewInteraction textView7 =
            onView(allOf(withId(R.id.questionField), isDisplayed()));
        textView7.check(matches(withText("Question #3: True")));

        // Estamos en pantalla "Question" y verificamos resultado de tercera pregunta
        ViewInteraction textView2 =
            onView(allOf(withId(R.id.resultField), isDisplayed()));
        textView2.check(matches(withText("???")));


        // Estamos en pantalla "Question" y pasamos a pantalla "Stats"
        ViewInteraction button17 =
            onView(allOf(withId(R.id.statsButton), withText("Stats"), isEnabled()));
        button17.perform(click());


        // Estamos en pantalla "Stats" y verificamos datos estadisticos
        ViewInteraction textView3 =
            onView(allOf(withId(R.id.totalQuestionsField), isDisplayed()));
        textView3.check(matches(withText("Total Questions: 2")));
        ViewInteraction textView4 =
            onView(allOf(withId(R.id.correctAnswersField), isDisplayed()));
        textView4.check(matches(withText("Correct Answers: 1")));


        // Estamos en pantalla "Stats" y pasamos a pantalla "Question"
        ViewInteraction button18 =
            onView(allOf(withId(R.id.restartButton), withText("Restart Quiz"), isEnabled()));
        button18.perform(click());

        // Estamos en pantalla "Question" y verificamos reinicio del Quiz
        ViewInteraction textView5 =
            onView(allOf(withId(R.id.questionField), isDisplayed()));
        textView5.check(matches(withText("Question #1: True")));
        ViewInteraction textView6 =
            onView(allOf(withId(R.id.resultField), isDisplayed()));
        textView6.check(matches(withText("???")));
        ViewInteraction button4 =
            onView(allOf(withId(R.id.trueButton), withText("True"), isDisplayed()));
        button4.check(matches(isEnabled()));
        ViewInteraction button5 =
            onView(allOf(withId(R.id.nextButton), withText("Next"), isDisplayed()));
        button5.check(matches(not(isEnabled())));
        ViewInteraction button6 =
            onView(allOf(withId(R.id.cheatButton), withText("Cheat"), isDisplayed()));
        button6.check(matches(isEnabled()));
        ViewInteraction button7 =
            onView(allOf(withId(R.id.statsButton), withText("Stats"), isDisplayed()));
        button7.check(matches(not(isEnabled())));

    }

}
