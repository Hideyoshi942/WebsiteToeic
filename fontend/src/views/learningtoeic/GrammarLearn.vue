<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import AppHeader from "@/components/Header.vue";
import SideBar from "@/components/Sidebar.vue";
import FooterPage from "@/components/FooterPage.vue";

// Base URL for API resources
const baseUrl = "http://localhost:8080";

// State variables
const grammarLessons = ref([]);
const errorMessage = ref("");
const selectedLesson = ref(null);
const isModalVisible = ref(false);

// Fetch grammar lessons from the API
const loadGrammarLessons = async () => {
  try {
    const { data } = await axios.get(`${baseUrl}/api/admin/grammar/loadGrammar`);
    grammarLessons.value = data;
  } catch (error) {
    console.error("Error loading grammar lessons:", error);
    errorMessage.value =
        "Không thể tải danh sách bài học ngữ pháp. Vui lòng thử lại sau.";
  }
};

// Close modal
const closeModal = () => {
  isModalVisible.value = false;
};

// Load data on component mount
onMounted(() => {
  loadGrammarLessons();
});
</script>

<template>
  <div class="d-flex flex-column min-vh-100">
    <AppHeader></AppHeader>
    <main class="flex-grow-1 container mt-5">
      <!-- Title -->
      <div class="text-center mb-4">
        <h3 class="page-header text-primary fw-bold">Danh sách bài học ngữ pháp</h3>
        <p class="text-muted">
          Khám phá các bài học ngữ pháp để cải thiện kỹ năng tiếng Anh của bạn!
        </p>
      </div>

      <!-- Error message -->
      <div v-if="errorMessage" class="alert alert-danger text-center mt-3">
        {{ errorMessage }}
      </div>

      <!-- Grammar Lessons -->
      <div v-if="grammarLessons.length" class="grammar-grid">
        <div
            v-for="(lesson, index) in grammarLessons"
            :key="lesson.grammarid"
            class="grammar-card"
        >
          <div class="card shadow-sm h-100">
            <div class="card-body text-center">
              <!-- Lesson Image -->
              <div class="mb-3">
                <img
                    :src="`${baseUrl}${lesson.grammarimage}`"
                    alt="Grammar Image"
                    class="img-fluid rounded"
                    style="height: 180px; object-fit: cover; width: 100%;"
                />
              </div>
              <!-- Lesson Title -->
              <h5 class="card-title text-primary fw-bold">
                {{ index + 1 }}. {{ lesson.grammarname }}
              </h5>
              <!-- View Details Button -->
              <button
                  class="btn btn-primary mt-3"
                  @click="$router.push({ name: 'GrammarLessonContent', params: { id: lesson.grammarid } })"
              >
                Xem chi tiết
              </button>
            </div>
          </div>
        </div>
      </div>
    </main>
    <SideBar></SideBar>
    <FooterPage></FooterPage>

    <!-- Custom Modal for Lesson Details -->
    <div v-if="isModalVisible" class="custom-modal">
      <div class="custom-modal-content">
        <div class="custom-modal-header">
          <h5>Chi tiết bài học: {{ selectedLesson?.grammarname }}</h5>
          <button class="close-button" @click="closeModal">×</button>
        </div>
        <div class="custom-modal-body">
          <div class="text-center mb-3">
            <img
                :src="`${baseUrl}${selectedLesson?.grammarimage}`"
                alt="Grammar Image"
                class="img-fluid rounded"
                style="height: 300px; object-fit: cover; width: 100%;"
            />
          </div>
          <p><strong>Tiêu đề:</strong></p>
          <p v-html="selectedLesson?.grammarcontenthtml" style="color: black;"></p>
          <p><strong>Nội dung bài học:</strong></p>
          <p v-html="selectedLesson?.grammarcontenthtmlmarkdown" style="color: black;"></p>
        </div>
        <div class="custom-modal-footer">
          <button class="btn btn-secondary" @click="closeModal">Đóng</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  max-width: 1200px;
}

/* Grammar Grid Layout */
.grammar-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}

.grammar-card {
  max-width: 350px;
  margin: auto;
}

/* Card Styling */
.card {
  border-radius: 10px;
  border: none;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease-in-out, box-shadow 0.3s ease-in-out;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

.card-title {
  font-size: 18px;
  margin-bottom: 15px;
}

/* Custom Modal */
.custom-modal {
  position: fixed;
  top: 10%;
  left: 50%;
  transform: translate(-50%, 0);
  z-index: 1000;
}

.custom-modal-content {
  background: #fff;
  border-radius: 8px;
  width: 600px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.25);
  position: relative;
  overflow: hidden;
}

.custom-modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
  background-color: #007bff;
  color: #fff;
}

.custom-modal-header h5 {
  margin: 0;
}

.close-button {
  background: transparent;
  border: none;
  font-size: 24px;
  color: #fff;
  cursor: pointer;
}

.custom-modal-body {
  padding: 20px;
  max-height: 400px;
  overflow-y: auto;
}

.custom-modal-footer {
  padding: 15px 20px;
  text-align: right;
  background-color: #f8f9fa;
}
</style>
