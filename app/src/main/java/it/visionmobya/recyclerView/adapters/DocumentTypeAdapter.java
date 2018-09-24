package it.visionmobya.recyclerView.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.visionmobya.R;
import it.visionmobya.listener.OnClientClickListener;
import it.visionmobya.listener.OnDocumentClickListener;
import it.visionmobya.models.DocumentCategory;
import it.visionmobya.recyclerView.viewholders.DocumentTypeViewHolder;

public class DocumentTypeAdapter extends RecyclerView.Adapter<DocumentTypeViewHolder> {

    private List<DocumentCategory> documentCategories;
    private OnDocumentClickListener onDocumentClickListener;

    public DocumentTypeAdapter(List<DocumentCategory> documentCategories, OnDocumentClickListener onDocumentClickListener){
        this.documentCategories = documentCategories;
        this.onDocumentClickListener = onDocumentClickListener;
    }

    @NonNull
    @Override
    public DocumentTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_docana, parent, false);
        DocumentTypeViewHolder clientListViewHolder = new DocumentTypeViewHolder(itemView);
        return clientListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentTypeViewHolder holder, final int position) {
        holder.docType.setText(documentCategories.get(position).getDescrizioneDocumento());
        holder.docTypeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDocumentClickListener.onClickDocument(documentCategories.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return documentCategories.size();
    }

    public void setDocumentCategories(List<DocumentCategory> documentCategories) {
        this.documentCategories = documentCategories;
        this.notifyDataSetChanged();
    }
}
