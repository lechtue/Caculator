package com.e.detai.ui.heso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.e.detai.R;

public class HeSoFragment extends Fragment {

    private String[] Unit = {
            "HEX", "DEC", "BIN"
    };
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, enter, bA, bB, bC, bD, bE, bF, delete, clear;
    TextView txtInput, txtHex, txtDec, txtBin;
    Spinner spnUnit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_heso, container, false);

        b1 = root.findViewById(R.id.btn_1);
        b2 = root.findViewById(R.id.btn_2);
        b3 = root.findViewById(R.id.btn_3);
        b4 = root.findViewById(R.id.btn_4);
        b5 = root.findViewById(R.id.btn_5);
        b6 = root.findViewById(R.id.btn_6);
        b7 = root.findViewById(R.id.btn_7);
        b8 = root.findViewById(R.id.btn_8);
        b9 = root.findViewById(R.id.btn_9);
        b0 = root.findViewById(R.id.btn_0);
        bA = root.findViewById(R.id.btnA);
        bF = root.findViewById(R.id.btnF);
        bE = root.findViewById(R.id.btnE);
        delete = root.findViewById(R.id.btnDelete);
        bB = root.findViewById(R.id.btnB);
        bC = root.findViewById(R.id.btnC);
        bD = root.findViewById(R.id.btnD);
        spnUnit = root.findViewById(R.id.spnUnit);
        enter = root.findViewById(R.id.btnEquals);
        clear = root.findViewById(R.id.btnClear);
        txtInput = root.findViewById(R.id.txtInput);
        txtHex = root.findViewById(R.id.txtHex);
        txtDec = root.findViewById(R.id.txtDec);
        txtBin = root.findViewById(R.id.txtBin);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("9");
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("0");
            }
        });
        bA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("A");
            }
        });

        bB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("B");
            }
        });

        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("C");
            }
        });
        bD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("D");
            }
        });
        bE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("E");
            }
        });
        bF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.append("F");
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAll();
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtInput.getText().toString().isEmpty()) {
                    txtInput.setError("Nhập dữ liệu");
                }
                else {
                    int i = changeUnit();
                    if (i == 0) {
                        hextobin();
                        hextodec();
                        txtHex.setText(txtInput.getText().toString());
                    } else if (i == 1) {
                        dectobin();
                        dectohex();
                        txtDec.setText(txtInput.getText().toString());
                    } else if (i == 2) {
                        bintodec();
                        bintohex();
                        txtBin.setText(txtInput.getText().toString());
                    }
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeLastChar();
            }
        });

        ArrayAdapter<String> type = new ArrayAdapter<String>(
                root.getContext(), R.layout.support_simple_spinner_dropdown_item, Unit
        );
        type.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnUnit.setAdapter(type);
        spnUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int i = changeUnit();
                if (i == 0) {
                    inPutHex();
                    resetAll();
                } else if (i == 1) {
                    inPutDec();
                    resetAll();
                } else if (i == 2) {
                    inPutBin();
                    resetAll();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        return root;
    }

    public void removeLastChar() {
        String str = txtInput.getText().toString();
        if (str.length() > 0) {
            txtInput.setText(str.substring(0, str.length() - 1));
        }
    }

    private int changeUnit() {
        int rowIdx = spnUnit.getSelectedItemPosition();
        if (rowIdx < 0) {
            rowIdx = 0;
        }
        return rowIdx;
    }

    public void inPutHex() {
        b1.setEnabled(true);
        bF.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b0.setEnabled(true);
        bA.setEnabled(true);
        bB.setEnabled(true);
        bC.setEnabled(true);
        bD.setEnabled(true);
        bE.setEnabled(true);
    }

    public void inPutDec() {
        b1.setEnabled(true);
        bF.setEnabled(false);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b0.setEnabled(true);
        bA.setEnabled(false);
        bB.setEnabled(false);
        bC.setEnabled(false);
        bD.setEnabled(false);
        bE.setEnabled(false);
    }

    public void inPutBin() {
        b1.setEnabled(true);
        bF.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);
        b0.setEnabled(true);
        bA.setEnabled(false);
        bB.setEnabled(false);
        bC.setEnabled(false);
        bD.setEnabled(false);
        bE.setEnabled(false);
    }

    public void resetAll() {
        txtInput.setText("");
        txtHex.setText("");
        txtDec.setText("");
        txtBin.setText("");
    }

    public void bintodec() {
        String binaryString = txtInput.getText().toString();
        int a = Integer.parseInt(binaryString, 2);
        txtDec.setText(Integer.toString(a));
    }
    public void bintohex() {
        String binaryString = txtInput.getText().toString();
        int a = Integer.parseInt(binaryString, 2);
        String hexa = Integer.toString(a, 16);
        txtHex.setText(hexa);
    }
    public void dectobin() {
        int x = Integer.parseInt(txtInput.getText().toString());
        txtBin.setText(Integer.toBinaryString(x));
    }
    public void dectohex() {
        int x = Integer.parseInt(txtInput.getText().toString());
        txtHex.setText(Integer.toHexString(x));
    }
    public void hextodec() {
        String hexa = txtInput.getText().toString();
        int decimal = Integer.parseInt(hexa, 16);
        txtDec.setText(Integer.toString(decimal));
    }
    public void hextobin() {
        String hexa = txtInput.getText().toString();
        int decimal = Integer.parseInt(hexa, 16);
        txtBin.setText(Integer.toBinaryString(decimal));
    }
}