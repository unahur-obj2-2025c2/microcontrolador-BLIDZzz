package ar.unahur.edu.obj2.patroncommand.careTaker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;
import ar.unahur.edu.obj2.patroncommand.operaciones.Operable;

public class HistoryManager {
    ArrayDeque<Operable> undoStack = new ArrayDeque<>();

    ArrayDeque<Operable> redoStack = new ArrayDeque<>();

    public void run(Programable micro, List<Operable> operaciones) {
        for (Operable operable : operaciones) {
            undoStack.add(operable);
            List<Operable> operacion = new ArrayList<Operable>();
            operacion.add(operable);
            micro.run(operacion);
        };
    }

    public void undo(Programable micro){
        Operable ultimoComando = undoStack.pop();
        redoStack.push(ultimoComando);
        ultimoComando.undo(micro);
    }

    public void redo(Programable micro){
        Operable ultimoComando = redoStack.pop();
        undoStack.push(ultimoComando);
        ultimoComando.execute(micro);
    }

}
