package org.fisco.bcos.asset.crypto.signature;
import org.fisco.bcos.asset.crypto.signature.utils.Base64;
import org.fisco.bcos.asset.crypto.signature.utils.BinaryAscii;
import org.fisco.bcos.asset.crypto.signature.utils.ByteString;
import org.fisco.bcos.asset.crypto.signature.utils.Der;
import java.io.IOException;
import java.math.BigInteger;


public class Signature {

    public BigInteger r;
    public BigInteger s;

    /**
     *
     * @param r r
     * @param s s
     */
    public Signature(BigInteger r, BigInteger s) {
        this.r = r;
        this.s = s;
    }

    /**
     *
     * @return ByteString
     */
    public ByteString toDer() {
        return Der.encodeSequence(Der.encodeInteger(r), Der.encodeInteger(s));
    }

    /**
     *
     * @return String
     */
    public String toBase64() {
        return Base64.encodeBytes(toDer().getBytes());
    }

    /**
     *
     * @param string byteString
     * @return Signature
     */
    public static Signature fromDer(ByteString string) {
        ByteString[] str = Der.removeSequence(string);
        ByteString rs = str[0];
        ByteString empty = str[1];
        if (!empty.isEmpty()) {
            throw new RuntimeException(String.format("trailing junk after DER sig: %s", BinaryAscii.hexFromBinary(empty)));
        }
        Object[] o = Der.removeInteger(rs);
        BigInteger r = new BigInteger(o[0].toString());
        ByteString rest = (ByteString) o[1];
        o = Der.removeInteger(rest);
        BigInteger s = new BigInteger(o[0].toString());
        empty = (ByteString) o[1];
        if (!empty.isEmpty()) {
            throw new RuntimeException(String.format("trailing junk after DER numbers: %s", BinaryAscii.hexFromBinary(empty)));
        }
        return new Signature(r, s);
    }

    /**
     *
     * @param string byteString
     * @return Signature
     */
    public static Signature fromBase64(ByteString string) {
        ByteString der = null;
        try {
            der = new ByteString(Base64.decode(string.getBytes()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Corrupted base64 string! Could not decode base64 from it");
        }
        return fromDer(der);
    }
}
