<template>
  <div class="col-md-9 animated bounce" style="float: right; margin-right: 70px;">
    <h3 class="page-header" style="color: black">Quản lý Bài Nghe</h3>
    <button class="btn btn-success" @click="openModal">Thêm mới bài nghe</button>
    <hr style="padding-top: 10px; padding-bottom: 10px;"/>

    <!-- Tìm kiếm -->
    <form class="form-inline">
      <div class="form-group">
        <select class="form-control" v-model="listeningpart">
          <option value="">Tất cả</option>
          <option value="1">Part 1</option>
          <option value="2">Part 2</option>
          <option value="3">Part 3</option>
          <option value="4">Part 4</option>
        </select>
      </div>
      &nbsp;&nbsp;
      <div class="form-group">
        <select class="form-control" v-model="listeninglevel">
          <option value="">Tất cả</option>
          <option value="1">Mức dễ</option>
          <option value="2">Mức trung bình</option>
          <option value="3">Mức khó</option>
        </select>
      </div>
      &nbsp;&nbsp;
      <button type="button" class="btn btn-primary" @click="loadData(1)">Duyệt Bài nghe</button>
    </form>

    <!-- Bảng bài nghe -->
    <table class="table table-hover baiNgheTable">
      <thead>
      <tr>
        <th>ID</th>
        <th>Tên bài nghe</th>
        <th>Part LC</th>
        <th>Độ khó</th>
        <th>Hành động</th> <!-- Thêm cột Hành động -->
      </tr>
      </thead>
      <tbody>
      <tr v-for="baiNghe in baiNghes" :key="baiNghe.listeningid">
        <td>{{ baiNghe.listeningid }}</td>
        <td>{{ baiNghe.listeningname }}</td>
        <td>{{ getPartText(baiNghe.listeningpart) }}</td>
        <td>{{ getDoKhoText(baiNghe.listeninglevel) }}</td>
        <td>
          <button class="btn btn-danger btn-sm" @click="deleteBaiNghe(baiNghe.listeningid)">Xóa</button>
        </td> <!-- Thêm nút Xóa -->
      </tr>
      </tbody>
    </table>


    <!-- Phân trang -->
    <ul class="pagination">
      <li v-for="page in totalPages" :key="page" class="page-item">
        <a href="#" class="page-link" @click.prevent="loadData(page)">{{ page }}</a>
      </li>
    </ul>

    <!-- Modal thêm bài nghe -->
    <!-- Modal thêm bài nghe -->
    <div v-if="showModal">
      <div class="modal-overlay" @click="closeModal"></div> <!-- Nền tối -->
      <div class="modal-dialog" role="document" style="margin-top: 90px;">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Tạo mới/Cập nhật bài nghe</h5>
            <button type="button" class="close" @click="closeModal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="submitForm" enctype="multipart/form-data">
              <div class="form-group">
                <label for="listeningname">Tên bài nghe:</label>
                <input type="text" v-model="newBaiNghe.listeningname" class="form-control" placeholder="Nhập vào tên bài nghe" required>
              </div>

              <div class="form-group">
                <label for="phanThi">Phần thi (Part):</label>
                <select v-model="newBaiNghe.listeningpart" class="form-control">
                  <option value="1">Part 1-Photographs</option>
                  <option value="2">Part 2-Question response</option>
                  <option value="3">Part 3-Short Conversations</option>
                  <option value="4">Part 4-Short talks</option>
                </select>
              </div>

              <div class="form-group">
                <label for="doKho">Độ khó:</label>
                <select v-model="newBaiNghe.listeninglevel" class="form-control">
                  <option value="1">Mức dễ</option>
                  <option value="2">Mức trung bình</option>
                  <option value="3">Mức khó</option>
                </select>
              </div>

              <div class="form-group">
                <label for="file_questionlistening">File câu hỏi bài nghe (Excel):</label>
                <input type="file" ref="excelInput" @change="previewFile" class="form-control" id="file_questionlistening" required>
              </div>

              <div class="form-group">
                <label for="file_questionlistening">List file nghe (Audio):</label>
                <input
                    type="file"
                    ref="audioInput"
                    @change="previewAudioFiles"
                    class="form-control"
                    id="file_questionlistening"
                    multiple
                    required>
              </div>
              <div class="form-group">
                <label for="file_questionlistening">List file ảnh (Image):</label>
                <input
                    type="file"
                    ref="imageInput"
                    @change="previewImageFiles"
                    class="form-control"
                    id="file_questionlistening_image"
                    multiple
                    required
                >
              </div>


              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" @click="closeModal">Hủy</button>
                <button type="submit" class="btn btn-primary">Xác nhận</button>
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
import axios from 'axios';

