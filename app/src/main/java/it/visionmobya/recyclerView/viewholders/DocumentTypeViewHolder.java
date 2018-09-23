package it.visionmobya.recyclerView.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import it.visionmobya.R;
import it.visionmobya.listener.OnClickListenerItem;

public class DocumentTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

     public TextView docType;
     OnClickListenerItem onClickListenerItem;

    public DocumentTypeViewHolder(View itemView,OnClickListenerItem onClickListenerItem) {
        super(itemView);
        this.onClickListenerItem = onClickListenerItem;

        docType = itemView.findViewById(R.id.docType);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onClickListenerItem.onClickClient(v,getAdapterPosition());
    }
}
