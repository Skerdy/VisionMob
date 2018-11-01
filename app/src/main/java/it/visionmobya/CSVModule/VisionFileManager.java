package it.visionmobya.CSVModule;

import android.annotation.SuppressLint;
import android.content.Context;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import it.visionmobya.Interface.ProgressBarMessage;
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




public class VisionFileManager {

    private Context context;
    private ProgressBarMessage progressBarMessage;
    private List<Article> articles;
    private List<ArticleCategory> articleCategories;
    private List<Client> clients;
    private List<Payment> payments;
    private List<Vat> vats;
    private List<Listino> listinos;
    private List<DocumentCategory> documentCategories;

    @SuppressLint("StaticFieldLeak")
    private static VisionFileManager visionFileManager;

    private VisionFileManager(){

    }

    public static synchronized VisionFileManager getInstance(){
            if(visionFileManager==null){
                visionFileManager = new VisionFileManager();
            }
        return visionFileManager;
    }

    public void init(Context context, ProgressBarMessage progressBarMessage){

        this.context = context;
        this.progressBarMessage = progressBarMessage;
        try {
            progressBarMessage.onLoadFile("Caricamento dei client...");
            this.clients = getAllClients();
            progressBarMessage.onLoadFile("Caricamento dei pagamenti...");
            this.payments = getAllPayments();
            progressBarMessage.onLoadFile("Caricamento di categorie di articoli...");
            this.articleCategories = getAllArticleCategories();
            progressBarMessage.onLoadFile("Caricamento di articoli...");
            this.articles = getAllArticles();
            progressBarMessage.onLoadFile("Caricamento di aliquote...");
            this.vats = getAllVats();
            progressBarMessage.onLoadFile("Caricamento di listini...");
            this.listinos = getAllListinos();
            progressBarMessage.onLoadFile("Caricamento di docana...");
            this.documentCategories = getAllDocumentCategories();

            transformObjectsWithCommaFields("|");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Reader getReader(InputStream initialStream) throws IOException {
        Reader targetReader = new InputStreamReader(initialStream);
        return targetReader;
    }

    //kjo metode kthen parserin e nje file duke futur input emrin e file ( emri i file ndodhet tek klasa TextFiles.java)
    private CSVParser getParserForFileAsset(String filename) throws IOException {
        return new CSVParser(getReader(this.context.getAssets().open(filename)), CSVFormat.DEFAULT);
    }

    private CSVParser getParserForFile(String filename) throws IOException {
        return new CSVParser(getReader(new FileInputStream(this.context.getFilesDir().getAbsolutePath()+"/" + filename)), CSVFormat.DEFAULT);
    }

    //kjo metode kthen listen me rekorde te nje parseri ne varesi te inputit qe i japim
    private List<CSVRecord> getRecordsForParser(CSVParser csvParser) throws IOException {
        return csvParser.getRecords();
    }

    //kjo metode kthen listen me kliente nga rekorded
    private List<Client> getClientsFromRecords(List<CSVRecord> records){
        List<Client> clients = new ArrayList<>();
        for(CSVRecord csvRecord : records){
            clients.add(CustomConverter.getClientFromCSVRecord(csvRecord));
        }
        return clients;
    }

   // kjo metode kthen kategorite e dokumenteve
    private List<DocumentCategory> getDocumentCategoriesFromRecords(List<CSVRecord> records){
        List<DocumentCategory> documentCategories = new ArrayList<>();
        for(CSVRecord csvRecord : records) {
            documentCategories.add(CustomConverter.getDocumentTypeFromRecord(csvRecord));
        }
        return documentCategories;
    }

    //kjo metode kthen gjithe kategorite
    private List<ArticleCategory> getArticleCategoriesFromRecords(List<CSVRecord> records){
        List<ArticleCategory> articleCategories = new ArrayList<>();
        for(CSVRecord csvRecord : records){
            articleCategories.add(CustomConverter.getArticleCategoryFromCSVRecord(csvRecord));
        }
        return articleCategories;
    }

    private List<Article> getArticlesFromRecords(List<CSVRecord> records){
        List<Article> articles = new ArrayList<>();
        for(CSVRecord csvRecord : records){
            articles.add(CustomConverter.getArticleFromRecord(csvRecord));
        }
        return articles;
    }

    private List<Expiration> getExpirationsFromRecords(List<CSVRecord> records) {
        List<Expiration> expirations = new ArrayList<>();
        for(CSVRecord csvRecord : records) {
            expirations.add(CustomConverter.getExpirationFromRecord(csvRecord));
        }
        return expirations;
    }

    private List<History> getHistoryFromRecords(List<CSVRecord> records) {
        List<History> histories = new ArrayList<>();
        for(CSVRecord csvRecord : records){
            histories.add(CustomConverter.getHistoryFromRecord(csvRecord));
        }
        return histories;
    }

    private List<Listino> getListinoFromRecords(List<CSVRecord> records){
        List<Listino> listinos = new ArrayList<>();
        for(CSVRecord csvRecord : records){
            listinos.add(CustomConverter.getListinoFromRecord(csvRecord));
        }
        return listinos;
    }

    private List<Lotti> getLottiFromRecords(List<CSVRecord> records) {
        List<Lotti> lottis = new ArrayList<>();
        for(CSVRecord csvRecord : records){
            lottis.add(CustomConverter.getLottiFromRecord(csvRecord));
        }
        return lottis;
    }

    private List<Payment> getPaymentFromRecords(List<CSVRecord> records){
        List<Payment> payments = new ArrayList<>();
        for(CSVRecord csvRecord : records){
            payments.add(CustomConverter.getPaymentFromRecord(csvRecord));
        }
        return payments;
    }

    private List<Vat> getVatsFromRecords(List<CSVRecord> records){
        List<Vat> vats = new ArrayList<>();
        for (CSVRecord csvRecord : records){
            vats.add(CustomConverter.getVatFromRecord(csvRecord));
        }
        return vats;
    }

    private List<DocRig> getDocRigasFromRecords(List<CSVRecord> records){
        List<DocRig> docRigs = new ArrayList<>();
        for(CSVRecord csvRecord : records){
            docRigs.add(CustomConverter.getDocRigFromRecord(csvRecord));
        }
        return docRigs;
    }

    private List<DocTes> getDocTestasFromRecords(List<CSVRecord> records){
        List<DocTes> docTestas = new ArrayList<>();
        for(CSVRecord csvRecord : records){
            docTestas.add(CustomConverter.getDocTesFromRecords(csvRecord));
        }
        return docTestas;
    }

    public List<Article> getArticlesWithCategoryId(String Id){
        List<Article> result = new ArrayList<>();
        for(Article article : articles){
            if(article.getCodiceCategoria().equals(Id)){
                result.add(article);
            }
        }
        return result;
    }



    private List<Article> getAllArticles() throws IOException {
        return getArticlesFromRecords(getRecordsForParser(getParserForFile(TextFiles.MAGART)));
    }

    private List<ArticleCategory> getAllArticleCategories() throws IOException {
        return getArticleCategoriesFromRecords(getRecordsForParser(getParserForFile(TextFiles.MAGGRP)));
    }

    private List<Client> getAllClients() throws IOException {
        return getClientsFromRecords(getRecordsForParser(getParserForFile(TextFiles.ANAGRAFE)));
    }

    private List<DocumentCategory> getAllDocumentCategories() throws IOException{
        return getDocumentCategoriesFromRecords(getRecordsForParser(getParserForFile(TextFiles.DOCANA)));
    }

    private List<Expiration> getAllExpirations() throws IOException{
        return getExpirationsFromRecords(getRecordsForParser(getParserForFile(TextFiles.SCADENZE)));
    }

    private List<Payment> getAllPayments() throws IOException {
        return getPaymentFromRecords(getRecordsForParser(getParserForFile(TextFiles.PAGAMENTI)));
    }

    private List<Vat> getAllVats() throws IOException{
        return getVatsFromRecords(getRecordsForParser(getParserForFile(TextFiles.ALIQUOTE)));
    }

    private List<Listino> getAllListinos() throws IOException{
        return getListinoFromRecords(getRecordsForParser(getParserForFile(TextFiles.LISTINI)));
    }

    private List<History> getAllHistories() throws IOException{
        return getHistoryFromRecords(getRecordsForParser(getParserForFile(TextFiles.HISTORY)));
    }

    private List<Lotti> getAllLotties() throws IOException {
        return getLottiFromRecords(getRecordsForParser(getParserForFile(TextFiles.LOTTI)));
    }

    public List<DocRig> getAllDocumentRigas() throws IOException {
        return getDocRigasFromRecords(getRecordsForParser(getParserForFile("export/" +TextFiles.DOCRIG)));
    }

    public List<DocTes> getAllDocumentTestas() throws IOException{
        return getDocTestasFromRecords(getRecordsForParser(getParserForFile("export/" +TextFiles.DOCTES)));
    }


    public void transformObjectsWithCommaFields(String specialChar){
        transformArticlesWithCommaFields(specialChar);
        transformClientsWithCommaFields(specialChar);
    }


    private void transformArticlesWithCommaFields(String specialChar){
        for(Article article : this.articles){
            article.setDescrizione(article.getDescrizione().replace(specialChar , ","));
            article.setCodiceArticolo(article.getCodiceArticolo().replace(specialChar, ","));
            article.setCodiceUnitaDiMisura( article.getCodiceUnitaDiMisura().replace(specialChar,","));
            article.setCodiceCategoria(article.getCodiceCategoria().replace(specialChar,","));
            article.setListino1(article.getListino1().replace(specialChar,","));
            article.setListino2(article.getListino2().replace(specialChar,","));
            article.setListino3(article.getListino3().replace(specialChar,","));
            article.setListino4(article.getListino4().replace(specialChar,","));
            article.setListino5(article.getListino5().replace(specialChar,","));
            article.setListino6(article.getListino6().replace(specialChar,","));
            article.setListino7(article.getListino7().replace(specialChar,","));
            article.setListino8(article.getListino8().replace(specialChar,","));
            article.setListino9(article.getListino9().replace(specialChar,","));
        }
    }

    private void transformClientsWithCommaFields(String specialChar){
        for(Client client : this.clients){
            client.setCodiceCliente(client.getCodiceCliente().replace(specialChar,","));
            client.setIndirizzo(client.getIndirizzo().replace(specialChar,","));
        }
    }






    //metodat publike per marrjen e te dhenave nga listat e mbushura ne runtime ne instancen e vetme te Vision manager te inicializuar ne login ose ne reLogin.
    public List<Article> getArticles() {
        return articles;
    }

    public List<ArticleCategory> getArticleCategories() {
        return articleCategories;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<Vat> getVats() {
        return vats;
    }

    public List<Listino> getListinos() {
        return listinos;
    }

    public List<DocumentCategory> getDocumentCategories() {
        return documentCategories;
    }

    //metodat publike per marrjen e te dhenave me parameter


}
