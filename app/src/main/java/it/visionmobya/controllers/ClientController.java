package it.visionmobya.controllers;

import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.models.Client;

public class ClientController {

    public static List<Client> getAllClients() {
        if (VisionFileManager.getInstance().getClients() != null) {
            return VisionFileManager.getInstance().getClients();
        } else {
            return new ArrayList<>();
        }
    }


}
