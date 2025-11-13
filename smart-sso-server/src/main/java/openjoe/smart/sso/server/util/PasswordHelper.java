package openjoe.smart.sso.server.util;

import openjoe.smart.sso.server.enums.ErrorCodeEnum;
import openjoe.smart.sso.server.stage.exception.ApplicationException;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordHelper {

	private static final String SALT = "`1qazx";


	public static String encrypt(String password) {
		if (!StringUtils.hasLength(password)) {
			throw new ApplicationException(ErrorCodeEnum.E1001);
		}
		try {
			return md5(new StringBuilder(password).append(SALT).toString());
		}
		catch (Exception e) {
			throw new ApplicationException(ErrorCodeEnum.E1002);
		}
	}

	private static String md5(String str) {
		String password = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			
			password = new BigInteger(1, md.digest()).toString(16);
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}

	public static void main(String[] args) {
        System.err.println("Encrypted        :" + encrypt("123456"));
    }
}
