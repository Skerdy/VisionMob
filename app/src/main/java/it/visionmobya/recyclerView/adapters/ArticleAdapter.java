package it.visionmobya.recyclerView.adapters;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import it.visionmobya.R;
import it.visionmobya.listener.OnArticleClickListener;
import it.visionmobya.models.Article;
import it.visionmobya.models.Client;
import it.visionmobya.recyclerView.diffUtil.ArticleDiffUtil;
import it.visionmobya.recyclerView.viewholders.ArticleViewHolder;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder>  implements Filterable {

    private List<Article> articles;
    private List<Article> filteredArticles;
    private List<Article> permanentArticles;
    private OnArticleClickListener onArticleClickListener;


    public ArticleAdapter(List<Article> articles, OnArticleClickListener onArticleClickListener) {
        this.permanentArticles = articles;
        this.articles = articles;
        this.onArticleClickListener = onArticleClickListener;

    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        ArticleViewHolder clientListViewHolder = new ArticleViewHolder(itemView);
        return clientListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, final int position) {
        holder.articleCode.setText(articles.get(position).getCodiceArticolo());
        holder.articleDescription.setText(articles.get(position).getDescrizione());
        holder.articleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onArticleClickListener!=null){
                    onArticleClickListener.onArticleClicked(articles.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticle(List<Article> article) {
        this.articles =article;
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ArticleDiffUtil(this.articles, article));
        diffResult.dispatchUpdatesTo(this);
        this.notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                if (charSequence != null && charSequence.length() > 0) {
                    charSequence = charSequence.toString().toUpperCase();
                    filteredArticles  = new ArrayList<>();
                    for (int i = 0; i < permanentArticles.size(); i++) {
                        if (permanentArticles.get(i).getCodiceArticolo().toUpperCase().contains(charSequence)) {
                            filteredArticles.add(permanentArticles.get(i));
                        }else if (permanentArticles.get(i).getDescrizione().toUpperCase().contains(charSequence)) {
                            filteredArticles.add(permanentArticles.get(i));
                        }
                    }
                    results.count = filteredArticles.size();
                    results.values = filteredArticles;
                } else {
                    results.count = permanentArticles.size();
                    results.values = permanentArticles;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                articles = (ArrayList<Article>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
