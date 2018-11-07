package it.visionmobya.controllers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.models.Vat;

public class AliquoteController {

    public static List<Vat> getAllVats() {
        if (VisionFileManager.getInstance().getVats() != null) {
            return VisionFileManager.getInstance().getVats();
        } else {
            return new ArrayList<>();
        }
    }

    public static Vat getVatWithId(String id) {
        if (VisionFileManager.getInstance().getVats() != null) {
            for (Vat vat : VisionFileManager.getInstance().getVats()) {
                Log.d("SkerdiIVA", " Iva id : " + vat.getCodiceIva() + " input Id = " + id);
                if (vat.getCodiceIva().equals(id))
                    return vat;
            }
            return null;
        } else
            return null;
    }
}
