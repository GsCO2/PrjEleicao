package prj;
import javax.swing.JOptionPane;
import java.io.*;
public class Main {
    public static void main(String args[]) throws IOException {
        int opc1 = 0;
        int opc2 = 0;
        int opc3 = 0;
        Votcao[] votacao1 = new Votcao[5];
        Votcao[] votacao2 = new Votcao[5];
        Eleitor[] eleitor = new Eleitor [10];
        Votcao[] apuracao = new Votcao[10];
        Metodos a = new Metodos();
        int[]contagemdevotos = new int[4];
        while(opc1 != 9){
            opc1 = Integer.parseInt(JOptionPane.showInputDialog("1- 2- 3- 4- 9- "));
            switch(opc1){
                case 1:
                    eleitor = a.RegistrarEleitor(eleitor);
                    break;
                case 2:
                    while(opc2 != 9){
                        int secao12 = 1; // essa variavel indica a seção 1 e 2
                        opc2 = Integer.parseInt(JOptionPane.showInputDialog("1- 2- 9- "));
                        switch(opc2){
                            case 1:
                                votacao1 = a.RegistraVotacao(eleitor, secao12, votacao1);
                                break;
                            case 2:
                                secao12++; // indica que a seção é 2
                                votacao2 = a.RegistraVotacao(eleitor, secao12, votacao2);
                                break;
                            case 9:
                                JOptionPane.showMessageDialog(null, "FIM");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opção Inválida");
                                break;
                        }
                    }
                    break;
                case 3:
                    apuracao = a.AgrupaApuracao(votacao1, votacao2);
                    break;
                case 4:
                    contagemdevotos = a.Apuracaogeral(apuracao, contagemdevotos);
                    while (opc3!= 9){
                        opc3 = Integer.parseInt(JOptionPane.showInputDialog("1- 2- 3- 4- 5- 6- 9- "));
                        switch(opc3){
                            case 1:
                                a.mostraovencedor(contagemdevotos);
                                break;
                            case 2:
                                a.mostrasegundo(contagemdevotos);
                                break;
                            case 3:
                                a.mostrabrancos(contagemdevotos);
                                break;
                            case 4:
                                a.mostranulos(contagemdevotos);
                                break;
                            case 5:
                                a.mostraeleitores(apuracao);
                                break;
                            case 6:
                                a.mostrapuracao(apuracao);
                                break;
                            case 9:
                                JOptionPane.showMessageDialog(null, "FIM");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opção Inválida");
                                break;
                        }
                    }
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null, "FIM");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida");
                    break;
            }
        }
    }
}
