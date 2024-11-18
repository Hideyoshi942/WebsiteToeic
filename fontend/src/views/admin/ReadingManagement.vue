<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

// Khai báo các biến
const baiDocs = ref([]);
const totalPages = ref(1);
const partSearch = ref('');
const doKhoSearch = ref('');
const currentPage = ref(1);
const searchId = ref('');
const showModal = ref(false);
const newBaiDoc = ref({
  id: '',
  tenBaiDoc: '',
  part: '',
  doKho: '',
  script: '',
  fileExcelCauHoi: null
});

// Hàm tải dữ liệu bài đọc
const loadData = async (page = 1) => {
  currentPage.value = page;
  try {
    const response = await axios.get('http://localhost:8080/api/admin/reading/all', {
      params: {
        page,
        doKho: doKhoSearch.value,
        part: partSearch.value,
        searchId: searchId.value
      }
    });
    baiDocs.value = response.data.content || [];
    totalPages.value = response.data.totalPages || 1;
  } catch (error) {
    console.error('Có lỗi xảy ra khi tải dữ liệu:', error);
    alert('Không thể tải dữ liệu. Vui lòng thử lại sau.');
  }
};

// Hàm hiển thị tên độ khó
const getDoKhoText = (doKho) => {
  const levels = { 1: 'Dễ', 2: 'Trung bình', 3: 'Khó' };
  return levels[doKho] || '';
};

// Hàm hiển thị tên phần thi
const getPartText = (part) => {
  const parts = { 5: 'Part 5-Complete sentence', 6: 'Part 6-Complete the paragraph', 7: 'Part 7-Reading comprehension' };
  return parts[part] || '';
};

// Hàm mở modal thêm hoặc cập nhật bài đọc
const openModal = (baiDoc = null) => {
  if (baiDoc) {
    newBaiDoc.value = { ...baiDoc };  // Cập nhật dữ liệu khi sửa bài đọc
  } else {
    newBaiDoc.value = {
      id: '',
      tenBaiDoc: '',
      part: '',
      doKho: '',
      script: '',
      fileExcelCauHoi: null
    };
  }
  showModal.value = true;
};

// Hàm đóng modal
const closeModal = () => {
  showModal.value = false;
};

// Hàm gửi form để tạo mới hoặc cập nhật bài đọc
const submitForm = async () => {
  const formData = new FormData();
  formData.append('readingname', newBaiDoc.value.tenBaiDoc);
  formData.append('readingpart', newBaiDoc.value.part);
  formData.append('readinglevel', newBaiDoc.value.doKho);
  formData.append('readingscript', newBaiDoc.value.script);

  if (newBaiDoc.value.fileExcelCauHoi) {
    formData.append('file_questionreading', newBaiDoc.value.fileExcelCauHoi);
  }

  try {
    if (newBaiDoc.value.id) {
      await axios.put(`http://localhost:8080/api/admin/reading/update/${newBaiDoc.value.id}`, formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });
    } else {
      await axios.post('http://localhost:8080/api/admin/reading/save', formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });
    }

    alert('Bài đọc đã được lưu thành công!');
    closeModal();
    loadData(currentPage.value);
  } catch (error) {
    console.error('Có lỗi xảy ra khi lưu bài đọc:', error);
    alert('Không thể lưu bài đọc. Vui lòng thử lại sau.');
  }
};

// Hàm xóa bài đọc
const deleteReading = async (id) => {
  if (confirm('Bạn có chắc chắn muốn xóa bài đọc này?')) {
    try {
      await axios.delete(`http://localhost:8080/api/admin/reading/delete/${id}`);
      alert('Xóa bài đọc thành công!');
      loadData(currentPage.value);
    } catch (error) {
      console.error('Có lỗi xảy ra khi xóa bài đọc:', error);
      alert('Không thể xóa bài đọc. Vui lòng thử lại sau.');
    }
  }
};

// Hàm xử lý thay đổi file ảnh
const handleFileChange = (event, field) => {
  newBaiDoc.value[field] = event.target.files[0];
};

// Hàm xử lý phân trang
const changePage = (page) => {
  loadData(page);
};

// Tải dữ liệu ban đầu
onMounted(() => {
  loadData();
});
</script>



