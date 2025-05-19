package io.sourceWare.program.client.model.MessageProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageFilter {


    /*
    * Returns a section
     */
    public static String getSection (String startDelimiter, String endDelimiter, String content, String message){
        Pattern pattern = Pattern.compile(startDelimiter + content + endDelimiter);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    // todo: implement
    /*
    * Returns a list of content
     */
    public static List<String> getList(String startDelimiter, String endDelimiter, String content, String message){
        Pattern pattern = Pattern.compile(startDelimiter + content + endDelimiter);
        Matcher matcher = pattern.matcher(message);

        List<String> signers = new ArrayList<>();
        while (matcher.find()) {
            signers.add(matcher.group(1));
        }

        return signers;
    }

    /*
    * Replaces a section content by something else
     */
    public static String setSection(String startDelimiter ,String endDelimiter ,String content ,String message, String replacement ){
        Pattern pattern = Pattern.compile(startDelimiter + content + endDelimiter);
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            // Build new message by replacing group(1) with newMessageContent
            String before = message.substring(0, matcher.start(1));
            String after = message.substring(matcher.end(1));
            return before + replacement + after;
        } else {
            // If no match, maybe just set it with delimiters as fallback
            return message;
        }
    }

    /*
    * Builds and returns a new message section
     */
    public static String addSection(String startDelimiter, String content, String endDelimiter){
        return startDelimiter + content + endDelimiter;
    }

    }
