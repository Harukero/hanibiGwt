function Hanabi(){var Q='bootstrap',R='begin',S='gwt.codesvr.Hanabi=',T='gwt.codesvr=',U='Hanabi',V='startup',W='DUMMY',X=0,Y=1,Z='iframe',$='javascript:""',_='position:absolute; width:0; height:0; border:none; left: -1000px;',ab=' top: -1000px;',bb='CSS1Compat',cb='<!doctype html>',db='',eb='<html><head><\/head><body><\/body><\/html>',fb='undefined',gb='DOMContentLoaded',hb=50,ib='Chrome',jb='eval("',kb='");',lb='script',mb='javascript',nb='moduleStartup',ob='moduleRequested',pb='Failed to load ',qb='head',rb='meta',sb='name',tb='Hanabi::',ub='::',vb='gwt:property',wb='content',xb='=',yb='gwt:onPropertyErrorFn',zb='Bad handler "',Ab='" for "gwt:onPropertyErrorFn"',Bb='gwt:onLoadErrorFn',Cb='" for "gwt:onLoadErrorFn"',Db='#',Eb='?',Fb='/',Gb='img',Hb='clear.cache.gif',Ib='baseUrl',Jb='Hanabi.nocache.js',Kb='base',Lb='//',Mb='locale',Nb='default',Ob='locale=',Pb=7,Qb='&',Rb='__gwt_Locale',Sb='_',Tb='Unexpected exception in locale detection, using default: ',Ub=2,Vb='selectingPermutation',Wb='Hanabi.devmode.js',Xb='fr',Yb='87BC0F4E7BD2907AC0128374E499011D',Zb='ja',$b='9BAEFF0DBBAC3CFBB5152E571F530B7D',_b='DA663AF64C6AD276CFC89BB0641636D2',ac=':',bc=10,cc='.cache.js',dc='link',ec='rel',fc='stylesheet',gc='href',hc='loadExternalRefs',ic='css/overridecss.css',jc='css/animation.css',kc='css/material-icons.css',lc='css/materialize.min.css',mc='css/materialize.blue.css',nc='css/overridecss.blue.css',oc='end',pc='http:',qc='file:',rc='_gwt_dummy_',sc='__gwtDevModeHook:Hanabi',tc='Ignoring non-whitelisted Dev Mode URL: ',uc=':moduleBase';var q=window;var r=document;t(Q,R);function s(){var a=q.location.search;return a.indexOf(S)!=-1||a.indexOf(T)!=-1}
function t(a,b){if(q.__gwtStatsEvent){q.__gwtStatsEvent({moduleName:U,sessionId:q.__gwtStatsSessionId,subSystem:V,evtGroup:a,millis:(new Date).getTime(),type:b})}}
Hanabi.__sendStats=t;Hanabi.__moduleName=U;Hanabi.__errFn=null;Hanabi.__moduleBase=W;Hanabi.__softPermutationId=X;Hanabi.__computePropValue=null;Hanabi.__getPropMap=null;Hanabi.__installRunAsyncCode=function(){};Hanabi.__gwtStartLoadingFragment=function(){return null};Hanabi.__gwt_isKnownPropertyValue=function(){return false};Hanabi.__gwt_getMetaProperty=function(){return null};var u=null;var v=q.__gwt_activeModules=q.__gwt_activeModules||{};v[U]={moduleName:U};Hanabi.__moduleStartupDone=function(e){var f=v[U].bindings;v[U].bindings=function(){var a=f?f():{};var b=e[Hanabi.__softPermutationId];for(var c=X;c<b.length;c++){var d=b[c];a[d[X]]=d[Y]}return a}};var w;function A(){B();return w}
function B(){if(w){return}var a=r.createElement(Z);a.src=$;a.id=U;a.style.cssText=_+ab;a.tabIndex=-1;r.body.appendChild(a);w=a.contentDocument;if(!w){w=a.contentWindow.document}w.open();var b=document.compatMode==bb?cb:db;w.write(b+eb);w.close()}
function C(k){function l(a){function b(){if(typeof r.readyState==fb){return typeof r.body!=fb&&r.body!=null}return /loaded|complete/.test(r.readyState)}
var c=b();if(c){a();return}function d(){if(!c){c=true;a();if(r.removeEventListener){r.removeEventListener(gb,d,false)}if(e){clearInterval(e)}}}
if(r.addEventListener){r.addEventListener(gb,d,false)}var e=setInterval(function(){if(b()){d()}},hb)}
function m(c){function d(a,b){a.removeChild(b)}
var e=A();var f=e.body;var g;if(navigator.userAgent.indexOf(ib)>-1&&window.JSON){var h=e.createDocumentFragment();h.appendChild(e.createTextNode(jb));for(var i=X;i<c.length;i++){var j=window.JSON.stringify(c[i]);h.appendChild(e.createTextNode(j.substring(Y,j.length-Y)))}h.appendChild(e.createTextNode(kb));g=e.createElement(lb);g.language=mb;g.appendChild(h);f.appendChild(g);d(f,g)}else{for(var i=X;i<c.length;i++){g=e.createElement(lb);g.language=mb;g.text=c[i];f.appendChild(g);d(f,g)}}}
Hanabi.onScriptDownloaded=function(a){l(function(){m(a)})};t(nb,ob);var n=r.createElement(lb);n.src=k;if(Hanabi.__errFn){n.onerror=function(){Hanabi.__errFn(U,new Error(pb+code))}}r.getElementsByTagName(qb)[X].appendChild(n)}
Hanabi.__startLoadingFragment=function(a){return G(a)};Hanabi.__installRunAsyncCode=function(a){var b=A();var c=b.body;var d=b.createElement(lb);d.language=mb;d.text=a;c.appendChild(d);c.removeChild(d)};function D(){var c={};var d;var e;var f=r.getElementsByTagName(rb);for(var g=X,h=f.length;g<h;++g){var i=f[g],j=i.getAttribute(sb),k;if(j){j=j.replace(tb,db);if(j.indexOf(ub)>=X){continue}if(j==vb){k=i.getAttribute(wb);if(k){var l,m=k.indexOf(xb);if(m>=X){j=k.substring(X,m);l=k.substring(m+Y)}else{j=k;l=db}c[j]=l}}else if(j==yb){k=i.getAttribute(wb);if(k){try{d=eval(k)}catch(a){alert(zb+k+Ab)}}}else if(j==Bb){k=i.getAttribute(wb);if(k){try{e=eval(k)}catch(a){alert(zb+k+Cb)}}}}}__gwt_getMetaProperty=function(a){var b=c[a];return b==null?null:b};u=d;Hanabi.__errFn=e}
function F(){function e(a){var b=a.lastIndexOf(Db);if(b==-1){b=a.length}var c=a.indexOf(Eb);if(c==-1){c=a.length}var d=a.lastIndexOf(Fb,Math.min(c,b));return d>=X?a.substring(X,d+Y):db}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=r.createElement(Gb);b.src=a+Hb;a=e(b.src)}return a}
function g(){var a=__gwt_getMetaProperty(Ib);if(a!=null){return a}return db}
function h(){var a=r.getElementsByTagName(lb);for(var b=X;b<a.length;++b){if(a[b].src.indexOf(Jb)!=-1){return e(a[b].src)}}return db}
function i(){var a=r.getElementsByTagName(Kb);if(a.length>X){return a[a.length-Y].href}return db}
function j(){var a=r.location;return a.href==a.protocol+Lb+a.host+a.pathname+a.search+a.hash}
var k=g();if(k==db){k=h()}if(k==db){k=i()}if(k==db&&j()){k=e(r.location.href)}k=f(k);return k}
function G(a){if(a.match(/^\//)){return a}if(a.match(/^[a-zA-Z]+:\/\//)){return a}return Hanabi.__moduleBase+a}
function H(){var i=[];var j=X;function k(a,b){var c=i;for(var d=X,e=a.length-Y;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
var l=[];var m=[];function n(a){var b=m[a](),c=l[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(u){u(a,d,b)}throw null}
m[Mb]=function(){var b=null;var c=Nb;try{if(!b){var d=location.search;var e=d.indexOf(Ob);if(e>=X){var f=d.substring(e+Pb);var g=d.indexOf(Qb,e);if(g<X){g=d.length}b=d.substring(e+Pb,g)}}if(!b){b=__gwt_getMetaProperty(Mb)}if(!b){b=q[Rb]}if(b){c=b}while(b&&!__gwt_isKnownPropertyValue(Mb,b)){var h=b.lastIndexOf(Sb);if(h<X){b=null;break}b=b.substring(X,h)}}catch(a){alert(Tb+a)}q[Rb]=c;return b||Nb};l[Mb]={'default':X,'fr':Y,'ja':Ub};__gwt_isKnownPropertyValue=function(a,b){return b in l[a]};Hanabi.__getPropMap=function(){var a={};for(var b in l){if(l.hasOwnProperty(b)){a[b]=n(b)}}return a};Hanabi.__computePropValue=n;q.__gwt_activeModules[U].bindings=Hanabi.__getPropMap;t(Q,Vb);if(s()){return G(Wb)}var o;try{k([Xb],Yb);k([Zb],$b);k([Nb],_b);o=i[n(Mb)];var p=o.indexOf(ac);if(p!=-1){j=parseInt(o.substring(p+Y),bc);o=o.substring(X,p)}}catch(a){}Hanabi.__softPermutationId=j;return G(o+cc)}
function I(){if(!q.__gwt_stylesLoaded){q.__gwt_stylesLoaded={}}function c(a){if(!__gwt_stylesLoaded[a]){var b=r.createElement(dc);b.setAttribute(ec,fc);b.setAttribute(gc,G(a));r.getElementsByTagName(qb)[X].appendChild(b);__gwt_stylesLoaded[a]=true}}
t(hc,R);c(ic);c(jc);c(kc);c(lc);c(mc);c(nc);t(hc,oc)}
D();Hanabi.__moduleBase=F();v[U].moduleBase=Hanabi.__moduleBase;var J=H();if(q){var K=!!(q.location.protocol==pc||q.location.protocol==qc);q.__gwt_activeModules[U].canRedirect=K;function L(){var b=rc;try{q.sessionStorage.setItem(b,b);q.sessionStorage.removeItem(b);return true}catch(a){return false}}
if(K&&L()){var M=sc;var N=q.sessionStorage[M];if(!/^http:\/\/(localhost|127\.0\.0\.1)(:\d+)?\/.*$/.test(N)){if(N&&(window.console&&console.log)){console.log(tc+N)}N=db}if(N&&!q[M]){q[M]=true;q[M+uc]=F();var O=r.createElement(lb);O.src=N;var P=r.getElementsByTagName(qb)[X];P.insertBefore(O,P.firstElementChild||P.children[X]);return false}}}I();t(Q,oc);C(J);return true}
Hanabi.succeeded=Hanabi();