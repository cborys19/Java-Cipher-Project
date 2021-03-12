# Java-Project-2

Encoding and Decoding 

  a.	Create an interface MessageEncoder that has a single abstract method encode(plainText), where plainText is the message to be encoded. The method returns the encoded message.
  
  b.	Create Abstract class Cipher that has a single abstract method cipherType(); the method returns a string.
  
  c.	Create a class SubstitutionCipher that extends class Cipher and implements the interface MessageEncoder defined in parts a and b. The constructor should have one parameter         called shift. Define method cipherType so that the method returns the string “SubstitutionCipher”. Define the method encode so that each letter is shifted by the value in         shift. For example, if shift is 3, a will be replaced by d, b will be replaced by e, c will be replaced by f, and so on. You may wish to define a private method that shifts       a single character.
  
  d.	Create a class ShuffleCipher that extends class Cipher and implements the interface MessageEncoder defined in parts a and b. The constructor should have one parameter called       n. Define method cipherType so that the method returns the string “ShuffleCipher”. Define the method encode so that the message is shuffled n times. To perform one shuffle,       split the message in half and then take characters from each half alternately. For example, if the message is “abcdefghi”, the halves are “abcde” and “fghi”. The shuffled         message is “afbgchdie”. You may wish to define a private method that performs one shuffle.
  
  e.	Create an interface MessageDecoder that has a single abstract method decode( cipherText), where cipherText is the message to be decoded. The method returns the decoded             message. 
  
  f.	Modify the classes SubstitutionCipher and ShuffleCipher defined in parts c and d, so that they implement MessageDecoder as well as the interface MessageEncoder. 
  
  g.	Finally, create class CodeProgram with a menu driven program that allows a user to encode and decode messages read from a file and written to another file.
