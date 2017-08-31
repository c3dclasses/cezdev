//---------------------------------------------------------------------------------
// file: CHash
// desc: defines a hash object
//---------------------------------------------------------------------------------

//-------------------------------------------
// class CHash
// desc: defines a hash object
//-------------------------------------------
var CHash = new Class ({
	initialize : function() { this.clear(); },
	create : function(hash) { this.m_hash=hash||{}; },
	merge : function(hash) { jQuery.extend(this.m_hash, hash); }, 
	clear : function() { this.m_hash={}; },
	get : function(key) { return (this.m_hash.hasOwnProperty(key)) ? this.m_hash[key] : null; },
	set : function(key, value) { this.m_hash[key] = value; },
	setRef : function(key, value) { this.m_hash[key] = value; },
	remove : function(key) { delete this.m_hash[key]; },
	size : function() { var size = 0, key; for (key in this.m_hash) { if (this.m_hash.hasOwnProperty(key)) size++; } return size; },
	isEmpty : function() { return this.size() == 0; },
	containsValue : function(value) { return this.values().indexOf(value) > -1; },
	containsKey : function(key) { return (this.m_hash.hasOwnProperty(key)); },
	keys : function() { var array=[]; for (key in this.m_hash) { if(this.m_hash.hasOwnProperty(key)) array.push(key); } return array; },
	values : function() { var array=[]; for(key in this.m_hash) { if(this.m_hash.hasOwnProperty(key)) array.push(this.m_hash[key]); } return array; },
	valueOf : function() { return this.m_hash; },
	_ : function() { return this.m_hash; },
	visit : function(fnvisit) { if(typeof(fnvisit) != "function") return; for(key in this.m_hash) fnvisit(key, this.m_hash[key]); },
	toStringVisit : function(fnvisit, cdata) {if(typeof(fnvisit) != "function") return; str=""; for(key in this.m_hash) str += fnvisit(key, this.m_hash[key]); return str; },
	hash : function(key) { if(arguments.length == 1) return this.get(key); else if(arguments.length == 2) this.set(key, arguments[1]); return; },
	urlencode : function() { if(this.m_hash) for(key in this.m_hash) this.m_hash[key] = urlencode($value); },
	toJSON : function() { return CParse.toJSONString(this.m_hash); },
	m_hash : null
}); // end CHash

//------------------------------------------------
// name: chash() 
// desc: creates a chash object
//------------------------------------------------
function chash(obj) {
	var chash = new CHash();
	chash.create(obj);
	return chash;
} // end chash