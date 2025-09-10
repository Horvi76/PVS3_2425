package basics;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TimeParse {
    public static void main(String[] args) {
        List<String> dateStrings = Arrays.asList(
                "2025-03-31",
                "03/31/2025",
                "31-03-2025",
                "2025/03/31",
                "31 Mar 2025",
                "2025/03/31 14:30",
                "31 Mar 2025 12:30"
        );

        String dateString = "31 Mar 2025 12:30";
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//        Date date = parser.parse(dateString);
    }
}
