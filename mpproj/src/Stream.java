import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
// import java.util.ArrayList;
import java.util.Comparator;
// import java.util.stream.Collectors;
import java.util.Date;

public class Stream {
    public static void main(String[] args) {
        ArrayList <Integer> jh = new ArrayList<>();
        jh.add(78);
        jh.add(98);
        jh.add(68);
        jh.add(38);
        ArrayList <Integer> copy = jh
        .stream()
        .filter(Integer -> Integer==90)
        .sorted(Comparator.comparing(Integer -> Integer.toString()))
        .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(copy);
        System.out.println(copy.size());

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Date j = new Date();
        // LocalDate local = j.toInstant() 
        //           .atZone(ZoneId.systemDefault()) 
        //           .toLocalDate(); 
        
  
        
        // LocalDate localDate1 = LocalDate.parse(dateone, formatter);
        // LocalDate localDate2 = LocalDate.parse(datetwo, formatter);

        // return receipts.stream()
        //     .filter(e -> !LocalDate.parse(e.date(), formatter).isBefore(localDate1)
        //             && !LocalDate.parse(e.date(), formatter).isAfter(localDate2))
        //     .sorted()
        //     .collect(Collectors.toList());

        
        
        
    }
    
    
}
