// This interface creates an abstract method of type String that will allow classes to
// decode a given string to the unique specifications of each class; takes a parameter
// of type String that represents the String to be decoded

public interface MessageDecoder {
    String decode(String cipherText);
}
