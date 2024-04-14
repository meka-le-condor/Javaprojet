/*
  Livre
 */
public class Livre {
    // Attributs
    private String titre, auteur, ISBN, anneePublication;
    // Methodes
    public Livre(){}
    public Livre( String titre, String auteur, String anneePublication, String ISBN)
    {
        this.titre=titre;
        this.auteur=auteur;
        this.anneePublication=anneePublication;
        this.ISBN=ISBN;
    }
    // getteurs et setteurs
    public String getTitre() {
        return this.titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getAuteur() {
        return this.auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public String getAnneePublication() {
        return this.anneePublication;
    }
    public void setAnneePublication(String anneePublication) {
        this.anneePublication = anneePublication;
    }
    public String getISBN() {
        return this.ISBN;
    }
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", année de publication=" + anneePublication +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }

}