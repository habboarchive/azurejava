package org.azure.utils;

/**
 * AzureJava,
 * Edited: 11/25/2015 (Scott Stamp).
 * (from Morgoth, thanks Jordan!)
 */
import com.netflix.governator.annotations.AutoBindSingleton;
import com.netflix.governator.annotations.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

@AutoBindSingleton
public class RSA {
    private static Logger logger = LoggerFactory.getLogger(RSA.class);
    public BigInteger Exponent; // public exponent
    public BigInteger n; // modulus
    // private key
    public BigInteger Private;
    // bools (can encrypt/decrypt?)
    public boolean Decryptable;

    public boolean Encryptable;
    @Configuration(value = "rsa.private-key")
    private String privateKey;
    @Configuration(value = "rsa.public-key")
    private String publicKey;
    @Configuration(value = "rsa.modulus")
    private String modulus;
    private boolean canEncrypt = false;
    private boolean canDecrypt = false;
    private BigInteger Zero = new BigInteger("0");

    public RSA() {

    }

    public void init() {
        n = new BigInteger("ac7f00c70bb1306c35bc55344239bca22f880bb70f8d1bcc2be360d25c4abaf8f899f9e3d4eed83ef320645f10f844639f4971c85124ae54e9b3ca76f66bc524ac3bfc81e3cb39b66a2a9d04b5cfccde118867741a10e6f3359a5a7987ef01cf3cc8098595d56ac59f3770eec06dbf4fd1227ca20b98196c7cc4ee4f5519e39d", 16);
        Exponent = new BigInteger("10001", 16);
        Private = new BigInteger("968e2f2e8bdcf687ac0b1a4f07727f5b0ba3719e30bb97c48795e91f1220c3baa4b937eb8c217faac9e7bed35eeb623d957cb40c040ec0fe2a109ba9638142ca45769a5cf68ccc3a42ec7d3aa112ad012d3d2843d9cc0c3c504ffbbbddbba3716043f65e15149935a52c1d1d71d5c589d5563784c97a91bc55e50d26ec2da2dd", 16);

        Encryptable = (n != null && n != Zero && Exponent != Zero);
        Decryptable = (Encryptable && Private != Zero && Private != null);
    }

    public int GetBlockSize() {
        return (n.bitLength() + 7) / 8;
    }

    public BigInteger DoPublic(BigInteger x) {
        if (this.Encryptable) {
            return x.modPow(new BigInteger(this.Exponent + ""), this.n);
        }

        return Zero;
    }

    public String Encrypt(String text) {
        BigInteger m = new BigInteger(this.pkcs1pad2(text.getBytes(), this.GetBlockSize()));

        if (m.equals(Zero)) {
            return null;
        }

        BigInteger c = this.DoPublic(m);

        if (c.equals(Zero)) {
            return null;
        }

        String result = c.toString(16);

        if ((result.length() & 1) == 0) {
            return result;
        }

        return "0" + result;
    }

    public String Sign(String text) {
        BigInteger m = new BigInteger(this.pkcs1pad2(text.getBytes(), this.GetBlockSize()));

        if (m.equals(Zero)) {
            return null;
        }

        BigInteger c = this.DoPrivate(m);

        if (c.equals(Zero)) {
            return null;
        }

        String result = c.toString(16);

        if ((result.length() & 1) == 0) {
            return result;
        }

        return "0" + result;
    }

    private byte[] pkcs1pad2(byte[] data, int n) {
        byte[] bytes = new byte[n];

        int i = data.length - 1;

        while (i >= 0 && n > 11) {
            bytes[--n] = data[i--];
        }

        bytes[--n] = 0;

        while (n > 2) {
            bytes[--n] = (byte) 0xFF;
        }

        bytes[--n] = (byte) 1;
        bytes[--n] = 0;

        return bytes;
    }

    public BigInteger DoPrivate(BigInteger x) {
        if (this.Decryptable) {
            return x.modPow(this.Private, this.n);
        }

        return Zero;
    }

    public String Decrypt(String ctext) {
        BigInteger c = new BigInteger(ctext, 16);
        BigInteger m = this.DoPrivate(c);

        if (m.equals(Zero)) {
            return null;
        }

        byte[] bytes = this.pkcs1unpad2(m, this.GetBlockSize());

        if (bytes == null) {
            return null;
        }

        return new String(bytes);
    }

    /**
     * @param src
     * @param n
     * @return
     */
    private byte[] pkcs1unpad2(BigInteger src, int n) {
        byte[] bytes = src.toByteArray();
        byte[] out;
        int i = 0;

        while (i < bytes.length && bytes[i] == 0) {
            ++i;
        }

        if (bytes.length - i != n - 1 || bytes[i] > 2) {
            return null;
        }

        ++i;

        while (bytes[i] != 0) {
            if (++i >= bytes.length) {
                return null;
            }
        }

        out = new byte[(bytes.length - i) + 1];
        int p = 0;

        while (++i < bytes.length) {
            out[p++] = (bytes[i]);
        }

        return out;
    }
}