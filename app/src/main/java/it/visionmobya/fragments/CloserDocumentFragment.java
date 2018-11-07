package it.visionmobya.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.visionmobya.CSVModule.CSVWriter;
import it.visionmobya.FTPClient.FtpClientSaveData;
import it.visionmobya.R;
import it.visionmobya.activities.OrdineClienteActivity;
import it.visionmobya.controllers.DocRigController;
import it.visionmobya.controllers.DocTesController;
import it.visionmobya.listener.OnSaveAndPrintButtonListener;
import it.visionmobya.models.Client;
import it.visionmobya.models.DocRig;
import it.visionmobya.models.DocTes;
import it.visionmobya.models.DocumentCategory;
import it.visionmobya.models.customModels.DocumentState;
import it.visionmobya.models.customModels.ServerCredentials;
import it.visionmobya.utils.CodesUtil;
import it.visionmobya.utils.MySharedPref;
import it.visionmobya.utils.Utils;
import it.visionmobya.utils.ValidatorHelper;

public class CloserDocumentFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, OnSaveAndPrintButtonListener {

    private static final String FRAGMENT_ARGUMENTS = "sdfdsf";
    private static final String FRAGMENT_ARGUMENTS_DATE = "asdadasddate";
    private static final String FRAGMENT_ARGUMENTS_CLIENT = "dsdsfsdclient";
    private EditText accontoEuroET, casualeTransportoET, aspettoDeiBeniET, numeroColliET, transportoCuraDiET, noteET;
    private TextView dataTransporto, oraTransporto;
    private Date pickedDate;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private DateFormat dateFormat;
    private DateFormat timeFormat;
    private AlertDialog.Builder alertDialog;
    private List<DocumentState> documentStates;
    private DocumentCategory documentCategory;
    private Date documentDate;
    private Client selectedClient;
    private MySharedPref mySharedPref;
    private MaterialDialog materialDialog;

    public static CloserDocumentFragment newInstance(DocumentCategory documentCategory, Date documentDate, Client selectedClient) {
        CloserDocumentFragment closerDocumentFragment = new CloserDocumentFragment();
        Bundle args = new Bundle();
        args.putSerializable(FRAGMENT_ARGUMENTS, documentCategory);
        args.putSerializable(FRAGMENT_ARGUMENTS_DATE, documentDate);
        args.putSerializable(FRAGMENT_ARGUMENTS_CLIENT, selectedClient);
        closerDocumentFragment.setArguments(args);
        return closerDocumentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().getSerializable(FRAGMENT_ARGUMENTS) != null) {
            //nese ka nje dokument state per kete fragment atehere e marrim dhe e atachojme ne referencen publike te document state te fragmentit specifik
            documentCategory = (DocumentCategory) getArguments().getSerializable(FRAGMENT_ARGUMENTS);
            documentDate = (Date) getArguments().getSerializable(FRAGMENT_ARGUMENTS_DATE);
            selectedClient = (Client) getArguments().getSerializable(FRAGMENT_ARGUMENTS_CLIENT);
        }
        //attach listenerin tek aktivity
        ((OrdineClienteActivity) getActivity()).setOnSaveAndPrintButtonListener(this);

        mySharedPref = new MySharedPref(getActivity());


