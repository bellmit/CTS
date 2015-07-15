function getDependencyWheelData(pkgData){
	var composerJson=writeComposerJson(pkgData);
	var composerLock=writeComposerLock(pkgData);
	var data = buildMatrixFromComposerJsonAndLock(composerJson, composerLock);
	return data;
}
function writeComposerJson(pkgData){
	var mainPackage="";
	var composerJson={};
	var requiredPackages={};
	if(pkgData.length>0){
		var pkgName=pkgData[0].packageName;
		var splitObj=pkgName.split(".");
		mainPackage=splitObj[0]+"/"+splitObj[1];		
	}
	composerJson["name"]=mainPackage;
	_.each(pkgData, function(pkgInfo){
		var packageName=pkgInfo.packageName;
		packageName=packageName.replace(/\./g,"/");
		requiredPackages[packageName]=">1.0";
	});
	composerJson["require"]=requiredPackages;
	return composerJson;
}
function writeComposerLock(pkgData){
	var composerLock={};	
	var composerLockPackages=[];
	_.each(pkgData,function(pkgInfo){
		var importList=[];
		_.each(pkgInfo.classList,function(clsInfo){
			if(clsInfo.importList){
				_.each(clsInfo.importList,function(importName){
					if(!(_.contains(importList,importName))){
						importName=importName.replace(/\./g,"/");
						importList.push(importName);
					}
				});
			}
		});
		var pkgObj={};
		var pkgName=pkgInfo.packageName;
		pkgName=pkgName.replace(/\./g,"/");
		pkgObj["name"]=pkgName;
		var requirePackages={};
		_.each(importList,function(importName){
			requirePackages[importName]=">1.0";
		});
		pkgObj["require"]=requirePackages;
		composerLockPackages.push(pkgObj);
	});
	composerLock["packages"]=composerLockPackages;
	return composerLock;
}
