package utils;

import exceptions.ValidateException;

import java.time.LocalDate;
import java.time.Period;

public class ValidateUtil {

    public static void checkServiceId(String id, String type) throws ValidateException {
        if (type.equals("VILLA")) {
            if (!id.matches("^SVVL-{4}$")) throw new ValidateException("dịch vụ phải svro-yyyy");
        } else if (type.equals("ROOM")) {
            if (!id.matches("^SVRO-{4}$")) throw new ValidateException("dịch vụ phải svro-yyyy");
        }
    }
    public static void checkServiceName(String name) throws ValidateException {
        if (!name.matches("^[A-Z][a-z0-9 ]*$"))
            throw new ValidateException("eeen phải đ");
    }
    public static void checkMaxPeople(int n) throws ValidateException {
        if (n <= 0 || n >= 20)
            throw new ValidateException("people phải ừ 10 đến 20");
    }

    public static void checkArea(double n) throws ValidateException {
        if (n <= 30)
            throw new ValidateException("khu vực max 30");
    }

    public static void checkFloor(int n) throws ValidateException {
        if (n <= 0)
            throw new ValidateException("phải trên 0 nhá");
    }

    public static void checkBirthday(String ddmmyyyy) throws ValidateException {
        try {
            LocalDate dob = DateUtil.parse(ddmmyyyy);
            LocalDate now = LocalDate.now();
            if (dob.isAfter(now))
                throw new ValidateException("không được sinh trong tương lai");
            int age = Period.between(dob, now).getYears();
            if (age < 18)
                throw new ValidateException("ít nhất 18  tuổi");
            if (age > 100)
                throw new ValidateException("tổi phỉa dưới <= 100");
        } catch (ValidateException e) {
            throw e;
        } catch (Exception e) {
            throw new ValidateException("sinh nhật phải dd/MM/yyyy");
        }
    }
}
