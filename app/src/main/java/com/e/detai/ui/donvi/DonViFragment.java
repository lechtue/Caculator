package com.e.detai.ui.donvi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.e.detai.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DonViFragment extends Fragment {
    private String[] types = {
            "Độ dài", "Thời gian", "Nhiệt độ","Khối lượng","Vận tốc"
    };
    private String[] length = {
            "KM", "HM", "DaM", "M", "DM", "CM", "mM", "uM"
    };
    private String[] time = {
            "Năm", "Tuần", "Ngày", "Giờ", "Phút", "Giây"
    };
    private String[] temp = {
            "Độ C", "Độ F", "Độ K"
    };
    private String[] weight = {
            "Tấn", "Tạ", "Yến", "KG", "HG", "DaG", "G", "mG"
    };
    private String[] speed = {
            "Km/h", "M/s"
    };

    private BigDecimal[][] ratiolenghweight = {
            {BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(1000), BigDecimal.valueOf(10000), BigDecimal.valueOf(100000), BigDecimal.valueOf(1000000), BigDecimal.valueOf(1000000000)},
            {BigDecimal.valueOf(0.1), BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(1000), BigDecimal.valueOf(10000), BigDecimal.valueOf(100000), BigDecimal.valueOf(100000000)},
            {BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.1), BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(1000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000000)},
            {BigDecimal.valueOf(0.001), BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.1), BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(1000), BigDecimal.valueOf(1000000)},
            {BigDecimal.valueOf(0.0001), BigDecimal.valueOf(0.001), BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.1), BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(100000)},
            {BigDecimal.valueOf(0.00001), BigDecimal.valueOf(0.0001), BigDecimal.valueOf(0.001), BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.1), BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(10000)},
            {BigDecimal.valueOf(0.000001), BigDecimal.valueOf(0.00001), BigDecimal.valueOf(0.0001), BigDecimal.valueOf(0.001), BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.1), BigDecimal.valueOf(1), BigDecimal.valueOf(1000)},
            {BigDecimal.valueOf(0.000000001), BigDecimal.valueOf(0.00000001), BigDecimal.valueOf(0.0000001), BigDecimal.valueOf(0.000001), BigDecimal.valueOf(0.00001), BigDecimal.valueOf(0.0001), BigDecimal.valueOf(0.001), BigDecimal.valueOf(1)},
    };
    private BigDecimal[][] ratiotime = {
            {BigDecimal.valueOf(1), BigDecimal.valueOf(52.179), BigDecimal.valueOf(365.25), BigDecimal.valueOf(8766), BigDecimal.valueOf(525960), BigDecimal.valueOf(31557600)},
            {BigDecimal.valueOf(0.019), BigDecimal.valueOf(1), BigDecimal.valueOf(7), BigDecimal.valueOf(168), BigDecimal.valueOf(10080), BigDecimal.valueOf(604800)},
            {BigDecimal.valueOf(0.003), BigDecimal.valueOf(0.143), BigDecimal.valueOf(1), BigDecimal.valueOf(24), BigDecimal.valueOf(1440), BigDecimal.valueOf(86400)},
            {BigDecimal.valueOf(0.0001), BigDecimal.valueOf(0.006), BigDecimal.valueOf(0.042), BigDecimal.valueOf(1), BigDecimal.valueOf(60), BigDecimal.valueOf(3600)},
            {BigDecimal.valueOf(0.000002), BigDecimal.valueOf(0.0001), BigDecimal.valueOf(0.0007), BigDecimal.valueOf(0.017), BigDecimal.valueOf(1), BigDecimal.valueOf(60)},
            {BigDecimal.valueOf(0.00000003), BigDecimal.valueOf(0.000002), BigDecimal.valueOf(0.000012), BigDecimal.valueOf(0.0003), BigDecimal.valueOf(0.017), BigDecimal.valueOf(1)},
    };
    private BigDecimal[][] ratiotemp = {
            {BigDecimal.valueOf(1), BigDecimal.valueOf(33.8), BigDecimal.valueOf(274.15)},
            {BigDecimal.valueOf(-17.22), BigDecimal.valueOf(1), BigDecimal.valueOf(255.93)},
            {BigDecimal.valueOf(-272.15), BigDecimal.valueOf(-457.87), BigDecimal.valueOf(1)},
    };
    private BigDecimal[][] ratiotemp0 = {
            {BigDecimal.valueOf(0), BigDecimal.valueOf(32), BigDecimal.valueOf(273.15)},
            {BigDecimal.valueOf(-17.78), BigDecimal.valueOf(0), BigDecimal.valueOf(255.37)},
            {BigDecimal.valueOf(-273.15), BigDecimal.valueOf(-459.67), BigDecimal.valueOf(0)},
    };
    private BigDecimal[][] ratiospeed = {
            {BigDecimal.valueOf(1), BigDecimal.valueOf(0.2778)},
            {BigDecimal.valueOf(3.6), BigDecimal.valueOf(1)},
    };

    private EditText txtNumber;
    private Spinner spnUnits,spnType;
    private TextView[] lblResults,lblUnits;
    private ArrayAdapter<String> unit;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_donvi, container, false);



        txtNumber = root.findViewById(R.id.txtInput);
        spnUnits = root.findViewById(R.id.spnUnit);
        spnType = root.findViewById(R.id.spnType);
        lblUnits = new TextView[]{
                root.findViewById(R.id.lbl1),
                root.findViewById(R.id.lbl2),
                root.findViewById(R.id.lbl3),
                root.findViewById(R.id.lbl4),
                root.findViewById(R.id.lbl5),
                root.findViewById(R.id.lbl6),
                root.findViewById(R.id.lbl7),
                root.findViewById(R.id.lbl8),
        };
        lblResults = new TextView[]{
                root.findViewById(R.id.txtHex),
                root.findViewById(R.id.txtDec),
                root.findViewById(R.id.txtBin),
                root.findViewById(R.id.lblKq4),
                root.findViewById(R.id.lblKq5),
                root.findViewById(R.id.lblKq6),
                root.findViewById(R.id.lblKq7),
                root.findViewById(R.id.lblKq8),
        };
        ArrayAdapter<String> type = new ArrayAdapter<String>(
                root.getContext(), R.layout.support_simple_spinner_dropdown_item,types
        );
        type.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnType.setAdapter(type);
        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(28);
                int i = changeType();
                if(i==0) {
                    reset();
                    unit = new ArrayAdapter<String>(
                            root.getContext(), R.layout.support_simple_spinner_dropdown_item, length
                    );
                    unit.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spnUnits.setAdapter(unit);
                    addUnit(length);
                }
                else if(i==1) {
                    reset();;
                    unit = new ArrayAdapter<String>(
                            root.getContext(), R.layout.support_simple_spinner_dropdown_item, time
                    );
                    unit.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spnUnits.setAdapter(unit);
                    addUnit(time);
                }
                else if(i==2) {
                    reset();;
                    unit = new ArrayAdapter<String>(
                            root.getContext(), R.layout.support_simple_spinner_dropdown_item, temp
                    );
                    unit.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spnUnits.setAdapter(unit);
                    addUnit(temp);
                }
                else if(i==3) {
                    reset();;
                    unit = new ArrayAdapter<String>(
                            root.getContext(), R.layout.support_simple_spinner_dropdown_item, weight
                    );
                    unit.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spnUnits.setAdapter(unit);
                    addUnit(weight);
                }
                else if(i==4) {
                    reset();;
                    unit = new ArrayAdapter<String>(
                            root.getContext(), R.layout.support_simple_spinner_dropdown_item, speed
                    );
                    unit.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spnUnits.setAdapter(unit);
                    addUnit(speed);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spnUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(24);
                int i = changeType();
                if (i == 0) {
                    changeUnit(ratiolenghweight);
                } else if (i == 1) {
                    changeUnit(ratiotime);
                } else if (i == 2) {
                    changeTempUnit();
                } else if (i == 3) {
                    changeUnit(ratiolenghweight);
                }else if (i == 4) {
                    changeUnit(ratiospeed);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        txtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int i = changeType();
                if (i == 0) {
                    changeUnit(ratiolenghweight);
                } else if (i == 1) {
                    changeUnit(ratiotime);
                } else if (i == 2) {
                    changeTempUnit();
                } else if (i == 3) {
                    changeUnit(ratiolenghweight);
                } else if (i == 4) {
                    changeUnit(ratiospeed);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return root;
    }

    private int changeType() {
        int rowIdx = spnType.getSelectedItemPosition();
        if (rowIdx < 0) {
            rowIdx = 0;
        }
        return rowIdx;
    }

    public void reset(){
        for (int i = 0; i < 8; i++) {
            lblUnits[i].setText("");
            lblResults[i].setText("");
        }
    }
    private void addUnit(String[] unit) {
        for (int i = 0; i < unit.length; i++) {
            lblUnits[i].setText(unit[i]);
        }
    }

    private void changeUnit(BigDecimal[][] ratio) {
        DecimalFormat df = new DecimalFormat("#,###.############");
        int rowIdx = spnUnits.getSelectedItemPosition();

        if (rowIdx < 0) rowIdx = 0;
        String input = txtNumber.getText().toString();

        if (input.isEmpty()) input = "0";
        BigDecimal number = new BigDecimal(input);
        for (int i = 0; i < spnUnits.getCount(); i++) {
            BigDecimal temp = number.multiply(ratio[rowIdx][i]);
            lblResults[i].setText(df.format(temp));
        }
    }

    private void changeTempUnit() {
        DecimalFormat df = new DecimalFormat("#,###.############");
        int rowIdx = spnUnits.getSelectedItemPosition();

        if (rowIdx < 0) rowIdx = 0;
        String input = txtNumber.getText().toString();

        if (input.isEmpty()) input = "0";
        BigDecimal number = new BigDecimal(input);
        if (input.equals("0")) {
            for (int i = 0; i < spnUnits.getCount(); i++) {
                BigDecimal temp = ratiotemp0[rowIdx][i];
                lblResults[i].setText(df.format(temp));
            }
        } else {
            for (int i = 0; i < spnUnits.getCount(); i++) {
                BigDecimal temp = number.multiply(ratiotemp[rowIdx][i]);
                lblResults[i].setText(df.format(temp));
            }
        }
    }
}