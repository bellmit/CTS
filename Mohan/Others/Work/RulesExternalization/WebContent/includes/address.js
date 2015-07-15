//for future address which can be changed
function showCityOptionsF(stateF){
    if(stateF=='cityList'){
	       radioToggle('cityInputF',0);
		   radioToggle('cityListF',1);
    }
    else{
	       radioToggle('cityInputF',1);
		   radioToggle('cityListF',0);
    }
}// end of future state toggle

function showCountryOptionsF(countryF){
    if(countryF=='USA'){
	       radioToggle('line3F',0);
	       radioToggle('line4F',0);
	       radioToggle('cityInputF',0);
		   radioToggle('cityListF',1);
		   radioToggle('stateFu',1);
		   radioToggle('provinceF',0);
		   radioToggle('zipFN',1);
		   radioToggle('postalF',0);
    }
    else if(countryF=='OTH'){
	       radioToggle('line3F',1);
	       radioToggle('line4F',1);
	       radioToggle('stateFu',0);
	       radioToggle('cityInputF',1);
		   radioToggle('cityListF',0);
		   radioToggle('provinceF',0);
		   radioToggle('zipF',0);
		   radioToggle('postalF',1);
    }
    else{
	       radioToggle('line3F',1);
	       radioToggle('line4F',1);
	       radioToggle('stateFu',0);
	       radioToggle('cityInputF',1);
		   radioToggle('cityListF',0);
		   radioToggle('provinceF',1);
		   radioToggle('zipF',0);
		   radioToggle('postalF',1);
    }
}// end of future country toggle

//for new address
function showCityOptionsN(stateN){
    if(stateN=='cityList'){
	       radioToggle('cityInputN',0);
		   radioToggle('cityListN',1);
    }
    else{
	       radioToggle('cityInputN',1);
		   radioToggle('cityListN',0);
    }
}// end of new state toggle

function showCountryOptionsN(countryN){
    if(countryN=='USA'){
	       radioToggle('line3N',0);
	       radioToggle('line4N',0);
	       radioToggle('cityInputN',0);
		   radioToggle('cityListN',1);
		   radioToggle('stateNe',1);
		   radioToggle('provinceN',0);
		   radioToggle('zipN',1);
		   radioToggle('postalN',0);
    }
    else if (countryN=='OTH'){
	       radioToggle('line3N',1);
	       radioToggle('line4N',1);
	       radioToggle('stateNe',0);
	       radioToggle('cityInputN',1);
		   radioToggle('cityListN',0);
		   radioToggle('provinceN',0);
		   radioToggle('zipN',0);
		   radioToggle('postalN',1);
    }
    else{
	       radioToggle('line3N',1);
	       radioToggle('line4N',1);
	       radioToggle('stateNe',0);
	       radioToggle('cityInputN',1);
		   radioToggle('cityListN',0);
		   radioToggle('provinceN',1);
		   radioToggle('zipN',0);
		   radioToggle('postalN',1);
    }
}// end of new country toggle

//for current address which can be changed
function showCityOptionsC(stateC){
    if(stateC=='cityList'){
	       radioToggle('cityInputC',0);
		   radioToggle('cityListC',1);
    }
    else{
	       radioToggle('cityInputC',1);
		   radioToggle('cityListC',0);
    }
}// end of current state toggle

function showCountryOptionsC(countryC){
    if(countryC=='USA'){
	       radioToggle('line3C',0);
	       radioToggle('line4C',0);
	       radioToggle('cityInputC',0);
		   radioToggle('cityListC',1);
		   radioToggle('stateCu',1);
		   radioToggle('provinceC',0);
		   radioToggle('zipC',1);
		   radioToggle('postalC',0);
    }
    else{
	       radioToggle('line3C',1);
	       radioToggle('line4C',1);
	       radioToggle('stateCu',0);
	       radioToggle('cityInputC',1);
		   radioToggle('cityListC',0);
		   radioToggle('provinceC',1);
		   radioToggle('zipC',0);
		   radioToggle('postalC',1);
    }
}// end of current country toggle
