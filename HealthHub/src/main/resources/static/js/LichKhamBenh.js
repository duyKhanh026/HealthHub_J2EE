function viewDetails(button) {
  const maLK = button.getAttribute("data-id");
  fetch(`/lichkham/details/${maLK}`)
          .then(response => response.json())
          .then(data => {
            document.getElementById("maLK").textContent = data.maLK;
            document.getElementById("hoTen").textContent = data.hoten;
            document.getElementById("email").textContent = data.email;
            document.getElementById("sdt").textContent = data.sDT;
            document.getElementById("note").textContent = data.note;
            document.getElementById("ngayGioDatKham").textContent = data.ngayGioDatKham;
            document.getElementById("trangThai").textContent = data.trangThai;

            // Hiển thị chi tiết chỉ khi trạng thái là "Đã khám"
            if (data.trangThai === "Đã khám") {
              document.getElementById("maHS").textContent = data.maHS;
              document.getElementById("chuanDoan").textContent = data.chuandoan;
              document.getElementById("donThuoc").textContent = data.donthuoc;
              document.getElementById("danDo").textContent = data.dando;
              // Hiển thị chi tiết
              document.getElementById("chiTietConfirmed").style.display = "block"; // Đảm bảo hiển thị div
            } else {
              document.getElementById("chiTietConfirmed").style.display = "none";
            }

            // Hiển thị nút hủy nếu trạng thái là "Chưa khám"
            if (data.trangThai === "Chưa khám") {
              document.getElementById("cancelButtonContainer").style.display = "block";
            } else {
              document.getElementById("cancelButtonContainer").style.display = "none";
            }

            // Mở modal
            new bootstrap.Modal(document.getElementById("detailModal")).show();

          })
          .catch(error => console.error("Error:", error));
}

function cancelLichKham() {
  const maLK = document.getElementById("maLK").textContent;
  // Hiển thị hộp thoại xác nhận hủy
  Swal.fire({
      title: 'Bạn có chắc chắn muốn hủy lịch khám này?',
      showCancelButton: true,
      confirmButtonText: 'Hủy lịch',
      cancelButtonText: 'Quay lại',
      icon: 'warning',
      reverseButtons: true
  }).then((result) => {
      if (result.isConfirmed) {
          // Gửi yêu cầu hủy lịch khám
          fetch(`/lichkham/delete/${maLK}`, {
              method: "POST"
          })
          .then(response => {
              if (response.ok) {
                  Swal.fire({
                      icon: 'success',
                      title: 'Lịch khám đã được hủy thành công.',
                      showConfirmButton: false,
                      timer: 1500 // Hiển thị thông báo thành công trong 1.5 giây
                  }).then(() => {
                      window.location.reload(); // Reload lại trang sau khi hủy
                  });
              } else {
                  Swal.fire({
                      icon: 'error',
                      title: 'Hủy lịch khám thất bại.',
                      text: 'Vui lòng thử lại.',
                      showConfirmButton: true
                  });
              }
          })
          .catch(error => {
              console.error("Error:", error);
              Swal.fire({
                  icon: 'error',
                  title: 'Lỗi',
                  text: 'Đã có lỗi xảy ra. Vui lòng thử lại.',
                  showConfirmButton: true
              });
          });
      }
  });
}

function filterLichKham() {
  const startDate = document.getElementById('startDate').value;
  const endDate = document.getElementById('endDate').value;

  // Kiểm tra nếu người dùng chưa chọn đầy đủ ngày
  if (!startDate || !endDate) {
      Swal.fire({
          icon: 'warning',
          title: 'Thiếu thông tin',
          text: 'Vui lòng chọn đầy đủ Ngày bắt đầu và Ngày kết thúc!'
      });
      return; // Dừng thực thi nếu chưa chọn ngày
  }

  // Kiểm tra nếu ngày kết thúc bé hơn ngày bắt đầu
  if (new Date(endDate) < new Date(startDate)) {
      Swal.fire({
          icon: 'error',
          title: 'Lỗi ngày chọn',
          text: 'Ngày kết thúc không được nhỏ hơn Ngày bắt đầu!'
      });
      return; // Dừng thực thi nếu điều kiện không hợp lệ
  }

  const url = `/api/lich-kham/filter?startDate=${startDate}&endDate=${endDate}`;

  fetch(url)
      .then(response => response.json())
      .then(data => {
          updateLichKhamList(data); // Cập nhật danh sách lịch khám với dữ liệu đã lọc
      })
      .catch(error => console.error('Error filtering Lich Kham:', error));
}

function viewAllLichKham() {
  fetch("/lichkham/all")
    .then(response => response.json())
    .then(data => {
      console.log(data); // Log để xem dữ liệu trả về từ API
      updateLichKhamList(data);
    })
    .catch(error => console.error("Error:", error));
}

function updateLichKhamList(data) {
  const lichKhamList = document.querySelector('.list-group'); // Sử dụng class list-group thay vì id
  if (lichKhamList) {
      // Tiến hành cập nhật
      lichKhamList.innerHTML = ""; // Xóa list trước khi làm

      data.forEach(lichKham => {
      const listItem = `
        <div class="list-group-item">
          <div class="d-flex justify-content-between align-items-center">
            <div>
              <h5>${lichKham.ngayGioDatKham}</h5>
              <p>Mã: ${lichKham.maLK}</p>
            </div>
            <div class="status-column">
              <span class="badge ${lichKham.trangThai === 'Đã khám' ? 'bg-success' : (lichKham.trangThai === 'Chưa khám' ? 'bg-danger' : 'bg-secondary')}">
                ${lichKham.trangThai}
              </span>
            </div>
            <a class="btn btn-primary btn-sm" data-id="${lichKham.maLK}" onclick="viewDetails(this)">Xem Chi Tiết</a>
          </div>
        </div>`;
      lichKhamList.insertAdjacentHTML("beforeend", listItem);
     });
  } else {
      console.error('Không tìm thấy phần tử với lớp list-group');
  }
}