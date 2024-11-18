<template>
  <div class="d-flex flex-column min-vh-100">
    <main class="flex-grow-1 container mt-5">
      <div class="text-center mb-4">
        <h3 class="page-header text-primary fw-bold">Thi Thử Nghe</h3>
        <p class="text-muted" v-if="questions.length">
          Bạn có <strong>{{ formatTime(timer) }}</strong> để hoàn thành bài thi.
        </p>
      </div>

      <!-- Thông báo lỗi -->
      <div v-if="errorMessage" class="alert alert-danger text-center mt-3">
        {{ errorMessage }}
      </div>

      <!-- Thông báo không có câu hỏi -->
      <div v-if="!questions.length && !errorMessage" class="alert alert-warning text-center mt-3">
        Không có câu hỏi nào cho bài thi này. Vui lòng kiểm tra lại sau!
      </div>

      <!-- Câu hỏi -->
      <div v-for="(question, index) in questions" :key="question.questionlisteningid" class="quiz-card">
        <div class="card shadow-sm h-100">
          <div class="card-body">
            <!-- Hiển thị hình ảnh nếu có -->
            <div class="text-center mb-3" v-if="question.questionlisteningimage">
              <img
                  :src="`${baseUrl}${question.questionlisteningimage}`"
                  alt="Listening Image"
                  class="img-fluid rounded vocab-img"
                  style="width: 200px; height: 200px;"
              />
            </div>
            <div class="text-center mb-3" v-else>
              <p class="text-muted">Không có hình ảnh</p>
            </div>

            <!-- Hiển thị audio nếu có -->
            <div class="text-center mb-3" v-if="question.questionlisteningaudio">
              <audio
                  :src="`${baseUrl}${question.questionlisteningaudio}`"
                  controls
              ></audio>
            </div>
            <div class="text-center mb-3" v-else>
              <p class="text-muted">Không có audio</p>
            </div>

            <h5 class="card-title text-primary fw-bold">
              Câu {{ index + 1 }}: {{ question.questionlisteningask }}
            </h5>
            <div class="form-check" v-for="(answer, answerIndex) in question.answers" :key="answerIndex">
              <input
                  class="form-check-input"
                  type="radio"
                  :name="'question_' + index"
                  :value="answer"
                  v-model="answers[index]"
                  :disabled="submitted"
              />
              <label class="form-check-label">{{ answer }}</label>
            </div>

            <!-- Hiển thị kết quả và giải thích -->
            <div v-if="submitted" class="mt-3">
              <p>
          <span :class="{'text-success': answers[index] === question.questionlisteninganswercorrect, 'text-danger': answers[index] !== question.questionlisteninganswercorrect}">
            Bạn đã trả lời: {{ answers[index] || 'Chưa trả lời' }}
          </span>
              </p>
              <p class="text-muted">
                Đáp án đúng: <strong>{{ question.questionlisteninganswercorrect }}</strong>
              </p>
              <p class="text-info">
                Giải thích: {{ question.questionlisteningexplain || 'Không có giải thích.' }}
              </p>
              <p class="text-info">
                Giải thích đoạn văn: {{ question.questionlisteningscript || 'Không có đoạn văn.' }}
              </p>
            </div>
          </div>
        </div>
      </div>


      <!-- Nút nộp bài -->
      <div class="text-center mt-4" style="margin-top: 10px;" v-if="questions.length">
        <button style="margin-right: 10px;" class="btn btn-primary" :disabled="submitted" @click="confirmSubmitQuiz">Nộp bài</button>
        <button class="btn btn-warning" @click="confirmGoHome">Về Trang Chủ</button>
      </div>

      <!-- Phần đệm -->
      <div class="mt-5"></div>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";

const baseUrl = "http://localhost:8080"; // API URL
const questions = ref([]);
const answers = ref([]);
const errorMessage = ref("");
const submitted = ref(false);
const score = ref(0);

// Timer variables
const timer = ref(2700); // 30 phút
let interval;

const route = useRoute();
const listeningid = route.params.id;

// Format time
const formatTime = (time) => {
  const minutes = Math.floor(time / 60);
  const seconds = time % 60;
  return `${minutes}:${seconds.toString().padStart(2, "0")}`;
};

// Hỏi xác nhận trước khi rời khỏi trang
const confirmBeforeUnload = (event) => {
  if (!submitted.value) {
    event.preventDefault();
    event.returnValue = "Bạn có chắc chắn muốn rời khỏi trang? Tất cả dữ liệu chưa được lưu sẽ mất.";
  }
};

// Xác nhận nộp bài
const confirmSubmitQuiz = () => {
  if (confirm("Bạn có chắc chắn muốn nộp bài?")) {
    submitQuiz();
  }
};

// Xác nhận quay về trang chủ
const confirmGoHome = () => {
  if (confirm("Bạn có chắc chắn muốn quay về trang chủ?")) {
    goHome();
  }
};

// Quay về trang chủ
const goHome = () => {
  window.location.href = "/listlisteningtest";
};

// Load câu hỏi từ API
const loadQuestions = async () => {
  try {
    console.log(listeningid);
    const { data } = await axios.get(`${baseUrl}/api/admin/listening/loadQuestionListening`, {
      params: { listeningid },
    });
    questions.value = data.map((q) => ({
      ...q,
      answers: [
        q.questionlisteninganswer1,
        q.questionlisteninganswer2,
        q.questionlisteninganswer3,
        q.questionlisteninganswer4,
      ],
      questionlisteningimage: q.questionlisteningimage || null, // Đảm bảo giá trị không undefined
      questionlisteningaudio: q.questionlisteningaudio || null, // Đảm bảo giá trị không undefined
      questionlisteningexplain: q.questionlisteningexplain || null, // Giải thích
    }));
    answers.value = new Array(questions.value.length).fill(null);

    // Nếu có câu hỏi thì bắt đầu đếm ngược
    if (questions.value.length > 0) {
      startTimer();
    }
  } catch (error) {
    console.error("Error loading questions:", error);
    errorMessage.value = "Không thể tải câu hỏi. Vui lòng thử lại sau.";
  }
};


// Nộp bài
const submitQuiz = () => {
  submitted.value = true;
  score.value = questions.value.reduce((sum, question, index) => {
    return sum + (answers.value[index] === question.questionlisteninganswercorrect ? 1 : 0);
  }, 0);
  clearInterval(interval);
};

// Đếm ngược thời gian
const startTimer = () => {
  interval = setInterval(() => {
    if (timer.value > 0) {
      timer.value--;
    } else {
      submitQuiz(); // Hết giờ tự động nộp bài
    }
  }, 1000);
};

// Lifecycle hooks
onMounted(() => {
  loadQuestions();
  window.addEventListener("beforeunload", confirmBeforeUnload); // Xác nhận trước khi reload
});

onBeforeUnmount(() => {
  window.removeEventListener("beforeunload", confirmBeforeUnload); // Xóa sự kiện khi rời trang
  clearInterval(interval); // Dừng timer
});
</script>

<style scoped>
.quiz-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.quiz-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  background-color: #f9f9f9;
}

.result {
  margin-top: 20px;
}

.result .list-group-item {
  border: none;
}

.text-info {
  font-size: 14px;
}

.mt-5 {
  height: 50px; /* Tạo không gian đệm */
}
</style>
