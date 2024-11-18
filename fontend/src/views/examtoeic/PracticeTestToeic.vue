<script setup>
import { ref, computed, onMounted } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";

const route = useRoute();

// Trạng thái bài thi
const questions = ref([]);
const practicetestid = +route.params.id;
const usertoeic = JSON.parse(localStorage.getItem('usertoeic'));
const timer = ref(1800); // 30 phút
const isSubmitted = ref(false);
const isLoading = ref(true);

// Pagination state
const currentPage = ref(1);
const pageSize = 50; // Số lượng câu hỏi mỗi trang
const totalPages = 2; // Tổng số trang cố định

// Thống kê
const correctListening = ref(0);
const incorrectListening = ref(0);
const correctReading = ref(0);
const incorrectReading = ref(0);

// Gọi API để tải danh sách câu hỏi
const loadQuestions = async (page = 1) => {
  try {
    isLoading.value = true;
    const response = await axios.get(
        `http://localhost:8080/api/admin/practicetest/loadQuestionPracticeTest/${practicetestid}?page=${page}&size=${pageSize}`
    );
    questions.value = response.data.content.map((q) => ({
      id: q.questionpracticetestid,
      question: q.questionpracticetestask,
      answers: [
        q.questionpracticetestanswer1,
        q.questionpracticetestanswer2,
        q.questionpracticetestanswer3,
        q.questionpracticetestanswer4,
      ],
      correctAnswer: ["A", "B", "C", "D"].indexOf(q.questionpracticetestanswercorrect),
      explanation: q.questionpracticetestparagraph,
      userAnswer: null,
      audio: q.questionpracticetestaudio,
      image: q.questionpracticetestimage,
    }));
    isLoading.value = false;
  } catch (error) {
    console.error("Error loading questions:", error);
    isLoading.value = false;
  }
};

// Chuyển trang
const goToNextPage = () => {
  if (currentPage.value < totalPages) {
    currentPage.value++;
    loadQuestions(currentPage.value);
  }
};

// Ghi nhận câu trả lời
const selectAnswer = (questionId, answerIndex) => {
  const question = questions.value.find((q) => q.id === questionId);
  if (question) {
    question.userAnswer = answerIndex;
  }
};

// Tính trạng thái màu sắc cho mỗi câu hỏi
const questionStatus = computed(() => {
  if (!isSubmitted.value) {
    return questions.value.map((q) =>
        q.userAnswer !== null ? "btn-warning" : "btn-outline-primary"
    );
  }
  return questions.value.map((q) => {
    if (q.userAnswer === null) return "btn-outline-secondary";
    return q.userAnswer === q.correctAnswer ? "btn-success" : "btn-danger";
  });
});

// Tính tổng số câu đúng và sai cho từng phần
const calculateResults = () => {
  const currentQuestions = questions.value;
  const correct = currentQuestions.filter((q) => q.userAnswer === q.correctAnswer).length;
  const incorrect = currentQuestions.filter(
      (q) => q.userAnswer !== q.correctAnswer && q.userAnswer !== null
  ).length;

  if (currentPage.value === 1) {
    correctListening.value = correct;
    incorrectListening.value = incorrect;
  } else if (currentPage.value === 2) {
    correctReading.value = correct;
    incorrectReading.value = incorrect;
  }
};

// Nộp bài thi và lưu kết quả
const submitTest = async () => {
  isSubmitted.value = true;
  calculateResults();
  console.log(practicetestid,
      usertoeic.id,
      correctListening.value,
      correctReading.value,
      incorrectListening.value,
      incorrectReading.value
  );
  // Gửi kết quả lên API
  try {
    const params = new URLSearchParams();
    params.append("resulttestcorrectlistening", correctListening.value);
    params.append("resulttestcorrectreading", correctReading.value);
    params.append("resulttestnumbercorrect", correctListening.value + correctReading.value);
    params.append("resulttestnumberincorrect", incorrectListening.value + incorrectReading.value);
    params.append("id", usertoeic.id);

    await axios.post(`http://localhost:8080/api/admin/result/saveResult/${practicetestid}`, params);

  } catch (error) {
    console.error("Error saving results:", error);
    alert("Lỗi lưu kết quả, vui lòng thử lại sau.");
  }
};

// Đếm ngược thời gian
onMounted(() => {
  loadQuestions(); // Tải câu hỏi đầu tiên
  const interval = setInterval(() => {
    if (timer.value > 0 && !isSubmitted.value) {
      timer.value--;
    } else {
      clearInterval(interval); // Dừng đồng hồ khi hết giờ hoặc đã nộp bài
    }
  }, 1000);
});
</script>

