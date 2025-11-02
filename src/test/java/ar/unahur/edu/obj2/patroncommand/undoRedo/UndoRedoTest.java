package ar.unahur.edu.obj2.patroncommand.undoRedo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.unahur.edu.obj2.patroncommand.invoker.Programa;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Microcontrolador;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;
import ar.unahur.edu.obj2.patroncommand.operaciones.Add;
import ar.unahur.edu.obj2.patroncommand.operaciones.Lod;
import ar.unahur.edu.obj2.patroncommand.operaciones.LodV;
import ar.unahur.edu.obj2.patroncommand.operaciones.Nop;
import ar.unahur.edu.obj2.patroncommand.operaciones.Str;
import ar.unahur.edu.obj2.patroncommand.operaciones.Swap;

public class UndoRedoTest {
    private Microcontrolador m = new Microcontrolador();
    private Programa p = new Programa();
    
    @BeforeEach
    public void setUp(){
        m.reset();
    }

    @Test
    public void funcionaLoDelUndo(){
        p.agregarOperacion(new LodV(10));
        p.agregarOperacion(new Swap());
        p.agregarOperacion(new LodV(10));
        p.agregarOperacion(new Add());
        p.ejecutar(m);
        assertEquals(20, m.getAcumuladorA());
    }
}
