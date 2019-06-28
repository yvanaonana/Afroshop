package com.tsafack.tkswif.afroshop.entities.entitiesCustom;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tsamo ulrich on 05/04/2018.
 */

public class ArticlePanier implements Parcelable {

    private String refArticlePanier;
    private String libelleArticlePanier;
    private String idAgent;
    private double prixUnitaireArticlePanier;
    private double quantiteArticlePanier;
    private String refArticle;
    private String idFournisseurArticle;
    private String regionArticle;

    protected ArticlePanier(Parcel in) {
        refArticlePanier = in.readString();
        libelleArticlePanier = in.readString();
        idAgent = in.readString();
        prixUnitaireArticlePanier = in.readDouble();
        quantiteArticlePanier = in.readDouble();
        refArticle = in.readString();
        idFournisseurArticle = in.readString();
        regionArticle = in.readString();
    }

    public static final Creator<ArticlePanier> CREATOR = new Creator<ArticlePanier>() {
        @Override
        public ArticlePanier createFromParcel(Parcel in) {
            return new ArticlePanier(in);
        }

        @Override
        public ArticlePanier[] newArray(int size) {
            return new ArticlePanier[size];
        }
    };

    public ArticlePanier() {
    }

    public ArticlePanier(String refArticlePanier, String libelleArticlePanier, String idAgent,
                         double prixUnitaireArticlePanier, double quantiteArticlePanier, String refArticle,
                         String idFournisseurArticle, String regionArticle) {
        this.refArticlePanier = refArticlePanier;
        this.libelleArticlePanier = libelleArticlePanier;
        this.idAgent = idAgent;
        this.prixUnitaireArticlePanier = prixUnitaireArticlePanier;
        this.quantiteArticlePanier = quantiteArticlePanier;
        this.refArticle = refArticle;
        this.idFournisseurArticle = idFournisseurArticle;
        this.regionArticle = regionArticle;

    }

    public String getRefArticlePanier() {
        return refArticlePanier;
    }

    public void setRefArticlePanier(String refArticlePanier) {
        this.refArticlePanier = refArticlePanier;
    }

    public String getLibelleArticlePanier() {
        return libelleArticlePanier;
    }

    public void setLibelleArticlePanier(String libelleArticlePanier) {
        this.libelleArticlePanier = libelleArticlePanier;
    }

    public String getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(String idAgent) {
        this.idAgent = idAgent;
    }

    public String getIdFournisseurArticle() {
        return idFournisseurArticle;
    }

    public void setIdFournisseurArticle(String idFournisseurArticle) {
        this.idFournisseurArticle = idFournisseurArticle;
    }

    public double getPrixUnitaireArticlePanier() {
        return prixUnitaireArticlePanier;
    }

    public void setPrixUnitaireArticlePanier(double prixUnitaireArticlePanier) {
        this.prixUnitaireArticlePanier = prixUnitaireArticlePanier;
    }

    public double getQuantiteArticlePanier() {
        return quantiteArticlePanier;
    }

    public void setQuantiteArticlePanier(double quantiteArticlePanier) {
        this.quantiteArticlePanier = quantiteArticlePanier;
    }

    public String getRefArticle() {
        return refArticle;
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }

    public String getRegionArticle() {
        return regionArticle;
    }

    public void setRegionArticle(String regionArticle) {
        this.regionArticle = regionArticle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(refArticlePanier);
        parcel.writeString(libelleArticlePanier);
        parcel.writeString(idAgent);
        parcel.writeDouble(prixUnitaireArticlePanier);
        parcel.writeDouble(quantiteArticlePanier);
        parcel.writeString(refArticle);
        parcel.writeString(idFournisseurArticle);
        parcel.writeString(regionArticle);
    }

}
