$(document).ready(function(){
	parser();
});
function parser() {
	
	var categoryid = 0;
	var sportid = 0;
	
	
	$(".category").each(function() {
		var category = $(this);
		categoryid = traitercategory(category, categoryid,0,0);
		
		
//		//souscategories
//		category.find(".subcategory").each(function(){
//			var subcategory = $(this);
//			var titresubc = format(recupererTitre(subcategory));
//			$("#csvcategories").append(titresubc+";"+categoryid+"<br>");
//			$("#csvsubcat").append(categoryid+";"+categoryarray[titre]+"<br>");
//			
//			
//			console.log(titresubc,categoryid);
//			
//			//sports
//			subcategory.find("ul:first > li").each(function(){
//				//console.log(categoryid);
//				var sport = $(this);
//				sportid = traitersport(sport, sportid, categoryid,0,0);
//				sportid ++;
//			});
//			
//			categoryid++;
//			
//		});
		//csvsports csvmember csvsubsport
	});
}

function traitercategory(category, categoryid, levelsouscategory, categoryparentid) {
	//Traitement d'une categorie
	//recuperer titre de la categorie
	var titre = format(recupererTitre(category));
	
	if(levelsouscategory != 0) {
		categoryid++;
	}
	$("#csvcategories").append(titre+";"+categoryid+"<br>");

	if(levelsouscategory < 1) {
		
	}
	else {
		$("#csvsubcat").append(categoryid+";"+categoryarray[titre]+"<br>");
	}
	
	
	if(category.has(".subcategory, .subsubcategory").length) {
		//la categorie a des sous categories
		categoryparentid = categoryid;
		levelsouscategory++;
		
		category.find(".subcategory, .subsubcategory").each(function(){
			var souscate = $(this);
			categoryid = traitercategory(souscate, categoryid, levelsouscategory, categoryparentid);
		});
		
		category.empty();
		category.append(titre);
		
	}
	
	return categoryid;
	
}

function traitersport(sport, sportid, categoryid, levelsoussport, sportparentid) {
	//Traitement d'un sport
	
	var titresport = format(recuperertitresport(format(sport.html())));
	
	
	if(levelsoussport != 0) {
		sportid++;
	}
	
	$("#csvsports").append(titresport+";"+sportid+"<br>");
	
	if(levelsoussport < 1) {
		$("#csvmember").append(sportid+";"+categoryid+"<br>");
	}
	else {
		$("#csvsubsport").append(sportid+";"+sportparentid+"<br>");
	}
	

	console.log("sport ", sportid, " ", titresport, " pour level ", levelsoussport);
	
	if(sport.has("ul").length) {
		sportparentid = sportid;
		levelsoussport++;
		// Le sport possède des sous sport
		
		sport.find("ul:first > li").each(function(){
			
			var soussport = $(this);
			sportid = traitersport(soussport, sportid, categoryid, levelsoussport, sportparentid);
		});
		
		sport.empty();
		sport.append(titresport);

	}
	else {
		//console.log("sport ", sportid, " ", titresport, " pour level ", levelsoussport);
		
	}
	
	
	return sportid;
}

function recuperertitresport(sport) {
	if(sport.indexOf("<ul>") != -1){
		return sport.substring(0, sport.indexOf("<ul>"));
	}
	else {
		return sport;
	}
	
}

function recupererTitre(elt) {
	return elt.find("h2,h3,h4").first().text();
}

function format(s) {
	return $.trim(s.replace(/[\n\r]/g, ''));
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