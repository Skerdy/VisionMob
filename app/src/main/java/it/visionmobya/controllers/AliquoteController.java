package it.visionmobya.controllers;

import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.models.Vat;

public class AliquoteController {

    public static List<Vat> getAllVats(){
        if(VisionFileManager.getInstance().getArticles()!=null) {
            return VisionFileManager.getInstance().getVats();
        }
        else {
            return new ArrayList<>();
        }
    }

    public static Vat getVatWithId(String id){
        if(VisionFileManager.getInstance().getArticles()!=null) {
            for (Vat vat : VisionFileManager.getInstance().getVats()) {
                if (vat.getCodiceIva().equals(id))
                    return vat;
            }
            return null;
        }else
            return null;
    }
}
