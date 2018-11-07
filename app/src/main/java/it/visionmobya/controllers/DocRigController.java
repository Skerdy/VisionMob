package it.visionmobya.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.models.DocRig;

public class DocRigController {

    private DocRigController() {
    }

    public static List<DocRig> getAllDocRigas() {
        List<DocRig> result = new ArrayList<>();
        try {
            result = VisionFileManager.getInstance().getAllDocumentRigas();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getLastIdRiga() {
        List<DocRig> list = getAllDocRigas();
        if (list.isEmpty()) {
            return 1;
        } else {
            return Integer.parseInt(list.get(list.size() - 1).getIdRiga());
        }
    }
}
