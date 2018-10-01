package it.visionmobya.utils;

import java.util.List;

import it.visionmobya.models.customModels.DocumentState;

public class FinanceHelper {

    public static Double[] calculateImponibileIvaTotale(List<DocumentState> documentStates){
        //kemi tre variblat qe duam te modifikojme sipas documentStates
        Double imponibile = 0.0;
        Double iva = 0.0;
        Double totale = 0.0;

        for(DocumentState documentState : documentStates){

                if(documentState.getImponibile()!=null) {
                    imponibile += documentState.getImponibile();
                }
                if(documentState.getIvaValueUponPrice()!=null){
                    iva += documentState.getIvaValueUponPrice();

                }
                if(documentState.getPrezzoTotaleArticle()!=null){
                    totale += documentState.getPrezzoTotaleArticle();
                }

        }
        //pas modifikimit kthejme nje array me doubles mbrapsht
       return returnArrayFromValues(imponibile, iva, totale);
    }

    private static Double[] returnArrayFromValues(Double imponibile, Double iva, Double totale){
        Double[] results = new Double[3];
        results[0] = imponibile;
        results[1] = iva;
        results[2] = totale;
        return results;
    }
}
