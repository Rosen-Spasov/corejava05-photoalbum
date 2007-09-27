function validate(){
username=person.username.value;
password=person.password.value;
passwordConfirm=person.passwordConfirm.value;
firstName=person.firstName.value;
lastName=person.lastName.value;

if (isEmpty(username,'enter user name')==false){
event.returnValue=false;
}else if (sizeValidation(username,10,'username ne moje da e pove4e ot 10 simvola')==false){
event.returnValue=false;
}else if (isEmpty(password,'enter password')==false){
event.returnValue=false;
}else if (sizeValidation(password,10,'password ne moje da e pove4e ot 10 simvola')==false){
event.returnValue=false;
}else if (isEmpty(passwordConfirm,'passwordConfirm')==false){
event.returnValue=false;
}else if (passwordConfirm!=password){
alert('wrong password');
event.returnValue=false;
}else if (isEmpty(firstName,'firstName')==false){
event.returnValue=false;
}else if (sizeValidation(firstName,10,'firstName ne moje da e pove4e ot 10 simvola')==false){
event.returnValue=false;
}else if (isEmpty(lastName,'lastName')==false){
event.returnValue=false;
}else if (sizeValidation(lastName,10,'lastName ne moje da e pove4e ot 10 simvola')==false){
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

