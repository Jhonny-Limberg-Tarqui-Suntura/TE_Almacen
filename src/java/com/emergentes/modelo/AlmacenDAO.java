package com.emergentes.modelo;
import java.util.ArrayList;

public class AlmacenDAO {
    private int correlativo;
    private ArrayList<Almacen> alm;

    public AlmacenDAO() {
        alm = new ArrayList<Almacen>();
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public ArrayList<Almacen> getAlm() {
        return alm;
    }

    public void setLibros(ArrayList<Almacen> alm) {
        this.alm = alm;
    }
    public void insertar(Almacen item){
        alm.add(item);
    }
    public void modificar(int id, Almacen item){
        int pos = posicion(id);
        alm.set(pos, item);
    }
    public void eliminar(int id){
        int pos = posicion(id);
        alm.remove(pos);
    }
    public int posicion(int id){
        int i = -1;
        if(alm.size() > 0){
            for(Almacen item : alm){
                ++i;
                if(item.getId() == id){
                    break;
                }
            }
        }
        return i;
    }
    
}
