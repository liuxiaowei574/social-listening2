function isNumber(string,sign) 
{ 
	var number; 
	if (string==null) return false; 
	if ((sign!=null) && (sign!='-') && (sign!='+')) 
	{ 
		alert('isNumber(string,sign)的参数出错： sign为null或"-"或"+"'); 
		return false; 
	} 
	number = new Number(string); 
	if (isNaN(number)) 
	{ 
		return false; 
	} 
	else if ((sign==null && number>-2000000000 && number<2000000000) || (sign=='-' && number<0 && number>-2000000000) || (sign=='+' && number>0 && number<2000000000))
	{ 
		return true; 
	} 
	else 
		return false; 
}

function isPlusInteger(string)
{
	var re = /^[1-9]\d*$/;
	if (re.test(string))
	{
		number = new Number(string); 
		if (number<2000000000)
			return true;
		else
			return false;
	}
	else
		return false;
}

function isValidString(string)
{
	if (string==null) 
		return false;
	else if (string=='')
		return false;
	else if (string.length>64)
		return false;
	else
		return true;
}

function isValidStringLS(string,maxlength)
{
	if (string==null) 
		return false;
	else if (string=='')
		return false;
	else if (string.length > maxlength)
		return false;
	else
		return true;
}

function isDate(string) { 
	var regex = new RegExp("^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$","g"); 
	if (!regex.test(string)) 
		return false;
	else
		return true;
}

function halfReplace(string)
{
	if (string == null)
		return '';
	string = string.replace(new RegExp(",","g"), ",");
	string = string.replace(new RegExp("&","g"), "&");
	string = string.replace(new RegExp("\\?","g"), "?");
	string = string.replace(new RegExp("=","g"), "=");
	string = string.replace(new RegExp("'","g"), "'");
	return string;
}

 String.prototype.endWith=function(str){
  if(str==null||str==""||this.length==0||str.length>this.length)
     return false;
  if(this.substring(this.length-str.length)==str)
     return true;
  else
     return false;
  return true;
 }
