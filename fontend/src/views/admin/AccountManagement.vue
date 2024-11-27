<script setup>
import { ref, onMounted, watch, computed } from 'vue';
import axios from 'axios';

// Khai báo một biến để lưu danh sách người dùng
const users = ref([]);
const currentPage = ref(1);
const totalPages = ref(1);
const role = ref('1');
const usertoeic = JSON.parse(localStorage.getItem('usertoeic'));

// Khai báo biến modal và dữ liệu người dùng mới
const isModalVisible = ref(false);
const newUser = ref({
  username: '',
  email: '',
  password: '',
  comfirmPassword: '',
  address: '',
  role: '2',
  phonenumber: '',
});

// Hàm để lấy danh sách người dùng từ API
const fetchUsers = async (page = 1) => {
  try {
    currentPage.value = page;
    const response = await axios.get('http://localhost:8080/api/admin/account/all', {
      params: {
        page: page,
        role: role.value,
      }
    });
    users.value = response.data.content; // Giả sử API trả về dữ liệu trong trường 'content'
    totalPages.value = response.data.totalPages;
  } catch (error) {
    console.error('Có lỗi xảy ra khi tải danh sách người dùng:', error);
  }
};

// Gọi hàm fetchUsers khi component được render
onMounted(() => {
  fetchUsers();
});

// Watch để theo dõi sự thay đổi của role và gọi lại fetchUsers
watch(role, () => {
  fetchUsers(currentPage.value);  // Gọi lại hàm fetchUsers khi role thay đổi
});

// Tạo mảng trang từ totalPages
const pageNumbers = computed(() => {
  return Array.from({ length: totalPages.value }, (_, i) => i + 1);
});

// Hàm để load dữ liệu khi người dùng nhấn vào trang phân trang
const loadData = (page) => {
  fetchUsers(page);
};

// Hàm để xóa người dùng
const deleteUser = async (userId) => {
  try {
    await axios.delete(`http://localhost:8080/api/admin/account/delete/${userId}`);
    fetchUsers(currentPage.value); // Tải lại danh sách sau khi xóa
  } catch (error) {
    console.error('Có lỗi xảy ra khi xóa người dùng:', error);
  }
};

// Hàm để mở modal thêm tài khoản
const openAddAccountModal = () => {
  isModalVisible.value = true;
};

// Hàm để đóng modal
const closeModal = () => {
  isModalVisible.value = false;
};

// Hàm để thêm tài khoản mới
const addNewAccount = async () => {
  try {
    // Kiểm tra xác nhận mật khẩu
    if (newUser.value.password !== newUser.value.comfirmPassword) {
      alert('Mật khẩu xác nhận không khớp!');
      return;
    }
    console.log(newUser.value.password)
    console.log(newUser.value.comfirmPassword)

    // Gửi thông tin tài khoản mới tới API
    const response = await axios.post('http://localhost:8080/register', newUser.value, {
      withCredentials: true
    });
    console.log('Tài khoản mới đã được thêm:', response.data);

    // Đóng modal và tải lại danh sách người dùng
    closeModal();
    fetchUsers(currentPage.value);
  } catch (error) {
    console.error('Có lỗi khi thêm tài khoản:', error);
  }
};
</script>

