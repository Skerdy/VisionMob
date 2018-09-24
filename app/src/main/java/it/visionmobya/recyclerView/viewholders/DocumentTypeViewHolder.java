package it.visionmobya.recyclerView.viewholders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import it.visionmobya.R;
import it.visionmobya.listener.OnClientClickListener;
import it.visionmobya.listener.OnDocumentClickListener;


public class DocumentTypeViewHolder extends RecyclerView.ViewHolder {

    public TextView docType;
    public CardView docTypeCardView;


    public DocumentTypeViewHolder(View itemView) {
        super(itemView);
        docType = itemView.findViewById(R.id.docType);
        docTypeCardView = itemView.findViewById(R.id.document_type_cardview);
    }
}


