package it.visionmobya.utils;

import it.visionmobya.models.Client;
import it.visionmobya.models.DocumentCategory;

public class TextViewHelper {

    public static String generateClientListTitle(DocumentCategory documentCategory, String date){
        StringBuilder stringBuilder = new StringBuilder();

        String documentCounter = "" + (Integer.parseInt(documentCategory.getConttatoreDocumento()) + 1);

        stringBuilder.append(documentCategory.getDescrizioneDocumento().trim()).append(" N° ")
                .append(documentCounter).append(" del ")
                .append(date);
        return stringBuilder.toString();

    }
    public static String getOrderClientToolbarTitle(Client client) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(client.getCodiceCliente()).append("  ").append(client.getRagioneSociale());
        return stringBuilder.toString();
    }

    public static String getClientMobileNumber(Client client){
        return client.getTelefono();
    }

    public static String getClientAddressOrderClient(Client client){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(client.getIndirizzo().trim())
                .append(" - ")
                .append(client.getCAP().trim())
                .append(" ").append(client.getCitta().trim()).append("(")
                .append(client.getProvincia().trim()).append(")");
        return stringBuilder.toString();
    }
}
