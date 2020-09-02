package com.alpine.team1.AlpineClassTeam1.CarStatus;

public class State {
    private boolean chemeng1;//车门1
    private boolean chemeng2;//车门2
    private boolean chemeng3;//车门3
    private boolean chemeng4;//车门4
    private boolean kongtiao;//空调

    private boolean chemensuo;//车门锁
    private boolean houbeixiang;//后备箱
    private boolean chechuang;//车窗

    private boolean shousha;//手刹
    private boolean anquandai;//安全带
    private boolean yushuaqi;//雨刷器

    private boolean lengqi;//冷气
    private boolean reqi;//热气
    private boolean carstart;

    private double shengyudianliang;//剩余电量
    private double yixingshigongli;//已行驶公里
    private double shengyuxingshigongli;//剩余行驶公里

    public double isShengyudianliang(){return shengyudianliang;}
    public void setShengyudianliang(double shengyudianliang) {
        this.shengyudianliang = shengyudianliang;
    }
    public double isYixingshigongli(){return yixingshigongli;}
    public void setYixingshigongli(double yixingshigongli) {
        this.yixingshigongli = yixingshigongli;
    }
    public double isShengyuxingshigongli(){return shengyuxingshigongli;}
    public void setShengyuxingshigongli(double shengyuxingshigongli) {
        this.shengyuxingshigongli = shengyuxingshigongli;
    }

    public boolean isChemeng1() {
        return chemeng1;
    }

    public void setChemeng1(boolean chemeng1) {
        this.chemeng1 = chemeng1;
    }

    public boolean isChemeng2() {
        return chemeng2;
    }

    public void setChemeng2(boolean chemeng2) { this.chemeng2 = chemeng2; }

    public boolean isChemeng3() {
        return chemeng3;
    }

    public void setChemeng3(boolean chemeng3) {
        this.chemeng3 = chemeng3;
    }

    public boolean isChemeng4() {
        return chemeng4;
    }

    public void setChemeng4(boolean chemeng4) {
        this.chemeng4 = chemeng4;
    }


    public boolean isKongtiao() {
        return kongtiao;
    }

    public void setKongtiao(boolean kongtiao) {
        this.kongtiao = kongtiao;
    }

    public boolean isHoubeixiang() { return houbeixiang; }

    public void setHoubeixiang(boolean houbeixiang) { this.houbeixiang = houbeixiang; }

    public boolean isChemensuo() {
        return chemensuo;
    }

    public void setChemensuo(boolean chemensuo) {
        this.chemensuo = chemensuo;
    }



    public boolean isChechuang() { return chechuang; }

    public void setChechuang(boolean chechuang) {
        this.chechuang = chechuang;
    }

    public boolean isShousha() { return shousha; }

    public void setShousha(boolean shousha) {
        this.shousha = shousha;
    }

    public boolean isAnquandai() { return anquandai; }

    public void setAnquandai(boolean anquandai) {
        this.anquandai = anquandai;
    }

    public boolean isYushuaqi() { return yushuaqi; }

    public void setYushuaqi(boolean yushuaqi) {
        this.yushuaqi = yushuaqi;
    }

    public boolean isCarstart() { return carstart; }
    public void setCarstart(boolean carstart) {
        this.carstart = carstart;
    }



}
