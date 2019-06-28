package com.tsafack.tkswif.afroshop.entities.entitiesCustom;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by tsamo ulrich on 12/04/2018.
 */

public class CommamdeCustom implements Parcelable {

    private String idCommande;
    private String libelleCommande;
    private ArrayList<ArticleCommandeCustom> articlesCommande;
    private double prixTotal;
    private boolean evolutionCommande;
    private boolean statuCommande;
    private String datePassationCommande;

    public CommamdeCustom() {
    }

    public CommamdeCustom(String idCommande, String libelleCommande, ArrayList<ArticleCommandeCustom> articlePaniers,
                          double prixTotal, boolean evolutionCommande, boolean statuCommande, String datePassationCommande) {
        this.idCommande = idCommande;
        this.libelleCommande = libelleCommande;
        this.articlesCommande = articlePaniers;
        this.prixTotal = prixTotal;
        this.evolutionCommande = evolutionCommande;
        this.statuCommande = statuCommande;
        this.datePassationCommande = datePassationCommande;
    }

    protected CommamdeCustom(Parcel in) {
        idCommande = in.readString();
        libelleCommande = in.readString();
        articlesCommande = in.createTypedArrayList(ArticleCommandeCustom.CREATOR);
        prixTotal = in.readDouble();
        datePassationCommande = in.readString();
        evolutionCommande = in.readByte() != 0;
        statuCommande = in.readByte() != 0;
    }

    public static final Creator<CommamdeCustom> CREATOR = new Creator<CommamdeCustom>() {
        @Override
        public CommamdeCustom createFromParcel(Parcel in) {
            return new CommamdeCustom(in);
        }

        @Override
        public CommamdeCustom[] newArray(int size) {
            return new CommamdeCustom[size];
        }
    };

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public String getLibelleCommande() {
        return libelleCommande;
    }

    public void setLibelleCommande(String libelleCommande) {
        this.libelleCommande = libelleCommande;
    }

    public ArrayList<ArticleCommandeCustom> getArticlePaniers() {
        return articlesCommande;
    }

    public void setArticlePaniers(ArrayList<ArticleCommandeCustom> articlePaniers) {
        this.articlesCommande = articlePaniers;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getDatePassationCommande() {
        return datePassationCommande;
    }

    public void setDatePassationCommande(String datePassationCommande) {
        this.datePassationCommande = datePassationCommande;
    }

    public boolean isEvolutionCommande() {
        return evolutionCommande;
    }

    public void setEvolutionCommande(boolean evolutionCommande) {
        this.evolutionCommande = evolutionCommande;
    }

    public boolean isStatuCommande() {
        return statuCommande;
    }

    public void setStatuCommande(boolean statuCommande) {
        this.statuCommande = statuCommande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommamdeCustom that = (CommamdeCustom) o;

        return getIdCommande().equals(that.getIdCommande());
    }

    @Override
    public int hashCode() {
        return getIdCommande().hashCode();
    }

    @Override
    public String toString() {
        return "CommamdeCustom { " +
                "idCommande = '" + idCommande + '\'' +
                ", libelleCommande = '" + libelleCommande + '\'' +
                ", articlesCommande = " + articlesCommande +
                ", prixTotal = " + prixTotal +
                ", evolutionCommande = " + evolutionCommande +
                ", statuCommande = " + statuCommande +
                ", datePublicationCommande = '" + datePassationCommande + '\'' +
                " }";
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idCommande);
        dest.writeString(libelleCommande);
        dest.writeTypedList(articlesCommande);
        dest.writeDouble(prixTotal);
        dest.writeString(datePassationCommande);
        dest.writeByte((byte) (evolutionCommande ? 1 : 0));
        dest.writeByte((byte) (statuCommande ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
