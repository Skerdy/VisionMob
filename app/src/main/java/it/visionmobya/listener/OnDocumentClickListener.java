package it.visionmobya.listener;

import it.visionmobya.models.Client;
import it.visionmobya.models.DocumentCategory;

public interface OnDocumentClickListener {
    void onClickDocument(DocumentCategory documentCategory, int position);
}
