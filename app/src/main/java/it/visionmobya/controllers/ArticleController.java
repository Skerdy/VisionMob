package it.visionmobya.controllers;

import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.models.Article;

public class ArticleController {

    private ArticleController(){

    }

    public static List<Article> getAllArticles(){
        return VisionFileManager.getInstance().getArticles();
    }

    public static Article getArticleWithId(String id){
        if(VisionFileManager.getInstance().getArticles()!=null) {
            for (Article article : VisionFileManager.getInstance().getArticles()) {
                if (article.getCodiceArticolo().equals(id))
                    return article;
            }
            return null;
        }
        else return null;
    }
}
