package prj;

class Eleitor {
    int numeleitor;
    String nomeleitor;
    int secao;
    
    Eleitor(){
        this(0,"",0);
    }
    Eleitor(int num, String nome, int sec){
        numeleitor = num;
        nomeleitor = nome;
        secao = sec;
    }
}