<template>
  <div class="container-fluid toeic-container">
    <!-- Sidebar -->
    <aside class="col-md-3 p-3 bg-light sidebar">
      <div class="timer text-center mb-4">
        <h4 class="text-primary">Thời gian còn lại</h4>
        <p class="display-6 text-danger">{{ Math.floor(timer / 60) }}:{{
            (timer % 60).toString().padStart(2, "0")
          }}</p>
      </div>
      <div class="question-map mb-4">
        <h5 class="text-center text-secondary">Câu hỏi</h5>
        <div class="grid d-grid gap-2">
          <button style="width: 25px; height: 25px;"
              v-for="(q, index) in questions"
              :key="q.id"
              class="btn question-box"
              :class="questionStatus[index]"
          >
            {{ index + 1 }}
          </button>
        </div>
      </div>
      <div class="stats text-center my-4" style="margin-top: 10px;">
        <p class="text-success">Câu đúng Listening: {{ correctListening }}</p>
        <p class="text-danger">Câu sai Listening: {{ incorrectListening }}</p>
        <p class="text-success">Câu đúng Reading: {{ correctReading }}</p>
        <p class="text-danger">Câu sai Reading: {{ incorrectReading }}</p>
      </div>
      <button style="margin-bottom: 10px;"
          class="btn btn-primary w-100 mt-3"
          @click="submitTest"
          :disabled="isLoading"
      >
        Nộp bài
      </button>
      <button
          class="btn btn-outline-secondary w-100 mt-2"
          @click="goToNextPage"
          :disabled="currentPage.value === totalPages"
      >
        Tiếp tục bài đọc
      </button>
    </aside>

    <!-- Nội dung câu hỏi -->
    <main class="col-md-9 py-3 test-content">
      <div v-if="isLoading" class="text-center">
        <p>Đang tải câu hỏi...</p>
      </div>
      <div v-else>
        <div
            v-for="(question, index) in questions"
            :key="question.id"
            class="question-block p-4 mb-4 border rounded shadow-sm bg-white"
        >
          <h5 class="mb-4">Câu {{ index + 1 }}: {{ question.question }}</h5>
          <div v-if="question.audio" class="mb-3">
            <audio :src="`http://localhost:8080/api/admin/practicetest/audio/${question.audio}.mp3`" controls></audio>
          </div>
          <div v-if="question.image" class="mb-3">
            <img
                :src="`http://localhost:8080/api/admin/practicetest/images/${question.image}.jpg`"
                alt="Question Image"
                class="img-fluid rounded"
                style="max-height: 200px;"
            />
          </div>
          <div class="answers">
            <button style="height: 50px;"
                v-for="(answer, idx) in question.answers"
                :key="idx"
                class="btn btn-outline-secondary btn-block text-start mb-2"
                :class="{
                'btn-warning': question.userAnswer === idx && !isSubmitted,
                'btn-success': isSubmitted && idx === question.correctAnswer,
                'btn-danger': isSubmitted && idx === question.userAnswer && idx !== question.correctAnswer,
              }"
                @click="selectAnswer(question.id, idx)"
            >
              {{ answer }}
            </button>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped>
/* Tổng thể */
.toeic-container {
  display: flex;
  height: 100vh;
  overflow: hidden;
}

/* Sidebar */
.sidebar {
  height: 100vh;
  position: sticky;
  top: 0;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  border-right: 1px solid #ddd;
}

.timer h4 {
  font-size: 20px;
  font-weight: bold;
}

.timer p {
  font-size: 24px;
  font-weight: bold;
}

.question-map h5 {
  font-size: 18px;
  font-weight: bold;
}

.grid {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 5px;
}

.question-box {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 40px; /* Thu nhỏ kích thước ô */
  height: 40px;
  font-size: 14px;
  font-weight: bold;
  border-radius: 5px;
  border: 1px solid #ddd;
  cursor: pointer;
  transition: all 0.3s ease;
}

.question-box.btn-outline-primary {
  background-color: #f8f9fa;
}

.question-box.btn-warning {
  background-color: #ffc107;
  color: #fff;
}

.question-box.btn-success {
  background-color: #28a745;
  color: #fff;
}

.question-box.btn-danger {
  background-color: #dc3545;
  color: #fff;
}

.question-box.btn-outline-secondary {
  background-color: #e9ecef;
  color: #6c757d;
}

/* Test Content */
.test-content {
  overflow-y: auto;
  background-color: #ffffff;
}

.question-block {
  background-color: #f8f9fa;
  margin-bottom: 20px;
}

.answers button {
  font-size: 16px;
  font-weight: bold;
  border-radius: 5px;
  text-align: left;
  padding: 10px;
  transition: all 0.3s ease;
}
</style>
