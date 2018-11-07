package it.visionmobya.recyclerView.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.visionmobya.R;
import it.visionmobya.models.Vat;
import it.visionmobya.recyclerView.viewholders.IvaViewHolder;

public class IvaAdapter extends RecyclerView.Adapter<IvaViewHolder> {

    private Context context;
    private List<Vat> vatList;


    public IvaAdapter(Context context, List<Vat> vatList) {
        this.context = context;
        this.vatList = vatList;
    }


    @NonNull
    @Override
    public IvaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        IvaViewHolder ivaViewHolder = new IvaViewHolder(itemView);
        return ivaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IvaViewHolder holder, final int position) {
        holder.descrizione.setText(vatList.get(position).getDescrizioneIva());
        holder.valuePercentage.setText(vatList.get(position).getAliquotaInPercentuale());
        holder.ivaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return vatList.size();
    }

    public void setVatList(List<Vat> vatList) {
        this.vatList = vatList;
        this.notifyDataSetChanged();
    }
}
