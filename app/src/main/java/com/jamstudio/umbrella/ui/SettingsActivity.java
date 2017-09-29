package com.jamstudio.umbrella.ui;

import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;

import com.jamstudio.umbrella.R;

import java.util.Map;

public class SettingsActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }


    public static class MyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);

            ListPreference unitPreference = (ListPreference) findPreference("units") ;
            unitPreference.setDefaultValue("0");

            EditTextPreference zipPreference = (EditTextPreference) findPreference("zip_code");
            zipPreference.setDefaultValue("95051");
            zipPreference.getEditText().setLines(1);
            zipPreference.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
            zipPreference.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);

            onSharedPreferenceChanged(null, "zip_code");

        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                              String key) {

            updateSummary(findPreference(key), key);

        }

        private void updateSummary(Preference preference, String key) {
            // set the EditTextPreference's summary value to its current text
            if (preference instanceof EditTextPreference)
            { EditTextPreference editTextPref = (EditTextPreference) preference;
                preference.setSummary(editTextPref.getText()); }

        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        }

        @Override
        public void onPause() {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }

    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }


}
