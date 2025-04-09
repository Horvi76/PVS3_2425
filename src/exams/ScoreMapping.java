package exams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreMapping {

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


    static double getAverageScore(List<TestResult> results, String name){
        return results.stream()
                .filter(test -> test.getStudentName().equals(name))
                .mapToDouble(TestResult::getScore)
                .average()
                .orElse(0);
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

        System.out.println("Loaded: " + results.size());
        long uniqueStudents = results.stream()
                .map(s -> s.studentName)
                .distinct()
                .count();
        System.out.println("Unique students: " + uniqueStudents);

        Map<String, Long> amountPerSubject = results.stream()
                .collect(Collectors.groupingBy(TestResult::getSubject, Collectors.counting()));

        Map<String, Double> avgTimeSpent = results.stream()
                .collect(Collectors.groupingBy(TestResult::getSubject, Collectors.averagingInt(TestResult::getTimeSpent)));

        System.out.println(amountPerSubject);
        System.out.println(avgTimeSpent);

        Map<String, List<String>> studentsCategories = results.stream()
                .map(TestResult::getStudentName)
                .distinct()
                .collect(Collectors.groupingBy(name -> {
                    double avg = getAverageScore(results, name);
                    System.out.println(avg);
                    if (avg > 50) return "Good";
                    if (avg >= 10) return "Bad";
                    return "Really bad";
                }, Collectors.toList()));

        studentsCategories.forEach((cat, names) -> System.out.println(cat + ": " + names.size()));
    }
}