const baiNghes = ref([]);
const totalPages = ref(1);
const listeningpart = ref('');
const listeninglevel = ref('');
const currentPage = ref(1);
const showModal = ref(false);
const newBaiNghe = ref({
  listeningname: '',
  listeningpart: '',
  listeninglevel: '',
  file_questionlistening: null,
  file_questionlisteningaudio: [],
  file_questionlisteningimage: [],
});

const excelInput = ref(null);

// Hàm mở modal
const openModal = () => {
  showModal.value = true;
};

// Hàm đóng modal
const closeModal = () => {
  showModal.value = false;
};

// Hàm gọi API để tải dữ liệu
const loadData = (page = 1) => {
  currentPage.value = page;
  axios.get('http://localhost:8080/api/admin/listening/all', {
    params: {
      page: page,
      listeningpart: listeningpart.value,
      listeninglevel: listeninglevel.value
    }
  })
  .then(response => {
    baiNghes.value = response.data.content;
    totalPages.value = response.data.totalPages;
  })
  .catch(error => {
    console.error('Có lỗi xảy ra khi tải dữ liệu:', error);
  });
};

// Hàm xóa bài nghe
const deleteBaiNghe = (listeningid) => {
  if (confirm('Bạn có chắc chắn muốn xóa bài nghe này?')) {
    axios.delete(`http://localhost:8080/api/admin/listening/delete/${listeningid}`)
    .then(() => {
      alert('Bài nghe đã được xóa thành công');
      loadData(currentPage.value); // Tải lại dữ liệu sau khi xóa
    })
    .catch(error => {
      console.error('Có lỗi xảy ra khi xóa bài nghe:', error);
    });
  }
};

// Hàm chuyển part thành text
const getPartText = (part) => {
  switch (part) {
    case 1: return 'Part 1-Photographs';
    case 2: return 'Part 2-Question response';
    case 3: return 'Part 3-Short Conversations';
    case 4: return 'Part 4-Short talks';
    default: return '';
  }
};

// Hàm chuyển độ khó thành text
const getDoKhoText = (doKho) => {
  switch (doKho) {
    case 1: return 'Dễ';
    case 2: return 'Trung bình';
    case 3: return 'Khó';
    default: return '';
  }
};

// Preview file excel
const previewFile = (event) => {
  newBaiNghe.value.file_questionlistening = event.target.files[0];
};
// Preview file audio
const previewAudioFiles = (event) => {
  newBaiNghe.value.file_questionlisteningaudio = Array.from(event.target.files);
  console.log("Audio Files: ", newBaiNghe.value.file_questionlisteningaudio);
};

// Preview file image
const previewImageFiles = (event) => {
  newBaiNghe.value.file_questionlisteningimage = Array.from(event.target.files);
  console.log("Image Files: ", newBaiNghe.value.file_questionlisteningimage);
};


// Hàm xử lý submit form
const submitForm = () => {
  const formData = new FormData();
  formData.append('listeningname', newBaiNghe.value.listeningname);
  formData.append('listeningpart', newBaiNghe.value.listeningpart);
  formData.append('listeninglevel', newBaiNghe.value.listeninglevel);
  if (newBaiNghe.value.file_questionlistening) formData.append('file_questionlistening', newBaiNghe.value.file_questionlistening);
  // Thêm các file audio
  newBaiNghe.value.file_questionlisteningaudio.forEach((file) => {
    formData.append('file_questionlisteningaudio', file);
  });

  // Thêm các file ảnh
  newBaiNghe.value.file_questionlisteningimage.forEach((file) => {
    formData.append('file_questionlisteningimage', file);
  });
  axios.post('http://localhost:8080/api/admin/listening/save', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    }
  })
  .then(() => {
    alert('Bài nghe đã được tạo thành công');
    showModal.value = false;
    loadData(currentPage.value); // Reload dữ liệu sau khi tạo thành công
  })
  .catch(error => {
    console.error('Có lỗi xảy ra khi tạo bài nghe:', error);
  });
};

