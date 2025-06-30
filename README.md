# App S9 - SharedPreferences Demo

Aplicación Android de ejemplo que demuestra el uso básico de SharedPreferences para almacenamiento persistente de datos.

## 📱 Descripción

Esta aplicación implementa un sistema simple de SharedPreferences que permite:
- Guardar y recuperar datos de usuario
- Detectar la primera ejecución de la app
- Limpiar todas las preferencias guardadas, tales como el cambio en el modo de visualización "Modo Oscuro"

## 🚀 Características

- **SharedPreferencesHelper**: Clase wrapper para simplificar el uso de SharedPreferences
- **Tipos de datos soportados**: String, Boolean, Int, Float, Long
- **Interfaz simple**: Campos de entrada y botones para interactuar con las preferencias
- **Persistencia**: Los datos se mantienen incluso después de cerrar la aplicación

## 📋 Requisitos

- Android Studio Arctic Fox o superior
- SDK mínimo: API 21 (Android 5.0)
- SDK objetivo: API 34 (Android 14)
- Kotlin 1.9.0
## 💻 Uso

1. **Guardar datos**: Ingresa tu nombre y presiona "Guardar"
   
3. **Cargar datos**: Presiona "Cargar" para ver los datos guardados
   
5. **Limpiar datos**: Presiona "Limpiar Todo" para eliminar todas las preferencias
   
7. **Contador de veces al ingresar**: Se visualiza la cantidad de veces ingresada a la app y se puede resetear

8. **Ingresar usuario**: Ingresa nuevos datos del usuario para poder guardar
   
10. **Modo Oscuro**: Haz switch en el modo oscuro para tener una visualización más agradable

## 📂 Estructura del Proyecto

```
app_s9/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/app_s9/
│           │   ├── MainActivity.kt
│           │   └── SharedPreferencesHelper.kt
│           │   └── UserActivity.kt
│           └── res/
│               └── layout/
│                   └── activity_main.xml
│                   └── activity_user.xml
└── SharedPreferences_Guide.md
```

## 📖 Documentación

Para más detalles sobre la implementación y cómo extender la funcionalidad, consulta [SharedPreferences_Guide.md](SharedPreferences_Guide.md)


## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la Licencia MIT.
