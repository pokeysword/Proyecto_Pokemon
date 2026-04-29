package org.example;

import java.util.HashMap;


public class TypeEffectiveness {
    private static final HashMap<String, Double> EFFECTIVENESS_MAP = new HashMap<>();

    static {
        

        // NORMAL
        EFFECTIVENESS_MAP.put("NORMAL_vs_ROCA", 0.5);
        EFFECTIVENESS_MAP.put("NORMAL_vs_ACERO", 0.5);
        EFFECTIVENESS_MAP.put("NORMAL_vs_FANTASMA", 0.0);

        // FUEGO
        EFFECTIVENESS_MAP.put("FUEGO_vs_FUEGO", 0.5);
        EFFECTIVENESS_MAP.put("FUEGO_vs_AGUA", 0.5);
        EFFECTIVENESS_MAP.put("FUEGO_vs_PLANTA", 2.0);
        EFFECTIVENESS_MAP.put("FUEGO_vs_HIELO", 2.0);
        EFFECTIVENESS_MAP.put("FUEGO_vs_BICHO", 2.0);
        EFFECTIVENESS_MAP.put("FUEGO_vs_ROCA", 0.5);
        EFFECTIVENESS_MAP.put("FUEGO_vs_FANTASMA", 0.5);
        EFFECTIVENESS_MAP.put("FUEGO_vs_ACERO", 2.0);

        // AGUA
        EFFECTIVENESS_MAP.put("AGUA_vs_FUEGO", 2.0);
        EFFECTIVENESS_MAP.put("AGUA_vs_AGUA", 0.5);
        EFFECTIVENESS_MAP.put("AGUA_vs_PLANTA", 0.5);
        EFFECTIVENESS_MAP.put("AGUA_vs_TIERRA", 2.0);
        EFFECTIVENESS_MAP.put("AGUA_vs_ROCA", 2.0);
        EFFECTIVENESS_MAP.put("AGUA_vs_ACERO", 1.0);

        // PLANTA
        EFFECTIVENESS_MAP.put("PLANTA_vs_FUEGO", 0.5);
        EFFECTIVENESS_MAP.put("PLANTA_vs_AGUA", 2.0);
        EFFECTIVENESS_MAP.put("PLANTA_vs_PLANTA", 0.5);
        EFFECTIVENESS_MAP.put("PLANTA_vs_VENENO", 0.5);
        EFFECTIVENESS_MAP.put("PLANTA_vs_TIERRA", 2.0);
        EFFECTIVENESS_MAP.put("PLANTA_vs_ROCA", 2.0);
        EFFECTIVENESS_MAP.put("PLANTA_vs_BICHO", 0.5);

        // ELÉCTRICO
        EFFECTIVENESS_MAP.put("ELÉCTRICO_vs_AGUA", 2.0);
        EFFECTIVENESS_MAP.put("ELÉCTRICO_vs_PLANTA", 0.5);
        EFFECTIVENESS_MAP.put("ELÉCTRICO_vs_ELÉCTRICO", 0.5);
        EFFECTIVENESS_MAP.put("ELÉCTRICO_vs_TIERRA", 0.0);
        EFFECTIVENESS_MAP.put("ELÉCTRICO_vs_VOLADOR", 2.0);

        // HIELO
        EFFECTIVENESS_MAP.put("HIELO_vs_FUEGO", 0.5);
        EFFECTIVENESS_MAP.put("HIELO_vs_AGUA", 0.5);
        EFFECTIVENESS_MAP.put("HIELO_vs_PLANTA", 2.0);
        EFFECTIVENESS_MAP.put("HIELO_vs_TIERRA", 2.0);
        EFFECTIVENESS_MAP.put("HIELO_vs_VOLADOR", 2.0);
        EFFECTIVENESS_MAP.put("HIELO_vs_DRAGÓN", 2.0);
        EFFECTIVENESS_MAP.put("HIELO_vs_ACERO", 0.5);

        // LUCHA
        EFFECTIVENESS_MAP.put("LUCHA_vs_NORMAL", 2.0);
        EFFECTIVENESS_MAP.put("LUCHA_vs_HIELO", 2.0);
        EFFECTIVENESS_MAP.put("LUCHA_vs_ROCA", 2.0);
        EFFECTIVENESS_MAP.put("LUCHA_vs_SINIESTRO", 2.0);
        EFFECTIVENESS_MAP.put("LUCHA_vs_ACERO", 2.0);
        EFFECTIVENESS_MAP.put("LUCHA_vs_VOLADOR", 0.5);
        EFFECTIVENESS_MAP.put("LUCHA_vs_VENENO", 0.5);
        EFFECTIVENESS_MAP.put("LUCHA_vs_PSÍQUICO", 0.5);
        EFFECTIVENESS_MAP.put("LUCHA_vs_BICHO", 0.5);
        EFFECTIVENESS_MAP.put("LUCHA_vs_FANTASMA", 0.0);
        EFFECTIVENESS_MAP.put("LUCHA_vs_HADA", 0.5);

        // VENENO
        EFFECTIVENESS_MAP.put("VENENO_vs_PLANTA", 2.0);
        EFFECTIVENESS_MAP.put("VENENO_vs_HADA", 2.0);
        EFFECTIVENESS_MAP.put("VENENO_vs_VENENO", 0.5);
        EFFECTIVENESS_MAP.put("VENENO_vs_TIERRA", 0.5);
        EFFECTIVENESS_MAP.put("VENENO_vs_ROCA", 0.5);
        EFFECTIVENESS_MAP.put("VENENO_vs_FANTASMA", 0.5);
        EFFECTIVENESS_MAP.put("VENENO_vs_ACERO", 0.0);

        // TIERRA
        EFFECTIVENESS_MAP.put("TIERRA_vs_FUEGO", 2.0);
        EFFECTIVENESS_MAP.put("TIERRA_vs_ELÉCTRICO", 2.0);
        EFFECTIVENESS_MAP.put("TIERRA_vs_VENENO", 2.0);
        EFFECTIVENESS_MAP.put("TIERRA_vs_ROCA", 2.0);
        EFFECTIVENESS_MAP.put("TIERRA_vs_ACERO", 2.0);
        EFFECTIVENESS_MAP.put("TIERRA_vs_PLANTA", 0.5);
        EFFECTIVENESS_MAP.put("TIERRA_vs_BICHO", 0.5);
        EFFECTIVENESS_MAP.put("TIERRA_vs_VOLADOR", 0.0);

        // VOLADOR
        EFFECTIVENESS_MAP.put("VOLADOR_vs_PLANTA", 2.0);
        EFFECTIVENESS_MAP.put("VOLADOR_vs_LUCHA", 2.0);
        EFFECTIVENESS_MAP.put("VOLADOR_vs_BICHO", 2.0);
        EFFECTIVENESS_MAP.put("VOLADOR_vs_TIERRA", 1.0);
        EFFECTIVENESS_MAP.put("VOLADOR_vs_ELÉCTRICO", 0.5);
        EFFECTIVENESS_MAP.put("VOLADOR_vs_ROCA", 0.5);
        EFFECTIVENESS_MAP.put("VOLADOR_vs_ACERO", 0.5);

        // PSÍQUICO
        EFFECTIVENESS_MAP.put("PSÍQUICO_vs_LUCHA", 2.0);
        EFFECTIVENESS_MAP.put("PSÍQUICO_vs_VENENO", 2.0);
        EFFECTIVENESS_MAP.put("PSÍQUICO_vs_PSÍQUICO", 0.5);
        EFFECTIVENESS_MAP.put("PSÍQUICO_vs_SINIESTRO", 0.0);
        EFFECTIVENESS_MAP.put("PSÍQUICO_vs_ACERO", 0.5);

        // BICHO
        EFFECTIVENESS_MAP.put("BICHO_vs_PLANTA", 2.0);
        EFFECTIVENESS_MAP.put("BICHO_vs_PSÍQUICO", 2.0);
        EFFECTIVENESS_MAP.put("BICHO_vs_SINIESTRO", 2.0);
        EFFECTIVENESS_MAP.put("BICHO_vs_FUEGO", 0.5);
        EFFECTIVENESS_MAP.put("BICHO_vs_LUCHA", 0.5);
        EFFECTIVENESS_MAP.put("BICHO_vs_VENENO", 0.5);
        EFFECTIVENESS_MAP.put("BICHO_vs_VOLADOR", 0.5);
        EFFECTIVENESS_MAP.put("BICHO_vs_FANTASMA", 0.5);
        EFFECTIVENESS_MAP.put("BICHO_vs_ACERO", 0.5);
        EFFECTIVENESS_MAP.put("BICHO_vs_HADA", 0.5);

        // ROCA
        EFFECTIVENESS_MAP.put("ROCA_vs_FUEGO", 2.0);
        EFFECTIVENESS_MAP.put("ROCA_vs_HIELO", 2.0);
        EFFECTIVENESS_MAP.put("ROCA_vs_VOLADOR", 2.0);
        EFFECTIVENESS_MAP.put("ROCA_vs_BICHO", 2.0);
        EFFECTIVENESS_MAP.put("ROCA_vs_TIERRA", 0.5);
        EFFECTIVENESS_MAP.put("ROCA_vs_LUCHA", 0.5);
        EFFECTIVENESS_MAP.put("ROCA_vs_ACERO", 0.5);

        // FANTASMA
        EFFECTIVENESS_MAP.put("FANTASMA_vs_PSÍQUICO", 2.0);
        EFFECTIVENESS_MAP.put("FANTASMA_vs_FANTASMA", 2.0);
        EFFECTIVENESS_MAP.put("FANTASMA_vs_NORMAL", 0.0);
        EFFECTIVENESS_MAP.put("FANTASMA_vs_LUCHA", 0.0);
        EFFECTIVENESS_MAP.put("FANTASMA_vs_SINIESTRO", 0.5);

        // DRAGÓN
        EFFECTIVENESS_MAP.put("DRAGÓN_vs_DRAGÓN", 2.0);
        EFFECTIVENESS_MAP.put("DRAGÓN_vs_ACERO", 0.5);
        EFFECTIVENESS_MAP.put("DRAGÓN_vs_HADA", 0.0);

        // SINIESTRO
        EFFECTIVENESS_MAP.put("SINIESTRO_vs_PSÍQUICO", 2.0);
        EFFECTIVENESS_MAP.put("SINIESTRO_vs_FANTASMA", 2.0);
        EFFECTIVENESS_MAP.put("SINIESTRO_vs_LUCHA", 0.5);
        EFFECTIVENESS_MAP.put("SINIESTRO_vs_SINIESTRO", 0.5);
        EFFECTIVENESS_MAP.put("SINIESTRO_vs_HADA", 0.5);

        // ACERO
        EFFECTIVENESS_MAP.put("ACERO_vs_NORMAL", 2.0);
        EFFECTIVENESS_MAP.put("ACERO_vs_HIELO", 2.0);
        EFFECTIVENESS_MAP.put("ACERO_vs_ROCA", 2.0);
        EFFECTIVENESS_MAP.put("ACERO_vs_HADA", 2.0);
        EFFECTIVENESS_MAP.put("ACERO_vs_FUEGO", 0.5);
        EFFECTIVENESS_MAP.put("ACERO_vs_AGUA", 0.5);
        EFFECTIVENESS_MAP.put("ACERO_vs_PLANTA", 0.5);
        EFFECTIVENESS_MAP.put("ACERO_vs_ELÉCTRICO", 0.5);
        EFFECTIVENESS_MAP.put("ACERO_vs_ACERO", 0.5);
        EFFECTIVENESS_MAP.put("ACERO_vs_PSÍQUICO", 1.0);
        EFFECTIVENESS_MAP.put("ACERO_vs_VENENO", 0.0);

        // HADA
        EFFECTIVENESS_MAP.put("HADA_vs_LUCHA", 2.0);
        EFFECTIVENESS_MAP.put("HADA_vs_SINIESTRO", 2.0);
        EFFECTIVENESS_MAP.put("HADA_vs_DRAGÓN", 2.0);
        EFFECTIVENESS_MAP.put("HADA_vs_VENENO", 0.5);
        EFFECTIVENESS_MAP.put("HADA_vs_ACERO", 0.5);
    }

    
    public static double getEffectiveness(Tipo tipoAtacante, Tipo tipoDefensor) {
        String key = tipoAtacante.name() + "_vs_" + tipoDefensor.name();
        return EFFECTIVENESS_MAP.getOrDefault(key, 1.0);
    }

    
    public static double getTotalEffectiveness(Tipo tipoAtacante, java.util.ArrayList<Tipo> tiposDefensor) {
        double efectividadTotal = 1.0;
        for (Tipo tipoDefensa : tiposDefensor) {
            efectividadTotal *= getEffectiveness(tipoAtacante, tipoDefensa);
        }
        return efectividadTotal;
    }

    
    public static String getEffectivenessDescription(double multiplicador) {
        if (multiplicador == 0.0) {
            return "sin efecto";
        } else if (multiplicador < 1.0) {
            return "no es muy efectivo";
        } else if (multiplicador == 1.0) {
            return "efectivo";
        } else {
            return "¡es muy efectivo!";
        }
    }
}
