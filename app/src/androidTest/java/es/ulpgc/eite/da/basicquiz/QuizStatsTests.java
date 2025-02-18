package es.ulpgc.eite.da.basicquiz;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

// Project: Basic Quiz
// Created by Luis Hernandez 
// Copyright (c) 2025 EITE (ULPGC)
@RunWith(AndroidJUnit4.class)
public class QuizStatsTests {

    @Rule
    public ActivityScenarioRule<QuestionActivity> activityRule =
        new ActivityScenarioRule<>(QuestionActivity.class);

    @Test
    public void testReturnToQuestionWithoutReset() {
        // Responder todas las preguntas del Quiz
        // Asumiendo que hay 5 preguntas
        for (int i = 0; i < 5; i++) {
            onView(ViewMatchers.withId(R.id.trueButton)).perform(ViewActions.click());
            onView(ViewMatchers.withId(R.id.nextButton)).perform(ViewActions.click());
        }

        // Abrir pantalla Stats
        onView(ViewMatchers.withId(R.id.cheatButton)).perform(ViewActions.click());

        // Pulsar botón Back en Stats
        Espresso.pressBack();

        // Verificar que se muestra la pantalla Question en la última pregunta
        // Asumiendo que la última pregunta es "Pregunta 5"
        onView(ViewMatchers.withId(R.id.questionField))
            .check(ViewAssertions.matches(ViewMatchers.withText("Pregunta 5"))); 
    }

    @Test
    public void testRestartQuizFromStats() {
        // Responder todas las preguntas del Quiz
        for (int i = 0; i < 5; i++) { // Asumiendo que hay 5 preguntas
            onView(ViewMatchers.withId(R.id.trueButton)).perform(ViewActions.click());
            onView(ViewMatchers.withId(R.id.nextButton)).perform(ViewActions.click());
        }

        // Abrir pantalla Stats
        onView(ViewMatchers.withId(R.id.cheatButton)).perform(ViewActions.click());

        // Pulsar botón Reiniciar
        onView(ViewMatchers.withId(R.id.restartButton)).perform(ViewActions.click());

        // Verificar que se muestra la pantalla Question con la primera pregunta
        // Asumiendo que la primera pregunta es "Pregunta 1"
        onView(ViewMatchers.withId(R.id.questionField))
            .check(ViewAssertions.matches(ViewMatchers.withText("Pregunta 1"))); 
    }

    @Test
    public void testCloseAppFromStats() {
        // Responder todas las preguntas del Quiz
        for (int i = 0; i < 5; i++) { // Asumiendo que hay 5 preguntas
            onView(ViewMatchers.withId(R.id.trueButton)).perform(ViewActions.click());
            onView(ViewMatchers.withId(R.id.nextButton)).perform(ViewActions.click());
        }

        // Abrir pantalla Stats
        onView(ViewMatchers.withId(R.id.cheatButton)).perform(ViewActions.click());

        // Pulsar botón Finalizar
        onView(ViewMatchers.withId(R.id.exitButton)).perform(ViewActions.click());

        // Verificar que la aplicación se cierra
        // Espresso no tiene una forma directa de verificar que la aplicación se cierra, 
        // pero podemos verificar que la actividad actual no es QuestionActivity
        // Esto es un poco hacky, pero es una forma de verificar que la aplicación se cerró
        try {
            onView(ViewMatchers.withId(R.id.questionField))
                .check(ViewAssertions.doesNotExist());
        } catch (Exception e) {
            // La aplicación se cerró, por lo que la vista no existe
        }
    }

    
}