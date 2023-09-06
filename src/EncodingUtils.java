import java.util.HashMap;

public abstract class EncodingUtils {

    public abstract String decode(String encodedText);
    public abstract String encode(String plainText);

    public static HashMap<Integer,Character> loadReferenceTable(){
        HashMap<Integer,Character> map = new HashMap<>();
        int counter = 0;
        for(char ch = 'A'; ch<='Z';ch++){
            map.put(counter,ch);
            counter++;
        }
        for(char ch = '0'; ch<='9';ch++){
            map.put(counter,ch);
            counter++;
        }
        for(char ch = '('; ch<='/';ch++){
            map.put(counter,ch);
            counter++;
        }
        return  map;
    }

    public static void printReferenceTable(){
        System.out.println("================================");
        System.out.println("        Reference table          ");
        System.out.println("================================");
        EncoderApp.referenceTable.forEach(((integer, character) -> System.out.println(integer + " : " + character)));
        System.out.println("Enter a character from reference table:");
    }
}
