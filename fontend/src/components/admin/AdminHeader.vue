<template>
  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand">Web Toeic - Trang Quản Trị</a>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
          <li>
            <a href="">
              <span class="glyphicon glyphicon-user">&nbsp;</span>Xin chào: {{ username }}</a>
          </li>
          <li>
            <button @click="logout()" style="margin-top: 15px;"><a href="" style="text-decoration: none; color: #eee;"><span class="glyphicon glyphicon-log-out"></span> Đăng xuất</a></button>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'AdminHeader',
  computed: {
    username() {
      const usertoeic = JSON.parse(localStorage.getItem('usertoeic') || '{}');
      return usertoeic.username || 'Khách';
    }
  },
  methods: {
    logout() {
      localStorage.removeItem('usertoeic');
      this.$router.push('/login');
    }
  },
};
</script>

<style scoped>
body {
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

th {
  text-align: center;
}

.link > a {
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

.navbar-right .dropdown-menu {
  left: 0;
  right: auto;
}

.navbar-collapse .navbar-nav.navbar-right:last-child {
  margin-right: 0;
}

span.error {
  color: red;
  margin-left: 5px;
}

canvas {
  width: 900px !important;
  height: 450px !important;
}
</style>
