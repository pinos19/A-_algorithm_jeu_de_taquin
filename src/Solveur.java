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
        Noeud but;
        ArrayList<Grille> grille_liste_tests = new ArrayList<Grille>();

        Grille grille1 = chargerFichier("D:/Bureau/anneeScolaire2020-2021/IA/tp2/algorithme-A-star/src/puzzles/puzzle2x2-unsolvable2.txt"); 
        Grille grille2 = chargerFichier("D:/Bureau/anneeScolaire2020-2021/IA/tp2/algorithme-A-star/src/puzzles/puzzle50.txt"); 
        Grille grille3 = chargerFichier("D:/Bureau/anneeScolaire2020-2021/IA/tp2/algorithme-A-star/src/puzzles/puzzle45.txt"); 
        Grille grille4 = chargerFichier("D:/Bureau/anneeScolaire2020-2021/IA/tp2/algorithme-A-star/src/puzzles/puzzle07.txt");
        Grille grille5 = chargerFichier("D:/Bureau/anneeScolaire2020-2021/IA/tp2/algorithme-A-star/src/puzzles/puzzle00.txt"); 
        

        grille_liste_tests.add(grille1);
        grille_liste_tests.add(grille2);
        grille_liste_tests.add(grille3);
        grille_liste_tests.add(grille4);
        grille_liste_tests.add(grille5);
        

        for(int i=0;i<grille_liste_tests.size();i++){
            System.out.println(grille_liste_tests.get(i));
            but = s.algoStar(grille_liste_tests.get(i));
            if(but==null){
                System.out.println("Il n'y a pas de solution");
            }else{
                System.out.println(but.getGrille());
            }
        }
    }
    public Solveur(){
        this.liste_noeuds_fermes = new ArrayList<Noeud>();
        this.liste_noeuds_ouverts = new ArrayList<Noeud>();
    }

    public Noeud algoStar(Grille initial){
        ArrayList<Noeud> liste_successeurs;
        Noeud noeud_racine = new Noeud(initial,null,0);
        Noeud noeudCourant;

        this.addNoeudListeOuverts(noeud_racine);
        do{
            noeudCourant = this.chercheNoeudCourant();
            this.addNoeudListeFermes(noeudCourant);
            this.removeNoeudListeOuverts(noeudCourant);
            liste_successeurs = noeudCourant.successeurs();
            triSuccesseurs(liste_successeurs,this.getListeFermes());
            this.successeurDansListeOuverte(liste_successeurs);
            if(this.getSizeOuverts()==0){
                noeudCourant=null;
            }

        }while(this.getSizeOuverts()!=0 && !noeudCourant.estUnEtatFinal());

        return noeudCourant;


    }
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
    public void removeNoeudListeFermes(Noeud n){
        this.liste_noeuds_fermes.remove(n);
    }
    public void removeNoeudListeOuverts(Noeud n){
        this.liste_noeuds_ouverts.remove(n);
    }

    public void addNoeudListeOuverts(Noeud n){
        this.liste_noeuds_ouverts.add(n);   
    }
    public void addNoeudListeOuverts(int indice,Noeud n){
        this.liste_noeuds_ouverts.add(indice,n);   
    }
    public void addNoeudListeFermes(Noeud n){
        this.liste_noeuds_fermes.add(n);   
    }
    public int getSizeOuverts(){
        int sortie;
        if(this.liste_noeuds_ouverts ==null){
            sortie = 0;
        }else{
            sortie = this.liste_noeuds_ouverts.size();
        }
        return sortie;
    }
    public int getSizeFermes(){
        int sortie;
        if(this.liste_noeuds_fermes ==null){
            sortie = 0;
        }else{
            sortie = this.liste_noeuds_fermes.size();
        }
        return sortie;
    }


    public static void triSuccesseurs(ArrayList<Noeud> liste_à_trier,ArrayList<Noeud> liste_fermes){
        Grille grille_successeur;
        Grille grille_fermes;
        int i,j,decrement=0,indice;
        int taille=liste_à_trier.size();
        for(i=0;i<taille;i++){
            indice= i-decrement;
            grille_successeur = liste_à_trier.get(indice).getGrille();
            for(j=0;j<liste_fermes.size();j++){
                grille_fermes = liste_fermes.get(j).getGrille();
                if(grille_successeur.equals(grille_fermes)){
                    liste_à_trier.remove(indice);
                    decrement++;
                }
            }
        }

    }
    public void successeurDansListeOuverte(ArrayList<Noeud> liste_successeurs_triee){
        Grille grille_temp_successeur;
        Noeud noeud_temp_successeur;
        Grille grille_temp_liste_noeuds_ouverts;
        Noeud noeud_temp_liste_ouverts;
        Noeud noeud_liste_ouvert_à_tester;
        boolean is_dans_liste;
        int i,j,indice_liste_ouverts=0;

        for(i=0;i<liste_successeurs_triee.size();i++){
            noeud_temp_successeur=liste_successeurs_triee.get(i);
            grille_temp_successeur = noeud_temp_successeur.getGrille();
            is_dans_liste = false;

            for(j=0;j<this.getSizeOuverts();j++){
                noeud_temp_liste_ouverts = this.getNoeudListeOuverts(j);
                grille_temp_liste_noeuds_ouverts = noeud_temp_liste_ouverts.getGrille();

                if(grille_temp_successeur.equals(grille_temp_liste_noeuds_ouverts)){
                    indice_liste_ouverts = j;
                    is_dans_liste = true;
                }
            }
            if(is_dans_liste){
                noeud_liste_ouvert_à_tester =this.getNoeudListeOuverts(indice_liste_ouverts);
                if(noeud_temp_successeur.f() < noeud_liste_ouvert_à_tester.f()){
                    this.removeNoeudListeOuverts(indice_liste_ouverts);
                    this.addNoeudListeOuverts(indice_liste_ouverts,noeud_temp_successeur);
                }
                is_dans_liste=false;
            }else{
                this.addNoeudListeOuverts(noeud_temp_successeur);
            }
        }
    }

    public Noeud chercheNoeudCourant(){
        int min, i, val_f_temp, indice_min=0;
        Noeud noeud_sortie;
        if(this.getSizeOuverts()!=0){
            min = this.liste_noeuds_ouverts.get(0).f();
            for(i=1;i<this.liste_noeuds_ouverts.size();i++){
                val_f_temp = this.liste_noeuds_ouverts.get(i).f();
                if(val_f_temp < min){
                    min = val_f_temp;
                    indice_min = i;
                }
            }
            noeud_sortie = this.getNoeudListeOuverts(indice_min);
        }else{
            noeud_sortie=null;
        }
        return noeud_sortie;
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
        int l=0,c=0,j,i;
        int res_int =0;
        String res_string="";

        line = in.readLine();
        Integer taille=Integer.valueOf(line);  
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
