/**
 * page formulaire-ajout.jsp pour ajouter un cours
 * séléction liste déroulante : matieree
 * @returns
 */
$(document).ready(function(){

	//recup de selectedMatiere
	 if($("#comptecourant").is(':checked')){

	     	   // masquage du taux
	            $(".p-taux").hide();
	            $(".p-decouvert").show();

	   } else if($("#compteepargne").is(':checked')){
	     	   
	     	   // masquage du découvert
	            $(".p-decouvert").hide();
	            $(".p-taux").show();	     	   
	    }; 		
});

$(document).ready(function(){
	$('.selectedCompte').change(function(){
		selected_value = $("input[name='p-typecompte']:checked").val();

	//si comptecourant coché
	 if(selected_value == "compte_courant"){

	     	   // masquage du taux
	            $(".p-taux").hide();
	            $(".p-decouvert").show();

	   } else if(selected_value == "compte_epargne"){
	     	   
	     	   // masquage du découvert
	            $(".p-decouvert").hide();
	            $(".p-taux").show();	     	   
	    }; 		
	});	
});