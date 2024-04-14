import java.util.Scanner;
public class Main { 
 public static void main(String[] args) {


   

       
        Scanner scanner = new Scanner(System.in);
        int choix = 0;

    while (choix != 3) {
            System.out.println("Choisissez votre rôle :");
            System.out.println("1. Utilisateur");
            System.out.println("2. Bibliothécaire");
            System.out.println("3. Quitter");

            System.out.print("Votre choix : ");
            choix = scanner.nextInt();

            switch (choix) {

                //interface user
                   case 1:

                   
                        System.out.print("Entrez le nom de l'utilisateur : ");
                        String nom = scanner.nextLine();
                        nom = scanner.nextLine();
                        System.out.print("Entrez le numéro d'identification de l'utilisateur : ");
                        String numeroIdentification = scanner.nextLine();
                       
                    
                        Utilisateur utilisateur = new Utilisateur(nom, numeroIdentification);
                        Bibliotheque bib=new Bibliotheque("fichier.txt");
                        int choix2 = 0;

                        while (choix2 != 4) {
                            System.out.println("Que souhaitez-vous faire ?");
                            System.out.println("1. Emprunter un livre");
                            System.out.println("2. Retourner un livre");
                            System.out.println("3. Afficher les livres empruntés");
                            System.out.println("4. Quitter");

                            System.out.print("Votre choix : ");
                            choix2 = scanner.nextInt();

                            switch (choix2) {
                                case 1:
                                    scanner.nextLine(); // Vider la ligne précédente
                                    // Afficher tous les livres avant d'emprunter
                                    bib.afficherLivresDansFichier("fichier.txt");
                                    System.out.print("Entrez l'ISBN du livre à emprunter : ");
                                    String ISBNLivreEmprunt = scanner.nextLine();                 
                                    Livre livre = bib.rechercherLivre(ISBNLivreEmprunt, "fichier.txt");
                                    if (livre!=null) {
                                         utilisateur.empruntlivre(livre,utilisateur);
                                    }else
                                    {System.out.println("le livre que vous essayer d'empreunter n'existe pas");}
                                   
                                    break;
                                case 2:
                                    scanner.nextLine(); // Vider la ligne précédente
                                    
                                    System.out.print("Entrez l'isbn du livre à retourner : ");
                                    String ISBNLivreEmpruntt = scanner.nextLine();
                                    //Livre livreRetour = bib.rechercherLivre(ISBNLivreEmpruntt, "fichier.txt");
                                    utilisateur.retournerlivre(ISBNLivreEmpruntt);
                                    break;
                                case 3:
                                    utilisateur.affichageEmprunt();
                                    break;
                                case 4:
                                    System.out.println("Au revoir !");
                                   
                                    break;
                                default:
                                    System.out.println("Choix invalide. Veuillez réessayer.");
                            }

                            System.out.println();
                        }

                                    break;
               
                    
                   case 2:
              
    //interface pour le biliotechcaire
                    Bibliotheque bibliotheque = new Bibliotheque("fichier.txt");
            
                    int choix1 = 0;
            
                    while (choix1 != 8) {
                        System.out.println("Que souhaitez-vous faire ?");
                        System.out.println("1. Ajouter un livre");
                        System.out.println("2. Supprimer un livre");
                        System.out.println("3. Rechercher un livre");
                        System.out.println("4. Enregistrer un emprunt");
                        System.out.println("5. Enregistrer un retour");
                        System.out.println("6. Afficher les statistiques");
                        System.out.println("7. Afficher tous les livres...");
                        System.out.println("8. Quitter");
            
                        System.out.print("Votre choix : ");
                        choix1 = scanner.nextInt();
            
                        switch (choix1) {
                            case 1:
                                scanner.nextLine(); // Vider la ligne précédente
                                System.out.println("Entrez le titre du livre : ");
                                String titreLivre = scanner.nextLine();
                                System.out.println("Entrez l'auteur du livre : ");
                                String auteurLivre = scanner.nextLine();
                                System.out.println("Entrez l'annee de publicatioin livre du livre : ");
                                String annepubLivre = scanner.nextLine();
                                System.out.println("Entrez l 'isbn' du livre : ");
                                String isbnLivre = scanner.nextLine();
                                Livre livre = new Livre(titreLivre,auteurLivre,annepubLivre,isbnLivre); 
                                System.out.println(livre);
                                bibliotheque.ajoutLivre(livre);
                                break;
                            case 2:
                                scanner.nextLine(); // Vider la ligne précédente
                                System.out.print("Entrez le titre du livre à supprimer : ");
                                String isbnLivreSupprimer = scanner.nextLine();
                                Livre livreSupprimer= bibliotheque.rechercherLivre(isbnLivreSupprimer, "fichier.txt");
                                bibliotheque.supprimerLivre(livreSupprimer);
                                break;
                            case 3:
                                scanner.nextLine(); 
                                System.out.print("Entrez le critère de recherche : ");
                                String critereRecherche = scanner.nextLine();
                                Livre livreRecherche = bibliotheque.rechercherLivre(critereRecherche, "fichier.txt");
                                if (livreRecherche != null) {
                                    System.out.println("Livre trouvé : " + livreRecherche.toString());
                                } else {
                                    System.out.println("Aucun livre correspondant au critère n'a été trouvé.");
                                }
                                break;
                            case 4:
                                scanner.nextLine(); // Vider la ligne précédente
                                System.out.print("Entrez le nom de l'utilisateur : ");
                                String nomUtilisateur = scanner.nextLine();
                                System.out.print("Entrez le numéro d'identification de l'utilisateur : ");
                                String numeroIdentificationn = scanner.nextLine();
                        
                                Utilisateur utilisateurEmprunt = new Utilisateur(nomUtilisateur, numeroIdentificationn);
                                System.out.print("Entrez le titre du livre à emprunter : ");
                                String titreLivreEmprunt = scanner.nextLine();
                                Livre livreEm = bibliotheque.rechercherLivre(titreLivreEmprunt, "fichier.txt");

                                if (livreEm!=null) {
                                     bibliotheque.enregistrerEmprunt(utilisateurEmprunt, livreEm);
                                }else{System.out.println("ce livre n existe pas");}

                               
                                break;
                            case 5:
                                scanner.nextLine(); // Vider la ligne précédente
                                System.out.print("Entrez le nom de l'utilisateur : ");
                                String nomUtilisateurRetour = scanner.nextLine();
                                System.out.print("Entrez le numéro d'identification de l'utilisateur : ");
                                String numeroIdentificationRetour = scanner.nextLine();
       
                                Utilisateur utilisateurRetour = new Utilisateur(nomUtilisateurRetour, numeroIdentificationRetour);
                                System.out.print("Entrez le titre du livre à retourner : ");
                                String titreLivreRetour = scanner.nextLine();
                                Livre livreRetour = bibliotheque.rechercherLivre(titreLivreRetour, "fichier.txt");
                                bibliotheque.enregistrerRetour(utilisateurRetour, livreRetour);
                                break;
                            case 6:
                                bibliotheque.afficherStatistiques();
                                break;
                            case 7:
                                bibliotheque.afficherLivresDansFichier("fichier.txt");
                                break;
                            case 8:
                                System.out.println("Au revoir !");
                                break;
                         
                            default:
                                System.out.println("Choix invalide. Veuillez réessayer.");
                        }
            
                        System.out.println();
                    }
            

                    break;
                   case 3:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }

            System.out.println();
        }

        
    scanner.close();


    }




   




}
