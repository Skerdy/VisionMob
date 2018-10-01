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
    private Double quantita;
    private Double imponibile;
    private Double scontoValue;
    private Double prezzoTotaleArticle;
    private Double prezzoUnitario;
    private Integer numerArticolo;
    private Double scontoPercentuale;
    private Double ivaValueUponPrice;
    @Builder.Default
    private boolean bindDirectly = false;

    public DocumentState(){

    }

    protected DocumentState(Parcel in) {
        article = in.readParcelable(Article.class.getClassLoader());
        descrizione = in.readString();
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
        if (in.readByte() == 0) {
            ivaValueUponPrice = null;
        } else {
            ivaValueUponPrice = in.readDouble();
        }
        bindDirectly = in.readByte() != 0;
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(article, i);
        parcel.writeString(descrizione);
        if (quantita == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(quantita);
        }
        if (imponibile == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(imponibile);
        }
        if (scontoValue == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(scontoValue);
        }
        if (prezzoTotaleArticle == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(prezzoTotaleArticle);
        }
        if (prezzoUnitario == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(prezzoUnitario);
        }
        if (numerArticolo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(numerArticolo);
        }
        if (scontoPercentuale == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(scontoPercentuale);
        }
        if (ivaValueUponPrice == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(ivaValueUponPrice);
        }
        parcel.writeByte((byte) (bindDirectly ? 1 : 0));
    }
}
