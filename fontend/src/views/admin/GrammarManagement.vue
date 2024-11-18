<template>
  <div class="container mt-5" style="margin-left: 25%;">
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
                <label for="grammarName" class="form-label">Tên bài Grammar</label>
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
import VueEasymde from 'vue-easymde';
import axios from 'axios';

// Biến trạng thái
const grammars = ref([]);
const currentGrammar = ref({
  id: null,
  name: '',
  image: null,
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
  formData.append('grammarcontenthtml', currentGrammar.value.contentMarkdown);
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
.container {
  max-width: 900px;
  margin: auto;
}

.modal-dialog {
  max-width: 600px;
}

.modal-body {
  max-height: 400px;
  overflow-y: auto;
}

.img-thumbnail {
  width: 80px;
  height: auto;
  border: 1px solid #ddd;
  padding: 5px;
  border-radius: 4px;
}
</style>
