    document.getElementById("date").addEventListener("change", function() {
    const selectedDate = this.value;

<!--    alert("chon ngay thanh cong" + selectedDate);-->


    fetch(`api/getAvailableTimes?date=${selectedDate}`)
        .then(response => response.json())
        .then(data => {
            const timeSelect = document.getElementById("time");
            timeSelect.innerHTML = ""; // Xóa các option cũ

            // Lọc các thời gian không phải là "Nghỉ"
            const availableTimes = data.filter(time => time.trangThai !== 'Nghỉ');

            // Thêm các giờ trống vào select
            data.forEach(time => {
                const option = document.createElement("option");
                option.value = time;
                option.textContent = time;
                timeSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching available times:', error));
});

    document.addEventListener("DOMContentLoaded", function() {
    const dateInput = document.getElementById("date");

    // Lấy ngày hiện tại dưới dạng 'YYYY-MM-DD'
    const today = new Date();
    const formattedDate = today.toISOString().split('T')[0]; // Chuyển đổi thành định dạng 'YYYY-MM-DD'

    // Cập nhật thuộc tính 'min' để chỉ cho phép chọn ngày từ hôm nay trở đi
    dateInput.setAttribute("min", formattedDate);
});

function checkinput(){
const name = document.getElementById("name").value.trim();
        const email = document.getElementById("email").value.trim();
        const phone = document.getElementById("phone").value.trim();
        const date = document.getElementById("date").value.trim();
        const time = document.getElementById("time").value.trim();

        // Kiểm tra từng trường
        if (!name) {
            alert("⚠️ Vui lòng nhập tên người khám.");
            return;
        }
        if (!email) {
            alert("⚠️ Vui lòng nhập email.");
            return;
        }
        if (!phone) {
            alert("⚠️ Vui lòng nhập số điện thoại.");
            return;
        }
        if (!date) {
            alert("⚠️ Vui lòng chọn ngày đặt khám.");
            return;
        }
        if (!time) {
            alert("⚠️ Vui lòng chọn giờ đặt khám.");
            return;
        }

        // Nếu tất cả trường hợp lệ, gửi form
        saveEdit();
}

function saveEdit() {
        // Hiển thị thông báo trước khi gửi form
    Swal.fire({
                title: "Đang xử lý...",
                html: '<div class="progress" style="height: 20px;">' +
                      '<div id="progress-bar" class="progress-bar progress-bar-striped progress-bar-animated" ' +
                      'role="progressbar" style="width: 0%;"></div></div>',
                showConfirmButton: false,
                allowOutsideClick: false,
                didOpen: () => {
                    let progressBar = document.getElementById("progress-bar");
                    let progress = 0;

                    // Tăng giá trị thanh tiến trình mỗi 50ms
                    let interval = setInterval(() => {
                        progress += 1;
                        progressBar.style.width = progress + "%";
                        progressBar.innerText = progress + "%";

                        if (progress >= 100) {
                            clearInterval(interval);
                            // Hiển thị thông báo thành công sau khi hoàn thành
                            Swal.fire({
                                title: "Thành công",
                                text: "Đặt lịch khám thành công!",
                                icon: "success",
                                confirmButtonText: "OK"
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    // Ví dụ: xử lý logic sau khi xác nhận
                                    document.getElementById("updateForm").submit();
                                }
                            });
                        }
                    }, 50); // 50ms * 100 = 5000ms (5 giây)
                }
            });
}