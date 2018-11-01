package it.visionmobya.CSVModule;

import android.content.Context;
import android.util.Log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

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
import it.visionmobya.utils.Utils;

public class CSVWriter {

    private static final String EXPORT_DIR = "/" + Utils.EXPORT;

    //fute null filename nese nuk do ta specifikosh se ku dueht te ruhet ky rekord pasi metoda e llogarit vete ku duhet ta shtoje rekordin
    public static void writeRecord(Context context, Object record, String fileName) throws IOException {
        String csv = null;
        String filePath = context.getFilesDir().getAbsolutePath();
        PrintWriter printWriter;
        CSVPrinter csvPrinter;

        boolean pathSpecified = false;

        if(fileName!=null){
            pathSpecified = true;
            filePath = filePath + "/" + fileName;
        }

        if(record instanceof Article){
            csv = ((Article) record).toCsvRecord();
            if(!pathSpecified){
                filePath= filePath+"/" + TextFiles.MAGART;
            }
        }
        else if(record instanceof ArticleCategory){
            csv = ((ArticleCategory) record).toCsvRecord();
            if(!pathSpecified){
                filePath = filePath  + "/" + TextFiles.MAGGRP;
            }
        }
        else if(record instanceof Client){
            csv = ((Client) record).toCsvRecord();
            if(!pathSpecified){
                filePath = filePath + "/" + TextFiles.ANAGRAFE;
            }
        }
        else if (record instanceof DocumentCategory){
            csv = ((DocumentCategory) record).toCsvRecord();
            if(!pathSpecified){
                filePath = filePath + "/" + TextFiles.DOCANA;
            }
        }
        else if(record instanceof Expiration){
            csv = ((Expiration) record).toCsvRecord();
            if(!pathSpecified){
                filePath = filePath+"/" + TextFiles.SCADENZE;
            }
        }
        else if (record instanceof History){
            csv = ((History) record).toCsvRecord();
            if(!pathSpecified){
                filePath = filePath + "/" + TextFiles.HISTORY;
            }
        }
        else if (record instanceof Listino){
            csv = ((Listino) record).toCsvRecord();
            if(!pathSpecified) {
                filePath = filePath + "/" + TextFiles.LISTINI;
            }
        }
        else if (record instanceof Lotti){
            csv = ((Lotti) record).toCsvRecord();
            if(!pathSpecified){
                filePath = filePath + "/" + TextFiles.LOTTI;
            }
        }
        else if(record instanceof Payment){
            csv = ((Payment) record).toCsvRecord();
            if(!pathSpecified){
                filePath = filePath + "/" +TextFiles.PAGAMENTI;
            }
        }
        else if (record instanceof Vat){
            csv = ((Vat) record).toCsvRecord();
            if(!pathSpecified){
                filePath = filePath + "/" + TextFiles.ALIQUOTE;
            }
        }

        else if(record instanceof DocRig){
            csv=((DocRig)record).toCSVRecord();
            if(!pathSpecified){
                filePath = filePath + EXPORT_DIR +"/" + TextFiles.DOCRIG;
            }
        }

        else if(record instanceof DocTes){
            csv= ((DocTes)record).toCsvRecord();
            if(!pathSpecified){
                filePath = filePath + EXPORT_DIR +"/" + TextFiles.DOCTES;
            }
        }

        else{
            csv = "";
        }

        File exportDirectory = new File(context.getFilesDir().getAbsolutePath() + EXPORT_DIR);

        if (!exportDirectory.exists()) {
            boolean result = false;
            try {
                 result = exportDirectory.mkdir();
            }
            catch(SecurityException se){
                Log.d("Directory" , " Directory creation failed: " + se.getMessage());
            }
            if(result) {
                Log.d("Directory" , " Directory created");
            }
        }

        Writer writer = new PrintWriter(new FileOutputStream(new File(filePath), true));
        printWriter = new PrintWriter(writer, true);
        csvPrinter = new CSVPrinter(printWriter,CSVFormat.DEFAULT.withRecordSeparator(System.lineSeparator()));
        csvPrinter.printRecord(csv.replace("\"", ""));
        printWriter.close();
        csvPrinter.close();
        writer.close();
    }
}
