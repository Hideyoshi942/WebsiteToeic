<template>
  <div class="d-flex flex-column min-vh-100">
    <main class="flex-grow-1 container mt-5">
      <div class="text-center mb-4">
        <h3 class="page-header text-primary fw-bold">Luyện Tập Nghe</h3>
        <p class="text-muted">Danh sách các bài thi nghe, hãy chọn bài thi để bắt đầu!</p>
      </div>

      <!-- Bộ lọc -->
      <div class="d-flex justify-content-center mb-4 row">
        <div class="form-group me-2 col-sm-2">
          <select class="form-control" v-model="levelFilter">
            <option value="">Tất cả cấp độ</option>
            <option value="1">Mức dễ</option>
            <option value="2">Mức trung bình</option>
            <option value="3">Mức khó</option>
          </select>
        </div>
        <div class="form-group me-2 col-sm-2">
          <select class="form-control" v-model="partFilter">
            <option value="">Tất cả Part</option>
            <option value="1">Part 1-Photographs</option>
            <option value="2">Part 2-Question response</option>
            <option value="3">Part 3-Short Conversations</option>
            <option value="4">Part 4-Short talks</option>
          </select>
        </div>
        <button class="btn btn-primary col-sm-1" @click="applyFilters">Duyệt</button>
      </div>

      <!-- Trạng thái đang tải -->
      <div v-if="isLoading" class="text-center">
        <p>Đang tải danh sách bài thi...</p>
      </div>

      <!-- Thông báo lỗi -->
      <div v-if="errorMessage && !isLoading" class="alert alert-danger text-center mt-3">
        {{ errorMessage }}
      </div>

      <!-- Danh sách bài thi -->
      <div v-if="!isLoading && filteredReadingList.length > 0" class="row mt-4" style="margin-bottom: 20px;">
        <div
            v-for="(listening) in filteredReadingList"
            :key="listening.listeningid"
            class="col-md-6 mb-4 d-flex justify-content-center"
        >
          <div class="card shadow-sm" style="width: 100%; max-width: 500px;">
            <div class="card-body text-start" style="padding: 20px;">
              <h5 class="card-title text-primary fw-bold">{{ listening.listeningname }}</h5>
              <p class="card-text text-muted">Cấp độ: {{ getLevelText(listening.listeninglevel) }}</p>
              <p class="card-text text-muted">Part: {{ getPartText(listening.listeningpart) }}</p>
              <p class="card-text text-muted">{{ listening.listeningscript }}</p>
              <p class="card-text text-muted">Thời gian: 45 phút</p>
              <button
                  class="btn btn-primary"
                  @click="$router.push({ name: 'ListeningTest', params: { id: listening.listeningid } })"
              >
                Bắt đầu thi
              </button>
            </div>
          </div>
        </div>
      </div>


      <!-- Thông báo không có kết quả -->
      <div v-if="!isLoading && filteredReadingList.length === 0" class="text-center mt-4">
        <p>Không tìm thấy bài thi nào phù hợp.</p>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

// Biến trạng thái
const listeningList = ref([]); // Danh sách bài thi
const filteredReadingList = ref([]); // Danh sách bài thi sau khi lọc
const errorMessage = ref(""); // Lỗi khi tải dữ liệu
const isLoading = ref(false); // Trạng thái đang tải dữ liệu

// Bộ lọc
const levelFilter = ref(""); // Bộ lọc cấp độ
const partFilter = ref(""); // Bộ lọc Part

// Hàm để tải dữ liệu từ API
const loadListeningList = async () => {
  isLoading.value = true;
  try {
    // Gọi API
    const response = await axios.get("http://localhost:8080/api/admin/listening/loadListening");

    // Lưu dữ liệu vào biến readingList
    if (response.data && response.data.length > 0) {
      listeningList.value = response.data.map((listening) => ({
        listeningid: listening.listeningid,
        listeninglevel: listening.listeninglevel,
        listeningpart: listening.listeningpart,
        listeningscript: listening.listeningscript,
        listeningname: listening.listeningname,
      }));
      // Ban đầu hiển thị toàn bộ danh sách
      filteredReadingList.value = [...listeningList.value];
    } else {
      errorMessage.value = "Không có bài thi đọc nào.";
    }
  } catch (error) {
    console.error("Lỗi khi tải danh sách bài thi đọc:", error);
    errorMessage.value = "Không thể tải danh sách bài thi đọc. Vui lòng thử lại sau.";
  } finally {
    isLoading.value = false;
  }
};

// Hàm áp dụng bộ lọc
const applyFilters = () => {
  filteredReadingList.value = listeningList .value.filter((listening) => {
    const levelMatch = levelFilter.value ? listening.listeninglevel === parseInt(levelFilter.value) : true;
    const partMatch = partFilter.value ? listening.listeningpart === parseInt(partFilter.value) : true;
    return levelMatch && partMatch;
  });
};

// Hàm để chuyển đổi level thành text
const getLevelText = (level) => {
  switch (level) {
    case 1:
      return "Mức dễ";
    case 2:
      return "Mức trung bình";
    case 3:
      return "Mức khó";
    default:
      return "Không xác định";
  }
};

// Hàm để chuyển đổi part thành text
const getPartText = (part) => {
  switch (part) {
    case 1:
      return "Part 1-Photographs";
    case 2:
      return "Part 2-Question response";
    case 3:
      return "Part 3-Short Conversations";
    case 4:
      return "Part 4-Short talks";
    default:
      return "Không xác định";
  }
};

// Tải dữ liệu khi khởi tạo
onMounted(() => {
  loadListeningList();
});
</script>

<style scoped>
/* Định dạng container */
.container {
  max-width: 1200px;
  margin: auto;
}

/* Card hiển thị bài thi */
.card {
  border: none;
  border-radius: 10px;
  transition: transform 0.2s ease-in-out, box-shadow 0.3s ease-in-out;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
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
</style>
