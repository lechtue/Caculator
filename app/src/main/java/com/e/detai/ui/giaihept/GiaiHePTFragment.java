package com.e.detai.ui.giaihept;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.e.detai.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class GiaiHePTFragment extends Fragment {
    Button btnGiai;
    TextView txtX, txtY, txtKQ;
    EditText txta, txtb, txtc, txtd, txte, txtf;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_giaihept, container, false);
        btnGiai = root.findViewById(R.id.btnGiaiHePT);
        txtX = root.findViewById(R.id.txtkqX);
        txtY = root.findViewById(R.id.txtkqY);
        txtKQ = root.findViewById(R.id.txtkqThongBao);
        txta = root.findViewById(R.id.txtA);
        txtb = root.findViewById(R.id.txtB);
        txtc = root.findViewById(R.id.txtC);
        txtd = root.findViewById(R.id.txtD);
        txte = root.findViewById(R.id.txtE);
        txtf = root.findViewById(R.id.txtF);
        btnGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLy();
            }
        });
        return root;
    }

    public void xuLy() {
        DecimalFormat df = new DecimalFormat("#,###.###");
        if (!ktRong()) {
            hideKeyboard(getActivity());
            BigDecimal dx, dy, dd, a, b, c, d, e, f;
            a = new BigDecimal(txta.getText().toString());
            b = new BigDecimal(txtb.getText().toString());
            c = new BigDecimal(txtc.getText().toString());
            d = new BigDecimal(txtd.getText().toString());
            e = new BigDecimal(txte.getText().toString());
            f = new BigDecimal(txtf.getText().toString());
 /*
 *  dd = a*e - d*b
    dx = e*c - f*b
    dy = a*f - d*c */

            dd=(a.multiply(e)).subtract(d.multiply(b));
            dx=(e.multiply(c)).subtract(f.multiply(b));
            dy=(a.multiply(f)).subtract(d.multiply(c));
            if (dd.compareTo(BigDecimal.valueOf(0)) == 0) {
                if ((dx.add(dy)).compareTo(BigDecimal.valueOf(0)) == 0) {
                    txtKQ.setText("Hệ phương trình có vô số nghiệm!");
                    txtX.setText("");
                    txtY.setText("");
                } else {
                    txtKQ.setText("Hệ phương trình vô nghiệm!");
                    txtX.setText("");
                    txtY.setText("");
                }
            } else {
                txtKQ.setText("Hệ phương trình có nghiệm");
                txtX.setText("X = " + df.format(dx.divide(dd)));
                txtY.setText("Y = " + df.format(dy.divide(dd)));
            }
        }
    }

    public boolean ktRong() {
        boolean kq = false;
        if (txta.getText().toString().isEmpty()) {
            txta.setError("Chưa nhập A");
            kq = true;
        }
        if (txtb.getText().toString().isEmpty()) {
            txtb.setError("Chưa nhập B");
            kq = true;
        }
        if (txtc.getText().toString().isEmpty()) {
            txtc.setError("Chưa nhập C");
            kq = true;
        }
        if (txtd.getText().toString().isEmpty()) {
            txtd.setError("Chưa nhập D");
            kq = true;
        }
        if (txte.getText().toString().isEmpty()) {
            txte.setError("Chưa nhập E");
            kq = true;
        }
        if (txtf.getText().toString().isEmpty()) {
            txtf.setError("Chưa nhập F");
            kq = true;
        }
        return kq;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
