import java.util.Arrays;
import java.util.Scanner;

public class Encoder {
    private String[] refList = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "(", ")",
            "*", "+", ",", "-", ".", "/" };

    public String[] getRef() {
        return refList;
    }

    // Rotate the orignal array with the offset to get a new array
    private String[] rotation(String[] originalArr, int offset, int arrLength) {
        int counter = 0;
        String[] rotatedRef = new String[originalArr.length];
        for (int i = arrLength; i < originalArr.length; i++) {
            rotatedRef[counter] = originalArr[i];
            counter++;
        }

        for (int i = 0; i < arrLength; i++) {
            rotatedRef[counter] = originalArr[i];
            counter++;
        }

        return rotatedRef;
    }

    // Obtaining the offset value
    private int getOffset(String[] originalArr) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter your offset value: ");
        int offset = Arrays.asList(originalArr).indexOf(s.next().toUpperCase());
        s.close();

        return offset;
    }

    public String encode(String plainText) {
        plainText = plainText.toUpperCase();
        char[] txtArr = plainText.toCharArray();
        String[] refArr = getRef();

        int offset = getOffset(refArr);
        int refLength = refArr.length - offset;
        String[] newRef = rotation(refArr, offset, refLength);

        // Checking each character of the input and encoding them using the rotated
        // array
        String result = "";
        for (char c : txtArr) {
            if (!Character.toString(c).equals(" ")) {
                int index = Arrays.asList(refArr).indexOf(Character.toString(c));

                result += newRef[index];
            } else {
                result += " ";
            }
        }

        return result;
    }

    public String decode(String encodedText) {
        encodedText = encodedText.toUpperCase();
        char[] txtArr = encodedText.toCharArray();
        String[] refArr = getRef();

        int offset = getOffset(refArr);
        int refLength = refArr.length - offset;
        String[] newRef = rotation(refArr, offset, refLength);

        // Checking each character of the input and decoding them using the decoded
        // array
        String result = "";
        for (char c : txtArr) {
            if (!Character.toString(c).equals(" ")) {
                int index = Arrays.asList(newRef).indexOf(Character.toString(c));

                result += refArr[index];
            } else {
                result += " ";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Encoder e = new Encoder();

        Scanner s = new Scanner(System.in);
        System.out.print("Do you wish to endode or decode? (E is for Encode and D is for Decode): ");
        String choice = s.nextLine();

        if (choice.equalsIgnoreCase("E")) {
            System.out.print("Please enter your text to encode (In Caps): ");
            String plainTxt = s.nextLine();
            System.out.println("The result is : " + e.encode(plainTxt));
        } else if (choice.equalsIgnoreCase("D")) {
            System.out.print("Please enter your text to decode (In Caps): ");
            String encodedTxt = s.nextLine();
            System.out.println("The result is : " + e.decode(encodedTxt));
        } else {
            System.out.println("Quitting the application....");
            System.exit(0);
        }
        s.close();
    }

}