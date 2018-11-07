package it.visionmobya.recyclerView.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.visionmobya.R;
import it.visionmobya.activities.OrdineClienteActivity;
import it.visionmobya.models.customModels.DocumentState;
import it.visionmobya.recyclerView.viewholders.ArticleRowsViewHolder;

public class ArticleRowsAdapter extends RecyclerView.Adapter<ArticleRowsViewHolder> {

    private ArrayList<DocumentState> documentStates;
    private ArrayList<DocumentState> eligibleDocumentStates;
    private Context context;

    public ArticleRowsAdapter(ArrayList<DocumentState> documentStates, Context context) {
        this.documentStates = documentStates;
        this.eligibleDocumentStates = new ArrayList<>();
        for (DocumentState documentState : documentStates) {
            if (documentState.isBindDirectly() && documentState.getArticle() != null) {
                eligibleDocumentStates.add(documentState);
            }
        }
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
    public void onBindViewHolder(@NonNull ArticleRowsViewHolder holder, final int position) {
        holder.articleName.setText(eligibleDocumentStates.get(position).getArticle().getDescrizione());
        holder.codeArticolo.setText(eligibleDocumentStates.get(position).getArticle().getCodiceArticolo());
        holder.unitMeasure.setText(eligibleDocumentStates.get(position).getArticle().getCodiceUnitaDiMisura());
        holder.quantita.setText(eligibleDocumentStates.get(position).getQuantita().toString());
        holder.totalFinal.setText(eligibleDocumentStates.get(position).getPrezzoTotaleArticle().toString());
        holder.rowNr.setText("" + (position + 1));

        holder.root_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ((OrdineClienteActivity) context).showArticleDetailsDialog(eligibleDocumentStates.get(position));
                return true;
            }
        });

        holder.root_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((OrdineClienteActivity) context).showParticularArticleRowFragment(position);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eligibleDocumentStates.remove(position);
                ((OrdineClienteActivity) context).deleteParticularArticleRowFragment(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return eligibleDocumentStates.size();
    }
}
