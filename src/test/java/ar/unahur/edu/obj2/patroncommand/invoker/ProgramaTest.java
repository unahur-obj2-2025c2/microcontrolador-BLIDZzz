package ar.unahur.edu.obj2.patroncommand.invoker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Microcontrolador;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;
import ar.unahur.edu.obj2.patroncommand.operaciones.Add;
import ar.unahur.edu.obj2.patroncommand.operaciones.Lod;
import ar.unahur.edu.obj2.patroncommand.operaciones.LodV;
import ar.unahur.edu.obj2.patroncommand.operaciones.Nop;
import ar.unahur.edu.obj2.patroncommand.operaciones.Str;
import ar.unahur.edu.obj2.patroncommand.operaciones.Swap;

public class ProgramaTest {

    private Programa p = new Programa();
    private Programable micro = new Microcontrolador();

    @BeforeEach
    void setUp() {
        p.vaciarLista();
        p.resetearMicro(micro);
    }

    @Test
    void avanzar3PosicionesElProgramaCounter() {
        p.agregarOperacion(new Nop());
        p.agregarOperacion(new Nop());
        p.agregarOperacion(new Nop());

        p.ejecutar(micro);

        assertEquals(3, micro.getProgramCounter());
    }

    @Test
    void sumar20Mas17YObtener37EnElAcumulador() {
        p.agregarOperacion(new LodV(20)); // carga 20 en acumulador a mientras b es 0
        p.agregarOperacion(new Swap()); // invierte los valores, a = 0 y b = 20
        p.agregarOperacion(new LodV(17)); // carga 17 en a
        p.agregarOperacion(new Add()); // suma los valores y guarda el resultado en a y coloca 0 en b

        p.ejecutar(micro);

        assertEquals(0, micro.getAcumuladorB());
        assertEquals(37, micro.getAcumuladorA());
        assertEquals(5, micro.getProgramCounter());
    }

    @Test
    void sumar2Mas8Mas5EnAcumuladorA() {
        p.agregarOperacion(new LodV(2)); // carga 2 en a
        p.agregarOperacion(new Str(0)); // guarda en la posicion 0 el 2
        p.agregarOperacion(new LodV(8)); // carga 8 en a

        p.agregarOperacion(new Swap()); // invierte el a y el b y quedan 0 en a y 8 en b
        p.agregarOperacion(new LodV(5)); // carga el 5 en a
        p.agregarOperacion(new Add()); // suma los valores y queda el resultado en a

        p.agregarOperacion(new Swap()); // intercambia los valores
        p.agregarOperacion(new Lod(0)); // carga el valor de la posicion 0 en a
        p.agregarOperacion(new Add()); // suma los valores y queda el resultado en a
    }

}
