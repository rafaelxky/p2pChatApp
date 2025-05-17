package io.sourceWare.program.client.model.MessageProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
       this.message = messageDelimiter + message;
   }

   public EncryptedMessage(){}

    /*
    * Used to bypass the protocol (use not recommended)
     */
    public void setRawMessage(String message) {
        this.message = message;
    }

    public void setProtocolMessage(String message) {
        this.message = messageDelimiter + message;
    }

    public String getRawMessage(){
        return message;
    }

    public String filterMessage(){
        String message = this.message;
        message.replace(messageDelimiter , "");
        message.replace(signDelimiter, "");
        message.replace(signedByDelimiter, "");
        return message;
    }

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

    public void setMessage(String message){
        message.replaceAll(messageDelimiter + anyContent + signedByDelimiter + orEndDelimiter + ")", message);
    }
    public void sign(String signer, String sign){
        message += signedByDelimiter + signer + " " + signDelimiter + sign + " ";
    }

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
}
