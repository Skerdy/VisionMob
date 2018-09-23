package it.visionmobya.models;

public class Listino {

    private String idListino;
    private String codiceArticolo;
    private String codiceCliente;
    private String codiceDestinazioneDiversa;
    private String codiceCategoriaArticolo;
    private String prezzo;
    private String sconto;
    private String dataInizioListino;
    private String dataFineListino;

    public String toCsvRecord() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(idListino).append(";")
                .append(codiceArticolo).append(";")
                .append(codiceCliente).append(";")
                .append(codiceDestinazioneDiversa).append(";")
                .append(codiceCategoriaArticolo).append(";")
                .append(prezzo).append(";")
                .append(sconto).append(";")
                .append(dataInizioListino).append(";")
                .append(dataFineListino);
        return stringBuilder.toString();
    }

    public Listino(){

    }

    public Listino(String idListino, String codiceArticolo, String codiceCliente, String codiceDestinazioneDiversa, String codiceCategoriaArticolo, String prezzo, String sconto, String dataInizioListino, String dataFineListino) {
        this.idListino = idListino;
        this.codiceArticolo = codiceArticolo;
        this.codiceCliente = codiceCliente;
        this.codiceDestinazioneDiversa = codiceDestinazioneDiversa;
        this.codiceCategoriaArticolo = codiceCategoriaArticolo;
        this.prezzo = prezzo;
        this.sconto = sconto;
        this.dataInizioListino = dataInizioListino;
        this.dataFineListino = dataFineListino;
    }

    public String getIdListino() {
        return idListino;
    }

    public void setIdListino(String idListino) {
        this.idListino = idListino;
    }

    public String getCodiceArticolo() {
        return codiceArticolo;
    }

    public void setCodiceArticolo(String codiceArticolo) {
        this.codiceArticolo = codiceArticolo;
    }

    public String getCodiceCliente() {
        return codiceCliente;
    }

    public void setCodiceCliente(String codiceCliente) {
        this.codiceCliente = codiceCliente;
    }

    public String getCodiceDestinazioneDiversa() {
        return codiceDestinazioneDiversa;
    }

    public void setCodiceDestinazioneDiversa(String codiceDestinazioneDiversa) {
        this.codiceDestinazioneDiversa = codiceDestinazioneDiversa;
    }

    public String getCodiceCategoriaArticolo() {
        return codiceCategoriaArticolo;
    }

    public void setCodiceCategoriaArticolo(String codiceCategoriaArticolo) {
        this.codiceCategoriaArticolo = codiceCategoriaArticolo;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getSconto() {
        return sconto;
    }

    public void setSconto(String sconto) {
        this.sconto = sconto;
    }

    public String getDataInizioListino() {
        return dataInizioListino;
    }

    public void setDataInizioListino(String dataInizioListino) {
        this.dataInizioListino = dataInizioListino;
    }

    public String getDataFineListino() {
        return dataFineListino;
    }

    public void setDataFineListino(String dataFineListino) {
        this.dataFineListino = dataFineListino;
    }
}
