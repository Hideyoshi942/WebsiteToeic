<template>
  <div class="d-flex flex-column min-vh-100">
    <AppHeader></AppHeader>
    <main class="flex-grow-1 container mt-5">
      <!-- Tiêu đề -->
      <div class="text-center mb-4">
        <h2 class="page-header text-primary fw-bold">
          {{ lessonDetail?.grammarname || "Chi tiết bài học" }}
        </h2>
        <p class="text-muted">
          Khám phá chi tiết bài học ngữ pháp và để lại bình luận của bạn!
        </p>
      </div>

      <!-- Nội dung bài học -->
      <div class="lesson-detail mb-5" v-if="lessonDetail">
        <div class="text-center mb-4">
          <img
              :src="`${baseUrl}${lessonDetail.grammarimage}`"
              alt="Grammar Image"
              class="img-fluid rounded"
              style="max-height: 300px; object-fit: cover;"
          />
        </div>
        <label for="grammarcontenthtml">Tên bài học:</label>
        <p v-html="lessonDetail.grammarcontenthtml"></p>
        <label for="grammarcontenthtmlmarkdown">Nội dung bài học:</label>
        <p v-html="renderMarkdown(lessonDetail.grammarcontenthtmlmarkdown)"></p>
      </div>

      <!-- Phần tạo bình luận -->
      <div class="mt-5">
        <h4 class="text-primary">Bình luận</h4>
        <div class="form-group mb-3">
          <textarea
              v-model="newComment"
              class="form-control"
              rows="3"
              placeholder="Viết bình luận của bạn tại đây..."
          ></textarea>
        </div>
        <button @click="submitComment" class="btn btn-primary">
          Gửi bình luận
        </button>
      </div>

      <!-- Danh sách bình luận -->
      <div class="mt-4">
        <h5 class="text-secondary">Danh sách bình luận:</h5>
        <div v-if="comments.length" class="list-group">
          <div
              v-for="comment in comments"
              :key="comment.commentid"
              class="list-group-item"
          >
            <strong>{{ comment.name }}</strong>
            <p>{{ comment.commentgrammarcontent }}</p>
            <small class="text-muted">{{ comment.commentgrammartime }}</small>
          </div>
        </div>
        <p v-else class="text-muted">Chưa có bình luận nào.</p>
      </div>
    </main>
    <FooterPage></FooterPage>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";
import AppHeader from "@/components/Header.vue";
import FooterPage from "@/components/FooterPage.vue";
import { marked } from "marked";
import DOMPurify from 'dompurify';

const baseUrl = "http://localhost:8080";

// State variables
const route = useRoute();
const grammarid = route.params.id;

const lessonDetail = ref(null);
const comments = ref([]);
const newComment = ref("");
const usertoeic = JSON.parse(localStorage.getItem('usertoeic'));

// Fetch lesson details
const loadLessonDetail = async () => {
  try {
    const { data } = await axios.get(`${baseUrl}/api/admin/grammar/loadGrammar/${grammarid}`);
    lessonDetail.value = data;
    console.log(lessonDetail.value);
  } catch (error) {
    console.error("Error loading lesson detail:", error);
  }
};

const renderMarkdown = (markdown) => {
  const dirtyHtml = marked(markdown);
  return DOMPurify.sanitize(dirtyHtml); // Sử dụng DOMPurify để làm sạch HTML
};


// Fetch comments
const loadComments = async () => {
  try {
    const { data } = await axios.get(
        `${baseUrl}/api/admin/grammar/loadCommentGrammar/${grammarid}`
    );
    comments.value = data;
    console.log(comments.value);
  } catch (error) {
    console.error("Error loading comments:", error);
  }
};

// Submit new comment
const submitComment = async () => {
  if (!newComment.value.trim()) {
    alert("Comment cannot be empty.");
    return;
  }

  try {
    const payload = new URLSearchParams();
    payload.append("grammarid", grammarid);
    payload.append("id", usertoeic.id);
    payload.append("commmentgrammarcontent", newComment.value.trim());
    await axios.post(`${baseUrl}/api/admin/grammar/createCommentGrammar`, payload, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
    });
    newComment.value = ""; // Reset comment input
    loadComments(); // Reload comments
  } catch (error) {
    console.error("Error submitting comment:", error);
    alert("Failed to submit comment. Please try again later.");
  }
};

// Fetch data on mount
onMounted(() => {
  loadLessonDetail();
  loadComments();
});
</script>

<style scoped>
.lesson-detail {
  padding: 20px;
  background: #f8f9fa;
  border-radius: 8px;
}
.list-group-item {
  margin-bottom: 15px;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 15px;
  background-color: #f9f9f9;
}

.list-group-item strong {
  color: #007bff;
}

.list-group-item p {
  margin: 5px 0;
}

.list-group-item small {
  font-size: 12px;
  color: #6c757d;
}
</style>