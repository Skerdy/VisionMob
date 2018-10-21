package it.visionmobya.utils;

import android.util.Log;
import android.widget.Toast;

import it.visionmobya.controllers.AliquoteController;
import it.visionmobya.models.Article;
import it.visionmobya.models.Vat;
import it.visionmobya.models.customModels.DocumentState;

public class DocumentStateHelper {

    public static final int NO_SCONTO_PERCENTUALE_FOUND = 213231313;


    //sets Article, and the sconto percentuale
    public static void selectArticleAction(DocumentState documentState, Article article){
        documentState.setArticle(article);

       //set document state sconto percentuale value
        switch (validateArticleScontoPErcentuale(article)) {
            case 1:
            documentState.setScontoPercentuale(Double.valueOf(article.getPercentualeDiSconto1()));
                break;
            case 2:
                documentState.setScontoPercentuale(Double.valueOf(article.getPercentualeDiSconto2()));
                break;
            case 3:
                documentState.setScontoPercentuale(Double.valueOf(article.getPercentualeDiSconto2()));
                break;
            case NO_SCONTO_PERCENTUALE_FOUND:
                documentState.setScontoPercentuale(30.0);
                break ;
        }
    }


    //sconto value llogaritet para imponibiles
    public static void calculateScontoValue(DocumentState documentState){
            Double scontoValue = 0.0;
            Double brutoPrice = documentState.getQuantita()*documentState.getPrezzoUnitario();
            scontoValue = brutoPrice * (documentState.getScontoPercentuale() / 100);
            documentState.setScontoValue(scontoValue);
    }


    //pasi kemi llogaritur sconto value llogarisim imponibilen
    public static  void calculateImponibileValue(DocumentState documentState){
            Double brutoPrice = documentState.getQuantita()*documentState.getPrezzoUnitario();
            calculateScontoValue(documentState);
            Double imponibile = brutoPrice - documentState.getScontoValue();
            documentState.setImponibile(imponibile);
    }

    public static boolean calculateTotalArticoloPrice(DocumentState documentState){

            calculateImponibileValue(documentState);
            Vat ivaValue = AliquoteController.getVatWithId(documentState.getArticle().getCodiceIvaVendite().trim());
            Log.d("SkerdiIVA" , " Iva id eshte : " +documentState.getArticle().getCodiceIvaVendite().trim());
            Double total_price = 0.0;
            //nese kodi i iva vendite eshte jo null bosh dhe ekziston aktualisht gjejme ivaValue dhe llogarisim total price;
            if(ivaValue!=null){
                Log.d("SkerdiIVA" , "Iva value e ndryshme nga null = " + ivaValue.getAliquotaInPercentuale());
                Double ivaPercentuale = Double.valueOf(ivaValue.getAliquotaInPercentuale());
                Double ivaValueUponPrice = documentState.getImponibile()*(ivaPercentuale/100);
                total_price = documentState.getImponibile() + ivaValueUponPrice;
                documentState.setPrezzoTotaleArticle(total_price);
                documentState.setIvaValueUponPrice(ivaValueUponPrice);
            }
            else {
                documentState.setPrezzoTotaleArticle(total_price);
                return false;
            }
            return true;
    }

    private static int validateArticleScontoPErcentuale(Article article){
        if(!article.getPercentualeDiSconto1().trim().isEmpty()){
            //kthejme 1 duke treguar se artikulli ka sconto percentuale 1
           return 1;
        }
        else {
            Log.d("ScontoValidation", "Artikulli nuk ka sconto percentuale 1");

            if(!article.getPercentualeDiSconto2().trim().isEmpty()){
                //kthejme 2 duke treguar se artikulli ka sconto percentuale 2
                return 2;

            }
            else{
                Log.d("ScontoValidation", "Artikulli nuk ka sconto percentuale 2");
                if(!article.getPercentualeDiSconto3().trim().isEmpty()){
                    //kthejme 3 duke treguar se artikulli ka sconto percentuale 3
                    return 3;

                }
                else{
                    Log.d("ScontoValidation", "Artikulli nuk ka sconto percentuale 3");
                }
            }
        }
        return NO_SCONTO_PERCENTUALE_FOUND;
    }

}
