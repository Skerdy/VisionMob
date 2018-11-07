package it.visionmobya.controllers;

import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.models.Article;
import it.visionmobya.models.Client;
import it.visionmobya.models.Listino;

public class ListinoController {

    //returns all listinos or empty when reference might be null for some reason
    public static List<Listino> getAllListinos() {
        if (VisionFileManager.getInstance().getListinos() != null) {
            return VisionFileManager.getInstance().getListinos();
        } else {
            return new ArrayList<>();
        }
    }

    public static boolean clientHasFixedPriceForArticle(Client client, Article article) {
        for (Listino listino : getAllListinos()) {
            if (listino.getCodiceCliente().equals(client.getCodiceCliente())
                    && listino.getCodiceArticolo().equals(article.getCodiceArticolo())) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param client  klienti per te cilin po kerkojme ne listino
     * @param article artikulli per te cilin po kerkojme ne listino
     * @return kthen price nese gjen nje kombinim mes te dyjave ose null nese nuk gjen ( null behet handle me vone ne
     * business logic
     */

    public static Double getArticlePriceForClient(Client client, Article article) {
        Double result = null;
        for (Listino listino : getAllListinos()) {
            if (listino.getCodiceCliente().equals(client.getCodiceCliente())
                    && listino.getCodiceArticolo().equals(article.getCodiceArticolo())) {
                result = Double.valueOf(listino.getPrezzo());
            }
        }
        return result;
    }


}
