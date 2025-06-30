package com.example.app_s9

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources.Theme
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserActivity : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnCargar: Button
    private lateinit var switchTheme: Switch

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences("userPrefs", Context.MODE_PRIVATE)

        if (prefs.getBoolean("dark_mode", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etEmail = findViewById(R.id.etEmail)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCargar = findViewById(R.id.btnCargar)
        switchTheme = findViewById(R.id.switchTheme)

        switchTheme.isChecked = prefs.getBoolean("dark_mode", false)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val edad = etEdad.text.toString()
            val email = etEmail.text.toString()

            prefs.edit().apply {
                putString("nombre", nombre)
                putString("edad", edad)
                putString("email", email)
                apply()
            }

            Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()
        }

        btnCargar.setOnClickListener {
            val nombre = prefs.getString("nombre", "Sin nombre")
            val edad = prefs.getString("edad", "0")
            val email = prefs.getString("email", "Sin email")

            etNombre.setText(nombre)
            etEdad.setText(edad)
            etEmail.setText(email)
        }

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("dark_mode", isChecked).apply()

            // Reiniciar para aplicar el tema
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked)
                    AppCompatDelegate.MODE_NIGHT_YES
                else
                    AppCompatDelegate.MODE_NIGHT_NO
            )
            recreate()
        }
    }
}