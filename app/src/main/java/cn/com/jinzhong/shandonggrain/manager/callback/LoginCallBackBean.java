package cn.com.jinzhong.shandonggrain.manager.callback;

import java.util.List;

/**
 * Created by ${sheldon} on 2017/7/11.
 */

public class LoginCallBackBean {

    /**
     * code : 200
     * msg : 13065825578
     * resultlist : [{"ChuangJianShiJian":"2017-07-11T12:45:24.447","DanWeiMingCheng":"王建","ID":14695,"ID_ChuangJianZhe":0,"ID_DiQu":0,"ID_ShangJiDanWei":0,"ID_YongHuLeiXing":9,"ID_ZhiShuDanWei":0,"KouLing":"e10adc3949ba59abbe56e057f20f883e","LastLogin":"2017-07-11T14:16:01.603","PaiXuHao":0,"ShunXuHao":0,"State":0,"YongHuMing":"371424199510031520"}]
     */

    private int code;
    private String msg;
    private List<ResultlistBean> resultlist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultlistBean> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<ResultlistBean> resultlist) {
        this.resultlist = resultlist;
    }

    public static class ResultlistBean {
        /**
         * ChuangJianShiJian : 2017-07-11T12:45:24.447
         * DanWeiMingCheng : 王建
         * ID : 14695
         * ID_ChuangJianZhe : 0
         * ID_DiQu : 0
         * ID_ShangJiDanWei : 0
         * ID_YongHuLeiXing : 9
         * ID_ZhiShuDanWei : 0
         * KouLing : e10adc3949ba59abbe56e057f20f883e
         * LastLogin : 2017-07-11T14:16:01.603
         * PaiXuHao : 0
         * ShunXuHao : 0
         * State : 0
         * YongHuMing : 371424199510031520
         */

        private String ChuangJianShiJian;
        private String DanWeiMingCheng;
        private int ID;
        private int ID_ChuangJianZhe;
        private int ID_DiQu;
        private int ID_ShangJiDanWei;
        private int ID_YongHuLeiXing;
        private int ID_ZhiShuDanWei;
        private String KouLing;
        private String LastLogin;
        private int PaiXuHao;
        private int ShunXuHao;
        private int State;
        private String YongHuMing;

        public String getChuangJianShiJian() {
            return ChuangJianShiJian;
        }

        public void setChuangJianShiJian(String ChuangJianShiJian) {
            this.ChuangJianShiJian = ChuangJianShiJian;
        }

        public String getDanWeiMingCheng() {
            return DanWeiMingCheng;
        }

        public void setDanWeiMingCheng(String DanWeiMingCheng) {
            this.DanWeiMingCheng = DanWeiMingCheng;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getID_ChuangJianZhe() {
            return ID_ChuangJianZhe;
        }

        public void setID_ChuangJianZhe(int ID_ChuangJianZhe) {
            this.ID_ChuangJianZhe = ID_ChuangJianZhe;
        }

        public int getID_DiQu() {
            return ID_DiQu;
        }

        public void setID_DiQu(int ID_DiQu) {
            this.ID_DiQu = ID_DiQu;
        }

        public int getID_ShangJiDanWei() {
            return ID_ShangJiDanWei;
        }

        public void setID_ShangJiDanWei(int ID_ShangJiDanWei) {
            this.ID_ShangJiDanWei = ID_ShangJiDanWei;
        }

        public int getID_YongHuLeiXing() {
            return ID_YongHuLeiXing;
        }

        public void setID_YongHuLeiXing(int ID_YongHuLeiXing) {
            this.ID_YongHuLeiXing = ID_YongHuLeiXing;
        }

        public int getID_ZhiShuDanWei() {
            return ID_ZhiShuDanWei;
        }

        public void setID_ZhiShuDanWei(int ID_ZhiShuDanWei) {
            this.ID_ZhiShuDanWei = ID_ZhiShuDanWei;
        }

        public String getKouLing() {
            return KouLing;
        }

        public void setKouLing(String KouLing) {
            this.KouLing = KouLing;
        }

        public String getLastLogin() {
            return LastLogin;
        }

        public void setLastLogin(String LastLogin) {
            this.LastLogin = LastLogin;
        }

        public int getPaiXuHao() {
            return PaiXuHao;
        }

        public void setPaiXuHao(int PaiXuHao) {
            this.PaiXuHao = PaiXuHao;
        }

        public int getShunXuHao() {
            return ShunXuHao;
        }

        public void setShunXuHao(int ShunXuHao) {
            this.ShunXuHao = ShunXuHao;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public String getYongHuMing() {
            return YongHuMing;
        }

        public void setYongHuMing(String YongHuMing) {
            this.YongHuMing = YongHuMing;
        }
    }
}
