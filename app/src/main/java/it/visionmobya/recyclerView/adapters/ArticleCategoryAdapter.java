package it.visionmobya.recyclerView.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import it.visionmobya.R;
import it.visionmobya.listener.OnArticleClickListener;
import it.visionmobya.listener.OnClientClickListener;
import it.visionmobya.models.ArticleCategory;
import it.visionmobya.recyclerView.viewholders.ArticleCategoryViewHolder;

public class ArticleCategoryAdapter extends RecyclerView.Adapter<ArticleCategoryViewHolder> {

    private List<ArticleCategory> articleCategories;
    private OnArticleClickListener onArticleClickListener;

    public ArticleCategoryAdapter(List<ArticleCategory> articleCategories, OnArticleClickListener onArticleClickListener) {
        this.articleCategories = articleCategories;
        this.onArticleClickListener = onArticleClickListener;
    }

    @NonNull
    @Override
    public ArticleCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_category, parent, false);
        ArticleCategoryViewHolder clientListViewHolder = new ArticleCategoryViewHolder(itemView);
        return clientListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleCategoryViewHolder holder, int position) {
        holder.article.setText(articleCategories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return articleCategories.size();
    }

    public void setArticleCategories(List<ArticleCategory> articleCategories) {
        this.articleCategories = articleCategories;
        this.notifyDataSetChanged();
    }
}
