package ar.unahur.edu.obj2.patroncommand.comandosCompuestos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.unahur.edu.obj2.patroncommand.excepciones.OperacionCompuestaException;
import ar.unahur.edu.obj2.patroncommand.invoker.Programa;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.ComandoCompuesto;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Microcontrolador;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;
import ar.unahur.edu.obj2.patroncommand.operaciones.Add;
import ar.unahur.edu.obj2.patroncommand.operaciones.Ifnz;
import ar.unahur.edu.obj2.patroncommand.operaciones.LodV;
import ar.unahur.edu.obj2.patroncommand.operaciones.Swap;

public class ComandoCompuestoTest {
    private Programa p = new Programa();
    private Programable micro = new Microcontrolador();

    private ComandoCompuesto ifnz;

    @BeforeEach
    void setUp() {
        p.vaciarLista();
        p.resetearMicro(micro);
    }

    @Test
    void sumar20Mas17YObtener37EnElAcumulador() {

        ifnz = new Ifnz(new LodV(20));
        ifnz.agregarComando(new Swap());
        ifnz.agregarComando(new LodV(17));
        ifnz.agregarComando(new Add());

        //p.agregarOperacion(new LodV(20)); // carga 20 en acumulador a mientras b es 0
        //p.agregarOperacion(new Swap()); // invierte los valores, a = 0 y b = 20
        //p.agregarOperacion(new LodV(17)); // carga 17 en a
        //p.agregarOperacion(new Add()); // suma los valores y guarda el resultado en a y coloca 0 en b

        p.agregarOperacion(ifnz);
        p.ejecutar(micro);

        assertEquals(0, micro.getAcumuladorB());
        assertEquals(37, micro.getAcumuladorA());
        assertEquals(6, micro.getProgramCounter());
    }

    @Test
    void siANoEs0NoEjecutaYLanzaError(){
        ifnz = new Ifnz(new LodV(20));
        ifnz.agregarComando(new Swap());
        ifnz.agregarComando(new LodV(17));
        ifnz.agregarComando(new Add());

        p.agregarOperacion(new LodV(5));
        p.ejecutar(micro);

        p.agregarOperacion(ifnz);
        
        assertThrows(OperacionCompuestaException.class, () -> p.ejecutar(micro));
    }

    @Test
    void comandoCompuestoFuncionaUndo(){
        ifnz = new Ifnz(new LodV(20));
        ifnz.agregarComando(new Swap());
        ifnz.agregarComando(new LodV(17));
        ifnz.agregarComando(new Add());

        p.agregarOperacion(ifnz);
        p.ejecutar(micro);
        
        assertEquals(0, micro.getAcumuladorB());
        assertEquals(37, micro.getAcumuladorA());
        assertEquals(6, micro.getProgramCounter());

        p.undo(micro);

        assertEquals(0, micro.getAcumuladorA());
        assertEquals(0, micro.getProgramCounter());
    }
}
