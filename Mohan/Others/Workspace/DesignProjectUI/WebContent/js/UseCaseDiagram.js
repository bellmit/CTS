function drawUseCaseDiagram(useCaseData){	
	var graph3 = new joint.dia.Graph;
	var width=7000;
	var height=500;
	var paper3 = new joint.dia.Paper({
	    el: $('#useCaseDiagram'),
	    width: width,
	    height: height,
	    gridSize: 1,
	    model: graph3
	});
	graph3.clear();
	var uml = joint.shapes.uml;
	var useCaseActor=new uml.Actor({
		actorName:"User"
	});
	
	var ucList={};
	_.each(useCaseData,function(ucName){
		ucList[ucName]=	new uml.UseCase({name:ucName});
	});
	graph3.addCell(useCaseActor);
	_.each(ucList,function(uc){graph3.addCell(uc);});
	
	var links=[];
	_.each(ucList,function(uc){
		links.push(new uml.UseCaseLink({source: { id: useCaseActor.id},
        target: { id: uc.id}}));
	});	
	graph3.addCells(links);
	joint.layout.DirectedGraph.layout(graph3, { setLinkVertices: true });
}