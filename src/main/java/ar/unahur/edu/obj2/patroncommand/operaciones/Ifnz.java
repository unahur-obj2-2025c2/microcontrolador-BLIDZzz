package ar.unahur.edu.obj2.patroncommand.operaciones;

import java.util.List;

import ar.unahur.edu.obj2.patroncommand.excepciones.OperacionCompuestaException;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Comando;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.ComandoCompuesto;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public class Ifnz extends ComandoCompuesto{

    

    public Ifnz(Comando comando) {
        super(comando);
    }

    public Ifnz(List<Comando> comando) {
        super(comando);
    }

    @Override
    protected void doExecute(Programable micro) {
        if (micro.getAcumuladorA() != 0) {
            throw new OperacionCompuestaException("El micro no tenía su valor 'A' en 0 al ejecutar el comando");
        }
        for (Comando comando : comandos) {
            comando.execute(micro);
        }
    }

}
