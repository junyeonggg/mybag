document.getElementById("btn_save_category").addEventListener("click", () => {
	const username = document.querySelector("#username").value;
	const title = document.querySelector("#title").value;
	const parent_id = document.querySelector("#parent_id").value;
	const data = {
		username: username,
		title: title,
		parent_id : parent_id
	}
	fetch("/api/mybag", {
		method: "post",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(data)
	})
		.then(response => response.json())
		.then(data=>{
			let div = document.createElement("div");
			div.setAttribute("class","flex_items");
			div.innerHTML = `<h2><a href="/mybag/${data.id}">${data.title}</a></h2>`
			const target_arr = document.querySelectorAll(".flex_container");
			const target = target_arr[target_arr.length-1]
			target.append(div);
		})

})
