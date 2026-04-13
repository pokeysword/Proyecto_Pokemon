# Mapa de Ubicación: Lógica de Batalla y Cálculo de Daño

## 📍 ¿DÓNDE ESTÁ QUÉ?

### 🎮 PUNTO DE ENTRADA
```
Main.java (línea 1-50)
└─ main()
   └─ GameView.iniciarSeleccionEquipo()
      └─ Persona.crearEquipo()
         └─ "3. Combatir con el equipo seleccionado" ◄─── AQUÍ ENTRA BATALLA
            └─ [VACÍO - NECESITA IMPLEMENTACIÓN]
```

---

### 💥 CÁLCULO DE DAÑO

#### ❌ NO EXISTE:
- **Archivo:** No hay `Battle.java`, `DamageCalculator.java`, `TypeEffectiveness.java`
- **Método:** No hay `calcularDaño()` en ningún lado
- **Fórmula:** No implementada

#### ✅ REFERENCIAS INCOMPLETAS:
```
Pokemon.java (línea 128-135)
├─ getMultiplicador(int multiplicador)  ◄─── NUNCA SE LLAMA
│  └─ Convierte modificadores a multiplicadores (0.25 a 4.0)
│
└─ Variables sin usar:
   ├─ ModAtack, ModDefense, ModSpAtack, ModSpDefense, ModSpeed
   └─ getMultiplicador() podría usarlos
```

---

### 🎨 ESTRUCTURA DE MOVIMIENTOS

#### DONDE ESTÁN:
```
src/main/java/org/example/movimientos/
├─ Movimiento.java (línea 1-24)
│  ├─ abstract void efecto(Pokemon atacante, Pokemon defensor)
│  ├─ int potencia         ◄─── NO SE USA EN MOVIMIENTOS DE DAÑO
│  ├─ int precision       ◄─── NO SE USA EN MOVIMIENTOS DE DAÑO
│  └─ int multcrit        ◄─── NO SE USA
│
├─ MOVIMIENTOS CON EFECTO VACÍO (daño no implementado):
│  ├─ Earthquake.java (Tierra, Físico, Potencia=100)
│  ├─ HydroPump.java (Agua, Especial, Potencia=110)
│  ├─ DragonClaw.java (Dragón, Físico, Potencia=75)
│  ├─ FlareBlitz.java (Fuego, Físico, Potencia=120)
│  ├─ IceBeam.java (Hielo, Especial, Potencia=90)
│  └─ 15+ más...
│
└─ MOVIMIENTOS CON EFECTO IMPLEMENTADO:
   ├─ CalmMind.java (línea 14-16): Aumenta SpAtk +1, SpDef +1
   ├─ SwordDance.java (línea 14-16): Aumenta Atk +2
   ├─ WillOWisp.java (línea 17-19): Aplica QUEMADO
   ├─ UTurn.java (línea 14-16): Fuerza cambio
   └─ Recover.java (línea 14-16): Recupera PS
```

#### PATRÓN:
```
// ❌ MAL (Vacío - mayoría de movimientos):
public class Earthquake extends Movimiento {
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        // SIN IMPLEMENTACIÓN
    }
}

// ✅ BIEN (Con efecto):
public class CalmMind extends Movimiento {
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        atacante.modificarSpAtk(1);      // ◄─── Implementado
        atacante.modificarSpDef(1);
    }
}
```

---

### 🧬 SISTEMA DE HABILIDADES

#### DONDE ESTÁN:
```
src/main/java/org/example/habilidades/
├─ Habilidad.java (línea 1-21) - BASE ABSTRACTA
│  ├─ void efecto(Pokemon portador)
│  ├─ void efectoalCambiar(Pokemon portador)
│  ├─ void efectoAlEntrar(Pokemon portador, Pokemon rival)
│  ├─ void efectoAlRecibirDaño(Pokemon rival, int daño, Movimiento move)
│  ├─ Tipo antesDeCalcularDaño(Pokemon def, Movimiento move)  ◄─── HOOK IMPORTANTE
│  └─ int antesDeCalcularefecto(Pokemon def, Movimiento move) ◄─── HOOK IMPORTANTE
│
├─ HABILIDADES CON EFECTOS IMPLEMENTADOS:
│  ├─ Intimidacion.java (línea 9-13): Baja Ataques al entrar
│  ├─ RoughSkin.java (línea 11-15): Daño al golpear (físico)
│  ├─ Pixilate.java (línea 10-15): Convierte NORMAL → HADA PRE-DAÑO  ◄─── INTERESANTE
│  ├─ Competitive.java: Aumenta SpAtk al bajar stats
│  ├─ Levitate.java: Inmunidad a Tierra
│  ├─ MoldBreaker.java: Ignora habilidades defensoras
│  └─ 5+ más...
```

