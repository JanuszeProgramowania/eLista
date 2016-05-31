/*! (c) Tom Wu | http://www-cs-students.stanford.edu/~tjw/jsbn/
 */
function parseBigInt(b, a) {
    return new BigInteger(b, a)
}
function linebrk(c, d) {
    var a = "";
    var b = 0;
    while (b + d < c.length) {
        a += c.substring(b, b + d) + "\n";
        b += d
    }
    return a + c.substring(b, c.length)
}
function byte2Hex(a) {
    if (a < 16) {
        return "0" + a.toString(16)
    } else {
        return a.toString(16)
    }
}
function pkcs1pad2(e, h) {
    if (h < e.length + 11) {
        alert("Message too long for RSA");
        return null
    }
    var g = new Array();
    var d = e.length - 1;
    while (d >= 0 && h > 0) {
        var f = e.charCodeAt(d--);
        if (f < 128) {
            g[--h] = f
        } else {
            if ((f > 127) && (f < 2048)) {
                g[--h] = (f & 63) | 128;
                g[--h] = (f >> 6) | 192
            } else {
                g[--h] = (f & 63) | 128;
                g[--h] = ((f >> 6) & 63) | 128;
                g[--h] = (f >> 12) | 224
            }
        }
    }
    g[--h] = 0;
    var b = new SecureRandom();
    var a = new Array();
    while (h > 2) {
        a[0] = 0;
        while (a[0] == 0) {
            b.nextBytes(a)
        }
        g[--h] = a[0]
    }
    g[--h] = 2;
    g[--h] = 0;
    return new BigInteger(g)
}
function oaep_mgf1_arr(c, a, e) {
    var b = "", d = 0;
    while (b.length < a) {
        b += e(String.fromCharCode.apply(String, c.concat([(d & 4278190080) >> 24, (d & 16711680) >> 16, (d & 65280) >> 8, d & 255])));
        d += 1
    }
    return b
}
var SHA1_SIZE = 20;
function oaep_pad(l, a, c) {
    if (l.length + 2 * SHA1_SIZE + 2 > a) {
        throw"Message too long for RSA"
    }
    var h = "", d;
    for (d = 0; d < a - l.length - 2 * SHA1_SIZE - 2; d += 1) {
        h += "\x00"
    }
    var e = rstr_sha1("") + h + "\x01" + l;
    var f = new Array(SHA1_SIZE);
    new SecureRandom().nextBytes(f);
    var g = oaep_mgf1_arr(f, e.length, c || rstr_sha1);
    var k = [];
    for (d = 0; d < e.length; d += 1) {
        k[d] = e.charCodeAt(d) ^ g.charCodeAt(d)
    }
    var j = oaep_mgf1_arr(k, f.length, rstr_sha1);
    var b = [0];
    for (d = 0; d < f.length; d += 1) {
        b[d + 1] = f[d] ^ j.charCodeAt(d)
    }
    return new BigInteger(b.concat(k))
}
function RSAKey() {
    this.n = null;
    this.e = 0;
    this.d = null;
    this.p = null;
    this.q = null;
    this.dmp1 = null;
    this.dmq1 = null;
    this.coeff = null
}
function RSASetPublic(b, a) {
    this.isPublic = true;
    if (typeof b !== "string") {
        this.n = b;
        this.e = a
    } else {
        if (b != null && a != null && b.length > 0 && a.length > 0) {
            this.n = parseBigInt(b, 16);
            this.e = parseInt(a, 16)
        } else {
            alert("Invalid RSA public key")
        }
    }
}
function RSADoPublic(a) {
    return a.modPowInt(this.e, this.n)
}
function RSAEncrypt(d) {
    var a = pkcs1pad2(d, (this.n.bitLength() + 7) >> 3);
    if (a == null) {
        return null
    }
    var e = this.doPublic(a);
    if (e == null) {
        return null
    }
    var b = e.toString(16);
    if ((b.length & 1) == 0) {
        return b
    } else {
        return "0" + b
    }
}
function RSAEncryptOAEP(e, d) {
    var a = oaep_pad(e, (this.n.bitLength() + 7) >> 3, d);
    if (a == null) {
        return null
    }
    var f = this.doPublic(a);
    if (f == null) {
        return null
    }
    var b = f.toString(16);
    if ((b.length & 1) == 0) {
        return b
    } else {
        return "0" + b
    }
}
RSAKey.prototype.doPublic = RSADoPublic;
RSAKey.prototype.setPublic = RSASetPublic;
RSAKey.prototype.encrypt = RSAEncrypt;
RSAKey.prototype.encryptOAEP = RSAEncryptOAEP;
RSAKey.prototype.type = "RSA";