import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DateMaxFinder {
    public static void main(String[] args) {
        ArrayList<String> allDate = new ArrayList<>();
        allDate.add("2022/12/12,12:22");
        allDate.add("2021/12/12,12:22");
        LocalDateTime maxDateTime = null;

        for (String dateString : allDate) {
            LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm"));
            if (maxDateTime == null || dateTime.isAfter(maxDateTime)) {
                maxDateTime = dateTime;
            }
        }

        String inputDateTimeString = maxDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm"));
        LocalDateTime inputDateTime = LocalDateTime.parse(inputDateTimeString, DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm"));

        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println(getLongs(inputDateTime, currentDateTime));
    }

    private static ArrayList<Long> getLongs(LocalDateTime inputDateTime, LocalDateTime currentDateTime) {
        LocalDateTime maxRemainingTime = (inputDateTime.isAfter(currentDateTime)) ? inputDateTime : currentDateTime;

        long days = currentDateTime.until(maxRemainingTime, ChronoUnit.DAYS);
        long hours = currentDateTime.until(maxRemainingTime, ChronoUnit.HOURS) % 24;
        long minutes = currentDateTime.until(maxRemainingTime, ChronoUnit.MINUTES) % 60;

        ArrayList<Long> returnValue = new ArrayList<>();
        returnValue.add(days);
        returnValue.add(hours);
        returnValue.add(minutes);
        return returnValue;
    }
}