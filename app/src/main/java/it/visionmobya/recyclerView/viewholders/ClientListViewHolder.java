package it.visionmobya.recyclerView.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import it.visionmobya.R;
import it.visionmobya.listener.OnClickListenerItem;

public class ClientListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView nameClient,codeClient,address;
    OnClickListenerItem onClickListener;
    public ClientListViewHolder(View itemView,OnClickListenerItem onClickListener) {
        super(itemView);
        this.onClickListener = onClickListener;
        nameClient = itemView.findViewById(R.id.nameClient);
        codeClient = itemView.findViewById(R.id.codeClient);
        address = itemView.findViewById(R.id.address);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onClickListener.onClickClient(v,getAdapterPosition());
    }
}
