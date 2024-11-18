<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

function navigate(path) {
  router.push(path)
}
</script>

<template>
  <div class="container-fluid">
    <div class="col-md-3">
      <div id="sidebar">
        <div class="container-fluid tmargin">
          <div class="input-group">
          </div>
        </div>
        <ul class="nav navbar-nav side-bar">
          <li class="side-bar tmargin">
            <a @click.prevent="navigate('/admin/listeningmanagement')">
              <span class="glyphicon glyphicon-shopping-cart">&nbsp;</span>Quản lý Bài Nghe</a>
          </li>

          <li class="side-bar">
            <a @click.prevent="navigate('/admin/readingmanagement')">
              <span class="glyphicon glyphicon-folder-open">&nbsp;</span>Quản lý Bài đọc</a>
          </li>

          <li class="side-bar">
            <a @click.prevent="navigate('/admin/practicetestmanagement')">
              <span class="glyphicon glyphicon-tasks">&nbsp;</span>Quản lý Đề thi</a>
          </li>

          <li class="side-bar">
            <a @click.prevent="navigate('/admin/grammarmanagement')">
              <span class="glyphicon glyphicon-flag">&nbsp;</span>Quản lý Bài Ngữ pháp</a>
          </li>

          <li class="side-bar">
            <router-link to="/admin/vocabularymanagement">
              <span class="glyphicon glyphicon-flag">&nbsp;</span>Quản lý Bài Từ vựng
            </router-link>
          </li>

          <li class="side-bar main-menu">
            <a @click.prevent="navigate('/admin/accountmanagement')">
              <span class="glyphicon glyphicon-th-list">&nbsp;</span>Quản lý Tài khoản</a>
          </li>

          <li class="side-bar">
            <router-link to="/admin/profile">
              <span class="glyphicon glyphicon-user">&nbsp;</span>Thông tin cá nhân
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container-fluid {
  padding-top: 80px;
}

#sidebar {
  position: fixed;
  top: 50px;
  left: 220px;
  width: 220px;
  margin-left: -220px;
  border: none;
  border-radius: 0;
  overflow-y: auto;
  background-color: #222;
  bottom: 0;
  overflow-x: hidden;
  padding-bottom: 40px;
}

.side-bar > li > a {
  color: #eee;
  width: 220px;
}

.side-bar li a:hover,
.side-bar li a:focus {
  background-color: #333;
}

.tmargin {
  margin-top: 15px;
}

span.error {
  color: red;
  margin-left: 5px;
}
</style>
