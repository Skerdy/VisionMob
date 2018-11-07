package it.visionmobya.recyclerView.diffUtil;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import it.visionmobya.models.Article;

public class ArticleDiffUtil extends DiffUtil.Callback {


    public List<Article> oldArticles;
    public List<Article> newArticles;

    public ArticleDiffUtil(List<Article> newArticles, List<Article> oldArticles) {
        this.newArticles = newArticles;
        this.oldArticles = oldArticles;
    }

    @Override
    public int getOldListSize() {
        return oldArticles.size();
    }

    @Override
    public int getNewListSize() {
        return newArticles.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldArticles.get(oldItemPosition).getCodiceArticolo().equals(newArticles.get(newItemPosition).getCodiceArticolo());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldArticles.get(oldItemPosition).equals(newArticles.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
