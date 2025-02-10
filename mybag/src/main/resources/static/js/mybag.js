document.getElementById("btn_save_category").addEventListener("click", () => {
	const username = document.querySelector("#username").value;
	const title = document.querySelector("#title").value;
	const parent_id = document.querySelector("#parent_id").value;
	const data = {
		username: username,
		title: title,
		parent_id: parent_id
	}
	fetch("/api/mybag", {
		method: "post",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(data)
	})
		.then(response => response.json())
		.then(data => {
			let div = document.createElement("div");
			div.setAttribute("class", "flex_items");
			div.innerHTML = `<h2><a href="/mybag/${data.id}">${data.title}</a></h2>`
			const target_arr = document.querySelectorAll(".flex_container");
			const target = target_arr[target_arr.length - 1]
			target.append(div);
		})

})


document.getElementById("btnChRank").addEventListener("click", () => {
	let modal = document.querySelector("#modalRank");
	modal.style.display = 'flex';

})

function upRank(el) {
	const modalList = document.querySelector("#modalDiv")
	const rankList = document.querySelectorAll(".rankDiv");
	const self = el.parentElement.parentElement.getAttribute("data-id");
	rankList.forEach((value, index) => {
		if (self == value.getAttribute("data-id") && index != 0) {
			modalList.insertBefore(value, rankList[index - 1]);
		}
	})
}
function downRank(el) {
	const modalList = document.querySelector("#modalDiv")
	const rankList = document.querySelectorAll(".rankDiv");
	const self = el.parentElement.parentElement.getAttribute("data-id");
	rankList.forEach((value, index) => {
		if (self == value.getAttribute("data-id") && index != rankList.length - 1) {
			modalList.insertBefore(value, rankList[index + 2]);
		}
	})
}

function saveRank() {
	const rankList = document.querySelectorAll(".rankDiv");
	const newRank = [];
	rankList.forEach(v => newRank.push(v.getAttribute("data-id")))
	console.log(newRank)
	fetch("/api/mybag/saveRank", {
		method: "post",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(newRank)
	}).then(window.alert("적용되었습니다."))
	.then(window.location.reload())
}