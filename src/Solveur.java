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
        Grille grille = s.chargerFichier("C:\\Users\\PC\\Desktop\\IA\\tp2\\algorithme-A-star\\src\\puzzles\\puzzle04.txt");
        System.out.println(grille);


    }

    public Grille chargerFichier(String nomFichier) throws IOException{
        BufferedReader in = null;
        try{
	        in = new BufferedReader(new FileReader(nomFichier));
        }catch(FileNotFoundException exc)
        {
	        System.out.println("Erreur d'ouverture");
        }
		String line;
        int taille;
        int compteur=0;
        int grille_tableau[][];

        line = in.readLine();
        taille =(int)line.charAt(0)-48;
        grille_tableau = new int[taille][taille];

		while ((line = in.readLine()) != null)
		{
            grille_tableau[compteur][0]=(int)line.charAt(0)-48;
            grille_tableau[compteur][1]=(int)line.charAt(2)-48;
            grille_tableau[compteur][2]=(int)line.charAt(4)-48;
            compteur++;
		}
		in.close();
        return new Grille(grille_tableau);
        
    }
    
}
