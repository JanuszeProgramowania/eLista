/*! Mike Samuel (c) 2009 | code.google.com/p/json-sans-eval
 */
var jsonParse = (function () {
    var e = "(?:-?\\b(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][+-]?[0-9]+)?\\b)";
    var j = '(?:[^\\0-\\x08\\x0a-\\x1f"\\\\]|\\\\(?:["/\\\\bfnrt]|u[0-9A-Fa-f]{4}))';
    var i = '(?:"' + j + '*")';
    var d = new RegExp("(?:false|true|null|[\\{\\}\\[\\]]|" + e + "|" + i + ")", "g");
    var k = new RegExp("\\\\(?:([^u])|u(.{4}))", "g");
    var g = {'"': '"', "/": "/", "\\": "\\", b: "\b", f: "\f", n: "\n", r: "\r", t: "\t"};

    function h(l, m, n) {
        return m ? g[m] : String.fromCharCode(parseInt(n, 16))
    }

    var c = new String("");
    var a = "\\";
    var f = {"{": Object, "[": Array};
    var b = Object.hasOwnProperty;
    return function (u, q) {
        var p = u.match(d);
        var x;
        var v = p[0];
        var l = false;
        if ("{" === v) {
            x = {}
        } else {
            if ("[" === v) {
                x = []
            } else {
                x = [];
                l = true
            }
        }
        var t;
        var r = [x];
        for (var o = 1 - l, m = p.length; o < m; ++o) {
            v = p[o];
            var w;
            switch (v.charCodeAt(0)) {
                default:
                    w = r[0];
                    w[t || w.length] = +(v);
                    t = void 0;
                    break;
                case 34:
                    v = v.substring(1, v.length - 1);
                    if (v.indexOf(a) !== -1) {
                        v = v.replace(k, h)
                    }
                    w = r[0];
                    if (!t) {
                        if (w instanceof Array) {
                            t = w.length
                        } else {
                            t = v || c;
                            break
                        }
                    }
                    w[t] = v;
                    t = void 0;
                    break;
                case 91:
                    w = r[0];
                    r.unshift(w[t || w.length] = []);
                    t = void 0;
                    break;
                case 93:
                    r.shift();
                    break;
                case 102:
                    w = r[0];
                    w[t || w.length] = false;
                    t = void 0;
                    break;
                case 110:
                    w = r[0];
                    w[t || w.length] = null;
                    t = void 0;
                    break;
                case 116:
                    w = r[0];
                    w[t || w.length] = true;
                    t = void 0;
                    break;
                case 123:
                    w = r[0];
                    r.unshift(w[t || w.length] = {});
                    t = void 0;
                    break;
                case 125:
                    r.shift();
                    break
            }
        }
        if (l) {
            if (r.length !== 1) {
                throw new Error()
            }
            x = x[0]
        } else {
            if (r.length) {
                throw new Error()
            }
        }
        if (q) {
            var s = function (C, B) {
                var D = C[B];
                if (D && typeof D === "object") {
                    var n = null;
                    for (var z in D) {
                        if (b.call(D, z) && D !== C) {
                            var y = s(D, z);
                            if (y !== void 0) {
                                D[z] = y
                            } else {
                                if (!n) {
                                    n = []
                                }
                                n.push(z)
                            }
                        }
                    }
                    if (n) {
                        for (var A = n.length; --A >= 0;) {
                            delete D[n[A]]
                        }
                    }
                }
                return q.call(C, B, D)
            };
            x = s({"": x}, "")
        }
        return x
    }
})();