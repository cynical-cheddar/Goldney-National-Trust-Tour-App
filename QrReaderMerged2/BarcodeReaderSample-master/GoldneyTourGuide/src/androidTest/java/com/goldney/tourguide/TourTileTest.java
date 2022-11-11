package com.goldney.tourguide;

import org.junit.Test;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TourTileTest {

    @Test
    public void setValuesFromJSON() {
    }

    @Test
    public void getTitle() {
        TourTile tt = new TourTile();
        tt.setValuesFromJSON("0", getApplicationContext(), "getTiles.json");
        assertThat(tt.getTitle(), is("Goldney House test Tile"));

    }

    @Test
    public void getDescription() {
        TourTile tt = new TourTile();
        tt.setValuesFromJSON("0", getApplicationContext(), "getTiles.json");
        assertThat(tt.getDescription(), is("The current house was built in 1724, is a listed building and occupies a hilltop position overlooking the city of Bristol and Brandon Hill. The landscape garden is used for weddings and receptions."));
    }

    @Test
    public void setTitle() {
        TourTile tt = new TourTile();
        tt.setTitle("abc1");
        assertThat(tt.getTitle(), is("abc1"));
    }

    @Test
    public void setDescription() {
    }

    @Test
    public void setImages() {
    }

    @Test
    public void setAudio() {
    }
}