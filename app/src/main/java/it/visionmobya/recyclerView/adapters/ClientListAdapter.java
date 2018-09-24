package it.visionmobya.recyclerView.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import it.visionmobya.R;
import it.visionmobya.listener.OnClientClickListener;
import it.visionmobya.models.Client;
import it.visionmobya.recyclerView.viewholders.ClientListViewHolder;


public class ClientListAdapter extends RecyclerView.Adapter<ClientListViewHolder> implements Filterable {

    private OnClientClickListener onClientClickListener;
    private Context context;
    private List<Client> clients;
    private List<Client> filteredClients;
    private List<Client> permanentClients;


    public ClientListAdapter(OnClientClickListener onClientClickListener, Context context, List<Client> clients) {
        this.onClientClickListener = onClientClickListener;
        this.context = context;
        this.clients = clients;
        this.permanentClients = clients;
    }

    @NonNull
    @Override
    public ClientListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client_list, parent, false);
        ClientListViewHolder clientListViewHolder = new ClientListViewHolder(itemView);
        return clientListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientListViewHolder holder, final int position) {
        holder.nameClient.setText(clients.get(position).getRagioneSociale());
        holder.codeClient.setText(clients.get(position).getCodiceCliente());
        holder.address.setText(clients.get(position).getIndirizzo());
        holder.clientLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClientClickListener.onClickClient(clients.get(position), position);
            }
        });
    }

    public void setClients(List<Client> clients ) {
        this.clients = clients;
        this.permanentClients = clients;
        this.notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return clients.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.d("Filter", "filter i ri me string : " + charSequence.toString());
                FilterResults results = new FilterResults();
                if (charSequence != null && charSequence.length() > 0) {
                    charSequence = charSequence.toString().toUpperCase();
                     filteredClients  = new ArrayList<>();
                    for (int i = 0; i < permanentClients.size(); i++) {
                        if (permanentClients.get(i).getRagioneSociale().toUpperCase().contains(charSequence)) {
                            filteredClients.add(permanentClients.get(i));
                        }else if (permanentClients.get(i).getIndirizzo().toUpperCase().contains(charSequence)) {
                            filteredClients.add(permanentClients.get(i));
                        }else if (permanentClients.get(i).getCodiceCliente().toUpperCase().contains(charSequence)) {
                            filteredClients.add(permanentClients.get(i));
                        }
                    }
                    results.count = filteredClients.size();
                    results.values = filteredClients;
                } else {
                    results.count = permanentClients.size();
                    results.values = permanentClients;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                clients = (ArrayList<Client>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
