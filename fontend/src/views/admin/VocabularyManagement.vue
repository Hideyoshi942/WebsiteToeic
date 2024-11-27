<script setup>
import { ref, reactive, onMounted } from 'vue';
import axios from 'axios';

// Reactive state
const vocabList = ref([]);
const vocabForm = reactive({
  vocabId: null,
  vocabName: '',
  fileExcel: null,
  fileImageVocab: null,
  fileQuestionVocab: null,
  fileListImage: null,
  fileListAudio: null,
});
const successMessage = ref('');
const errorMessage = ref('');
const showModal = ref(false);

// Functions
const resetForm = () => {
  vocabForm.vocabId = null;
  vocabForm.vocabName = '';
  vocabForm.fileExcel = null;
  vocabForm.fileImageVocab = null;
  vocabForm.fileQuestionVocab = null;
  vocabForm.fileListImage = null;
  vocabForm.fileListAudio = null;
  showModal.value = false; // Close modal
};

const handleFileChange = (event, type) => {
  const files = event.target.files;
  if (type === 'imageVocab') vocabForm.fileImageVocab = files[0];
  else if (type === 'fileExcel') vocabForm.fileExcel = files[0];
  else if (type === 'fileExcelQuestion') vocabForm.fileQuestionVocab = files[0];
  else if (type === 'fileListImage') vocabForm.fileListImage = Array.from(files);
  else if (type === 'fileListAudio') vocabForm.fileListAudio = Array.from(files);
};

const loadAllVocab = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/admin/vocab/loadVocab');
    vocabList.value = response.data.map((vocab) => ({
      vocabId: vocab.vocabularyid,
      vocabName: vocab.vocabularyname,
      imageUrl: `http://localhost:8080${vocab.vocabularyimage}`, // URL ảnh từ API
    }));
  } catch (error) {
    errorMessage.value = 'Lỗi khi tải danh sách bài từ vựng!';
    console.error(error);
  }
};

const handleSaveOrUpdate = async () => {
  const formData = new FormData();
  formData.append('namevocab', vocabForm.vocabName);
  if (vocabForm.fileExcel) formData.append('file_excel_lesson', vocabForm.fileExcel);
  if (vocabForm.fileImageVocab) formData.append('file_imageVocab', vocabForm.fileImageVocab);
  if (vocabForm.fileQuestionVocab) formData.append('file_questionvocab', vocabForm.fileQuestionVocab);
  if (vocabForm.fileListImage) {
    vocabForm.fileListImage.forEach((file) => {
      formData.append('file_image_lessonVocab', file);
    });
  }

  if (vocabForm.fileListAudio) {
    vocabForm.fileListAudio.forEach((file) => {
      formData.append('file_listening', file);
    });
  }



  if (vocabForm.vocabId) formData.append('vocabularyid', vocabForm.vocabId);

  try {
    const url = vocabForm.vocabId
        ? 'http://localhost:8080/api/admin/vocab/update'
        : 'http://localhost:8080/api/admin/vocab/save';
    await axios.post(url, formData, { headers: { 'Content-Type': 'multipart/form-data' } });
    successMessage.value = vocabForm.vocabId
        ? 'Cập nhật bài từ vựng thành công!'
        : 'Thêm mới bài từ vựng thành công!';
    resetForm();
    loadAllVocab();
  } catch (error) {
    errorMessage.value = 'Lỗi trong quá trình xử lý!';
    console.error(error);
  }
};

const handleEdit = async (id) => {
  try {
    const response = await axios.get(`http://localhost:8080/api/admin/vocab/infoVocabulary/${id}`);
    vocabForm.vocabId = id;
    vocabForm.vocabName = response.data; // API trả về tên bài
    showModal.value = true;
  } catch (error) {
    errorMessage.value = 'Lỗi khi tải thông tin bài từ vựng!';
    console.error(error);
  }
};

const handleDelete = async (id) => {
  if (confirm('Bạn có chắc chắn muốn xóa bài từ vựng này?')) {
    try {
      await axios.delete(`http://localhost:8080/api/admin/vocab/delete/${id}`);
      successMessage.value = 'Xóa bài từ vựng thành công!';
      loadAllVocab();
    } catch (error) {
      errorMessage.value = 'Lỗi khi xóa bài từ vựng!';
      console.error(error);
    }
  }
};

// Load vocab on mount
onMounted(() => {
  loadAllVocab();
});
</script>

