package io.sourceWare.program.client.util;

public class Validator {
    public static boolean areInputsNull(Object ... args){
        for (Object arg : args){
            if (arg == null){
                return true;
            }
        }
        return false;
    }
}
