<template>
    <div :class="['overlay', { hidden: isOverlayHidden }]">
        <!-- Modal đăng nhập -->
        <div class="modal">
            <div class="modal-header">
                <div :class="{ active: activeTab === 'login' }" @click="showForm('login')">Đăng nhập</div>
                <div :class="{ active: activeTab === 'register' }" @click="showForm('register')">Đăng ký</div>
            </div>
            <!-- Form Đăng Nhập -->
            <div v-if="activeTab === 'login'">
                <form id="loginForm">
                    <label for="username">Tên đăng nhập</label>
                    <input type="text" id="username" placeholder="Nhập tên đăng nhập">
                    <label for="password">Mật khẩu</label>
                    <input type="password" id="password" placeholder="Nhập mật khẩu">
                    <div class="remember">
                        <input type="checkbox" id="remember">
                        <label for="remember">Ghi nhớ đăng nhập</label>
                    </div>
                    <button type="submit" class="submit-btn">Đăng nhập</button>
                    <div class="forgot-password">Quên mật khẩu?</div>
                    <div class="guest-login" @click="continueAsGuest">Tiếp tục với tư cách khách</div>
                </form>
            </div>
            <!-- Form Đăng Ký -->
            <div v-if="activeTab === 'register'">
                <form id="registerForm">
                    <label for="fullname">Họ và tên</label>
                    <input type="text" id="fullname" placeholder="Họ và tên">
                    <label for="registerUsername">Tên đăng nhập</label>
                    <input type="text" id="registerUsername" placeholder="Tên đăng nhập">
                    <label for="registerPassword">Mật khẩu</label>
                    <input type="password" id="registerPassword" placeholder="Nhập mật khẩu">
                    <label for="confirmPassword">Nhập lại mật khẩu</label>
                    <input type="password" id="confirmPassword" placeholder="Nhập lại mật khẩu">
                    <label for="email">Email</label>
                    <input type="text" id="email" placeholder="Email">
                    <label for="phone">Số điện thoại</label>
                    <input type="text" id="phone" placeholder="Số điện thoại">
                    <button type="submit" class="submit-btn">Đăng ký</button>
                </form>
            </div>
        </div>
    </div>
</template>

<style scoped> 
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
}

/* Overlay nền đen mờ */
.overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background-color: rgba(0, 0, 0, 0.6);
    display: flex;
    align-items: center;
    justify-content: center;
}

/* Modal đăng nhập, đăng ký */
.modal {
    background-color: white;
    width: 400px;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    text-align: center;
    position: relative;
}

/* Header tabs */
.modal-header {
    display: flex;
    border-bottom: 1px solid #ddd;
    margin-bottom: 20px;
}

.modal-header div {
    flex: 1;
    padding: 10px;
    cursor: pointer;
    font-weight: bold;
}

.modal-header .active {
    color: white;
    background-color: #007bff;
}

/* Nội dung form */
.modal form {
    display: flex;
    flex-direction: column;
}

.modal form label {
    margin-bottom: 5px;
    font-size: 14px;
}

.modal form input[type="text"],
.modal form input[type="password"] {
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 14px;
    width: 100%;
}

/* Các phần khác */
    .modal form .remember {
    display: flex;
    align-items: center;
    margin-bottom: 15px;
    font-size: 14px;
}

.modal form .submit-btn {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px;
    border-radius: 4px;
    font-weight: bold;
    cursor: pointer;
}

.modal .forgot-password {
    margin-top: 10px;
    font-size: 14px;
    color: #666;
}

.modal .guest-login {
    margin-top: 5px;
    font-size: 14px;
    color: #007bff;
    cursor: pointer;
    text-decoration: underline;
}
</style>

<script>
export default{
    name: "LoginFormTest",
    data() {
        return {
            activeTab: 'login',
            isOverlayHidden: false 
        };
    },
    methods: {
        showForm(tab) {
            this.activeTab = tab;
        },
        continueAsGuest(){
            this.isOverlayHidden = true;
        }
    }
}
</script>