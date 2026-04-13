# Arquitectura de la Interfaz Gráfica

## Flujo de la Aplicación

```
┌─────────────────────────────────────────────────────────┐
│                    MAIN (Punto de Entrada)              │
│              - Inicializa Pokémon                       │
│              - Lanza GameWindow                         │
└────────────────────────┬────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────┐
│           GAMEWINDOW (Ventana Principal)                │
│     Extiende: javafx.application.Application           │
│                                                         │
│  ┌─────────────────────────────────┐                   │
│  │  Pantalla de Bienvenida         │                   │
│  │  - Entrada de nombre            │                   │
│  │  - Botón comenzar               │                   │
│  └─────────────┬───────────────────┘                   │
│                │                                        │
│                ▼                                        │
│  ┌─────────────────────────────────┐                   │
│  │  Selección de Equipo            │                   │
│  │  - Lista de Pokémon             │                   │
│  │  - Visualización de tarjetas    │                   │
│  │  - Botón continuar a batalla    │                   │
│  └─────────────┬───────────────────┘                   │
│                │                                        │
│                ▼                                        │
│  ┌─────────────────────────────────┐                   │
│  │  showBatalla()                  │ ─────────────┐   │
│  │  - Crea rival                   │              │   │
│  │  - Abre BattleView              │              │   │
│  └─────────────────────────────────┘              │   │
└─────────────────────────────────────────────────────────┘
                                                    │
        ┌───────────────────────────────────────────┘
        │
        ▼
┌─────────────────────────────────────────────────────────┐
│             BATTLEVIEW (Vista de Batalla)               │
│     Extiende: Stage (Ventana secundaria)               │
│                                                         │
│  ┌─────────────────────────────────┐                   │
│  │   Panel Superior (Top)          │                   │
│  │   - Info Pokémon Jugador        │                   │
│  │   - HP Bar Jugador              │                   │
│  │   - Separador                   │                   │
│  │   - Info Pokémon Rival          │                   │
│  │   - HP Bar Rival                │                   │
│  └─────────────────────────────────┘                   │
│                                                         │
│  ┌─────────────────────────────────┐                   │
│  │   Centro (Center)               │                   │
│  │   - Historial de batalla        │                   │
│  │   - Log de acciones             │                   │
│  │   - Mensajes de evento          │                   │
│  └─────────────────────────────────┘                   │
│                                                         │
│  ┌─────────────────────────────────┐                   │
│  │   Panel Inferior (Bottom)       │                   │
│  │   - Botones de movimientos      │                   │
│  │   - Selección de acciones       │                   │
│  └─────────────────────────────────┘                   │
└─────────────────────────────────────────────────────────┘

```

## Estructura de Clases

```
MODELOS DE NEGOCIO
├── Persona
│   └── pokemonActualIndex (NUEVO)
│   └── getPokemonActual() (NUEVO)
│
├── Pokemon
│   └── experiencia (NUEVO)
│   └── getLevel() (NUEVO)
│   └── getTipo() (NUEVO)
│   └── getEstadisticas() (NUEVO)
│   └── Estadisticas (CLASE INTERNA NUEVA)
│
├── Battle
└── TypeEffectiveness

VISTAS (view/)
├── GameWindow
│   └── showWelcomeScreen()
│   └── mostrarSeleccionEquipo()
│   └── mostrarBatalla()
│
├── BattleView
│   └── show()
│   └── crearTopBox()
│   └── realizarMovimiento()
│
├── PokemonDetailView
│   └── show()
│   └── crearGridInfoBasica()
│   └── crearBoxEstadisticas()
│
└── UIController
    └── iniciarJuego()
    └── iniciarBatalla()
    └── mostrarDetailsPokemon()
```

## Flujo de Interacción

```
Usuario
   │
   ├─→ Ingresa nombre en GameWindow
   │   │
   │   └─→ Persona.setNombre()
   │
   ├─→ Selecciona "Crear Equipo"
   │   │
   │   └─→ Persona.crearEquipo()
   │
   ├─→ Presiona "Continuar"
   │   │
   │   └─→ GameWindow.mostrarBatalla()
   │       └─→ Main.crearRival()
   │       └─→ BattleView.show()
   │
   └─→ Selecciona movimiento en BattleView
       │
       └─→ BattleView.realizarMovimiento()
           └─→ Actualiza UI
           └─→ Verifica estado de batalla
```

## Dependencias de Librerías

```
pom.xml
├── javafx-controls
├── javafx-fxml
├── javafx-graphics
└── javafx-maven-plugin
```

## Patrones de Diseño Utilizados

1. **MVC (Model-View-Controller)**
   - Modelo: Persona, Pokemon, Battle
   - Vista: GameWindow, BattleView, PokemonDetailView
   - Controlador: UIController

2. **Observer Pattern** (implícito en JavaFX)
   - Los controles observan cambios en datos

3. **Singleton** (implícito)
   - GameWindow es única instancia por ejecución

4. **Inner Class**
   - Estadisticas dentro de Pokemon

## Mejoras Futuras Sugeridas

```
┌─────────────────────────────────────────┐
│   Sistema de Animaciones                │
│   ├─ Transiciones entre pantallas      │
│   └─ Efectos de ataque                  │
├─────────────────────────────────────────┤
│   Multimedia                            │
│   ├─ Sonidos                            │
│   ├─ Música de fondo                    │
│   └─ Sprites de Pokémon                 │
├─────────────────────────────────────────┤
│   Persistencia                          │
│   ├─ Guardar partidas                   │
│   ├─ Cargar partidas                    │
│   └─ Estadísticas                       │
├─────────────────────────────────────────┤
│   Gameplay                              │
│   ├─ Sistema de experiencia             │
│   ├─ Más Pokémon                        │
│   ├─ Más movimientos                    │
│   └─ Efectos de tipo                    │
└─────────────────────────────────────────┘
```
