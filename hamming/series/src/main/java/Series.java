import java.util.ArrayList;
import java.util.List;

class Series {

  private String serie;

  Series(String input) {
    this.serie = input;
  }

  List<String> slices(int size) {
    List<String> slices = new ArrayList<>();

    if (size <= 0) {
      throw new IllegalArgumentException("Slice size is too small.");
    } else if (size > serie.length()) {
      throw new IllegalArgumentException("Slice size is too big.");
    }

    for (int start = 0, end = size; end <= serie.length(); start++, end++) {
      String current = serie.substring(start, end);
      slices.add(current);
    }

    return slices;
  }

}