import java.util.HashMap;
import java.util.Scanner;

public class EncoderApp {
    public static final HashMap<Integer,Character> referenceTable = EncodingUtils.loadReferenceTable();
    static char offsetCharacter;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        Message obj = new Message();
        do {
            menu();
            try {
                choice = Integer.valueOf(sc.nextLine());
                switch(choice){
                    case 1:
                        //Encode plain Text message
                        userSelection(sc,obj);
                        EncodingUtils.printReferenceTable();
                        String userInput = sc.nextLine();
                        if(userInput.length()!=1){
                            System.out.println("Invalid Option! PLease enter a character.");
                        }
                        else {
                            validateCharacter(userInput, obj);
                        }
                        break;
                    case 2:
                        //Decode encoded message
                        userSelection(sc,obj);
                        System.out.println("PlainText Message: " + obj.getDecodedMessage());
                        break;
                    case 3:
                        System.out.println("==================");
                        System.out.println("   Exiting App    ");
                        System.out.println("==================");
                        break;
                    default:
                        System.out.println("Invalid Option!");
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("You have not entered a number. PLease select option 1 or 2.");
            }
        }while(choice != 3);
    }

    public static void menu(){
        System.out.println("===================");
        System.out.println("    Encoder Menu    ");
        System.out.println("====================");
        System.out.println("1. Encode message.");
        System.out.println("2. Decode message.");
        System.out.println("3. Quit");
        System.out.print("Enter choice: ");
    }
    public static void userSelection(Scanner sc, Message obj){
        System.out.println("Enter your message: ");
        obj.setPlainTextMessage(sc.nextLine());
    }
    public static void validateCharacter(String userInput, Message obj){
        offsetCharacter = userInput.charAt(0);
        if (referenceTable.containsValue(offsetCharacter)) {
            System.out.println("Encoded Message: " + obj.encode(obj.getPlainTextMessage().toUpperCase()));
        } else {
            System.out.println("Invalid character chosen. Please enter again.");
        }
    }
}
