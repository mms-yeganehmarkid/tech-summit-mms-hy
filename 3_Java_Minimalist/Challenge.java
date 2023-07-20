import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
public class Challenge {

    String weightedSort(String text) {

        StringBuilder sb = new StringBuilder();
        int weight = 0;
        Map<String, Long> map = new HashMap<>();
        for (char c: text.toCharArray()) {
            if (!(c == '.' || c == ',' || c == '?' || c == '!')) {
                if (c == ' ') {
                    if(!sb.toString().isEmpty()) {
                        sb.append("|").append(weight);
                        map.put(sb.toString(), (long) weight);
                        sb = new StringBuilder();
                        weight = 0;
                    }
                } else {
                    if (!(c == '-' || c == '\'')) {
                        sb.append(c);
                        weight += c;
                    }
                }
            }
        }
        if(!sb.toString().isEmpty()) {
            sb.append("|").append(weight);
            map.put(sb.toString(), (long) weight);
        }

        String collect = map.keySet().stream().sorted((o1, o2) -> {
            long l1 = map.get(o1);
            long l2 = map.get(o2);
            if(l1 != l2){
                return Long.compare(l2, l1);
            } else {
                return o2.compareTo(o1);
            }
        }).collect(Collectors.joining("\n"));
        return collect;
    }
}
