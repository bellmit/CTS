function buildClassTree(data){
	var pkgData=data.metricsInfo;
	var counter=1;	
	var classObj={};
	classObj.id=counter;
	counter++;
	classObj.label="Class Diagram";
	classObj.inode=true;
	classObj.open=false;
	classObj.packageIds=[];
	classObj.branch=[];	
	_.each(pkgData,function(pkgInfo){
		var clsData=pkgInfo.classList;
		if(clsData!=undefined && clsData.length>0){
			var packageObj={};
			packageObj.id=counter;
			classObj.packageIds.push(counter);
			counter++;
			packageObj.label=pkgInfo.packageName;
			packageObj.inode=true;
			packageObj.open=false;
			packageObj.branch=[];	
			_.each(clsData,function(clsInfo){
				var classObj={};
				classObj.id=counter;
				counter++;
				classObj.label=clsInfo.clsName;
				classObj.inode=true;
				classObj.open=false;
				classObj.branch=[];
				var fldList=clsInfo.fieldList;
				if(fldList!=undefined){
					_.each(fldList,function(fieldInfo){
						var fldObj={};
						fldObj.id=counter;
						counter++;
						fldObj.label=fieldInfo.fieldName+" : "+fieldInfo.fieldType;
						fldObj.inode=false;
						classObj.branch.push(fldObj);
					});
				}
				var methodList=clsInfo.methodList;
				if(methodList!=undefined){
					_.each(methodList,function(methodInfo){
						var methodObj={};
						methodObj.id=counter;
						counter++;
						var paramList=methodInfo.paramList;
						var paramString="";
						_.each(paramList,function(paramObj){
							paramString=paramString+paramObj.fieldType+",";
						});
						paramString=paramString.substring(0,paramString.length-1);
						methodObj.label=methodInfo.methodName+"("+paramString+") : "+methodInfo.returnType;
						methodObj.inode=false;
						classObj.branch.push(methodObj);
					});
				}
				packageObj.branch.push(classObj);
			});
			classObj.branch.push(packageObj);
		}
		});	
	return classObj;
}