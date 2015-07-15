// Main Router


//App.Router.map(function() {
//    this.resource('index', { path: '/' } );
//    this.resource('memberSearch', { path: '/memberSearch' } );
//    this.resource('results', { path: '/results' } );
//    this.resource('detail', { path: '/detail' } );
//    this.resource('memberDetail',{ path: '/memberDetail/:memberId' });
//});


//Main Router Map
App.Router.map(function() {
	this.resource('index', { path: '/' } );
	this.resource('memberSearch',{ path: '/memberSearch' },  function() {
	    this.resource('results',{ path: '/results' },  function() {
		    this.route('detail',{ path: '/:claimNumber'})	    
		});
	});
	
	this.resource('claimDetail',{ path: '/claimDetail/:claimId' },function(){
		this.route('TPLDetail')
	});
	
});





// Default Router
App.IndexRoute = Ember.Route.extend({
    model: function () {
    }
});


// Router for Search Page
App.MemberSearchRoute = Ember.Route.extend({
    headerName:'Member Search Results',
    model: function () {
        var searchCriteria = App.MemberSearchCriteria.create();
        return searchCriteria;
    }
});

//  Router for Results Section   Grid section
App.ResultsRoute = Ember.Route.extend({

    model: function() {
        console.log(' Route Log for View ResultsRoute');
        return App.MemberSearchResultDO.all();
    }
});


App.ResultsDetailRoute = Ember.Route.extend({
	model: function(params){
		console.log("  Route Log for View Details section : Params : "+params.claimNumber);
        var claimDetailDO = App.ClaimDetailDO.create({
            claimId:params.claimNumber
        });

        return claimDetailDO;
	}

});

App.ClaimDetailRoute = Ember.Route.extend({
	model: function(params){
		console.log("  Route Log for View ClaimDetailRoute section : Params : "+params.claimId);
		return App.ClaimDetailDO.claimData(params);
//		var claimDetailDO = App.ClaimDetailDO.create({
//            claimId:params.claimId
//        });
//
//        return claimDetailDO;
	}
});





//
//
////  Router for Results Details Section in the same page as the results
//App.ResultsDetailRoute = Ember.Route.extend({
//    model: function(params){
//        
//    }
//
//});
//
//
//// Router for the Details in the next page
//App.MemberDetailRoute = Ember.Route.extend({
//
//    model: function(params) {
//        //return this.store.find('post', params.post_id);
//        console.log(" Params : "+params.claimId);
//        var memberDetailDO = App.MemberDetail.create({
//        	claimId:params.claimId,
//            memberFirstName:'Dummy first Name',
//            memberLastName:'Dummy Last Name'
//        });
//
//        return memberDetailDO;
//
//    }
//});
//







//JUNK Code
//App.Router.map( function() {
//    this.resource('index', { path: '/' } );
//    this.resource('memberSearch', { path: '/memberSearch' });
//    this.resource('memberSearchResults', { path: '/memberSearch/memberSearchResults' });
//});



//App.MemberSearchResultsRoute = Ember.Route.extend({
//    model: function(){
//        console.log(' Route Log for View');
//        //this.store.find('MemberSearchResult');
//        return this.store.find('MemberSearchResult');
//    }
//
//});

////Router for Results Section   Grid section
//App.MemberSearchResultsRoute = Ember.Route.extend({
//
//model: function() {
//    console.log(' Route Log for View MemberSearchResultsRoute');
//    return App.MemberSearchResultDO.all();
//}
//});


