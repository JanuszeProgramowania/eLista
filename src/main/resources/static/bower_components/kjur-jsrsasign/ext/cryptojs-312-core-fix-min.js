/*! CryptoJS v3.1.2 core-fix.js
 * code.google.com/p/crypto-js
 * (c) 2009-2013 by Jeff Mott. All rights reserved.
 * code.google.com/p/crypto-js/wiki/License
 * THIS IS FIX of 'core.js' to fix Hmac issue.
 * https://code.google.com/p/crypto-js/issues/detail?id=84
 * https://crypto-js.googlecode.com/svn-history/r667/branches/3.x/src/core.js
 */
var CryptoJS = CryptoJS || (function (e, g) {
        var a = {};
        var b = a.lib = {};
        var j = b.Base = (function () {
            function n() {
            }

            return {
                extend: function (p) {
                    n.prototype = this;
                    var o = new n();
                    if (p) {
                        o.mixIn(p)
                    }
                    if (!o.hasOwnProperty("init")) {
                        o.init = function () {
                            o.$super.init.apply(this, arguments)
                        }
                    }
                    o.init.prototype = o;
                    o.$super = this;
                    return o
                }, create: function () {
                    var o = this.extend();
                    o.init.apply(o, arguments);
                    return o
                }, init: function () {
                }, mixIn: function (p) {
                    for (var o in p) {
                        if (p.hasOwnProperty(o)) {
                            this[o] = p[o]
                        }
                    }
                    if (p.hasOwnProperty("toString")) {
                        this.toString = p.toString
                    }
                }, clone: function () {
                    return this.init.prototype.extend(this)
                }
            }
        }());
        var l = b.WordArray = j.extend({
            init: function (o, n) {
                o = this.words = o || [];
                if (n != g) {
                    this.sigBytes = n
                } else {
                    this.sigBytes = o.length * 4
                }
            }, toString: function (n) {
                return (n || h).stringify(this)
            }, concat: function (t) {
                var q = this.words;
                var p = t.words;
                var n = this.sigBytes;
                var s = t.sigBytes;
                this.clamp();
                if (n % 4) {
                    for (var r = 0; r < s; r++) {
                        var o = (p[r >>> 2] >>> (24 - (r % 4) * 8)) & 255;
                        q[(n + r) >>> 2] |= o << (24 - ((n + r) % 4) * 8)
                    }
                } else {
                    for (var r = 0; r < s; r += 4) {
                        q[(n + r) >>> 2] = p[r >>> 2]
                    }
                }
                this.sigBytes += s;
                return this
            }, clamp: function () {
                var o = this.words;
                var n = this.sigBytes;
                o[n >>> 2] &= 4294967295 << (32 - (n % 4) * 8);
                o.length = e.ceil(n / 4)
            }, clone: function () {
                var n = j.clone.call(this);
                n.words = this.words.slice(0);
                return n
            }, random: function (p) {
                var o = [];
                for (var n = 0; n < p; n += 4) {
                    o.push((e.random() * 4294967296) | 0)
                }
                return new l.init(o, p)
            }
        });
        var m = a.enc = {};
        var h = m.Hex = {
            stringify: function (p) {
                var r = p.words;
                var o = p.sigBytes;
                var q = [];
                for (var n = 0; n < o; n++) {
                    var s = (r[n >>> 2] >>> (24 - (n % 4) * 8)) & 255;
                    q.push((s >>> 4).toString(16));
                    q.push((s & 15).toString(16))
                }
                return q.join("")
            }, parse: function (p) {
                var n = p.length;
                var q = [];
                for (var o = 0; o < n; o += 2) {
                    q[o >>> 3] |= parseInt(p.substr(o, 2), 16) << (24 - (o % 8) * 4)
                }
                return new l.init(q, n / 2)
            }
        };
        var d = m.Latin1 = {
            stringify: function (q) {
                var r = q.words;
                var p = q.sigBytes;
                var n = [];
                for (var o = 0; o < p; o++) {
                    var s = (r[o >>> 2] >>> (24 - (o % 4) * 8)) & 255;
                    n.push(String.fromCharCode(s))
                }
                return n.join("")
            }, parse: function (p) {
                var n = p.length;
                var q = [];
                for (var o = 0; o < n; o++) {
                    q[o >>> 2] |= (p.charCodeAt(o) & 255) << (24 - (o % 4) * 8)
                }
                return new l.init(q, n)
            }
        };
        var c = m.Utf8 = {
            stringify: function (n) {
                try {
                    return decodeURIComponent(escape(d.stringify(n)))
                } catch (o) {
                    throw new Error("Malformed UTF-8 data")
                }
            }, parse: function (n) {
                return d.parse(unescape(encodeURIComponent(n)))
            }
        };
        var i = b.BufferedBlockAlgorithm = j.extend({
            reset: function () {
                this._data = new l.init();
                this._nDataBytes = 0
            }, _append: function (n) {
                if (typeof n == "string") {
                    n = c.parse(n)
                }
                this._data.concat(n);
                this._nDataBytes += n.sigBytes
            }, _process: function (w) {
                var q = this._data;
                var x = q.words;
                var n = q.sigBytes;
                var t = this.blockSize;
                var v = t * 4;
                var u = n / v;
                if (w) {
                    u = e.ceil(u)
                } else {
                    u = e.max((u | 0) - this._minBufferSize, 0)
                }
                var s = u * t;
                var r = e.min(s * 4, n);
                if (s) {
                    for (var p = 0; p < s; p += t) {
                        this._doProcessBlock(x, p)
                    }
                    var o = x.splice(0, s);
                    q.sigBytes -= r
                }
                return new l.init(o, r)
            }, clone: function () {
                var n = j.clone.call(this);
                n._data = this._data.clone();
                return n
            }, _minBufferSize: 0
        });
        var f = b.Hasher = i.extend({
            cfg: j.extend(), init: function (n) {
                this.cfg = this.cfg.extend(n);
                this.reset()
            }, reset: function () {
                i.reset.call(this);
                this._doReset()
            }, update: function (n) {
                this._append(n);
                this._process();
                return this
            }, finalize: function (n) {
                if (n) {
                    this._append(n)
                }
                var o = this._doFinalize();
                return o
            }, blockSize: 512 / 32, _createHelper: function (n) {
                return function (p, o) {
                    return new n.init(o).finalize(p)
                }
            }, _createHmacHelper: function (n) {
                return function (p, o) {
                    return new k.HMAC.init(n, o).finalize(p)
                }
            }
        });
        var k = a.algo = {};
        return a
    }(Math));