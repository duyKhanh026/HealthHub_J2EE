
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

function saveEdit() {
        // Hiển thị thông báo trước khi gửi form
    Swal.fire({
        title: "Thành công",
        text: "Thêm bệnh nhân thành công!",
        icon: "success",
        confirmButtonText: "OK"
    }).then((result) => {
        if (result.isConfirmed) {
            // Khi người dùng nhấn OK, gửi form
            document.getElementById("updateForm").submit();
        }
    });
}