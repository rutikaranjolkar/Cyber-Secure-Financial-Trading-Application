/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.kamranzafar.otp.OTP;

/**
 *
 * @author Rutika Ranjolkar
 */
public class OTPUtils {

    public static String generateKey(String salt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String seed = sdf.format(new Date()) + salt;
        String count = getMinuteOfDay() + "";
        return OTP.generate(seed, count, 6, "hotp");
    }

    public static int getMinuteOfDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        int min = 60 * calendar.get(Calendar.HOUR_OF_DAY)
                + calendar.get(Calendar.MINUTE);
        return min;
    }

    public static int getSecond() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }
}
