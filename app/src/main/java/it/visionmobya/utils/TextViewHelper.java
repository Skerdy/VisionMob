package it.visionmobya.utils;

public class TextViewHelper {

    public static String generateClientListTitle(String documentType, String documentCounter, String date){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(documentType).append(" N° ")
                .append(documentCounter).append(" del ")
                .append(date);
        return stringBuilder.toString();
    }
}
