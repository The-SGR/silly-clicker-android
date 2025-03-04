package ru.sgrstudios.sillyclicker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView clicks;
    ImageButton btn;
    Button gitBtn, resetBtn;
    int count = 0;
    private static final String FILE_NAME = "data.txt";
    private boolean isResetRequested = false;
    private final Handler resetHandler = new Handler();
    RadioGroup cats;
    RadioButton millyBtn, uniBtn, lunaBtn, lunarBtn;



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

        //Другие кнопки
        clicks = findViewById(R.id.textView2);
        btn = findViewById(R.id.btn);
        gitBtn = findViewById(R.id.button2);
        resetBtn = findViewById(R.id.resetBtn); //💀

        //Кошики
        cats = findViewById(R.id.radioGroup);
        millyBtn = findViewById(R.id.millyBtn);
        uniBtn = findViewById(R.id.uniBtn);
        lunaBtn = findViewById(R.id.lunaBtn);
        lunarBtn = findViewById(R.id.lunarBtn);

        count = loadCount();
        clicks.setText(String.valueOf(count));

        //Кнопка основная
        btn.setOnClickListener(v -> {
            count++;
            clicks.setText(String.valueOf(count));
            saveCount(count);
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

        resetBtn.setOnClickListener(v -> {
            if (isResetRequested) {
                //
                count = 0;
                clicks.setText(String.valueOf(count));
                deleteFile(FILE_NAME);
                Toast.makeText(this, "Progress reset", Toast.LENGTH_SHORT).show();
                isResetRequested = false;
                resetHandler.removeCallbacksAndMessages(null);
            } else {
                isResetRequested = true;
                Toast.makeText(this, "Click one more time to reset progress", Toast.LENGTH_SHORT).show();
                resetHandler.postDelayed(() -> {
                    isResetRequested = false;
                    Toast.makeText(this, "Reset canceled", Toast.LENGTH_SHORT).show();
                }, 3000);
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

    //
    private void saveCount(int count) {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(String.valueOf(count).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int loadCount() {
        try (FileInputStream fis = openFileInput(FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    } //дипсику и стаковерфлоу руки целовал

} //спасибо за помощь, Fartunruss!