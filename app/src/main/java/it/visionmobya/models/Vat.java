package it.visionmobya.models;

public class Vat {

    private String codiceIva;
    private String descrizioneIva;
    private String AliquotaInPercentuale;
    private String CampoFisso;

    public String toCsvRecord() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(codiceIva).append(";")
                .append(descrizioneIva).append(";")
                .append(AliquotaInPercentuale).append(";")
                .append(CampoFisso).append(";");
        return stringBuilder.toString();
    }

    public String getCodiceIva() {
        return codiceIva;
    }

    public void setCodiceIva(String codiceIva) {
        this.codiceIva = codiceIva;
    }

    public String getDescrizioneIva() {
        return descrizioneIva;
    }

    public void setDescrizioneIva(String descrizioneIva) {
        this.descrizioneIva = descrizioneIva;
    }

    public String getAliquotaInPercentuale() {
        return AliquotaInPercentuale;
    }

    public void setAliquotaInPercentuale(String aliquotaInPercentuale) {
        AliquotaInPercentuale = aliquotaInPercentuale;
    }

    public String getCampoFisso() {
        return CampoFisso;
    }

    public void setCampoFisso(String campoFisso) {
        CampoFisso = campoFisso;
    }
}
