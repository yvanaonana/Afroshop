package com.tsafack.tkswif.afroshop.entities.entitiesCustom;

import android.os.Parcel;
import android.os.Parcelable;

import com.tsafack.tkswif.afroshop.entities.entitiesBD.ArticleCommande;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsamo ulrich on 24/04/2018.
 */

public class CommandePanier implements Parcelable {

    private String loginAgenceBancaire;
    private boolean statutPayementCommande;
    private List<ArticleCommande> articleCommandes = new ArrayList<>();

    protected CommandePanier(Parcel in) {
        loginAgenceBancaire = in.readString();
        statutPayementCommande = in.readByte() != 0;
        articleCommandes = in.createTypedArrayList(ArticleCommande.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loginAgenceBancaire);
        dest.writeByte((byte) (statutPayementCommande ? 1 : 0));
        dest.writeTypedList(articleCommandes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommandePanier> CREATOR = new Creator<CommandePanier>() {
        @Override
        public CommandePanier createFromParcel(Parcel in) {
            return new CommandePanier(in);
        }

        @Override
        public CommandePanier[] newArray(int size) {
            return new CommandePanier[size];
        }
    };

    public CommandePanier() {
    }

    public CommandePanier(String loginAgenceBancaire, boolean statutPayementCommande, List<ArticleCommande> articleCommandes) {
        this.loginAgenceBancaire = loginAgenceBancaire;
        this.statutPayementCommande = statutPayementCommande;
        this.articleCommandes = articleCommandes;
    }

    public String getLoginAgenceBancaire() {
        return loginAgenceBancaire;
    }

    public void setLoginAgenceBancaire(String loginAgenceBancaire) {
        this.loginAgenceBancaire = loginAgenceBancaire;
    }

    public boolean isStatutPayementCommande() {
        return statutPayementCommande;
    }

    public void setStatutPayementCommande(boolean statutPayementCommande) {
        this.statutPayementCommande = statutPayementCommande;
    }

    public List<ArticleCommande> getArticleCommandes() {
        return articleCommandes;
    }

    public void setArticleCommandes(List<ArticleCommande> articleCommandes) {
        this.articleCommandes = articleCommandes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandePanier that = (CommandePanier) o;

        return getArticleCommandes() != null ? getArticleCommandes().equals(that.getArticleCommandes()) : that.getArticleCommandes() == null;
    }

    @Override
    public int hashCode() {
        return getArticleCommandes() != null ? getArticleCommandes().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CommandeCustom { " +
                " loginAgenceBancaire = '" + loginAgenceBancaire + '\'' +
                ", statutPayementCommande = " + statutPayementCommande +
                ", articleCommandes = " + articleCommandes +
                " }";
    }
}
