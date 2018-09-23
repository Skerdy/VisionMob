package it.visionmobya.recyclerView.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.visionmobya.R;
import it.visionmobya.listener.OnClickListenerItem;
import it.visionmobya.models.DocumentCategory;
import it.visionmobya.recyclerView.viewholders.DocumentTypeViewHolder;

public class DocumentTypeAdapter extends RecyclerView.Adapter<DocumentTypeViewHolder> {

    List<DocumentCategory> documentCategories;
    OnClickListenerItem onClickListenerItem;

    public DocumentTypeAdapter(List<DocumentCategory> documentCategories, OnClickListenerItem onClickListenerItem){
        this.documentCategories = documentCategories;
        this.onClickListenerItem = onClickListenerItem;
    }

    @NonNull
    @Override
    public DocumentTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_docana, parent, false);
        DocumentTypeViewHolder clientListViewHolder = new DocumentTypeViewHolder(itemView,onClickListenerItem);
        return clientListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentTypeViewHolder holder, int position) {
        holder.docType.setText(documentCategories.get(position).getDescrizioneDocumento());
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
