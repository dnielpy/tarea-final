# ?? Sistema de Gesti�n de Consultorio M�dico Familiar (CMF)

## ?? Descripci�n del Proyecto

Este proyecto es un sistema de gesti�n dise�ado para un **Consultorio M�dico Familiar (CMF)**. Su objetivo principal es facilitar la administraci�n de pacientes, visitas m�dicas, personal sanitario y registros hist�ricos. El sistema permite gestionar de manera eficiente las operaciones diarias del consultorio, proporcionando herramientas para el an�lisis y la toma de decisiones.

## ? Caracter�sticas Principales

- **????? Gesti�n de Pacientes**: 
  - Registro de pacientes con datos personales, historial cl�nico y enfermedades cr�nicas.
  - Identificaci�n de pacientes en riesgo, como mujeres embarazadas en situaciones cr�ticas.

- **?? Gesti�n de Visitas M�dicas**:
  - Registro y edici�n de visitas m�dicas con detalles como diagn�stico, tratamiento y an�lisis realizados.
  - Filtrado de visitas por fecha y generaci�n de estad�sticas mensuales.

- **?? Reportes y Estad�sticas**:
  - Porcentajes de mujeres respecto a hombres, embarazadas en riesgo y pacientes en riesgo.
  - Distribuci�n de pacientes por rangos de edad.

- **?? Gesti�n del Personal Sanitario**:
  - Registro de m�dicos y enfermeras con informaci�n detallada como experiencia, licenciatura y fecha de inicio en el CMF.
  - Asignaci�n de roles y credenciales de usuario.

- **?? Registros Hist�ricos**:
  - Gesti�n de hojas de cargos diarias con las visitas realizadas.
  - Registro general e hist�rico de actividades del consultorio.

- **?? Funcionalidades de B�squeda**:
  - B�squeda avanzada en tablas de datos con filtros personalizados.

- **?? Interfaz Moderna**:
  - Dise�o visual atractivo con componentes personalizados como barras de desplazamiento animadas y botones interactivos.

## ?? Funcionalidades Destacadas

### 1. **Gesti�n de Pacientes**
- Registro de pacientes con validaci�n de datos como el **Carn� de Identidad (CI)**.
- Identificaci�n autom�tica de g�nero y estado de embarazo basado en el CI.
- Generaci�n de enfermedades cr�nicas y vacunas aleatorias para pruebas.

### 2. **Gesti�n de Visitas**
- Registro de visitas con detalles como diagn�stico, tratamiento y an�lisis realizados.
- Generaci�n de an�lisis m�dicos aleatorios para visitas.
- Edici�n y eliminaci�n de visitas existentes.

### 3. **Reportes y Estad�sticas**
- **?? Porcentajes**:
  - Mujeres respecto a hombres.
  - Embarazadas en riesgo.
  - Pacientes en riesgo del total.
- **?? Rangos de Edad**:
  - Distribuci�n de pacientes en rangos de 10 a�os.
- **?? Visitas por D�a**:
  - Cantidad de visitas realizadas en un d�a espec�fico o en un mes.

### 4. **Gesti�n del Personal**
- Registro de m�dicos y enfermeras con datos como experiencia, licenciatura y fecha de inicio.
- Asignaci�n de roles y credenciales para acceso al sistema.

### 5. **Interfaz de Usuario**
- **?? Buscador de Tablas**: Permite filtrar datos en tiempo real.
- **?? Hojas de Cargos Diarias**: Registro de visitas realizadas en un d�a espec�fico.
- **?? Componentes Personalizados**: Barras de desplazamiento modernas y botones interactivos.

## ??? Tecnolog�as Utilizadas

- **Java**: Lenguaje principal para la l�gica del sistema.
- **Swing**: Para la creaci�n de la interfaz gr�fica de usuario.
- **JTable**: Para la visualizaci�n y manipulaci�n de datos tabulares.
- **LocalDate**: Para la gesti�n de fechas en el sistema.

## ?? Estructura del Proyecto

- **`entidades`**: Clases principales como `Paciente`, `Visita`, `CMF`, y m�s.
- **`frontend`**: Componentes de la interfaz gr�fica, como paneles, tablas y formularios.
- **`util`**: Clases de utilidad para generaci�n de datos de prueba y constantes.

## ?? Datos de Prueba
El sistema incluye un generador de datos de prueba (`MockDataGenerator`) que permite simular pacientes, visitas y an�lisis para facilitar el desarrollo y las pruebas.

## ?? Objetivo
Este sistema est� dise�ado para optimizar la gesti�n de un consultorio m�dico, proporcionando herramientas avanzadas para la administraci�n de pacientes, personal y registros, todo en una interfaz moderna y f�cil de usar.

�Gracias por usar el Sistema de Gesti�n de CMF! ??
