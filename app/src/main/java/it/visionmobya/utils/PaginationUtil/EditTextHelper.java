package it.visionmobya.utils.PaginationUtil;

import android.widget.EditText;

public class EditTextHelper {

    public static boolean isEditTextModified(EditText editText){
        if(editText.getText()!=null && editText.getText().toString()!=null && !editText.getText().toString().isEmpty()) {
            return true;
        }
        else
            return false;
    }
}
