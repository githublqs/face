package com.face.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 加密辅助类---（单向加密）
 * 
 * 单向散列算法： 属于摘要算法，不是一种加密算法，作用是把任意长的输入消息串变化成固定长的输出串的一种函数
 * BASE64（严格地说，属于编码格式，而非加密算法） MD5（Message Digest algorithm 5，信息摘要算法） SHA（Secure
 * Hash Algorithm，安全散列算法） HMAC（Hash Message Authentication Code，散列消息鉴别码）
 * CRC（Cyclical Redundancy Check，循环冗余码校验）
 * 
 * 
 * 加密：加密是一种以密码方式发送信息的方法。只有拥有正确密钥的人才能解开这个信息的密码。对于其他人来说，这个信息看起来就像是一系列随机的字母、数字和符号。
 * 如果你要发送不应该让其他人看的敏感信息时，加密是特别重要的。
 * 
 * 数字签名：数字签名是一种类似写在纸上的普通的物理签名，但是使用了公钥加密领域的技术实现，用于鉴别数字信息的方法。一套数字签名通常定义两种互补的运算，
 * 一个用于签名，另一个用于验证。
 * 
 * 加密与数字签名的区别 加密同数字签名一样，公共密钥加密使用PGP等软件，使用数学算法转换信息并且依靠公共和专用密钥。但是，加密和数字签名是有区别的，
 * 加密的目的是通过把信息翻译成密码秘密地隐藏内容
 * 。数字签名的目的是完整性和身份识别性，验证一个信息的发送者和指出内容没有被修改。虽然加密和数字签名能够单独使用，但是，你还可以对加密的信息采用数字签名。
 * 
 * 当你签署一个信息时，你使用你的专用密钥，任何有你的公共密钥的人都能够验证这个签名是合法的。当你加密一个信息的时候，你为接收你的信息的人使用这个公共密钥，
 * 并且使用他或者她的专用密钥解码这个信息。用于人们要保持自己的专用密钥的机密，并且使用口令保护这些密钥，这个信息的接收者应该是惟一的能够观看这个信息的人。
 * 
 * @author ly
 * 
 */
public class SingleEncrypUtil {
	/**
	 * 获得一个MD5加密32位的（普通的）
	 * 
	 * MD5 即Message-Digest Algorithm 5（信息-摘要算法
	 * 5），用于确保信息传输完整一致。是计算机广泛使用的杂凑算法之一（又译摘要算法
	 * 、哈希算法），主流编程语言普遍已有MD5实现。将数据（如汉字）运算为另一固定长度值
	 * ，是杂凑算法的基础原理，MD5的前身有MD2、MD3和MD4。MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被
	 * "压缩"成一种保密的格式（就是把一个任意长度的字节串变换成一定长的十六进制数字串）。
	 * 除了MD5以外，其中比较有名的还有sha-1、RIPEMD以及Haval等
	 * 
	 * @return
	 */
	public byte[] getMD5Plain(String plainText) {
		// 根据MD5算法生成MessageDigest对象
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		byte[] srcBytes = plainText.getBytes();
		// 使用srcBytes更新摘要
		md5.update(srcBytes);
		// 完成哈希计算，得到result
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}

