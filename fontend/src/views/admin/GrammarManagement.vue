<template>
  <div class="container mt-5" style="margin-left: 20%;">
    <!-- Tiêu đề và nút thêm mới -->
    <div>
      <h3 class="page-header text-primary">Quản lý bài hướng dẫn Grammar</h3>
      <button class="btn btn-success mt-3" @click="openModal">Thêm mới</button>
    </div>

    <!-- Thông báo -->
    <div v-if="infoMessage" class="alert alert-success text-center mt-3">{{ infoMessage }}</div>
    <div v-if="errorMessage" class="alert alert-danger text-center mt-3">{{ errorMessage }}</div>
    <hr />

    <!-- Bảng danh sách Grammar -->
    <div class="table-responsive">
      <table class="table table-hover align-middle">
        <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Tên bài Grammar</th>
          <th>Ảnh</th>
          <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="grammar in grammars" :key="grammar.id">
          <td>{{ grammar.id }}</td>
          <td>{{ grammar.name }}</td>
          <td>
            <img :src="grammar.image" alt="Grammar Image" class="img-thumbnail" />
          </td>
          <td>
            <button style="margin-right: 10px;" class="btn btn-warning btn-sm mx-2" @click="editGrammar(grammar.id)">Cập nhật</button>
            <button class="btn btn-danger btn-sm" @click="deleteGrammar(grammar.id)">Xóa</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <!-- Modal thêm/cập nhật Grammar -->
    <div v-if="isModalVisible" class="modal show fade d-block" tabindex="-1" style="background-color: rgba(0, 0, 0, 0.5);">
      <div class="modal-dialog modal-md modal-dialog-centered" style="margin-top: 15%;">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ modalTitle }}</h5>
            <button type="button" class="btn-close" @click="closeModal">&times;</button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="saveGrammar">
              <!-- Nhập tên bài Grammar -->
              <div class="mb-3">
                <label for="grammarName" class="form-label">Grammar</label>
                <input
                    type="text"
                    id="grammarName"
                    v-model="currentGrammar.name"
                    class="form-control"
                    placeholder="Nhập tên bài Grammar"
                    required
                />
              </div>

              <!-- Nhập ảnh bài Grammar -->
              <div class="mb-3">
                <label for="grammarImage" class="form-label">Ảnh bài Grammar</label>
                <input
                    type="file"
                    id="grammarImage"
                    @change="handleFileChange($event, 'image')"
                    class="form-control"
                    accept="image/*"
                />
              </div>
              <div class="mb-3">
                <label for="grammarName" class="form-label">Tên bài Grammar</label>
                <input
                    type="text"
                    id="grammarName"
                    v-model="currentGrammar.contenthtml"
                    class="form-control"
                    placeholder="Nhập tên bài Grammar"
                    required
                />
              </div>

              <!-- Markdown Editor -->
              <div class="mb-3">
                <label for="contentMarkdown" class="form-label">Nội dung bài Grammar</label>
                <vue-easymde v-model="currentGrammar.contentMarkdown" />
              </div>

              <!-- File câu hỏi ngữ pháp -->
              <div class="mb-3">
                <label for="excelFile" class="form-label">File câu hỏi ngữ pháp (Excel)</label>
                <input
                    type="file"
                    id="excelFile"
                    @change="handleFileChange($event, 'file')"
                    class="form-control"
                    accept=".xlsx,.xls"
                />
              </div>

              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" @click="closeModal">Đóng</button>
                <button type="submit" class="btn btn-primary">
                  {{ currentGrammar.id ? 'Cập nhật' : 'Thêm mới' }}
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import VueEasymde from "vue-easymde";
import "easymde/dist/easymde.min.css";
import axios from 'axios';

// Biến trạng thái
const grammars = ref([]);
const currentGrammar = ref({
  id: null,
  name: '',
  image: null,
  contenthtml: '',
  contentMarkdown: '',
  file: null,
});
const isModalVisible = ref(false);
const modalTitle = ref('Thêm mới bài Grammar');
const infoMessage = ref('');
const errorMessage = ref('');
// Tải danh sách Grammar
const loadGrammars = async () => {
  try {
    const { data } = await axios.get('http://localhost:8080/api/admin/grammar/loadGrammar');
    grammars.value = data.map(grammar => ({
      id: grammar.grammarid,
      name: grammar.grammarname,
      image: `http://localhost:8080${grammar.grammarimage}`,
    }));
  } catch (error) {
    console.error('Error loading grammars:', error);
  }
};

// Mở modal
const openModal = () => {
  resetCurrentGrammar();
  modalTitle.value = 'Thêm mới bài Grammar';
  isModalVisible.value = true;
};

// Đóng modal
const closeModal = () => {
  isModalVisible.value = false;
  infoMessage.value = '';
  errorMessage.value = '';
};

// Đặt lại dữ liệu
const resetCurrentGrammar = () => {
  currentGrammar.value = {
    id: null,
    name: '',
    image: null,
    contenthtml: '',
    contentMarkdown: '',
    file: null,
  };
};

// Xử lý thay đổi file
const handleFileChange = (event, type) => {
  const file = event.target.files[0];
  if (type === 'image') {
    currentGrammar.value.image = file;
  } else if (type === 'file') {
    currentGrammar.value.file = file;
  }
};

