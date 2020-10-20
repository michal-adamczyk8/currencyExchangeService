package com.currency.app.domain.user.service.validation;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;

@Service
public class PeselValidation {

    private static final DecimalFormat numFormat = new DecimalFormat("00");

    public boolean isChecksumValid(String pesel) {
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;

        int csum = Integer.parseInt(pesel.substring(10, 11));
        for (int i = 0; i < 10; i++) {
            char c = pesel.charAt(i);
            sum += weights[i] * Integer.parseInt(String.valueOf(c));
        }
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;
        return (sum == csum);
    }

    public boolean isUserOver18YearsOld(String pesel) {
        LocalDate userBirthDay = getBirthDate(pesel);
        LocalDate nowDate = LocalDate.now();
        Period period = Period.between(userBirthDay, nowDate);
        return period.getYears() >= 18;
    }

    private LocalDate getBirthDate(String pesel) {
        return LocalDate.of(getBirthYear(pesel), getBirthMonth(pesel), getBirthDay(pesel));
    }

    private Integer getBirthYear(String pesel) {
        int year = 10 * getDigitFromPos(pesel, 0);
        year += getDigitFromPos(pesel, 1);
        int month = 10 * getDigitFromPos(pesel, 2);
        month += getDigitFromPos(pesel, 3);
        // normalize year
        if (month > 80 && month < 93) {
            year += 1800;
        } else if (month > 0 && month < 13) {
            year += 1900;
        } else if (month > 20 && month < 33) {
            year += 2000;
        } else if (month > 40 && month < 53) {
            year += 2100;
        } else if (month > 60 && month < 73) {
            year += 2200;
        }
        return year;
    }

    private Integer getBirthMonth(String pesel) {
        int month = 10 * getDigitFromPos(pesel, 2);
        month += getDigitFromPos(pesel, 3);
        if (month > 80 && month < 93) {
            month -= 80;
        } else if (month > 20 && month < 33) {
            month -= 20;
        } else if (month > 40 && month < 53) {
            month -= 40;
        } else if (month > 60 && month < 73) {
            month -= 60;
        }
        return Integer.valueOf(numFormat.format(month));
    }

    private Integer getBirthDay(String pesel) {
        int day = 10 * getDigitFromPos(pesel, 4);
        day += getDigitFromPos(pesel, 5);
        return Integer.valueOf(numFormat.format(day));
    }

    private static int getDigitFromPos(String pesel, Integer pos) {
        return Integer.parseInt(pesel.substring(pos, pos + 1));
    }


}
