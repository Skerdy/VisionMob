package it.visionmobya.models;

public class Lotti {

    private String idLotto;
    private String codiceLotto;
    private String descrizioneLotto;

    public String toCsvRecord() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(idLotto).append(";")
                .append(codiceLotto).append(";")
                .append(descrizioneLotto);
        return stringBuilder.toString();
    }

    public String getIdLotto() {
        return idLotto;
    }

    public void setIdLotto(String idLotto) {
        this.idLotto = idLotto;
    }

    public String getCodiceLotto() {
        return codiceLotto;
    }

    public void setCodiceLotto(String codiceLotto) {
        this.codiceLotto = codiceLotto;
    }

    public String getDescrizioneLotto() {
        return descrizioneLotto;
    }

    public void setDescrizioneLotto(String descrizioneLotto) {
        this.descrizioneLotto = descrizioneLotto;
    }
}
