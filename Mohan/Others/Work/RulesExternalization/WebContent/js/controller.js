// Controller for the Member Search
App.MemberSearchController = Ember.Controller.extend({
	providerType: [
	               {providerType: "Dental",       id: 1},
	               {providerType: "Professional", id: 2},
	               {providerType: "Institutional",id: 3}
	],
    submitAction : function(){ // Search Button Click
        console.log("now we can submit the model:" + this.get("model"));

        // getting the contents of the model and logging it
        console.log("now we can submit the model:" + this.get("model").get('providerFirstName'));
        console.log("now we can submit the model:" + this.get("model").get('providerLastName'));
        console.log("now we can submit the model:" + this.get("model").get('providerType'));



        this.transitionToRoute('/memberSearch/results');     // Navigate to the Results Section
    }
});


App.ResultsController = Ember.ArrayController.extend();



// Junk Code to be deleted

////Pushing the data in the model into the Local Data Store
//
//        var memberStore = this.store.push('MemberSearchResult', {
//            memberId: 'MemberID'+this.get("providerLastName")+this.get("providerType"),
//            providerLastName: this.get("providerLastName"),
//            providerFirstName: this.get("providerFirstName"),
//            providerType: this.get("providerType")
//        });