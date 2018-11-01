package it.visionmobya.listener;

import java.util.List;

import it.visionmobya.models.customModels.DocumentState;

public interface OnSaveAndPrintButtonListener {
    void onSaveAndPrintClicked(List<DocumentState> documentStates);
}
