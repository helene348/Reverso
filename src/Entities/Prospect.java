package Entities;

import utilitaires.Adresse;
import utilitaires.DomaineSociete;
import utilitaires.SocieteException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prospect extends Societe {
    private Date dateProspection;
    private String prospectInteresse;
    private static List<Prospect> prospectList = new ArrayList<>();

    //constructeur vide
    public Prospect(){

    }

    //constructeur sans commentaires
    public Prospect(int identifiant, String raisonSociale, DomaineSociete domaine, Adresse adresse, String telephone,
                    String adresseMail, Date dateProspection, String prospectInteresse) throws SocieteException, ParseException {
        super(identifiant, raisonSociale, domaine, adresse, telephone, adresseMail);
        this.setDateProspection(dateProspection);
        this.setProspectInteresse(prospectInteresse);
    }

    //constructeur avec commentaires
    public Prospect(int identifiant, String raisonSociale, DomaineSociete domaine, Adresse adresse, String telephone,
                    String adresseMail, String commentaires, Date dateProspection, String prospectInteresse)
                    throws SocieteException, ParseException {
        super(identifiant, raisonSociale, domaine, adresse, telephone, adresseMail, commentaires);
        this.setDateProspection(dateProspection);
        this.setProspectInteresse(prospectInteresse);
    }

    public static List<Prospect> getProspectList() {
        return prospectList;
    }

    public Date getDateProspection() {
        return dateProspection;
    }

    public String getProspectInteresse() {
        return prospectInteresse;
    }

    /**
     * Setter de la date de prospection
     * @param dateProspection : date de prospection du client.
     * @throws SocieteException : lance une exception personnalisée si la date est dans le futur.
     * @throws ParseException : lance une exception si le format(dd/MM/yyyy) de la date n'est pas respectée.
     */
    public void setDateProspection(Date dateProspection) throws SocieteException, ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();
        System.out.println(dateFormat.format(now));

        if(dateProspection.after(now)){
            throw new SocieteException("La date doit être à aujourd'hui ou plus ancienne");
        }

        this.dateProspection = dateProspection;
    }

    public void setProspectInteresse(String prospectInteresse) {
        this.prospectInteresse = prospectInteresse;
    }

    public static void setProspectList(List<Prospect> prospectList) {
        Prospect.prospectList = prospectList;
    }
}