	/**
	 * 再次加密
	 * 
	 * @param inStr
	 *            传入加密字符串
	 * @param inChar
	 *            传入加密的字符
	 * @return
	 */
	public static String encrypt(String inStr, char inChar) {
		// String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ inChar);
		}
		String encrypt = new String(a);
		return encrypt;
	}

	/**
	 * 将再次加密的进行解密
	 * 
	 * @param inStr
	 *            传入解密字符串
	 * @param inChar
	 *            传入解密的字符
	 * @return
	 */
	public static String decrypt(String inStr, char inChar) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ inChar);
		}
		String decrypt = new String(a);
		return decrypt;
	}

	/**
	 * 将字节数组转化为16进制
	 * 
	 * @param bytes
	 * @return
	 */
	public String getHexString(byte[] bytes) {
		// * signum 参数表示符号值，1表示正数，-1表示负号
		// *
		// * 这个函数的原理为 byte[]bytes={50}
		// * 50 所对应的2进制为 ‭00110010‬
		// * BigInteger bigint=new BigInteger(1,bytes);
		// * bigint.toString()=50 //输出的默认为十进制
		// * byte[]bytes={50,50}
		// * 所对应的二进制连接起来为0011001000110010
		// * 这个连接起来的数对应的十进制为12850
		// * 所以bigint.toString()=12850 //输出的默认为十进制
		// *signum 参数表示符号值，1表示正数，-1表示负号---并将
		return new BigInteger(1, bytes).toString(16);
	}

	/**
	 * 获取一个唯一的md5加密字节数组
	 * 
	 * @param plainText
	 *            传入一个字符串
	 * @return
	 * 
	 * 
	 * 
	 *         MD5 即Message-Digest Algorithm 5（信息-摘要算法
	 *         5），用于确保信息传输完整一致。是计算机广泛使用的杂凑算法之一
	 *         （又译摘要算法、哈希算法），主流编程语言普遍已有MD5实现。将数据（如汉字
	 *         ）运算为另一固定长度值，是杂凑算法的基础原理，MD5的前身有MD2
	 *         、MD3和MD4。MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被
	 *         "压缩"成一种保密的格式（就是把一个任意长度的字节串变换成一定长的十六进制数字串）。
	 *         除了MD5以外，其中比较有名的还有sha-1、RIPEMD以及Haval等
	 */
	public byte[] getMD5Sole(String plainText) {
		Random ra = new Random();
		plainText = plainText + System.currentTimeMillis() + ra.nextLong();
		try {
			// 根据MD5算法生成MessageDigest对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用srcBytes更新摘要
			md.update(plainText.getBytes());
			// 完成哈希计算，得到result
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取一个SHA加密字节数组
	 * 
	 * @param plainText
	 * @return
	 * 
	 *         SHA是一种数据加密算法，该算法经过加密专家多年来的发展和改进已日益完善，现在已成为公认的最安全的散列算法之一，并被广泛使用。
	 *         该算法的思想是接收一段明文
	 *         ，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息）
	 *         ，并把它们转化为长度较短、
	 *         位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。散列函数值可以说是对明文的一种“指纹”或是
	 *         “摘要”所以对散列值的数字签名就可以视为对此明文的数字签名。
	 * 
	 *         安全散列算法SHA（Secure Hash Algorithm，SHA)是美国国家标准技术研究所发布的国家标准FIPS PUB
	 *         180，最新的标准已经于2008年更新到FIPS PUB
	 *         180-3。其中规定了SHA-1，SHA-224，SHA-256，SHA-
	 *         384，和SHA-512这几种单向散列算法。SHA-1，SHA
	 *         -224和SHA-256适用于长度不超过2^64二进制位的消息。SHA
	 *         -384和SHA-512适用于长度不超过2^128二进制位的消息。
	 */
	public byte[] getSHAPlain(String plainText) {
		try {
			// 根据SHA算法生成MessageDigest对象
			MessageDigest md = MessageDigest.getInstance("SHA");
			// 使用srcBytes更新摘要
			md.update(plainText.getBytes());
			// 完成哈希计算，得到result
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取一个唯一的SHA加密字节数组
	 * 
	 * @param plainText
	 * @return
	 * 
	 *         SHA是一种数据加密算法，该算法经过加密专家多年来的发展和改进已日益完善，现在已成为公认的最安全的散列算法之一，并被广泛使用。
	 *         该算法的思想是接收一段明文
	 *         ，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息）
	 *         ，并把它们转化为长度较短、
	 *         位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。散列函数值可以说是对明文的一种“指纹”或是
	 *         “摘要”所以对散列值的数字签名就可以视为对此明文的数字签名。
	 * 
	 *         安全散列算法SHA（Secure Hash Algorithm，SHA)是美国国家标准技术研究所发布的国家标准FIPS PUB
	 *         180，最新的标准已经于2008年更新到FIPS PUB
	 *         180-3。其中规定了SHA-1，SHA-224，SHA-256，SHA-
	 *         384，和SHA-512这几种单向散列算法。SHA-1，SHA
	 *         -224和SHA-256适用于长度不超过2^64二进制位的消息。SHA
	 *         -384和SHA-512适用于长度不超过2^128二进制位的消息。
	 */
	public byte[] getSHASole(String plainText) {
		Random ra = new Random();
		plainText = plainText + System.currentTimeMillis() + ra.nextLong();
		try {
			// 根据SHA算法生成MessageDigest对象
			MessageDigest md = MessageDigest.getInstance("SHA");
			// 使用srcBytes更新摘要
			md.update(plainText.getBytes());
			// 完成哈希计算，得到result
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
}
