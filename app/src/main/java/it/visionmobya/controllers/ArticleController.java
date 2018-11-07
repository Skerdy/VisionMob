package it.visionmobya.controllers;

import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.models.Article;
import it.visionmobya.models.Client;

public class ArticleController {

    private static final Integer LISTINO_1 = 1;
    private static final Integer LISTINO_2 = 2;
    private static final Integer LISTINO_3 = 3;
    private static final Integer LISTINO_4 = 4;
    private static final Integer LISTINO_5 = 5;
    private static final Integer LISTINO_6 = 6;
    private static final Integer LISTINO_7 = 7;
    private static final Integer LISTINO_8 = 8;
    private static final Integer LISTINO_9 = 9;

    private ArticleController() {

    }

    public static List<Article> getAllArticles() {
        return VisionFileManager.getInstance().getArticles();
    }

    public static Article getArticleWithId(String id) {
        if (VisionFileManager.getInstance().getArticles() != null) {
            for (Article article : VisionFileManager.getInstance().getArticles()) {
                if (article.getCodiceArticolo().equals(id))
                    return article;
            }
            return null;
        } else return null;
    }

    /**
     * @param client  klienti per te cilin kerkojme indeksin e listino qe do perdorim tek article per price
     * @param article artikulli tek i cili do marrim price
     * @return kthen njeren nga listinot e paracaktuara tek klienti por mund te ktheje by default listinon e pare
     * te artikullit nese nuk behet match asnje kusht
     */
    public static Double getArticlePriceFromClientListino(Client client, Article article) {
        if (!client.getListino().trim().isEmpty()) {
            Integer clientListinoIndex = Integer.valueOf(client.getListino());
            if (clientListinoIndex.equals(LISTINO_1)) {
                return Double.valueOf(article.getListino1());
            } else if (clientListinoIndex.equals(LISTINO_2)) {
                return Double.valueOf(article.getListino2());
            } else if (clientListinoIndex.equals(LISTINO_3)) {
                return Double.valueOf(article.getListino3());
            } else if (clientListinoIndex.equals(LISTINO_4)) {
                return Double.valueOf(article.getListino4());
            } else if (clientListinoIndex.equals(LISTINO_5)) {
                return Double.valueOf(article.getListino5());
            } else if (clientListinoIndex.equals(LISTINO_6)) {
                return Double.valueOf(article.getListino6());
            } else if (clientListinoIndex.equals(LISTINO_7)) {
                return Double.valueOf(article.getListino7());
            } else if (clientListinoIndex.equals(LISTINO_8)) {
                return Double.valueOf(article.getListino8());
            } else if (clientListinoIndex.equals(LISTINO_9)) {
                return Double.valueOf(article.getListino9());
            }
        }
        return Double.valueOf(article.getListino1());
    }
}
