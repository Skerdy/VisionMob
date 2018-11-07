package it.visionmobya.recyclerView.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.visionmobya.R;
import it.visionmobya.listener.OnArticleCategoryClickListener;
import it.visionmobya.models.ArticleCategory;
import it.visionmobya.recyclerView.viewholders.ArticleCategoryViewHolder;

public class ArticleCategoryAdapter extends RecyclerView.Adapter<ArticleCategoryViewHolder> {

    private List<ArticleCategory> articleCategories;
    private OnArticleCategoryClickListener onArticleCategoryClickListener;

    public ArticleCategoryAdapter(List<ArticleCategory> articleCategories, OnArticleCategoryClickListener onArticleCategoryClickListener) {
        this.articleCategories = articleCategories;
        this.onArticleCategoryClickListener = onArticleCategoryClickListener;
    }

    @NonNull
    @Override
    public ArticleCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_category, parent, false);
        ArticleCategoryViewHolder clientListViewHolder = new ArticleCategoryViewHolder(itemView);
        return clientListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleCategoryViewHolder holder, final int position) {
        holder.article.setText(articleCategories.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onArticleCategoryClickListener.onArticleCategoryClicked(articleCategories.get(position), position);
            }
        });
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
