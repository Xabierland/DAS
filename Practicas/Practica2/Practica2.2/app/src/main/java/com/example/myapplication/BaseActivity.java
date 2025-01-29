package com.example.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity {
    protected static final String LANGUAGE_KEY = "language_key";
    
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