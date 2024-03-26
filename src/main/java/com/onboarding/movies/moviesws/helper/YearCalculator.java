package com.onboarding.movies.moviesws.helper;

import com.onboarding.movies.moviesws.model.MovieEntity;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class YearCalculator {
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static void calculateYear(MovieEntity movieEntity) {
        LocalDate releaseDate = convertToLocalDateViaInstant(movieEntity.getReleaseDate());

        movieEntity.setYear(releaseDate.getYear());
    }
}
