function drawSequenceDiagram(sequenceData,filterList){
	var data="";
	var seqLength=sequenceData.length;
	
	//console.log("sequence data length "+seqLength);
	for(var i=0;i<seqLength;i++){
		var innerSeqData=sequenceData[i];		
		var innerSeqLength=innerSeqData.length;
		for(var j=0;j<innerSeqLength;j++){
			var seqData=innerSeqData[j];	
			//console.log("seqData",seqData);
			var tempFromClass=seqData.fromClass;
			var fromClass;
			if(tempFromClass.indexOf(".jsp")==-1){
				var index=tempFromClass.lastIndexOf(".");
				fromClass=tempFromClass.substring(index+1);
			}else{
				//var tempIndex=tempFromClass.lastIndexOf(".");
				//fromClass=tempFromClass.substring(tempIndex+1);
				fromClass=tempFromClass;
			}
			var inFilterList=false;
			if(_.contains(filterList,fromClass))
				inFilterList=true;
			var tempToClass=seqData.toClass;
			var index1=tempToClass.lastIndexOf(".");
			var toClass=tempToClass.substring(index1+1);
			if(_.contains(filterList,toClass))
				inFilterList=true;
			if(!inFilterList)
				data+=fromClass+"->"+toClass+": "+seqData.message+"\n";		
		}
	}
	var diagram = Diagram.parse(data);	
	diagram.drawSVG('sequenceDiagram',{theme:'simple'});
}
function getClasses(sequenceData,filterList){
	var seqLength=sequenceData.length;
	var classList=[];
	var counter=1;
	var addedClasses=[];
	for(var i=0;i<seqLength;i++){
		var innerSeqData=sequenceData[i];		
		var innerSeqLength=innerSeqData.length;
		for(var j=0;j<innerSeqLength;j++){
			var seqData=innerSeqData[j];
			var tempFromClass=seqData.fromClass;
			var fromClass;
			
			if(tempFromClass.indexOf(".jsp")==-1){
				var index=tempFromClass.lastIndexOf(".");
				fromClass=tempFromClass.substring(index+1);
				if(!(_.contains(addedClasses,fromClass))){
					var clsObj={};
					clsObj.id=counter;
					++counter;
					clsObj.label=fromClass;
					if(_.contains(filterList,fromClass))
						clsObj.isChecked=true;
					else
						clsObj.isChecked=false;
					classList.push(clsObj);
					addedClasses.push(fromClass);
				}
			}
			var tempToClass=seqData.toClass;
			var index1=tempToClass.lastIndexOf(".");
			var toClass=tempToClass.substring(index1+1);
			if(!(_.contains(addedClasses,toClass))){
				var clsObj={};
				clsObj.id=counter;
				++counter;
				clsObj.label=toClass;
				if(_.contains(filterList,toClass))
					clsObj.isChecked=true;
				else
					clsObj.isChecked=false;
				classList.push(clsObj);
				addedClasses.push(toClass);
			}
		}
	}
	return classList;
}