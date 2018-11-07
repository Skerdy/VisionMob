package it.visionmobya.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Article implements Serializable, Parcelable {

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
    private String codiceArticolo;
    private String descrizione;
    private String codiceUnitaDiMisura;
    private String codiceCategoria;
    private String listino1;
    private String Listino2;
    private String Listino3;
    private String Listino4;
    private String Listino5;
    private String Listino6;
    private String Listino7;
    private String Listino8;
    private String Listino9;
    private String codiceIvaVendite;
    private String PercentualeDiSconto1;
    private String PercentualeDiSconto2;
    private String PercentualeDiSconto3;
    private String alias;
    private String CodiceUnitàDiMisura1;
    private String CodiceUnitàDiMisura2;
    private String CodiceUnitàDiMisura3;
    private String FattorediConversione1;
    private String FattorediConversione2;
    private String FattorediConversione3;

    public Article(ArticleBuilder articleBuilder) {

        codiceArticolo = articleBuilder.codiceArticolo;
        descrizione = articleBuilder.descrizione;
        codiceUnitaDiMisura = articleBuilder.codiceUnitaDiMisura;
        codiceCategoria = articleBuilder.codiceCategoria;
        listino1 = articleBuilder.listino1;
        Listino2 = articleBuilder.Listino2;
        Listino3 = articleBuilder.Listino3;
        Listino4 = articleBuilder.Listino4;
        Listino5 = articleBuilder.Listino5;
        Listino6 = articleBuilder.Listino6;
        Listino7 = articleBuilder.Listino7;
        Listino8 = articleBuilder.Listino8;
        Listino9 = articleBuilder.Listino9;
        codiceIvaVendite = articleBuilder.codiceIvaVendite;
        PercentualeDiSconto1 = articleBuilder.PercentualeDiSconto1;
        PercentualeDiSconto2 = articleBuilder.PercentualeDiSconto2;
        PercentualeDiSconto3 = articleBuilder.PercentualeDiSconto3;
        alias = articleBuilder.alias;
        CodiceUnitàDiMisura1 = articleBuilder.CodiceUnitàDiMisura1;
        CodiceUnitàDiMisura2 = articleBuilder.CodiceUnitàDiMisura2;
        CodiceUnitàDiMisura3 = articleBuilder.CodiceUnitàDiMisura3;
        FattorediConversione1 = articleBuilder.FattorediConversione1;
        FattorediConversione2 = articleBuilder.FattorediConversione2;
        FattorediConversione3 = articleBuilder.FattorediConversione3;
    }

    protected Article(Parcel in) {
        codiceArticolo = in.readString();
        descrizione = in.readString();
        codiceUnitaDiMisura = in.readString();
        codiceCategoria = in.readString();
        listino1 = in.readString();
        Listino2 = in.readString();
        Listino3 = in.readString();
        Listino4 = in.readString();
        Listino5 = in.readString();
        Listino6 = in.readString();
        Listino7 = in.readString();
        Listino8 = in.readString();
        Listino9 = in.readString();
        codiceIvaVendite = in.readString();
        PercentualeDiSconto1 = in.readString();
        PercentualeDiSconto2 = in.readString();
        PercentualeDiSconto3 = in.readString();
        alias = in.readString();
        CodiceUnitàDiMisura1 = in.readString();
        CodiceUnitàDiMisura2 = in.readString();
        CodiceUnitàDiMisura3 = in.readString();
        FattorediConversione1 = in.readString();
        FattorediConversione2 = in.readString();
        FattorediConversione3 = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codiceArticolo);
        dest.writeString(descrizione);
        dest.writeString(codiceUnitaDiMisura);
        dest.writeString(codiceCategoria);
        dest.writeString(listino1);
        dest.writeString(Listino2);
        dest.writeString(Listino3);
        dest.writeString(Listino4);
        dest.writeString(Listino5);
        dest.writeString(Listino6);
        dest.writeString(Listino7);
        dest.writeString(Listino8);
        dest.writeString(Listino9);
        dest.writeString(codiceIvaVendite);
        dest.writeString(PercentualeDiSconto1);
        dest.writeString(PercentualeDiSconto2);
        dest.writeString(PercentualeDiSconto3);
        dest.writeString(alias);
        dest.writeString(CodiceUnitàDiMisura1);
        dest.writeString(CodiceUnitàDiMisura2);
        dest.writeString(CodiceUnitàDiMisura3);
        dest.writeString(FattorediConversione1);
        dest.writeString(FattorediConversione2);
        dest.writeString(FattorediConversione3);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String toCsvRecord() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(codiceArticolo).append(";")
                .append(descrizione).append(";")
                .append(codiceUnitaDiMisura).append(";")
                .append(codiceCategoria).append(";")
                .append(listino1).append(";")
                .append(Listino2).append(";")
                .append(Listino3).append(";")
                .append(Listino4).append(";")
                .append(Listino5).append(";")
                .append(Listino6).append(";")
                .append(Listino7).append(";")
                .append(Listino8).append(";")
                .append(Listino9).append(";")
                .append(codiceIvaVendite).append(";")
                .append(PercentualeDiSconto1).append(";")
                .append(PercentualeDiSconto2).append(";")
                .append(PercentualeDiSconto3).append(";")
                .append(alias).append(";")
                .append(CodiceUnitàDiMisura1).append(";")
                .append(CodiceUnitàDiMisura2).append(";")
                .append(CodiceUnitàDiMisura3).append(";")
                .append(FattorediConversione1).append(";")
                .append(FattorediConversione2).append(";")
                .append(FattorediConversione3);
        return stringBuilder.toString();

    }

    public String getCodiceArticolo() {
        return codiceArticolo;
    }

    public void setCodiceArticolo(String codiceArticolo) {
        this.codiceArticolo = codiceArticolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodiceUnitaDiMisura() {
        return codiceUnitaDiMisura;
    }

    public void setCodiceUnitaDiMisura(String codiceUnitaDiMisura) {
        this.codiceUnitaDiMisura = codiceUnitaDiMisura;
    }

    public String getCodiceCategoria() {
        return codiceCategoria;
    }

    public void setCodiceCategoria(String codiceCategoria) {
        this.codiceCategoria = codiceCategoria;
    }

    public String getListino1() {
        return listino1;
    }

    public void setListino1(String listino1) {
        this.listino1 = listino1;
    }

    public String getListino2() {
        return Listino2;
    }

    public void setListino2(String listino2) {
        Listino2 = listino2;
    }

    public String getListino3() {
        return Listino3;
    }

    public void setListino3(String listino3) {
        Listino3 = listino3;
    }

    public String getListino4() {
        return Listino4;
    }

    public void setListino4(String listino4) {
        Listino4 = listino4;
    }

    public String getListino5() {
        return Listino5;
    }

    public void setListino5(String listino5) {
        Listino5 = listino5;
    }

    public String getListino6() {
        return Listino6;
    }

    public void setListino6(String listino6) {
        Listino6 = listino6;
    }

    public String getListino7() {
        return Listino7;
    }

    public void setListino7(String listino7) {
        Listino7 = listino7;
    }

    public String getListino8() {
        return Listino8;
    }

    public void setListino8(String listino8) {
        Listino8 = listino8;
    }

    public String getListino9() {
        return Listino9;
    }

    public void setListino9(String listino9) {
        Listino9 = listino9;
    }

    public String getCodiceIvaVendite() {
        return codiceIvaVendite;
    }

    public void setCodiceIvaVendite(String codiceIvaVendite) {
        this.codiceIvaVendite = codiceIvaVendite;
    }

    public String getPercentualeDiSconto1() {
        return PercentualeDiSconto1;
    }

    public void setPercentualeDiSconto1(String percentualeDiSconto1) {
        PercentualeDiSconto1 = percentualeDiSconto1;
    }

    public String getPercentualeDiSconto2() {
        return PercentualeDiSconto2;
    }

    public void setPercentualeDiSconto2(String percentualeDiSconto2) {
        PercentualeDiSconto2 = percentualeDiSconto2;
    }

    public String getPercentualeDiSconto3() {
        return PercentualeDiSconto3;
    }

    public void setPercentualeDiSconto3(String percentualeDiSconto3) {
        PercentualeDiSconto3 = percentualeDiSconto3;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCodiceUnitàDiMisura1() {
        return CodiceUnitàDiMisura1;
    }

    public void setCodiceUnitàDiMisura1(String codiceUnitàDiMisura1) {
        CodiceUnitàDiMisura1 = codiceUnitàDiMisura1;
    }

    public String getCodiceUnitàDiMisura2() {
        return CodiceUnitàDiMisura2;
    }

    public void setCodiceUnitàDiMisura2(String codiceUnitàDiMisura2) {
        CodiceUnitàDiMisura2 = codiceUnitàDiMisura2;
    }

    public String getCodiceUnitàDiMisura3() {
        return CodiceUnitàDiMisura3;
    }

    public void setCodiceUnitàDiMisura3(String codiceUnitàDiMisura3) {
        CodiceUnitàDiMisura3 = codiceUnitàDiMisura3;
    }

    public String getFattorediConversione1() {
        return FattorediConversione1;
    }

    public void setFattorediConversione1(String fattorediConversione1) {
        FattorediConversione1 = fattorediConversione1;
    }

    public String getFattorediConversione2() {
        return FattorediConversione2;
    }

    public void setFattorediConversione2(String fattorediConversione2) {
        FattorediConversione2 = fattorediConversione2;
    }

    public String getFattorediConversione3() {
        return FattorediConversione3;
    }

    public void setFattorediConversione3(String fattorediConversione3) {
        FattorediConversione3 = fattorediConversione3;
    }

    @Override
    public String toString() {
        return "Article{" +
                "codiceArticolo='" + codiceArticolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", codiceUnitaDiMisura='" + codiceUnitaDiMisura + '\'' +
                ", codiceCategoria='" + codiceCategoria + '\'' +
                ", listino1='" + listino1 + '\'' +
                ", Listino2='" + Listino2 + '\'' +
                ", Listino3='" + Listino3 + '\'' +
                ", Listino4='" + Listino4 + '\'' +
                ", Listino5='" + Listino5 + '\'' +
                ", Listino6='" + Listino6 + '\'' +
                ", Listino7='" + Listino7 + '\'' +
                ", Listino8='" + Listino8 + '\'' +
                ", Listino9='" + Listino9 + '\'' +
                ", codiceIvaVendite='" + codiceIvaVendite + '\'' +
                ", PercentualeDiSconto1='" + PercentualeDiSconto1 + '\'' +
                ", PercentualeDiSconto2='" + PercentualeDiSconto2 + '\'' +
                ", PercentualeDiSconto3='" + PercentualeDiSconto3 + '\'' +
                ", alias='" + alias + '\'' +
                ", CodiceUnitàDiMisura1='" + CodiceUnitàDiMisura1 + '\'' +
                ", CodiceUnitàDiMisura2='" + CodiceUnitàDiMisura2 + '\'' +
                ", CodiceUnitàDiMisura3='" + CodiceUnitàDiMisura3 + '\'' +
                ", FattorediConversione1='" + FattorediConversione1 + '\'' +
                ", FattorediConversione2='" + FattorediConversione2 + '\'' +
                ", FattorediConversione3='" + FattorediConversione3 + '\'' +
                '}';
    }

    public static class ArticleBuilder {

        private final String codiceArticolo;
        private final String descrizione;
        private String codiceUnitaDiMisura = "";
        private String codiceCategoria = "";
        private String listino1 = "";
        private String Listino2 = "";
        private String Listino3 = "";
        private String Listino4 = "";
        private String Listino5 = "";
        private String Listino6 = "";
        private String Listino7 = "";
        private String Listino8 = "";
        private String Listino9 = "";
        private String codiceIvaVendite = "";
        private String PercentualeDiSconto1 = "";
        private String PercentualeDiSconto2 = "";
        private String PercentualeDiSconto3 = "";
        private String alias = "";
        private String CodiceUnitàDiMisura1 = "";
        private String CodiceUnitàDiMisura2 = "";
        private String CodiceUnitàDiMisura3 = "";
        private String FattorediConversione1 = "";
        private String FattorediConversione2 = "";
        private String FattorediConversione3 = "";

        public ArticleBuilder(String descrizione, String codiceArticolo) {
            this.codiceArticolo = codiceArticolo;
            this.descrizione = descrizione;
        }

        public ArticleBuilder withCodiceUnitaDiMisura(String codiceUnitaDiMisura) {
            this.codiceUnitaDiMisura = codiceUnitaDiMisura;
            return this;
        }

        public ArticleBuilder withCodiceCategoria(String codiceCategoria) {
            this.codiceCategoria = codiceCategoria;
            return this;
        }

        public ArticleBuilder withListino1(String listino1) {
            this.listino1 = listino1;
            return this;
        }

        public ArticleBuilder withListino2(String listino2) {
            this.Listino2 = listino2;
            return this;
        }

        public ArticleBuilder withListino3(String listino3) {
            this.Listino3 = listino3;
            return this;
        }

        public ArticleBuilder withListino4(String listino4) {
            this.Listino4 = listino4;
            return this;
        }

        public ArticleBuilder withListino5(String listino5) {
            this.Listino5 = listino5;
            return this;
        }

        public ArticleBuilder withListino6(String listino6) {
            this.Listino6 = listino6;
            return this;
        }

        public ArticleBuilder withListino7(String listino7) {
            this.Listino7 = listino7;
            return this;
        }

        public ArticleBuilder withListino8(String listino8) {
            this.Listino8 = listino8;
            return this;
        }

        public ArticleBuilder withListino9(String listino9) {
            this.Listino9 = listino9;
            return this;
        }

        public ArticleBuilder withCodiceIvaVendite(String codiceIvaVendite) {
            this.codiceIvaVendite = codiceIvaVendite;
            return this;
        }

        public ArticleBuilder withPercentualeDiSconto1(String percentualeDiSconto1) {
            this.PercentualeDiSconto1 = percentualeDiSconto1;
            return this;
        }

        public ArticleBuilder withPercentualeDiSconto2(String percentualeDiSconto2) {
            this.PercentualeDiSconto2 = percentualeDiSconto2;
            return this;
        }

        public ArticleBuilder withPercentualeDiSconto3(String percentualeDiSconto3) {
            this.PercentualeDiSconto3 = percentualeDiSconto3;
            return this;
        }

        public ArticleBuilder withAlias(String alias) {
            this.alias = alias;
            return this;
        }

        public ArticleBuilder withCodiceUnitaDiMisura1(String codiceUnitaDiMisura1) {
            this.CodiceUnitàDiMisura1 = codiceUnitaDiMisura1;
            return this;
        }

        public ArticleBuilder withCodiceUnitaDiMisura2(String codiceUnitàDiMisura2) {
            this.CodiceUnitàDiMisura2 = codiceUnitàDiMisura2;
            return this;
        }

        public ArticleBuilder withCodiceUnitaDiMisura3(String codiceUnitaDiMisura3) {
            this.CodiceUnitàDiMisura3 = codiceUnitaDiMisura3;
            return this;
        }

        public ArticleBuilder withFattorediConversione1(String fattorediConversione1) {
            this.FattorediConversione1 = fattorediConversione1;
            return this;
        }

        public ArticleBuilder withFattorediConversione2(String fattorediConversione2) {
            this.FattorediConversione2 = fattorediConversione2;
            return this;
        }

        public ArticleBuilder withFattorediConversione3(String fattorediConversione3) {
            this.FattorediConversione3 = fattorediConversione3;
            return this;
        }

        public Article build() {
            return new Article(this);
        }

    }
}
