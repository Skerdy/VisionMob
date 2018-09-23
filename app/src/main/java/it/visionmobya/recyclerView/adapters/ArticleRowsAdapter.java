package it.visionmobya.recyclerView.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.visionmobya.R;
import it.visionmobya.models.customModels.DocumentState;
import it.visionmobya.recyclerView.viewholders.ArticleRowsViewHolder;

public class ArticleRowsAdapter extends RecyclerView.Adapter<ArticleRowsViewHolder> {

    private ArrayList<DocumentState>documentStates;
    private Context context;

    public ArticleRowsAdapter(ArrayList<DocumentState> documentStates, Context context) {
        this.documentStates = documentStates;
        this.context = context;
    }

    @NonNull
    @Override
    public ArticleRowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_rows, parent, false);
        ArticleRowsViewHolder articleRowsViewHolder = new ArticleRowsViewHolder(itemView);
        return articleRowsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleRowsViewHolder holder, int position) {
        holder.articleName.setText(documentStates.get(position).getArticle().getDescrizione());
        holder.codeArticolo.setText(documentStates.get(position).getArticle().getCodiceArticolo());
        holder.unitMeasure.setText(documentStates.get(position).getArticle().getCodiceUnitaDiMisura());
        holder.quantita.setText(documentStates.get(position).getQuantita().toString());
        holder.totalFinal.setText(documentStates.get(position).getPrezzoTotaleArticle().toString());
        holder.rowNr.setText(""+(position+1));

    }

    @Override
    public int getItemCount() {
        return documentStates.size();
    }
}
