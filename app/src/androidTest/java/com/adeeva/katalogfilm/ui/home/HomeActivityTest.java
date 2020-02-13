package com.adeeva.katalogfilm.ui.home;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.adeeva.katalogfilm.R;
import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.utils.DataDummy;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class HomeActivityTest {

    private ArrayList<FilmEntity> dummyMovie = DataDummy.generateDummyMovie();
    private ArrayList<FilmEntity> dummyTv = DataDummy.generateDummyTv();

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void loadMovie(){
        delay2Seconds();
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    @Test
    public void loadDetailMovie(){
        delay2Seconds();
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        delay2Seconds();
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie.get(0).getTitle())));

        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
        onView(withId(R.id.text_date)).check(matches(withText(String.format("Release %s", dummyMovie.get(0).getReleaseDate()))));

        onView(withId(R.id.text_description)).check(matches(isDisplayed()));
        onView(withId(R.id.text_description)).check(matches(withText(dummyMovie.get(0).getDescription())));

    }

    @Test
    public void loadTv(){
        onView(withText("Tv Shows")).perform(click());
        delay2Seconds();
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition(dummyTv.size()));
    }

    private void delay2Seconds(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}