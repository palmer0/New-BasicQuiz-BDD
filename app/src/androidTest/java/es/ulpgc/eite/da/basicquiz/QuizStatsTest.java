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
    public ActivityScenarioRule<QuestionActivity> mActivityScenarioRule =
        new ActivityScenarioRule<>(QuestionActivity.class);

    @Test
    public void test() {

        // Estamos en pantalla "Question" y respondemos a primera pregunta
        ViewInteraction materialButton = onView(
            allOf(withId(R.id.trueButton), withText("True"), isEnabled()));
        materialButton.perform(click());

        // Estamos en pantalla "Question" y pasamos a segunda pregunta
        ViewInteraction materialButton2 = onView(
            allOf(withId(R.id.nextButton), withText("Next"), isEnabled()));
        materialButton2.perform(click());

        // Estamos en pantalla "Question" y respondemos a segunda pregunta
        ViewInteraction materialButton3 = onView(
            allOf(withId(R.id.trueButton), withText("True"), isEnabled()));
        materialButton3.perform(click());

        // Estamos en pantalla "Question" y pasamos a tercera pregunta
        ViewInteraction materialButton4 = onView(
            allOf(withId(R.id.nextButton), withText("Next"), isEnabled()));
        materialButton4.perform(click());

        // Estamos en pantalla "Question" y verificamos estado boton "Stats"
        ViewInteraction button2 = onView(
            allOf(withId(R.id.statsButton), withText("Stats"), isDisplayed()));
        button2.check(matches(not(isEnabled())));

        // Estamos en pantalla "Question" y pasamos a pantalla "Cheat"
        ViewInteraction materialButton5 = onView(
            allOf(withId(R.id.cheatButton), withText("Cheat"), isEnabled()));
        materialButton5.perform(click());

        // Estamos en pantalla "Cheat" y vemos respuesta a tercera pregunta
        ViewInteraction materialButton6 = onView(
            allOf(withId(R.id.yesButton), withText("Yes"), isEnabled()));
        materialButton6.perform(click());


        // Estamos en pantalla "Cheat" y volvemos a pantalla "Question"
        pressBack();


        // Estamos en pantalla "Question" y verificamos estado boton "True"
        ViewInteraction button = onView(
            allOf(withId(R.id.trueButton), withText("True"), isDisplayed()));
        button.check(matches(not(isEnabled())));



        // Estamos en pantalla "Question" y verificamos tercera pregunta
        ViewInteraction textView = onView(
            allOf(withId(R.id.questionField), isDisplayed()));
        textView.check(matches(withText("Question #3: True")));

        // Estamos en pantalla "Question" y verificamos resultado de tercera pregunta
        ViewInteraction textView2 = onView(
            allOf(withId(R.id.resultField), isDisplayed()));
        textView2.check(matches(withText("???")));


        // Estamos en pantalla "Question" y pasamos a pantalla "Stats"
        ViewInteraction materialButton7 = onView(
            allOf(withId(R.id.statsButton), withText("Stats"), isEnabled()));
        materialButton7.perform(click());


        // Estamos en pantalla "Stats" y verificamos datos estadisticos
        ViewInteraction textView3 = onView(
            allOf(withId(R.id.totalQuestionsField), isDisplayed()));
        textView3.check(matches(withText("Total Questions: 2")));
        ViewInteraction textView4 = onView(
            allOf(withId(R.id.correctAnswersField), isDisplayed()));
        textView4.check(matches(withText("Correct Answers: 1")));


        // Estamos en pantalla "Stats" y pasamos a pantalla "Question"
        ViewInteraction materialButton8 = onView(
            allOf(withId(R.id.restartButton), withText("Restart Quiz"), isDisplayed()));
        materialButton8.perform(click());

        // Estamos en pantalla "Question" y verificamos reinicio del Quiz
        ViewInteraction textView5 = onView(
            allOf(withId(R.id.questionField), isDisplayed()));
        textView5.check(matches(withText("Question #1: True")));
        ViewInteraction textView6 = onView(
            allOf(withId(R.id.resultField), withText("???"), isDisplayed()));
        textView6.check(matches(withText("???")));
        ViewInteraction button4 = onView(
            allOf(withId(R.id.trueButton), withText("True"), isDisplayed()));
        button4.check(matches(isEnabled()));
        ViewInteraction button5 = onView(
            allOf(withId(R.id.nextButton), withText("Next"), isDisplayed()));
        button5.check(matches(not(isEnabled())));
        ViewInteraction button6 = onView(
            allOf(withId(R.id.cheatButton), withText("Cheat"), isDisplayed()));
        button6.check(matches(isEnabled()));
        ViewInteraction button7 = onView(
            allOf(withId(R.id.statsButton), withText("Stats"), isDisplayed()));
        button7.check(matches(not(isEnabled())));


    }

}
