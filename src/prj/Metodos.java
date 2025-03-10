package prj;
import javax.swing.JOptionPane;
import java.io.*;
public class Metodos {
    public Eleitor[] RegistrarEleitor(Eleitor[] eleitor) throws IOException{
        int i, j; String fileName = "Cadastro_Eleitor.txt";
        BufferedWriter gravar = new BufferedWriter(new FileWriter(fileName));
        int valida[] = {1,3,4,5,9,10};
        int contsec1 = 0;
        int contsec2 = 0;
        int tentativas = 0;
        for(i = 0; i < 10; i++){
            eleitor[i] = new Eleitor();
        }
        for(i = 0; i < 10; i++){
            eleitor[i].numeleitor = Integer.parseInt(JOptionPane.showInputDialog("num eleitor"));
            gravar.write(Integer.toString(eleitor[i].numeleitor));
            gravar.newLine();
            eleitor[i].nomeleitor = JOptionPane.showInputDialog("nome eleitor");
            gravar.write(eleitor[i].nomeleitor);
            gravar.newLine();
            boolean validasecao = false;
            while(!validasecao){
                eleitor[i].secao = Integer.parseInt(JOptionPane.showInputDialog("secao"));
                for(j = 0; j < 6; j++){ 
                    if(eleitor[i].secao == valida[j]){ // verifica se a sessão é válida
                        validasecao = true;
                        break;
                    } 
                } if(!validasecao){ // 
                    tentativas++; // isso faz com que o loop não fique infinito
                    JOptionPane.showMessageDialog(null, "Seção Inválida");
                    if(tentativas >= 3){
                        JOptionPane.showMessageDialog(null, "NÚMERO DE TENTATIVAS EXCEDIDO");
                        System.exit(0);
                    }
                } else {
                    if(eleitor[i].secao == 1 || eleitor[i].secao == 3 || eleitor[i].secao == 4){ // 
                        if(contsec1 < eleitor.length / 2 ){ // esse conta se já alcancou o limite da votacao 1
                            contsec1++; 
                            validasecao = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Limite de eleitores para as seções 1, 3 e 4 alcançado");
                        }
                    } else if (eleitor[i].secao == 5 || eleitor[i].secao == 9 || eleitor[i].secao == 10) {
                        if(contsec2 < eleitor.length / 2){ // esse conta se já alcancou o limite da votacao 2
                            contsec2++;  
                            validasecao = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Limite de eleitores para as seções 5, 9 e 10 alcançado.");
                        }
                    }
                }
            }
            gravar.write(Integer.toString(eleitor[i].secao));
            gravar.newLine();
        }
            gravar.close();
            return eleitor;
    }
    public Votcao[] RegistraVotacao(Eleitor[]eleitor, int secao12, Votcao[]votacao) throws IOException{
        String fileName = ("CadastroVotacao"+ secao12 +".txt");
        String fileName1 = "Cadastro_Eleitor.txt";
        Votcao aux[] = new Votcao[1];
        int i, j;
        BufferedReader ler = new BufferedReader(new FileReader(fileName1));
        BufferedWriter gravar = new BufferedWriter(new FileWriter(fileName));
        for(i = 0; i < 10; i++){
            eleitor[i] = new Eleitor();
        }
        for(i = 0; i < 10; i++){
            eleitor[i].numeleitor = Integer.parseInt(ler.readLine());
            eleitor[i].nomeleitor = ler.readLine();
            eleitor[i].secao = Integer.parseInt(ler.readLine());
        }
        ler.close();
        for(i = 0; i < 5; i++){
            votacao[i] = new Votcao();
        }
        for(i = 0; i < 5; i++){
            if(secao12 == 1){ // verifica se sessao é 1 
                for(j = 0; j < eleitor.length; j++){
                    if(eleitor[j].secao == 1 || eleitor[j].secao == 3 || eleitor[j].secao == 4){
                        votacao[i].secao = eleitor[j].secao; // secao precisa ser igual a do outro
                        eleitor[j].secao = 0;
                        votacao[i].numeleitor = eleitor[j].numeleitor; // numeleitor precisa ser igual a do outro
                        eleitor[j].secao = 0;
                        votacao[i].codcandidato = Integer.parseInt(JOptionPane.showInputDialog("códigio do candidato"));
                        if(votacao[i].codcandidato > 4){ // acima de 4 vai virar nulo de qualquer jeito
                            votacao[i].codcandidato = 4;
                        }
                        break;
                    }
                }
            } else{ // verifica se sessao é 2
                for(j = 0; j < eleitor.length; j++){
                    if(eleitor[j].secao == 5 || eleitor[j].secao == 9 || eleitor[j].secao == 10){
                        votacao[i].secao = eleitor[j].secao; // secao precisa ser igual a do outro
                        eleitor[j].secao = 0;
                        votacao[i].numeleitor = eleitor[j].numeleitor; // numeleitor precisa ser igual a do outro
                        eleitor[j].secao = 0;
                        votacao[i].codcandidato = Integer.parseInt(JOptionPane.showInputDialog("código do candidato"));
                        if(votacao[i].codcandidato > 4){ // acima de 4 vai virar nulo de qualquer jeito
                            votacao[i].codcandidato = 4;
                        }
                    break;
                    }
                }
            }
        }
        for(i = 0; i < 4; i++){
            for(j = i+1; j < 5; j++){
                if(votacao[i].codcandidato > votacao[j].codcandidato){
                    aux[0] = votacao[i];
                    votacao[i] = votacao[j];
                    votacao[j] = aux[0];
                }
            }
        }
        for(i = 0; i < 5; i++){
            gravar.write(Integer.toString(votacao[i].secao));
            gravar.newLine();
            gravar.write(Integer.toString(votacao[i].codcandidato));
            gravar.newLine();
            gravar.write(Integer.toString(votacao[i].numeleitor));
            gravar.newLine();
        }
        gravar.close();
        return votacao;
    }
    public Votcao[] AgrupaApuracao(Votcao[] votacao1, Votcao[] votacao2) throws IOException {
        Votcao[] apuracao = new Votcao[10];
        String fileName1 = "CadastroVotacao1.txt";
        String fileName2 = "CadastroVotacao2.txt";
        String fileName3 = "Apuracao.txt";
        BufferedReader ler1 = new BufferedReader(new FileReader (fileName1));
        BufferedReader ler2 = new BufferedReader(new FileReader (fileName2));
        BufferedWriter gravar = new BufferedWriter(new FileWriter (fileName3));
        int i;
        for(i = 0; i < 5; i++){
            votacao1[i] = new Votcao();
            votacao2[i] = new Votcao();
        }
        for(i = 0; i < 5; i++){
            votacao1[i].secao = Integer.parseInt(ler1.readLine());
            votacao1[i].codcandidato = Integer.parseInt(ler1.readLine());
            votacao1[i].numeleitor = Integer.parseInt(ler1.readLine());
            votacao2[i].secao = Integer.parseInt(ler2.readLine());
            votacao2[i].codcandidato = Integer.parseInt(ler2.readLine());
            votacao2[i].numeleitor = Integer.parseInt(ler2.readLine());
        }    
        for(i = 0; i < 10; i++){
            apuracao[i] = new Votcao();
        }
        i = 0;
        int j = 0; int k = 0;
        while (i < votacao1.length && j < votacao2.length) { // balance-line 
            if (votacao1[i].codcandidato < votacao2[j].codcandidato) {
                apuracao[k] = votacao1[i];
                i++;
            } else {
                apuracao[k] = votacao2[j];
                j++;
            }
            k++;
        }
        while (i < votacao1.length) { // verifica se nada ficou de fora
            apuracao[k++] = votacao1[i++];
        }

        while (j < votacao2.length) { // verifica se nada ficou de fora
            apuracao[k++] = votacao2[j++];
        }
        for(i = 0; i < 10; i++){
            gravar.write(Integer.toString(apuracao[i].secao));
            gravar.newLine();
            gravar.write(Integer.toString(apuracao[i].codcandidato));
            gravar.newLine();
            gravar.write(Integer.toString(apuracao[i].numeleitor));
            gravar.newLine();
        }
        ler1.close();
        ler2.close();
        gravar.close();
        System.out.println("Apuração concluída!");
        return apuracao;
    }
    public int[] Apuracaogeral(Votcao[] apuracao, int[] contagemdevotos) throws IOException{
        int i, aux; String fileName = "Apuracao.txt";
        BufferedReader ler = new BufferedReader(new FileReader(fileName));
        for(i = 0; i < apuracao.length; i++){
            apuracao[i] = new Votcao();
            apuracao[i].secao = Integer.parseInt(ler.readLine());
            apuracao[i].codcandidato = Integer.parseInt(ler.readLine());
            apuracao[i].numeleitor = Integer.parseInt(ler.readLine());
        }
        for(i = 0; i < apuracao.length; i++){
            aux = apuracao[i].codcandidato;
            contagemdevotos[aux-1]++;
        }
        
        return contagemdevotos;
    }
    public void mostraovencedor(int[]contagemdevotos) throws IOException {
        if(contagemdevotos[0] > contagemdevotos[1]){
            JOptionPane.showMessageDialog(null, "O vencedor foi o candidato de código 1 ");
        } else{
            JOptionPane.showMessageDialog(null,"O vencedor colocado foi o candidato de código 2 ");
        }
    }
    public void mostrasegundo(int[]contagemdevotos) throws IOException {
        if(contagemdevotos[0] < contagemdevotos[1]){
            JOptionPane.showMessageDialog(null,"O Segundo colocado foi o candidato de código 1 ");
        } else{
            JOptionPane.showMessageDialog(null,"O Segundo colocado foi o candidato de código 2 ");
        }
    }
    public void mostrabrancos(int[]contagemdevotos) throws IOException {
        JOptionPane.showMessageDialog(null,"A quantidade de votos em brancos foi de " + contagemdevotos[2]);
    }
    public void mostranulos(int[]contagemdevotos) throws IOException {
        JOptionPane.showMessageDialog(null,"A quantidade de votos nulos foi de " + contagemdevotos[3]);
    }
    public void mostraeleitores(Votcao[] apuracao) throws IOException {
        int i; String fileName = "Apuracao.txt";
        BufferedReader ler = new BufferedReader(new FileReader(fileName));
        for(i = 0; i < apuracao.length; i++){
            apuracao[i] = new Votcao();
            apuracao[i].secao = Integer.parseInt(ler.readLine());
            apuracao[i].codcandidato = Integer.parseInt(ler.readLine());
            apuracao[i].numeleitor = Integer.parseInt(ler.readLine());
        }
        for(i = 0; i < apuracao.length; i++){
            System.out.println("O número de eleitor dos eleitores votantes foi: " + apuracao[i].numeleitor);
        }
    }
    public void mostrapuracao(Votcao[] apuracao) throws IOException {
        int i; String fileName = "Apuracao.txt";
        BufferedReader ler = new BufferedReader(new FileReader(fileName));
        for(i = 0; i < apuracao.length; i++){
            apuracao[i] = new Votcao();
            apuracao[i].secao = Integer.parseInt(ler.readLine());
            apuracao[i].codcandidato = Integer.parseInt(ler.readLine());
            apuracao[i].numeleitor = Integer.parseInt(ler.readLine());
        }
        for(i = 0; i < apuracao.length; i++){
            System.out.println("Apuracao[" + i + "] -> secao: " + apuracao[i].secao + 
            ", codcandidato: " + apuracao[i].codcandidato + 
            ", numeleitor: " + apuracao[i].numeleitor);
        }
        ler.close();
    }
}    

                
                