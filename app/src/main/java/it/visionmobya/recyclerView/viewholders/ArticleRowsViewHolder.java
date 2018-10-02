package it.visionmobya.recyclerView.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.visionmobya.R;

public class ArticleRowsViewHolder extends RecyclerView.ViewHolder {
    public TextView articleName, codeArticolo, unitMeasure, quantita, totalFinal, rowNr;
    public LinearLayout root_layout;
    public ImageView delete;

    public ArticleRowsViewHolder(View itemView) {
        super(itemView);
        articleName = itemView.findViewById(R.id.articleName);
        codeArticolo = itemView.findViewById(R.id.codeArticolo);
        unitMeasure = itemView.findViewById(R.id.unitMeasure);
        quantita = itemView.findViewById(R.id.quantita);
        totalFinal = itemView.findViewById(R.id.totalFinal);
        rowNr = itemView.findViewById(R.id.rowNr);
        root_layout = itemView.findViewById(R.id.root_layout);
        delete = itemView.findViewById(R.id.delete);
    }
}
