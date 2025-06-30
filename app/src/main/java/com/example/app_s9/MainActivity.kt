package com.example.app_s9

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var editTextUsername: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button
    private lateinit var buttonClear: Button
    private lateinit var textViewResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Inicializa contador
        val sharedPref = getSharedPreferences("myPrefs", MODE_PRIVATE)
        val count = sharedPref.getInt("launchCount", 0) + 1
        sharedPref.edit().putInt("launchCount", count).apply()

        val tvCounter = findViewById<TextView>(R.id.tvCounter)
        val btnReset = findViewById<Button>(R.id.btnReset)
        tvCounter.text = "Veces que abriste la app: $count"

        btnReset.setOnClickListener {
            sharedPref.edit().putInt("launchCount", 0).apply()
            tvCounter.text = "Veces que abriste la app: 0"
            Toast.makeText(this, "Contador reseteado", Toast.LENGTH_SHORT).show()
        }

        Log.d("Contador", "Veces que se ha abierto la app: $count")

        // Diseño adaptable
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnOpenUserInfo = findViewById<Button>(R.id.btnIngresar)
        btnOpenUserInfo.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
        
        // Inicializar SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper(this)
        
        // Inicializar vistas
        initViews()
        
        // Configurar listeners
        setupListeners()
        
        // Verificar si es la primera vez que se abre la app
        checkFirstTime()
    }
    
    private fun initViews() {
        editTextUsername = findViewById(R.id.editTextUsername)
        buttonSave = findViewById(R.id.buttonSave)
        buttonLoad = findViewById(R.id.buttonLoad)
        buttonClear = findViewById(R.id.buttonClear)
        textViewResult = findViewById(R.id.textViewResult)
    }
    
    private fun setupListeners() {
        buttonSave.setOnClickListener {
            saveData()
        }
        
        buttonLoad.setOnClickListener {
            loadData()
        }
        
        buttonClear.setOnClickListener {
            clearAllData()
        }
    }
    
    private fun saveData() {
        val username = editTextUsername.text.toString().trim()
        
        if (username.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Guardar datos
        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_USERNAME, username)
        sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, false)
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_USER_ID, (1000..9999).random())
        
        Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
        editTextUsername.setText("")
    }
    
    private fun loadData() {
        val username = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_USERNAME, "Sin nombre")
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        val userId = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_USER_ID, 0)
        
        val result = "Usuario: $username\nID: $userId\nPrimera vez: ${if (isFirstTime) "Sí" else "No"}"
        textViewResult.text = result
    }
    
    private fun clearAllData() {
        sharedPreferencesHelper.clearAll()
        textViewResult.text = ""
        editTextUsername.setText("")
        Toast.makeText(this, "Todas las preferencias han sido eliminadas", Toast.LENGTH_SHORT).show()
    }
    
    private fun checkFirstTime() {
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        
        if (isFirstTime) {
            Toast.makeText(this, "¡Bienvenido por primera vez!", Toast.LENGTH_LONG).show()
        }
    }
}