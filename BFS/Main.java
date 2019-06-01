/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willy
 */
public class Main {
    public static void main(String [] Args){
        Margem margem = new Margem();
        Margem m2 = new Margem();
        m2.canibais = 3;
        m2.canoa = 1;
        m2.missionarios = 3;
        margem.lado_oposto = m2;
        Busca busca = new Busca(margem);
        Margem m = busca.Buscar();
        
        while(m != null){
            if(m.estado_anterior != null){
                System.out.println("Margem 1: " + "canibais: " + m.estado_anterior.canibais + " missionarios: " + m.estado_anterior.missionarios + " canoa: " + m.canoa);
                System.out.println("Canoa: " + m.passageiros[0]+ " " + m.passageiros[1]);
                System.out.println("Margem 2: " + "canibais: " + m.lado_oposto.estado_anterior.canibais + " missionarios: " + m.lado_oposto.estado_anterior.missionarios + " canoa: " + m.lado_oposto.canoa);
                System.out.println("----------------------------------------------------");
            }else{
                System.out.println("Margem 1: " + "canibais: " + m.canibais + " missionarios: " + m.missionarios + " canoa: " + m.canoa);
                System.out.println("Canoa: " + m.passageiros[0]+ " " + m.passageiros[1]);
                System.out.println("Margem 2: " + "canibais: " + m.lado_oposto.canibais + " missionarios: " + m.lado_oposto.missionarios + " canoa: " + m.lado_oposto.canoa);
                System.out.println("----------------------------------------------------");
            }
            m = m.estado_anterior;
        }
    }
    
}
