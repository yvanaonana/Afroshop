package com.tsafack.tkswif.afroshop.entities.entitiesBD;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ESPACE-TEK on 05/02/2018.
 */

public class ArticleCommande implements Parcelable{

    private String refArticleCommande;
    private String idAgent;
    private String idAgence;
    private String numCommande;
    private String refArticle;
    private double qteArticleCommande;
    private boolean mentionArticleCommande;
    private String idFournisseurArticle;
    private String idClient;

    public ArticleCommande() {
    }

    public ArticleCommande(String refArticleCommande, String idAgent, String idArgence,
                           String numCommande, String refArticle, double qteArticleCommande,
                           boolean mentionArticleCommande, String idFournisseurArticle, String idClient) {
        this.refArticleCommande = refArticleCommande;
        this.idAgent = idAgent;
        this.idAgence = idArgence;
        this.numCommande = numCommande;
        this.refArticle = refArticle;
        this.qteArticleCommande = qteArticleCommande;
        this.mentionArticleCommande = mentionArticleCommande;
        this.idFournisseurArticle = idFournisseurArticle;
        this.idClient = idClient;
    }

    protected ArticleCommande(Parcel in) {
        refArticleCommande = in.readString();
        idAgent = in.readString();
        idAgence = in.readString();
        numCommande = in.readString();
        refArticle = in.readString();
        qteArticleCommande = in.readDouble();
        idFournisseurArticle = in.readString();
        idClient = in.readString();
        mentionArticleCommande = in.readByte() != 0;
    }

    public static final Creator<ArticleCommande> CREATOR = new Creator<ArticleCommande>() {
        @Override
        public ArticleCommande createFromParcel(Parcel in) {
            return new ArticleCommande(in);
        }

        @Override
        public ArticleCommande[] newArray(int size) {
            return new ArticleCommande[size];
        }
    };

    public String getRefArticleCommande() {
        return refArticleCommande;
    }

    public void setRefArticleCommande(String refArticleCommande) {
        this.refArticleCommande = refArticleCommande;
    }

    public String getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(String idAgent) {
        this.idAgent = idAgent;
    }

    public String getIdArgence() {
        return idAgence;
    }

    public void setIdArgence(String idArgence) {
        this.idAgence = idArgence;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public String getRefArticle() {
        return refArticle;
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }

    public double getQteArticleCommande() {
        return qteArticleCommande;
    }

    public void setQteArticleCommande(double qteArticleCommande) {
        this.qteArticleCommande = qteArticleCommande;
    }

    public String getIdFournisseurArticle() {
        return idFournisseurArticle;
    }

    public void setIdFournisseurArticle(String idFournisseurArticle) {
        this.idFournisseurArticle = idFournisseurArticle;
    }

    public boolean isMentionArticleCommande() {
        return mentionArticleCommande;
    }

    public void setMentionArticleCommande(boolean mentionArticleCommande) {
        this.mentionArticleCommande = mentionArticleCommande;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleCommande that = (ArticleCommande) o;

        if (!refArticleCommande.equals(that.refArticleCommande)) return false;
        if (idAgent != null ? !idAgent.equals(that.idAgent) : that.idAgent != null) return false;
        if (idAgence != null ? !idAgence.equals(that.idAgence) : that.idAgence != null)
            return false;
        return numCommande != null ? numCommande.equals(that.numCommande) : that.numCommande == null;

    }

    @Override
    public int hashCode() {
        int result = refArticleCommande.hashCode();
        result = 31 * result + (idAgent != null ? idAgent.hashCode() : 0);
        result = 31 * result + (idAgence != null ? idAgence.hashCode() : 0);
        result = 31 * result + (numCommande != null ? numCommande.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArticleCommande{" +
                "refArticleCommande = '" + refArticleCommande + '\'' +
                ", idAgent = '" + idAgent + '\'' +
                ", idArgence = '" + idAgence + '\'' +
                ", numCommande = '" + numCommande + '\'' +
                ", refArticle = '" + refArticle + '\'' +
                ", qteArticleCommande = " + qteArticleCommande +
                ", mentionArticleCommande = " + mentionArticleCommande +
                ", idFournisseur = " + idFournisseurArticle +
                ", idClient = " + idClient +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(refArticleCommande);
        parcel.writeString(idAgent);
        parcel.writeString(idAgence);
        parcel.writeString(numCommande);
        parcel.writeString(refArticle);
        parcel.writeDouble(qteArticleCommande);
        parcel.writeString(idFournisseurArticle);
        parcel.writeString(idClient);
        parcel.writeByte((byte) (mentionArticleCommande ? 1 : 0));
    }
}
