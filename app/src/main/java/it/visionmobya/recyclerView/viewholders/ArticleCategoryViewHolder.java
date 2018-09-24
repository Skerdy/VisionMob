package it.visionmobya.recyclerView.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import it.visionmobya.R;


public class ArticleCategoryViewHolder extends RecyclerView.ViewHolder  {

    public TextView article;

    public ArticleCategoryViewHolder(View itemView) {
        super(itemView);
        article = itemView.findViewById(R.id.article);
    }

}
