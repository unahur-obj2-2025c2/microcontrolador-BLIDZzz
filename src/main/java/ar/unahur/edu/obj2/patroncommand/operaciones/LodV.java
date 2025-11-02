package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Comando;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public class LodV extends Comando{

    private final Integer val;

    public LodV(Integer val){
        this.val = val;
    }

    @Override
    protected void doExecute(Programable micro) {
        micro.setAcumuladorA(val);;
    }

}
