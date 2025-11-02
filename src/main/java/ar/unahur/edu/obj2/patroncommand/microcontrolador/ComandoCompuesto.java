package ar.unahur.edu.obj2.patroncommand.microcontrolador;

import java.util.ArrayList;
import java.util.List;

public abstract class ComandoCompuesto extends Comando{

    protected List<Comando> comandos;

    public ComandoCompuesto(Comando comando){
        comandos = new ArrayList<>();
        comandos.add(comando);
    }

    public ComandoCompuesto(List<Comando> comandos){
        comandos.addAll(comandos);
    }

    public void agregarComando(Comando comando){
        comandos.add(comando);
    }
}
