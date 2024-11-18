<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import AppHeader from "@/components/Header.vue";

const results = ref([]); // Lưu kết quả từ API
const isLoading = ref(true); // Trạng thái tải dữ liệu
const error = ref(null); // Trạng thái lỗi

// Hàm gọi API để tải dữ liệu kết quả
const loadResults = async () => {
  try {
    isLoading.value = true;
    const response = await axios.get("http://localhost:8080/api/admin/result/showResult");
    results.value = response.data; // Gắn dữ liệu vào biến `results`
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
          <th>ID Bài Thi</th>
          <th>ID Người Dùng</th>
          <th>Đúng Listening</th>
          <th>Đúng Reading</th>
          <th>Số Câu Đúng</th>
          <th>Số Câu Sai</th>
          <th>Thời Gian</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(result, index) in results" :key="result.resulttestid">
          <td>{{ index + 1 }}</td>
          <td>{{ result.practicetestid.practicetestid }}</td>
          <td>{{ result.username }}</td>
          <td>{{ result.resulttestcorrectlistening }}</td>
          <td>{{ result.resulttestcorrectreading }}</td>
          <td>{{ result.resulttestnumbercorrect }}</td>
          <td>{{ result.resulttestnumberincorrect }}</td>
          <td>{{ new Date(result.resulttesttime).toLocaleString() }}</td>
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
