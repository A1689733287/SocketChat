package com.gpg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {
	// 字符串进行加密的算法的名称
	public static final String ALGORITHM = "RSA";
	// 字符串进行加密填充的名称
	public static final String PADDING = "RSA/NONE/NoPadding";
	// 字符串持有安全提供者的名称
	public static final String PROVIDER = "BC";
	// 私匙文件路径 （类文件的路径）
	public static final String PRIVATE_KEY_FILE = RSAUtil.class.getClassLoader().getResource("").getPath()
			+ "private_response_key_1.key";
	// 公匙文件路径（类文件的路径）
	public static final String PUBLIC_KEY_FILE = RSAUtil.class.getClassLoader().getResource("").getPath()
			+ "public_request_key_1.key";

	/**
	 * 静态类中加载提供者
	 */
	static {
		// 需要导入bcprov.jar包
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}

	/**
	 * 利用公钥进行加密
	 * 
	 * @param text
	 *            需要加密的字符
	 * @return 返回加密后的字符
	 */
	public static String encrypt(String text) {
		if (!areKeyPresent()) {
			generateKey();
		}
		String cipherTextBase64 = "";
		try {
			// 获取对象流
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
			// 通过对象流获取公钥
			PublicKey publicKey = (PublicKey) inputStream.readObject();
			// 初始化密码对象
			Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);
			// 使用Cipher.ENCRYPT_MODE的加密模式
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			// 进行最终的加密
			byte[] cipherText = cipher.doFinal(text.getBytes());
			//使用Base64进行解码，最终形成加密后的字符串
			cipherTextBase64 = Base64.getEncoder().encodeToString(cipherText);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return cipherTextBase64;
	}

	/**
	 * 利用私钥进行解密
	 * 
	 * @param text
	 *            需要解密的字符
	 * @return 解密后的字符
	 */
	public static String decrypt(String text) {
		if (!areKeyPresent()) {
			generateKey();
		}
		byte[] dectypterText = null;
		try {
			//使用对象流读取对象
			ObjectInput input = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
			// 读取私钥
			PrivateKey privateKey = (PrivateKey) input.readObject();
			// 获取到密码对象
			Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);
			// 使用解密模式进行解密
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			// 使用Base64进行解码
			byte[] str = Base64.getDecoder().decode(text);
			// 得到最终的解密字符
			dectypterText = cipher.doFinal(str);
			input.close();
		} catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchProviderException
				| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | IOException e) {
			e.printStackTrace();
		}
		return new String(dectypterText);
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @return
	 */
	public static boolean areKeyPresent() {
		File privateKey = new File(PRIVATE_KEY_FILE);
		File publicKey = new File(PUBLIC_KEY_FILE);
		if (privateKey.exists() & publicKey.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * 生成公匙和私匙
	 */
	public static void generateKey() {
		try {
			// 得到密钥对的生成对象
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER);
			keyGen.initialize(1024);
			// 得到密钥对对象
			KeyPair keyPair = keyGen.generateKeyPair();
			// 创建文件对象
			File privateKeyFile = new File(PRIVATE_KEY_FILE);
			File publicKeyFile = new File(PUBLIC_KEY_FILE);
			if (privateKeyFile.getParentFile() != null) {
				privateKeyFile.getParentFile().mkdirs();
			}
			privateKeyFile.createNewFile();
			if (publicKeyFile.getParentFile() != null) {
				publicKeyFile.getParentFile().mkdirs();
			}
			publicKeyFile.createNewFile();
			
			ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
			//将生成的公钥写出到文件
			publicKeyOS.writeObject(keyPair.getPublic());
			publicKeyOS.close();
			ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
			//将生成的私钥写出到文件
			privateKeyOS.writeObject(keyPair.getPrivate());
			// 关闭流对象
			privateKeyOS.close();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 如果文件不存在生成秘钥和公钥
		if (!areKeyPresent()) {
			generateKey();
		}
		String encrypt = encrypt("123456");
		System.out.println(encrypt);
		System.out.println(decrypt(encrypt));
	}
}
