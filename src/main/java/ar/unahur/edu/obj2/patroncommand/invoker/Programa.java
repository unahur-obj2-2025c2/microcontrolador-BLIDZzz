package ar.unahur.edu.obj2.patroncommand.invoker;

import java.util.ArrayList;
import java.util.List;

import ar.unahur.edu.obj2.patroncommand.careTaker.HistoryManager;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;
import ar.unahur.edu.obj2.patroncommand.operaciones.Operable;

public class Programa {

    private HistoryManager historial;

    private List<Operable> operaciones;

    public Programa(){
        this.operaciones = new ArrayList<>();
        
        historial = new HistoryManager();
    }

    public void agregarOperacion(Operable operacion) {
        operaciones.add(operacion);
    }

    public void vaciarLista(){
        operaciones.clear();
    }

    public void ejecutar(Programable micro){
        historial.run(micro, operaciones);
    }

    public void resetearMicro(Programable micro){
        micro.reset();
    }

    public void undo(Programable micro){
        historial.undo(micro);
    }

    public void redo(Programable micro){
        historial.undo(micro);
    }

}
