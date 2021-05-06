import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        int [][] grille = new int[3][3];
        grille[0][0]=7;
        grille[0][1]=2;
        grille[0][2]=4;

        grille[1][0]=5;
        grille[1][1]=0;
        grille[1][2]=6;

        grille[2][0]=8;
        grille[2][1]=3;
        grille[2][2]=1;

        /*for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(grille[i][j]+" ");
            }
            System.out.println("");
        }*/

        Grille g1 = new Grille(grille);
        Noeud noeud = new Noeud(g1,null,0);
        System.out.println(noeud.getGrille().toString());
        ArrayList<Noeud> liste_successeurs = noeud.successeurs();
        for(int i=0;i<liste_successeurs.size();i++){
            System.out.println(liste_successeurs.get(i).getGrille().toString());
        }

       


    }
}
