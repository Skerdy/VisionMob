package it.visionmobya.recyclerView.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.visionmobya.R;

public class IvaViewHolder extends RecyclerView.ViewHolder {

    public TextView descrizione, valuePercentage;
    public LinearLayout ivaItem;

    public IvaViewHolder(View itemView) {
        super(itemView);
        descrizione = itemView.findViewById(R.id.articleCode);
        valuePercentage = itemView.findViewById(R.id.articleDescription);
        ivaItem = itemView.findViewById(R.id.article_item);
    }

}
