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

    private static LocalDate genereteDateOuvrableRand(long startEpochDay,long endEpochDay){
        while(true){
            long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1); // Générer un jour aléatoire
            LocalDate date=LocalDate.ofEpochDay(randomEpochDay);
            if(isWeekend(date)){
                return date;
            }
        }
    }

    private static LocalDateTime genereteDateTimeOuvrableRand(long startEpochSecond,long endEpochSecond){
        while(true){
            long randomEpochSecond = ThreadLocalRandom.current().nextLong(startEpochSecond, endEpochSecond + 1); // Générer un timestamp aléatoire
            LocalDateTime date=LocalDateTime.ofEpochSecond(randomEpochSecond, 0, ZoneOffset.UTC);
            if(isWeekend(date.toLocalDate())){
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
}
