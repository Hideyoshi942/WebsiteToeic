<template>
  <div class="d-flex flex-column min-vh-100">
    <main class="flex-grow-1 container mt-5">
      <div class="text-center mb-4">
        <h3 class="page-header text-primary fw-bold">Thi thử</h3>
        <p class="text-muted">Danh sách các bài thi thử, hãy chọn bài thi để bắt đầu!</p>
      </div>

      <!-- Trạng thái đang tải -->
      <div v-if="isLoading" class="text-center">
        <p>Đang tải danh sách bài thi...</p>
      </div>

      <!-- Thông báo lỗi -->
      <div v-if="errorMessage && !isLoading" class="alert alert-danger text-center mt-3">
        {{ errorMessage }}
      </div>

      <!-- Danh sách các bài thi ngữ pháp -->
      <div v-if="!isLoading && practiceTestList.length > 0" class="row mt-4" style="margin-bottom: 20px;">
        <div
            v-for="(practicetest) in practiceTestList"
            :key="practicetest.practicetestid"
            class="col-12 d-flex justify-content-center align-items-center mb-3"
        >
          <div class="card shadow-sm d-flex flex-row align-items-center" style="width: 100%; max-width: 800px;">
            <img :src="practicetest.practicetestimage" alt="Grammar Image" class="card-img-left" />
            <div class="card-body text-start">
              <h5 class="card-title text-primary fw-bold">{{ practicetest.practicetestname }}</h5>
              <p class="card-text text-muted">Thi thử về chủ đề "{{ practicetest.practicetestname }}".</p>
              <h5 class="card-title text-muted">Thời gian: 30 phút</h5>
              <button
                  class="btn btn-primary"
                  @click="$router.push({ name: 'PracticeTestToeic', params: { id: practicetest.practicetestid } })"
              >
                Bắt đầu thi
              </button>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

// Biến trạng thái
const practiceTestList = ref([]); // Danh sách bài thi
const errorMessage = ref(""); // Lỗi khi tải dữ liệu
const isLoading = ref(false); // Trạng thái đang tải dữ liệu

// Tải danh sách bài thi ngữ pháp
const loadGrammarLessons = async () => {
  isLoading.value = true; // Bắt đầu tải
  try {
    // Gọi API
    const response = await axios.get(
        "http://localhost:8080/api/admin/practicetest/loadpracticetest" // Đường dẫn API
    );

    // Kiểm tra nếu API trả về dữ liệu
    if (response.data && response.data.length > 0) {
      // Chuyển đổi dữ liệu sang định dạng cần thiết
      practiceTestList.value = response.data.map((practicetest) => ({
        practicetestid: practicetest.practicetestid,
        practicetestname: practicetest.practicetestname,
        practicetestimage: `http://localhost:8080${practicetest.practicetestimage}`, // Đường dẫn ảnh
      }));
    } else {
      // Không có dữ liệu
      errorMessage.value = "Không có bài thi thử nào.";
    }
  } catch (error) {
    console.error("Lỗi khi tải danh sách bài thi thử:", error);
    errorMessage.value = "Không thể tải danh sách bài thi thử. Vui lòng thử lại sau.";
  } finally {
    isLoading.value = false; // Kết thúc tải
  }
};

// Tải dữ liệu khi khởi tạo
onMounted(() => {
  loadGrammarLessons();
});
</script>

<style scoped>
/* Định dạng container */
.container {
  max-width: 800px;
  margin: auto;
}

/* Định dạng container */
.container {
  max-width: 800px;
  margin: auto;
}

/* Định dạng card */
.card {
  display: flex;
  flex-direction: row;
  align-items: center;
  border: none;
  border-radius: 10px;
  transition: transform 0.2s ease-in-out, box-shadow 0.3s ease-in-out;
  padding: 10px;
  gap: 20px; /* Khoảng cách giữa ảnh và nội dung */
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

/* Định dạng hình ảnh bên trái */
.card-img-left {
  width: 150px;
  height: 150px;
  object-fit: cover;
  border-radius: 10px;
  flex-shrink: 0; /* Ngăn hình ảnh bị co lại */
}

/* Nội dung bên phải */
.card-body {
  flex: 1; /* Đảm bảo nội dung chiếm phần còn lại của card */
}

.card-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.card-text {
  font-size: 14px;
  color: #6c757d;
  margin-bottom: 15px;
}

/* Nút bắt đầu thi */
.btn {
  font-size: 14px;
  font-weight: bold;
  padding: 10px;
  border-radius: 8px;
  transition: background-color 0.3s ease-in-out, color 0.3s ease-in-out;
}

.btn-primary {
  background-color: #007bff;
  border: none;
}

.btn-primary:hover {
  background-color: #0056b3;
}

/* Khoảng cách giữa các phần tử */
.row > .col-12 {
  margin-bottom: 20px;
}


/* Định dạng nút */
.btn {
  font-size: 14px;
  font-weight: bold;
  padding: 10px;
  border-radius: 8px;
  transition: background-color 0.3s ease-in-out, color 0.3s ease-in-out;
}

.btn-primary {
  background-color: #007bff;
  border: none;
}

.btn-primary:hover {
  background-color: #0056b3;
}

/* Khoảng cách giữa các card */
.row > .col-12 {
  margin-bottom: 20px;
}

/* Modal */
.modal-content {
  border-radius: 15px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
}

.modal-header {
  border-bottom: 2px solid #007bff;
  padding: 20px;
  background-color: #f8f9fa;
}

.modal-title {
  font-size: 20px;
  color: #007bff;
}

.modal-body {
  max-height: 400px;
  overflow-y: auto;
  padding: 20px;
  font-size: 16px;
  color: #6c757d;
}

/* Thông báo lỗi */
.alert {
  font-size: 16px;
  font-weight: bold;
  border-radius: 8px;
}

.alert-danger {
  background-color: #f8d7da;
  color: #842029;
  border: 1px solid #f5c2c7;
}
</style>
