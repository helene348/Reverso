package utilitaires;

import javax.swing.*;

public class Adresse{
    private int numeroRue;
    private String nomRue;
    private String codePostal;
    private String ville;

    public Adresse(){

    }

    public Adresse(int numeroRue, String nomRue, String codePostal, String ville) throws SocieteException{
        this.setNumeroRue(numeroRue);
        this.setNomRue(nomRue);
        this.setCodePostal(codePostal);
        this.setVille(ville);
    }

    public int getNumeroRue() {
        return numeroRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    /**
     * Setter du numéro de rue de la classe Adresse.
     * @param numeroRue : numéro de rue de l'adresse.
     * @throws NumberFormatException : lance une exception si le champ n'est pas complété avec des chiffres.
     * @throws SocieteException : lance une exception personnalisée si le numéro de la rue est inférieur ou égal à 0.
     */
    public void setNumeroRue(int numeroRue) throws NumberFormatException, SocieteException {
        if (numeroRue <= 0) {
            throw new SocieteException("Le numéro de la rue doit être supérieur à 0");
        }
        this.numeroRue = numeroRue;
    }

    /**
     * Setter du nom de la rue de Adresse.
     * @param nomRue : nom de rue de l'adresse.
     * @throws SocieteException : lance une exception personnalisée si le champ n'est pas rempli.
     */
    public void setNomRue(String nomRue) throws SocieteException{
        if (nomRue == null || nomRue.isEmpty()) {
            throw new SocieteException("Le nom de la rue doit être renseigné");
        }
        this.nomRue = nomRue;
    }

    /**
     * Setter du code postal de Adresse.
     * @param codePostal : code postal de l'adresse.
     * @throws SocieteException : lance une exception personnalisée si le champ n'est pas rempli.
     */
    public void setCodePostal(String codePostal) throws SocieteException{
        if (codePostal == null || codePostal.isEmpty()) {
            throw new SocieteException("Le code postal doit être renseigné");
        }
        this.codePostal = codePostal;
    }

    /**
     * Setter de la ville de Adresse.
     * @param ville : ville de l'adresse
     * @throws SocieteException : lance une exception personnalisée si le champ n'est pas rempli.
     */
    public void setVille(String ville) throws SocieteException{
        if (ville == null || ville.isEmpty()) {
            throw new SocieteException("La ville doit être renseignée");
        }
        this.ville = ville;
    }

}
