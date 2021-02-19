package Entities;

import utilitaires.Adresse;
import utilitaires.DomaineSociete;
import utilitaires.SocieteException;

import java.util.ArrayList;
import java.util.List;

public class Client extends Societe{
    private int chiffreAffairesClient;
    private int nombreEmployes;
    private static List<Client> clientList = new ArrayList<>();

    //constructeur client vide
    public Client(){
    }

    //constructeur sans commentaires
    public Client(int identifiant, String raisonSociale, DomaineSociete domaine, Adresse adresse, String telephone,
                  String adresseMail, int chiffreAffairesClient, int nombreEmployes) throws SocieteException {
        super(identifiant, raisonSociale, domaine, adresse, telephone, adresseMail);
        this.setChiffreAffairesClient(chiffreAffairesClient, nombreEmployes);
        this.setNombreEmployes(nombreEmployes);
    }

    //constructeur avec commentaires
    public Client(int identifiant, String raisonSociale, DomaineSociete domaine, Adresse adresse, String telephone,
                  String adresseMail, String commentaires, int chiffreAffairesClient, int nombreEmployes) throws SocieteException {
        super(identifiant, raisonSociale, domaine, adresse, telephone, adresseMail, commentaires);
        this.setChiffreAffairesClient(chiffreAffairesClient, nombreEmployes);
        this.setNombreEmployes(nombreEmployes);
    }

    /**
     * Setter du chiffre d'affaires de Client qui lance 3 exceptions.
     * @param chiffreAffairesClient : chiffre d'affaire du client.
     * @param nombreEmployes : nombre d'employés du client.
     * @throws SocieteException : lance une exception personnalisée si chiffresAffairesClient est supérieur à 0 et
     * si le résultat de la division du chiffresAffairesClient par nombreEmployés est strictement inférieur à 10.
     * @throws NumberFormatException : lance une exception si l'on reçoit en entrée autre chose qu'un chiffre
     * @throws ArithmeticException : lance une exception pour signifier que l'on ne peut pas diviser par 0.
     */
    public void setChiffreAffairesClient(int chiffreAffairesClient, int nombreEmployes) throws SocieteException, NumberFormatException,
            ArithmeticException {
        if (chiffreAffairesClient > 0) {
            if (chiffreAffairesClient/nombreEmployes <10) {
                throw new SocieteException("le chiffre d'affaires doit être augmenté ou le nombre d'employés diminué");
            }

        }
        this.chiffreAffairesClient = chiffreAffairesClient;
    }

    /**
     * Setter du nombre d'employés avec 2 exceptions.
     * @param nombreEmployes : nombre d'employés du client.
     * @throws NumberFormatException : lance une exception si l'entrée n'est pas en chiffres.
     * @throws SocieteException : lance une exception personnalisée si le nombreEmployés est inférieur à 1.
     */
    public void setNombreEmployes(int nombreEmployes) throws NumberFormatException, SocieteException {
        if (nombreEmployes < 1) {
            throw new SocieteException("Le nombre d'employés doit être positif");

        }
        this.nombreEmployes = nombreEmployes;
    }

    public static void setClientList(List<Client> clientList) {
        Client.clientList = clientList;
    }

    public static List<Client> getClientList() {
        return clientList;
    }

    public int getChiffreAffairesClient() {
        return chiffreAffairesClient;
    }

    public int getNombreEmployes() {
        return nombreEmployes;
    }
}