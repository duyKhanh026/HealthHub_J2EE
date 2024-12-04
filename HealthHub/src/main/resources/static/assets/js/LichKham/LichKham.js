//Biến toàn cục kiểm tra captcha
let captcha_solved = false;

function enableButton() {
	captcha_solved = true;
}

// Hàm được gọi khi reCAPTCHA hết hạn
function disableButton() {
    captcha_solved = false;
}


document.getElementById("date").addEventListener("change", function () {
    const selectedDate = this.value;
    const currentDate = new Date();
    const currentTime = currentDate.getHours() * 60 + currentDate.getMinutes(); // Thời gian hiện tại tính bằng phút

    fetch(`api/getAvailableTimes?date=${selectedDate}`)
        .then(response => response.json())
        .then(data => {
            const timeSelect = document.getElementById("time");
            timeSelect.innerHTML = ""; // Xóa các option cũ

            // Lọc các giờ không phải "Nghỉ" và lớn hơn giờ hiện tại
            const availableTimes = data.filter(time => {
                // Chuyển giờ từ chuỗi sang phút
                const [hours, minutes] = time.split(":").map(Number);
                const timeInMinutes = hours * 60 + minutes;

                // Loại bỏ giờ đã qua nếu là ngày hiện tại
                if (new Date(selectedDate).toDateString() === currentDate.toDateString()) {
                    return timeInMinutes > currentTime && time.trangThai !== 'Nghỉ';
                }

                return time.trangThai !== 'Nghỉ'; // Nếu không phải ngày hiện tại, chỉ lọc trạng thái "Nghỉ"
            });

            // Thêm các giờ hợp lệ vào select
            availableTimes.forEach(time => {
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
		
		if (!captcha_solved) {
    		document.querySelector(".recaptcha-error-message").style.display = "block"; 
			return;
		}
		
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


//document.getElementById("date").addEventListener("change", function () {
//    const selectedDate = this.value;
//
//    fetch(`/checkDate?date=${selectedDate}`)
//        .then(response => response.json())
//        .then(hasAppointment => {
//            if (hasAppointment) {
//                // Hiển thị thông báo
//                Swal.fire({
//                    icon: "warning",
//                    title: "Lịch đã tồn tại",
//                    text: "Bạn đã có lịch khám trong ngày này. Vui lòng hủy lịch cũ trước khi đặt lịch mới.",
//                });
//
//                // Vô hiệu hóa nút "Make an Appointment"
//                document.getElementById("submitButton").disabled = true;
//            } else {
//                // Bật lại nút "Make an Appointment"
//                document.getElementById("submitButton").disabled = false;
//            }
//        })
//        .catch(error => console.error("Error checking date:", error));
//});