// Tải dữ liệu ban đầu khi component được mount
onMounted(() => {
  loadData();
});
</script>


<style scoped>
/* Header */
.page-header {
  text-align: center;
  color: #2c3e50;
  font-size: 2rem;
  margin-bottom: 20px;
  text-transform: uppercase;
  font-weight: bold;
}

/* Nút Thêm bài nghe */
.btn-success {
  font-size: 1rem;
  padding: 10px 20px;
  border-radius: 5px;
  font-weight: bold;
  background-color: #28a745;
  border: none;
  color: white;
  transition: background-color 0.3s ease-in-out;
}

.btn-success:hover {
  background-color: #218838;
}

/* Form tìm kiếm */
.form-inline {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  gap: 10px;
  margin-bottom: 20px;
}

.form-inline .form-control {
  width: 200px;
  padding: 8px;
  font-size: 1rem;
  border-radius: 5px;
  border: 1px solid #ced4da;
  transition: border-color 0.3s ease-in-out;
}

.form-inline .form-control:focus {
  border-color: #80bdff;
  outline: none;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

/* Nút Duyệt bài nghe */
.btn-primary {
  padding: 8px 15px;
  font-size: 1rem;
  border-radius: 5px;
  background-color: #007bff;
  border: none;
  color: white;
  transition: background-color 0.3s ease-in-out;
}

.btn-primary:hover {
  background-color: #0056b3;
}

/* Bảng bài nghe */
.table {
  width: 100%;
  margin-top: 20px;
  border-collapse: collapse;
  text-align: left;
  background-color: #f8f9fa;
  border-radius: 5px;
  overflow: hidden;
}

.table th, .table td {
  padding: 12px 15px;
  border-bottom: 1px solid #dee2e6;
}

.table th {
  background-color: #343a40;
  color: white;
  text-transform: uppercase;
  font-size: 0.9rem;
}

.table tr:nth-child(even) {
  background-color: #f2f2f2;
}

.table tr:hover {
  background-color: #f1f1f1;
  cursor: pointer;
}

.btn-danger {
  font-size: 0.8rem;
  padding: 5px 10px;
  border-radius: 3px;
  background-color: #dc3545;
  color: white;
  border: none;
  transition: background-color 0.3s ease-in-out;
}

.btn-danger:hover {
  background-color: #c82333;
}

/* Pagination */
.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.pagination .page-item {
  margin: 0 5px;
}

.pagination .page-link {
  padding: 10px 15px;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  text-decoration: none;
  transition: background-color 0.3s ease-in-out;
}

.pagination .page-link:hover {
  background-color: #0056b3;
}

/* Modal */
.modal-dialog {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1050;
  width: 600px; /* Tăng kích thước modal */
  max-width: 90%; /* Responsive trên màn hình nhỏ */
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  padding: 20px;
  animation: fadeIn 0.3s ease-in-out;
}

/* Modal header */
.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #dee2e6;
  margin-bottom: 15px;
}

.modal-title {
  font-size: 1.5rem;
  color: #343a40;
  font-weight: bold;
}

.modal-header .close {
  font-size: 1.5rem;
  color: #6c757d;
  border: none;
  background: none;
  cursor: pointer;
}

/* Modal footer */
.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

.modal-footer .btn {
  padding: 10px 15px;
  border-radius: 5px;
  font-size: 1rem;
}

/* Nút trong modal */
.modal-footer .btn-secondary {
  background-color: #6c757d;
  color: white;
  border: none;
  transition: background-color 0.3s ease-in-out;
}

.modal-footer .btn-secondary:hover {
  background-color: #5a6268;
}

.modal-footer .btn-primary {
  background-color: #007bff;
  color: white;
  border: none;
  transition: background-color 0.3s ease-in-out;
}

.modal-footer .btn-primary:hover {
  background-color: #0056b3;
}

/* Modal body */
.modal-body {
  font-size: 1rem;
}

.modal-body .form-control {
  font-size: 1rem;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ced4da;
}

.modal-body .form-control:focus {
  border-color: #80bdff;
  outline: none;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

/* Nền tối phía sau modal */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1040;
}

/* Animation */
@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

</style>
