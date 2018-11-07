package it.visionmobya.CSVModule;

import android.util.Log;

import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

import it.visionmobya.models.Article;
import it.visionmobya.models.ArticleCategory;
import it.visionmobya.models.Client;
import it.visionmobya.models.DocRig;
import it.visionmobya.models.DocTes;
import it.visionmobya.models.DocumentCategory;
import it.visionmobya.models.Expiration;
import it.visionmobya.models.History;
import it.visionmobya.models.Listino;
import it.visionmobya.models.Lotti;
import it.visionmobya.models.Payment;
import it.visionmobya.models.Vat;

public class CustomConverter {

    public static ArticleCategory getArticleCategoryFromCSVRecord(CSVRecord csvRecord) {
        ArticleCategory articleCategory = new ArticleCategory();
        String[] fields = csvRecord.get(0).split(";");
        articleCategory.setId(fields[0]);
        articleCategory.setName(fields[1]);
        return articleCategory;
    }

    public static Client getClientFromCSVRecord(CSVRecord csvRecord) {

        Log.d("Size", "Size i rekord eshte:" + csvRecord.size());
        Client client = new Client.ClientBuilder("", "").build();
        List<String> fields = getUnitStringsFromRecord(csvRecord);

        client.setCodiceCliente(fields.get(0));
        client.setRagioneSociale(fields.get(1));
        client.setRagioneSociale2(fields.get(2));
        client.setPartitaIVA(fields.get(3));
        client.setCodiceFiscale(fields.get(4));
        client.setIndirizzo(fields.get(5));
        client.setCAP(fields.get(6));
        client.setCitta(fields.get(7));
        client.setProvincia(fields.get(8));
        client.setCodiceZona(fields.get(9));
        client.setCodicePagamento(fields.get(10));
        client.setCodiceIva(fields.get(11));
        client.setListino(fields.get(12));
        client.setValuta(fields.get(13));
        client.setCategoria(fields.get(14));
        client.setSituazione(fields.get(15));
        client.setAgente(fields.get(16));
        client.setTelefono(fields.get(20));
        client.setEmail(fields.get(18));
        client.setGiorniVisite(fields.get(19));
        client.setSequenza(fields.get(20));
        client.setCodiceDocumentoAbituale(fields.get(21));
        client.setCodiceValuta(fields.get(22));
        client.setChkCessioni(fields.get(23));
        client.setChkPrezzi(fields.get(24));
        client.setSconti(fields.get(25));
        return client;
    }

    public static DocumentCategory getDocumentTypeFromRecord(CSVRecord csvRecord) {
        DocumentCategory documentCategory = new DocumentCategory();
        List<String> fields = getUnitStringsFromRecord(csvRecord);
        documentCategory.setCodiceDocumento(fields.get(0));
        documentCategory.setDescrizioneDocumento(fields.get(1));
        documentCategory.setTipoDocumento(fields.get(2));
        documentCategory.setConttatoreDocumento(fields.get(3));
        return documentCategory;
    }

    public static Article getArticleFromRecord(CSVRecord csvRecord) {
        Article article = new Article.ArticleBuilder("", "").build();
        List<String> fields = getUnitStringsFromRecord(csvRecord);
        article.setCodiceArticolo(fields.get(0));
        article.setDescrizione(fields.get(1));
        article.setCodiceUnitaDiMisura(fields.get(2));
        article.setCodiceCategoria(fields.get(3));
        article.setListino1(fields.get(4));
        article.setListino2(fields.get(5));
        article.setListino3(fields.get(6));
        article.setListino4(fields.get(7));
        article.setListino5(fields.get(8));
        article.setListino6(fields.get(9));
        article.setListino7(fields.get(10));
        article.setListino8(fields.get(11));
        article.setListino9(fields.get(12));
        article.setCodiceIvaVendite(fields.get(13));
        article.setPercentualeDiSconto1(fields.get(14));
        article.setPercentualeDiSconto2(fields.get(15));
        article.setPercentualeDiSconto3(fields.get(16));
        article.setAlias(fields.get(17));
        article.setCodiceUnitàDiMisura1(fields.get(18));
        article.setCodiceUnitàDiMisura2(fields.get(19));
        article.setCodiceUnitàDiMisura3(fields.get(20));
        article.setFattorediConversione1(fields.get(21));
        article.setFattorediConversione2(fields.get(22));
        article.setFattorediConversione3(fields.get(23));
        return article;
    }

    public static Expiration getExpirationFromRecord(CSVRecord csvRecord) {
        Expiration expiration = new Expiration();
        List<String> fields = getUnitStringsFromRecord(csvRecord);
        expiration.setIdScadenza(fields.get(0));
        expiration.setDataFattura(fields.get(1));
        expiration.setNumeroFattura(fields.get(2));
        expiration.setDataScadenza(fields.get(3));
        expiration.setCodiceCliente(fields.get(4));
        expiration.setImportoSingolaScadenza(fields.get(5));
        expiration.setImportoTotaleFattura(fields.get(6));
        expiration.setCodiceDocumento(fields.get(7));
        return expiration;
    }

