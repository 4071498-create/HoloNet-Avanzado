/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.holonetavanzado;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author prato
 */
public class HoloNetControl {
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    
    public List<Nave> obtenerNavesPorBandoPlaneta(String bando){
        try (Session session = sf.openSession()){
            String hql = "FROM Nave n INNER JOIN n.planetaOrigen p WHERE p.afiliacion = :bando AND n.estadoSistema <> 'Mantenimiento'";
            return session.createQuery(hql, Nave.class).setParameter("bando", bando).getResultList();
        }
    }
    
    public List<Nave> obtenerFlotaCriticaConPlaneta(){
        try (Session session = sf.openSession()){
            String hql = "FROM Nave n INNER JOIN FETCH n.planetaOrigen WHERE n.estadoSistema = 'Critico'";
            return session.createQuery(hql, Nave.class).getResultList();
        }
    }
    public List<Object[]> obtenerReportePlanetasAsignados(){
        try (Session session = sf.openSession()){
            String hql = "SELECT n.nombre p.nombre FROM Nave n LEFT JOIN n.planetaOrigen p";
            return session.createQuery(hql, Object[].class).getResultList();
        }
    }
}
