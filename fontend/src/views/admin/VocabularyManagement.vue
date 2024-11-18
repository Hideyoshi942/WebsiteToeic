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
  showModal.value = false; // Close modal
};

const handleFileChange = (event, type) => {
  const files = event.target.files;
  if (type === 'imageVocab') vocabForm.fileImageVocab = files[0];
  else if (type === 'fileExcel') vocabForm.fileExcel = files[0];
  else if (type === 'fileExcelQuestion') vocabForm.fileQuestionVocab = files[0];
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
  <div class="container my-5" style="margin-left: 25%;">
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
      <div class="modal-dialog modal-lg">
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
.container {
  max-width: 1000px;
}

.modal {
  display: flex !important;
  align-items: center;
  justify-content: center;
}

.table th,
.table td {
  vertical-align: middle;
}
</style>
