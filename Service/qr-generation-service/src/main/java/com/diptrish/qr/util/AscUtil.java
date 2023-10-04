package com.diptrish.qr.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diptrish.qr.config.PropertyConstants;

@Service
public class AscUtil {

	@Autowired
	private PropertyConstants propertyCons;

	public String generateHexKey(int keySizeInBytes) {
		SecureRandom secureRandom = new SecureRandom();
		byte[] keyBytes = new byte[keySizeInBytes];
		secureRandom.nextBytes(keyBytes);

		StringBuilder hexKey = new StringBuilder();
		for (byte b : keyBytes) {
			hexKey.append(String.format("%02X", b));
		}

		return hexKey.toString();
	}

	public SecretKey generateAESKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256); // You can choose key size (128, 192, or 256)
		return keyGenerator.generateKey();
	}

	public String encrypt(byte[] key, byte[] initVector, String value) {
		byte[] encrypted = null;
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector);
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			encrypted = cipher.doFinal(completeBlocks(value));
			return Base64.getEncoder().encodeToString(encrypted);

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return null;
	}

	public String decrypt(byte[] key, byte[] initVector, String value) {
		byte[] encrypted = null;
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector);
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			return new String(cipher.doFinal(Base64.getDecoder().decode(value)));
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return null;
	}

	public byte[] completeBlocks(String message) {
		try {

			int bytesLenght = message.getBytes("UTF-8").length;
			if (bytesLenght % 16 != 0) {
				byte[] newArray = new byte[bytesLenght + (16 - (bytesLenght % 16))];
				System.arraycopy(message.getBytes(), 0, newArray, 0, bytesLenght);
				return newArray;
			}

			return message.getBytes("UTF-8");

		} catch (UnsupportedEncodingException ex) {
			System.out.println("" + ex);
		}
		return null;
	}

	public String getEncryptValue(String value) {
		String encrypt = value;
		final byte[] initVector = new byte[16];
		String key = propertyCons.getAscKey();

		if (StringUtils.isNotBlank(value)) {
			encrypt = encrypt(new BigInteger(key, 16).toByteArray(), initVector, value.trim()).trim();
		}
		return encrypt;
	}

	public String getDecryptValue(String value) {
		String decrypt = value;
		final byte[] initVector = new byte[16];
		String key = propertyCons.getAscKey();

		if (StringUtils.isNotBlank(value)) {
			decrypt = decrypt(new BigInteger(key, 16).toByteArray(), initVector, value.trim()).trim();
		}

		return decrypt;
	}

}
