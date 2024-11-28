package mg.itu.matelas.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

public final class Utilitaire {
    public static float generateNumberRand(double min,double max){
        return (float)(min + (max - min) * (float) Math.random());
    }

    public static LocalDate generateDateRand(LocalDate dateMin,LocalDate dateMax){
        long startEpochDay = dateMin.toEpochDay(); // Nombre de jours depuis Epoch
        long endEpochDay = dateMax.toEpochDay();

        return genereteDateOuvrableRand(startEpochDay,endEpochDay);
    }

    public static Long generateNumberRandLong(long min,long max){
        return (Long)(min + (max - min) * (long) Math.random());
    }

    private static LocalDate genereteDateOuvrableRand(long startEpochDay,long endEpochDay){
        while(true){
            long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1); // Générer un jour aléatoire
            LocalDate date=LocalDate.ofEpochDay(randomEpochDay);
            if(!isWeekend(date)){
                return date;
            }
        }
    }

    private static LocalDateTime genereteDateTimeOuvrableRand(long startEpochSecond,long endEpochSecond){
        while(true){
            long randomEpochSecond = ThreadLocalRandom.current().nextLong(startEpochSecond, endEpochSecond + 1); // Générer un timestamp aléatoire
            LocalDateTime date=LocalDateTime.ofEpochSecond(randomEpochSecond, 0, ZoneOffset.UTC);
            if(!isWeekend(date.toLocalDate())){
                return date;
            }
        }
    }

    public static LocalDateTime generateDateRand(LocalDateTime dateMin,LocalDateTime dateMax){
        long startEpochSecond = dateMin.toEpochSecond(ZoneOffset.UTC); // Epoch en secondes
        long endEpochSecond = dateMax.toEpochSecond(ZoneOffset.UTC);

        return genereteDateTimeOuvrableRand(startEpochSecond,endEpochSecond);
    }

    public static boolean isWeekend(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek(); // Récupérer le jour de la semaine
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public static String cleanAndFormat(String input) {
        if (input == null) {
            return null; // Gérer les cas où l'entrée est null
        }
        // Remplacer les virgules par des points
        input = input.replace(",", ".");
        // Supprimer tous les caractères sauf les chiffres et les points
        return input.replaceAll("[^0-9.]", "");
    }

    public static LocalDate parseDate(String date){
        return LocalDate.parse(cleanAndFormat(date));
    }

    public static double parseDouble(String value){
        return Double.parseDouble(cleanAndFormat(value));
    }

    public static Long parseLong(String value){
        return Long.parseLong(cleanAndFormat(value));
    }

    public static float parseFloat(String value){
        return Float.parseFloat(cleanAndFormat(value));
    }

    public static float parseInt(String value){
        return Integer.parseInt(cleanAndFormat(value));
    }

    public static double correct(String value){
        return Double.parseDouble(cleanAndFormat(value));
    }
}
