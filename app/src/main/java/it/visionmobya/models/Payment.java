package it.visionmobya.models;

public class Payment {

    private String codicePagamento;
    private String descrizionePagamento;

    public Payment() {

    }

    public Payment(PaymentBuilder paymentBuilder) {
        codicePagamento = paymentBuilder.codicePagamento;
        descrizionePagamento = paymentBuilder.descrizionePagamento;
    }

    public Payment(String codicePagamento, String descrizionePagamento) {
        this.codicePagamento = codicePagamento;
        this.descrizionePagamento = descrizionePagamento;
    }

    public String toCsvRecord() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(codicePagamento).append(";")
                .append(descrizionePagamento);
        return stringBuilder.toString();
    }

    public String getDescrizionePagamento() {
        return descrizionePagamento;
    }

    public void setDescrizionePagamento(String descrizionePagamento) {
        this.descrizionePagamento = descrizionePagamento;
    }

    public String getCodicePagamento() {
        return codicePagamento;
    }

    public void setCodicePagamento(String codicePagamento) {
        this.codicePagamento = codicePagamento;
    }

    public static class PaymentBuilder {
        private String codicePagamento = "";
        private String descrizionePagamento = "";

        public PaymentBuilder() {
        }

        public Payment build() {
            return new Payment(this);
        }

        public PaymentBuilder withCodicePagamento(String codicePagamento) {
            this.codicePagamento = codicePagamento;
            return this;
        }

        public PaymentBuilder withDescrizionePagamento(String descrizionePagamento) {
            this.descrizionePagamento = descrizionePagamento;
            return this;
        }

    }
}
