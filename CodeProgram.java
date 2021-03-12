import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class CodeProgram {
    public static boolean globFlag = false; // global boolean variable; used to control do-while loop in main()

    public static void main(String[] args) throws FileNotFoundException{
        // main driver method; throws FileNotFoundException

        System.out.println("Welcome to the Cipher Program!");
        do{
            // loop repeatedly calls the menu() and task() methods until user no longer wishes to continue
            menu();
            taskPrompt();
        }while(!globFlag); // loop runs until the boolean flag is true

        System.out.println("Cipher Program has ended"); // prompts user that program has ended
    }

    public static void menu() throws InputMismatchException, FileNotFoundException{
        // method that is basically in charge of the entire program; provides a menu for the user to select their
        // choice of cipher to employ; throws FileNotFoundException

        Scanner input = new Scanner(System.in); // scanner variable to take inputs from user

        try{
            System.out.println("1 - Substitution Cipher");
            System.out.println("2 - Shuffle Cipher");
            System.out.print("Which cipher do you choose to utilize?: "); // prompts user to choose their cipher
            int choice = input.nextInt(); // scans integer value from user and uses it as their choice of cipher

            if(choice == 1) // executes if user enters a 1 (Substitution Cipher)
                substitution(); // calls substitution method
            else if(choice == 2) // executes if user enters a 2 (Shuffle Cipher)
                shuffle(); // calls shuffle method
            else{
                System.out.println("**Error: please enter a valid integer**"); // error prompt
                System.out.println(); // formatting
                menu(); // calls this method; sort of a makeshift loop
            }
        }
        catch(InputMismatchException ex){ // catches InputMismatchException if thrown in try-block
            System.out.println("**InputMismatchException handled: please enter valid input**"); // error prompt
            System.out.println(); // formatting
            menu(); // calls this method; sort of a makeshift loop
        }
    }

    public static void substitution() throws InputMismatchException, FileNotFoundException {
        // method handles the case of the user wanted to run a substitution cipher; throws a InputMismatchException and
        // a FileNotFoundException

        Scanner input1 = new Scanner(System.in); // scanner variable
        boolean subFlag = false; // boolean variable to act as a flag; used for controlling loops
        int shift = 0; // initializes the shift variable of type integer; equals the number of characters that each
                       // character will move

        SubstitutionCipher typeObject = new SubstitutionCipher(0); // object exists to test Cipher class
        System.out.println(typeObject.cipherType() + " selected"); // displays type of cipher

        try{ // tests code below for InputMismatchException
            System.out.print("Enter your numerical shift: "); // prompts user for shift value
            shift = input1.nextInt(); // scans shift value
        }
        catch(InputMismatchException ex){ // catches InputMismatchException if thrown from try-block
            System.out.println("**Error: please enter a value of type integer**"); // error prompt
            input1.nextLine(); // empties out the scanner variable
            substitution(); // calls this method; sort of a makeshift loop
        }

        SubstitutionCipher subC = new SubstitutionCipher(shift); // creates SubstitutionCipher class object

        System.out.print("Enter name of input file (include .txt extension): "); // prompts user for name of input file
        String subFileName = input1.next(); // scans name of file from user
        File subFile = new File(subFileName); // creates new file and file object named after user's input
        Scanner text = new Scanner(subFile); // scanner variable that will read text from user

        System.out.print("Enter name of output file (include .txt extension): "); // prompts user for name of output
                                                                                  // file
        String subOutputFileName = input1.next(); // scans name of output file from user

        String compE = "E"; // string variable that's used to compare upcoming user input; will be compared to user's
                            // input in the case of the user choosing to encode
        String compD = "D"; // string variable that's used to compare upcoming user input; will be compared to user's
                            // input in the case of the user choosing to decode
        String choice; // String variable that will store the user's choice of encode or decode; scanned in
        while(!subFlag){
            System.out.print("Would you like to Encode (E/e) or Decode (D/d)?: "); // asks user if they want to encode
                                                                                   // or decode
            choice = input1.next(); // scans input from user

            if(choice.equalsIgnoreCase(compE)){ // executes if user's input matches the E variable above; ignores case
                String encodedText = subC.encode(text.nextLine()); // the text from the input file is scanned to the
                                                                   // encode method in the SubstitutionCipher class;
                                                                   // the value returned from that method is stored in
                                                                   // this variable
                PrintWriter pw = new PrintWriter(subOutputFileName); // declares PrintWriter object pw; name of the
                                                                     // file will be what the user chose as the name of
                                                                     // the output file
                pw.print(encodedText); // prints the encoded text to the output file
                pw.close(); // closes output file in the program; allows us to view contents of file in Java IDE

                System.out.println("Text from " + subFileName + " encoded to " + subOutputFileName);
                subFlag = true; // method's flag is now true so this while-loop can be exited
            }
            else if(choice.equalsIgnoreCase(compD)){ // executes if user's input matches the D variable above; ignores
                                                     // case
                String decodedText = subC.decode(text.nextLine()); // the text from the input file is scanned to the
                                                                   // decode method in the SubstitutionCipher class;
                                                                   // the value returned from that method is stored in
                                                                   // this variable
                PrintWriter pw = new PrintWriter(subOutputFileName); // declares PrintWriter object pw; name of the
                                                                     // file will be what the user chose as the name of
                                                                     // the output file
                pw.print(decodedText); // prints decoded text to the output file
                pw.close(); // closes output file in the program; allows us to view contents of file in Java IDE

                System.out.println("Text from " + subFileName + " decoded to " + subOutputFileName);
                subFlag = true; // method's flag is now true so this while-loop can be exited
            }
            else{ // executes if user enters an invalid string or any other value
                System.out.println("**Error: please enter valid input**"); // error prompt
                subFlag = false; // flag is still false; so this loops back to asking the user if they want to encode
                                 // or decode
            }
        }
        System.out.println(); // formatting
    }

    public static void shuffle() throws InputMismatchException, FileNotFoundException{
        // method handles the case of the user wanted to run a shuffle cipher; throws a InputMismatchException and
        // a FileNotFoundException

        Scanner input2 = new Scanner(System.in); // scanner variable
        boolean shuffFlag = false; // boolean variable to act as a flag; used to control while-loops

        int shuffles = 0; // initializes shuffle variable of type integer; used to record how many shuffles the
                          // user wants

        ShuffleCipher typeObject = new ShuffleCipher(0); // object exists to test Cipher class
        System.out.println(typeObject.cipherType() + " selected"); // displays cipher type

        try{ // tests code below for InputMismatchException
            System.out.print("Enter how many times the text should be shuffled: "); // prompts user for shuffle input
            shuffles = input2.nextInt(); // scans user's input to shuffle variable
        }
        catch(InputMismatchException ex){ // catches thrown exception if need be
            System.out.println("**Error: please enter a value of type integer**"); // error prompt
            input2.nextLine(); // clears scanner variable
            shuffle(); // calls this method; sort of a makeshift loop
        }

        ShuffleCipher shuffC = new ShuffleCipher(shuffles); // creates object for class ShuffleCipher

        System.out.print("Enter name of input file (include .txt extension): "); // prompts user to enter name of
                                                                                 // input file
        String subInputName = input2.next(); // scans user's input into variable of type String
        File subInputFile = new File(subInputName); // creates File object for the input file
        Scanner subInputFileText = new Scanner(subInputFile); // creates Scanner object for the input file; used to
                                                              // parse string from the file

        System.out.print("Enter name of output file (include .txt extension): "); // prompts user to enter name of
                                                                                  // output file
        String subOutputName = input2.next(); // scans user's input into variable of type String

        String compE = "E"; // string variable that's used to compare upcoming user input; will be compared to user's
                            // input in the case of the user choosing to encode
        String compD = "D"; // string variable that's used to compare upcoming user input; will be compared to user's
                            // input in the case of the user choosing to decode
        while(!shuffFlag){ // loops until flag is declared true
            System.out.print("Would you like to Encode (E/e) or Decode (D/d)?: "); // prompts user to make choice
                                                                                   // between encoding or decoding
            String choice = input2.next(); // scans user's input to String variable

            if(choice.equalsIgnoreCase(compE)){ // executes if user's input calls to encode
                String encodedText = shuffC.encode(subInputFileText.nextLine()); // The parsed information from the
                                                                                 // input file will passed to the encode
                                                                                 // method in the ShuffleCipher class;
                                                                                 // the returned String will be stored
                                                                                 // in this variable
                PrintWriter pw = new PrintWriter(subOutputName); // creates output file via PrintWriter object pw
                pw.print(encodedText); // encoded text is printed to output file
                pw.close(); // output file is closed; allows us to view file contents in Java IDE

                System.out.println("Text from " + subInputName + " encoded to " + subOutputName);
                shuffFlag = true; // flag turned to true; loop can now be exited
            }
            else if(choice.equalsIgnoreCase(compD)){ // executes if user's input calls to decode
                String decodedText = shuffC.decode(subInputFileText.nextLine()); // The parsed information from the
                                                                                 // input file will passed to the decode
                                                                                 // method in the ShuffleCipher class;
                                                                                 // the returned String will be stored
                                                                                 // in this variable
                PrintWriter pw = new PrintWriter(subOutputName); // creates output file via PrintWriter object pw
                pw.print(decodedText); // decoded text is printed to output file
                pw.close(); // output file is closed; allows us to view file contents in Java IDE

                System.out.println("Text from " + subInputName + " decoded to " + subOutputName);
                shuffFlag = true; // flag turned to true; loop can now be exited
            }
            else{ // executes if user's input is invalid (doesn't call to either encode or decode text)
                System.out.println("**Error: please enter valid input**"); // error prompt
                shuffFlag = false; // flag remains false, so the loop reiterates
            }
        }
        System.out.println(); // formatting
    }

    public static void taskPrompt(){
        // method acts as the means of asking the user whether or not they would like to continue using the Cipher
        // program

        Scanner taskInput = new Scanner(System.in); // scanner variable for this method

        System.out.print("Do you wish to do another task? Yes (Y/y) or No (N/n): "); // asks user whether or not to
                                                                                     // continue using program
        String answer = taskInput.next(); // takes user's input and stores it as String variable

        String yes = "Y"; // String that will handle the case of the user choosing yes
        String no = "N"; // String that will handle the case of the user choosing no
        if(answer.equalsIgnoreCase(yes)) // executes if user chooses yes/continue; compares input and yes variable;
                                         // ignores case
            globFlag = false; // the global variable will remain false, which causes the do-while loop in the main() to
                              // reiterate and start the program from the top
        else if(answer.equalsIgnoreCase(no)) // executes if the user chooses to stop the program by choosing "no";
                                             // ignores case
            globFlag = true;  // the global flag variable will be marked true, exiting the do-while loop in the main()
                              // and effectively ending the program
        else{ // executes if the user uses an invalid input
            System.out.println("**Error: please answer yes or no"); // error prompt
            taskPrompt(); // calls this method; sort of a makeshift loop
        }
    }
}
