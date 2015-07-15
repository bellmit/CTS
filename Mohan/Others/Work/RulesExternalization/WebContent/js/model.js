App.ApplicationAdapter = DS.FixtureAdapter.extend();

//SearchCriteria Model
App.MemberSearchCriteria = Ember.Object.extend({
    providerFirstName:'',
    providerLastName:'',
    providerType:''
});


//SearchCriteria Model
App.ClaimDetailDO = Ember.Object.extend();
App.ClaimDetailDO.reopenClass({
    claimData: function(params) {
    	console.log('Request Object  ClaimDetailDO Data claimId : '+params.claimId);
        return $.getJSON("/MemberSupportPortalSpring/MemberSupportServices/"+params.claimId).then(function(response) {
            console.log('Success ClaimDetailDO Data '+response);
            console.log('Success ClaimDetailDO Data '+JSON.stringify(response));
            return App.ClaimDetailDO.create(response);
        }, function(reason) {
            console.log('Reject Data '+JSON.stringify(reason));
        });
    }
});




App.MemberSearchResultDO = Ember.Object.extend();

App.MemberSearchResultDO.reopenClass({
    all: function() {
        return $.getJSON("/MemberSupportPortalSpring/MemberSupportServices").then(function(response) {
            console.log('Success Data '+response);
            console.log('Success Data '+JSON.stringify(response));
            var spanListVar = [];
            console.log(' return of the REST call ');
            response.spanList.forEach(
                function (memberSearchResultDO) {
                	console.log('Success Data 1 '+JSON.stringify(memberSearchResultDO));
                    spanListVar.push( App.MemberSearchResultDO.create(memberSearchResultDO) );
                });
            console.log(' return of the REST call : '+spanListVar);
            return spanListVar;
        }, function(reason) {
            console.log('Reject Data '+JSON.stringify(reason));
        });
    }
});

App.MemberSearchResult.FIXTURES = [
];



// Junk code To Be Deleted
/*
App.MemberSearchResult.FIXTURES = [
  {id: 1,"memberId":"M1","providerLastName":"last1","providerFirstName":"first1","providerType":"Professional"},
  {id: 2,"memberId":"M2","providerLastName":"Last 2","providerFirstName":"First 2","providerType":"Dental"},
  {id: 3,"memberId":"M3","providerLastName":"Last 3","providerFirstName":"First 3","providerType":"Institutional"}
];
  */

/*
App.Store = DS.Store.extend({
    revision: 12,
    adapter: DS.LSAdapter.create({
        namespace: 'app-emberjs'
    })
});


//SearchResultModel
App.MemberSearchResult = DS.Model.extend({
    providerFirstName: DS.attr('string'),
    providerLastName: DS.attr('string'),
    memberId: DS.attr('string'),
    providerType: DS.attr('string')
});


//App.ApplicationAdapter = DS.JsonApiAdapter.extend();

*/