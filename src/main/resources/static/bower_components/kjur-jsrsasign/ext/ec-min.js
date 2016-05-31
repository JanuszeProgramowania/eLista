/*! (c) Tom Wu | http://www-cs-students.stanford.edu/~tjw/jsbn/
 */
function ECFieldElementFp(b, a) {
    this.x = a;
    this.q = b
}
function feFpEquals(a) {
    if (a == this) {
        return true
    }
    return (this.q.equals(a.q) && this.x.equals(a.x))
}
function feFpToBigInteger() {
    return this.x
}
function feFpNegate() {
    return new ECFieldElementFp(this.q, this.x.negate().mod(this.q))
}
function feFpAdd(a) {
    return new ECFieldElementFp(this.q, this.x.add(a.toBigInteger()).mod(this.q))
}
function feFpSubtract(a) {
    return new ECFieldElementFp(this.q, this.x.subtract(a.toBigInteger()).mod(this.q))
}
function feFpMultiply(a) {
    return new ECFieldElementFp(this.q, this.x.multiply(a.toBigInteger()).mod(this.q))
}
function feFpSquare() {
    return new ECFieldElementFp(this.q, this.x.square().mod(this.q))
}
function feFpDivide(a) {
    return new ECFieldElementFp(this.q, this.x.multiply(a.toBigInteger().modInverse(this.q)).mod(this.q))
}
ECFieldElementFp.prototype.equals = feFpEquals;
ECFieldElementFp.prototype.toBigInteger = feFpToBigInteger;
ECFieldElementFp.prototype.negate = feFpNegate;
ECFieldElementFp.prototype.add = feFpAdd;
ECFieldElementFp.prototype.subtract = feFpSubtract;
ECFieldElementFp.prototype.multiply = feFpMultiply;
ECFieldElementFp.prototype.square = feFpSquare;
ECFieldElementFp.prototype.divide = feFpDivide;
function ECPointFp(c, a, d, b) {
    this.curve = c;
    this.x = a;
    this.y = d;
    if (b == null) {
        this.z = BigInteger.ONE
    } else {
        this.z = b
    }
    this.zinv = null
}
function pointFpGetX() {
    if (this.zinv == null) {
        this.zinv = this.z.modInverse(this.curve.q)
    }
    return this.curve.fromBigInteger(this.x.toBigInteger().multiply(this.zinv).mod(this.curve.q))
}
function pointFpGetY() {
    if (this.zinv == null) {
        this.zinv = this.z.modInverse(this.curve.q)
    }
    return this.curve.fromBigInteger(this.y.toBigInteger().multiply(this.zinv).mod(this.curve.q))
}
function pointFpEquals(a) {
    if (a == this) {
        return true
    }
    if (this.isInfinity()) {
        return a.isInfinity()
    }
    if (a.isInfinity()) {
        return this.isInfinity()
    }
    var c, b;
    c = a.y.toBigInteger().multiply(this.z).subtract(this.y.toBigInteger().multiply(a.z)).mod(this.curve.q);
    if (!c.equals(BigInteger.ZERO)) {
        return false
    }
    b = a.x.toBigInteger().multiply(this.z).subtract(this.x.toBigInteger().multiply(a.z)).mod(this.curve.q);
    return b.equals(BigInteger.ZERO)
}
function pointFpIsInfinity() {
    if ((this.x == null) && (this.y == null)) {
        return true
    }
    return this.z.equals(BigInteger.ZERO) && !this.y.toBigInteger().equals(BigInteger.ZERO)
}
function pointFpNegate() {
    return new ECPointFp(this.curve, this.x, this.y.negate(), this.z)
}
function pointFpAdd(l) {
    if (this.isInfinity()) {
        return l
    }
    if (l.isInfinity()) {
        return this
    }
    var p = l.y.toBigInteger().multiply(this.z).subtract(this.y.toBigInteger().multiply(l.z)).mod(this.curve.q);
    var o = l.x.toBigInteger().multiply(this.z).subtract(this.x.toBigInteger().multiply(l.z)).mod(this.curve.q);
    if (BigInteger.ZERO.equals(o)) {
        if (BigInteger.ZERO.equals(p)) {
            return this.twice()
        }
        return this.curve.getInfinity()
    }
    var j = new BigInteger("3");
    var e = this.x.toBigInteger();
    var n = this.y.toBigInteger();
    var c = l.x.toBigInteger();
    var k = l.y.toBigInteger();
    var m = o.square();
    var i = m.multiply(o);
    var d = e.multiply(m);
    var g = p.square().multiply(this.z);
    var a = g.subtract(d.shiftLeft(1)).multiply(l.z).subtract(i).multiply(o).mod(this.curve.q);
    var h = d.multiply(j).multiply(p).subtract(n.multiply(i)).subtract(g.multiply(p)).multiply(l.z).add(p.multiply(i)).mod(this.curve.q);
    var f = i.multiply(this.z).multiply(l.z).mod(this.curve.q);
    return new ECPointFp(this.curve, this.curve.fromBigInteger(a), this.curve.fromBigInteger(h), f)
}
function pointFpTwice() {
    if (this.isInfinity()) {
        return this
    }
    if (this.y.toBigInteger().signum() == 0) {
        return this.curve.getInfinity()
    }
    var g = new BigInteger("3");
    var c = this.x.toBigInteger();
    var h = this.y.toBigInteger();
    var e = h.multiply(this.z);
    var j = e.multiply(h).mod(this.curve.q);
    var i = this.curve.a.toBigInteger();
    var k = c.square().multiply(g);
    if (!BigInteger.ZERO.equals(i)) {
        k = k.add(this.z.square().multiply(i))
    }
    k = k.mod(this.curve.q);
    var b = k.square().subtract(c.shiftLeft(3).multiply(j)).shiftLeft(1).multiply(e).mod(this.curve.q);
    var f = k.multiply(g).multiply(c).subtract(j.shiftLeft(1)).shiftLeft(2).multiply(j).subtract(k.square().multiply(k)).mod(this.curve.q);
    var d = e.square().multiply(e).shiftLeft(3).mod(this.curve.q);
    return new ECPointFp(this.curve, this.curve.fromBigInteger(b), this.curve.fromBigInteger(f), d)
}
function pointFpMultiply(b) {
    if (this.isInfinity()) {
        return this
    }
    if (b.signum() == 0) {
        return this.curve.getInfinity()
    }
    var g = b;
    var f = g.multiply(new BigInteger("3"));
    var l = this.negate();
    var d = this;
    var c;
    for (c = f.bitLength() - 2; c > 0; --c) {
        d = d.twice();
        var a = f.testBit(c);
        var j = g.testBit(c);
        if (a != j) {
            d = d.add(a ? this : l)
        }
    }
    return d
}
function pointFpMultiplyTwo(c, a, b) {
    var d;
    if (c.bitLength() > b.bitLength()) {
        d = c.bitLength() - 1
    } else {
        d = b.bitLength() - 1
    }
    var f = this.curve.getInfinity();
    var e = this.add(a);
    while (d >= 0) {
        f = f.twice();
        if (c.testBit(d)) {
            if (b.testBit(d)) {
                f = f.add(e)
            } else {
                f = f.add(this)
            }
        } else {
            if (b.testBit(d)) {
                f = f.add(a)
            }
        }
        --d
    }
    return f
}
ECPointFp.prototype.getX = pointFpGetX;
ECPointFp.prototype.getY = pointFpGetY;
ECPointFp.prototype.equals = pointFpEquals;
ECPointFp.prototype.isInfinity = pointFpIsInfinity;
ECPointFp.prototype.negate = pointFpNegate;
ECPointFp.prototype.add = pointFpAdd;
ECPointFp.prototype.twice = pointFpTwice;
ECPointFp.prototype.multiply = pointFpMultiply;
ECPointFp.prototype.multiplyTwo = pointFpMultiplyTwo;
function ECCurveFp(e, d, c) {
    this.q = e;
    this.a = this.fromBigInteger(d);
    this.b = this.fromBigInteger(c);
    this.infinity = new ECPointFp(this, null, null)
}
function curveFpGetQ() {
    return this.q
}
function curveFpGetA() {
    return this.a
}
function curveFpGetB() {
    return this.b
}
function curveFpEquals(a) {
    if (a == this) {
        return true
    }
    return (this.q.equals(a.q) && this.a.equals(a.a) && this.b.equals(a.b))
}
function curveFpGetInfinity() {
    return this.infinity
}
function curveFpFromBigInteger(a) {
    return new ECFieldElementFp(this.q, a)
}
function curveFpDecodePointHex(d) {
    switch (parseInt(d.substr(0, 2), 16)) {
        case 0:
            return this.infinity;
        case 2:
        case 3:
            return null;
        case 4:
        case 6:
        case 7:
            var a = (d.length - 2) / 2;
            var c = d.substr(2, a);
            var b = d.substr(a + 2, a);
            return new ECPointFp(this, this.fromBigInteger(new BigInteger(c, 16)), this.fromBigInteger(new BigInteger(b, 16)));
        default:
            return null
    }
}
ECCurveFp.prototype.getQ = curveFpGetQ;
ECCurveFp.prototype.getA = curveFpGetA;
ECCurveFp.prototype.getB = curveFpGetB;
ECCurveFp.prototype.equals = curveFpEquals;
ECCurveFp.prototype.getInfinity = curveFpGetInfinity;
ECCurveFp.prototype.fromBigInteger = curveFpFromBigInteger;
ECCurveFp.prototype.decodePointHex = curveFpDecodePointHex;