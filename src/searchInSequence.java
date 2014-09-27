import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rafaelrs on 27.09.14.
 */
public class searchInSequence {

    public static void main(String[] args) {

        String inData;

        //Data input part
        System.out.println("Enter number:");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            inData = in.readLine();

            //Entered data format checking
            Integer.parseInt(inData);
        } catch (IOException e) {
            System.out.println("Input ERROR!");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Input ERROR! Wrong number format");
            return;
        }

        //Let's find number, that can be part of sequence
        int foundNumber = 0;
        int foundNumberShift = 0;
        for (int i = 0; i < inData.length(); i++) {
            for (int j = 0; j < inData.length() - i; j++) {
                String currNumber = inData.substring(j, j + i + 1);
                int parsedCurrNumber = Integer.parseInt(currNumber);

                String composedNumber = fillAround(parsedCurrNumber, j, inData.length() - j - (i + 1));

                if (composedNumber.equals(inData)) {
                    foundNumber = parsedCurrNumber;
                    foundNumberShift = j;

                    break;
                }

            }

            if (foundNumber != 0) break;
        }

        //Calculating position of foundNumber
        String foundNumberStr = String.valueOf(foundNumber);
        long foundNumberPosition = 0;

        for (int i = 1; i < foundNumberStr.length(); i++) foundNumberPosition += 9 * Math.pow(10, i - 1) * i;
        foundNumberPosition += (foundNumber - Math.pow(10, foundNumberStr.length() - 1)) * (foundNumberStr.length()) + 1;

        //Correction position using shift
        long sequencePosition = foundNumberPosition - foundNumberShift;

        //Printing result
        System.out.println();
        System.out.println("Sequence position is: " + String.valueOf(sequencePosition));

    }

    /**
     * Fills space around given number with sequence values required length
     *
     * @param focusNumber - number being in middle
     * @param charsBefore - length of space before number required to fill
     * @param charsAfter - length of space after number required to fill
     * @return filled string
     */
    private static String fillAround(int focusNumber, int charsBefore, int charsAfter) {
        String prevSequence = "";
        String nextSequence = "";

        //Fill head of sequence
        int currNumber = focusNumber;
        while (prevSequence.length() < charsBefore) {
            currNumber--;

            //There's no sense to continue
            if (currNumber <= 0) break;

            prevSequence += String.valueOf(currNumber);
        }
        if (prevSequence.length() > charsBefore)
            prevSequence = prevSequence.substring(prevSequence.length() - charsBefore);

        //Fill tail of sequence
        currNumber = focusNumber;
        while (nextSequence.length() < charsAfter) {
            currNumber++;

            nextSequence += String.valueOf(currNumber);
        }
        if (nextSequence.length() > charsAfter)
            nextSequence = nextSequence.substring(0, charsAfter);

        return prevSequence + String.valueOf(focusNumber) + nextSequence;
    }
}
