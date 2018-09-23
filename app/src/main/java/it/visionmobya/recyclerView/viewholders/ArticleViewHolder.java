package it.visionmobya.recyclerView.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.visionmobya.R;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    public TextView articleCode, articleDescription;
    public LinearLayout articleItem;

    public ArticleViewHolder(View itemView) {

        super(itemView);
        articleItem = itemView.findViewById(R.id.article_item);
        articleCode = itemView.findViewById(R.id.articleCode);
        articleDescription = itemView.findViewById(R.id.articleDescription);
    }
}
