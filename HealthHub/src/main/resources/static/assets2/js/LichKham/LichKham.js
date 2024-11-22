function openFormAdd() {

    document.getElementById("addForm").style.display = "block";
}

function closeFormAdd() {

    document.getElementById("addForm").style.display = "none";
}

var addButton = document.getElementById('addButton');

addButton.addEventListener('click', function(event) {

    openFormAdd();
});