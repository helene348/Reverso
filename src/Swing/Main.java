package Swing;

import Entities.Client;
import Entities.Prospect;
import utilitaires.Adresse;
import utilitaires.DomaineSociete;
import utilitaires.SocieteException;

import javax.swing.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws SocieteException {
        JFrame frameDebut = new FenetrePrincipale();

        frameDebut.setTitle("REVERSO");
        frameDebut.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameDebut.setLocationRelativeTo(null);
        frameDebut.setVisible(true);
        frameDebut.pack();


    }
}
