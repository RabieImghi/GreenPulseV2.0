package Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateValidator {
    public static boolean isThisDateValid(List<LocalDate> existingDates, LocalDate startDate, LocalDate endDate){
        for(LocalDate dateTest = startDate; !dateTest.isAfter(endDate); dateTest=dateTest.plusDays(1)){
            if(existingDates.contains(dateTest)){
                return true;
            }
        }
        return false;
    }
    public static List<LocalDate> dateListRange(LocalDate startDate , LocalDate endDate){
        List<LocalDate> dateListRange = new ArrayList<>();
        for(LocalDate dateTest = startDate; !dateTest.isAfter(endDate); dateTest=dateTest.plusDays(1)){
            dateListRange.add(dateTest);

        }
        return dateListRange;
    }

}
