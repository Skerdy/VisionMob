package it.visionmobya.models;

public class History {

    private String codiceDocumento;
    private String numeroDocumento;
    private String dataDocumento;
    private String codiceCliente;
    private String codiceCategoriaArticolo;
    private String codiceArticolo;
    private String quantità;
    private String prezzoNetto;

    public String getCodiceDocumento() {
        return codiceDocumento;
    }

    public String toCsvRecord() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(codiceDocumento).append(";")
                .append(numeroDocumento).append(";")
                .append(dataDocumento).append(";")
                .append(codiceCliente).append(";")
                .append(codiceCategoriaArticolo).append(";")
                .append(codiceArticolo).append(";")
                .append(quantità).append(";")
                .append(prezzoNetto);
        return stringBuilder.toString();
    }

    public void setCodiceDocumento(String codiceDocumento) {
        this.codiceDocumento = codiceDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(String dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public String getCodiceCliente() {
        return codiceCliente;
    }

    public void setCodiceCliente(String codiceCliente) {
        this.codiceCliente = codiceCliente;
    }

    public String getCodiceCategoriaArticolo() {
        return codiceCategoriaArticolo;
    }

    public void setCodiceCategoriaArticolo(String codiceCategoriaArticolo) {
        this.codiceCategoriaArticolo = codiceCategoriaArticolo;
    }

    public String getCodiceArticolo() {
        return codiceArticolo;
    }

    public void setCodiceArticolo(String codiceArticolo) {
        this.codiceArticolo = codiceArticolo;
    }

    public String getQuantità() {
        return quantità;
    }

    public void setQuantità(String quantità) {
        this.quantità = quantità;
    }

    public String getPrezzoNetto() {
        return prezzoNetto;
    }

    public void setPrezzoNetto(String prezzoNetto) {
        this.prezzoNetto = prezzoNetto;
    }
}
