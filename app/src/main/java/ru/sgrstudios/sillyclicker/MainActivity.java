package ru.sgrstudios.sillyclicker;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView clicks;
    ImageButton btn;
    Button gitBtn;
    int count = 0;
    RadioGroup cats;
    RadioButton millyBtn, uniBtn, lunaBtn, lunarBtn;

    /* ActivityResultLauncher<String[]> mPermResLaunch;
    private boolean isWritePermissionGranted = false;
    private boolean isReadPermissionGranted = false;

    private boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void writeData() {
        if (isExternalStorageWritable() && checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            File file = new File(Environment.getExternalStorageDirectory(), "data.txt");

            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(String.valueOf(count).getBytes());
                fos.close();
                Toast.makeText(this, "File saved!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Error saving file.", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadData() {
        File file = new File(Environment.getExternalStorageDirectory(), "data.txt");
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String line = reader.readLine();
                if (line != null) {
                    count = Integer.parseInt(line);
                    clicks.setText(String.valueOf(count));
                }
                reader.close();
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    } //помогите, я уже 16 часов пытаюсь сделать это

    private void requestPerm() {
        isWritePermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        isReadPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        List<String> permReq = new ArrayList<String>();

        if (!isWritePermissionGranted) {
            permReq.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!isReadPermissionGranted) {
            permReq.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!permReq.isEmpty()) {
            mPermResLaunch.launch(permReq.toArray(new String[0]));
        }
    } */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*mPermResLaunch = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> o) {
                if (o.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != null) {
                    isWritePermissionGranted = Boolean.TRUE.equals(o.get(Manifest.permission.WRITE_EXTERNAL_STORAGE));
                }

                if (o.get(Manifest.permission.READ_EXTERNAL_STORAGE) != null) {
                    isReadPermissionGranted = Boolean.TRUE.equals(o.get(Manifest.permission.READ_EXTERNAL_STORAGE));
                }
            }
        });*/

        //Другие кнопки
        clicks = findViewById(R.id.textView2);
        btn = findViewById(R.id.btn);
        gitBtn = findViewById(R.id.button2);
        //resetBtn = findViewById(R.id.resetBtn); //💀

        //Кошики
        cats = findViewById(R.id.radioGroup);
        millyBtn = findViewById(R.id.millyBtn);
        uniBtn = findViewById(R.id.uniBtn);
        lunaBtn = findViewById(R.id.lunaBtn);
        lunarBtn = findViewById(R.id.lunarBtn);

        //loadData();
        //requestPerm();

        //Кнопка основная
        btn.setOnClickListener(v -> {
            count++;
            clicks.setText(String.valueOf(count));
            //writeData();
        });

        //Гитхаб кнопка
        gitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect = new Intent(Intent.ACTION_VIEW);
                redirect.setData(Uri.parse("https://github.com/The-SGR"));
                startActivity(redirect);
            }
        });

        //Кошики :3
        cats.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.millyBtn) {
                    btn.setImageResource(R.drawable.milly);
                } else if (checkedId == R.id.uniBtn) {
                    btn.setImageResource(R.drawable.uni);
                } else if (checkedId == R.id.lunaBtn) {
                    btn.setImageResource(R.drawable.lunaistabby);
                } else if (checkedId == R.id.lunarBtn) {
                    btn.setImageResource(R.drawable.lunar);
                }
            }
        });

    }

}