package com.example.practice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data_model
{
    @SerializedName("faclNm")
    @Expose
    private String faclNm;

    @SerializedName("faclTyCd")
    @Expose
    private String faclTyCd;

    @SerializedName("faclLng")
    @Expose
    private String faclLng;

    @SerializedName("lcMnad")
    @Expose
    private String lcMnad;

    @SerializedName("salStaDivCd")
    @Expose
    private String salStaDivCd;

    @SerializedName("faclLat")
    @Expose
    private String faclLat;

    @SerializedName("wfcltId")
    @Expose
    private String wfcltId;


    public String getFaclNm ()
    {
        return faclNm;
    }

    public void setFaclNm (String faclNm)
    {
        this.faclNm = faclNm;
    }

    public String getFaclTyCd ()
    {
        return faclTyCd;
    }

    public void setFaclTyCd (String faclTyCd)
    {
        this.faclTyCd = faclTyCd;
    }

    public String getFaclLng ()
    {
        return faclLng;
    }

    public void setFaclLng (String faclLng)
    {
        this.faclLng = faclLng;
    }

    public String getLcMnad ()
    {
        return lcMnad;
    }

    public void setLcMnad (String lcMnad)
    {
        this.lcMnad = lcMnad;
    }

    public String getSalStaDivCd ()
    {
        return salStaDivCd;
    }

    public void setSalStaDivCd (String salStaDivCd)
    {
        this.salStaDivCd = salStaDivCd;
    }

    public String getFaclLat ()
    {
        return faclLat;
    }

    public void setFaclLat (String faclLat)
    {
        this.faclLat = faclLat;
    }

    public String getWfcltId ()
    {
        return wfcltId;
    }

    public void setWfcltId (String wfcltId)
    {
        this.wfcltId = wfcltId;
    }

    @Override
    public String toString()
    {
        return "faclNm = "+faclNm+"\n faclTyCd = "+faclTyCd+"\n faclLng = "+faclLng+"\n lcMnad = "+lcMnad+"\n salStaDivCd = "+salStaDivCd+"\n faclLat = "+faclLat+"\n wfcltId = "+wfcltId+"\n\n\n";
    }
}