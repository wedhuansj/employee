package a.employee.utility;
import a.employee.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
@Service
public class ValidationUtility {
    public boolean checkValid(String s) {
        return s.trim().isEmpty();
    }
    public boolean checkGen(String s) {
        return s.equalsIgnoreCase("nam") || s.equalsIgnoreCase("nữ");
    }
    public boolean checkNum(int x) {
        try {
            int a = Integer.parseInt(String.valueOf(x));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    public boolean checkPhone(String s) {
        if (s == null)
            return false;
        String regex = "^(0|\\+84)(3[2-9]|5[25689]|7[06-9]|8[1-689]|9[0-46-9])[0-9]{7}$";
        return Pattern.matches(regex, s);
    }
    public boolean checkSal(Double a) {
        return a <= 0;
    }
    public boolean checkType(int type) {
        return type == 1 || type == 2;
    }
    public boolean checkEmail(String s) {
        if (s == null)
            return false;
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return s.matches(regex);
    }
}
