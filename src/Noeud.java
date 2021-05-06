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
                if(this.grille.getValeur(i,j)!=valeur_exacte){
                    sortie++;
                }
                valeur_exacte++;
            }
        }
        return sortie;

    }


}
