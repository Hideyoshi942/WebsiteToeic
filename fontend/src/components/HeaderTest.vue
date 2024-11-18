<template>
  <header>
    <div id="header_cover">
      <div class="cover">
        <router-link to="/" id="logo" style="height: 100%;">
          <img src="../../public/img/logo-01.png" height="100%" alt="Logo">
        </router-link>
        <div class="header_title">
          <h1>Hệ thống thi trực tuyến</h1>
        </div>
      </div>
      <div class="cover">
        <div class="header_authentication">
          <h1>{{ usertoeic.username }}</h1>
        </div>
        <div class="header_question_menu" @click.stop="toggleMenu">
          <i class="fas fa-bars" style="color: gray;"></i>
        </div>
      </div>
    </div>

    <!-- Menu -->
    <div v-if="isMenuVisible" class="menu-overlay" @click="closeMenu">
      <div class="menu-content" @click.stop>
        <router-link to="/" class="menu-item">Trang chủ </router-link>
        <router-link to="/resulttest" class="menu-item">Kết quả thi</router-link>
      </div>
    </div>
  </header>
</template>
<script>
export default {
  name: "HeaderTest",
  data() {
    return {
      usertoeic: JSON.parse(localStorage.getItem("usertoeic") || "{}"), // Lấy dữ liệu từ localStorage
      isMenuVisible: false, // Trạng thái hiển thị menu
    };
  },
  methods: {
    toggleMenu() {
      // Hiển thị hoặc ẩn menu
      this.isMenuVisible = !this.isMenuVisible;
    },
    closeMenu() {
      // Đóng menu
      this.isMenuVisible = false;
    },
  },
};
</script>
<style scoped>
/* Header styles */
#header_cover {
  height: 70px;
  background-color: #333333;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-left: 3%;
  padding-right: 3%;
  box-sizing: border-box;
  width: 100%;
}

.cover {
  height: 100%;
  display: flex;
  align-items: center;
}

.cover h1 {
  margin-left: 25px;
  margin-right: 20px;
}

.header_question_menu {
  width: 40px;
  height: 40px;
  background-color: white;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

h1 {
  color: white;
  font-size: 20px;
}

.header_title > h1 {
  font-size: 30px;
}

/* Menu Overlay */
.menu-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

/* Menu Content */
.menu-content {
  background-color: white;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
  width: 300px;
  text-align: center;
}

.menu-item {
  display: block;
  margin: 10px 0;
  padding: 10px 0;
  font-size: 18px;
  font-weight: bold;
  color: #333;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.menu-item:hover {
  background-color: #f0f0f0;
}
</style>
