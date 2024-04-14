// Classe Bibliothèque : 
//  Attributs : 
// o ArrayList<Livre> listeLivres 
// o HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs 
//  Méthodes : 
// o Méthode pour ajouter un livre à la bibliothèque. 
// o Méthode pour supprimer un livre de la bibliothèque. 
// o Méthode pour rechercher un livre par titre, auteur ou ISBN. 
// o Méthode pour enregistrer l'emprunt d'un livre par un utilisateur. 
// o Méthode pour enregistrer le retour d'un livre par un utilisateur. 
// o Méthode pour vérifier l'éligibilité d'un utilisateur à emprunter un livre. 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

public class Bibliotheque {

    private ArrayList<Livre> listeLivres;
    private HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;
    //HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs = new HashMap<>();

    private String fichierDonnees;

    public Bibliotheque(String fichierDonnees) {
        listeLivres = new ArrayList<>();
        empruntsUtilisateurs = new HashMap<>();
        this.fichierDonnees = fichierDonnees;
       // chargerLivres();
    }



    public void ajoutLivre(Livre livre) {
        if (!listeLivres.contains(livre)) {
            listeLivres.add(livre);
            System.out.println(listeLivres);
            sauvegarderLivres();
            System.out.println("Le livre \"" + livre.getTitre() + "\" a été ajouté");
        } else {
            System.out.println("Le livre \"" + livre.getTitre() + "\" est déjà présent");
        }
    }



    
    private void sauvegarderLivres() {
        try {
            FileWriter fw = new FileWriter(fichierDonnees, true); // Mode ajout
            BufferedWriter bw = new BufferedWriter(fw);
    
            for (Livre livre : listeLivres) {
                String ligne = livre.getTitre() + ";" + livre.getAuteur() + ";"  + livre.getAnneePublication() + ";"+ livre.getISBN();
                bw.write(ligne);
                bw.newLine();
            }
    
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des livres : " + e.getMessage());
        }
    }


    


