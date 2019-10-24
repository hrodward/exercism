import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class ResistorColor {

    Map<String, Integer> colors;

    public ResistorColor() {

        colors = new LinkedHashMap<>(10);
        colors.put("black", 0);
        colors.put("brown", 1);
        colors.put("red", 2);
        colors.put("orange", 3);
        colors.put("yellow", 4);
        colors.put("green", 5);
        colors.put("blue", 6);
        colors.put("violet", 7);
        colors.put("grey", 8);
        colors.put("white", 9);

        colors = colors.entrySet()
            .stream()
            .sorted(Entry.comparingByValue())
            .collect(Collectors.toMap(
                Entry::getKey,
                Entry::getValue,
                (e1, e2) -> e1, // collisions are not merged; second item is ignored
                LinkedHashMap::new)
            );

    }

    int colorCode(String color) {
        return colors.get(color);
    }

    String[] colors() {
        return colors.keySet().toArray(new String[0]);
    }
}
