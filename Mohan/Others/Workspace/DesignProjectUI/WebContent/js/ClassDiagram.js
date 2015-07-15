function getClassesForCD(dataObj,pkgName,filterListForCD){	
	var pkgData=dataObj.metricsInfo;
	var clsData=[];
	var counter=1;
	var totalClsData=[];
	var relData=dataObj.relationList;
	var relList=[];
	_.each(pkgData,function(pkgInfo){	
		_.each(pkgInfo.classList,function(clsInfo){
			totalClsData.push(clsInfo);		
		});
	});
	_.each(pkgData,function(pkgInfo){
		if(pkgInfo.packageName==pkgName){
			_.each(pkgInfo.classList,function(clsInfo){
				var clsObj={};
				clsObj.id=counter;
				++counter;
				clsObj.label=clsInfo.clsName;
				if(_.contains(filterListForCD,clsInfo.clsName))
					clsObj.isChecked=true;
				else
					clsObj.isChecked=false;
				clsData.push(clsObj);
				_.each(relData,function(relInfo){
					if(relInfo.sourceClass==clsInfo.clsName || relInfo.targetClass==clsInfo.clsName){
						relList.push(relInfo);
					}
				});
			});		
		}
	});
	_.each(relList,function(relInfo){
		var sourceClassFound=false;
		var targetClassFound=false;
		for(var i=0;i<clsData.length;i++){	
			if(clsData[i].label==relInfo.sourceClass){
				sourceClassFound=true;
				break;
			}			
		}
		for(var i=0;i<clsData.length;i++){	
			if(clsData[i].label==relInfo.targetClass){
				targetClassFound=true;
				break;
			}			
		}
		if(!sourceClassFound){
			for(var i=0;i<totalClsData.length;i++){
				if(totalClsData[i].clsName==relInfo.sourceClass){
					var clsObj={};
					clsObj.id=counter;
					++counter;
					clsObj.label=totalClsData[i].clsName;
					if(_.contains(filterListForCD,clsObj.label))
						clsObj.isChecked=true;
					else
						clsObj.isChecked=false;
					clsData.push(clsObj);
					break;
				}
			}
		}
		if(!targetClassFound){
			for(var i=0;i<totalClsData.length;i++){
				if(totalClsData[i].clsName==relInfo.targetClass){
					var clsObj={};
					clsObj.id=counter;
					++counter;
					clsObj.label=totalClsData[i].clsName;
					if(_.contains(filterListForCD,clsObj.label))
						clsObj.isChecked=true;
					else
						clsObj.isChecked=false;
					clsData.push(clsObj);
					break;
				}
			}
		}
	});
	return clsData;
}
function drawClassDiagram(dataObj,pkgName,expandAll,filterListForCD){
	var graph = new joint.dia.Graph;
	var width=50000;
	var height=30000;
	var paper2 = new joint.dia.Paper({
	    el: $('#classDiagram'),
	    width: width,
	    height: height,
	    gridSize: 1,
	    model: graph
	});
	graph.clear();
var uml = joint.shapes.uml;
var classes ={};

var letterSize = 6;
var pkgData=dataObj.metricsInfo;
var clsData=[];
var totalClsData=[];
var relData=dataObj.relationList;
var relList=[];
_.each(pkgData,function(pkgInfo){	
	_.each(pkgInfo.classList,function(clsInfo){
		totalClsData.push(clsInfo);		
	});
});
_.each(pkgData,function(pkgInfo){
	if(pkgInfo.packageName==pkgName){
		_.each(pkgInfo.classList,function(clsInfo){
			if(!(_.contains(filterListForCD,clsInfo.clsName))){
				clsData.push(clsInfo);
				_.each(relData,function(relInfo){
					if(relInfo.sourceClass==clsInfo.clsName || relInfo.targetClass==clsInfo.clsName){
						if(!(_.contains(filterListForCD,relInfo.sourceClass) || _.contains(filterListForCD,relInfo.targetClass))){
							relList.push(relInfo);
						}
					}
				});
			}
		});		
	}
});
_.each(relList,function(relInfo){
	var sourceClassFound=false;
	var targetClassFound=false;
	for(var i=0;i<clsData.length;i++){	
		if(clsData[i].clsName==relInfo.sourceClass){
			sourceClassFound=true;
			break;
		}			
	}
	for(var i=0;i<clsData.length;i++){	
		if(clsData[i].clsName==relInfo.targetClass){
			targetClassFound=true;
			break;
		}			
	}
	if(!sourceClassFound){
		for(var i=0;i<totalClsData.length;i++){
			if(totalClsData[i].clsName==relInfo.sourceClass){
				if(!(_.contains(filterListForCD,totalClsData[i].clsName))){
					clsData.push(totalClsData[i]);
					break;
				}
			}
		}
	}
	if(!targetClassFound){
		for(var i=0;i<totalClsData.length;i++){
			if(totalClsData[i].clsName==relInfo.targetClass){
				if(!(_.contains(filterListForCD,totalClsData[i].clsName))){
					clsData.push(totalClsData[i]);
					break;
				}
			}
		}
	}
});
for(var i=0;i<clsData.length;i++){	
	var fldList=clsData[i].fieldList;
	var methodList=clsData[i].methodList;
	var clsName=clsData[i].clsName;	
	var fldString=[];
	var methodString=[];
	var width=0;
	var height =0;
	if(expandAll){
	if(fldList!=undefined){
		for(var j=0;j<fldList.length;j++){		
				fldString[j]=fldList[j].fieldName+": "+fldList[j].fieldType;
		}
	}
	if(methodList!=undefined){
		for(var m=0;m<methodList.length;m++){
			var paramList=methodList[m].paramList;
			var paramString="";
			for(var k=0;k<paramList.length;k++){
				paramString=paramString+paramList[k].fieldName+": "+paramList[k].fieldType+",";
			}
			paramString=paramString.substring(0,paramString.length-1);
			methodString[m]="+ "+methodList[m].methodName+"("+paramString+"): "+methodList[m].returnType;			
		}
	}
	
	var longest1=0;
	if(methodString.length>0)
		longest1=methodString.sort(function (a, b) { return b.length - a.length;})[0].length;
	var longest2=0;
	if(fldString.length>0)
		longest2=fldString.sort(function (a, b) { return b.length - a.length;})[0].length;
	
	var longest=Math.max(longest1,longest2);
	var noOfLines=10;
	if(methodList!=undefined)
		noOfLines+=methodList.length;
	if(fldList!=undefined)
		noOfLines+=fldList.length;
	width = 2 * (letterSize * (0.5 * longest + 1));
	height = 2.2 * ((noOfLines + 1) * letterSize);
	}else{
		width=2*(letterSize * (0.5 * clsName.length + 10));
		height=2.2*11*letterSize;
		fldString=[];
		methodString=[];
	}
	if(clsName!=undefined){
		if(clsData[i].classType=='class'){		
			classes[clsName]=new uml.Class({
				size:{ width: width, height: height },
				name:clsName,
				attributes:fldString,
				methods:methodString
			});
		}
		else{
			classes[clsName]=new uml.Interface({			
				"size":{ width: width, height: height },
				"name":clsName,
				"attributes":fldString,
				"methods":methodString
			});
		}
		//console.log("cls Name ",clsName);
	}
}
graph.addCells(classes);

var relations = [];

for(var k=0;k<relList.length;k++){
	try{
		//console.log("relList source "+k+" "+relList[k].sourceClass+" target "+relList[k].targetClass);
	var source1=classes[relList[k].sourceClass].id;
	var target1=classes[relList[k].targetClass].id;
	if(source1!=undefined && target1!=undefined){
		if(relList[k].relationType=="Generalization"){
			relations[k]= new uml.Generalization({source:{id:source1},target:{id:target1}});		
		}
		else if(relList[k].relationType=="Implementation")
			relations[k]= new uml.Implementation({source:{id:source1},target:{id:target1}});
		else if(relList[k].relationType=="Aggregation")
			relations[k]= new uml.Aggregation({source:{id:source1},target:{id:target1}});	
		}
	}catch(error){
		//console.log("error relList source "+k+" "+relList[k].sourceClass+" target "+relList[k].targetClass);
	}
}
graph.addCells(relations);
joint.layout.DirectedGraph.layout(graph, { setLinkVertices: true });
}