package prj;
class Votcao {
    int secao;
    int codcandidato;
    int numeleitor;
    Votcao(){
        this(0,0,0);
    }
    Votcao(int sec, int cod, int num){
        secao = sec;
        codcandidato = cod;
        numeleitor = num;
    }
}