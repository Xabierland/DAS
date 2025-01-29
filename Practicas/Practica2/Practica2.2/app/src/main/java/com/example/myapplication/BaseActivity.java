package com.example.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity {
    protected static final String LANGUAGE_KEY = "language_key";
    
    // Este metodo es el primero que se ejecuta al crear una actividad
    // Al llamarse antes del onCreate, se puede modificar el contexto de la actividad
    // De esta forma, se puede cambiar el idioma de la actividad antes de que se cree
    // Es por esto que he hecho que todas las demas actividades hereden de esta
    // No me escondo, la idea me la ha dado Claude
    @Override
    protected void attachBaseContext(Context newBase) {
        String savedLanguage = newBase.getSharedPreferences("Settings", MODE_PRIVATE)
                .getString(LANGUAGE_KEY, Locale.getDefault().getLanguage());
        Locale locale = new Locale(savedLanguage);
        Configuration config = new Configuration(newBase.getResources().getConfiguration());
        config.setLocale(locale);
        Context context = newBase.createConfigurationContext(config);
        super.attachBaseContext(context);
    }

    protected void setLocale(String languageCode) {
        getSharedPreferences("Settings", MODE_PRIVATE)
                .edit()
                .putString(LANGUAGE_KEY, languageCode)
                .apply();

        Locale locale = new Locale(languageCode);
        Configuration config = new Configuration(getResources().getConfiguration());
        config.setLocale(locale);
        createConfigurationContext(config);
        
        recreate();
    }
}