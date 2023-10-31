package com.example.calculatorkraska;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editWidth, editHeight, editPaintUsage, editPaintLayers, editPaintVolume;
    private RadioGroup radioGroupReserve;
    private Button buttonCalculate;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация элементов интерфейса
        editWidth = findViewById(R.id.editWidth);
        editHeight = findViewById(R.id.editHeight);
        editPaintUsage = findViewById(R.id.editPaintUsage);
        editPaintLayers = findViewById(R.id.editPaintLayers);
        editPaintVolume = findViewById(R.id.editPaintVolume);
        radioGroupReserve = findViewById(R.id.radioGroupReserve);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textResult = findViewById(R.id.textResult);

        // Обработка нажатия кнопки "Рассчитать"
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение введенных данных
                double width = Double.parseDouble(editWidth.getText().toString());
                double height = Double.parseDouble(editHeight.getText().toString());
                double paintUsage = Double.parseDouble(editPaintUsage.getText().toString());
                int paintLayers = Integer.parseInt(editPaintLayers.getText().toString());
                double paintVolume = Double.parseDouble(editPaintVolume.getText().toString());

                // Вычисление расхода краски
                double totalArea = width * height;
                double totalPaintNeeded = paintUsage * totalArea * paintLayers;

                // Учет запаса
                int reservePercentage = getReservePercentage();
                double totalPaintWithReserve = totalPaintNeeded * (1 + (reservePercentage / 100.0));

                // Вывод результата
                textResult.setText("Итоговое количество краски: " + totalPaintWithReserve + " литров");
            }
        });
    }

    // Метод для получения выбранного процента запаса
    private int getReservePercentage() {
        int checkedRadioButtonId = radioGroupReserve.getCheckedRadioButtonId();
        switch (checkedRadioButtonId) {
            case R.id.radio10Percent:
                return 10;
            case R.id.radio15Percent:
                return 15;
            case R.id.radioNoReserve:
                return 0;
            default:
                return 0; // По умолчанию без запаса
        }
    }
}
