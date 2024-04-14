// Classe Utilisateur : 
//  Attributs : 
// o String nom 
// o int numeroIdentification 
// o ArrayList<Livre> livresEmpruntes 
//  Méthodes : 
// o Constructeur pour initialiser les attributs. 
// o Méthode pour emprunter un livre. 
// o Méthode pour retourner un livre. 
// o Méthode pour afficher les livres empruntés par l'utilisateur.
/**
 * Utilisateur
 */
import java.util.ArrayList;
import java.util.Objects;

public class Utilisateur {
    // Attributs
    private String nom;
    private String numeroIdentification;
    private ArrayList<Livre> livresEmprunt;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Utilisateur other = (Utilisateur) obj;
        return Objects.equals(this.nom, other.nom);
    }
    


    // Methodes
public Utilisateur(String nom,String numeroIdentification) 
    {
        this.nom=nom;
        this.numeroIdentification=numeroIdentification;
        this.livresEmprunt=new ArrayList<Livre>();
    }

   
public ArrayList<Livre> getLivresEmprunt() {
    return livresEmprunt;
}
public String getNom() {
    return nom;
}
public String getNumeroIdentification() {
    return numeroIdentification;
}
//emprunter un livre  
public void empruntlivre(Livre livre, Utilisateur utilisateur) {
    // Vérifier si l'utilisateur est éligible à emprunter un livre
    if (verifierEligibiliteEmprunt(utilisateur)) {
        // Vérifier si le livre est déjà emprunté
        if (!livresEmprunt.contains(livre)) {
            livresEmprunt.add(livre);
            System.out.println("Le livre \"" + livre.getTitre() + "\" a été emprunté par l'utilisateur");
        } else {
            System.out.println("Le livre \"" + livre.getTitre() + "\" est déjà emprunté par un autre utilisateur");
        }
    } else {
        System.out.println("L'utilisateur " + utilisateur.getNom() + " n'est pas éligible à emprunter un livre pour le moment.");
    }
}

public boolean verifierEligibiliteEmprunt(Utilisateur utilisateur) {
    // Récupérer la liste des livres empruntés par l'utilisateur
   
    // Vérifier si l'utilisateur a déjà emprunté un livre
    if (!livresEmprunt.isEmpty()) {
        System.out.println("L'utilisateur " + utilisateur.getNom() + " a déjà emprunté un livre.");
        return false; // L'utilisateur n'est pas éligible à emprunter un autre livre
    }
    
    // L'utilisateur est éligible à emprunter un livre car il n'a pas encore emprunté de livre
    System.out.println("L'utilisateur " + utilisateur.getNom() + " est éligible à emprunter un livre.");
    return true;
}



public void retournerlivre(String ISBNLivre) {
    boolean livreTrouve = false;
    for (Livre livre : livresEmprunt) {
        if (livre.getISBN().equals(ISBNLivre)) {
            livresEmprunt.remove(livre);
            System.out.println("Le livre avec le titre " + livre.getTitre() + " a été retourné par l'utilisateur");
            livreTrouve = true;
            break;
        }
    }
    if (!livreTrouve) {
        System.out.println("Le livre avec l'ISBN " + ISBNLivre + " n'a pas été emprunté par l'utilisateur");
    }
}


    public void affichageEmprunt()
    {
        // System.out.println("Livres empruntés par l'utilisateur " + nom + ":");
        // for (Livre livre : livresEmprunt) {
        //     livre.toString();
        //     if (livresEmprunt.size()==0) {
        //         System.out.println("aucun emprunt");
        //     }
        // }
        if (livresEmprunt.size()==0){
            System.out.println("aucun emprunt");
        }
        else{
            // System.out.println("Livres empruntés par l'utilisateur " + nom + ":");
            // for (Livre livre : livresEmprunt) {
            //          livre.toString();
            System.out.println("Livres empruntés par l'utilisateur " + nom + ":");
            for (Livre livre : livresEmprunt) {
                System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
            }
            
        }
    }   

}
