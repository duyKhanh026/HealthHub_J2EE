//// Hàm mở form thêm
//function openFormAdd() {
//    const form = document.getElementById("addForm");
//    form.classList.add("show"); // Thêm lớp 'show' để hiển thị form
//}
//
//// Hàm đóng form thêm
//function closeFormAdd() {
//    const form = document.getElementById("addForm");
//    form.classList.remove("show"); // Xóa lớp 'show' để ẩn form
//}
//
//// Hàm mở form sửa
//function openFormEdit(benhNhanData) {
//    // Đổ dữ liệu vào form sửa
//    document.getElementById("ED_BN_ID").value = benhNhanData.MaBN;
//    document.getElementById("ED_BN_Name").value = benhNhanData.HoTen;
//    document.getElementById("ED_BN_Date").value = benhNhanData.NgaySinh ? benhNhanData.NgaySinh.split('T')[0] : '';
//    document.getElementById("ED_BN_GT").value = benhNhanData.Gioitinh;
//    document.getElementById("ED_BN_Phone").value = benhNhanData.SDT;
//    document.getElementById("ED_BN_Email").value = benhNhanData.Email;
//    document.getElementById("ED_BN_ADR").value = benhNhanData.Diachi;
//    document.getElementById("ED_BN_His").value = benhNhanData.Tiensubenh;
//
//    // Hiển thị form sửa
//    const formEdit = document.getElementById("editForm");
//    formEdit.classList.add("show");
//}
//
//// Hàm đóng form sửa
//function closeFormEdit() {
//    const formEdit = document.getElementById("editForm");
//    formEdit.classList.remove("show");
//}
//
//// Gắn sự kiện click cho các nút sửa
//document.addEventListener("DOMContentLoaded", function() {
//    document.querySelectorAll('.openFormButton').forEach(button => {
//        button.addEventListener('click', function(event) {
//            event.preventDefault();
//            // Lấy hàng chứa nút "Sửa"
//            const row = this.closest('tr');
//            // Lấy dữ liệu từ các ô trong hàng
//            const benhNhanData = {
//                MaBN: row.children[1].innerText.trim(),
//                HoTen: row.children[2].innerText.trim(),
//                NgaySinh: row.children[3].innerText.trim(),
//                Gioitinh: row.children[4].innerText.trim(),
//                SDT: row.children[5].innerText.trim(),
//                Email: row.children[6].innerText.trim(),
//                Diachi: row.children[7].innerText.trim(),
//                Tiensubenh: row.children[8].innerText.trim()
//            };
//            // Gọi hàm mở form sửa với dữ liệu bệnh nhân
//            openFormEdit(benhNhanData);
//        });
//    });
//});
