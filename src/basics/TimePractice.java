package basics;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.Chronology;

public class TimePractice {
    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalTime lessonStart = LocalTime.of(9,50, 0);
        System.out.println(lessonStart);

        //neni casovy interval, proste vezme obecny casovy format a prevede na HH:MM(:SS)
        LocalTime from = LocalTime.from(lessonStart);
//        System.out.println(from);


        LocalTime constants = LocalTime.NOON;
        System.out.println(constants);

        //kolik uplynulo casu od zacatku hodiny?
        System.out.println("Od zacatku hodiny...:");
        System.out.println(time.toSecondOfDay() - lessonStart.toSecondOfDay());
        System.out.println(time.minusSeconds(lessonStart.toSecondOfDay()));
        System.out.println(Duration.between(from, time));

        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.println(LocalDate.EPOCH);
        System.out.println(LocalDate.MAX);
        System.out.println(LocalDate.MIN);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(LocalDateTime.of(2025,11,1,2,50));

    }
}
