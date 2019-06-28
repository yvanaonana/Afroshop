package com.tsafack.tkswif.afroshop.serveur;

/**
 * Created by messomo on 11/27/2017.
 */

public class ActionsRequettes {

    // adresse du serveur
    public static final String serveur = "https://www.tkswift.com/afroshop/";
//    public static final String serveur = "http://192.168.1.101/AfroshopWeb/";

    //adresse du script principale
    public static final String TRAITEMENT = serveur ;// + "traitement.php";
    //le mot cles qui permet l'appel d'un service
    public static final String ACTION = "action";


    //differentes actions que l'on peut effectuer:

    /**************MODULE GESTION DES COMMANDES****************************************************/

    public static final String ACTION_INSCRIPTION_UTILISATEUR = "INSCRIPTION_UTILISATEUR";
    public static final String ACTION_CONNECTER_UTILISATEUR = "CONNEXION_UTILISATEUR";
    public static final String ACTION_MODIFIER_UTILISATEUR = "MODIFIER_UTILISATEUR";
    public static final String ACTION_SUPPRIMER_UTILISATEUR = "SUPPRIMER_UTILISATEUR";
    public static final String ACTION_GET_UTILISATEUR = "";


    public static final String ACTION_CHOISIR_AGENT = "CHOISIR_AGENT";
    public static final String ACTION_SELECTIONNER_CATEGORIE_PRODUIT = "SELECTIONNER_CATEGORIE_PRODUIT";

    public static final String ACTION_PUBLIER_PRODUIT = "PUBLIER_ARTICLE";
    public static final String ACTION_LISTER_CATEGORIE = "LISTER_CATEGORIE";
    public static final String ACTION_LISTER_PRODUIT_UTILISATEUR = "LISTER_ARTICLE_PAR_UTILISATEUR";
    public static final String ACTION_LISTER_PRODUIT_CATEGORIE = "LISTER_ARTICLE_PAR_CATEGORIE";
    public static final String ACTION_MODIFIER_PRODUIT = "MODIFIER_PRODUIT";
    public static final String ACTION_SUPPRIMER_PRODUIT = "SUPPRIMER_PRODUIT";
    public static final String ACTION_LISTER_PRODUIT_LES_PLUS_POPULAIRES = "";
    public static final String ACTION_LISTER_PRODUIT_LES_PLUS_RECENTS = "";
    public static final String ACTION_LISTER_PRODUIT_LES_PLUS_COMMANDES = "";

    public static final String ACTION_PASSER_COMMANDE = "PASSER_COMMANDE";
    public static final String ACTION_MODIFIER_COMMANDE = "MODIFIER_COMMANDE";
    public static final String ACTION_LISTER_COMMANDE = "LISTER_COMMANDE";
    public static final String ACTION_LISTER_COMMANDE_RECU ="COMMANDES_RECUS";
    public static final String ACTION_LISTER_COMMANDE_PASSEE = "COMMANDES_PASSEES";
    public static final String ACTION_SUPPRIMER_COMMANDE = "SUPPRIMER_COMMANDE";
    public static final String ACTION_VALIDER_COMMANDE = "VALIDER_COMMANDE";
    public static final String ACTION_ANNULER_COMMANDE = "ANNULER_COMMANDE";
    public static final String ACTION_AJOUTER_J_AIME = "AIMER_ARTICLE";
    public static final String ACTION_CHOISIR_AGENCE = "CHOISIR_AGENCE";
    public static final String ACTION_LISTER_AGENCE_BANCAIRE = "CHOISIR_AGENCE_BANCAIRE";

    /**************MODULE GESTION DES COMMANDES****************************************************/

}