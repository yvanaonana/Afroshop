package com.tsafack.tkswif.afroshop.entities.entitiesCustom;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tsamo ulrich on 25/04/2018.
 */

public class ArticleCommandeCustom implements Parcelable {

    private String refArticleCommandeCustom;
    private String libelleArticleCommandeCustom;
    private String idAgent;
    private String emailAgent;
    private double prixUnitaireArticleCommandeCustom;
    private double quantiteArticleCommandeCustom;
    private String refArticle;
    private String idFournisseurArticle;
    private String regionArticle;
    private String urlImageArticleCommandeCustom;

    public ArticleCommandeCustom() {
    }

    public ArticleCommandeCustom(String refArticleCommandeCustom, String libelleArticleCommandeCustom,
                                 String idAgent, String emailAgent, double prixUnitaireArticleCommandeCustom, double quantiteArticleCommandeCustom,
                                 String refArticle, String idFournisseurArticle, String regionArticle,
                                 String urlImageArticleCommandeCustom) {
        this.refArticleCommandeCustom = refArticleCommandeCustom;
        this.libelleArticleCommandeCustom = libelleArticleCommandeCustom;
        this.idAgent = idAgent;
        this.emailAgent = emailAgent;
        this.prixUnitaireArticleCommandeCustom = prixUnitaireArticleCommandeCustom;
        this.quantiteArticleCommandeCustom = quantiteArticleCommandeCustom;
        this.refArticle = refArticle;
        this.idFournisseurArticle = idFournisseurArticle;
        this.regionArticle = regionArticle;
        this.urlImageArticleCommandeCustom = urlImageArticleCommandeCustom;
    }


    protected ArticleCommandeCustom(Parcel in) {
        refArticleCommandeCustom = in.readString();
        libelleArticleCommandeCustom = in.readString();
        idAgent = in.readString();
        emailAgent = in.readString();
        prixUnitaireArticleCommandeCustom = in.readDouble();
        quantiteArticleCommandeCustom = in.readDouble();
        refArticle = in.readString();
        idFournisseurArticle = in.readString();
        regionArticle = in.readString();
        urlImageArticleCommandeCustom = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(refArticleCommandeCustom);
        dest.writeString(libelleArticleCommandeCustom);
        dest.writeString(idAgent);
        dest.writeString(emailAgent);
        dest.writeDouble(prixUnitaireArticleCommandeCustom);
        dest.writeDouble(quantiteArticleCommandeCustom);
        dest.writeString(refArticle);
        dest.writeString(idFournisseurArticle);
        dest.writeString(regionArticle);
        dest.writeString(urlImageArticleCommandeCustom);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ArticleCommandeCustom> CREATOR = new Creator<ArticleCommandeCustom>() {
        @Override
        public ArticleCommandeCustom createFromParcel(Parcel in) {
            return new ArticleCommandeCustom(in);
        }

        @Override
        public ArticleCommandeCustom[] newArray(int size) {
            return new ArticleCommandeCustom[size];
        }
    };

    public String getRefArticleCommandeCustom() {
        return refArticleCommandeCustom;
    }

    public void setRefArticleCommandeCustom(String refArticleCommandeCustom) {
        this.refArticleCommandeCustom = refArticleCommandeCustom;
    }

    public String getEmailAgent() {
        return emailAgent;
    }

    public void setEmailAgent(String emailAgent) {
        this.emailAgent = emailAgent;
    }

    public String getLibelleArticleCommandeCustom() {
        return libelleArticleCommandeCustom;
    }

    public void setLibelleArticleCommandeCustom(String libelleArticleCommandeCustom) {
        this.libelleArticleCommandeCustom = libelleArticleCommandeCustom;
    }

    public String getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(String idAgent) {
        this.idAgent = idAgent;
    }

    public double getPrixUnitaireArticleCommandeCustom() {
        return prixUnitaireArticleCommandeCustom;
    }

    public void setPrixUnitaireArticleCommandeCustom(double prixUnitaireArticleCommandeCustom) {
        this.prixUnitaireArticleCommandeCustom = prixUnitaireArticleCommandeCustom;
    }

    public double getQuantiteArticleCommandeCustom() {
        return quantiteArticleCommandeCustom;
    }

    public void setQuantiteArticleCommandeCustom(double quantiteArticleCommandeCustom) {
        this.quantiteArticleCommandeCustom = quantiteArticleCommandeCustom;
    }

    public String getRefArticle() {
        return refArticle;
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }

    public String getIdFournisseurArticle() {
        return idFournisseurArticle;
    }

    public void setIdFournisseurArticle(String idFournisseurArticle) {
        this.idFournisseurArticle = idFournisseurArticle;
    }

    public String getRegionArticle() {
        return regionArticle;
    }

    public void setRegionArticle(String regionArticle) {
        this.regionArticle = regionArticle;
    }

    public String getUrlImageArticleCommandeCustom() {
        return urlImageArticleCommandeCustom;
    }

    public void setUrlImageArticleCommandeCustom(String urlImageArticleCommandeCustom) {
        this.urlImageArticleCommandeCustom = urlImageArticleCommandeCustom;
    }

    @Override
    public String toString() {
        return "ArticleCommandeCustom{" +
                "refArticleCommandeCustom='" + refArticleCommandeCustom + '\'' +
                ", libelleArticleCommandeCustom='" + libelleArticleCommandeCustom + '\'' +
                ", idAgent='" + idAgent + '\'' +
                ", emailAgent='" + emailAgent + '\'' +
                ", prixUnitaireArticleCommandeCustom=" + prixUnitaireArticleCommandeCustom +
                ", quantiteArticleCommandeCustom=" + quantiteArticleCommandeCustom +
                ", refArticle='" + refArticle + '\'' +
                ", idFournisseurArticle='" + idFournisseurArticle + '\'' +
                ", regionArticle='" + regionArticle + '\'' +
                ", urlImageArticleCommandeCustom='" + urlImageArticleCommandeCustom + '\'' +
                '}';
    }
}
