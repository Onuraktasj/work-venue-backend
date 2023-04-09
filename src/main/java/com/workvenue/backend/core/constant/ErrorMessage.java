package com.workvenue.backend.core.constant;

public class ErrorMessage {

    public static class GeneralError {
        public final static String UNEXPECTED_ERROR = "Beklenmedik bir hata oluştu.";
        public final static String SYSTEM_ERROR = "Sistemsel bir hata oluştu."; //TODO: hata yakalanamazsa bu kullanılacak.
    }

    public static class VisitorError {
        public final static String GET_USER_NULL_ERROR = "Herhangi bir kullanıcı bulunamadı.";
        public final static String USER_ALREADY_SAVED = "Bu mail adresi zaten kayıtlı.";
        public final static String EMAIL_NOT_FOUND = "Bu mail adresiyle kayıtlı kullanıcı bulunamadı.";
        public static final String GET_VENUE_NULL_ERROR = "Herhangi bir mekan bulunamadı.";

        public static final String GET_VENUE_BY_NAME_ERROR = "Belirtilen isimde de bir mekan bulunamadı.";
    }


}
