package org.example;

import java.lang.reflect.Array;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.Scanner;

import org.example.habilidades.*;
import org.example.movimientos.*;

public class Main {

    // Hacer estáticos los pokémon para que sean accesibles desde Persona
    static Pokemon RotomWash;
    static Pokemon Garchomp;
    static Pokemon Togekiss;
    static Pokemon Metagross;
    static Pokemon Milotic;
    static Pokemon Arcanine;
    static Pokemon Amoonguss;
    static Pokemon Dragapult;
    static Pokemon Excadrill;
    static Pokemon Sylveon;

    static void main(String[] args) {
        // Inicializar todos los Pokémon
        inicializarPokemon();

        // Crear la vista
        GameView gameView = new GameView();
        gameView.showWelcome();

        // Crear la persona y iniciar la selección de equipo
        Persona jugador = new Persona();
        gameView.seleccionarnombre(jugador);
        gameView.iniciarSeleccionEquipo(jugador);

//        Para futuro rival
//        Persona jugador = new Persona("Rival");
//        gameView.iniciarSeleccionEquipo(jugador);
    }

    static void inicializarPokemon() {
        // Rotom
        ArrayList<Tipo> tiposRotom = new ArrayList<>();
        tiposRotom.add(Tipo.ELÉCTRICO);
        tiposRotom.add(Tipo.AGUA);
        ArrayList<Movimiento> movimientosRotom = new ArrayList<>();
        movimientosRotom.add(new HydroPump());
        movimientosRotom.add(new VoltSwitch());
        movimientosRotom.add(new WillOWisp());
        movimientosRotom.add(new Protect());
        RotomWash = new Pokemon("Rotom-Wash", 50, tiposRotom, new Levitate(), 157, 85, 128, 125, 128, 106, movimientosRotom);

        // Garchomp
        ArrayList<Tipo> tiposGarchomp = new ArrayList<>();
        tiposGarchomp.add(Tipo.DRAGÓN);
        tiposGarchomp.add(Tipo.TIERRA);
        ArrayList<Movimiento> movimientosGarchomp = new ArrayList<>();
        movimientosGarchomp.add(new Earthquake());
        movimientosGarchomp.add(new DragonClaw());
        movimientosGarchomp.add(new RockSlide());
        movimientosGarchomp.add(new Protect());
        Garchomp = new Pokemon("Garchomp", 50, tiposGarchomp, new RoughSkin(), 183, 182, 115, 95, 105, 154, movimientosGarchomp);

        // Togekiss
        ArrayList<Tipo> tiposTogekiss = new ArrayList<>();
        tiposTogekiss.add(Tipo.HADA);
        tiposTogekiss.add(Tipo.VOLADOR);
        ArrayList<Movimiento> movimientosTogekiss = new ArrayList<>();
        movimientosTogekiss.add(new AirSlash());
        movimientosTogekiss.add(new DazzlingGleam());
        movimientosTogekiss.add(new CalmMind());
        movimientosTogekiss.add(new Protect());
        Togekiss = new Pokemon("Togekiss", 50, tiposTogekiss, new SereneGrace(), 191, 90, 115, 140, 135, 113, movimientosTogekiss);

        // Metagross
        ArrayList<Tipo> tiposMetagross = new ArrayList<>();
        tiposMetagross.add(Tipo.ACERO);
        tiposMetagross.add(Tipo.PSÍQUICO);
        ArrayList<Movimiento> movimientosMetagross = new ArrayList<>();
        movimientosMetagross.add(new MeteorMash());
        movimientosMetagross.add(new ZenHeadbutt());
        movimientosMetagross.add(new Earthquake());
        movimientosMetagross.add(new Protect());
        Metagross = new Pokemon("Metagross", 50, tiposMetagross, new ClearBody(), 187, 178, 150, 105, 110, 110, movimientosMetagross);

        // Milotic
        ArrayList<Tipo> tiposMilotic = new ArrayList<>();
        tiposMilotic.add(Tipo.AGUA);
        ArrayList<Movimiento> movimientosMilotic = new ArrayList<>();
        movimientosMilotic.add(new Scald());
        movimientosMilotic.add(new IceBeam());
        movimientosMilotic.add(new Recover());
        movimientosMilotic.add(new Protect());
        Milotic = new Pokemon("Milotic", 50, tiposMilotic, new Competitive(), 202, 90, 125, 135, 145, 101, movimientosMilotic);

        // Arcanine
        ArrayList<Tipo> tiposArcanine = new ArrayList<>();
        tiposArcanine.add(Tipo.FUEGO);
        ArrayList<Movimiento> movimientosArcanine = new ArrayList<>();
        movimientosArcanine.add(new FlareBlitz());
        movimientosArcanine.add(new Snarl());
        movimientosArcanine.add(new Protect());
        Arcanine = new Pokemon("Arcanine", 50, tiposArcanine, new Intimidacion(), 181, 181, 120, 100, 85, 118, movimientosArcanine);

        // Amoonguss
        ArrayList<Tipo> tiposAmoonguss = new ArrayList<>();
        tiposAmoonguss.add(Tipo.BICHO);
        tiposAmoonguss.add(Tipo.PLANTA);
        ArrayList<Movimiento> movimientosAmoonguss = new ArrayList<>();
        movimientosAmoonguss.add(new Spore());
        movimientosAmoonguss.add(new EnergyBall());
        movimientosAmoonguss.add(new RagePowder());
        movimientosAmoonguss.add(new Protect());
        Amoonguss = new Pokemon("Amoonguss", 50, tiposAmoonguss, new Regenerator(), 185, 85, 110, 101, 127, 51, movimientosAmoonguss);

        // Dragapult
        ArrayList<Tipo> tiposDragapult = new ArrayList<>();
        tiposDragapult.add(Tipo.DRAGÓN);
        tiposDragapult.add(Tipo.FANTASMA);
        ArrayList<Movimiento> movimientosDragapult = new ArrayList<>();
        movimientosDragapult.add(new DragonPulse());
        movimientosDragapult.add(new ShadowBall());
        movimientosDragapult.add(new UTurn());
        movimientosDragapult.add(new Protect());
        Dragapult = new Pokemon("Dragapult", 50, tiposDragapult, new ClearBody(), 193, 120, 75, 100, 75, 142, movimientosDragapult);

        // Excadrill
        ArrayList<Tipo> tiposExcadrill = new ArrayList<>();
        tiposExcadrill.add(Tipo.TIERRA);
        tiposExcadrill.add(Tipo.ACERO);
        ArrayList<Movimiento> movimientosExcadrill = new ArrayList<>();
        movimientosExcadrill.add(new IronHead());
        movimientosExcadrill.add(new Earthquake());
        movimientosExcadrill.add(new SwordDance());
        movimientosExcadrill.add(new Protect());
        Excadrill = new Pokemon("Excadrill", 50, tiposExcadrill, new MoldBreaker(), 185, 185, 115, 85, 105, 154, movimientosExcadrill);

        // Sylveon
        ArrayList<Tipo> tiposSylveon = new ArrayList<>();
        tiposSylveon.add(Tipo.HADA);
        ArrayList<Movimiento> movimientosSylveon = new ArrayList<>();
        movimientosSylveon.add(new HyperVoice());
        movimientosSylveon.add(new MysticalFire());
        movimientosSylveon.add(new CalmMind());
        movimientosSylveon.add(new Protect());
        Sylveon = new Pokemon("Sylveon", 50, tiposSylveon, new Pixilate(), 185, 185, 115, 85, 105, 154, movimientosSylveon);
    }
}