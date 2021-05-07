import java.util.ArrayList;


import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Solveur {
    private ArrayList<Noeud> liste_noeuds_ouverts;
    private ArrayList<Noeud> liste_noeuds_fermes;

    public static void main(String[] args) throws IOException{
        Solveur s = new Solveur();
        Grille grille1 = chargerFichier("C:/Users/PC/Desktop/IA/tp2/algorithme-A-star/src/puzzles/puzzle04.txt"); 
        //Grille grille2 = chargerFichier("C:/Users/PC/Desktop/IA/tp2/algorithme-A-star/src/puzzles/puzzle08.txt"); 
        Grille grille3 = chargerFichier("C:/Users/PC/Desktop/IA/tp2/algorithme-A-star/src/puzzles/puzzle07.txt"); 
        

        s.addNoeudListeOuverts(new Noeud(grille1,null,0));
        //s.addNoeudListeOuverts(new Noeud(grille2,null,0));
        s.addNoeudListeOuverts(new Noeud(grille3,null,0));
        Noeud noeud_courant = s.chercheNoeudCourant();
        System.out.println(noeud_courant.getGrille());
        



    }
    public Solveur(){
        this.liste_noeuds_fermes = new ArrayList<Noeud>();
        this.liste_noeuds_ouverts = new ArrayList<Noeud>();
    }

    /*public Noeud algoStar(Grille initial){
        ArrayList<Noeud> liste_successeurs;
        Noeud noeud_racine = new Noeud(initial,null,0);
        Noeud noeudCourant;
        Solveur s = new Solveur();
        this.liste_noeuds_ouverts.add(noeud_racine);

        do{
            noeudCourant = s.chercheNoeudCourant();
            this.liste_noeuds_fermes.add(noeudCourant);
            this.liste_noeuds_ouverts.remove(noeudCourant);
            liste_successeurs = noeudCourant.successeurs();


            s.triSuccesseurs(liste_successeurs,s);
            s.successeurDansListeOuverte(liste_successeurs,s);


        }while(!noeudCourant.estUnEtatFinal() || this.liste_noeuds_ouverts.size()!=0);

        return noeudCourant;


    }*/
    public static int puissanceDix(int indice){
        int sortie=1;
        for(int i=0;i<indice;i++){
            sortie = sortie * 10;
        }
        return sortie;
    }

    public ArrayList<Noeud> getListeFermes(){
        return this.liste_noeuds_fermes;
    }
    public ArrayList<Noeud> getListeOuverts(){
        return this.liste_noeuds_ouverts;
    }
    public Noeud getNoeudListeOuverts(int indice){
        return this.liste_noeuds_ouverts.get(indice);
    }
    public Noeud getNoeudListeFermes(int indice){
        return this.liste_noeuds_fermes.get(indice);
    }
    public void removeNoeudListeFermes(int indice){
        this.liste_noeuds_fermes.remove(indice);
    }
    public void removeNoeudListeOuverts(int indice){
        this.liste_noeuds_ouverts.remove(indice);
    }
    public void addNoeudListeOuverts(Noeud n){
        this.liste_noeuds_ouverts.add(n);   
    }
    public void addNoeudListeFermes(Noeud n){
        this.liste_noeuds_fermes.add(n);   
    }



    public void test(ArrayList<Integer> liste){
        liste.remove(0);
    }

    /*public void triSuccesseurs(ArrayList<Noeud> liste_successeurs_à_trier,Solveur s){
        Grille grille_temp_successeur;
        Grille grille_temp_liste_noeuds_fermes;
        int i,j,decrement=0,indice;
        ArrayList<Noeud> liste_fermes = s.getListeFermes();

        for(i=0;i<liste_successeurs_à_trier.size();i++){
            indice= i-decrement;
            grille_temp_successeur = liste_successeurs_à_trier.get(indice).getGrille();
            for(j=0;j<liste_fermes.size();j++){
                grille_temp_liste_noeuds_fermes = liste_fermes.get(j).getGrille();
                if(grille_temp_successeur.equals(grille_temp_liste_noeuds_fermes)){
                    liste_successeurs_à_trier.remove(indice);
                    decrement++;
                }
            }
        }

    }*/
    /*public void successeurDansListeOuverte(ArrayList<Noeud> liste_successeurs_triee, Solveur s){
        Grille grille_temp_successeur;
        Noeud noeud_temp_successeur;
        Grille grille_temp_liste_noeuds_ouverts;
        Noeud noeud_temp_liste_ouverts;
        Noeud noeud_liste_ouvert_à_tester;
        boolean is_dans_liste;
        int i,j,indice_liste_ouverts=0;
        ArrayList<Noeud> liste_ouverts = s.getListeOuverts();

        for(i=0;i<liste_successeurs_triee.size();i++){
            noeud_temp_successeur=liste_successeurs_triee.get(i);
            grille_temp_successeur = noeud_temp_successeur.getGrille();
            is_dans_liste = false;

            for(j=0;j<liste_ouverts.size();j++){
                noeud_temp_liste_ouverts = liste_ouverts.get(j);
                grille_temp_liste_noeuds_ouverts = noeud_temp_liste_ouverts.getGrille();
                if(grille_temp_successeur.equals(grille_temp_liste_noeuds_ouverts)){
                    indice_liste_ouverts = j;
                    is_dans_liste = true;
                }
            }
            if(is_dans_liste){
                noeud_liste_ouvert_à_tester =liste_ouverts.get(indice_liste_ouverts);
                if(noeud_temp_successeur.f() < noeud_liste_ouvert_à_tester.f()){
                    liste_ouverts.remove(noeud_liste_ouvert_à_tester);
                    liste_ouverts.add(noeud_temp_successeur);
                }
            }else{
                liste_ouverts.add(noeud_temp_successeur);
            }
        }
    }*/

    public Noeud chercheNoeudCourant(){
        int min, i, val_f_temp, indice_min=0;
        min = this.liste_noeuds_ouverts.get(0).f();

        for(i=1;i<this.liste_noeuds_ouverts.size();i++){
            val_f_temp = this.liste_noeuds_ouverts.get(i).f();
            if(val_f_temp < min){
                min = val_f_temp;
                indice_min = i;
            }
        }
        return this.liste_noeuds_ouverts.get(indice_min);
    }

    public static Grille chargerFichier(String nomFichier) throws IOException{
        BufferedReader in = null;
        try{
	        in = new BufferedReader(new FileReader(nomFichier));
        }catch(FileNotFoundException exc)
        {
	        System.out.println("Erreur d'ouverture");
        }
		String line;
        int grille_tableau[][];
        int taille;
        int l=0,c=0,j,i;
        int res_int =0;
        String res_string="";

        line = in.readLine();
        taille =(int)line.charAt(0)-48;
        grille_tableau = new int[taille][taille];

		while ((line = in.readLine()) != null)
		{
            c=0;
            for(j=0;j<line.length();j++){
                if(line.charAt(j)!=' '){
                    res_string = res_string+line.charAt(j);
                }
                if((line.charAt(j)==' ' || j==line.length()-1) && res_string!=""){
                    for(i=0;i<res_string.length();i++){
                        res_int = res_int + ((int)res_string.charAt(i)-48)*puissanceDix(res_string.length()-1-i);
                    }
                    grille_tableau[l][c] = res_int;
                    c++;
                    res_int=0;
                    res_string="";
                } 
            }
            l++;
		}
		in.close();
        return new Grille(grille_tableau);
    
    }
    public String toString(){
        String sortie="";
        Noeud noeud_temp;
        int i;
        sortie=sortie+"Liste ouverte\n";
        for(i=0;i<this.liste_noeuds_ouverts.size();i++){
            noeud_temp = this.liste_noeuds_ouverts.get(i);
            sortie = sortie +noeud_temp.getGrille().toString()+"\n";
        }
        sortie=sortie+"Liste fermée\n";
        for(i=0;i<this.liste_noeuds_fermes.size();i++){
            noeud_temp = this.liste_noeuds_fermes.get(i);
            sortie = sortie +noeud_temp.getGrille().toString()+"\n";
        }
        return sortie;
    }
    
}
