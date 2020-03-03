package com.adeeva.katalogfilm.ui.home;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.adeeva.katalogfilm.R;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.utils.DataDummy;
import com.adeeva.katalogfilm.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class HomeActivityTest {

    private ArrayList<FilmEntity> dummyMovie = DataDummy.generateDummyMovie();
    private ArrayList<TvEntity> dummyTv = DataDummy.generateDummyTv();

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    @Test
    public void loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie.get(0).getTitle())));

        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
        onView(withId(R.id.text_date)).check(matches(withText(String.format("Release %s", dummyMovie.get(0).getReleaseDate()))));

        onView(withId(R.id.text_description)).check(matches(isDisplayed()));
        onView(withId(R.id.text_description)).check(matches(withText(dummyMovie.get(0).getDescription())));

    }

    @Test
    public void loadFavoriteMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.action_bookmark)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withText("Favorite Movies")).perform(click());
        onView(withId(R.id.rv_favorite_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_favorite_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
        onView(withId(R.id.action_bookmark)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());

        //onView(withText("Favorite Movies")).perform(click());
        //onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()));
        //onView(withId(R.id.rv_favorite)).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

}