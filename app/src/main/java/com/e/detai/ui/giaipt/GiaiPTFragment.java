package com.e.detai.ui.giaipt;

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
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class GiaiPTFragment extends Fragment {
    EditText txtInputA, txtInputB, txtInputC;
    Button btnOK;
    TextView txtThongBao, txtX1, txtX2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_giaipt, container, false);
        txtInputA = root.findViewById(R.id.txtInputA);
        txtInputB = root.findViewById(R.id.txtInputB);
        txtInputC = root.findViewById(R.id.txtInputC);
        txtThongBao = root.findViewById(R.id.txtGo);
        txtX1 = root.findViewById(R.id.txtX1);
        txtX2 = root.findViewById(R.id.txtX2);
        btnOK = root.findViewById(R.id.btnChange);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLy();
            }
        });
        return root;
    }

    public void xuLy() {
        DecimalFormat df = new DecimalFormat("#,###.###");
        if(!ktRong()) {
            if (txtInputA.getText().toString().equals("0")) {
                txtInputA.setError("A phải khác 0");
            } else {
                hideKeyboard(getActivity());
                BigDecimal delta;
                BigDecimal a, b, c, x1, x2;
                a = new BigDecimal(txtInputA.getText().toString());
                b = new BigDecimal(txtInputB.getText().toString());
                c = new BigDecimal(txtInputC.getText().toString());
                delta = b.multiply(b).subtract(a.multiply(c.multiply(BigDecimal.valueOf(4))));
                if (delta.compareTo(BigDecimal.valueOf(0)) < 0) {
                    txtThongBao.setText("Phương trình vô nghiệm");
                    txtX1.setText("");
                    txtX2.setText("");
                } else if (delta.compareTo(BigDecimal.valueOf(0)) == 0) {
                    txtThongBao.setText("Phương trình có nghiệm kép");
                    x1 = b.multiply(BigDecimal.valueOf(-1)).divide(a.multiply(BigDecimal.valueOf(2)));
                    txtX1.setText("X = " + df.format(x1));
                    txtX2.setText("");
                } else {
                    x1 = (b.multiply(BigDecimal.valueOf(-1)).add(Sqrt(delta))).divide(a.multiply(BigDecimal.valueOf(2)));
                    x2 = (b.multiply(BigDecimal.valueOf(-1)).subtract(Sqrt(delta))).divide(a.multiply(BigDecimal.valueOf(2)));
                    txtThongBao.setText("Phương trình có 2 nghiệm phân biệt");
                    txtX1.setText("X1 = " + df.format(x1));
                    txtX2.setText("X2 = " + df.format(x2));
                }
            }
        }
    }
        public boolean ktRong() {
            boolean kq = false;
            if (txtInputA.getText().toString().isEmpty()) {
                txtInputA.setError("Chưa nhập A");
                kq = true;
            }
            if (txtInputB.getText().toString().isEmpty()) {
                txtInputB.setError("Chưa nhập B");
                kq = true;
            }
            if (txtInputC.getText().toString().isEmpty()) {
                txtInputC.setError("Chưa nhập C");
                kq = true;
            }
            return kq;
        }



    private static final BigDecimal SQRT_DIG = new BigDecimal(150);
    private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());
    private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG.intValue(), RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());
        BigDecimal currentSquare = xn1.pow(2);
        BigDecimal currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        if (currentPrecision.compareTo(precision) <= -1){
            return xn1;
        }
        return sqrtNewtonRaphson(c, xn1, precision);
    }

    public static BigDecimal Sqrt(BigDecimal c){
        return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide(SQRT_PRE));
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
