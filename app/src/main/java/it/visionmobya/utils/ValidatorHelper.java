package it.visionmobya.utils;

import android.widget.EditText;

import java.util.List;

import it.visionmobya.models.Article;
import it.visionmobya.models.customModels.DocumentState;

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

    public static boolean isDocumentEligibleForDocumentClosure(DocumentState documentState){

        if(documentState==null){
            return false;
        }

        if(documentState.getArticle()==null){
            return false;
        }

        if(documentState.getNumerArticolo() == null){
            return false;
        }

        if(documentState.getImponibile() == null) {
            return false;
        }
        if(documentState.getIvaValueUponPrice() == null ) {
            return false;
        }
        if(documentState.getPrezzoUnitario() == null){
            return false;
        }
        if(documentState.getQuantita() == null){
            return false;
        }
        if(documentState.getPrezzoTotaleArticle() == null){
            return false;
        }
        if(documentState.getScontoPercentuale() == null){
            return false;
        }

        if (documentState.getUnitaDiMisura() == null){
            return false;
        }

        if(documentState.getScontoValue() == null){
            return false;
        }
        return true;
    }

    public static boolean areDocumentsEligibleForDocumentClosure(List<DocumentState> documentStates) {
        boolean flag = true;
        for(DocumentState documentState : documentStates){
            if(!isDocumentEligibleForDocumentClosure(documentState)){
                flag = false;
            }
        }
        return flag;
    }
}
