package layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.li.criminalintent.Crime;
import com.example.li.criminalintent.CrimeLab;
import com.example.li.criminalintent.R;

import java.util.Calendar;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static final String EXTRA_CRIME_ID = "com.example.li.criminalintent.crime_id";
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        UUID crimeId;
        if (getArguments() == null) {
            crimeId = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        } else {
            crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        }
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getmTitle());

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        Calendar tempCalendar = mCrime.getmCalendar();
        int year = tempCalendar.get(Calendar.YEAR);
        int month = tempCalendar.get(Calendar.MONTH) + 1;
        int day = tempCalendar.get(Calendar.DAY_OF_MONTH);
        int hour = tempCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = tempCalendar.get(Calendar.MINUTE);
        mDateButton.setText(String.format("%d年%d月%d日 %02d:%02d", year, month, day, hour, minute));
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //正确的设置DatePickerDialog代码
//                Calendar c = Calendar.getInstance();
//
//                new DatePickerDialog(getActivity(),
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                mDateButton.setText(String.format("%d年%d月%d日", year, month+1, dayOfMonth));
//                                Calendar mDate = Calendar.getInstance();
//                                mDate.set(year, month, dayOfMonth);
//                                mCrime.setmDate(mDate.getTime());
//                            }
//                        },
//                        c.get(Calendar.YEAR),
//                        c.get(Calendar.MONTH),
//                        c.get(Calendar.DAY_OF_MONTH)).show();

                // 尝试设置点击弹出列表，选择DatePickerdialog或TimePickerDialog
                new AlertDialog.Builder(getContext())
                        .setTitle("操作选项")
                        .setItems(new CharSequence[]{"选择日期", "选择时间"},
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Calendar c = Calendar.getInstance();
                                        switch (which) {
                                            case 0:
                                                ChooseDate(c);
                                                break;
                                            case 1:
                                                ChooseTime(c);
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                })
                        .setNegativeButton("取消", null)
                        .show();

            }
        });


        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setmSolved(isChecked);
            }
        });

        return v;
    }

    public void ChooseDate(Calendar c) {
        new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Calendar mCalendar = mCrime.getmCalendar();
                        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
                        int minute = mCalendar.get(Calendar.MINUTE);
                        mCalendar.set(year, month, dayOfMonth);
                        mDateButton.setText(String.format("%d年%d月%d日 %02d:%02d", year, month+1, dayOfMonth, hour, minute));
                    }
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void ChooseTime(Calendar c) {
        new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar mCalendar = mCrime.getmCalendar();
                        int year = mCalendar.get(Calendar.YEAR);
                        int month = mCalendar.get(Calendar.MONTH);
                        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
                        mCalendar.set(year, month, day, hourOfDay, minute);
                        mDateButton.setText(String.format("%d年%d月%d日 %02d:%02d", year, month + 1, day, hourOfDay, minute));
                    }
                },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                true).show();
    }

    public void returnResult() {
        getActivity().setResult(Activity.RESULT_OK, null);
    }

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
