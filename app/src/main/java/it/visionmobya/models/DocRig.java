package it.visionmobya.models;

public class DocRig {

    private String idTesta;
    private String idRiga;
    private String codiceDocumento;
    private String numeroDocumento;
    private String dataDocumento;
    private String numeroRiga;
    private String codiceArt;
    private String codiceUm;
    private String quantita;
    private String sconti;
    private String codiceIva;
    private String omaggio;
    private String desDocRig;
    private String lotto;
    private String noteRiga;

    public DocRig() {

    }

    public DocRig(DocRigBuilder docRigBuilder) {
        idTesta = docRigBuilder.idTesta;
        idRiga = docRigBuilder.idRiga;
        codiceDocumento = docRigBuilder.codiceDocumento;
        numeroDocumento = docRigBuilder.numeroDocumento;
        dataDocumento = docRigBuilder.dataDocumento;
        numeroRiga = docRigBuilder.numeroRiga;
        codiceArt = docRigBuilder.codiceArt;
        codiceUm = docRigBuilder.codiceUm;
        quantita = docRigBuilder.quantita;
        sconti = docRigBuilder.sconti;
        codiceIva = docRigBuilder.codiceIva;
        omaggio = docRigBuilder.omaggio;
        desDocRig = docRigBuilder.desDocRig;
        lotto = docRigBuilder.lotto;
        noteRiga = docRigBuilder.noteRiga;
    }

    public String toCSVRecord(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(idTesta).append(";")
                .append(idRiga).append(";")
                .append(codiceDocumento).append(";")
                .append(numeroDocumento).append(";")
                .append(dataDocumento).append(";")
                .append(numeroRiga).append(";")
                .append(codiceArt).append(";")
                .append(codiceUm).append(";")
                .append(quantita).append(";")
                .append(sconti).append(";")
                .append(codiceIva).append(";")
                .append(omaggio).append(";")
                .append(desDocRig).append(";")
                .append(lotto).append(";")
                .append(noteRiga).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    public String getIdTesta() {
        return idTesta;
    }

    public void setIdTesta(String idTesta) {
        this.idTesta = idTesta;
    }

    public String getIdRiga() {
        return idRiga;
    }

    public void setIdRiga(String idRiga) {
        this.idRiga = idRiga;
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

    public String getNumeroRiga() {
        return numeroRiga;
    }

    public void setNumeroRiga(String numeroRiga) {
        this.numeroRiga = numeroRiga;
    }

    public String getCodiceArt() {
        return codiceArt;
    }

    public void setCodiceArt(String codiceArt) {
        this.codiceArt = codiceArt;
    }

    public String getCodiceUm() {
        return codiceUm;
    }

    public void setCodiceUm(String codiceUm) {
        this.codiceUm = codiceUm;
    }

    public String getQuantita() {
        return quantita;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public String getSconti() {
        return sconti;
    }

    public void setSconti(String sconti) {
        this.sconti = sconti;
    }

    public String getCodiceIva() {
        return codiceIva;
    }

    public void setCodiceIva(String codiceIva) {
        this.codiceIva = codiceIva;
    }

    public String getOmaggio() {
        return omaggio;
    }

    public void setOmaggio(String omaggio) {
        this.omaggio = omaggio;
    }

    public String getDesDocRig() {
        return desDocRig;
    }

    public void setDesDocRig(String desDocRig) {
        this.desDocRig = desDocRig;
    }

    public String getLotto() {
        return lotto;
    }

    public void setLotto(String lotto) {
        this.lotto = lotto;
    }

    public String getNoteRiga() {
        return noteRiga;
    }

    public void setNoteRiga(String noteRiga) {
        this.noteRiga = noteRiga;
    }

    public static class DocRigBuilder {

        private final String idTesta;
        private final String idRiga;
        private String codiceDocumento = "";
        private String numeroDocumento = "";
        private String dataDocumento = "";
        private String numeroRiga = "";
        private String codiceArt = "";
        private String codiceUm = "";
        private String quantita = "";
        private String sconti = "";
        private String codiceIva = "";
        private String omaggio = "";
        private String desDocRig = "";
        private String lotto = "";
        private String noteRiga = "";

        public DocRigBuilder(String idTesta, String idRiga) {
            this.idTesta = idTesta;
            this.idRiga = idRiga;
        }

        public DocRigBuilder withCodiceDocumento(String codiceDocumento) {
            this.codiceDocumento = codiceDocumento;
            return this;
        }

        public DocRigBuilder withNumeroDocumento(String numeroDocumento) {
            this.numeroDocumento = numeroDocumento;
            return this;
        }

        public DocRigBuilder withDataDocumento(String dataDocumento) {
            this.dataDocumento = dataDocumento;
            return this;
        }

        public DocRigBuilder withNumeroRiga(String numeroRiga) {
            this.numeroRiga = numeroRiga;
            return this;
        }

        public DocRigBuilder withCodiceArt(String codiceArt) {
            this.codiceArt = codiceArt;
            return this;
        }

        public DocRigBuilder withCodiceUm(String codiceUm) {
            this.codiceUm = codiceUm;
            return this;
        }

        public DocRigBuilder withQuantita(String quantita) {
            this.quantita = quantita;
            return this;
        }

        public DocRigBuilder withSconti(String sconti) {
            this.sconti = sconti;
            return this;
        }

        public DocRigBuilder withCodiceIva(String codiceIva) {
            this.codiceIva = codiceIva;
            return this;
        }

        public DocRigBuilder withOmaggio(String omaggio) {
            this.omaggio = omaggio;
            return this;
        }

        public DocRigBuilder withDesDocRig(String desDocRig) {
            this.desDocRig = desDocRig;
            return this;
        }

        public DocRigBuilder withLotto(String lotto) {
            this.lotto = lotto;
            return this;
        }

        public DocRigBuilder withNoteRiga(String noteRiga) {
            this.noteRiga = noteRiga;
            return this;
        }


        public DocRig build() {
            return new DocRig(this);
        }

    }
}
