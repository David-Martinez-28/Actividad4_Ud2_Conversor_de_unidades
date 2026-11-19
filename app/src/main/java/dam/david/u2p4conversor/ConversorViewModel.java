package dam.david.u2p4conversor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

public class ConversorViewModel extends ViewModel {

    private final MutableLiveData<String> resultadoConversion = new MutableLiveData<>("");
    private final MutableLiveData<String> mensajeError = new MutableLiveData<>(null);
    private final MutableLiveData<Integer> tipoConversionIndex = new MutableLiveData<>(0);

    public LiveData<String> getResultadoConversion() {
        return resultadoConversion;
    }

    public LiveData<String> getMensajeError() {
        return mensajeError;
    }

    public LiveData<Integer> getTipoConversionIndex() {
        return tipoConversionIndex;
    }

    public void setTipoConversionIndex(int index) {
        if (tipoConversionIndex.getValue() == null || tipoConversionIndex.getValue() != index) {
            tipoConversionIndex.setValue(index);
            resultadoConversion.setValue("");
            mensajeError.setValue(null);
        }
    }

    private double validarEntrada(String texto) throws Exception {
        if (texto == null || texto.trim().isEmpty()) {
            throw new Exception("El valor no puede estar vacío");
        }
        double valor;
        try {
            valor = Double.parseDouble(texto.trim());
        } catch (NumberFormatException e) {
            throw new Exception("Formato numérico no válido");
        }
        if (valor < 1) {
            throw new Exception("Sólo números >=1");
        }
        return valor;
    }

    public void convertirPulgadasACm(String pulgadaText) throws Exception {
        try {
            double valor = validarEntrada(pulgadaText);
            double res = valor * 2.54;
            resultadoConversion.setValue(String.format(Locale.US, "%.2f cm", res));
            mensajeError.setValue(null);
        } catch (Exception e) {
            resultadoConversion.setValue("");
            mensajeError.setValue(e.getMessage());
            throw e;
        }
    }

    public void convertirCmAPulgadas(String cmText) throws Exception {
        try {
            double valor = validarEntrada(cmText);
            double res = valor / 2.54;
            resultadoConversion.setValue(String.format(Locale.US, "%.2f in", res));
            mensajeError.setValue(null);
        } catch (Exception e) {
            resultadoConversion.setValue("");
            mensajeError.setValue(e.getMessage());
            throw e;
        }
    }

    public void convertirKmAMillas(String kmText) throws Exception {
        try {
            double valor = validarEntrada(kmText);
            double res = valor * 0.621371;
            resultadoConversion.setValue(String.format(Locale.US, "%.2f mi", res));
            mensajeError.setValue(null);
        } catch (Exception e) {
            resultadoConversion.setValue("");
            mensajeError.setValue(e.getMessage());
            throw e;
        }
    }

    public void convertirMillasAKm(String millasText) throws Exception {
        try {
            double valor = validarEntrada(millasText);
            double res = valor / 0.621371;
            resultadoConversion.setValue(String.format(Locale.US, "%.2f km", res));
            mensajeError.setValue(null);
        } catch (Exception e) {
            resultadoConversion.setValue("");
            mensajeError.setValue(e.getMessage());
            throw e;
        }
    }

    public void convertirKgALibras(String kgText) throws Exception {
        try {
            double valor = validarEntrada(kgText);
            double res = valor * 2.20462;
            resultadoConversion.setValue(String.format(Locale.US, "%.2f lb", res));
            mensajeError.setValue(null);
        } catch (Exception e) {
            resultadoConversion.setValue("");
            mensajeError.setValue(e.getMessage());
            throw e;
        }
    }

    public void convertirLibrasAKg(String librasText) throws Exception {
        try {
            double valor = validarEntrada(librasText);
            double res = valor / 2.20462;
            resultadoConversion.setValue(String.format(Locale.US, "%.2f kg", res));
            mensajeError.setValue(null);
        } catch (Exception e) {
            resultadoConversion.setValue("");
            mensajeError.setValue(e.getMessage());
            throw e;
        }
    }
}