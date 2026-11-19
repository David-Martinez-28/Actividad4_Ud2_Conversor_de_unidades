# Actividad 4 · UD2 — Conversor de unidades

Aplicación Android sencilla que convierte longitudes entre **pulgadas** y **centímetros**. Desarrollada en Java como actividad de la Unidad 2 (Desarrollo de Aplicaciones Multiplataforma / DAM).

## Características

- Conversión de **pulgadas → centímetros** (`pulgadas × 2,54`).
- Conversión de **centímetros → pulgadas** (`cm ÷ 2,54`).
- Un único botón superior para **alternar entre los dos conversores**; solo se muestra un bloque a la vez.
- Entrada numérica con decimales (`numberDecimal`).
- Resultado redondeado a **2 decimales**.
- Si el campo está vacío, el resultado se limpia.
- Diseño *edge-to-edge* con imagen de fondo (`metro_fondo.jpeg`).

## Capturas

_Pendiente de añadir (por ejemplo en una carpeta `docs/` y enlazadas aquí)._

## Uso

1. Al abrir la app se muestra el conversor de **pulgadas a centímetros**.
2. Escribe el valor en el campo de texto y pulsa **Convertir**.
3. Pulsa el botón superior (**Cambiar a Cm -> Pulgadas**) para cambiar de conversor, y viceversa.

## Requisitos

| Herramienta | Versión |
|---|---|
| Android Studio | Versión reciente con soporte para SDK 37 |
| `compileSdk` / `targetSdk` | 37 |
| `minSdk` | 37 |
| Java | 11 |
| Gradle | Wrapper incluido (`gradlew`) |

> **Nota:** `minSdk = 37` limita la app a dispositivos o emuladores con esa versión de Android. Si quieres que funcione en más dispositivos, baja este valor en `app/build.gradle.kts`.

## Cómo ejecutarlo

1. Clona el repositorio:
   ```bash
   git clone <URL-del-repositorio>
   ```
2. Abre la carpeta en **Android Studio** y espera a que termine la sincronización de Gradle.
3. Elige un emulador o dispositivo compatible y pulsa **Run ▶**.

También desde terminal:

```bash
./gradlew assembleDebug      # genera el APK de depuración
./gradlew test               # tests unitarios
```

## Estructura del proyecto

```
app/src/main/
├── java/dam/david/u2p4conversor/
│   └── MainActivity.java     # Lógica de la app y conversiones
├── res/
│   ├── layout/activity_main.xml   # Interfaz (ConstraintLayout)
│   ├── values/strings.xml         # Textos
│   └── drawable/metro_fondo.jpeg  # Imagen de fondo
└── AndroidManifest.xml
```

### Cómo funciona

`MainActivity` enlaza las vistas en `setUI()` y gestiona tres botones:

- **Convertir** (pulgadas → cm) → `convertirPulgadasACm()`
- **Convertir a Pulgadas** (cm → pulgadas) → `convertirCmAPulgadas()`
- **Botón superior** → alterna la visibilidad entre `mostrarBloqueUno()` y `mostrarBloqueDos()`.

Los resultados se formatean con `String.format(Locale.US, "%.2f", ...)`, por lo que el separador decimal es siempre el punto.

## Tecnologías

- Java 11
- Android SDK 37
- AndroidX (AppCompat, ConstraintLayout, Activity)
- Material Components
- JUnit y Espresso (plantillas de test incluidas)

## Autor

**David** — `dam.david.u2p4conversor`
