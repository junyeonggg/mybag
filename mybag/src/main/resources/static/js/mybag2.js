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
			div.innerHTML = `<h2><span>${data.title}</span></h2>`
			const target_arr = document.querySelectorAll(".flex_container");
			const target = target_arr[target_arr.length - 1]
			target.append(div);
		})

})
function getContent(id) {
	return fetch(`/api/mybag/${id}`, {
		method: "get",
	})
}

function outPutContent(btn) {
	const id = btn.parentElement.getAttribute("data-id")
	const open = btn.parentElement.getAttribute("data-open")
	if (open == "true") {
		const targets = [...btn.parentElement.children];
		for(const target of targets.reverse()){
			if(target.tagName.toLowerCase() == "h2"){
				break;
			}
			target.parentElement.removeChild(target)
		}
		btn.parentElement.setAttribute("data-open", "false")
	} else {
		getContent(id)
			.then(response => response.text())
			.then(data => {
				const btnEl = document.createElement("button")
				btnEl.setAttribute("onclick", "setContent(this)");
				btnEl.innerText = "수정";

				const divEl = document.createElement("div");
				const innerHtml = marked.parse(data,markedOptions)
				divEl.innerHTML = innerHtml
				btn.parentElement.append(btnEl);
				btn.parentElement.append(divEl);
				btn.parentElement.setAttribute("data-open", "true")
			})
	}
}

function setContent(self) {
	const id = self.parentElement.getAttribute("data-id")

	const newInput = document.createElement("button")
	newInput.setAttribute("onclick", "saveContent(this)")
	newInput.innerText = "수정완료";


	const div = self.nextElementSibling;
	let text = "";
	const textarea = document.createElement("textarea");
	textarea.setAttribute("id",`textarea${id}`)
	const preview = document.createElement("div");
	preview.setAttribute("id",`preview${id}`)
	preview.setAttribute("class","preview")
	
	const newDiv = document.createElement("div");
	newDiv.setAttribute("class","flex_container_row")
	getContent(id)
		.then(response => response.text())
		.then(data => {
			text = data;
			textarea.value = text
			div.replaceWith(textarea)
			newDiv.appendChild(textarea)
			newDiv.appendChild(preview)
			self.parentElement.append(newDiv)
			self.replaceWith(newInput);
			previewInput(id)
		})
}
const markedOptions = {
	breaks: true,     // Enter로 줄바꿈을 처리
};
function previewInput(previewId){
	const markdownInput =document.getElementById(`textarea${previewId}`); 
	const preview = document.getElementById(`preview${previewId}`)
	preview.innerHTML = marked.parse(markdownInput.value,markedOptions);
	markdownInput.addEventListener("input",()=>{
		const markdownText = markdownInput.value;
		preview.innerHTML = marked.parse(markdownText,markedOptions)
	})
	
}


function saveContent(self) {
	
	const id = self.parentElement.getAttribute("data-id");
	const content = document.querySelector(`#textarea${id}`).value

	fetch("/api/mybag/content", {
		method: "put",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify({
			parent_id: id,
			content: content
		})
	}).then(window.location.reload())
}

