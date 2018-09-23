package it.visionmobya.models;

public class DocumentCategory {

    private String codiceDocumento;
    private String descrizioneDocumento;
    private String tipoDocumento;
    private String conttatoreDocumento;

    public String getCodiceDocumento() {
        return codiceDocumento;
    }


    public String toCsvRecord(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(codiceDocumento).append(";")
                .append(descrizioneDocumento).append(";")
                .append(tipoDocumento).append(";")
                .append(conttatoreDocumento);
        return stringBuilder.toString();
    }
    public void setCodiceDocumento(String codiceDocumento) {
        this.codiceDocumento = codiceDocumento;
    }

    public String getDescrizioneDocumento() {
        return descrizioneDocumento;
    }

    public void setDescrizioneDocumento(String descrizioneDocumento) {
        this.descrizioneDocumento = descrizioneDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getConttatoreDocumento() {
        return conttatoreDocumento;
    }

    public void setConttatoreDocumento(String conttatoreDocumento) {
        this.conttatoreDocumento = conttatoreDocumento;
    }
}
