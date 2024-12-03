document.addEventListener('DOMContentLoaded', () => {
    const ctx = document.getElementById('myLineChart');
    if (ctx) {
        const chartContext = ctx.getContext('2d');
        let myLineChart = new Chart(chartContext, {
            type: 'line',
            data: {
                labels: [],
                datasets: [{
                    label: 'Số lượng lịch khám',
                    data: [],
                    backgroundColor: 'rgba(78, 115, 223, 0.05)',
                    borderColor: 'rgba(78, 115, 223, 1)',
                    pointBackgroundColor: 'rgba(78, 115, 223, 1)',
                    pointBorderColor: 'rgba(78, 115, 223, 1)',
                    pointHoverBackgroundColor: 'rgba(78, 115, 223, 1)',
                    pointHoverBorderColor: 'rgba(78, 115, 223, 1)',
                    fill: true,
                    tension: 0.4
                }]
            },
            options: {
                responsive: true,
                scales: {
                    x: { title: { display: true, text: 'Ngày' } },
                    y: { title: { display: true, text: 'Số lượng lịch khám' }, beginAtZero: true }
                }
            }
        });

        // Xử lý nút "Lọc"
        document.getElementById('filterButton').addEventListener('click', async function () {
            const startDate = document.getElementById('startDate').value;
            const endDate = document.getElementById('endDate').value;
            const status = document.getElementById('status').value;

            const startDateTime = new Date(startDate).toISOString().slice(0, 19);
            const endDateTime = new Date(endDate).toISOString().slice(0, 19);

            try {
                const response = await fetch(`/ThongKe/data?startDate=${startDateTime}&endDate=${endDateTime}&status=${status}`);
                if (!response.ok) throw new Error('Lỗi khi lấy dữ liệu từ API.');

                const data = await response.json();
                myLineChart.data.labels = data.labels;
                myLineChart.data.datasets[0].data = data.data;
                myLineChart.update();
            } catch (error) {
                console.error('Có lỗi xảy ra:', error);
                alert('Không thể tải dữ liệu. Vui lòng thử lại sau.');
            }
        });
    } else {
        console.error("Canvas với ID 'myLineChart' không tồn tại.");
    }
});