#### HOOKS DISPONIBLES PARA BATALLA:
```
ANTES de ataque:
├─ Habilidad.antesDeCalcularDaño()
│  └─ Puede modificar tipo (ej: Pixilate cambia NORMAL→HADA)
│
DURANTE daño:
├─ Movimiento.efecto() en línea de batalla
│
DESPUÉS daño:
└─ Habilidad.efectoAlRecibirDaño()
   └─ Puede hacer daño de retorno (ej: RoughSkin)
```

---

### 📊 ESTRUCTURA DE STATS Y TIPOS

#### Pokemon.java (línea 10-26) - STATS:
```
Base:
├─ ps:          PS base (ej: Garchomp=183)
├─ atack:       Ataque base (ej: Garchomp=182)
├─ defense:     Defensa base (ej: Garchomp=115)
├─ spAtack:     Ataque Especial base (ej: Garchomp=95)
├─ spDefense:   Defensa Especial base (ej: Garchomp=105)
└─ speed:       Velocidad base (ej: Garchomp=154)

Modificadores (rango -6 a +6):
├─ ModAtack         (multiplicador 0.25 a 4.0)
├─ ModDefense
├─ ModSpAtack
├─ ModSpDefense
└─ ModSpeed
```

#### Tipo.java (línea 1-3) - TIPOS:
```
18 tipos definidos:
NORMAL, FUEGO, AGUA, PLANTA, ELÉCTRICO, HIELO, LUCHA, VENENO, 
TIERRA, VOLADOR, PSÍQUICO, BICHO, ROCA, FANTASMA, DRAGÓN, 
SINIESTRO, ACERO, HADA

SIN tabla de efectividad implementada ◄─── NECESITA TypeEffectiveness.java
```

#### Categoria.java (línea 1-3):
```
FISICO    ← Usa Ataque vs Defensa
ESPECIAL  ← Usa Ataque Especial vs Defensa Especial
ESTADO    ← No causa daño
```

---

### 🎯 LUGARES DONDE SE NECESITA IMPLEMENTAR

#### 1. CREAR: Battle.java
```
Ubicación: src/main/java/org/example/Battle.java
Responsabilidades:
├─ Manejar rondas de combate
├─ Determinar orden (speed + prioridad)
├─ Ejecutar ataques
├─ Verificar fin de batalla
└─ Mostrar estado
```

#### 2. CREAR: DamageCalculator.java
```
Ubicación: src/main/java/org/example/DamageCalculator.java
Responsabilidades:
├─ public int calcularDaño(Pokemon atk, Pokemon def, Movimiento move)
└─ private double calcularDañoBase()
```

#### 3. CREAR: TypeEffectiveness.java
```
Ubicación: src/main/java/org/example/TypeEffectiveness.java
Responsabilidades:
├─ public double getEffectiveness(Tipo ataque, Tipo defensa)
├─ public double getEffectiveness(Tipo ataque, ArrayList<Tipo> defensas)
└─ Matriz 18×18 de tipos
```

#### 4. MODIFICAR: Movimiento.java
```
Ubicación: src/main/java/org/example/movimientos/Movimiento.java
Agregar getters públicos:
├─ public int getPotencia()
├─ public int getPrecision()
├─ public int getPP()
└─ public int getMultcrit()
```

#### 5. MODIFICAR: Todos los movimientos de daño (18 movimientos)
```
Ubicación: src/main/java/org/example/movimientos/*.java
Cambiar método efecto() de VACÍO a:
├─ Llamar DamageCalculator
├─ Aplicar daño al defensor
├─ Mostrar mensaje
└─ Llamar efectos de habilidad
```

#### 6. MODIFICAR: Persona.java
```
Ubicación: src/main/java/org/example/Persona.java (línea 25-45)
En switch case 3:
├─ Crear instancia de Battle
├─ Llamar batalla.iniciarBattle()
└─ Mostrar resultado
```

---

### 📈 FLUJO ACTUAL vs ESPERADO

#### ❌ FLUJO ACTUAL:
```
Main.main()
  └─ GameView.iniciarSeleccionEquipo()
     └─ Persona.crearEquipo()  [Do-while infinito]
        ├─ Opción 1: Agregar pokémon ✅
        ├─ Opción 2: Ver equipo ✅
        └─ Opción 3: "Combatir" ► SALE DEL LOOP PERO SIN IMPLEMENTACIÓN ❌
```