        materialDialog = new MaterialDialog.Builder(getActivity())
                .title("Error")
                .content("Please do not leave blank fields while filling article data").positiveText("Ok")
                .build();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.closer_doc_fragment, container, false);
        //bejme inicializimin e gjithe komponenteve te deklaruara ne drawing time dhe i perdorim pasi behet draw ne onviewcreated
        initUI(view);
        return view;
    }


    private void setupDatePicker() {


        //auto vendos daten dhe oren e sotme tek textviewt

        setTodayDateAndTime();
        Calendar now = Calendar.getInstance();

        datePickerDialog = DatePickerDialog.newInstance(
                CloserDocumentFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.setTitle("Choose the Transport Date");

        timePickerDialog = TimePickerDialog.newInstance(
                CloserDocumentFragment.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.setTitle("Choose the Transport Hour");
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //nese document state nuk eshte null atehere fillo mbush tere komponentet me te dhenat e ruajtura perkatesisht
    }

    private void initUI(View view) {

        this.accontoEuroET = view.findViewById(R.id.accontoEuro);
        this.casualeTransportoET = view.findViewById(R.id.casualeDelTransporto);
        this.aspettoDeiBeniET = view.findViewById(R.id.aspettoDeibeni);
        this.numeroColliET = view.findViewById(R.id.numroColli);
        this.transportoCuraDiET = view.findViewById(R.id.transportoAcuraDi);
        this.dataTransporto = view.findViewById(R.id.dataTransporto);
        this.oraTransporto = view.findViewById(R.id.oraTransposto);
        this.noteET = view.findViewById(R.id.note);
        setupDatePicker();

        this.dataTransporto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datePickerDialog != null) {
                    datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
                }
            }
        });

        this.oraTransporto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePickerDialog != null) {
                    timePickerDialog.show(getActivity().getFragmentManager(), "TimePickerDialog");
                }
            }
        });

        alertDialog = new AlertDialog.Builder(getActivity(), R.style.AlertDialogBox);
        alertDialog.setTitle("Save and Print");
        alertDialog.setMessage("Sei sicuro di salvare il documento?");
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (ValidatorHelper.areDocumentsEligibleForDocumentClosure(documentStates))
                    generateDocRigaAndTesta();
                else {
                    materialDialog.show();
                }
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    private boolean isDataValid() {
        boolean flag = true;
        if (accontoEuroET.getText().toString().trim().isEmpty()) {
            flag = false;
            accontoEuroET.setError("Please insert acconto Euro!");
        }
        if (casualeTransportoET.getText().toString().trim().isEmpty()) {
            flag = false;
            casualeTransportoET.setError("Please insert Transporto Casuale!");
        }
        if (aspettoDeiBeniET.getText().toString().trim().isEmpty()) {
            flag = false;
            aspettoDeiBeniET.setError("Please insert Aspetto dei beni ET!");
        }
        if (numeroColliET.getText().toString().trim().isEmpty()) {
            flag = false;
            numeroColliET.setError("Please insert Numero Colli!");
        }
        if (transportoCuraDiET.getText().toString().trim().isEmpty()) {
            flag = false;
            transportoCuraDiET.setError("Please insert Transporto Cura !");
        }
        if (dataTransporto.getText().toString().trim().isEmpty()) {
            flag = false;
            dataTransporto.setError("Please insert data Transporto !");
        }

        if (oraTransporto.getText().toString().trim().isEmpty()) {
            flag = false;
            oraTransporto.setError("Please insert ora Transporto!");
        }

        return flag;
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        Date date = calendar.getTime();
        dataTransporto.setText(dateFormat.format(date));
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String secondString = second < 10 ? "0" + second : "" + second;
        String time = hourString + ":" + minuteString;
        oraTransporto.setText(time);
    }

    private void setTodayDateAndTime() {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        timeFormat = new SimpleDateFormat("HH:mm");

        Date today = Calendar.getInstance().getTime();

        String dateString = dateFormat.format(today);
        String timeString = timeFormat.format(today);
        this.dataTransporto.setText(dateString);
        this.oraTransporto.setText(timeString);
    }

    //pasi shtypet butoni save ne aktivity
    @Override
    public void onSaveAndPrintClicked(List<DocumentState> documentStates) {
        this.documentStates = documentStates;
        if (isDataValid()) {
            alertDialog.show();
        }
    }

    private void generateDocRigaAndTesta() {
        //kemi nje doc testa dhe disa doc riga sa document states kemi

        FtpClientSaveData ftpClientSaveData = new FtpClientSaveData(getActivity());
        //krijojme doc Testa
        String testaId = DocTesController.getLastIdTesta();
        Integer newTestaId = Integer.valueOf(testaId) + 1;

        String documentCount = documentCategory.getConttatoreDocumento();
        Integer newDocumentCount = Integer.valueOf(documentCount) + 1;

        //mos harrojme te shtojme me 1 countin tek docana per ate document category

        String agentUserName = mySharedPref.getStringFromSharedPref(CodesUtil.USER_NAME);

        //kujtohu te marresh codice pagamento te klientit nga anagrafe
        String codicePagamento = "codice pagamento";


        DocTes docTes = new DocTes.DoctesBuilder(newTestaId.toString(), documentCategory.getCodiceDocumento())
                .withNumeroDocumento(newDocumentCount.toString())
                .withDataDocumento(dateFormat.format(documentDate))
                .withCodiceClifor(this.selectedClient.getCodiceCliente())
                .withCodiceAgente(Utils.getAgentCodeFromUserName(agentUserName))
                .withCodiceValuta("EUR")
                .withCodicePagamenti(codicePagamento)
                .withNoteTesta(noteET.getText().toString())
                .withAcconto(accontoEuroET.getText().toString()).build();

        try {
            CSVWriter.writeRecord(getActivity(), docTes, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (DocumentState documentState : documentStates) {
            int lastDocRigId = DocRigController.getLastIdRiga();
            String docRigId = (lastDocRigId + 1) + "";

            DocRig docRig = new DocRig.DocRigBuilder(docTes.getIdTesta(), docRigId).withCodiceDocumento(documentCategory.getCodiceDocumento())
                    .withNumeroDocumento(newDocumentCount.toString())
                    .withDataDocumento(dateFormat.format(documentDate))
                    .withNumeroRiga("1")
                    .withCodiceArt(documentState.getArticle().getCodiceArticolo())
                    .withCodiceUm(documentState.getUnitaDiMisura())
                    .withQuantita(documentState.getQuantita().toString())
                    .withSconti(documentState.getScontoPercentuale().toString())
                    .withCodiceIva(documentState.getArticle().getCodiceIvaVendite())
                    .withOmaggio("")
                    .withDesDocRig(documentState.getDescrizione())
                    .withLotto("")
                    .withNoteRiga(noteET.getText().toString()).build();

            try {
                CSVWriter.writeRecord(getActivity(), docRig, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String username = mySharedPref.getStringFromSharedPref(CodesUtil.USER_NAME);
        String password = mySharedPref.getStringFromSharedPref(CodesUtil.PASSWORD);
        String url = mySharedPref.getStringFromSharedPref(CodesUtil.URL);
        String port = mySharedPref.getStringFromSharedPref(CodesUtil.PORT);
        if (port.equals(MySharedPref.GET_STRING_FAILED)) {
            port = "21";
        }
        Log.d("ServerSave", " username : " + username + " pass : " + password + " url : " + url + " port" + port);
        ServerCredentials serverCredentials = new ServerCredentials(username.replace("\"", ""), password, url, Integer.valueOf(port));
        String importDirectory = Utils.getAgentWorkingDirectory(username, Utils.IMPORT);
        String exportDirectory = Utils.getAgentWorkingDirectory(username, Utils.EXPORT);
        serverCredentials.setImportDirectory(importDirectory);
        serverCredentials.setExportDirectory(exportDirectory);
        ftpClientSaveData.execute(serverCredentials);
    }
}
