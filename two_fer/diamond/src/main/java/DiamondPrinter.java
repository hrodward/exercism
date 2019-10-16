import java.util.ArrayList;
import java.util.List;

class DiamondPrinter {

    private static final int A_CODE = 'A';

    private String getLine(char character, int sideSpacesCount, int middleSpacesCount) {
        String sideSpaces = "%" + sideSpacesCount + "s";
        String middleSpaces = "%" + middleSpacesCount + "s";
        if (sideSpacesCount > 0) {
            if (middleSpacesCount > 0) {
                return String.format(sideSpaces + "%c" + middleSpaces + "%c" + sideSpaces, " ", character, " ", character, " ");
            } else {
                return String.format(sideSpaces + "%c" + sideSpaces, " ", character, " ");
            }
        } else if (middleSpacesCount > 0) {
            return String.format("%c" + middleSpaces + "%c", character, " ", character);
        } else {
            return String.valueOf(character);
        }
    }

    List<String> printToList(char letter) {
        List<String> diamond = new ArrayList<>();
        int middleSpaces = 0;

        // First half of the diamond
        for (int localIdx = A_CODE; localIdx <= letter; localIdx++) {
            int sideSpaces = letter - localIdx;
            diamond.add(getLine((char) localIdx, sideSpaces, middleSpaces));
            if (sideSpaces > 0) {
                if (middleSpaces == 0) {
                    middleSpaces++; // first to second line
                } else {
                    middleSpaces += 2; // rest of the lines
                }
            }
        }

        // Second half of the diamond
        middleSpaces -= 2;
        for (int localIdx = letter - 1; localIdx >= A_CODE; localIdx--) {
            int sideSpaces = letter - localIdx;
            diamond.add(getLine((char) localIdx, sideSpaces, middleSpaces));
            middleSpaces -= 2;
        }

        return diamond;
    }

}
