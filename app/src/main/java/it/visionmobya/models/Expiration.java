package it.visionmobya.models;

public class Expiration {

    private String idScadenza;
    private String dataFattura;
    private String numeroFattura;
    private String dataScadenza;
    private String codiceCliente;
    private String importoSingolaScadenza;
    private String importoTotaleFattura;
    private String codiceDocumento;

    public String toCsvRecord() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(idScadenza).append(";")
                .append(dataFattura).append(";")
                .append(numeroFattura).append(";")
                .append(dataScadenza).append(";")
                .append(codiceCliente).append(";")
                .append(importoSingolaScadenza).append(";")
                .append(importoTotaleFattura).append(";")
                .append(codiceDocumento);
        return stringBuilder.toString();
    }

    public String getIdScadenza() {
        return idScadenza;
    }

    public void setIdScadenza(String idScadenza) {
        this.idScadenza = idScadenza;
    }

    public String getDataFattura() {
        return dataFattura;
    }

    public void setDataFattura(String dataFattura) {
        this.dataFattura = dataFattura;
    }

    public String getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(String numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public String getCodiceCliente() {
        return codiceCliente;
    }

    public void setCodiceCliente(String codiceCliente) {
        this.codiceCliente = codiceCliente;
    }

    public String getImportoSingolaScadenza() {
        return importoSingolaScadenza;
    }

    public void setImportoSingolaScadenza(String importoSingolaScadenza) {
        this.importoSingolaScadenza = importoSingolaScadenza;
    }

    public String getImportoTotaleFattura() {
        return importoTotaleFattura;
    }

    public void setImportoTotaleFattura(String importoTotaleFattura) {
        this.importoTotaleFattura = importoTotaleFattura;
    }

    public String getCodiceDocumento() {
        return codiceDocumento;
    }

    public void setCodiceDocumento(String codiceDocumento) {
        this.codiceDocumento = codiceDocumento;
    }
}