// Lưu Grammar
const saveGrammar = async () => {
  const formData = new FormData();
  formData.append('grammarname', currentGrammar.value.name);
  formData.append('grammarimage', currentGrammar.value.image);
  formData.append('grammarcontenthtml', currentGrammar.value.contenthtml);
  formData.append('grammarcontentmarkdown', currentGrammar.value.contentMarkdown);
  formData.append('file_questiongrammar', currentGrammar.value.file);

  try {
    if (currentGrammar.value.id) {
      formData.append('grammarid', currentGrammar.value.id);
      await axios.put('http://localhost:8080/api/admin/grammar/update', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
      });
      infoMessage.value = 'Cập nhật bài grammar thành công!';
    } else {
      await axios.post('http://localhost:8080/api/admin/grammar/save', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
      });
      infoMessage.value = 'Thêm mới bài grammar thành công!';
    }
    closeModal();
    loadGrammars();
  } catch (error) {
    errorMessage.value = 'Có lỗi xảy ra. Vui lòng thử lại!';
    console.error('Error saving grammar:', error);
  }
};

// Chỉnh sửa Grammar
const editGrammar = async id => {
  try {
    const { data } = await axios.get(`http://localhost:8080/api/admin/grammar/infoGrammar/${id}`);
    currentGrammar.value = {
      id,
      name: data.grammarname,
      image: null,
      contentMarkdown: data.grammarcontentmarkdown,
      file: null,
    };
    modalTitle.value = 'Cập nhật bài Grammar';
    isModalVisible.value = true;
  } catch (error) {
    console.error('Error fetching grammar:', error);
  }
};

// Xóa Grammar
const deleteGrammar = async id => {
  if (confirm('Bạn có chắc muốn xóa bài Grammar này?')) {
    try {
      await axios.post(`http://localhost:8080/api/admin/grammar/delete/${id}`);
      infoMessage.value = 'Xóa bài Grammar thành công!';
      loadGrammars();
    } catch (error) {
      errorMessage.value = 'Xóa bài Grammar thất bại!';
      console.error('Error deleting grammar:', error);
    }
  }
};

// Tải dữ liệu khi khởi tạo
onMounted(() => {
  loadGrammars();
});
</script>

<style scoped>
/* Tổng thể */
.container {
  max-width: 1200px;
  margin: auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

/* Tiêu đề */
h3.page-header {
  font-size: 24px;
  font-weight: bold;
  color: #4a90e2;
  margin-bottom: 20px;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
}

/* Button thêm mới */
.btn-success {
  font-size: 16px;
  padding: 10px 15px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.btn-success:hover {
  background-color: #218838;
}

/* Thông báo */
.alert {
  font-size: 16px;
  border-radius: 5px;
}

/* Bảng */
.table {
  width: 100%;
  background-color: white;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
}

.table th,
.table td {
  text-align: center;
  padding: 12px;
}

.table th {
  background-color: #4a90e2;
  color: white;
  font-weight: bold;
}

.table td {
  border-bottom: 1px solid #ddd;
}

.table-hover tbody tr:hover {
  background-color: #f5f5f5;
}

/* Ảnh thumbnail */
.img-thumbnail {
  width: 100px;
  height: auto;
  border: 1px solid #ddd;
  padding: 5px;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Nút hành động */
.btn-warning {
  background-color: #ffc107;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  font-size: 14px;
  transition: background-color 0.3s ease;
}

.btn-warning:hover {
  background-color: #e0a800;
}

.btn-danger {
  background-color: #dc3545;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  font-size: 14px;
  transition: background-color 0.3s ease;
}

.btn-danger:hover {
  background-color: #c82333;
}

/* Modal */
.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1050;
  overflow-y: auto;
}

.modal-dialog {
  max-width: 600px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
  animation: scaleIn 0.3s ease-in-out;
}

@keyframes scaleIn {
  from {
    transform: scale(0.8);
    opacity: 0;
  }
  to {
    transform: scale(1);
    opacity: 1;
  }
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
  border-bottom: 1px solid #ddd;
}

.modal-header h5 {
  font-size: 18px;
  color: #333;
  margin: 0;
}

.modal-header .btn-close {
  font-size: 20px;
  background: none;
  border: none;
  color: #666;
  cursor: pointer;
  transition: color 0.3s ease;
}

.modal-header .btn-close:hover {
  color: #333;
}

.modal-body {
  padding: 20px;
  max-height: 400px;
  overflow-y: auto;
}

.modal-body label {
  font-weight: bold;
  color: #333;
}

.modal-body .form-control {
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 10px;
  font-size: 14px;
  margin-top: 5px;
  transition: border-color 0.3s ease;
}

.modal-body .form-control:focus {
  border-color: #4a90e2;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  padding: 15px 20px;
  border-top: 1px solid #ddd;
  gap: 10px;
}

.modal-footer .btn {
  padding: 8px 15px;
  font-size: 14px;
  border-radius: 5px;
}

.modal-footer .btn-secondary {
  background-color: #6c757d;
  color: white;
  border: none;
  transition: background-color 0.3s ease;
}

.modal-footer .btn-secondary:hover {
  background-color: #5a6268;
}

.modal-footer .btn-primary {
  background-color: #007bff;
  color: white;
  border: none;
  transition: background-color 0.3s ease;
}

.modal-footer .btn-primary:hover {
  background-color: #0056b3;
}
</style>

