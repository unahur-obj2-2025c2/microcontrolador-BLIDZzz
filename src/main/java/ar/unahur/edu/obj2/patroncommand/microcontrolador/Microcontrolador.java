package ar.unahur.edu.obj2.patroncommand.microcontrolador;

import java.util.Arrays;
import java.util.List;

import ar.unahur.edu.obj2.patroncommand.excepciones.FueraDeRangoDeMemoriaException;
import ar.unahur.edu.obj2.patroncommand.operaciones.Operable;

public class Microcontrolador implements Programable{
    private Integer acumuladorA;
    private Integer acumuladorB;
    private Integer programCounter;
    private List<Integer> memoria;

    public Microcontrolador() {
        reset();
    }

    @Override
    public void run(List<Operable> operaciones) {
        operaciones.forEach(o -> o.execute(this));
    }

    @Override
    public void incProgramCounter() {
        programCounter ++;
    }

    @Override
    public Integer getProgramCounter() {
        return programCounter;
    }

    @Override
    public void setAcumuladorA(Integer value) {
        acumuladorA = value;
    }

    @Override
    public Integer getAcumuladorA() {
        return acumuladorA;
    }

    @Override
    public void setAcumuladorB(Integer value) {
        acumuladorB = value;
    }

    @Override
    public Integer getAcumuladorB() {
        return acumuladorB;
    }

    @Override
    public void setAddr(Integer addr) {
        estaDentroDelRangoDeMemoria(addr);
        // Las listas no permiten acceder a datos via corchetes
        memoria.set(addr, acumuladorA);
    }

    @Override
    public Integer getAddr(Integer addr) {
        estaDentroDelRangoDeMemoria(addr);
        // Las listas no permiten acceder a datos via corchetes
        return memoria.get(addr);
    }

    private void estaDentroDelRangoDeMemoria(Integer direccionEnMemoria){
        if (direccionEnMemoria < 0 || 1023 < memoria.size()) {
            throw new FueraDeRangoDeMemoriaException();
        }
    }

    @Override
    public void reset() {
        acumuladorA = 0;
        acumuladorB = 0;
        programCounter = 0;
        memoria = Arrays.asList(new Integer[1024]);
    }

    @Override
    public Programable copiar() {
        Microcontrolador nuevo = new Microcontrolador();
        nuevo.acumuladorA = this.acumuladorA;
        nuevo.acumuladorB = this.acumuladorB;
        nuevo.programCounter = this.programCounter;
        return nuevo;
    }

    @Override
    public void copiarDesde(Programable microDeRespaldo) {
        this.acumuladorA = microDeRespaldo.getAcumuladorA();
        this.acumuladorB = microDeRespaldo.getAcumuladorB();
        this.programCounter = microDeRespaldo.getProgramCounter();
        
    }


}
