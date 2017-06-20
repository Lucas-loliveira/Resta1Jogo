package PackSolver;




import java.util.ArrayList;

public class Solver {

    private Stack stack = new Stack();


    public ArrayList<Propjogadas> solve(int[][] tabule) {

        int tab[][] = tabule;
        int numDP;

        Propjogadas c = new Propjogadas();
        Propjogadas d = new Propjogadas();
        
             numDP=numbPieces(tab);

        joga(tab);

        while (stack.pilhaVazia() == false && stack.tamanho() < numDP - 1) {


            c = stack.desempilhar();
            resmov(tab, c.getxd(), c.getyd(), c.getmuv());

            if (c.getmuv() < 4) {

                int h = analisemov(tab, c.getxd(), c.getyd(), c.getmuv());

                if (h < 5) {

                    c.Type_Muv = h;
                    mov(tab, c);
                    joga(tab);

                } else {

                    d = findValidZero(tab, c.getxd(), c.getyd() + 1);

                    if (d.getmuv() < 5) {
                        mov(tab, d);
                        joga(tab);
                    }
                }
            }
        }

        return stack.returnList();
    }

    private void joga(int[][] tabuleiro) {

        int saida;

        Propjogadas b = new Propjogadas();

        do {

            saida = 1;
            b = findValidZero(tabuleiro, 0, 0);
            if (b.Type_Muv < 5) {
                mov(tabuleiro, b);
                saida = 0;
            }

        } while (saida == 0);

    }
    
    public int numbPieces(int[][]tab){
   int k, p, NB = 0;

        for (k = 0; k < tab.length; k++) { // num de peças
            for (p = 0; p < tab.length; p++) {
                if (tab[k][p] == 1) {
                    NB++;
                }
            }
        }
        return NB;
    }

    private Propjogadas findValidZero(int[][] tabuleiro, int x, int y) {
        Propjogadas a = new Propjogadas();

        a.Type_Muv = 5;
        int resp;
        int i, j;
        for (i = x; i < tabuleiro.length; i++) {
            for (j = y; j < tabuleiro.length; j++) {
                if (tabuleiro[i][j] == 0) {
                    resp = analisemov(tabuleiro, i, j, 0);
                    if (resp < 5) {

                        a.X_Dest = i;
                        a.Y_Dest = j;
                        a.Type_Muv = resp;
                        return a;
                    }
                }
            }
            y = 0;
        }

        return a;

    }

    public int analisemov(int[][] tabuleiro, int X, int Y, int resAnt) {

        if (resAnt < 1) {
            if (X > 1) {
                if (tabuleiro[X - 2][Y] == 1 && tabuleiro[X - 1][Y] == 1)// cima
                {
                    return 1;
                }
            }
        }

        if (resAnt < 2) {
            if (X + 2 < ((tabuleiro.length))) {
                if (tabuleiro[X + 2][Y] == 1 && tabuleiro[X + 1][Y] == 1) // baixo
                {
                    return 2;
                }
            }
        }
        if (resAnt < 3) {
            if (Y + 2 < ((tabuleiro.length))) {
                if (tabuleiro[X][Y + 2] == 1 && tabuleiro[X][Y + 1] == 1) // direita
                {
                    return 3;
                }
            }
        }
        if (resAnt < 4) {
            if (Y > 1) {
                if ((tabuleiro[X][Y - 2] == 1 && tabuleiro[X][Y - 1] == 1))//esquerda
                {
                    return 4;
                }
            }
        }

        return 5;

    }

    public void mov(int[][] tabuleiro, Propjogadas jogada) {
        int X = jogada.getxd();
        int Y = jogada.getyd();
        switch (jogada.Type_Muv) {

            case 1:
                tabuleiro[X][Y] = 1;
                tabuleiro[X - 1][Y] = 0;
                tabuleiro[X - 2][Y] = 0;
                empilha(X - 2, X, Y, Y, jogada.Type_Muv);
                break;

            case 2:
                tabuleiro[X][Y] = 1;
                tabuleiro[X + 1][Y] = 0;
                tabuleiro[X + 2][Y] = 0;
                empilha(X + 2, X, Y, Y, jogada.Type_Muv);
                break;

            case 3:
                tabuleiro[X][Y] = 1;
                tabuleiro[X][Y + 1] = 0;
                tabuleiro[X][Y + 2] = 0;
                empilha(X, X, Y + 2, Y, jogada.Type_Muv);
                break;

            case 4:
                tabuleiro[X][Y] = 1;
                tabuleiro[X][Y - 1] = 0;
                tabuleiro[X][Y - 2] = 0;
                empilha(X, X, Y - 2, Y, jogada.Type_Muv);
                break;

            default:
                System.out.println("Erro de movimentação");
        }
    }

    private void resmov(int[][] tabuleiro, int X, int Y, int destino) {
        switch (destino) {

            case 1:
                tabuleiro[X][Y] = 0;
                tabuleiro[X - 1][Y] = 1;
                tabuleiro[X - 2][Y] = 1;
                break;

            case 2:
                tabuleiro[X][Y] = 0;
                tabuleiro[X + 1][Y] = 1;
                tabuleiro[X + 2][Y] = 1;
                break;

            case 3:
                tabuleiro[X][Y] = 0;
                tabuleiro[X][Y + 1] = 1;
                tabuleiro[X][Y + 2] = 1;
                break;

            case 4:
                tabuleiro[X][Y] = 0;
                tabuleiro[X][Y - 1] = 1;
                tabuleiro[X][Y - 2] = 1;
                break;

            default:
                System.out.println("Erro de movimentação");
        }
    }

    private void empilha(int X1, int X2, int Y1, int Y2, int tMov) {
        Propjogadas p = new Propjogadas();
        p.X_Orig = X1;
        p.Y_Orig = Y1;
        p.X_Dest = X2;
        p.Y_Dest = Y2;
        p.Type_Muv = tMov;
        stack.empilhar(p);
    }
}
