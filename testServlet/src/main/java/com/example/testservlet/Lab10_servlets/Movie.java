package com.example.testservlet.Lab10_servlets;

public class Movie {
    public String Title;
    public String Director;
    public String Genre;
    public int Duration;
    public float Rating;

    public Movie(String title, String director, String genre, int duration, float rating) {
        Title = title;
        Director = director;
        Genre = genre;
        Duration = duration;
        Rating = rating;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }
}