#### ✅ FLUJO ESPERADO:
```
Main.main()
  └─ GameView.iniciarSeleccionEquipo()
     └─ Persona.crearEquipo()
        └─ Opción 3: "Combatir"
           └─ Battle batalla = new Battle(jugador1, rival)
              └─ batalla.iniciarBattle()
                 ├─ Turno 1
                 ├─ Turno 2
                 └─ [...]
                    └─ Ganador encontrado
                       └─ Mostrar resultado en GameView
```

---

### 🔗 CONEXIONES ENTRE ARCHIVOS

```
Battle.java (POR CREAR)
├─ USA: Pokemon.java
├─ USA: Persona.java
├─ USA: Movimiento.java
├─ CREA: DamageCalculator.java
├─ CREA: TypeEffectiveness.java
└─ LLAMA: Habilidad.java hooks

DamageCalculator.java (POR CREAR)
├─ USA: Movimiento.java (getPotencia, getPrecision)
├─ USA: Pokemon.java (getAtack, getDefense, getMultiplicador)
├─ USA: Categoria.java (para determinar qué stat usar)
└─ USA: TypeEffectiveness.java

TypeEffectiveness.java (POR CREAR)
└─ USA: Tipo.java (lista de tipos)

Movimiento.java MODIFICADO
├─ Agrega getters: getPotencia(), getPrecision(), getPP()
└─ Movimientos específicos: implementan efecto() real
```

---

### 📋 LISTA DE VERIFICACIÓN: ¿ESTÁ IMPLEMENTADO?

| Componente | Archivo | Línea | Estado |
|-----------|---------|-------|--------|
| Clase Battle | ❌ NO EXISTE | - | 🔴 |
| Cálculo Daño | ❌ NO EXISTE | - | 🔴 |
| Tabla Efectividad | ❌ NO EXISTE | - | 🔴 |
| Stats Base Pokémon | Pokemon.java | 10-25 | ✅ |
| Modificadores Stats | Pokemon.java | 18-25 | ✅ |
| Movimiento base | Movimiento.java | 1-25 | ✅ (parcial) |
| Habilidades base | Habilidad.java | 1-21 | ✅ |
| Hooks Habilidad | Habilidad.java | 18-20 | ✅ |
| Efectos de habilidades | habilidades/*.java | varios | ✅ |
| Movimientos con efecto | movimientos/*.java | varios | ✅ (parcial) |
| Movimientos sin efecto | movimientos/*.java | varios | ❌ |
| Interfaz usuario | GameView.java | 1-30 | ✅ (parcial) |
| Selección equipo | Persona.java | 15-65 | ✅ |

---

### 🎲 DATOS ESPECÍFICOS POR POKÉMON (Main.java)

```
RotomWash:      Nivel 50, Tipos: [ELÉCTRICO, AGUA], Speed: 106
Garchomp:       Nivel 50, Tipos: [DRAGÓN, TIERRA],  Speed: 154  ◄─── RÁPIDO
Togekiss:       Nivel 50, Tipos: [HADA, VOLADOR],   Speed: 113
Metagross:      Nivel 50, Tipos: [ACERO, PSÍQUICO], Speed: 110
Milotic:        Nivel 50, Tipos: [AGUA],            Speed: 101
Arcanine:       Nivel 50, Tipos: [FUEGO],           Speed: 145
Amoonguss:      [...]
Dragapult:      [...]
Excadrill:      [...]
Sylveon:        [...]
```

---

## 🎯 RESUMEN VISUAL

```
                    ┌─────────────────────────────────────────┐
                    │       Proyecto Pokémon - Estado         │
                    └─────────────────────────────────────────┘
                                      │
                    ┌─────────────────┼─────────────────┐
                    │                 │                 │
            ✅ COMPLETADO      🟠 PARCIAL         ❌ FALTA
                    │                 │                 │
        • Pokémon     • Movimientos   • Battle
        • Tipos       • Habilidades   • DamageCalc
        • Stats       • Efectos       • TypeEffec
        • Habilidades • UI Selección
        
        ════════════════════════════════════════════
        
        BLOQUEADOR PRINCIPAL:
        
        Sin Battle.java → Sin DamageCalculator.java → 
        Sin TypeEffectiveness.java
        
        = NO HAY SISTEMA DE COMBATE
```
