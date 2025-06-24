# 🏥 Sistema de Gestión de Consultorio Médico Familiar (CMF)

## 📋 Descripción del Proyecto

Este proyecto es un sistema de gestión diseñado para un **Consultorio Médico Familiar (CMF)**. Su objetivo principal es facilitar la administración de pacientes, visitas médicas, personal sanitario y registros históricos. El sistema permite gestionar de manera eficiente las operaciones diarias del consultorio, proporcionando herramientas para el análisis y la toma de decisiones.

## ✨ Características Principales

- **👩‍⚕️ Gestión de Pacientes**: 
  - Registro de pacientes con datos personales, historial clínico y enfermedades crónicas.
  - Identificación de pacientes en riesgo, como mujeres embarazadas en situaciones críticas.

- **🩺 Gestión de Visitas Médicas**:
  - Registro y edición de visitas médicas con detalles como diagnóstico, tratamiento y análisis realizados.
  - Filtrado de visitas por fecha y generación de estadísticas mensuales.

- **📊 Reportes y Estadísticas**:
  - Porcentajes de mujeres respecto a hombres, embarazadas en riesgo y pacientes en riesgo.
  - Distribución de pacientes por rangos de edad.

- **👨‍⚕️ Gestión del Personal Sanitario**:
  - Registro de médicos y enfermeras con información detallada como experiencia, licenciatura y fecha de inicio en el CMF.
  - Asignación de roles y credenciales de usuario.

- **📜 Registros Históricos**:
  - Gestión de hojas de cargos diarias con las visitas realizadas.
  - Registro general e histórico de actividades del consultorio.

- **🔍 Funcionalidades de Búsqueda**:
  - Búsqueda avanzada en tablas de datos con filtros personalizados.

- **💻 Interfaz Moderna**:
  - Diseño visual atractivo con componentes personalizados como barras de desplazamiento animadas y botones interactivos.

## 🌟 Funcionalidades Destacadas

### 1. **👩‍⚕️ Gestión de Pacientes**
- Registro de pacientes con validación de datos como el **Carné de Identidad (CI)**.
- Identificación automática de género y estado de embarazo basado en el CI.
- Generación de enfermedades crónicas y vacunas aleatorias para pruebas.

### 2. **🩺 Gestión de Visitas**
- Registro de visitas con detalles como diagnóstico, tratamiento y análisis realizados.
- Generación de análisis médicos aleatorios para visitas.
- Edición y eliminación de visitas existentes.

### 3. **📊 Reportes y Estadísticas**
- **📈 Porcentajes**:
  - Mujeres respecto a hombres.
  - Embarazadas en riesgo.
  - Pacientes en riesgo del total.
- **📅 Rangos de Edad**:
  - Distribución de pacientes en rangos de 10 años.
- **📆 Visitas por Día**:
  - Cantidad de visitas realizadas en un día específico o en un mes.

### 4. **👨‍⚕️ Gestión del Personal**
- Registro de médicos y enfermeras con datos como experiencia, licenciatura y fecha de inicio.
- Asignación de roles y credenciales para acceso al sistema.

### 5. **💻 Interfaz de Usuario**
- **🔍 Buscador de Tablas**: Permite filtrar datos en tiempo real.
- **📜 Hojas de Cargos Diarias**: Registro de visitas realizadas en un día específico.
- **🎨 Componentes Personalizados**: Barras de desplazamiento modernas y botones interactivos.

## 🛠️ Tecnologías Utilizadas

- **☕ Java**: Lenguaje principal para la lógica del sistema.
- **🖼️ Swing**: Para la creación de la interfaz gráfica de usuario.
- **📋 JTable**: Para la visualización y manipulación de datos tabulares.
- **📅 LocalDate**: Para la gestión de fechas en el sistema.

## 📂 Estructura del Proyecto

- **`entidades`**: Clases principales como `Paciente`, `Visita`, `CMF`, y más.
- **`frontend`**: Componentes de la interfaz gráfica, como paneles, tablas y formularios.
- **`util`**: Clases de utilidad para generación de datos de prueba y constantes.

## 🧪 Datos de Prueba
El sistema incluye un generador de datos de prueba (`MockDataGenerator`) que permite simular pacientes, visitas y análisis para facilitar el desarrollo y las pruebas.

## 🎯 Objetivo
Este sistema está diseñado para optimizar la gestión de un consultorio médico, proporcionando herramientas avanzadas para la administración de pacientes, personal y registros, todo en una interfaz moderna y fácil de usar.

¡Gracias por usar el Sistema de Gestión de CMF! 🎉
