package com.example.practice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data_model
{
    @SerializedName("estbDate")
    @Expose
    private String estbDate;

    @SerializedName("faclNm")
    @Expose
    private String faclNm;

    @SerializedName("faclTyCd")
    @Expose
    private String faclTyCd;

    @SerializedName("faclLng")
    @Expose
    private String faclLng;

    @SerializedName("salStaNm")
    @Expose
    private String salStaNm;

    @SerializedName("lcMnad")
    @Expose
    private String lcMnad;

    @SerializedName("salStaDivCd")
    @Expose
    private String salStaDivCd;

    @SerializedName("wfcltDivCd")
    @Expose
    private String wfcltDivCd;

    @SerializedName("faclInfId")
    @Expose
    private String faclInfId;

    @SerializedName("faclLat")
    @Expose
    private String faclLat;

    @SerializedName("faclRprnNm")
    @Expose
    private String faclRprnNm;

    @SerializedName("wfcltId")
    @Expose
    private String wfcltId;

    public String getEstbDate ()
    {
        return estbDate;
    }

    public void setEstbDate (String estbDate)
    {
        this.estbDate = estbDate;
    }

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

    public String getSalStaNm ()
    {
        return salStaNm;
    }

    public void setSalStaNm (String salStaNm)
    {
        this.salStaNm = salStaNm;
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

    public String getWfcltDivCd ()
    {
        return wfcltDivCd;
    }

    public void setWfcltDivCd (String wfcltDivCd)
    {
        this.wfcltDivCd = wfcltDivCd;
    }

    public String getFaclInfId ()
    {
        return faclInfId;
    }

    public void setFaclInfId (String faclInfId)
    {
        this.faclInfId = faclInfId;
    }

    public String getFaclLat ()
    {
        return faclLat;
    }

    public void setFaclLat (String faclLat)
    {
        this.faclLat = faclLat;
    }

    public String getFaclRprnNm ()
    {
        return faclRprnNm;
    }

    public void setFaclRprnNm (String faclRprnNm)
    {
        this.faclRprnNm = faclRprnNm;
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
        return "estbDate = "+estbDate+"\n faclNm = "+faclNm+"\n faclTyCd = "+faclTyCd+"\n faclLng = "+faclLng+"\n salStaNm = "+salStaNm+"\n lcMnad = "+lcMnad+"\n salStaDivCd = "+salStaDivCd+"\n wfcltDivCd = "+wfcltDivCd+"\n faclInfId = "+faclInfId+"\n faclLat = "+faclLat+"\n faclRprnNm = "+faclRprnNm+"\n wfcltId = "+wfcltId+"\n\n\n";
    }
}