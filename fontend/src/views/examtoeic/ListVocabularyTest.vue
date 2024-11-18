<template>
  <div class="d-flex flex-column min-vh-100">
    <main class="flex-grow-1 d-flex align-items-center justify-content-center my-3">
      <div class="container">
        <!-- Tiêu đề trang -->
        <div class="text-center">
          <h3 class="page-header text-primary fw-bold">Luyện Tập Từ Vựng</h3>
          <p class="text-muted">Danh sách các bài thi từ vựng, hãy chọn bài thi để bắt đầu!</p>
        </div>

        <!-- Thông báo lỗi -->
        <div v-if="errorMessage" class="alert alert-danger text-center mt-3">
          {{ errorMessage }}
        </div>

        <!-- Danh sách các bài thi từ vựng -->
        <div class="row mt-4">
          <div
              v-for="(vocab) in vocabList"
              :key="vocab.vocabularyid"
              class="col-12 d-flex justify-content-center align-items-center mb-3"
          >
            <div class="card shadow-sm d-flex flex-row align-items-center" style="width: 100%; max-width: 800px;">
              <img
                  :src="vocab.vocabularyimage"
                  alt="Vocabulary Image"
                  class="card-img-left"
              />
              <div class="card-body text-start">
                <h5 class="card-title text-primary fw-bold">{{ vocab.vocabularyname }}</h5>
                <p class="card-text text-muted">Thi từ vựng về chủ đề "{{ vocab.vocabularyname }}".</p>
                <h5 class="card-title text-muted">Thời gian: 30 phút</h5>
                <button
                    class="btn btn-primary"
                    @click="$router.push({ name: 'VocabularyTest', params: { id: vocab.vocabularyid } })"
                >
                  Bắt đầu thi
                </button>
              </div>
            </div>
          </div>
        </div>

        <!-- Modal bắt đầu thi -->
        <div
            v-if="selectedLesson"
            class="modal fade show d-block"
            tabindex="-1"
            style="background-color: rgba(0, 0, 0, 0.5);"
        >
          <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title text-primary fw-bold">
                  Học từ vựng: {{ selectedLesson.vocabularyname }}
                </h5>
                <button type="button" class="btn-close" @click="closeLesson"></button>
              </div>
              <div class="modal-body">
                <p>Danh sách từ vựng sẽ được tải tại đây...</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

// Biến trạng thái
const vocabList = ref([]);
const selectedLesson = ref(null);
const errorMessage = ref('');

// Tải danh sách bài học từ vựng
const loadVocabLessons = async () => {
  try {
    const { data } = await axios.get('http://localhost:8080/api/admin/vocab/loadVocab'); // Thay bằng endpoint API của bạn
    vocabList.value = data.map((vocab) => ({
      vocabularyid: vocab.vocabularyid,
      vocabularyname: vocab.vocabularyname,
      vocabularyimage: `http://localhost:8080${vocab.vocabularyimage}`, // Tùy chỉnh đường dẫn ảnh nếu cần
    }));
  } catch (error) {
    console.error('Lỗi khi tải danh sách bài thi từ vựng:', error);
    errorMessage.value = 'Không thể tải danh sách bài thi từ vựng. Vui lòng thử lại sau.';
  }
};

// Đóng modal bài học
const closeLesson = () => {
  selectedLesson.value = null;
};

// Tải dữ liệu khi khởi tạo
onMounted(() => {
  loadVocabLessons();
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
