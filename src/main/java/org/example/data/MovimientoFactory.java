package org.example.data;

import org.example.movimientos.*;

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

