package it.visionmobya.models;

import java.util.StringJoiner;

public class Client {

    private String codiceCliente;
    private String ragioneSociale;
    private String ragioneSociale2;
    private String partitaIVA;
    private String codiceFiscale;
    private String indirizzo;
    private String CAP;
    private String citta;
    private String provincia;
    private String codiceZona;
    private String codicePagamento;
    private String codiceIva;
    private String listino;
    private String valuta;
    private String categoria;
    private String situazione;
    private String agente;
    private String telefono;
    private String email;
    private String giorniVisite;
    private String sequenza;
    private String codiceDocumentoAbituale;
    private String codiceValuta;
    private String chkCessioni;
    private String chkPrezzi;
    private String sconti;

    public static class ClientBuilder {

        private final String codiceCliente;
        private final String ragioneSociale;
        private String ragioneSociale2 = "";
        private String partitaIVA = "";
        private String codiceFiscale = "";
        private String indirizzo = "";
        private String CAP = "";
        private String citta = "";
        private String provincia = "";
        private String codiceZona = "";
        private String codicePagamento = "";
        private String codiceIva = "";
        private String listino = "";
        private String valuta = "";
        private String categoria = "";
        private String situazione = "";
        private String agente = "";
        private String telefono = "";
        private String email = "";
        private String giorniVisite = "";
        private String sequenza = "";
        private String codiceDocumentoAbituale = "";
        private String codiceValuta = "";
        private String chkCessioni = "";
        private String chkPrezzi = "";
        private String sconti = "";

        public ClientBuilder(String codiceCliente, String ragioneSociale) {
            this.codiceCliente = codiceCliente;
            this.ragioneSociale = ragioneSociale;
        }

        public ClientBuilder withRagioneSociale2(String ragioneSociale2) {
            this.ragioneSociale2 = ragioneSociale2;
            return this;
        }

        public ClientBuilder withPartitaIVA(String partitaIVA) {
            this.partitaIVA = partitaIVA;
            return this;
        }

        public ClientBuilder withCodiceFiscale(String codiceFiscale) {
            this.codiceFiscale = codiceFiscale;
            return this;
        }

        public ClientBuilder withIndirizzo(String indirizzo) {
            this.indirizzo = indirizzo;
            return this;
        }

        public ClientBuilder withCAP(String CAP) {
            this.CAP = CAP;
            return this;
        }

        public ClientBuilder withCitta(String citta) {
            this.citta = citta;
            return this;
        }

        public ClientBuilder withProvincia(String provincia) {
            this.provincia = provincia;
            return this;
        }

        public ClientBuilder withCodiceZona(String codiceZona) {
            this.codiceZona = codiceZona;
            return this;
        }

        public ClientBuilder withCodicePagamento(String codicePagamento) {
            this.codicePagamento = codicePagamento;
            return this;
        }

        public ClientBuilder withCodiceIva(String codiceIva) {
            this.codiceIva = codiceIva;
            return this;
        }

        public ClientBuilder withListino(String listino) {
            this.listino = listino;
            return this;
        }

        public ClientBuilder withValuta(String valuta) {
            this.valuta = valuta;
            return this;
        }

        public ClientBuilder withCategoria(String categoria) {
            this.categoria = categoria;
            return this;
        }

        public ClientBuilder withSituazione(String situazione) {
            this.situazione = situazione;
            return this;
        }

        public ClientBuilder withAgente(String agente) {
            this.agente = agente;
            return this;
        }

        public ClientBuilder withTelefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public ClientBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder withGiorniVisite(String giorniVisite) {
            this.giorniVisite = giorniVisite;
            return this;
        }

        public ClientBuilder withSequenza(String sequenza) {
            this.sequenza = sequenza;
            return this;
        }

        public ClientBuilder withCodiceDocumentoAbituale(String codiceDocumentoAbituale) {
            this.codiceDocumentoAbituale = codiceDocumentoAbituale;
            return this;
        }

        public ClientBuilder withCodiceValuta(String codiceValuta) {
            this.codiceValuta = codiceValuta;
            return this;
        }

        public ClientBuilder withChkCessioni(String chkCessioni) {
            this.chkCessioni = chkCessioni;
            return this;
        }

        public ClientBuilder withChkPrezzi(String chkPrezzi) {
            this.chkPrezzi = chkPrezzi;
            return this;
        }

        public ClientBuilder withSconti(String sconti) {
            this.sconti = sconti;
            return this;
        }


        public Client build() {
            return new Client(this);
        }
    }

