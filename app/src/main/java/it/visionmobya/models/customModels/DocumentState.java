package it.visionmobya.models.customModels;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import it.visionmobya.models.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class DocumentState implements Serializable, Parcelable {

    private Article article;
    private String descrizione;
    private String codiceIva;
    private Double quantita;
    private Double imponibile;
    private Double scontoValue;
    private Double prezzoTotaleArticle;
    private Double prezzoUnitario;
    private Integer numerArticolo;
    private Double scontoPercentuale;

    public DocumentState(){

    }



    protected DocumentState(Parcel in) {
        descrizione = in.readString();
        codiceIva = in.readString();
        if (in.readByte() == 0) {
            quantita = null;
        } else {
            quantita = in.readDouble();
        }
        if (in.readByte() == 0) {
            imponibile = null;
        } else {
            imponibile = in.readDouble();
        }
        if (in.readByte() == 0) {
            scontoValue = null;
        } else {
            scontoValue = in.readDouble();
        }
        if (in.readByte() == 0) {
            prezzoTotaleArticle = null;
        } else {
            prezzoTotaleArticle = in.readDouble();
        }
        if (in.readByte() == 0) {
            prezzoUnitario = null;
        } else {
            prezzoUnitario = in.readDouble();
        }
        if (in.readByte() == 0) {
            numerArticolo = null;
        } else {
            numerArticolo = in.readInt();
        }
        if (in.readByte() == 0) {
            scontoPercentuale = null;
        } else {
            scontoPercentuale = in.readDouble();
        }
    }

    public static final Creator<DocumentState> CREATOR = new Creator<DocumentState>() {
        @Override
        public DocumentState createFromParcel(Parcel in) {
            return new DocumentState(in);
        }

        @Override
        public DocumentState[] newArray(int size) {
            return new DocumentState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descrizione);
        dest.writeString(codiceIva);
        if (quantita == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(quantita);
        }
        if (imponibile == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(imponibile);
        }
        if (scontoValue == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(scontoValue);
        }
        if (prezzoTotaleArticle == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(prezzoTotaleArticle);
        }
        if (prezzoUnitario == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(prezzoUnitario);
        }
        if (numerArticolo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numerArticolo);
        }
        if (scontoPercentuale == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(scontoPercentuale);
        }
    }
}
