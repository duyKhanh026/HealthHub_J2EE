<script>
    function validateForm() {
        // Lấy các trường dữ liệu cần kiểm tra
        const fields = [
            document.getElementById("ten"),
            document.getElementById("ngaysinh"),
            document.getElementById("gioitinh"),
            document.getElementById("sdt"),
            document.getElementById("email"),
            document.getElementById("address"),
            document.getElementById("benh")
        ];

        // Kiểm tra nếu có trường nào bị bỏ trống
        for (let field of fields) {
            if (field.value.trim() === "") {
                alert("Vui lòng điền đầy đủ thông tin vào tất cả các trường.");
                field.focus(); // Đưa con trỏ đến trường bị thiếu
                return false; // Ngăn form gửi đi
            }
        }
        return true; // Cho phép form gửi đi nếu tất cả các trường đã điền đủ
    }
</script>