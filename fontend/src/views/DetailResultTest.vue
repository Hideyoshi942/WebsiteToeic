<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import AppHeader from "@/components/Header.vue";
import { useRoute } from "vue-router";

const route = useRoute();
const results = ref([]); // Lưu kết quả từ API
const isLoading = ref(true); // Trạng thái tải dữ liệu
const error = ref(null); // Trạng thái lỗi
const resulttestid = +route.params.id;

// Hàm gọi API để tải dữ liệu kết quả
const loadResults = async () => {
  try {
    isLoading.value = true;
    const response = await axios.get(`http://localhost:8080/api/admin/result/showResult/${resulttestid}`);
    results.value = response.data; // Gắn dữ liệu vào biến `results`
    console.log(results.value);
  } catch (err) {
    error.value = "Lỗi khi tải dữ liệu kết quả. Vui lòng thử lại!";
    console.error(err);
  } finally {
    isLoading.value = false;
  }
};

// Gọi API khi component được mount
onMounted(() => {
  loadResults();
});
</script>
<template>
  <AppHeader></AppHeader>
  <div class="container my-4">
    <h1 class="text-center mb-4">Danh sách kết quả bài thi</h1>

    <!-- Hiển thị trạng thái tải -->
    <div v-if="isLoading" class="text-center">
      <p>Đang tải dữ liệu...</p>
    </div>

    <!-- Hiển thị lỗi nếu có -->
    <div v-if="error" class="alert alert-danger">
      {{ error }}
    </div>

    <!-- Bảng kết quả -->
    <div v-if="!isLoading && !error">
      <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
          <th>#</th>
          <th>ID Câu hỏi</th>
          <th>Câu hỏi</th>
          <th>Đáp án đúng</th>
          <th>Đáp án người dùng</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(result, index) in results" :key="result.detailresultexamid">
          <td>{{ index + 1 }}</td>
          <td>{{ result.detailresultexamquestionid }}</td>
          <td>{{ result.detailresultexamquestion }}</td>
          <td>{{ result.detailresultexamanswercorrect }}</td>
          <td>{{ result.detailresultexamansweruser }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
}

.table {
  margin-top: 20px;
}

.table th, .table td {
  text-align: center;
  vertical-align: middle;
}
</style>
