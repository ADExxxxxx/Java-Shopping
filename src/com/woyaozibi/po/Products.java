package com.woyaozibi.po;

public class Products {
    private int pid;
    private String pname;
    private String price;
    private String imgurl;
    private String pdesc;
    private int cid;

    public  Products(){}

    @Override
    public String toString(){
        return "pid:"+pid+"\n"+
                "pname"+pname+"\n"+
                "price"+price+"\n"+
                "imgurl"+imgurl+"\n"+
                "pdesc"+pdesc+"\n";
     }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
