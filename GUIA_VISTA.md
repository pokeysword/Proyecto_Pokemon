# 🎮 Interfaz Gráfica - Pokémon Battle Simulator

## 📁 Estructura de la Vista

La interfaz gráfica ha sido completamente reorganizada en una carpeta dedicada: **`src/main/java/org/example/view/`**

### Archivos Creados:

#### 1. **GameWindow.java**
- **Propósito**: Ventana principal de la aplicación JavaFX
- **Características**:
  - Pantalla de bienvenida con entrada de nombre
  - Pantalla de selección de equipo con visualización de Pokémon
  - Gestión de transiciones entre pantallas
  - Interfaz moderna con colores temáticos

#### 2. **BattleView.java**
- **Propósito**: Visualización de la batalla en tiempo real
- **Características**:
  - Panel superior con información de Pokémon (HP, nivel, tipo)
  - Barras de HP con visualización en tiempo real
  - Centro de batalla con historial de eventos
  - Panel inferior con opciones de movimientos
  - Animaciones y efectos de visualización

#### 3. **PokemonDetailView.java**
- **Propósito**: Vista detallada de estadísticas de un Pokémon
- **Características**:
  - Información básica (nombre, tipo, nivel, experiencia)
  - Estadísticas detalladas (HP, Ataque, Defensa, etc.)
  - Visualización de movimientos disponibles
  - Información de habilidad

#### 4. **UIController.java**
- **Propósito**: Controlador centralizado de navegación
- **Características**:
  - Gestión de transiciones entre vistas
  - Control del flujo del juego
  - Coordinación entre componentes

## 🎨 Características de la Interfaz

### Diseño Visual
- **Colores temáticos**: Azules, naranjas y rojos según el contexto
- **Tema oscuro**: Fondo oscuro para reducir fatiga visual
- **Cards interactivas**: Tarjetas con bordes y estilos distintivos

### Funcionalidades
1. **Pantalla de Bienvenida**: Entrada del nombre del entrenador
2. **Selección de Equipo**: Visualización de Pokémon disponibles
3. **Batalla en Vivo**: 
   - Información de ambos Pokémon
   - Historial de acciones
   - Selección de movimientos
4. **Detalles del Pokémon**: Estadísticas completas y movimientos

## 🚀 Cómo Ejecutar

### Requisitos
- JavaFX 25.0 o superior
- Java 25 o superior
- Maven

### Opciones de ejecución

**Opción 1: Desde el IDE**
```bash
Ejecutar Main.java directamente
```

**Opción 2: Desde Maven**
```bash
mvn clean javafx:run
```

**Opción 3: Compilar y ejecutar**
```bash
mvn clean package
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar target/Proyecto_Pokemon-1.0-SNAPSHOT.jar
```

## 📊 Cambios en las Clases Existentes

### Main.java
- Inicialización de Pokémon en bloque estático
- Método main redirigido a GameWindow.main()
- Método crearRival() es accesible para la GUI

### Persona.java
- Agregado `pokemonActualIndex` para rastrear el Pokémon activo
- Métodos nuevos:
  - `getPokemonActual()`: Obtiene el Pokémon en batalla
  - `setPokemonActual(int index)`: Cambia el Pokémon activo
  - `getPokemonActualIndex()`: Obtiene el índice actual

### Pokemon.java
- Agregado variable `experiencia`
- Métodos nuevos:
  - `getLevel()`: Obtiene el nivel
  - `getExperiencia()`: Obtiene la experiencia
  - `getTipo()`: Obtiene el tipo principal
  - `getEstadisticas()`: Retorna objeto Estadísticas
  - Clase interna `Estadisticas` para acceso a stats

### pom.xml
- Agregadas dependencias JavaFX
- Configurado plugin de Maven para ejecutar con JavaFX

## 🎯 Estructura de Carpetas

```
src/main/java/org/example/
├── view/                          ← CARPETA NUEVA
│   ├── GameWindow.java           (Ventana principal)
│   ├── BattleView.java           (Vista de batalla)
│   ├── PokemonDetailView.java    (Detalles del Pokémon)
│   └── UIController.java         (Controlador)
├── Main.java                      (Actualizado)
├── Persona.java                   (Actualizado)
├── Pokemon.java                   (Actualizado)
├── Battle.java
├── ...otros archivos...
└── [habilidades/]
└── [movimientos/]
```

## 💡 Próximas Mejoras Sugeridas

1. Agregar efectos de sonido
2. Agregar imágenes/sprites de Pokémon
3. Implementar sistema de guardado de partidas
4. Agregar más Pokémon y movimientos
5. Sistema de experiencia y niveles funcional
6. Animaciones de movimientos más elaboradas

## 📝 Notas

- La interfaz gráfica utiliza JavaFX para una experiencia moderna
- El sistema de colores es completamente personalizable dentro de cada clase
- Los componentes están diseñados para ser escalables y mantenibles
