// Currently not used....
//// View to display the Search Results in a new page
//App.SearchResultsView = Ember.View.extend({
//    templateName: 'MemberSearchResults',
//    headerName:'Member Search Results',
//    memberList: function(){
//        console.log('get model for the view');
//        return this.store.find('MemberSearchResult');
//    },
//    model: function() {
//        console.log('get model for the view');
//        return this.store.find('MemberSearchResult');
//    },
//    actions: {
//        getDetails:function(){
//
//        }
//    }
//});













//App.SearchCriteriaView = Ember.View.extend({
//    templateName: 'MemberSearchCriteria',
//    actions: {
//        click: function(evt) {
//            console.log(' Button Clicked');
//            this.get('controller').send('searchMemberClaims');
//
//        },
//        searchMemberClaims: function(){
//            console.log('searchMemberClaims Clicked ');
//        },
//        newSearchMemberClaims: function(){
//            console.log('newSearchMemberClaims Clicked ');
//        }
//    }
//
//
//});
//
//
//App.SearchButton= Ember.View.extend({
//    click: function(evt) {
//        console.log(' Button Clicked');
//        this.get('controller').send('searchMemberClaims');
//
//    }
//});