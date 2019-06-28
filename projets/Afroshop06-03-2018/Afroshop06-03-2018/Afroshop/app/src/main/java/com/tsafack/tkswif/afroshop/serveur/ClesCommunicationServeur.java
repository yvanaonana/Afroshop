package com.tsafack.tkswif.afroshop.serveur;

/**
 * Created by ESPACE-TEK on 05/02/2018.
 */

public class ClesCommunicationServeur {


    /**************MODULE GESTION DES COMMANDES****************************************************/

    // clés pour l'enregistrement ou la lecteure des données d'un utilisateur
    public static final String ID_UTILISATEUR = "idUtilisateur";
    public static final String NOM_UTILISATEUR = "nomUtilisateur";
    public static final String DATE_NAISSANCE_UTILISATEUR = "dateNaissanceUtilisateur";
    public static final String TELEPHONE_UTILISATEUR = "telephoneUtilisateur";
    public static final String EMAIL_UTILISATEUR = "emailUtilisateur";
    public static final String PASSWORD_UTILISATEUR = "passwordUtilisateur";
    public static final String PAYS_UTILISATEUR = "paysUtilisateur";
    public static final String REGION_UTILISATEUR = "regionUtilisateur";
    public static final String LOCALITE_UTILISATEUR = "localiteUtilisateur";
    public static final String VILLE_UTILISATEUR = "villeUtilisateur";
    public static final String DATE_INSCRIPTION_UTILISATEUR = "dateCreationUtilisateur";
    public static final String URL_MEDIA_ARTICLE = "urlMediaArticle";
    public static final String TYPE_MEDIA_ARTICLE = "typeMediaArticle";
    public static final String PANIER = "panier";
    public static final String LOGIN_AGENCE_BANCAIRE = "LOGINAGENCEBANCAIRE";
    public static final String NOM_AGENCE_BANCAIRE = "NOMAGENCEBANCAIRE";
    public static final String ID_COMMANDE = "NUMCOMMANDE";
    public static final String PRIX_TOTAL_ARTICLE = "MONTANTPAYEMENTCOMMANDE";
    public static final String ARTICLES_COMMANDE = "ARTICLESCOMMANDE";
    public static final String ID_FOURNISSEUR = "idFournisseur";
    public static final String LIB_ARTICLE_COMMANDE = "LIBARTICLE";
    public static final String IMAGE_ARTICLE_ = "IMAGE_ARTICLE_";
    public static final String GROUPE_UTILISATEUR = "groupeUtilisateur";
    public static final String TYPE_UTILISATEUR = "typeUtilisateur";


    //public static  final String DATE_INSCRIPTION_UTILISATEUR = "DATEINSCRIPTIONUTILISATEUR";


    // clés pour l'enregistrement ou la recuperation des données d'une commande
    // publicstatic  String NUM_COMMANDE = "NUMCOMMANDE";
    // publicstatic String ID_UTILISATEUR = "IDUTILISATEUR";
    public static String LIB_COMMANDE = "LIBCOMMANDE";
    public static String DATE_COMMANDE = "DATECOMMANDE";
    public static String ETAT_COMMANDE = "ETATCOMMANDE";
    public static String EVOLUTION_COMMANDE = "EVOLUTIONCOMMANDE";


    // clés pour l'enregistrement des données d'un agent
    //public static String ID_AGENT = "IDAGENT";
    public static String NOM_AGENT = "NOMAGENT";
    public static String DATE_NAISSANCEAGENT = "DATENAISSANCEAGENT";
    public static String TELEPHONE_AGENT = "TELEPHONEAGENT";
    public static String EMAIL_AGENT = "EMAILAGENT";
    public static String PAYS_AGENT = "PAYSAGENT";
    public static String REGION_AGENT = "REGIONAGENT";
    public static String LOCALITE_AGENT = "LOCALITEAGENT";
    public static String VILLE_AGENT = "VILLEAGENT";
    //public static String DATEINSCRIPTION_AGENT = "DATEINSCRIPTIONAGENT";


