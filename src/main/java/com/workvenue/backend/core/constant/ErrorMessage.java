package com.workvenue.backend.core.constant;

import lombok.experimental.UtilityClass;

public class ErrorMessage {

    @UtilityClass
    public class GeneralError {
        public final static String UNEXPECTED_ERROR = "Beklenmedik bir hata oluştu.";
        public final static String SYSTEM_ERROR = "Sistemsel bir hata oluştu.";
        public final static String EMPTY_LIST_ERROR = "Herhangi bir veri bulunamadı.";
        public final static String NULL_OBJECT_ERROR = "Herhangi bir nesne bulunamadı.";
    }

    @UtilityClass
    public class VisitorError {
        public final static String SAVE_USER_ERROR = "Kullanıcı kaydedilemedi.";
        public final static String GET_ALL_USER_NULL_ERROR = "Herhangi bir kullanıcı bulunamadı.";
        public final static String GET_USER_NULL_ERROR = "Belirtilen kullanıcı bulunamadı.";
        public final static String USER_ALREADY_SAVED = "Bu mail adresi zaten kayıtlı.";
        public final static String EMAIL_NOT_FOUND = "Bu mail adresiyle kayıtlı kullanıcı bulunamadı.";
    }

    @UtilityClass
    public class VenueError {
        public static final String GET_VENUE_NULL_ERROR = "Herhangi bir mekan bulunamadı.";
        public static final String GET_VENUE_BY_NAME_ERROR = "Belirtilen isimde de bir mekan bulunamadı.";
    }
}
