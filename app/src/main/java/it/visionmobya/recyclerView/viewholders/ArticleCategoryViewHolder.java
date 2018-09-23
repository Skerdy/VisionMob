package it.visionmobya.recyclerView.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import it.visionmobya.R;
import it.visionmobya.listener.OnClickListenerItem;

public class ArticleCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView article;
    OnClickListenerItem onClickListenerItem;
    public ArticleCategoryViewHolder(View itemView,OnClickListenerItem onClickListenerItem) {
        super(itemView);
        this.onClickListenerItem = onClickListenerItem;
        article = itemView.findViewById(R.id.article);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onClickListenerItem.onClickClient(v,getAdapterPosition());
    }
}
