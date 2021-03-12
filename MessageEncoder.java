// This interface creates an abstract method of type String that will allow classes to
// encode a given string to the unique specifications of each class; takes a parameter
// of type String that represents the String to be encoded

public interface MessageEncoder {
    String encode(String plainText);
}
