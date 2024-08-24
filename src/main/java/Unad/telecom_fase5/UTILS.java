package Unad.telecom_fase5;

public class UTILS {

    public  static String TOKEN_OT ="APP_USR-910859472954590-080210-00c6984d4f718add7cce9bdc88abe143-280720499";
    public  static String TOKEN_VANE ="APP_USR-2625059277787645-041123-ea665332ba486bda3a192d3455a33696-1188679528";

    public  static String TOKEN_SL18 ="APP_USR-2593561135856099-082416-9bab61fe752d7e138008c81c8d32b330-1958875497";

    public static String KEY_VERIFICA;
    public static String TOKEN_MERCADO;

    public static String getKeyVerifica() {
        KEY_VERIFICA="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnRJZCI6IjY2Y2E0ZTFlYzcxYTdkMDAwMzYyZmVkOCIsInAiOiJ2ayIsIkpXVFBocmFzZSI6IjY2Y2E0ZGQ3MDdiMGM5NDVkN2U5YTFjZSIsImV4cGlyZXNBdCI6MTcyNzIxMjcwMywiaWF0IjoxNzI0NTM0MzAzfQ.eW8DDV4lyFfg2OLGDZZqEMT-JPL30QJVBADYjN__baQ";
        return KEY_VERIFICA;
    }



    public static String getTokenMercado() {
        TOKEN_MERCADO=TOKEN_SL18;
        return TOKEN_MERCADO;
    }

}
