<template>
  <div class="d-flex flex-column min-vh-100"> <!-- Thêm Flexbox để căn giữa -->
    <AppHeader></AppHeader>
    <main class="flex-grow-1 d-flex align-items-center justify-content-center my-3">
      <div class="container">
        <!-- Tiêu đề trang -->
        <div class="text-center">
          <h3 class="page-header text-primary fw-bold">Học Từ Vựng</h3>
          <p class="text-muted">Danh sách các bài học từ vựng, hãy chọn bài học để bắt đầu!</p>
        </div>

        <!-- Thông báo lỗi -->
        <div v-if="errorMessage" class="alert alert-danger text-center mt-3">
          {{ errorMessage }}
        </div>

        <!-- Danh sách các bài học từ vựng -->
        <div class="row mt-4 gy-4 m-3" style="margin-left: 120px;">
          <div
              v-for="(vocab) in vocabList"
              :key="vocab.vocabularyid"
              class="col-md-4 d-flex justify-content-center align-items-center"
          >
            <div class="card h-100 shadow-sm text-center" style="width: 18rem;">
              <img
                  :src="vocab.vocabularyimage"
                  alt="Vocabulary Image"
                  class="card-img-top"
                  style="height: 200px; object-fit: cover; border-radius: 10px 10px 0 0;"
              />
              <div class="card-body d-flex flex-column justify-content-between">
                <h5 class="card-title text-primary fw-bold">{{ vocab.vocabularyname }}</h5>
                <p class="card-text text-muted">Học từ vựng về chủ đề "{{ vocab.vocabularyname }}".</p>
                <button
                    class="btn btn-primary mt-auto"
                    @click="$router.push({ name: 'VocabularyLessonContent', params: { id: vocab.vocabularyid } })"
                >
                  Bắt đầu học
                </button>

              </div>
            </div>
          </div>
        </div>

        <!-- Modal bắt đầu học -->
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
    <FooterPage></FooterPage>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import AppHeader from "@/components/Header.vue";
import FooterPage from "@/components/FooterPage.vue";

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
    console.error('Lỗi khi tải danh sách bài học từ vựng:', error);
    errorMessage.value = 'Không thể tải danh sách bài học từ vựng. Vui lòng thử lại sau.';
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
.container {
  max-width: 1200px;
  margin: auto;
}

.card {
  border: none;
  border-radius: 10px;
  transition: transform 0.2s ease-in-out, box-shadow 0.3s ease-in-out;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

.card-img-top {
  border-radius: 10px 10px 0 0;
}

.card-title {
  font-size: 18px;
  font-weight: bold;
}

.card-text {
  font-size: 14px;
  color: #6c757d;
}

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

/* Căn giữa thẻ main */
main {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 20px 0;
}
</style>
