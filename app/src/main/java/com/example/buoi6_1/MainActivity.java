package com.example.buoi6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtMSSV,txtHoTen,txtNamSinh;
    Button btnGuiThongTin,btnEdit;
    ListView lvDssv;
    ArrayList<SinhVien> sinhViens = new ArrayList<>();
    int index = -1;

    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls(){
        txtMSSV = findViewById(R.id.txtMSSV);
        txtHoTen = findViewById(R.id.txtHoten);
        txtNamSinh = findViewById(R.id.txtNamSinh);
        btnGuiThongTin = findViewById(R.id.btnGuiThongTin);
        btnEdit = findViewById(R.id.btnEdit);
        lvDssv = findViewById(R.id.Lvdssv);
        showListView();
    }
    private void showListView(){
        arrayAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_single_choice,
                sinhViens
        );
        lvDssv.setAdapter(arrayAdapter);
    }
    private void addEvents(){
        btnGuiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msv = txtMSSV.getText().toString();
                String hoTen = txtHoTen.getText().toString();
                int namSinh = Integer.parseInt(txtNamSinh.getText().toString());
                SinhVien sv = new SinhVien(msv,hoTen,namSinh);
                sinhViens.add(sv);
                txtMSSV.setText("");
                txtHoTen.setText("");
                txtNamSinh.setText("");
                showListView();

            }
        });
        lvDssv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index=i;
                txtMSSV.setText(sinhViens.get(i).getMsv());
                txtHoTen.setText(sinhViens.get(i).getHoTen());
                txtNamSinh.setText(sinhViens.get(i).getNamSinh()+"");
                Toast.makeText(MainActivity.this, sinhViens.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });
        lvDssv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                sinhViens.remove(i);
                showListView();
                return true;
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra dữ liệu click
                if (index == -1) {
                    return;
                }
                String msv = txtMSSV.getText().toString();
                String hoTen = txtHoTen.getText().toString();
                int namSinh = Integer.parseInt(txtNamSinh.getText().toString());
                sinhViens.get(index).setNamSinh(namSinh);
                sinhViens.get(index).setHoTen(hoTen);
                sinhViens.get(index).setMsv(msv);
                txtMSSV.setText("");
                txtHoTen.setText("");
                txtNamSinh.setText("");
                index = -1;
                showListView();
            }
        });

    }
}