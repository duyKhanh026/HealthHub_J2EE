function saveEdit() {
    // Hiển thị thông báo trước khi gửi form
    Swal.fire({
        title: "Thành công",
        text: "Cập nhật chi tiết lịch khám thành công!",
        icon: "success",
        confirmButtonText: "OK"
    }).then((result) => {
        if (result.isConfirmed) {
            // Khi người dùng nhấn OK, gửi form
            document.getElementById("updateForm").submit();
        }
    });
}

function savethanhtoan(){
    Swal.fire({
            title: "Thành công",
            text: "Thanh toán thành công!",
            icon: "success",
            confirmButtonText: "OK"
        }).then((result) => {
            if (result.isConfirmed) {
                // Khi người dùng nhấn OK, gửi form
                document.getElementById("updateForm").submit();
            }
        });
}