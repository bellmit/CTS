function buildProjectTree(data){
	var pkgData=data.metricsInfo;
	var seqDiagInfo=data.seqDiagInfo;
	var projectName="";
	if(pkgData.length>0){
		var pkgName=pkgData[0].packageName;
		var splitObj=pkgName.split(".");
		projectName=splitObj[1];
	}		
	var counter=1;
	var seqTree={};
	seqTree.id=counter;
	seqTree.label=projectName;
	seqTree.inode=true;
	seqTree.open=false;
	seqTree.useCaseIds=[];
	seqTree.useCaseData=[];
	seqTree.seqIds=[];
	seqTree.seqData=[];
	seqTree.branch=[];
	counter++;
	_.each(seqDiagInfo,function(seqInfo){
		var modObj={};
		modObj.id=counter;
		counter++;
		modObj.label=seqInfo.moduleName;
		modObj.inode=true;
		modObj.open=false;
		modObj.branch=[];
		var useCaseObj={};
		useCaseObj.id=counter;
		seqTree.useCaseIds.push(counter);
		var tempUcObj={};
		tempUcObj.id=counter;
		tempUcObj.modName=seqInfo.moduleName;
		tempUcObj.ucList=[];
		counter++;
		useCaseObj.label="Use Cases";
		useCaseObj.inode=true;
		useCaseObj.open=false;
		useCaseObj.branch=[];
		_.each(seqInfo.useCaseInfo,function(useCaseInfo){
			var ucInfoObj={};
			ucInfoObj.id=counter;
			counter++;
			ucInfoObj.label=useCaseInfo.useCaseName;
			tempUcObj.ucList.push(useCaseInfo.useCaseName);
			ucInfoObj.inode=true;
			ucInfoObj.open=false;
			ucInfoObj.branch=[];
			_.each(useCaseInfo.webCaseInfo,function(webCaseInfo){
				var webCaseInfoObj={};
				webCaseInfoObj.id=counter;
				var tempSeqData={};
				tempSeqData.id=counter;
				seqTree.seqIds.push(counter);
				tempSeqData.sequence=[];
				counter++;
				webCaseInfoObj.label=webCaseInfo.webCaseName;
				webCaseInfoObj.inode=false;
				_.each(webCaseInfo.sequenceInfo,function(actSequence){
					tempSeqData.sequence.push(actSequence);					
				});
				ucInfoObj.branch.push(webCaseInfoObj);
				seqTree.seqData.push(tempSeqData);
				tempSeqData={};
			});
			useCaseObj.branch.push(ucInfoObj);			
		});
		seqTree.useCaseData.push(tempUcObj);
		modObj.branch.push(useCaseObj);
		seqTree.branch.push(modObj);
	});	
	return seqTree;
}