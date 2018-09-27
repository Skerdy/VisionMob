package it.visionmobya.listener;

import it.visionmobya.models.ArticleCategory;

public interface OnArticleCategoryClickListener {

    void onArticleCategoryClicked(ArticleCategory article, int position);
}
