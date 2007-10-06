function isConfirm(){
var answer = confirm ("Сигурен ли сте, че искате да продължите?")
if (answer)
{
event.returnValue=true;
}
else
{
event.returnValue=false;
}

}

function validateSearchName()
{

namePhoto = searchPhoto.searchName.value;

if (isEmpty(namePhoto,'Въведете име на снимката')==false)
{
	event.returnValue=false;
}
else if (sizeValidation(namePhoto,10,'Името неможе да е повече от 10 символа')==false)
{
	event.returnValue=false;
}
else if (containIllegalChar(namePhoto,'Името може да съдържа само букви и цифри на латиница')==false)
{
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