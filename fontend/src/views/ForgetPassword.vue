<template>
  <main class="container" style="margin-left: 30%;">
    <div class="d-flex justify-content-center align-items-center vh-100 bg-light">
      <div class="card p-4 shadow" style="max-width: 400px; width: 100%;">
        <h2 class="text-center mb-3">Quên Mật Khẩu</h2>
        <p class="text-muted text-center mb-4">
          Nhập địa chỉ email của bạn để nhận liên kết đặt lại mật khẩu.
        </p>
        <form @submit="handleSubmit">
          <div class="mb-3">
            <label for="email" class="form-label">Địa chỉ email</label>
            <input
                type="email"
                id="email"
                v-model="email"
                class="form-control"
                placeholder="Nhập email của bạn"
                required
            />
            <small v-if="errorMessage" class="text-danger">
              {{ errorMessage }}
            </small>
          </div>
          <button type="submit" class="btn btn-primary w-100" style="margin-bottom: 5px; margin-top: 10px;">
            Đặt lại mật khẩu
          </button>
        </form>
        <div class="text-center mt-3" style="margin-bottom: 5px;">
          <router-link to="/login" class="text-decoration-none">
            Quay lại trang đăng nhập
          </router-link>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import axios from "axios"; // Import axios đúng cách

export default {
  data() {
    return {
      email: "",
      errorMessage: "", // Biến để lưu thông báo lỗi
    };
  },
  methods: {
    async handleSubmit() {
      // Kiểm tra định dạng email
      if (!this.validateEmail(this.email)) {
        this.errorMessage = "Email phải có định dạng hợp lệ và kết thúc bằng @gmail.com.";
        return;
      }
      this.errorMessage = ""; // Xóa lỗi nếu hợp lệ

      try {
        // Gửi yêu cầu HTTP bằng axios, nhưng không cần sử dụng response
        await axios.put(
            `http://localhost:8080/api/admin/profile/forgetPass/${this.email}`, // Dùng backtick để tạo chuỗi mẫu
            { email: this.email },
            { withCredentials: true }
        );

        // Thông báo thành công
        alert(`Email đặt lại mật khẩu đã được gửi đến: ${this.email}`);
      } catch (error) {
        // Nếu có lỗi, xử lý thông báo lỗi
        console.error("Có lỗi khi gửi email:", error);
        alert("Không thể gửi email. Vui lòng thử lại.");
      }
    },
    validateEmail(email) {
      // Biểu thức regex kiểm tra email phải có đuôi @gmail.com
      const gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
      return gmailRegex.test(email);
    },
  },
};
</script>

<style scoped>
body {
  background-color: #f8f9fa;
}
</style>