    public Client(ClientBuilder clientBuilder) {
        codiceCliente = clientBuilder.codiceCliente;
        ragioneSociale = clientBuilder.ragioneSociale;
        ragioneSociale2 = clientBuilder.ragioneSociale2;
        partitaIVA = clientBuilder.partitaIVA;
        codiceFiscale = clientBuilder.codiceFiscale;
        indirizzo = clientBuilder.indirizzo;
        CAP = clientBuilder.CAP;
        citta = clientBuilder.citta;
        provincia = clientBuilder.provincia;
        codiceZona = clientBuilder.codiceZona;
        codicePagamento = clientBuilder.codicePagamento;
        codiceIva = clientBuilder.codiceIva;
        listino = clientBuilder.listino;
        valuta = clientBuilder.valuta;
        categoria = clientBuilder.categoria;
        situazione = clientBuilder.situazione;
        agente = clientBuilder.agente;
        telefono = clientBuilder.telefono;
        email = clientBuilder.email;
        giorniVisite = clientBuilder.giorniVisite;
        sequenza = clientBuilder.sequenza;
        codiceDocumentoAbituale = clientBuilder.codiceDocumentoAbituale;
        codiceValuta = clientBuilder.codiceValuta;
        chkCessioni = clientBuilder.chkCessioni;
        chkPrezzi = clientBuilder.chkPrezzi;
        sconti = clientBuilder.sconti;

    }


    public String toCsvRecord() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(codiceCliente).append(";")
                .append(ragioneSociale).append(";")
                .append(ragioneSociale2).append(";")
                .append(partitaIVA).append(";")
                .append(codiceFiscale).append(";")
                .append(indirizzo).append(";")
                .append(CAP).append(";")
                .append(citta).append(";")
                .append(provincia).append(";")
                .append(codiceZona).append(";")
                .append(codicePagamento).append(";")
                .append(codiceIva).append(";")
                .append(listino).append(";")
                .append(valuta).append(";")
                .append(categoria).append(";")
                .append(situazione).append(";")
                .append(agente).append(";")
                .append(telefono).append(";")
                .append(email).append(";")
                .append(giorniVisite).append(";")
                .append(sequenza).append(";")
                .append(codiceDocumentoAbituale).append(";")
                .append(codiceValuta).append(";")
                .append(chkCessioni).append(";")
                .append(chkPrezzi).append(";")
                .append(sconti);
        return stringBuilder.toString();
    }

    public String getCodiceCliente() {
        return codiceCliente;
    }

    public void setCodiceCliente(String codiceCliente) {
        this.codiceCliente = codiceCliente;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getRagioneSociale2() {
        return ragioneSociale2;
    }

    public void setRagioneSociale2(String ragioneSociale2) {
        this.ragioneSociale2 = ragioneSociale2;
    }

    public String getPartitaIVA() {
        return partitaIVA;
    }

    public void setPartitaIVA(String partitaIVA) {
        this.partitaIVA = partitaIVA;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodiceZona() {
        return codiceZona;
    }

    public void setCodiceZona(String codiceZona) {
        this.codiceZona = codiceZona;
    }

    public String getCodicePagamento() {
        return codicePagamento;
    }

    public void setCodicePagamento(String codicePagamento) {
        this.codicePagamento = codicePagamento;
    }

    public String getCodiceIva() {
        return codiceIva;
    }

    public void setCodiceIva(String codiceIva) {
        this.codiceIva = codiceIva;
    }

    public String getListino() {
        return listino;
    }

    public void setListino(String listino) {
        this.listino = listino;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSituazione() {
        return situazione;
    }

    public void setSituazione(String situazione) {
        this.situazione = situazione;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGiorniVisite() {
        return giorniVisite;
    }

    public void setGiorniVisite(String giorniVisite) {
        this.giorniVisite = giorniVisite;
    }

    public String getSequenza() {
        return sequenza;
    }

    public void setSequenza(String sequenza) {
        this.sequenza = sequenza;
    }

    public String getCodiceDocumentoAbituale() {
        return codiceDocumentoAbituale;
    }

    public void setCodiceDocumentoAbituale(String codiceDocumentoAbituale) {
        this.codiceDocumentoAbituale = codiceDocumentoAbituale;
    }

    public String getCodiceValuta() {
        return codiceValuta;
    }

    public void setCodiceValuta(String codiceValuta) {
        this.codiceValuta = codiceValuta;
    }

    public String getChkCessioni() {
        return chkCessioni;
    }

    public void setChkCessioni(String chkCessioni) {
        this.chkCessioni = chkCessioni;
    }

    public String getChkPrezzi() {
        return chkPrezzi;
    }

    public void setChkPrezzi(String chkPrezzi) {
        this.chkPrezzi = chkPrezzi;
    }

    public String getSconti() {
        return sconti;
    }

    public void setSconti(String sconti) {
        this.sconti = sconti;
    }
}
