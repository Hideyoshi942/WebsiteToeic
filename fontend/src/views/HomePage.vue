<script setup>
import { ref, onMounted, onUnmounted } from 'vue';

// Lấy thông tin người dùng
const userToeic = JSON.parse(localStorage.getItem('usertoeic') || '{}');
const username = ref(userToeic.username || 'Khách');

// Countdown logic
const timeRemaining = ref("");

const updateCountdown = () => {
  const now = new Date();

  // Lấy các thành phần thời gian
  const year = now.getFullYear();
  const month = String(now.getMonth() + 1).padStart(2, '0'); // Tháng bắt đầu từ 0 nên cần +1
  const date = String(now.getDate()).padStart(2, '0');
  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const seconds = String(now.getSeconds()).padStart(2, '0');

  const weekdays = ['Chủ nhật', 'Thứ Hai', 'Thứ Ba', 'Thứ Tư', 'Thứ Năm', 'Thứ Sáu', 'Thứ Bảy'];
  const day = weekdays[now.getDay()];

  // Định dạng kết quả
  return timeRemaining.value = `${hours}:${minutes}:${seconds} ${day}, ngày ${date}-${month}-${year}`;
};

let intervalId;
onMounted(() => {
  updateCountdown(); // Cập nhật ngay khi load
  intervalId = setInterval(updateCountdown, 1000); // Cập nhật mỗi giây
});

onUnmounted(() => {
  clearInterval(intervalId); // Xóa interval khi component bị hủy
});
</script>

<template>
  <div class="bgimg">
    <div class="topleft">
      <p>Logo</p>
    </div>
    <div class="middle">
      <h1>COMING SOON</h1>
      <p style="color: white;">Welcome, {{ username }}</p>
      <hr>
      <p style="color: white;">{{ timeRemaining }}</p>
    </div>
    <div class="bottomleft">
      <p>Welcome, {{ username }}</p>
    </div>
  </div>
</template>

<style scoped>
/* Đặt toàn bộ trang web với chiều cao 100% */
body, html {
  margin: 0;
  padding: 0;
  height: 100%;
  font-family: "Courier New", Courier, monospace;
}

/* Phần hình nền */
.bgimg {
  background-image: url('https://img.freepik.com/premium-photo/paper-with-text-toeic-yellow-background-with-stationery_384017-7414.jpg');
  height: 300px;
  background-position: center;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  text-align: center;
}

/* Vùng nội dung chính */
.middle {
  max-width: 600px;
  padding: 20px;
  background: rgba(0, 0, 0, 0.6); /* Nền tối nhẹ */
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.4);
}

.middle h1 {
  font-size: 48px;
  margin-bottom: 20px;
  font-weight: bold;
  color: #ffd700; /* Màu vàng sáng cho chữ */
}

.middle p {
  font-size: 18px;
  margin: 10px 0;
}

.middle hr {
  width: 50%;
  margin: 20px auto;
  border: 0;
  border-top: 2px solid white;
}

/* Góc trên trái */
.topleft {
  position: absolute;
  top: 20px;
  left: 20px;
  font-size: 16px;
  color: white;
}

/* Góc dưới trái */
.bottomleft {
  position: absolute;
  bottom: 20px;
  left: 20px;
  font-size: 16px;
  color: white;
}
</style>