# 🏦 Simulador de Cajero Automatico (ATM)

**Trabajo Práctico Nº 2 - Programación III**  
Carrera: Ingeniería en Sistemas de Información  

---

## 👥 Integrantes

- Hernández Domínguez Tatiana Ayelen - DNI: 45.563.479  
- Ochoa Candela Maribel - DNI: 46.401.137  
- Sibilia María Giuliana - DNI: 46.723.876

---

## 📖 Descripción del Proyecto

Simulacion de un cajero automatico que permite realizar operaciones bancarias basicas con validaciones, manejo de excepciones y registro de transacciones.
Ademas, incluye un sistema de logging que registra cada operacion con fecha, monto y saldo resultante.

---

## 🛠️ Tecnologías utilizadas

- Lenguaje: Java 23 (JDK 23) 
- Gestor de Dependencias: Apache Maven  
- IDE: Visual Studio Code  
---

## ⚙️ Funcionalidades

✔ Crear y gestionar cuentas bancarias  
✔ Consultar saldo  
✔ Realizar depósitos  
✔ Realizar extracciones con validaciones  
✔ Transferencias entre cuentas  
✔ Historial de últimas 10 transacciones  
✔ Logging de operaciones con formato  
✔ Interfaz de usuario por consola  
✔ Manejo de excepciones:  
  - PIN incorrecto  
  - Saldo insuficiente  
  - Cuenta inactiva  
  - Límite de extracción excedido

---

## 📂 Estructura del proyecto

- `model` → clases principales   
- `service` → lógica de negocio  
- `exception` → excepciones personalizadas
- `util` → clases auxiliares para formateo y logging (Formato, Logger)
- `ui` → clase Main (interfaz de ejecución) 

---

## 📊 Diagrama de estados de una cuenta
<img width="371" height="202" alt="Diagrama  drawio" src="https://github.com/user-attachments/assets/6dd55fd0-76cc-47f6-b2b6-c9a8301f6e81" />
