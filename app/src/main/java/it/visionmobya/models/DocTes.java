package it.visionmobya.models;

import org.apache.commons.csv.CSVRecord;

public class DocTes {

    private String idTesta;
    private String codiceDocumento;
    private String numeroDocumento;
    private String dataDocumento;
    private String codiceClifor;
    private String codiceDestDiv;
    private String codiceAgente;
    private String CodiceValuta;
    private String codicePagamenti;
    private String codiceSconto;
    private String dataCons;
    private String noteTesta;
    private String acconto;

    public DocTes() {

    }

    public DocTes(DoctesBuilder doctesBuilder) {
        idTesta = doctesBuilder.idTesta;
        codiceDocumento = doctesBuilder.codiceDocumento;
        numeroDocumento = doctesBuilder.numeroDocumento;
        dataDocumento = doctesBuilder.dataDocumento;
        codiceClifor = doctesBuilder.codiceClifor;
        codiceDestDiv = doctesBuilder.codiceDestDiv;
        codiceAgente = doctesBuilder.codiceAgente;
        CodiceValuta = doctesBuilder.CodiceValuta;
        codicePagamenti = doctesBuilder.codicePagamenti;
        codiceSconto = doctesBuilder.codiceSconto;
        dataCons = doctesBuilder.dataCons;
        noteTesta = doctesBuilder.noteTesta;
        acconto = doctesBuilder.acconto;
    }

    public String toCsvRecord() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(idTesta).append(";")
                .append(codiceDocumento).append(";")
                .append(numeroDocumento).append(";")
                .append(dataDocumento).append(";")
                .append(codiceClifor).append(";")
                .append(codiceDestDiv).append(";")
                .append(codiceAgente).append(";")
                .append(CodiceValuta).append(";")
                .append(codicePagamenti).append(";")
                .append(codiceSconto).append(";")
                .append(dataCons).append(";")
                .append(noteTesta).append(";")
                .append(acconto).append(System.lineSeparator());

        return stringBuilder.toString();
    }

    public String getIdTesta() {
        return idTesta;
    }

    public void setIdTesta(String idTesta) {
        this.idTesta = idTesta;
    }

    public String getCodiceDocumento() {
        return codiceDocumento;
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

    public String getCodiceClifor() {
        return codiceClifor;
    }

    public void setCodiceClifor(String codiceClifor) {
        this.codiceClifor = codiceClifor;
    }

    public String getCodiceDestDiv() {
        return codiceDestDiv;
    }

    public void setCodiceDestDiv(String codiceDestDiv) {
        this.codiceDestDiv = codiceDestDiv;
    }

    public String getCodiceAgente() {
        return codiceAgente;
    }

    public void setCodiceAgente(String codiceAgente) {
        this.codiceAgente = codiceAgente;
    }

    public String getCodiceValuta() {
        return CodiceValuta;
    }

    public void setCodiceValuta(String codiceValuta) {
        CodiceValuta = codiceValuta;
    }

    public String getCodicePagamenti() {
        return codicePagamenti;
    }

    public void setCodicePagamenti(String codicePagamenti) {
        this.codicePagamenti = codicePagamenti;
    }

    public String getCodiceSconto() {
        return codiceSconto;
    }

    public void setCodiceSconto(String codiceSconto) {
        this.codiceSconto = codiceSconto;
    }

    public String getDataCons() {
        return dataCons;
    }

    public void setDataCons(String dataCons) {
        this.dataCons = dataCons;
    }

    public String getNoteTesta() {
        return noteTesta;
    }

    public void setNoteTesta(String noteTesta) {
        this.noteTesta = noteTesta;
    }

    public String getAcconto() {
        return acconto;
    }

    public void setAcconto(String acconto) {
        this.acconto = acconto;
    }

    public static class DoctesBuilder {
        private final String idTesta;
        private final String codiceDocumento;
        private String numeroDocumento = "";
        private String dataDocumento = "";
        private String codiceClifor = "";
        private String codiceDestDiv = "";
        private String codiceAgente = "";
        private String CodiceValuta = "";
        private String codicePagamenti = "";
        private String codiceSconto = "";
        private String dataCons = "";
        private String noteTesta = "";
        private String acconto = "";

        public DoctesBuilder(String idTesta, String codiceDocumento) {
            this.idTesta = idTesta;
            this.codiceDocumento = codiceDocumento;
        }

        public DoctesBuilder withNumeroDocumento(String numeroDocumento) {
            this.numeroDocumento = numeroDocumento;
            return this;
        }

        public DoctesBuilder withDataDocumento(String dataDocumento) {
            this.dataDocumento = dataDocumento;
            return this;
        }

        public DoctesBuilder withCodiceClifor(String codiceClifor) {
            this.codiceClifor = codiceClifor;
            return this;
        }

        public DoctesBuilder withCodiceDestDiv(String codiceDestDiv) {
            this.codiceDestDiv = codiceDestDiv;
            return this;
        }

        public DoctesBuilder withCodiceAgente(String codiceAgente) {
            this.codiceAgente = codiceAgente;
            return this;
        }

        public DoctesBuilder withCodiceValuta(String codiceValuta) {
            this.CodiceValuta = codiceValuta;
            return this;
        }

        public DoctesBuilder withCodicePagamenti(String codicePagamenti) {
            this.codicePagamenti = codicePagamenti;
            return this;
        }

        public DoctesBuilder withCodiceSconto(String codiceSconto) {
            this.codiceSconto = codiceSconto;
            return this;
        }

        public DoctesBuilder withDataCons(String dataCons) {
            this.dataCons = dataCons;
            return this;
        }

        public DoctesBuilder withNoteTesta(String noteTesta) {
            this.noteTesta = noteTesta;
            return this;
        }

        public DoctesBuilder withAcconto(String acconto) {
            this.acconto = acconto;
            return this;
        }

        public DocTes build() {
            return new DocTes(this);
        }

    }
}
