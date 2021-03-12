public class SubstitutionCipher extends Cipher
    implements MessageEncoder, MessageDecoder{
    private int shift; // private integer variable that stores the amount of characters each letter will shift

    public SubstitutionCipher(int shift){
        // constructor that sets the shift to a specified value
        this.shift = shift;
    }

    @Override
    public String cipherType(){
        // overridden abstract method from class Cipher; displays type of cipher
        return "SubstitutionCipher";
    }

    @Override
    public String encode(String plainText){
        // overridden abstract method from interface MessageEncoder; returns encoded version of the string passed as
        // a parameter

        StringBuilder temp = new StringBuilder(); // temporary StringBuilder variable for easy modification

        for(int i = 0; i < plainText.length(); i++) // loops to end of original string
            temp.append((char)(plainText.charAt(i) + this.shift)); // loop appends shifted characters until all
                                                                   // characters in original string have been changed;
                                                                   // utilizes casting for accurate shifting

        String newTemp = new String(temp); // stores finalized edited StringBuilder variable as a String

        return newTemp;
    }

    @Override
    public String decode(String cipherText){
        // overridden abstract method from interface MessageDecoder; returns decoded version of the string generated
        // by encode()

        StringBuilder x = new StringBuilder(); // temporary StringBuilder variable for easy modification

        for(int i = 0; i < cipherText.length(); i++) // loops to end of encoded string
            x.append((char)(cipherText.charAt(i) - this.shift)); // performs opposite task of the loop in the encoding
                                                                 // method; characters will be shifted back the amount
                                                                 // of characters set in the shift variable

        String y = new String(x); // stores finalized edited StringBuilder variable as a String

        return y;
    }
}