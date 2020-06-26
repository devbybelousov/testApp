package enumeration;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum RomanNumber {
    I(1),
    IV(4),
    V(2),
    IX(9),
    X(10);

    private int value;

    RomanNumber(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static List<RomanNumber> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumber e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}
