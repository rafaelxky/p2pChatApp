package io.sourceWare.program.client.model.MessageProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageProtocalHandler {
    public static String filterMessage(String message){
        message.replace("message: ", "");
        message.replace("sign: ", "");
        message.replace(" signedBy: ", "");
        return message;
    }

    public static String getMessage(String message){
        Pattern pattern = Pattern.compile("message:\\s*(.*?)\\s*sender:");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            message = matcher.group(1);
            return message;
        } else {
           return null;
        }
    }

    public static List<String> getSigners(String message){
        Pattern pattern = Pattern.compile("signedBy:\\s*(.*?)\\s*sign:");
        Matcher matcher = pattern.matcher(message);

        List<String> signers = new ArrayList<>();
        while (matcher.find()) {
            signers.add(matcher.group(1));
        }

        return signers;
    }
    public static List<String> getSigns(String message){
        Pattern pattern = Pattern.compile("sign:\\s*(.*?)\\s*(?=signedBy:|$)");
        Matcher matcher = pattern.matcher(message);

        List<String> signers = new ArrayList<>();
        while (matcher.find()) {
            signers.add(matcher.group(1));
        }

        return signers;
    }
}
