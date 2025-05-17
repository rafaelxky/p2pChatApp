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
    String orEndDelimiter = "|$";
    String anyContent = "\\s*(.*?)\\s*(";
   String message;

   /*
   * protocol:
   * 1 - message:
   * 2 - signedBy:
   * 3 - sign:
    */
   public EncryptedMessage(String message) {
       this.message = messageDelimiter + message + " \n";
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
        Pattern pattern = Pattern.compile(messageDelimiter + anyContent + signedByDelimiter + orEndDelimiter + ")");
        Matcher matcher = pattern.matcher(this.message);
        if (matcher.find()) {
            String message = matcher.group(1);
            return message;
        } else {
            return null;
        }
    }

    /*
    * Replaces the message section
     */
    public void setMessage(String message){
        //this.message = messageDelimiter + this.message.replaceAll(messageDelimiter + anyContent + signedByDelimiter + orEndDelimiter + ")", message) + " \n";
        Pattern pattern = Pattern.compile(  Pattern.quote(messageDelimiter) + "\\s*(.*?)\\s*(?:" + Pattern.quote(signedByDelimiter) + "|$)");
        Matcher matcher = pattern.matcher(this.message);

        if (matcher.find()) {
            // Build new message by replacing group(1) with newMessageContent
            String before = this.message.substring(0, matcher.start(1));
            String after = this.message.substring(matcher.end(1));
            this.message = before + message + after;
        } else {
            // If no match, maybe just set it with delimiters as fallback
        }
    }

    /*
    * Adds a sign section
     */
    public void sign(String signer, String sign){
        message += signedByDelimiter + signer + " \n" + signDelimiter + sign + " \n";
    }

    /*
    * Returns a list of all the signers delimited by "signedBy: "
     */
    public List<String> getSigners(){
        Pattern pattern = Pattern.compile(signedByDelimiter+ anyContent + signDelimiter + ")");
        Matcher matcher = pattern.matcher(message);

        List<String> signers = new ArrayList<>();
        while (matcher.find()) {
            signers.add(matcher.group(1));
        }

        return signers;
    }
    public List<String> getSigns(){
        Pattern pattern = Pattern.compile(signDelimiter+ anyContent + signedByDelimiter + orEndDelimiter + ")");
        Matcher matcher = pattern.matcher(message);

        List<String> signers = new ArrayList<>();
        while (matcher.find()) {
            signers.add(matcher.group(1));
        }

        return signers;
    }

    public String getMessageSender(){
        return getSigners().get(0);
    }
}
