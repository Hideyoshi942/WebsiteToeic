<template>
<main>
    <div id="div-authencation">
        <div class="div-title">
            <h1>ROCKET - XÁC THỰC</h1>
        </div>
        <div>
            <div id="login">
              <h1>Đăng nhập</h1>
              <div>
                    <form name="form" @submit.prevent="submit">
                        <label for="email">Email</label>
                        <input type="text" placeholder="EMAIL" v-model="data.email" style="color: black">
                        <label for="password">Mật khẩu</label>
                        <input type="password" placeholder="MẬT KHẨU" v-model="data.password" style="color: black">
                        <div style="display: flex;justify-content: space-between;">
                            <div style="width: 45%;display: flex;align-items: center;">
                                <input type="checkbox"><label>Ghi nhớ đăng nhập</label>
                            </div>
                            <router-link to="/forgetpassword" style="color: white;">Quên mật khẩu?</router-link>
                        </div>
                        <button type="submit">ĐĂNG NHẬP</button>
                        <div>
                            <p class="text-white" style="text-align: center;">---- HOẶC -----</p>
                        </div>
                        <button>ĐĂNG NHẬP BẰNG GOOGLE</button>
                    </form>
                </div>
            </div>
            <div style="width: 50px;"></div>
            <div id="signup">
                <h1>Đăng ký</h1>
                <div>
                    <form @submit="submitRegister">
                        <div>
                            <div>
                                <label for="username">Họ và tên</label>
                                <input type="text" placeholder="HỌ VÀ TÊN" v-model="registerData.username">
                                <label for="password">Mật khẩu</label>
                                <input type="password" placeholder="MẬT KHẨU" v-model="registerData.password">
                                <label for="email">Email</label>
                                <input type="text" placeholder="EMAIL" v-model="registerData.email">
                            </div>
                            <div>
                                <label for="address">Địa chỉ</label>
                                <input type="text" placeholder="ĐỊA CHỈ" v-model="registerData.address">
                                <label for="comfirmPassword">Nhập lai mật khẩu</label>
                                <input type="password" placeholder="NHẬP LẠI MẬT KHẨU" v-model="registerData.comfirmPassword">
                                <label for="phonenumber">Số điện thoại</label>
                                <input type="text" placeholder="SỐ ĐIỆN THOẠI" v-model="registerData.phonenumber">
                            </div>
                        </div>
                        <button type="submit">ĐĂNG KÝ</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
</template>

<script>
import axios from 'axios';
import {reactive} from "vue";
import {useRouter} from "vue-router";

export default {
  name: "LoginView",
  setup() {
    const data = reactive({
      email: '',
      password: ''
    });
    const registerData = reactive({
      username: '',
      password: '',
      comfirmPassword: '',
      email: '',
      address: '',
      phonenumber: ''
    });
    const  router = useRouter();

    const submit = async () => {
      if (!data.email || !data.password) {
        alert('Vui lòng điền đầy đủ thông tin email và mật khẩu.');
        return;
      }
      try {
        const response = await axios.post('http://localhost:8080/login', data, {
          withCredentials: true
        });
        console.log(response.data);
        localStorage.setItem('usertoeic', JSON.stringify(response.data))

        const checkRole = JSON.parse(localStorage.getItem('usertoeic'));
        if (checkRole.role == 1) {
          router.push('/admin');
        } else {
          router.push('/homepage').then(() => window.location.reload());
        }
      } catch (error) {
        console.error('Lỗi đăng nhập:', error.response ? error.response.data : error);
        alert('Đã có lỗi trong quá trình đăng nhập.');
      }
    };

    const submitRegister = async () => {
      if (registerData.password !== registerData.comfirmPassword) {
        alert('Mật khẩu và xác nhận mật khẩu không trùng khớp.');
        return;
      }

      try {
        const response = await axios.post('http://localhost:8080/register', registerData, {
          withCredentials: true
        });
        console.log(response.data);
        alert('Đăng ký thành công!');
        router.push('/login');  // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
      } catch (error) {
        console.error('Lỗi đăng ký:', error.response ? error.response.data : error);
        alert('Đã có lỗi trong quá trình đăng ký.');
      }
    };

    return {
      data,
      registerData,
      submit,
      submitRegister
    }
  },
};
</script>

<style>
.div-title{
    height: 150px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color:  rgb(28, 82, 163);
}

.div-title>h1{
    color: white;
    font-size: 30px;
    font-weight: lighter;
}
#div-authencation>div:last-child{
    padding: 5% 15% 5% 15%;
    height: auto;
    display: flex;
    justify-content: center;
}
#div-authencation>div:last-child>div>h1{
    margin-bottom: 20px;
}

#login{
    width: 40%;
}
#div-authencation>div:last-child>#login>div{
    background-color:  rgb(28, 82, 163);
    padding: 25px;
}
#div-authencation>div:last-child>#login>div>form, #div-authencation>div:last-child>#login>div>form label, #div-authencation>div:last-child>#login>div>form>div>a{
    color: white;
    font-weight: bold;
}
#div-authencation>div:last-child>#login>div>form>label, #login>div>form>input{
    width: 100%;
    height: 40px;
    margin-bottom: 15px;
    margin-top: 15px;
}
#login>div>form>input{
    padding-left: 10px;
    background-color: white;
}

input[type="checkbox"] {
    width: 20px !important;
    height: 20px !important;
    cursor: pointer;
    accent-color: #007bff;
    margin-right: 10px;
}
#login form > div:nth-child(3)>a {
  text-decoration: underline;
  width: 45%;
}

#login>div div:last-child{
    margin-top: 20px;
    display: flex;
    justify-content: center;
}  
#login>div button{
    padding: 5px;
    background-color: white;
    font-weight: bold;
    color: #007bff;
    margin: 20px 0px 20px 0px;
    width: 100%;
    height: 40px;
} 

#signup{
    width: 45%;
}

#signup>div{
    background-color: rgba(255, 228, 196, 0.507);
    padding: 20px;
}
#signup>div>form label{
    font-weight: bold;
}
#signup>div>form>div:first-child{
    display: flex;
    justify-content: space-between;
}
#signup>div>form>div:first-child div{
    width: 49%;
}
#signup>div>form>div:first-child div input{
    width: 100%;
    height: 40px;
    padding-left: 10px;
    margin-top: 15px;
    margin-bottom: 15px;
}
#signup>div>form button{
    width: 100%;
    height: 40px;
    background-color:  rgb(28, 82, 163);
    color: white;
    font-weight: bold;
    margin-top: 10px;
    margin-bottom: 10px;
}
</style>