package com.tsafack.tkswif.afroshop.entities.entitiesBD;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by ESPACE-TEK on 05/02/2018.
 */

public class Agent implements Parcelable{
    private  String idAgent;
    private String nomAgent;
    private Date datNaissanceAgent;
    private String telephoneAgent;
    private String emailAgent;
    private String paysAgent;
    private String regionAgent;
    private String localiteAgent;
    private String villeAgent;
    private String dateInscriptionAgent;

    public Agent() {
    }

    public Agent(String idAgent, String nomAgent, Date datNaissanceAgent, String telephoneAgent,
                 String emailAgent, String paysAgent, String regionAgent, String localiteAgent,
                 String villeAgent, String dateInscriptionAgent) {
        this.idAgent = idAgent;
        this.nomAgent = nomAgent;
        this.datNaissanceAgent = datNaissanceAgent;
        this.telephoneAgent = telephoneAgent;
        this.emailAgent = emailAgent;
        this.paysAgent = paysAgent;
        this.regionAgent = regionAgent;
        this.localiteAgent = localiteAgent;
        this.villeAgent = villeAgent;
        this.dateInscriptionAgent = dateInscriptionAgent;
    }

    protected Agent(Parcel in) {
        idAgent = in.readString();
        nomAgent = in.readString();
        telephoneAgent = in.readString();
        emailAgent = in.readString();
        paysAgent = in.readString();
        regionAgent = in.readString();
        localiteAgent = in.readString();
        villeAgent = in.readString();
        dateInscriptionAgent = in.readString();
    }

    public static final Creator<Agent> CREATOR = new Creator<Agent>() {
        @Override
        public Agent createFromParcel(Parcel in) {
            return new Agent(in);
        }

        @Override
        public Agent[] newArray(int size) {
            return new Agent[size];
        }
    };

    public String getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(String idAgent) {
        this.idAgent = idAgent;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public Date getDatNaissanceAgent() {
        return datNaissanceAgent;
    }

    public void setDatNaissanceAgent(Date datNaissanceAgent) {
        this.datNaissanceAgent = datNaissanceAgent;
    }

    public String getTelephoneAgent() {
        return telephoneAgent;
    }

    public void setTelephoneAgent(String telephoneAgent) {
        this.telephoneAgent = telephoneAgent;
    }

    public String getEmailAgent() {
        return emailAgent;
    }

    public void setEmailAgent(String emailAgent) {
        this.emailAgent = emailAgent;
    }

    public String getPaysAgent() {
        return paysAgent;
    }

    public void setPaysAgent(String paysAgent) {
        this.paysAgent = paysAgent;
    }

    public String getRegionAgent() {
        return regionAgent;
    }

    public void setRegionAgent(String regionAgent) {
        this.regionAgent = regionAgent;
    }

    public String getLocaliteAgent() {
        return localiteAgent;
    }

    public void setLocaliteAgent(String localiteAgent) {
        this.localiteAgent = localiteAgent;
    }

    public String getVilleAgent() {
        return villeAgent;
    }

    public void setVilleAgent(String villeAgent) {
        this.villeAgent = villeAgent;
    }

    public String getDateInscriptionAgent() {
        return dateInscriptionAgent;
    }

    public void setDateInscriptionAgent(String dateInscriptionAgent) {
        this.dateInscriptionAgent = dateInscriptionAgent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agent agent = (Agent) o;

        return idAgent.equals(agent.idAgent);

    }

    @Override
    public int hashCode() {
        return idAgent.hashCode();
    }

    @Override
    public String toString() {
        return "Agent{" +
                "idAgent='" + idAgent + '\'' +
                ", nomAgent='" + nomAgent + '\'' +
                ", datNaissanceAgent=" + datNaissanceAgent +
                ", telephoneAgent='" + telephoneAgent + '\'' +
                ", emailAgent='" + emailAgent + '\'' +
                ", paysAgent='" + paysAgent + '\'' +
                ", regionAgent='" + regionAgent + '\'' +
                ", localiteAgent='" + localiteAgent + '\'' +
                ", villeAgent='" + villeAgent + '\'' +
                ", dateInscriptionAgent='" + dateInscriptionAgent + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idAgent);
        parcel.writeString(nomAgent);
        parcel.writeString(telephoneAgent);
        parcel.writeString(emailAgent);
        parcel.writeString(paysAgent);
        parcel.writeString(regionAgent);
        parcel.writeString(localiteAgent);
        parcel.writeString(villeAgent);
        parcel.writeString(dateInscriptionAgent);
    }
}
