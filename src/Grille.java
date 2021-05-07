public class Grille {
    private int[][] grille;
    private int taille;
    private int ligne0;
    private int colonne0;
    
    public Grille(int g[][]){
        int taille_temp=g.length;
        this.grille = new int[taille_temp][taille_temp];
        for(int i=0;i<taille_temp;i++){
            for(int j=0;j<taille_temp;j++){
                this.grille[i][j] = g[i][j];
                if(g[i][j]==0){
                    this.ligne0=i;
                    this.colonne0=j;
                }
            }
        }
        this.taille = taille_temp;

    }
    // Les accesseurs

    public int[][] getGrille(){
        return this.grille;
    }
    public int getTaille(){
        return this.taille;
    }
    public int getLigne0(){
        return this.ligne0;
    }
    public int getColonne0(){
        return this.colonne0;
    }
    public int getValeur(int i,int j){
        return this.grille[i][j];
    }
    public int[][] copier(){
        int[][] copie_grille = new int[this.taille][this.taille];
        for(int i=0;i<this.taille;i++){
            for(int j=0;j<this.taille;j++){
                copie_grille[i][j]=this.grille[i][j];
            }
        }
        return copie_grille;
    }

    public boolean equals(Object obj){
        Grille g = (Grille) obj;
        if(this.taille != g.taille){
            return false;
        }
        for(int i=0;i<this.taille;i++){
            for(int j=0;j<this.taille;j++){
                if(this.grille[i][j] != g.getValeur(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    public String toString(){
        String result = "";
        for(int i=0;i<this.taille;i++){
            for(int j=0;j<this.taille;j++){
                result = result + String.valueOf(this.grille[i][j])+" ";
            }
            result = result + "\n";
        }
        return result;

    }







}
