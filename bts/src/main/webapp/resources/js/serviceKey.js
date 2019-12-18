var serviceGlobal;

function setInit_G(key){
	serviceGlobal = new valueForm();
	
	function valueForm(){
		var serviceKey=key;
		
		this.getKey=function(){
			return serviceKey;
		};
		
		this.setKey=function(input){
			serviceKey=input;
		}
	}
}