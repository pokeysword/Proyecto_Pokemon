package org.example;

public enum Tipo {
    NORMAL, FUEGO, AGUA, PLANTA, ELÉCTRICO, HIELO, LUCHA, VENENO, TIERRA, VOLADOR, PSÍQUICO, BICHO, ROCA, FANTASMA, DRAGÓN, SINIESTRO, ACERO, HADA;


    public String getNombre() {
        return this.name();
    }
}
