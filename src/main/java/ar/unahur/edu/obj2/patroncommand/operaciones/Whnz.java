package ar.unahur.edu.obj2.patroncommand.operaciones;

import java.util.List;

import ar.unahur.edu.obj2.patroncommand.excepciones.OperacionCompuestaException;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Comando;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.ComandoCompuesto;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public class Whnz extends ComandoCompuesto{

    public Whnz(Comando comando) {
        super(comando);
    }

    public Whnz(List<Comando> comando) {
        super(comando);
    }

    @Override
    protected void doExecute(Programable micro) {
        for (Comando comando : comandos) {
            if (micro.getAcumuladorA() != 0) {
                throw new OperacionCompuestaException("Acumulador 'A' fue distinto de 0 durante la ejecución");
            }
            comando.execute(micro);
        }
    }

}
