<template>
<div id="cover-form-container" :class="{'hidden': isHidden}">
    <div class="form-container">
        <button class="close-btn" @click="$emit('closeForm')">×</button>
        <div class="consult-form">
            <h2><span class="form-title">TƯ VẤN LỘ TRÌNH HỌC MIỄN PHÍ TẠI ROCKET</span></h2>
            <form>
            <div class="form-group">
                <input type="text" placeholder="Họ và tên">
                <input type="text" placeholder="Số điện thoại">
            </div>
            <input type="text" placeholder="Trường học (nếu có)">
            <input type="text" placeholder="Bạn cần bằng TOEIC để làm gì?">
            <input type="text" placeholder="Hãy chọn trình độ hiện tại của bạn">
            <input type="text" placeholder="Chọn tiếp mục tiêu bạn muốn chinh phục">
            <input type="text" placeholder="Lộ trình học TOEIC mà bạn quan tâm">
            <button type="submit" class="submit-btn">ĐĂNG KÝ NGAY</button>
            </form>
        </div>

        <!-- Phần Hình Ảnh Quảng Cáo -->
        <div class="promo-section">
            <div class="promo-content">
            <h2>Rocket</h2>
            <h3>TỪ TÌM HIỂU TRÌNH ĐỘ CỦA MÌNH QUA NHỮNH BÀI KIỂM TRA</h3>
            <ul>
                <li>ĐA DẠNG CÁC BÀI KIỂM TRA</li>
                <li>TRA ĐỔI VỚI NHỮNG NGƯỜI KHÁC</li>
                <li>CÁC BÀI KIỂM TRA SÁT VƠI THỰC TẾ</li>
            </ul>
            <button class="promo-btn" @click="navigateToExams">TÌM HIỂU NGAY</button>
            </div>
        </div>
    </div>
</div>

</template>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
}

body {
  background-color: #eaeaea;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

span{
    color: white;
}

#cover-form-container{
    width: 100vw;
    height: 100vw;
    background-color: rgba(0, 0, 0, 0.6);
    position: fixed;
    top: 0;
    left: 0;
}

/* Css cho nút x */
.close-btn {
  position: absolute;
  width: 30px;
  height: 30px;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 24px;
  font-weight: bold;
  color: white;
  cursor: pointer;
  z-index: 10;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #e65a2e;
  border-radius: 20px;
}

.form-container {
  display: flex;
  max-width: 900px;
  width: 100%;
  background-color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  border-radius: 8px;
  overflow: hidden;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translateX(-50%) translateY(-50%);
}

.consult-form {
  background-color: #05389d;
  color: white;
  padding: 20px;
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.consult-form h2 {
  text-align: center;
  margin-bottom: 20px;
  font-weight: bold;
  position: relative;
}

.form-title {
  border-bottom: 2px solid white;
  padding-bottom: 5px;
}

.form-group {
  display: flex;
  gap: 10px;
  width: 100%;
}

.form-group input,
.consult-form input {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid white;
  border-radius: 5px;
}

.consult-form input::placeholder {
  color: #e0e0e0;
}

.submit-btn {
  width: 100%;
  padding: 10px;
  background-color: #ff6f3d;
  color: white;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
}

.submit-btn:hover {
  background-color: #e65a2e;
}

.promo-section {
  background-color: #0558d6;
  color: white;
  padding: 20px;
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
}

.promo-content {
  text-align: center;
}

.promo-section h2 {
  font-size: 1.5em;
  font-weight: bold;
  color: #ff4400;
}

.promo-section h3 {
  font-size: 1.2em;
  margin: 10px 0;
  font-weight: bold;
}

.promo-section ul {
  list-style: none;
  text-align: left;
  padding: 0;
  margin: 20px 0;
}

.promo-section ul li {
  background-color: #0070ff;
  padding: 10px;
  margin: 5px 0;
  border-radius: 5px;
  color: white;
}

.promo-btn {
  padding: 10px 20px;
  background-color: #ff6f3d;
  color: white;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
}

.promo-btn:hover {
  background-color: #e65a2e;
}

.promo-image {
  max-width: 150px;
  position: absolute;
  right: 10px;
  bottom: 10px;
  border-radius: 5px;
}

</style>

<script>
export default{
    name: "FormAdvise",
    props: {
          isHidden: {
          type: Boolean,
          default: true
      }
    },
    methods: {
    navigateToExams() {
      this.$emit('closeForm');  
      this.$router.push('/exams'); 
    }
  }
}
</script>