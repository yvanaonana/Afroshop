package com.tsafack.tkswif.afroshop.entities.entitiesBD;

import android.os.Parcel;
import android.os.Parcelable;

import com.tsafack.tkswif.afroshop.entities.articles;

import java.util.Date;

/**
 * Created by ESPACE-TEK on 04/02/2018.
 */

public class Article extends articles implements Parcelable{

    private String refArticle;
    private String idCategorieArticle;
    private String idUtilisateur;
    private String libArticle;
    private String descriptionArticle;
    private double qteInitialeArticle;
    private Date datePublicationArticle;
    private double prixUnitaireArticle;
    private String uniteArticle;
    private Integer nombreAimeArticle;
    private boolean mentionArticle;
    private String numeroNormalisationArticle;
    private String regionArticle;
    private String villeArticle;
    private String paysArticle;
    private String quartierArticle;

    public Article() {
    }

    public Article(String refArticle, String idCategorieArticle, String idUtilisateur,
                   String libArticle, String descriptionArticle, double qteInitialeArticle,
                   Date datePublicationArticle, double prixUnitaireArticle, String uniteArticle,
                   Integer nombreAimeArticle, boolean mentionArticle, String numeroNormalisationArticle,
                   String regionArticle, String villeArticle, String quartierArticle, String paysArticle) {
        this.refArticle = refArticle;
        this.idCategorieArticle = idCategorieArticle;
        this.idUtilisateur = idUtilisateur;
        this.libArticle = libArticle;
        this.descriptionArticle = descriptionArticle;
        this.qteInitialeArticle = qteInitialeArticle;
        this.datePublicationArticle = datePublicationArticle;
        this.prixUnitaireArticle = prixUnitaireArticle;
        this.uniteArticle = uniteArticle;
        this.nombreAimeArticle = nombreAimeArticle;
        this.mentionArticle = mentionArticle;
        this.numeroNormalisationArticle = numeroNormalisationArticle;
        this.regionArticle = regionArticle;
        this.villeArticle = villeArticle;
        this.quartierArticle = quartierArticle;
        this.paysArticle = paysArticle;
    }

    protected Article(Parcel in) {
        refArticle = in.readString();
        idCategorieArticle = in.readString();
        idUtilisateur = in.readString();
        libArticle = in.readString();
        descriptionArticle = in.readString();
        qteInitialeArticle = in.readDouble();
        prixUnitaireArticle = in.readDouble();
        uniteArticle = in.readString();
        mentionArticle = in.readByte() != 0;
        numeroNormalisationArticle = in.readString();
        regionArticle = in.readString();
        villeArticle = in.readString();
        quartierArticle = in.readString();
        paysArticle = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getRefArticle() {
        return refArticle;
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }

    public String getIdCategorieArticle() {
        return idCategorieArticle;
    }

    public void setIdCategorieArticle(String idCategorieArticle) {
        this.idCategorieArticle = idCategorieArticle;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLibArticle() {
        return libArticle;
    }

    public void setLibArticle(String libArticle) {
        this.libArticle = libArticle;
    }

    public String getDescriptionArticle() {
        return descriptionArticle;
    }

    public void setDescriptionArticle(String descriptionArticle) {
        this.descriptionArticle = descriptionArticle;
    }

    public double getQteInitialeArticle() {
        return qteInitialeArticle;
    }

    public void setQteInitialeArticle(double qteInitialeArticle) {
        this.qteInitialeArticle = qteInitialeArticle;
    }

    public Date getDatePublicationArticle() {
        return datePublicationArticle;
    }

    public void setDatePublicationArticle(Date datePublicationArticle) {
        this.datePublicationArticle = datePublicationArticle;
    }

    public double getPrixUnitaireArticle() {
        return prixUnitaireArticle;
    }

    public void setPrixUnitaireArticle(double prixUnitaireArticle) {
        this.prixUnitaireArticle = prixUnitaireArticle;
    }

    public String getUniteArticle() {
        return uniteArticle;
    }

    public void setUniteArticle(String uniteArticle) {
        this.uniteArticle = uniteArticle;
    }

    public Integer getNombreAimeArticle() {
        return nombreAimeArticle;
    }

    public String getRegionArticle() {
        return regionArticle;
    }

    public void setRegionArticle(String regionArticle) {
        this.regionArticle = regionArticle;
    }

    public String getVilleArticle() {
        return villeArticle;
    }

    public void setVilleArticle(String villeArticle) {
        this.villeArticle = villeArticle;
    }

    public String getQuartierArticle() {
        return quartierArticle;
    }

    public void setQuartierArticle(String quartierArticle) {
        this.quartierArticle = quartierArticle;
    }

    public void setNombreAimeArticle(Integer nombreAimeArticle) {

        this.nombreAimeArticle = nombreAimeArticle;
    }

    public boolean isMentionArticle() {
        return mentionArticle;
    }

    public void setMentionArticle(boolean mentionArticle) {
        this.mentionArticle = mentionArticle;
    }

    public String getNumeroNormalisationArticle() {
        return numeroNormalisationArticle;
    }

    public void setNumeroNormalisationArticle(String numeroNormalisationArticle) {
        this.numeroNormalisationArticle = numeroNormalisationArticle;
    }

    public String getPaysArticle() {
        return paysArticle;
    }

    public void setPaysArticle(String paysArticle) {
        this.paysArticle = paysArticle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!refArticle.equals(article.refArticle)) return false;
        if (!idCategorieArticle.equals(article.idCategorieArticle)) return false;
        if (!idUtilisateur.equals(article.idUtilisateur)) return false;
        return libArticle.equals(article.libArticle);

    }

    @Override
    public int hashCode() {
        int result = refArticle.hashCode();
        result = 31 * result + idCategorieArticle.hashCode();
        result = 31 * result + idUtilisateur.hashCode();
        result = 31 * result + libArticle.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "refArticle = '" + refArticle + '\'' +
                ", idCategorieArticle = '" + idCategorieArticle + '\'' +
                ", idUtilisateur = '" + idUtilisateur + '\'' +
                ", libArticle = '" + libArticle + '\'' +
                ", descriptionArticle = '" + descriptionArticle + '\'' +
                ", qteInitialeArticle = " + qteInitialeArticle +
                ", datePublicationArticle = " + datePublicationArticle +
                ", prixUnitaireArticle = " + prixUnitaireArticle +
                ", uniteArticle = '" + uniteArticle + '\'' +
                ", nombreAimeArticle = " + nombreAimeArticle +
                ", mentionArticle = " + mentionArticle +
                ", numeroNormalisationArticle = '" + numeroNormalisationArticle + '\'' +
                ", regionArticle = " + regionArticle +
                ", quartierArticle = " + quartierArticle +
                ", villeArticle = " + villeArticle +
                ", paysArticle = " + paysArticle +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(refArticle);
        parcel.writeString(idCategorieArticle);
        parcel.writeString(idUtilisateur);
        parcel.writeString(libArticle);
        parcel.writeString(descriptionArticle);
        parcel.writeDouble(qteInitialeArticle);
        parcel.writeDouble(prixUnitaireArticle);
        parcel.writeString(uniteArticle);
        parcel.writeByte((byte) (mentionArticle ? 1 : 0));
        parcel.writeString(numeroNormalisationArticle);
        parcel.writeString(regionArticle);
        parcel.writeString(villeArticle);
        parcel.writeString(quartierArticle);
        parcel.writeString(paysArticle);
    }
}
