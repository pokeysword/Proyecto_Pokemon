package org.example.habilidades;
import org.example.Pokemon;
import org.example.movimientos.Movimiento;

public class SereneGrace extends Habilidad {
    public SereneGrace() {
        super("SereneGrace");
    }

    @Override
    public int antesDeCalcularefecto(Pokemon def, Movimiento move){
        return 2;
    }
    }
