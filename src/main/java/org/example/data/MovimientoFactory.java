package org.example.data;

import org.example.movimientos.AirSlash;
import org.example.movimientos.CalmMind;
import org.example.movimientos.DazzlingGleam;
import org.example.movimientos.DragonClaw;
import org.example.movimientos.DragonPulse;
import org.example.movimientos.Earthquake;
import org.example.movimientos.EnergyBall;
import org.example.movimientos.FlareBlitz;
import org.example.movimientos.HyperVoice;
import org.example.movimientos.HydroPump;
import org.example.movimientos.IceBeam;
import org.example.movimientos.IronHead;
import org.example.movimientos.MeteorMash;
import org.example.movimientos.Movimiento;
import org.example.movimientos.MysticalFire;
import org.example.movimientos.Protect;
import org.example.movimientos.RagePowder;
import org.example.movimientos.Recover;
import org.example.movimientos.RockSlide;
import org.example.movimientos.Scald;
import org.example.movimientos.ShadowBall;
import org.example.movimientos.Snarl;
import org.example.movimientos.Spore;
import org.example.movimientos.SwordDance;
import org.example.movimientos.UTurn;
import org.example.movimientos.VoltSwitch;
import org.example.movimientos.WillOWisp;
import org.example.movimientos.ZenHeadbutt;

public final class MovimientoFactory {
    private MovimientoFactory() {
    }

    public static Movimiento crear(String code) throws PokemonDataException {
        if (code == null || code.trim().isEmpty()) {
            throw new PokemonDataException("Movimiento vacio en base de datos");
        }
        switch (code.trim()) {
            case "HydroPump":
                return new HydroPump();
            case "VoltSwitch":
                return new VoltSwitch();
            case "WillOWisp":
                return new WillOWisp();
            case "Protect":
                return new Protect();
            case "Earthquake":
                return new Earthquake();
            case "DragonClaw":
                return new DragonClaw();
            case "RockSlide":
                return new RockSlide();
            case "AirSlash":
                return new AirSlash();
            case "DazzlingGleam":
                return new DazzlingGleam();
            case "CalmMind":
                return new CalmMind();
            case "MeteorMash":
                return new MeteorMash();
            case "ZenHeadbutt":
                return new ZenHeadbutt();
            case "Scald":
                return new Scald();
            case "IceBeam":
                return new IceBeam();
            case "Recover":
                return new Recover();
            case "FlareBlitz":
                return new FlareBlitz();
            case "Snarl":
                return new Snarl();
            case "Spore":
                return new Spore();
            case "EnergyBall":
                return new EnergyBall();
            case "RagePowder":
                return new RagePowder();
            case "DragonPulse":
                return new DragonPulse();
            case "ShadowBall":
                return new ShadowBall();
            case "UTurn":
                return new UTurn();
            case "IronHead":
                return new IronHead();
            case "SwordDance":
                return new SwordDance();
            case "HyperVoice":
                return new HyperVoice();
            case "MysticalFire":
                return new MysticalFire();
            default:
                throw new PokemonDataException("Movimiento desconocido: " + code);
        }
    }
}

