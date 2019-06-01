import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willy
 */
public class Busca {

    public Margem margem;
    public int[] canibais_movimentos = { 2, 1, 1, 0, 0};
    public int[] missionarios_movimentos = { 0, 0, 1, 1, 2};
    //public int[] canibais_enviados = {-2,-1,-1, 0, 0};
    //public int[] missionarios_enviados = { 0, 0,-1,-1,-2};
    public Queue<Margem> fronteira = new LinkedList();

    Busca(Margem margem) {
        this.margem = margem;
    }
    
    public void movimentos(int canibais, int missionarios, int canoa , Margem m){
        int margem_oposta_num_canibais = 3 - canibais;
        int margem_oposta_num_missionarios = 3 - missionarios; 
        int novo_estado_canibais;
        int novo_estado_missionarios;
        int novo_estado_canibais_oposto;
        int novo_estado_missionarios_oposto;
        for(int i = 0; i < 5 ; i ++){
            //Se a canoa estado outro lado, entao ele recebera mais missionarios e/ou canibais
            if(canoa == 0){
                novo_estado_canibais = canibais + canibais_movimentos[i];
                novo_estado_missionarios = missionarios + missionarios_movimentos[i];
                novo_estado_canibais_oposto =  margem_oposta_num_canibais - canibais_movimentos[i];
                novo_estado_missionarios_oposto = margem_oposta_num_missionarios - missionarios_movimentos[i]; 
            }
            //Se nao, se a canoa esta do lado certo os missionarios e/ou canibais serao enviados para a outra margem
            else{
                novo_estado_canibais = canibais - canibais_movimentos[i];
                novo_estado_missionarios = missionarios - missionarios_movimentos[i];
                novo_estado_canibais_oposto = margem_oposta_num_canibais  + canibais_movimentos[i];
                novo_estado_missionarios_oposto = margem_oposta_num_missionarios + missionarios_movimentos[i];
            }            
            //Testar se esta dentro do intervalo de 1 a 3
            boolean teste_margem_atual = novo_estado_canibais < 4 && novo_estado_missionarios < 4  && novo_estado_canibais > -1 && novo_estado_missionarios > -1;
            boolean teste_margem_oposta = novo_estado_canibais_oposto < 4 && novo_estado_missionarios_oposto < 4  && novo_estado_canibais_oposto > -1 && novo_estado_missionarios_oposto > -1;
          
            //Testa se o numero de canibais nas duas margens e menor ou igual ao numero de missionarios
            boolean canibais_maior_numero = novo_estado_missionarios >= novo_estado_canibais;
            boolean canibais_maior_numero_oposto = novo_estado_missionarios_oposto >= novo_estado_canibais_oposto;
            if(novo_estado_missionarios == 0){ canibais_maior_numero = true;}
            if(novo_estado_missionarios_oposto == 0){ canibais_maior_numero_oposto = true;}
            
            //Se todas as condiçoes de envio forem atendidas ele salvara o objeto
            if(teste_margem_atual && teste_margem_oposta && canibais_maior_numero && canibais_maior_numero_oposto){                
                Margem mg = new Margem();
                Margem mg_oposto = new Margem();
                mg.canibais = novo_estado_canibais;
                mg.passageiros[0] = canibais_movimentos[i];
                mg.passageiros[1] = missionarios_movimentos[i];
                mg.missionarios = novo_estado_missionarios;
                mg.estado_anterior = m;
		mg_oposto.estado_anterior = m.lado_oposto;
                mg_oposto.canibais = novo_estado_canibais_oposto;
                mg_oposto.missionarios = novo_estado_missionarios_oposto;
                
                //Definir o novo lado da canoa
                if(canoa == 1){
                    mg.canoa = 0;
                    mg_oposto.canoa = 1;
                }else{
                    mg.canoa = 1;
                    mg_oposto.canoa = 0;
                }
                mg.lado_oposto = mg_oposto;
                this.fronteira.add(mg);
            }
            
        }
    } 
    public Margem Buscar(){
        this.fronteira.add(this.margem);
        Margem aux = null;
        while(!this.fronteira.isEmpty()){
            Margem u = this.fronteira.poll();
            if(u.canibais == 3 && u.missionarios == 3){
                aux = u;
                break;
            }
            this.movimentos(u.canibais,u.missionarios,u.canoa, u);
            
        }
        return aux;
    }
    
}
