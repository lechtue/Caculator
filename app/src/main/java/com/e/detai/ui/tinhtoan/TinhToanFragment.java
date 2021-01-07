package com.e.detai.ui.tinhtoan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.e.detai.R;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static java.lang.Math.sqrt;

public class TinhToanFragment extends Fragment {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, enter, add, sub, mul, div, clear, sq, sqrt, answer, nphai, ntrai, dec, percent, b00, delete;
    TextView txt1, txt2;
    Queue<String> hangDoi = new LinkedList<>();
    Stack<String> nganXep = new Stack<>();
    String phepTinh[];
    BigDecimal ans = new BigDecimal(0);

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_tinhtoan, container, false);
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
        b00 = root.findViewById(R.id.btn_00);
        nphai = root.findViewById(R.id.btnF);
        ntrai = root.findViewById(R.id.btnE);
        delete = root.findViewById(R.id.btnDelete);
        answer = root.findViewById(R.id.btnAns);
        add = root.findViewById(R.id.btnA);
        sub = root.findViewById(R.id.btnB);
        mul = root.findViewById(R.id.btnC);
        div = root.findViewById(R.id.btnD);
        sq = root.findViewById(R.id.btnPower);
        sqrt = root.findViewById(R.id.btnRoot);
        dec = root.findViewById(R.id.btnDecimal);
        enter = root.findViewById(R.id.btnEquals);
        clear = root.findViewById(R.id.btnClear);
        txt1 = root.findViewById(R.id.txtresult);
        txt2 = root.findViewById(R.id.txtformula);

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans != null)
                    txt1.setText(ans.toString());
                else
                    txt1.setText("0");
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("9");
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("0");
            }
        });
        b00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append("00");
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.append(".");
            }
        });
        ntrai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt2.append(txt1.getText().toString() + "( ");
                txt1.setText("");
            }
        });
        nphai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt2.append(txt1.getText().toString() + " )");
                txt1.setText("");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt2.append(txt1.getText().toString() + " + ");
                txt1.setText("");
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt2.append(txt1.getText().toString() + " - ");
                txt1.setText("");
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt2.append(txt1.getText().toString() + " * ");
                txt1.setText("");
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt2.append(txt1.getText().toString() + " / ");
                txt1.setText("");
            }
        });

        sq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt2.append(txt1.getText().toString() + " ^ ");
                txt1.setText("");
            }
        });

        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt2.append(txt1.getText().toString() + " √ ");
                txt1.setText("");
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allReset();
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ktInput()){
                    txt2.setText(txt1.getText().toString());
                    txt1.setText("");
                }
                else {
                    txt2.append(txt1.getText().toString());
                    thuatToan();
                    tinhToan();
                    txt1.setText("");
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeLastChar();
            }
        });
        return root;
    }

    public void removeLastChar() {
        String str = txt1.getText().toString();
        if (str.length() > 0) {
            txt1.setText(str.substring(0, str.length() - 1));
        }
    }

    public void allReset() {
        txt1.setText("");
        txt2.setText("");
        hangDoi = new LinkedList<>();
        nganXep = new Stack<>();
    }

    public void thuatToan() {
        phepTinh = xoaKhoangTrang(txt2.getText().toString()).split(" ");
        for (int i = 0; i < phepTinh.length; i++) {
            if (!ktToantu(phepTinh[i])) {
                hangDoi.offer(phepTinh[i]);
            } else {
                if (phepTinh[i].equals("(")) {
                    nganXep.push(phepTinh[i]);
                } else {
                    if (phepTinh[i].equals(")")) {
                        do {
                            if (!nganXep.peek().equals("(")) {
                                hangDoi.offer(nganXep.pop());
                            }
                        } while (!nganXep.peek().equals("("));
                        nganXep.pop();
                    } else {
                        while (!nganXep.isEmpty() && doUuTien(nganXep.peek()) >= doUuTien(phepTinh[i])) {
                            hangDoi.offer(nganXep.pop());
                        }
                        nganXep.push(phepTinh[i]);
                    }
                }
            }
        }
        while (!nganXep.isEmpty()) {
            hangDoi.offer(nganXep.pop());
        }
    }

    public int doUuTien(String c) {
        if (c.equals("+") || c.equals("-"))
            return 1;
        else if (c.equals("*") || c.equals("/"))
            return 2;
        else if (c.equals("^") || c.equals("√"))
            return 3;
        else
            return 0;
    }

    public boolean ktToantu(String s) {
        String operator[] = {"+", "-", "*", "/", ")", "(", "^", "√"};
        Arrays.sort(operator);
        if (Arrays.binarySearch(operator, s) > -1)
            return true;
        else return false;
    }
    public String xoaKhoangTrang(String c) {
        String a = c;
        if (c.contains("  ")) {
            a = c.replace("  ", " ");
        }
        return a;
    }
    public void tinhToan() {
        BigDecimal var1, var2;
        Integer var3;
        Double var4;
        do {
            if (!ktToantu(hangDoi.peek()))
                nganXep.push(hangDoi.poll());
            else {
                if (hangDoi.peek().equals("+")) {
                    var2 = new BigDecimal(nganXep.pop());
                    var1 = new BigDecimal(nganXep.pop());
                    ans = var1.add(var2);
                    nganXep.push(ans.toString());
                    hangDoi.poll();
                } else if (hangDoi.peek().equals("-")) {
                    var2 = new BigDecimal(nganXep.pop());
                    var1 = new BigDecimal(nganXep.pop());
                    ans = var1.subtract(var2);
                    nganXep.push(ans.toString());
                    hangDoi.poll();
                } else if (hangDoi.peek().equals("*")) {
                    var2 = new BigDecimal(nganXep.pop());
                    var1 = new BigDecimal(nganXep.pop());
                    ans = var1.multiply(var2);
                    nganXep.push(ans.toString());
                    hangDoi.poll();
                } else if (hangDoi.peek().equals("/")) {
                    var2 = new BigDecimal(nganXep.pop());
                    var1 = new BigDecimal(nganXep.pop());
                    ans = var1.divide(var2);
                    nganXep.push(ans.toString());
                    hangDoi.poll();
                } else if (hangDoi.peek().equals("^")) {
                    var3 = Integer.parseInt(nganXep.pop());
                    var1 = new BigDecimal(nganXep.pop());
                    ans = var1.pow(var3);
                    nganXep.push(ans.toString());
                    hangDoi.poll();
                } else if (hangDoi.peek().equals("√")) {
                    var4 = Double.parseDouble(nganXep.pop());
                    ans = BigDecimal.valueOf(sqrt(var4));
                    nganXep.push(ans.toString());
                    hangDoi.poll();
                }
            }
        }while (!hangDoi.isEmpty()) ;
        txt2.setText(ans.toString());
    }
    public boolean ktInput(){
        String input=txt2.getText().toString();
        if(input.isEmpty())
            return false;
        else
            return true;
    }
}

