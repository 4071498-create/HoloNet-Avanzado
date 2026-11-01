/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.holonetavanzado;
import java.util.List;
/**
 *
 * @author aleja
 */
public class Main {
    public static void main(String[] args) {
        // Instanciamos el controlador que contiene los métodos HQL
        HoloNetControl control = new HoloNetControl();

        System.out.println("       FASE 4: ORQUESTACIÓN Y VERIFICACIÓN        ");

        // 1.Método: obtenerNavesPorBandoPlaneta
        String bandoPrueba = "Imperio";
        System.out.println("\n--- 1. Naves del bando: " + bandoPrueba + " ---");
        try {
            List<Nave> navesBando = control.obtenerNavesPorBandoPlaneta(bandoPrueba);
            if (navesBando.isEmpty()) {
                System.out.println("No se encontraron naves para este bando.");
            } else {
                for (Nave nave : navesBando) {
                    System.out.println("• Nave: " + nave.getNombre() + " | Estado: " + nave.getEstadoSistema());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al ejecutar obtenerNavesPorBandoPlaneta: " + e.getMessage());
        }

        // 2.Método: obtenerFlotaCriticaConPlaneta
        System.out.println("\n--- 2. Flota Crítica con Planeta de Origen ---");
        try {
            List<Nave> navesCriticas = control.obtenerFlotaCriticaConPlaneta();
            if (navesCriticas.isEmpty()) {
                System.out.println("No hay naves en estado crítico.");
            } else {
                for (Nave nave : navesCriticas) {
                    String nombrePlaneta = (nave.getPlanetaOrigen() != null) ? nave.getPlanetaOrigen().getNombre() : "Sin planeta";
                    System.out.println("• Nave Crítica: " + nave.getNombre() + " | Origen: " + nombrePlaneta);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al ejecutar obtenerFlotaCriticaConPlaneta: " + e.getMessage());
        }

        // 3. Probar método: obtenerReportePlanetasAsignados (List<Object[]>)
        System.out.println("\n--- 3. Reporte General de Naves y Planetas (LEFT JOIN) ---");
        try {
            List<Object[]> reportePlanetas = control.obtenerReportePlanetasAsignados();
            if (reportePlanetas.isEmpty()) {
                System.out.println("El reporte está vacío.");
            } else {
                for (Object[] fila : reportePlanetas) {
                    String nombreNave = (String) fila[0];
                    String nombrePlaneta = (fila[1] != null) ? (String) fila[1] : "null";
                    
                    System.out.println(String.format("• Nave: %-15s | Planeta de Origen: %s", nombreNave, nombrePlaneta));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al ejecutar obtenerReportePlanetasAsignados: " + e.getMessage());
        }
        System.out.println("          VERIFICACIÓN FINALIZADA CON ÉXITO       ");
    }
}
