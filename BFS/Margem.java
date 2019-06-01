

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willy
 */
public class Margem {
    public int canibais;
    public int missionarios;
    public int canoa;
    public Margem estado_anterior = null;
    public Margem lado_oposto;
    public int[] passageiros = { 0, 0}; 
    
    public void Margem(){
        this.canibais = 0;
        this.canibais = 0;
        this.canoa = 0;
    }
}