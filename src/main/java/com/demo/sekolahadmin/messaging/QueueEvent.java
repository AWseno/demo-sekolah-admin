package com.demo.sekolahadmin.messaging;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueueEvent {

    public static final String EXCHANGE = "sekolah.admin";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Admin {
        public static final String INACTIVE = "sekolah.admin.inactive";
        public static final String REGISTER = "sekolah.admin.register";

    }

}
