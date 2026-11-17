package dam.david.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private TextView textViewPulgada;
    private EditText etPulgada;
    private Button buttonConvertir;
    private TextView tvResultadoPulgadaToCm;


    private TextView textViewCm;
    private EditText etCentimetro;
    private Button buttonConvertirCm;
    private TextView tvResultadoCmToPulgada;

    private Button buttonOcultarBloque;

    private boolean mostrandoBloque1 = true;

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

        setUI();
    }

    private void setUI() {


        textViewPulgada = findViewById(R.id.textView);
        etPulgada = findViewById(R.id.et_Pulgada);
        buttonConvertir = findViewById(R.id.button_Convertir);
        tvResultadoPulgadaToCm = findViewById(R.id.et_Resultado);


        textViewCm = findViewById(R.id.textViewCm);
        etCentimetro = findViewById(R.id.et_Centimetro);
        buttonConvertirCm = findViewById(R.id.button_ConvertirCm);
        tvResultadoCmToPulgada = findViewById(R.id.et_ResultadoPulgada);
        buttonOcultarBloque = findViewById(R.id.button_OcultarBloque);


        mostrarBloqueUno();


        buttonConvertir.setOnClickListener(view -> {
            try {
                String texto = etPulgada.getText().toString();
                if (!texto.isEmpty()) {
                    tvResultadoPulgadaToCm.setText(convertirPulgadasACm(texto));
                } else {
                    tvResultadoPulgadaToCm.setText("");
                }
            } catch (Exception e) {
                Log.e("LogsConversor", e.getMessage() != null ? e.getMessage() : "Error desconocido");
            }
        });


        buttonConvertirCm.setOnClickListener(view -> {
            try {
                String texto = etCentimetro.getText().toString();
                if (!texto.isEmpty()) {
                    tvResultadoCmToPulgada.setText(convertirCmAPulgadas(texto));
                } else {
                    tvResultadoCmToPulgada.setText("");
                }
            } catch (Exception e) {
                Log.e("LogsConversor", e.getMessage() != null ? e.getMessage() : "Error desconocido");
            }
        });


        buttonOcultarBloque.setOnClickListener(view -> {
            if (mostrandoBloque1) {

                mostrarBloqueDos();
                mostrandoBloque1 = false;
            } else {

                mostrarBloqueUno();
                mostrandoBloque1 = true;
            }
        });
    }

    private void mostrarBloqueUno() {
        textViewPulgada.setVisibility(View.VISIBLE);
        etPulgada.setVisibility(View.VISIBLE);
        buttonConvertir.setVisibility(View.VISIBLE);
        tvResultadoPulgadaToCm.setVisibility(View.VISIBLE);

        textViewCm.setVisibility(View.GONE);
        etCentimetro.setVisibility(View.GONE);
        buttonConvertirCm.setVisibility(View.GONE);
        tvResultadoCmToPulgada.setVisibility(View.GONE);

        buttonOcultarBloque.setText("Cambiar a Cm -> Pulgadas");
    }

    private void mostrarBloqueDos() {
        textViewPulgada.setVisibility(View.GONE);
        etPulgada.setVisibility(View.GONE);
        buttonConvertir.setVisibility(View.GONE);
        tvResultadoPulgadaToCm.setVisibility(View.GONE);

        textViewCm.setVisibility(View.VISIBLE);
        etCentimetro.setVisibility(View.VISIBLE);
        buttonConvertirCm.setVisibility(View.VISIBLE);
        tvResultadoCmToPulgada.setVisibility(View.VISIBLE);

        buttonOcultarBloque.setText("Cambiar a Pulgadas -> Cm");
    }



    private String convertirPulgadasACm(String pulgadaText) {
        double pulgadaValue = Double.parseDouble(pulgadaText) * 2.54;
        return String.format(Locale.US, "%.2f", pulgadaValue);
    }

    private String convertirCmAPulgadas(String cmText) {
        double cmValue = Double.parseDouble(cmText) / 2.54;
        return String.format(Locale.US, "%.2f", cmValue);
    }
}