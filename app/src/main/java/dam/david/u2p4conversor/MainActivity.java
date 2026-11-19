package dam.david.u2p4conversor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LogsConversor";

    private Spinner spinnerConversion;
    private ImageButton btnAyuda;
    private TextView textViewTitulo;
    private EditText etValor;
    private Button buttonConvertir;
    private TextView tvResultado;
    private TextView mensajeError;

    private ConversorViewModel viewModel;

    private final String[] opcionesConversion = {
            "Pulgadas -> Centímetros",
            "Centímetros -> Pulgadas",
            "Kilómetros -> Millas",
            "Millas -> Kilómetros",
            "Kilogramos -> Libras",
            "Libras -> Kilogramos"
    };

    private final String[] titulos = {
            "Convertir de Pulgadas a Centímetros",
            "Convertir de Centímetros a Pulgadas",
            "Convertir de Kilómetros a Millas",
            "Convertir de Millas a Kilómetros",
            "Convertir de Kilogramos a Libras",
            "Convertir de Libras a Kilogramos"
    };

    private final String[] hintsEntrada = {
            "Ingrese pulgadas",
            "Ingrese centímetros",
            "Ingrese kilómetros",
            "Ingrese millas",
            "Ingrese kilogramos",
            "Ingrese libras"
    };

    private final String[] hintsResultado = {
            "Resultado en centímetros",
            "Resultado en pulgadas",
            "Resultado en millas",
            "Resultado en kilómetros",
            "Resultado en libras",
            "Resultado en kilogramos"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Activity creada");

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(ConversorViewModel.class);

        setUI();
        observarViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Activity iniciada (a punto de ser visible)");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: Activity visible y en primer plano");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: Activity pausada (pierde el foco)");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: Activity detenida (ya no es visible)");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: Activity reiniciándose tras estar detenida");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Activity destruida");
    }

    private void setUI() {
        spinnerConversion = findViewById(R.id.spinner_Conversion);
        btnAyuda = findViewById(R.id.btn_Ayuda);
        textViewTitulo = findViewById(R.id.textView);
        etValor = findViewById(R.id.et_Pulgada);
        buttonConvertir = findViewById(R.id.button_Convertir);
        tvResultado = findViewById(R.id.et_Resultado);
        mensajeError = findViewById(R.id.textError);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, opcionesConversion);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversion.setAdapter(adapter);

        spinnerConversion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setTipoConversionIndex(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        buttonConvertir.setOnClickListener(view -> {
            Log.d(TAG, "Botón pulsado: buttonConvertir (" + buttonConvertir.getText() + ")");
            String texto = etValor.getText().toString();
            int tipo = spinnerConversion.getSelectedItemPosition();
            try {
                switch (tipo) {
                    case 0:
                        viewModel.convertirPulgadasACm(texto);
                        break;
                    case 1:
                        viewModel.convertirCmAPulgadas(texto);
                        break;
                    case 2:
                        viewModel.convertirKmAMillas(texto);
                        break;
                    case 3:
                        viewModel.convertirMillasAKm(texto);
                        break;
                    case 4:
                        viewModel.convertirKgALibras(texto);
                        break;
                    case 5:
                        viewModel.convertirLibrasAKg(texto);
                        break;
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage() != null ? e.getMessage() : "Error desconocido");
            }
        });

        btnAyuda.setOnClickListener(view -> {
            Log.d(TAG, "Botón pulsado: btnAyuda");
            Intent intent = new Intent(MainActivity.this, Ayuda.class);
            startActivity(intent);
        });
    }

    private void observarViewModel() {
        viewModel.getTipoConversionIndex().observe(this, index -> {
            if (spinnerConversion.getSelectedItemPosition() != index) {
                spinnerConversion.setSelection(index);
            }
            textViewTitulo.setText(titulos[index]);
            etValor.setHint(hintsEntrada[index]);
            tvResultado.setHint(hintsResultado[index]);
        });

        viewModel.getResultadoConversion().observe(this, resultado -> {
            tvResultado.setText(resultado);
        });

        viewModel.getMensajeError().observe(this, error -> {
            if (error == null) {
                mensajeError.setVisibility(View.GONE);
            } else {
                mensajeError.setText(error);
                mensajeError.setVisibility(View.VISIBLE);
            }
        });
    }
}