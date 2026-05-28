package org.example;

/**
 * Interfaz para notificar cambios de estado de batalla a una vista.
 */
public interface BattleStatusView {
    /**
     * Actualiza el estado visible de los Pokemon en batalla.
     *
     * @param nombre1 nombre del primer Pokemon.
     * @param ps1 puntos de salud actuales del primer Pokemon.
     * @param max1 puntos de salud maximos del primer Pokemon.
     * @param nombre2 nombre del segundo Pokemon.
     * @param ps2 puntos de salud actuales del segundo Pokemon.
     * @param max2 puntos de salud maximos del segundo Pokemon.
     */
    void updateBattleStatus(String nombre1, int ps1, int max1, String nombre2, int ps2, int max2);
}
