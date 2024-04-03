import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
// import java.util.ArrayList;
import java.util.Comparator;
// import java.util.stream.Collectors;
import java.util.Date;
import java.util.HashMap;

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

        Map<Integer,Integer> map = new HashMap<>();
        map.put(8760,1);
        map.put(8769,34);
        map.put(8762,87);
        map.put(8765,9);

        // List<Integer> employeeById = new ArrayList<>(audioLikes.values());
        // Collections.sort(employeeById);
        // SortedSet<Integer> values = new TreeSet<>(audioLikes.values());
        // System.out.println(audioLikes);
        
        Map.Entry<Integer,Integer>[] arrmap =map.entrySet().toArray(new Map.Entry[map.size()]);
        for (int i = 0; i < arrmap.length-1; i++) {
            for (int j = i+1; j < arrmap.length; j++) {
                if(arrmap[j].getValue()>arrmap[i].getValue()){
                    Map.Entry<Integer,Integer> tmp = arrmap[i];
                    arrmap[i] = arrmap[j];
                    arrmap[j] =tmp;
                }
            }
        }
        for (int i = 0; i < arrmap.length-1; i++) {
            for (int j = i+1; j < arrmap.length; j++) {
               System.out.println(arrmap[i].getKey());
            }
        }

        System.out.println(arrmap);
        
    }
    
    
}