<template>
  <div class="container my-5" style="margin-left: 20%;">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h3 class="text-center w-100">Quản lý Bài hướng dẫn học từ vựng</h3>
      <button class="btn btn-success" @click="showModal = true" style="margin-bottom: 20px;">Thêm mới</button>
    </div>

    <p v-if="successMessage" class="alert alert-success text-center">{{ successMessage }}</p>
    <p v-if="errorMessage" class="alert alert-danger text-center">{{ errorMessage }}</p>

    <div class="table-responsive">
      <table class="table table-bordered table-striped text-center">
        <thead class="table-primary">
        <tr>
          <th>ID</th>
          <th>Tên bài từ vựng</th>
          <th>Ảnh</th>
          <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <tr v-if="vocabList.length === 0">
          <td colspan="4">Không có bài từ vựng nào</td>
        </tr>
        <tr v-for="vocab in vocabList" :key="vocab.vocabId">
          <td>{{ vocab.vocabId }}</td>
          <td>{{ vocab.vocabName }}</td>
          <td>
            <img :src="vocab.imageUrl" alt="Ảnh bài từ vựng" class="img-thumbnail" style="height: 50px;" />
          </td>
          <td>
            <button class="btn btn-warning btn-sm me-2" @click="handleEdit(vocab.vocabId)" style="margin-right: 10px;">Cập nhật</button>
            <button class="btn btn-danger btn-sm" @click="handleDelete(vocab.vocabId)">Xóa</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <!-- Modal -->
    <div v-if="showModal" class="modal fade show d-flex justify-content-center align-items-center" style="background-color: rgba(0, 0, 0, 0.5);">
      <div class="modal-dialog modal-lg" style="margin-top: 25%;">
        <div class="modal-content">
          <div class="modal-header bg-primary text-white">
            <h5 class="modal-title">{{ vocabForm.vocabId ? 'Cập nhật bài từ vựng' : 'Thêm mới bài từ vựng' }}</h5>
            <button type="button" class="btn-close" @click="resetForm"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="handleSaveOrUpdate">
              <div class="mb-3">
                <label for="vocabName" class="form-label">Tên bài từ vựng:</label>
                <input type="text" id="vocabName" v-model="vocabForm.vocabName" class="form-control" required />
              </div>
              <div class="mb-3">
                <label for="imageVocab" class="form-label">File ảnh bài từ vựng:</label>
                <input type="file" id="imageVocab" @change="handleFileChange($event, 'imageVocab')" class="form-control" accept="image/*" />
              </div>
              <div class="mb-3">
                <label for="fileExcel" class="form-label">File nội dung bài học từ vựng:</label>
                <input type="file" id="fileExcel" @change="handleFileChange($event, 'fileExcel')" class="form-control" accept=".xlsx,.xls" />
              </div>
              <div class="mb-3">
                <label for="fileListImage" class="form-label">File ảnh nội dung bài học từ vựng (Multiple):</label>
                <input type="file" id="fileListImage" @change="handleFileChange($event, 'fileListImage')" class="form-control" accept="image/*" multiple />
              </div>
              <div class="mb-3">
                <label for="fileListAudio" class="form-label">File nghe nội dung bài học từ vựng (Multiple):</label>
                <input type="file" id="fileListAudio" @change="handleFileChange($event, 'fileListAudio')" class="form-control" accept=".mp3" multiple />
              </div>
              <div class="mb-3">
                <label for="fileExcelQuestion" class="form-label">File câu hỏi từ vựng:</label>
                <input type="file" id="fileExcelQuestion" @change="handleFileChange($event, 'fileExcelQuestion')" class="form-control" accept=".xlsx,.xls" />
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary">{{ vocabForm.vocabId ? 'Cập nhật' : 'Thêm mới' }}</button>
                <button type="button" class="btn btn-secondary" @click="resetForm">Hủy</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Tổng thể */
.container {
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

/* Tiêu đề */
h3 {
  font-size: 24px;
  font-weight: bold;
  color: #4a90e2;
  margin-bottom: 20px;
  text-align: center;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
}

/* Nút thêm mới */
.btn-success {
  font-size: 16px;
  padding: 10px 15px;
  border-radius: 5px;
  transition: all 0.3s ease;
}

.btn-success:hover {
  background-color: #218838;
  color: white;
}

/* Thông báo */
.alert {
  font-size: 16px;
  border-radius: 5px;
  padding: 10px 20px;
}

.alert-success {
  background-color: #d4edda;
  color: #155724;
  border-color: #c3e6cb;
}

.alert-danger {
  background-color: #f8d7da;
  color: #721c24;
  border-color: #f5c6cb;
}

/* Bảng */
.table {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  overflow: hidden;
}

.table th,
.table td {
  text-align: center;
  vertical-align: middle;
  padding: 12px 15px;
}

.table th {
  background-color: #007bff;
  color: white;
  font-weight: bold;
}

.table-striped tbody tr:nth-child(odd) {
  background-color: #f2f2f2;
}

.table-striped tbody tr:hover {
  background-color: #e9ecef;
  cursor: pointer;
}

.img-thumbnail {
  width: 80px;
  height: auto;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Nút hành động */
.btn-warning {
  background-color: #ffc107;
  color: white;
  border-radius: 5px;
  transition: all 0.3s ease;
}

.btn-warning:hover {
  background-color: #e0a800;
}

.btn-danger {
  background-color: #dc3545;
  color: white;
  border-radius: 5px;
  transition: all 0.3s ease;
}

.btn-danger:hover {
  background-color: #c82333;
}

/* Modal */
.modal {
  display: flex !important;
  align-items: center;
  justify-content: center;
}

.modal-dialog {
  max-width: 700px;
  border-radius: 8px;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
}

.modal-content {
  border-radius: 8px;
  overflow: hidden;
}

.modal-header {
  background-color: #007bff;
  color: white;
  padding: 15px 20px;
  font-size: 18px;
  font-weight: bold;
}

.modal-header .btn-close {
  background: none;
  border: none;
  color: white;
  font-size: 18px;
  cursor: pointer;
}

.modal-header .btn-close:hover {
  color: #ddd;
}

.modal-body {
  padding: 20px;
  background-color: #f8f9fa;
}

.modal-body label {
  font-weight: bold;
  color: #333;
}

.modal-body .form-control {
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 10px;
  margin-top: 5px;
  font-size: 14px;
  transition: border-color 0.3s ease;
}

.modal-body .form-control:focus {
  border-color: #4a90e2;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  padding: 15px 20px;
}

.modal-footer .btn {
  padding: 10px 15px;
  border-radius: 5px;
  font-size: 14px;
  transition: all 0.3s ease;
}

.modal-footer .btn-primary {
  background-color: #007bff;
  color: white;
}

.modal-footer .btn-primary:hover {
  background-color: #0056b3;
}

.modal-footer .btn-secondary {
  background-color: #6c757d;
  color: white;
}

.modal-footer .btn-secondary:hover {
  background-color: #5a6268;
}
</style>

