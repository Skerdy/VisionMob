package it.visionmobya.recyclerView.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.visionmobya.R;

public class ClientListViewHolder extends RecyclerView.ViewHolder {
    public TextView nameClient,codeClient,address;
    public LinearLayout clientLayout;

    public ClientListViewHolder(View itemView) {
        super(itemView);
        nameClient = itemView.findViewById(R.id.nameClient);
        codeClient = itemView.findViewById(R.id.codeClient);
        address = itemView.findViewById(R.id.address);
        clientLayout = itemView.findViewById(R.id.client_item_layout);
    }
}
