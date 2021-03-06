public class ShuffleCipher extends Cipher
        implements MessageEncoder, MessageDecoder{
    private int n; // private integer variable stores the amount of times the given String will be shuffled

    public ShuffleCipher(int n){
        // constructor that sets the number of shuffles to a specified value
        this.n = n;
    }

    @Override
    public String cipherType(){
        // overridden abstract method; displays type of cipher
        return "ShuffleCipher";
    }

    @Override
    public String encode(String plainText){
        // overridden abstract method from interface MessageEncode; returns the encoded/shuffled version of
        // given String

        String newStr = plainText; // copies parameter into String variable to avoid unnecessary changes
        for(int i = 0; i < this.n; i++) // loops the amount of times that n is set to
            newStr = encodeOnce(newStr); // replaces String value with the one generated by the encodeOnce() method

        return newStr;
    }

    private String encodeOnce(String pText){
        // private method that was suggested in the given program guidelines; used to shuffle the string at least once;
        // takes string value, cuts it into two strings, then is pieced back together with alternating characters from
        // each string; takes String parameter that represents the plainText parameter from encode()

        int median; // will store index of the middle value in the string
        if(pText.length() % 2 == 0) // executes if the length of the string is even
            median = pText.length() / 2;
        else // executes if length of the string is odd
            median = (pText.length() + 1) / 2;

        // String values are stored as StringBuilders for easier manipulation
        StringBuilder s1 = new StringBuilder(pText.substring(0, median)); // stores first half of String
        StringBuilder s2 = new StringBuilder(pText.substring(median)); // stores second half of String
        StringBuilder s3 = new StringBuilder(); // creates empty StringBuilder variable

        for(int i = 0, j = 0; i < s1.length(); i++){
            // loops to the end of the the first half of the String
            s3.append(s1.charAt(i)); // adds character at the index to the empty StringBuilder
            if(j < s2.length()) // executes if j is not yet greater than the length of the second half of the string
                s3.append(s2.charAt(i)); // adds character at the index to the empty StringBuilder
        }

        String s = new String(s3); // stores finalized StringBuilder as a regular string
        return s;
    }

    @Override
    public String decode(String cipherText){
        // overridden abstract method from interface MessageDecoder; used to unshuffle (decode) the string
        // from encode()

        StringBuilder temp = new StringBuilder(cipherText); // stores parameter String value in temporary StringBuilder
                                                            // variable for easier manipulation
        StringBuilder s = new StringBuilder(); // creates empty StringBuilder variable

        for(int i = 0; i < this.n; i++){
            // iterates the same amount of times the encode() method did
            for(int j = 0; j < temp.length(); j++){
                // iterates through the StringBuilder's length
                if(j % 2 == 0) // executes if the current iteration is even
                    s = s.append(temp.charAt(j)); // appends that character to the empty StringBuilder
            }
            for(int j = 0; j < temp.length(); j++){
                // iterates through the StringBuilder's length
                if(j % 2 == 1) // executes if the current iteration is odd
                    s = s.append(temp.charAt(j)); // appends that character to the empty StringBuilder
            }
            temp = s; // the temporary StringBuilder now contains the finalized, once-empty StringBuilder value
            s = new StringBuilder(); // the variable that was once-empty is made empty once more so that temp and s
                                     // are not confused for one another since they hold the same value; sort of like
                                     // garbage collection in C++
        }
        String newTemp = new String(temp); // the temporary StringBuilder value is stored as a regular String

        return newTemp;
    }
}
