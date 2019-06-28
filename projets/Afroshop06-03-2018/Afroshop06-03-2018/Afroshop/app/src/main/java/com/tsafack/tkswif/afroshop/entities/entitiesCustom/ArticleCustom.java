package com.tsafack.tkswif.afroshop.entities.entitiesCustom;

import android.os.Parcel;
import android.os.Parcelable;

import com.tsafack.tkswif.afroshop.entities.entitiesBD.CategorieArticle;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.MediaArticle;

import java.util.ArrayList;

/**
 * Created by leds on 3/13/2018.
 */

public class ArticleCustom implements Parcelable {

    private String refArticle;
    private String libArticle;
    private String categorieArticle;
    private String descriptionArticle;
    private double qteInitialeArticle;
    private double qteRestanteArticle;
    private double prixUnitaireArticle;
    private String datePublicationArticle;
    private String uniteArticle;
    private String numNormalisationArticle;
    private String idFournisseurArticle;
    private String nomFournisseurArticle;
    private int nombreAimeArticle;
    private boolean mentionArticle;
    private ArrayList<MediaArticle> images;
    private boolean aimeArticle;
    private String regionArticle;
    private String villeArticle;
    private String quartierArticle;

    public ArticleCustom() {
    }

    public ArticleCustom(String refArticle, String libArticle, String categorieArticle,
                         String descriptionArticle, double qteInitialeArticle,
                         double qteRestanteArticle, double prixUnitaireArticle,
                         String datePublicationArticle, String uniteArticle,
                         String numNormalisationArticle, String idFournisseurArticle,
                         String nomFournisseurArticle, int nombreAimeArticle, boolean mentionArticle,
                         boolean aimeArticle, String regionArticle, String villeArticle, String quartierArticle) {
        this.refArticle = refArticle;
        this.libArticle = libArticle;
        this.categorieArticle = categorieArticle;
        this.descriptionArticle = descriptionArticle;
        this.qteInitialeArticle = qteInitialeArticle;
        this.qteRestanteArticle = qteRestanteArticle;
        this.prixUnitaireArticle = prixUnitaireArticle;
        this.datePublicationArticle = datePublicationArticle;
        this.uniteArticle = uniteArticle;
        this.numNormalisationArticle = numNormalisationArticle;
        this.idFournisseurArticle = idFournisseurArticle;
        this.nomFournisseurArticle = nomFournisseurArticle;
        this.nombreAimeArticle = nombreAimeArticle;
        this.mentionArticle = mentionArticle;
        this.aimeArticle = aimeArticle;
        this.regionArticle = regionArticle;
        this.quartierArticle = quartierArticle;
        this.villeArticle = villeArticle;
    }


    protected ArticleCustom(Parcel in) {
        refArticle = in.readString();
        libArticle = in.readString();
        categorieArticle = in.readString();
        descriptionArticle = in.readString();
        qteInitialeArticle = in.readDouble();
        qteRestanteArticle = in.readDouble();
        prixUnitaireArticle = in.readDouble();
        datePublicationArticle = in.readString();
        uniteArticle = in.readString();
        numNormalisationArticle = in.readString();
        idFournisseurArticle = in.readString();
        nomFournisseurArticle = in.readString();
        nombreAimeArticle = in.readInt();
        mentionArticle = in.readByte() != 0;
        images = in.createTypedArrayList(MediaArticle.CREATOR);
        aimeArticle = in.readByte() != 0;
        regionArticle = in.readString();
        villeArticle = in.readString();
        quartierArticle = in.readString();
    }

    public static final Creator<ArticleCustom> CREATOR = new Creator<ArticleCustom>() {
        @Override
        public ArticleCustom createFromParcel(Parcel in) {
            return new ArticleCustom(in);
        }

        @Override
        public ArticleCustom[] newArray(int size) {
            return new ArticleCustom[size];
        }
    };

    public String getRefArticle() {
        return refArticle;
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }

    public String getLibArticle() {
        return libArticle;
    }

    public void setLibArticle(String libArticle) {
        this.libArticle = libArticle;
    }

    public String getCategorieArticle() {
        return categorieArticle;
    }

    public void setCategorieArticle(String categorieArticle) {
        this.categorieArticle = categorieArticle;
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

    public double getQteRestanteArticle() {
        return qteRestanteArticle;
    }

    public void setQteRestanteArticle(double qteRestanteArticle) {
        this.qteRestanteArticle = qteRestanteArticle;
    }

    public double getPrixUnitaireArticle() {
        return prixUnitaireArticle;
    }

    public void setPrixUnitaireArticle(double prixUnitaireArticle) {
        this.prixUnitaireArticle = prixUnitaireArticle;
    }

    public String getDatePublicationArticle() {
        return datePublicationArticle;
    }

    public void setDatePublicationArticle(String datePublicationArticle) {
        this.datePublicationArticle = datePublicationArticle;
    }

    public String getUniteArticle() {
        return uniteArticle;
    }

    public void setUniteArticle(String uniteArticle) {
        this.uniteArticle = uniteArticle;
    }

    public String getNumNormalisationArticle() {
        return numNormalisationArticle;
    }

    public void setNumNormalisationArticle(String numNormalisationArticle) {
        this.numNormalisationArticle = numNormalisationArticle;
    }

    public String getIdFournisseurArticle() {
        return idFournisseurArticle;
    }

    public void setIdFournisseurArticle(String idFournisseurArticle) {
        this.idFournisseurArticle = idFournisseurArticle;
    }

    public String getNomFournisseurArticle() {
        return nomFournisseurArticle;
    }

    public void setNomFournisseurArticle(String nomFournisseurArticle) {
        this.nomFournisseurArticle = nomFournisseurArticle;
    }

    public int getNombreAimeArticle() {
        return nombreAimeArticle;
    }

    public void setNombreAimeArticle(int nombreAimeArticle) {
        this.nombreAimeArticle = nombreAimeArticle;
    }

    public boolean isMentionArticle() {
        return mentionArticle;
    }

    public void setMentionArticle(boolean mentionArticle) {
        this.mentionArticle = mentionArticle;
    }

    public ArrayList<MediaArticle> getImages() {
        return images;
    }

    public void setImages(ArrayList<MediaArticle> images) {
        this.images = images;
    }

    public boolean isAimeArticle() {
        return aimeArticle;
    }

    public void setAimeArticle(boolean aimeArticle) {
        this.aimeArticle = aimeArticle;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(refArticle);
        parcel.writeString(libArticle);
        parcel.writeString(categorieArticle);
        parcel.writeString(descriptionArticle);
        parcel.writeDouble(qteInitialeArticle);
        parcel.writeDouble(qteRestanteArticle);
        parcel.writeDouble(prixUnitaireArticle);
        parcel.writeString(datePublicationArticle);
        parcel.writeString(uniteArticle);
        parcel.writeString(numNormalisationArticle);
        parcel.writeString(idFournisseurArticle);
        parcel.writeString(nomFournisseurArticle);
        parcel.writeInt(nombreAimeArticle);
        parcel.writeByte((byte) (mentionArticle ? 1 : 0));
        parcel.writeTypedList(images);
        parcel.writeByte((byte) (aimeArticle ? 1 : 0));
        parcel.writeString(regionArticle);
        parcel.writeString(villeArticle);
        parcel.writeString(quartierArticle);
    }
}
