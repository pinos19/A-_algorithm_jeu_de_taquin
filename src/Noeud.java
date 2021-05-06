
import java.util.ArrayList; 
 
public class Noeud {
    private Grille grille;
    private Noeud pere;
    private int g;

    public Noeud(Grille grille, Noeud p, int g){
        this.grille = grille;
        this.pere = p;
        this.g = g;
    }

    public Grille getGrille(){
        return this.grille;
    }
    public Noeud getPere(){
        return this.pere;
    }
    public int h(){
        int taille = this.grille.getTaille();
        int valeur_exacte=1;
        int sortie=0;
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                if(i==(taille-1) && j==(taille-1)){
                    valeur_exacte=0;
                }
                if(this.grille.getValeur(i,j)!=0){
                    if(this.grille.getValeur(i,j)!=valeur_exacte){
                    sortie++;
                    }
                }
                valeur_exacte++;
            }
        }
        return sortie;

    }
    public int g(){
        return this.g;
    }
    public int f(){
        return this.h()+this.g();
    }
    public boolean estUnEtatFinal(){
        int taille = this.grille.getTaille();
        int valeur_exacte=1;
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                if(i==(taille-1) && j==(taille-1)){
                    valeur_exacte=0;
                }
                if(this.grille.getValeur(i,j)!=0){
                    if(this.grille.getValeur(i,j)!=valeur_exacte){
                        return false;
                    }
                }
                valeur_exacte++;
            }
        }
        return true;
    }
    public ArrayList<Noeud> successeurs(){
        ArrayList<Noeud> liste_successeurs = new ArrayList<Noeud>();
        int ligne0 = this.grille.getLigne0();
        int taille = this.grille.getTaille();
        int colonne0 = this.grille.getColonne0();

        int ligne_temp;
        int colonne_temp;
        int val_temp;
        Grille grille_objet_temp;
        int grille_temp[][];

        int tableau_temporaire[][] =   {{ligne0-1,colonne0},
                                        {ligne0+1,colonne0},
                                        {ligne0,colonne0-1},
                                        {ligne0,colonne0+1}};
        for(int i=0;i<4;i++){
            ligne_temp = tableau_temporaire[i][0];
            colonne_temp = tableau_temporaire[i][1];

            if((ligne_temp >= 0 && ligne_temp <= taille-1) && (colonne_temp >=0 && colonne_temp <= taille-1)){
                grille_temp = this.grille.copier();
                val_temp=grille_temp[ligne_temp][colonne_temp];
                grille_temp[ligne_temp][colonne_temp] = 0;
                grille_temp[ligne0][colonne0] = val_temp;
                grille_objet_temp = new Grille(grille_temp);
                liste_successeurs.add(new Noeud(grille_objet_temp,this,this.g()+1));

            }

        }
        return liste_successeurs;
        
        

    }







}
