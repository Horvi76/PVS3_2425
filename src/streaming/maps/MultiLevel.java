package streaming.maps;

import exams.ScoreMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MultiLevel {

    static class TestResult{
        String studentName, subject;
        double score;
        int timeSpent;

        public TestResult(String studentName, String subject, double score, int timeSpent) {
            this.studentName = studentName;
            this.subject = subject;
            this.score = score;
            this.timeSpent = timeSpent;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getSubject() {
            return subject;
        }

        public double getScore() {
            return score;
        }

        public int getTimeSpent() {
            return timeSpent;
        }
    }

    public static void main(String[] args) throws IOException {
        List<TestResult> results = Files.lines(Paths.get("data\\scores.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .map(splitLine -> new TestResult(
                        splitLine[0],
                        splitLine[1],
                        Double.parseDouble(splitLine[2]),
                        Integer.parseInt(splitLine[3])
                )).toList();

        //Zadani: mapa: student - [predmet] - {skore}
        Map<String, Map<String, List<Double>>> grouped = results.stream()
                .collect(Collectors.groupingBy(
                        TestResult::getStudentName,//prvni mapa, prvni klic
                                Collectors.groupingBy(
                                        TestResult::getSubject,
                                        Collectors.mapping(TestResult::getScore, Collectors.toList())
                                )
                ));

        grouped.forEach((k, v) -> {
            System.out.println(k);
            v.forEach((subjects, scores) -> {
                System.out.println("\t"+subjects);
                scores.forEach(score -> System.out.println("\t\t" + score));
            });
        });

        //pouze prumery
        Map<String, Map<String, Double>> averages = results.stream()
                .collect(Collectors.groupingBy(
                        TestResult::getStudentName,//prvni mapa, prvni klic
                        Collectors.groupingBy(
                                TestResult::getSubject,
                                Collectors.averagingDouble(TestResult::getScore))
                        )
                );
        averages.forEach((student, subjectScores) -> {
            System.out.println(student);
            subjectScores.forEach((subject, score) -> System.out.println("\t" + subject + ": " + score));
        });
    }

}
