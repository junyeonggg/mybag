document.getElementById("btn_save_category").addEventListener("click",()=>{
	const username  = document.querySelector("#username").value;
	const category_name = document.querySelector("#category_name").value;
	const data ={
		username :username,
		category_name : category_name
	}
	fetch("/mybag",{
	method : "post",
	headers : {"Content-Type":"application/json"},	
	body : JSON.stringify(data)
	})
})