
class Margem {
    constructor() {
        this.estado_anterior = null;
        this.passageiros = [0, 0];
        if (this.canibais === undefined)
            this.canibais = 0;
        if (this.missionarios === undefined)
            this.missionarios = 0;
        if (this.canoa === undefined)
            this.canoa = 0;
        if (this.lado_oposto === undefined)
            this.lado_oposto = null;
    }
    Margem() {
        this.canibais = 0;
        this.canibais = 0;
        this.canoa = 0;
    }
}
Margem["__class"] = "Margem";

class Busca {
    constructor(margem) {
        this.canibais_movimentos = [2, 1, 1, 0, 0];
        this.missionarios_movimentos = [0, 0, 1, 1, 2];
        this.fronteira = ([]);
        if (this.margem === undefined)
            this.margem = null;
        this.margem = margem;
    }
    movimentos(canibais, missionarios, canoa, m) {
        let margem_oposta_num_canibais = 3 - canibais;
        let margem_oposta_num_missionarios = 3 - missionarios;
        let novo_estado_canibais;
        let novo_estado_missionarios;
        let novo_estado_canibais_oposto;
        let novo_estado_missionarios_oposto;
        for (let i = 0; i < 5; i++) {
            {
                if (canoa === 0) {
                    novo_estado_canibais = canibais + this.canibais_movimentos[i];
                    novo_estado_missionarios = missionarios + this.missionarios_movimentos[i];
                    novo_estado_canibais_oposto = margem_oposta_num_canibais - this.canibais_movimentos[i];
                    novo_estado_missionarios_oposto = margem_oposta_num_missionarios - this.missionarios_movimentos[i];
                }
                else {
                    novo_estado_canibais = canibais - this.canibais_movimentos[i];
                    novo_estado_missionarios = missionarios - this.missionarios_movimentos[i];
                    novo_estado_canibais_oposto = margem_oposta_num_canibais + this.canibais_movimentos[i];
                    novo_estado_missionarios_oposto = margem_oposta_num_missionarios + this.missionarios_movimentos[i];
                }
                let teste_margem_atual = novo_estado_canibais < 4 && novo_estado_missionarios < 4 && novo_estado_canibais > -1 && novo_estado_missionarios > -1;
                let teste_margem_oposta = novo_estado_canibais_oposto < 4 && novo_estado_missionarios_oposto < 4 && novo_estado_canibais_oposto > -1 && novo_estado_missionarios_oposto > -1;
                let canibais_maior_numero = novo_estado_missionarios >= novo_estado_canibais;
                let canibais_maior_numero_oposto = novo_estado_missionarios_oposto >= novo_estado_canibais_oposto;
                if (novo_estado_missionarios === 0) {
                    canibais_maior_numero = true;
                }
                if (novo_estado_missionarios_oposto === 0) {
                    canibais_maior_numero_oposto = true;
                }
                if (teste_margem_atual && teste_margem_oposta && canibais_maior_numero && canibais_maior_numero_oposto) {
                    let mg = new Margem();
                    let mg_oposto = new Margem();
                    mg.canibais = novo_estado_canibais;
                    mg.passageiros[0] = this.canibais_movimentos[i];
                    mg.passageiros[1] = this.missionarios_movimentos[i];
                    mg.missionarios = novo_estado_missionarios;
                    mg.estado_anterior = m;
                    mg_oposto.estado_anterior = m.lado_oposto;
                    mg_oposto.canibais = novo_estado_canibais_oposto;
                    mg_oposto.missionarios = novo_estado_missionarios_oposto;
                    if (canoa === 1) {
                        mg.canoa = 0;
                        mg_oposto.canoa = 1;
                    }
                    else {
                        mg.canoa = 1;
                        mg_oposto.canoa = 0;
                    }
                    mg.lado_oposto = mg_oposto;
                    /* add */ (this.fronteira.push(mg) > 0);
                }
            }
            ;
        }
    }
    Buscar() {
        /* add */ (this.fronteira.push(this.margem) > 0);
        let aux = null;
        while ((!(this.fronteira.length == 0))) {
            {
                let u = (a => a.length == 0 ? null : a.shift())(this.fronteira);
                if (u.canibais === 3 && u.missionarios === 3) {
                    aux = u;
                    break;
                }
                this.movimentos(u.canibais, u.missionarios, u.canoa, u);
            }
        }
        ;
        return aux;
    }
}
Busca["__class"] = "Busca";