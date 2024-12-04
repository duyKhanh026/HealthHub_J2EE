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

function makeReadonly() {
    // Lấy phần tử input số tiền và đặt readonly
    const soTienInput = document.querySelector('input[name="soTien"]');
    if (soTienInput) {
        soTienInput.readOnly = true;
    }
}
// nếu thanh toán r thi dong nut thanh toan, hien thi nut pdf
document.addEventListener("DOMContentLoaded", function () {
    const trangThaiInput = document.getElementById("trangThaiInput");
    const thanhToanBtns = document.querySelectorAll(".btn-thanh-toan");
    const xuatPdfBtn = document.querySelector(".btn-xuat-pdf");
        const soTienInput = document.getElementById("soTienInput");

    if (trangThaiInput.value === "Đã thanh toán") {

    soTienInput.setAttribute("readonly", "true");
        // Ẩn các nút thanh toán
//        thanhToanBtns.forEach(btn => btn.style.display = "none");
//        // Hiện nút xuất PDF
//        xuatPdfBtn.style.display = "block";
    } else {
        // Hiện các nút thanh toán
//        thanhToanBtns.forEach(btn => btn.style.display = "block");
//        // Ẩn nút xuất PDF
//        xuatPdfBtn.style.display = "none";
    }
});

// neu da kham roi thi dong các thong tin kham
//document.addEventListener("DOMContentLoaded", function () {
//    const trangThaiInput = document.getElementById("trangThaiInput"); // Trạng thái
//    const chuanDoanInput = document.getElementById("chuanDoanInput"); // Chuẩn đoán
//    const donThuocInput = document.getElementById("donThuocInput"); // Đơn thuốc
//    const ghiChuThemInput = document.getElementById("ghiChuThemInput"); // Ghi chú thêm
//
//    // Kiểm tra nếu trạng thái là "Đã khám"
//    if (trangThaiInput && trangThaiInput.value === "Đã khám") {
//        // Nếu trạng thái là "Đã khám", đặt các trường còn lại thành readonly
//        if (chuanDoanInput) chuanDoanInput.readOnly = true;
//        if (donThuocInput) donThuocInput.readOnly = true;
//        if (ghiChuThemInput) ghiChuThemInput.readOnly = true;
//    }
//});