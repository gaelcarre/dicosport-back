$(document).ready(function(){
	//cleanup();
	//wrapper();
	var arrCate = parserCsvCategories();
	//console.log(arrCate);
	//console.log(arrCate);
	var arrSp = parserCsvSports();
	parserRelat(arrCate, arrSp);
});

function format(s) {
	return $.trim(s.replace(/[\n\r]/g, ''));
}

function parserRelat(arrCate, arrSp){
	console.log(arrSp);
	var subcat = "";
	$(".subsubcategory").each(function(){
		var self = $(this);
		var text_self = self.find("h4").text();
		var parent = self.parent(".subcategory");
		//console.log(parent);
		var parent_text = parent.find("h3").text();
		
		var idH4 = arrCate[format(text_self)];
		var idParent = arrCate[format(parent_text)];
		subcat += idH4+";"+idParent+"<br>";
	});
	
	$(".subcategory").each(function(){
		var self = $(this);
		var text_self = self.find("h3").text();
		var parent = self.parent(".category");
		//console.log(parent);
		var parent_text = parent.find("h2").text();
		
		var idH4 = arrCate[format(text_self)];
		var idParent = arrCate[format(parent_text)];
		subcat += idH4+";"+idParent+"<br>";
	});
	
	$("#csvsubcat").append(subcat);
	
	var member = "";
	$("li").each(function(){
		var self = $(this);
		var sportid = arrSp[format(self.text())];
		var parent = null;
		var parentid = -1;
		parent = self.parents(".subsubcategory");
		if(parent.length) {
			parentid = arrCate[format(parent.find("h4:first").text())];
		}
		else {
			parent = self.parents(".subcategory");
			if(parent.length) {
				parentid = arrCate[format(parent.find("h3:first").text())];
			}
			else {
				parent = self.parents(".category");
				if(parent.length){
					parentid = arrCate[format(parent.find("h2:first").text())];
				}
			}
		}
//		console.log(sportid+"-"+parentid);
		
		if(sportid != undefined && parentid != undefined && parentid != -1) {
			member += sportid+";"+parentid+"<br>";
		}

	});
	
	$("#csvmember").append(member);
}

function parserCsvCategories(){
	var res = "";
	var array = [];
	var i = 0;
	$("h2,h3,h4").each(function(){
		var self = $(this);
		res += format(self.text())+";"+i+"<br>";
		array[format(self.text())] = i;
		i++;
	});
	
	$("#csvcategories").append(res);
	return array;
}
function parserCsvSports(){
	var res = "";
	var array = [];
	var i = 0;
	$("li").each(function(){
		var self = $(this);
		var texte;
		
		if(self.has("ul").length) {
			texte = format(self.text()).replace(format(self.find("ul").text()),'');
		}
		
		else {
			texte = format(self.text());
		}
		
		res += texte+";"+i+"<br>";
		array[texte] = i;
		i++;
	});
	
	$("#csvsports").append(res);
	return array;
}

function cleanup() {

	console.log("remove image");
	$(".thumb").each(function(){
		$(this).remove();
	})
	
	console.log("remove all class");
	$("*").each(function(index) {
		$(this).removeAttr("class");
	});
	
	console.log("remove links");
	$("a").each(function(index) {
		var t = $(this).text();
		var elt = $(this).parent().prepend(t);
		$(this).remove();
	});
	
	$("div[role='note']").each(function(index) {
		$(this).remove();
	});
	
	$("div").each(function(index) {
		if($(this).is(':empty')){
			$(this).remove();
		}
	});
	
	$("p").each(function(){
		$(this).remove();
	})
	
}

function wrapper(){
//	$('h2').each(function(){
//	    var self = $(this),
//	        contents = self.nextUntil('h2'),
//	        newWrap = $('<div />').insertAfter(self);
//	    newWrap.append(self.clone());
//	    newWrap.append(contents);
//	    newWrap.addClass("category")
//	    self.remove();
//	});
//	$('h3').each(function(){
//	    var self = $(this),
//	        contents = self.nextUntil('h3'),
//	        newWrap = $('<div />').insertAfter(self);
//	    newWrap.append(self.clone());
//	    newWrap.append(contents);
//	    newWrap.addClass("subcategory")
//	    self.remove();
//	});
	
	$('h4').each(function(){
    var self = $(this),
        contents = self.nextUntil('h4'),
        newWrap = $('<div />').insertAfter(self);
    newWrap.append(self.clone());
    newWrap.append(contents);
    newWrap.addClass("subsubcategory")
    self.remove();
});
}

