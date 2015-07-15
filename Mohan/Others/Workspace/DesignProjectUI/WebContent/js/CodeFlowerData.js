var convertFromClocToJSON = function(pkgData) {
  var rootData;
  if(pkgData.length>0){
		var pkgName=pkgData[0].packageName;
		var splitObj=pkgName.split(".");
		rootData=splitObj[0];
	}
  var lines=[];
  _.each(pkgData,function(pkgInfo){
	  var clsList=pkgInfo.classMetricList;
	  _.each(clsList,function(clsInfo){		
		  if(clsInfo){
			  var clsName=pkgInfo.packageName+"."+clsInfo.className;			 
			  var funcs=clsInfo.functionMetricList;	
			  lines.push({fileName:clsName,size:clsInfo.clsNcssCount,functions:funcs.length,ccn:clsInfo.clsCyclomaticCxty});			  
		  }
	  });
  });
  var json = {};
  lines.forEach(function(line) {   
    var filename = line.fileName;
    if (!filename) return;
    var elements = filename.split(".");
    var current = json;
    elements.forEach(function(element) {
      if (!current[element]) {
        current[element] = {};
      }
      current = current[element];
    });
    current.functions = line.functions;
    current.ccn=line.ccn;
    current.size = line.size;
  });

  json = getChildren(json)[0];
  json.name = rootData;
  return json;
};
var getChildren = function(json) {
	  var children = [];
	 
	  for (var key in json) {
	    var child = { name: key };
	    if (json[key].size) {
	      // value node
	      child.size = json[key].size;
	      child.functions = json[key].functions;
	      child.ccn = json[key].ccn;
	    } else {
	      // children node
	      var childChildren = getChildren(json[key]);
	      if (childChildren) child.children = childChildren;
	    }
	    children.push(child);
	    delete json[key];
	  }
	  return children;
	};
	