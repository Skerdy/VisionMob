package it.visionmobya.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;

public class Utils {

    public static final String IMPORT= "import";
    public static final String EXPORT = "export";



    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String getAgentWorkingDirectory(String agentUserName, String directory){
        StringBuilder stringBuilder = new StringBuilder();
        String agentCode = agentUserName.toLowerCase().replace("agente", "");
        stringBuilder.append("/").append(agentCode).append("/").append(directory);
        Log.d("directory " , " dir : " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static String doubleToSringFormat(Double doubleVal){
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return decimalFormat.format(doubleVal);
    }

}