    public void afficherLivresDansFichier(String nomFichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            System.out.println("Livres actuellement disponibles : ");
            while ((ligne = br.readLine()) != null) {
                String[] elements = ligne.split(";");
                String titre = elements[0];
                String auteur = elements[1];
                String anneePublication = elements[2];
                String ISBN = elements[3];
                System.out.println("Titre : " + titre + ", Auteur : " + auteur + ", Année de publication : " + anneePublication + ", ISBN : " + ISBN);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }
    

  

   

    public void supprimerLivre(Livre livre) {
        
        String titreLivre = livre.getTitre();
        // Indicateur pour savoir si le livre a été trouvé et supprimé
        boolean livreTrouve = false;

        // Création d'une nouvelle liste pour stocker les livres sans le livre à supprimer
        List<Livre> nouvelleListe = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("fichier.txt"))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // Séparer la ligne en utilisant le délimiteur approprié (par exemple, ";")
                String[] elements = ligne.split(";");

                // Si le titre du livre actuel n'est pas celui que nous voulons supprimer, l'ajouter à la nouvelle liste
                if (!elements[0].equals(titreLivre)) {
                    nouvelleListe.add(new Livre(elements[0], elements[1], elements[2], elements[3]));
                } else {
                    // Le livre a été trouvé et supprimé
                    livreTrouve = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }

        // Écrire la nouvelle liste dans le fichier
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("fichier.txt"))) {
            for (Livre livre1 : nouvelleListe) {
                String ligne = livre1.getTitre() + ";" + livre1.getAuteur() + ";" + livre1.getAnneePublication() + ";" + livre1.getISBN();
                bw.write(ligne);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }

        // Afficher un message approprié en fonction de si le livre a été trouvé et supprimé
        if (livreTrouve) {
            System.out.println("Le livre \"" + titreLivre + "\" a été supprimé");
        } else {
            System.out.println("Le livre \"" + titreLivre + "\" n'est pas présent");
        }
    }


    public Livre rechercherLivre(String critere, String fichierDonnees) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichierDonnees))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] champs = ligne.split(";");
                String titre = champs[0];
                String auteur = champs[1];
                String isbn = champs[3];
                if (titre.equals(critere) || auteur.equals(critere) || isbn.equals(critere)) {
                    // Créez un objet Livre avec les informations trouvées dans la ligne
                    // (assurez-vous que l'ordre des champs correspond à celui dans votre fichier)
                    return new Livre(titre, auteur, champs[2], isbn);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la recherche du livre : " + e.getMessage());
        }
        return null;
    }

   
    public void enregistrerEmprunt(Utilisateur utilisateur, Livre livre) {
        if (verifierEligibiliteEmprunt(utilisateur)) {
            if (!empruntsUtilisateurs.containsKey(utilisateur)) {
                empruntsUtilisateurs.put(utilisateur, new ArrayList<>());
            }
            ArrayList<Livre> livresEmpruntes = empruntsUtilisateurs.get(utilisateur);
            if (livresEmpruntes.size() < 2) {
                livresEmpruntes.add(livre);
                sauvegarderEmpruntsUtilisateurs();
                System.out.println("L'utilisateur " + utilisateur.getNom() + " a emprunté le livre \"" + livre.getTitre() + "\"");
            } else {
                System.out.println("L'utilisateur " + utilisateur.getNom() + " a déjà emprunté deux livres.");
            }
        } else {
            System.out.println("L'utilisateur " + utilisateur.getNom() + " n'est pas éligible à emprunter un livre pour le moment.");
        }
    }

    

    


    // Méthode pour vérifier l'éligibilité d'un utilisateur à emprunter un livre
    private boolean verifierEligibiliteEmprunt(Utilisateur utilisateur) {
        String nom = utilisateur.getNom();
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("livre.txt"))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts[0].equals(nom)) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count < 2;
    }

    public void enregistrerRetour(Utilisateur utilisateur, Livre livre) {
        // Vérifier si l'utilisateur a emprunté des livres
        String nomUtilisateur = utilisateur.getNom();
        try (BufferedReader reader = new BufferedReader(new FileReader("livre.txt"))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts[0].equals(nomUtilisateur)) {
                    ArrayList<Livre> livresEmpruntes = empruntsUtilisateurs.get(utilisateur);
                   // livresEmpruntes.remove(livre);
                    sauvegarderEmpruntsUtilisateurs();
                    System.out.println("Le livre \"" + livre.getTitre() + "\" a été retourné par l'utilisateur " + utilisateur.getNom());

                }
                else {
                    System.out.println("Le livre \"" + livre.getTitre() + "\" n'a pas été emprunté par l'utilisateur " + utilisateur.getNom());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      
    }
    
    
    
    
    

    // Méthode pour sauvegarder les emprunts d'utilisateurs dans le fichier
    private void sauvegarderEmpruntsUtilisateurs() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("livre.txt"))) {
            for (Map.Entry<Utilisateur, ArrayList<Livre>> entry : empruntsUtilisateurs.entrySet()) {
                Utilisateur utilisateur = entry.getKey();
                ArrayList<Livre> livresEmpruntes = entry.getValue();
                for (Livre livre : livresEmpruntes) {
                    writer.println(utilisateur.getNom() + ";" + livre.getTitre());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    

    public void afficherStatistiques() {
        int totalLivres = listeLivres.size();
        int totalEmprunts = 0;
        int totalLignes = 0;
    
        // Compter le nombre d'emprunts
        for (ArrayList<Livre> emprunts : empruntsUtilisateurs.values()) {
            totalEmprunts += emprunts.size();
        }
    
        // Compter le nombre de lignes dans le fichier
        try (BufferedReader reader = new BufferedReader(new FileReader("livre.txt"))) {
            while (reader.readLine() != null) {
                totalLignes++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        
        System.out.println("Nombre d'exemplaires empruntés : " + totalEmprunts);
        System.out.println("Nombre total de livres : " + totalLignes);
    }

}