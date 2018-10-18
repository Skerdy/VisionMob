package it.visionmobya.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import it.visionmobya.R;
import it.visionmobya.activities.ClientListActivity;

public class CloserDocumentFragment extends Fragment implements DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener {

    private EditText accontoEuro, casualeDelTransporto, aspettoDeibeni, numroColli, transportoAcuraDi, note;

    private TextView  dataTransporto, oraTransporto;
    private Date pickedDate;

    private DatePickerDialog datePickerDialog;

    private TimePickerDialog timePickerDialog;
    private DateFormat dateFormat;
    private DateFormat timeFormat;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.closer_doc_fragment, container, false);
        //bejme inicializimin e gjithe komponenteve te deklaruara ne drawing time dhe i perdorim pasi behet draw ne onviewcreated
        initUI(view);
        return view;
    }


    private void setupDatePicker(){


        //auto vendos daten dhe oren e sotme tek textviewt

        setTodayDateAndTime();
        Calendar now = Calendar.getInstance();

         datePickerDialog = DatePickerDialog.newInstance (
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

        this.accontoEuro          = view.findViewById(R.id.accontoEuro);
        this.casualeDelTransporto = view.findViewById(R.id.casualeDelTransporto);
        this.aspettoDeibeni       = view.findViewById(R.id.aspettoDeibeni);
        this.numroColli           = view.findViewById(R.id.numroColli);
        this.transportoAcuraDi    = view.findViewById(R.id.transportoAcuraDi);
        this.dataTransporto       = view.findViewById(R.id.dataTransporto);
        this.oraTransporto        = view.findViewById(R.id.oraTransposto);
        this.note                 = view.findViewById(R.id.note);

        setupDatePicker();

        this.dataTransporto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datePickerDialog!=null){
                    datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
                }
            }
        });

        this.oraTransporto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timePickerDialog!=null){
                    timePickerDialog.show(getActivity().getFragmentManager(), "TimePickerDialog");
                }
            }
        });
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
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String secondString = second < 10 ? "0"+second : ""+second;
        String time = hourString+":"+minuteString;
        oraTransporto.setText(time);
    }

    private void setTodayDateAndTime(){
         dateFormat = new SimpleDateFormat("MM/dd/yyyy");
         timeFormat = new SimpleDateFormat("HH:mm");

        Date today = Calendar.getInstance().getTime();

        String dateString = dateFormat.format(today);
        String timeString = timeFormat.format(today);
        this.dataTransporto.setText(dateString);
        this.oraTransporto.setText(timeString);
    }
}