    public static History getHistoryFromRecord(CSVRecord csvRecord) {
        History history = new History();
        List<String> fields = getUnitStringsFromRecord(csvRecord);
        history.setCodiceDocumento(fields.get(0));
        history.setNumeroDocumento(fields.get(1));
        history.setDataDocumento(fields.get(2));
        history.setCodiceCliente(fields.get(3));
        history.setCodiceCategoriaArticolo(fields.get(4));
        history.setCodiceArticolo(fields.get(5));
        history.setQuantità(fields.get(6));
        history.setPrezzoNetto(fields.get(7));
        return history;
    }

    public static Listino getListinoFromRecord(CSVRecord csvRecord) {
        Listino listino = new Listino();
        List<String> fields = getUnitStringsFromRecord(csvRecord);
        listino.setIdListino(fields.get(0));
        listino.setCodiceArticolo(fields.get(1));
        listino.setCodiceCliente(fields.get(2));
        listino.setCodiceDestinazioneDiversa(fields.get(3));
        listino.setCodiceCategoriaArticolo(fields.get(4));
        listino.setPrezzo(fields.get(5));
        listino.setSconto(fields.get(6));
        listino.setDataInizioListino(fields.get(7));
        listino.setDataFineListino(fields.get(8));
        return listino;
    }

    public static Lotti getLottiFromRecord(CSVRecord csvRecord) {
        Lotti lotti = new Lotti();
        List<String> fields = getUnitStringsFromRecord(csvRecord);
        lotti.setIdLotto(fields.get(0));
        lotti.setCodiceLotto(fields.get(1));
        lotti.setDescrizioneLotto(fields.get(2));
        return lotti;
    }

    public static Payment getPaymentFromRecord(CSVRecord csvRecord) {
        Payment payment = new Payment();
        List<String> fields = getUnitStringsFromRecord(csvRecord);
        payment.setCodicePagamento(fields.get(0));
        payment.setDescrizionePagamento(fields.get(1));
        return payment;
    }

    public static Vat getVatFromRecord(CSVRecord csvRecord) {
        Vat vat = new Vat();
        List<String> fields = getUnitStringsFromRecord(csvRecord);
        vat.setCodiceIva(fields.get(0));
        vat.setDescrizioneIva(fields.get(1));
        vat.setAliquotaInPercentuale(fields.get(2));
        vat.setCampoFisso(fields.get(3));
        return vat;

    }

    public static DocRig getDocRigFromRecord(CSVRecord csvRecord) {
        List<String> fields = getUnitStringsFromRecord(csvRecord);
        DocRig docRig = new DocRig.DocRigBuilder(fields.get(0), fields.get(1))
                .withCodiceDocumento(fields.get(2))
                .withNumeroDocumento(fields.get(3))
                .withDataDocumento(fields.get(4))
                .withNumeroRiga(fields.get(5))
                .withCodiceArt(fields.get(6))
                .withCodiceUm(fields.get(7))
                .withQuantita(fields.get(8))
                .withSconti(fields.get(9))
                .withCodiceIva(fields.get(10))
                .withOmaggio(fields.get(11))
                .withDesDocRig(fields.get(12))
                .withLotto(fields.get(13))
                .withNoteRiga(fields.get(14)).build();
        return docRig;
    }

    public static DocTes getDocTesFromRecords(CSVRecord csvRecord) {
        List<String> fields = getUnitStringsFromRecord(csvRecord);
        DocTes docTes = new DocTes.DoctesBuilder(fields.get(0), fields.get(1))
                .withNumeroDocumento(fields.get(2))
                .withDataDocumento(fields.get(3))
                .withCodiceClifor(fields.get(4))
                .withCodiceDestDiv(fields.get(5))
                .withCodiceAgente(fields.get(6))
                .withCodiceValuta(fields.get(7))
                .withCodicePagamenti(fields.get(8))
                .withCodiceSconto(fields.get(9))
                .withDataCons(fields.get(10))
                .withNoteTesta(fields.get(11))
                .withAcconto(fields.get(12)).build();
        return docTes;
    }

    public static List<String> getUnitStringsFromRecord(CSVRecord csvRecord) {
        List<String> fields = new ArrayList<>();
        for (int i = 0; i < csvRecord.size(); i++) {
            for (int j = 0; j < csvRecord.get(i).split("\\;").length; j++) {
                fields.add(csvRecord.get(i).split("\\;")[j]);
            }
        }
        return fields;
    }


}
