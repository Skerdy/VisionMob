package it.visionmobya.utils;

import android.widget.EditText;

import it.visionmobya.models.Article;

public class ValidatorHelper {

    public static boolean isDocumentEligibleForCalculations(Article article, EditText quantita, EditText unitMisura , EditText prezzoUnit, EditText scontoPercentuale){
        boolean flag = true;
        //nese article eshte null nuk behen calculations
        if(article == null){
            flag = false;
        }
        if(quantita.getText().toString().trim().isEmpty()){
            flag = false;
        }
        if(unitMisura.getText().toString().trim().isEmpty()){
            flag = false;
        }
        if(prezzoUnit.getText().toString().trim().isEmpty()){
            flag = false;
        }
        if(scontoPercentuale.getText().toString().trim().isEmpty()){
            flag = false;
        }
        return flag;
    }
}
