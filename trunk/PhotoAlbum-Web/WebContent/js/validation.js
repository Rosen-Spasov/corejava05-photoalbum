function validate(){
username=person.username.value;
password=person.password.value;
passwordConfirm=person.passwordConfirm.value;
firstName=person.firstName.value;
lastName=person.lastName.value;

if (isEmpty(username,'Въведете потребителско име')==false){
	event.returnValue=false;
}else if (sizeValidation(username,10,'Потребителското име неможе да е повече от 10 символа')==false){
	event.returnValue=false;
}else if (containIllegalChar(username,'Може да съдържа само букви и цифри на латиница')==false){
	event.returnValue=false;
}else if (isEmpty(password,'Въведете парола')==false){
	event.returnValue=false;
}else if (sizeValidation(password,10,'Паролата неможе да е повече от 10 символа')==false){
	event.returnValue=false;
}else if (isEmpty(passwordConfirm,'Въведените пароли са различни')==false){
	event.returnValue=false;
}else if (passwordConfirm!=password){
	alert('Грешна парола');
	event.returnValue=false;
}else if (isEmpty(firstName,'Име')==false){
	event.returnValue=false;
}else if (sizeValidation(firstName,10,'Името неможе да е повече от 10 символа')==false){
	event.returnValue=false;
}else if (containIllegalChar(firstName,'Може да съдържа само букви и цифри на латиница')==false){
	event.returnValue=false;
}else if (isEmpty(lastName,'Въведете Фамилия')==false){
	event.returnValue=false;
}else if (sizeValidation(lastName,10,'Фамилията неможе да е повече от 10 символа')==false){
	event.returnValue=false;
}else if (containIllegalChar(lastName,'Може да съдържа само букви и цифри на латиница')==false){
	event.returnValue=false;
}


}
function isEmpty(field,msg){
	if (field==null || field==''){
		alert(msg);
		return false;
	}
		return true;
}

function sizeValidation(field,maxSize,msg){
if (field.lastIndexOf('')>maxSize){
	alert(msg);
	return false;
}
return true;
}

function containIllegalChar(field,msg){
	re = /[^A-Za-z0-9._]/g;
	if(field.match(re)){
		alert(msg);
		return false;
	}
	return true;
}



			
