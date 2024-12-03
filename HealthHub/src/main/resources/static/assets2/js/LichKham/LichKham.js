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

document.getElementById("date").addEventListener("change", function() {
    const selectedDate = this.value;


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


    function saveEdit() {
    // Hiển thị thông báo trước khi gửi form
    Swal.fire({
        title: "Thành công",
        text: "Đặt lịch khám thành công!",
        icon: "success",
        confirmButtonText: "OK"
    }).then((result) => {
        if (result.isConfirmed) {
            // Khi người dùng nhấn OK, gửi form
            document.getElementById("updateForm").submit();
        }
    });
}