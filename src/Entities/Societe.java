package Entities;

import utilitaires.Adresse;
import utilitaires.DomaineSociete;
import utilitaires.SocieteException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Societe {
    //variables
    private int identifiant = 0;
    private String raisonSociale;
    private DomaineSociete domaine;
    public Adresse adresse;
    private String telephone;
    private String adresseMail;
    private Client client;
    private Prospect prospect;
    private String commentaires;

    public static final String TELEPHONE_REGEX = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    //constructeurs

    public Societe(){

    }
    //constructeur sans commmentaires
    public Societe(int identifiant, String raisonSociale, DomaineSociete domaine, Adresse adresse, String telephone,
                   String adresseMail) throws SocieteException{
        this.setIdentifiant(identifiant);
        this.setRaisonSociale(raisonSociale);
        this.setDomaine(domaine);
        this.adresse = adresse;
        this.setTelephone(telephone);
        this.setAdresseMail(adresseMail);

    }

    //constructeur avec commentaires
    public Societe(int identifiant, String raisonSociale, DomaineSociete domaine, Adresse adresse, String telephone,
                   String adresseMail, String commentaires) throws SocieteException {
        this.setIdentifiant(identifiant);
        this.setRaisonSociale(raisonSociale);
        this.setDomaine(domaine);
        this.adresse = adresse;
        this.setTelephone(telephone);
        this.setAdresseMail(adresseMail);
        this.setCommentaires(commentaires);
    }



    //méthodes

    /**
     * Méthode qui permet de récupérer uniquement la raison sociale d'une société dans une jcombobox.
     * @return : String de la raison sociale de la société.
     */
    @Override
    public String toString(){
        return this.getRaisonSociale();

    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * Setter de la raison sociale de la société.
     * @param raisonSociale : raison sociale de la société
     * @throws SocieteException : lance une exception personnalisée si le champ n'est pas rempli.
     */
    public void setRaisonSociale(String raisonSociale) throws SocieteException {
        if(raisonSociale == null || raisonSociale.isEmpty()) {
            throw new SocieteException("La raison sociale doit être renseignée");
        }
        this.raisonSociale = raisonSociale;
    }

    public DomaineSociete getDomaine() {
        return domaine;
    }

    public void setDomaine(DomaineSociete domaine) {
        this.domaine = domaine;
    }

    public String getAdresse() {
        return adresse.toString();
    }

    /**
     * Setter de l'adresse de la société.
     * @param adresse : adresse de la société.
     * @throws SocieteException :lance une exception personnalisée si le champ n'est pas rempli.
     */
    public void setAdresse(Adresse adresse) throws SocieteException {
        if(adresse == null){
            throw new SocieteException("l'adresse doit être renseignée");
        }
        this.adresse = adresse;

    }

    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter du numéro de téléphone de la société.
     * @param telephone : numéro de téléphone de la société
     * @throws SocieteException : lance une exception personnalisée si le champ n'est pas rempli, et si le format
     * du regex n'est pas respecté.
     * see ://stackoverflow.com/questions/38483885/regex-for-french-telephone-numbers
     */
    public void setTelephone(String telephone) throws SocieteException{
        if(telephone == null || telephone.isEmpty()){
            throw new SocieteException("Le numero de téléphone doit être renseigné");
        }
        Pattern pattern = Pattern.compile(TELEPHONE_REGEX);
        Matcher matcher = pattern.matcher(telephone);
        boolean matches = matcher.matches();

        if(!matches){
            throw new SocieteException("le format de numéro de téléphone n'est pas correct");
        }
        this.telephone = telephone;
    }


    public String getAdresseMail() {
        return adresseMail;
    }

    /**
     * Setter de l'adresse mail de la classe Société.
     * @param adresseMail : adresse mail de la société
     * @throws SocieteException : lance une exception personnalisée si le champ est vide ou si le format du mail ne
     * correspond pas au regex.
     * see ://howtodoinjava.com/java/regex/java-regex-validate-email-address/
     */
    public void setAdresseMail(String adresseMail) throws SocieteException {
        if(adresseMail == null || adresseMail.isEmpty()){
            throw new SocieteException("L'adresse mail doit être renseignée");
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(adresseMail);
        boolean matches = matcher.matches();

        if(!matches){
            throw new SocieteException("Le format de l'adresse mail n'est pas correct");
        }
        this.adresseMail = adresseMail;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Prospect getProspect() {
        return prospect;
    }

    public void setProspect(Prospect prospect) {
        this.prospect = prospect;
    }

}
