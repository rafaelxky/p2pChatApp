package io.sourceWare.program.client.model.MessageProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* This class handles the message and everything associated with it
* That includes all the classes to get the message, signers and the sign
 */
public class EncryptedMessage {
    String messageDelimiter = "message: ";
    String signDelimiter = "sign: ";
    String signedByDelimiter = "signedBy: ";
    String endDelimiter = "\n";
    String toDelimiter = "to: ";
    String anyContent = "\\s*(.*?)\\s*";
   String message;

   // todo: switch regex limit to \n instead of orEndDelimiter

   /*
   * protocol:
   * 1 - message:
   * 2 - signedBy:
   * 3 - sign:
    */

   public EncryptedMessage(String message, String to){
       this.message = messageDelimiter + message + " \n" + toDelimiter + to + " \n";
   }

   public EncryptedMessage(String message){
       this.message = messageDelimiter + message + " \n" ;
   }

   public EncryptedMessage(){}

    /*
    * Used to bypass the protocol when setting the message (use not recommended)
     */
    public void setRawMessage(String message) {
        this.message = message;
    }

    /*
    * Sets the message.
    * It will automatically add message:
     */
    public void setProtocolMessage(String message) {
        this.message = messageDelimiter + message;
    }

    /*
    * gets the raw message with all delimiters
     */
    public String getRawMessage(){
        return message;
    }

    /*
    * Returns only the message section
     */
    public String getMessage(){
        return MessageFilter.getSection(messageDelimiter, endDelimiter, anyContent, this.message);
    }

    /*
    * Replaces the message section
     */
    public void setMessage(String message){
      this.message = MessageFilter.setSection(messageDelimiter, endDelimiter, anyContent, this.message, message);
    }

    /*
    * Adds a sign section
     */
    public void sign(String signer, String sign){
        message += MessageFilter.addSection(signedByDelimiter, signer, endDelimiter)
                + MessageFilter.addSection(signDelimiter, sign, endDelimiter);
    }

    /*
    * Returns a list of all the signers delimited by "signedBy: "
     */
    public List<String> getSigners(){
       return MessageFilter.getList(signedByDelimiter, endDelimiter, anyContent, this.message);
    }
    public List<String> getSigns(){
       return MessageFilter.getList(signDelimiter, endDelimiter, anyContent, this.message);
    }

    public String getMessageSender(){
        return getSigners().get(0);
    }
}
