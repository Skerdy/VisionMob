package it.visionmobya.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.models.DocTes;

public class DocTesController {

    private DocTesController() {

    }

    //returns all doctes
    public static List<DocTes> getAllDocTes() {
        List<DocTes> result = new ArrayList<>();
        try {
            result = VisionFileManager.getInstance().getAllDocumentTestas();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getLastIdTesta() {
        List<DocTes> list = getAllDocTes();
        if (list.isEmpty()) {
            return "1";
        } else {
            return list.get(list.size() - 1).getIdTesta();
        }
    }

}
