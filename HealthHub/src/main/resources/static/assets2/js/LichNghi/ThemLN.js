function openFormAdd() {
    alert("Opening the form...");  // Alert khi mở form
    document.getElementById("addForm").style.display = "block";
}

function closeFormAdd() {
    alert("Closing the form...");  // Alert khi đóng form
    document.getElementById("addForm").style.display = "none";
}

var addButton = document.getElementById('addButton');

addButton.addEventListener('click', function(event) {
    alert("Add button clicked");  // Alert khi nhấn nút thêm
    openFormAdd();
});