    // clés pour l'enregistrement ou la recuperation des données d'une catégorie d'article
    public static String ID_CATEGORIE_ARTICLE = "idCategorieArticle";
    public static String LIBELLE_CATEGORIE_ARTICLE = "libelleCategorieArticle";
    public static String TYPE_CATEGORIE_ARTICLE = "typeCategorieArticle";
    public static String DATE_CREATION_CATEGORIE_ARTICLE = "DATECREATIONCATEGORIEARTICLE";
    public static String IMAGE_CATEGORIE_ARTICLE = "IMAGECATEGORIEARTICLE";


    // clés pour l'enregistrement ou la recuperation des données d'un article
    public static String REF_ARTICLE = "refArticle";
    //public static String ID_CATEGORIE_ARTICLE = "IDCATEGORIEARTICLE";
    //public static String ID_UTILISATEUR = "IDUTILISATEUR";
    public static String LIB_ARTICLE = "libelleArticle";
    public static String DESCRIPTION_ARTICLE = "descriptionArticle";
    public static String QTE_INITIALE_ARTICLE = "QTEINITIALEARTICLE";
    public static String DATE_PUBLICATION_ARTICLE = "datePublicationArticle";
    public static String PRIX_UNITAIRE_ARTICLE = "prixUnitaireArticle";
    public static String UNITE_ARTICLE = "uniteArticle";
    public static String NOMBRE_AIME_ARTICLE = "NOMBREAIMEARTICLE";
    public static String MENTION_ARTICLE = "mentionArticle";
    public static String NUMERO_NORMALISATION_ARTICLE = "numeroNormalisationArticle";
    public static final String QTE_STOCK_ARTICLE = "qteStockArticle";
    public static final String AIME_ARTICLE = "AIMERARTICLE";
    public static final String REGION_ARTICLE = "regionArticle";
    public static final String LOCALITE_ARTICLE = "localiteArticle";
    public static final String PAYS_ARTICLE = "paysArticle";
    public static final String VILLE_ARTICLE = "villeArticle";
    public static final String TYPE_ARTICLE = "typeArticle";
    public static final String IMAGE_ARTICLE_1 = "IMAGE_ARTICLE_1";
    public static final String IMAGE_ARTICLE_2 = "IMAGE_ARTICLE_2";
    public static final String IMAGE_ARTICLE_3 = "IMAGE_ARTICLE_3";
    public static final String IMAGE_ARTICLE_4 = "IMAGE_ARTICLE_4";
    public static final String IMAGE_ARTICLE_5 = "IMAGE_ARTICLE_5";

    // clés pour l'enregistrement ou la recuperation des données d'une agence
    public static String ID_AGENCE = "IDAGENCE";
    public static String NOM_AGENCE = "NOMAGENCE";
    public static String DATE_NAISSANCE_AGENCE = "DATENAISSANCEAGENCE";
    public static String TELEPHONE_AGENCE = "TELEPHONEAGENCE";
    public static String EMAIL_AGENCE = "EMAILAGENCE";
    public static String PAYS_AGENCE = "PAYSAGENCE";
    public static String REGION_AGENCE = "REGIONAGENCE";
    public static String LOCALITE_AGENCE = "LOCALITEAGENCE";
    public static String VILLE_AGENCE = "VILLEAGENCE";
    //public static String DATE_INSCRIPTION_AGENCE = "DATEINSCRIPTIONAGENCE";


    // clés pour l'enregistrement ou la recuperation des données d'une commande d'article
    public static String REF_ARTICLE_COMMANDE = "REFARTICLECOMMANDE";
    public static String ID_AGENT = "IDAGENT";
    public static String NUM_COMMANDE = "NUMCOMMANDE";
    //public static  String REF_ARTICLE = "REFARTICLE";
    public static String QTE_ARTICLE_COMMANDE = "QTEARTICLECOMMANDE";
    public static String MENTION_ARTICLE_COMMANDE = "MENTIONARTICLECOMMANDE";


    /**************FIN DU MODULE GESTION DES COMMANDES*********************************************/
}
