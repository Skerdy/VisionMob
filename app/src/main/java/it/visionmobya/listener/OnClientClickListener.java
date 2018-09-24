package it.visionmobya.listener;

import android.view.View;

import it.visionmobya.models.Client;

public interface OnClientClickListener {
    void onClickClient(Client client, int position);
}