<template>
  <div class="col-md-9 animated bounce" style="float: right; margin-right: 50px;">
    <h3 class="page-header" style="color: black;">Quản lý Bài Đọc</h3>

    <button class="btn btn-success" @click="openModal">Thêm mới bài Đọc</button>
    <hr />


    <form class="form-inline">
      <div class="form-group">
        <select class="form-control" v-model="partSearch">
          <option value="">Tất cả</option>
          <option value="5">Part 5</option>
          <option value="6">Part 6</option>
          <option value="7">Part 7</option>
        </select>
      </div>
      &nbsp;&nbsp;
      <div class="form-group">
        <select class="form-control" v-model="doKhoSearch">
          <option value="">Tất cả</option>
          <option value="1">Mức dễ</option>
          <option value="2">Mức trung bình</option>
          <option value="3">Mức khó</option>
        </select>
      </div>
      &nbsp;&nbsp;
      <button type="button" class="btn btn-primary" @click="loadData(1)">Duyệt Bài đọc</button>
    </form>

    <table class="table table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>Tên bài đọc</th>
        <th>Part LC</th>
        <th>Độ khó</th>
        <th>Thao tác</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="baiDoc in baiDocs" :key="baiDoc.id">
        <td>{{ baiDoc.readingid }}</td>
        <td>{{ baiDoc.readingname }}</td>
        <td>{{ getPartText(baiDoc.readingpart) }}</td>
        <td>{{ getDoKhoText(baiDoc.readinglevel) }}</td>
        <td>
          <button class="btn btn-primary" @click="openModal(baiDoc)" style="margin-right: 10px;">Cập nhật</button>
          <button class="btn btn-danger" @click="deleteReading(baiDoc.id)">Xóa</button>
        </td>
      </tr>
      </tbody>
    </table>

    <ul class="pagination">
      <li v-for="page in totalPages" :key="page" :class="{ active: page === currentPage }">
        <a class="page-link" @click.prevent="changePage(page)">{{ page }}</a>
      </li>
    </ul>

    <!-- Modal -->
    <!-- Modal -->
    <div v-if="showModal">
      <div class="modal-overlay" @click="closeModal"></div> <!-- Nền tối -->
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ newBaiDoc.id ? 'Cập nhật' : 'Thêm mới' }} bài đọc</h5>
            <button type="button" class="close" @click="closeModal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="submitForm" enctype="multipart/form-data">
              <div class="form-group">
                <label for="tenBaiDoc">Tên bài đọc</label>
                <input type="text" class="form-control" id="tenBaiDoc" v-model="newBaiDoc.tenBaiDoc" required />
              </div>
              <div class="form-group">
                <label for="part">Phần</label>
                <select class="form-control" id="part" v-model="newBaiDoc.part">
                  <option value="5">Part 5-Complete sentence</option>
                  <option value="6">Part 6-Complete the paragraph</option>
                  <option value="7">Part 7-Reading comprehension</option>
                </select>
              </div>
              <div class="form-group">
                <label for="doKho">Độ khó</label>
                <select class="form-control" id="doKho" v-model="newBaiDoc.doKho">
                  <option value="1">Dễ</option>
                  <option value="2">Trung bình</option>
                  <option value="3">Khó</option>
                </select>
              </div>
              <div class="form-group">
                <label for="script">Script</label>
                <textarea class="form-control" id="script" v-model="newBaiDoc.script" required></textarea>
              </div>
              <div class="form-group">
                <label for="fileExcelCauHoi">File câu hỏi bài tập đọc (Excel)</label>
                <input type="file" class="form-control" id="fileExcelCauHoi" @change="handleFileChange($event, 'fileExcelCauHoi')" />
              </div>
              <button type="submit" class="btn btn-primary">Lưu</button>
            </form>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<style scoped>
/* Nền tối phía sau modal */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1040;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* Modal chính giữa màn hình */
.modal-dialog {
  position: fixed;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%); /* Căn giữa theo cả X và Y */
  z-index: 1050;
  width: 500px; /* Độ rộng của modal */
  max-width: 90%; /* Đáp ứng trên màn hình nhỏ */
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  padding: 20px;
  animation: fadeIn 0.3s ease-in-out;
}

/* Hiệu ứng xuất hiện modal */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translate(-50%, -55%); /* Giảm nhẹ khi xuất hiện */
  }
  to {
    opacity: 1;
    transform: translate(-50%, -50%);
  }
}

/* Đóng modal */
.modal-header .close {
  border: none;
  background: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #000;
}

/* Nút trong modal */
.modal-footer button {
  margin-left: 10px;
}
</style>