<template>
  <div class="col-md-9 animated bounce"  style="float: right; margin-right: 70px;">
    <h3 class="page-header">Quản lý Tài khoản</h3>

    <button class="btn btn-success btnThemTaiKhoan" @click="openAddAccountModal">Thêm mới tài khoản</button>
    <hr>
    <div class="form-group form-inline">
      <label for="sel1"><strong>Lọc tài khoản:</strong> </label>
      <select v-model="role" id="vaiTro" class="form-control">
        <option value="1">Vai trò 1</option>
        <option value="2">Vai trò 2</option>
      </select>
    </div>
    <hr>

    <table class="table table-hover taiKhoanTable" style="text-align: center;">
      <thead>
      <tr>
        <th>Mã ND</th>
        <th>Họ tên</th>
        <th>Email</th>
        <th>Số điện thoại</th>
        <th>Địa chỉ</th>
        <th>Vai trò</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.id" style="text-align: center;">
        <td>{{ user.id }}</td>
        <td>{{ user.username }}</td>
        <td>{{ user.email }}</td>
        <td>{{ user.phonenumber }}</td>
        <td>{{ user.address }}</td>
        <td>{{ user.role }}</td>
        <td>
          <button v-if="user.role !== usertoeic.role" @click="deleteUser(user.id)">Xóa</button>
        </td>
      </tr>
      </tbody>
    </table>

    <!-- Phân trang -->
    <ul class="pagination">
      <li v-for="page in pageNumbers" :key="page" class="page-item">
        <a href="#" class="page-link" @click.prevent="loadData(page)">{{ page }}</a>
      </li>
    </ul>
  </div>

  <!-- Modal thêm tài khoản -->
  <div v-if="isModalVisible" style="display: block;">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Thêm tài khoản mới</h5>
          <button type="button" class="close" @click="closeModal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="addNewAccount">
            <div class="form-group">
              <label for="username">Tên đăng nhập</label>
              <input type="text" class="form-control" id="username" v-model="newUser.username" required autocomplete="off">
            </div>
            <div class="form-group">
              <label for="email">Địa chỉ email</label>
              <input type="email" class="form-control" id="email" v-model="newUser.email" required autocomplete="off">
            </div>
            <div class="form-group">
              <label for="password">Mật khẩu</label>
              <input type="password" class="form-control" id="password" v-model="newUser.password" required autocomplete="off">
            </div>
            <div class="form-group">
              <label for="comfirmPassword">Xác nhận mật khẩu</label>
              <input type="password" class="form-control" id="comfirmPassword" v-model="newUser.comfirmPassword" required autocomplete="off">
            </div>
            <div class="form-group">
              <label for="address">Địa chỉ</label>
              <input type="text" class="form-control" id="address" v-model="newUser.address" required autocomplete="off">
            </div>
            <div class="form-group">
              <label for="role">Vai trò</label>
              <select class="form-control" id="role" v-model="newUser.role" required autocomplete="off">
                <option value="1">Vai trò 1</option>
                <option value="2">Vai trò 2</option>
              </select>
            </div>
            <div class="form-group">
              <label for="phonenumber">Số điện thoại</label>
              <input type="text" class="form-control" id="phonenumber" v-model="newUser.phonenumber" required autocomplete="off">
            </div>
            <button type="submit" class="btn btn-primary">Lưu</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Toàn bộ container */
.col-md-9 {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* Header */
.page-header {
  color: #333;
  margin-bottom: 20px;
}

/* Nút thêm tài khoản */
.btnThemTaiKhoan {
  background-color: #28a745;
  color: #fff;
  font-weight: bold;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
.btnThemTaiKhoan:hover {
  background-color: #218838;
}

/* Bảng */
.table {
  width: 100%;
  border-collapse: collapse;
  background: #fff;
}
.table th {
  background-color: #007bff;
  color: #fff;
  text-align: center;
  padding: 10px;
}
.table td {
  padding: 10px;
  text-align: center;
  border: 1px solid #ddd;
}
.table tr:nth-child(even) {
  background-color: #f2f2f2;
}
.table tr:hover {
  background-color: #e9ecef;
}

/* Nút trong bảng */
.table button {
  padding: 6px 12px;
  font-size: 14px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  color: #fff;
  transition: background-color 0.3s ease;
}
.table .btn-warning {
  background-color: #ffc107;
}
.table .btn-warning:hover {
  background-color: #e0a800;
}
.table .btn-danger {
  background-color: #dc3545;
}
.table .btn-danger:hover {
  background-color: #c82333;
}

/* Pagination */
.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
.pagination .page-item {
  list-style: none;
}
.pagination .page-link {
  padding: 8px 12px;
  margin: 0 5px;
  border: 1px solid #007bff;
  border-radius: 4px;
  text-decoration: none;
  color: #007bff;
  transition: background-color 0.3s ease;
}
.pagination .page-link:hover {
  background-color: #007bff;
  color: #fff;
}

/* Modal */
.modal-dialog {
  max-width: 600px;
  margin: 50px auto;
}
.modal-content {
  border-radius: 10px;
  box-shadow: 0 2px 20px rgba(0, 0, 0, 0.2);
}
.modal-header {
  background-color: #007bff;
  color: #fff;
  border-bottom: 1px solid #ccc;
  padding: 15px;
  font-size: 18px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.modal-header .close {
  color: #fff;
  font-size: 24px;
  cursor: pointer;
}
.modal-body {
  padding: 20px;
}
.modal-footer {
  display: flex;
  justify-content: flex-end;
  padding: 15px;
  border-top: 1px solid #ccc;
}
.modal-footer .btn {
  padding: 8px 20px;
  border-radius: 4px;
  border: none;
  transition: background-color 0.3s ease;
}
.modal-footer .btn-primary {
  background-color: #007bff;
  color: #fff;
}
.modal-footer .btn-primary:hover {
  background-color: #0056b3;
}
.modal-footer .btn-secondary {
  background-color: #6c757d;
  color: #fff;
}
.modal-footer .btn-secondary:hover {
  background-color: #5a6268;
}

/* Form */
.form-group {
  margin-bottom: 15px;
}
.form-group label {
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
  display: inline-block;
}
.form-control {
  width: 100%;
  padding: 10px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: border-color 0.3s ease;
}
.form-control:focus {
  border-color: #007bff;
  outline: none;
}

/* Background Overlay for Modal */
[style^='display: block;'] {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1000;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>

