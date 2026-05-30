# Proyecto Pokémon 🎮

¡Bienvenido al **Proyecto Pokémon**! Este es un proyecto desarrollado en **Java** que gestiona información relacionada con Pokémon, incluyendo datos de especies, movimientos y configuraciones específicas de algunos Pokémon (como *Rotom-Wash*).

## 🛠️ Tecnologías Utilizadas

*   **Lenguaje:** Java 25
*   **Gestor de dependencias y construcción:** Maven

## 📁 Estructura del Proyecto

El repositorio cuenta con la siguiente estructura principal:

*   `src/main/java/`: Código fuente de la aplicación.
*   `src/main/resources/`: Recursos (audio e imagen de fondo para la GUI).
*   `sql/pokemon_schema.sql`: Script de creación de la base de datos.
*   `battle-log.txt`: Registro de combates (se actualiza al jugar).
*   `pom.xml`: Configuración de Maven.

## 🚀 Requisitos Previos

Para poder compilar y ejecutar este proyecto en tu entorno local, necesitarás tener instalado:

1.  **Java Development Kit (JDK)** (ver versión en `pom.xml`).
2.  **Apache Maven** instalado en tu sistema.

## ⚙️ Instalación y Uso

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/pokeysword/Proyecto_Pokemon.git
   cd Proyecto_Pokemon
   ```

2. **Compila el proyecto con Maven:**
   ```bash
   mvn clean compile
   ```

3. **Ejecuta la aplicación (consola):**
   *(Dependiendo de la clase principal, el comando puede variar)*
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```
   *(Nota: Sustituye `TuClasePrincipal` por el nombre real de tu clase con el método `main`)*

## 🐘 Base de Datos (PostgreSQL)

El proyecto carga el catálogo de Pokémon **solo desde PostgreSQL**. El script SQL está en `sql/pokemon_schema.sql`.

### Configuración rápida

1. Crea la base de datos y el esquema ejecutando el SQL:
   ```sql
   -- En psql
   \i sql/pokemon_schema.sql
   ```
2. Define las credenciales en `.env` (raíz del proyecto):
   - `DB_URL` o `URL`
   - `DB_USER` o `USER`
   - `DB_PASSWORD` o `PASSWORD`
   - `DB_SCHEMA` o `SCHEMA` (opcional, por defecto `pokemon`)

Si la conexión falla, se muestra un error y el juego se cierra.

## 🎵 Audio

Para reproducir música necesitas agregar tus propios archivos (por derechos de autor no se incluyen en el repo).
Coloca estos archivos en `src/main/resources/audio/`:

- `center.wav`
- `battle.wav`

El juego intentará reproducirlos en bucle durante la selección y la batalla.
Si no existen, se mostrará un aviso y el juego continuará sin audio.

## 🖼️ Fondo de la GUI

Para usar imagen de fondo en la interfaz gráfica, agrega este archivo:

- `src/main/resources/fondo.png`

La imagen se dibuja como fondo de la ventana (no del texto).

## 📝 Notas Adicionales

* Si utilizas IntelliJ IDEA, el proyecto ya cuenta con el directorio `.idea`, por lo que puedes abrirlo directamente desde el IDE y detectará automáticamente la configuración de Maven.

## 🖥️ Interfaz grafica

Se agrego una interfaz grafica simple (Swing) que muestra la salida y permite ingresar comandos sin modificar el modelo.
Incluye fondo de ventana y un campo de entrada estilizado.

Para iniciarla ejecuta:

```bash
mvn exec:java -Dexec.mainClass="org.example.MainGui"
```
