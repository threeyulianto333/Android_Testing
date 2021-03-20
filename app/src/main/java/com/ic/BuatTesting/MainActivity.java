package com.ic.BuatTesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.faltenreich.skeletonlayout.Skeleton;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialog;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btn_kirim;
    Skeleton skeleton;
    boolean skelOpen;
    String tanggal = null;
    Calendar today = Calendar.getInstance();
    int yearSelected,monthSelected;

//    ViewGroup rootView = (ViewGroup) ((ViewGroup) this
//            .findViewById(android.R.id.content)).getChildAt(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skelOpen = false;

        btn_kirim = findViewById(R.id.btn_kirim);

        // Either use an existing Skeletonlayout
        skeleton = findViewById(R.id.SkeletonLayout);

        skeleton.setShimmerAngle(20);
        skeleton.setShimmerDurationInMillis(500);
        skeleton.setShowShimmer(true);
        skeleton.showSkeleton();

        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(MainActivity.this,
                new MonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int selectedMonth, int selectedYear) { // on date set '
                    }
                }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

        builder.setActivatedMonth(Calendar.JULY)
                .setMinYear(1990)
                .setActivatedYear(today.get(Calendar.YEAR))
                .setMaxYear(today.get(Calendar.YEAR))
//                .setMinMonth(Calendar.FEBRUARY)
                .setTitle("Select trading month")
//                .setMonthRange(Calendar.FEBRUARY, Calendar.NOVEMBER)
                // .setMaxMonth(Calendar.OCTOBER)
                // .setYearRange(1890, 1890)
                // .setMonthAndYearRange(Calendar.FEBRUARY, Calendar.OCTOBER, 1890, 1890)
//                .showMonthOnly()
                // .showYearOnly()
                .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
                    @Override
                    public void onMonthChanged(int selectedMonth) { // on month selected
                        tanggal = null;
                        selectedMonth += 1;
                        Log.d("bulan", "bulan = " + selectedMonth + "-");
                        tanggal = selectedMonth + "-";
                    }
                })
                .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                    @Override
                    public void onYearChanged(int selectedYear) { // on year selected
                        tanggal += selectedYear;
                        Log.d("bulan", "tanggal get = " + tanggal);
                    }
                });
//                .build();
//                .show();

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMonYear();
//                builder.build().show();
//                ifTglNoChange(tanggal);
//                Log.d("bulan", "tanggal get = " + tanggal);
//                if (!skelOpen) {
//                    skeleton.showOriginal();
//                    skelOpen = true;
//                } else {
//                    skeleton.showSkeleton();
//                    skelOpen = false;
//                }
//                skeletonScreen.show();
            }
        });
    }

    private void ifTglNoChange(String tanggal) {
        if (tanggal == null) {
            tanggal = String.valueOf(today.get(Calendar.MONTH) + 1) + "-" + String.valueOf(today.get(Calendar.YEAR));
        }
        if (tanggal.length() < 3) {
            tanggal = tanggal + String.valueOf(today.get(Calendar.YEAR));
        }
        Log.d("Tanggal", "bulan = " + tanggal);
    }

    void initMonYear() {
        //Set default values
        Calendar calendar = Calendar.getInstance();
        yearSelected = calendar.get(Calendar.YEAR);
        monthSelected = calendar.get(Calendar.MONTH);

        MonthYearPickerDialogFragment dialogFragment = MonthYearPickerDialogFragment
                .getInstance(monthSelected, yearSelected);

        dialogFragment.show(getSupportFragmentManager(), null);

        dialogFragment.setOnDateSetListener(new MonthYearPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int year, int monthOfYear) {
                // do something
                monthOfYear += 1;
                Log.d("bulan", "bulan isi  = " + monthOfYear + "-" + year);
            }
        });
    }
}