<template>
  <footer>
    <div id="footer-main">
      <div id="footer-base">
        <h2>TRUNG TÂM ANH NGỮ ROCKET</h2>
        <ul>
          <li><b>Cơ sở 1 - Kim Mã:</b> Số 22A ngách 25, ngõ 629 Kim Mã, phường Ngọc Khánh, Ba Đình, Hà Nội</li>
          <li><b>Cơ sở 1 - Kim Mã:</b> Số 22A ngách 25, ngõ 629 Kim Mã, phường Ngọc Khánh, Ba Đình, Hà Nội</li>
        </ul>
        <button @click="gotoBase">XEM BẢN ĐỒ</button>
      </div>
      <div id="footer-contact">
        <h2>CÔNG TY TNHH TƯ VẤN GIÁO DỤC VÀ XÚC TIỀN THƯƠNG MẠI ROCKET</h2>
        <ul>
          <li>
            <i class="fas fa-phone"></i>
            <span>0353.546.276</span>
          </li>
          <li>
            <i class="fas fa-envelope"></i>
            <span>21010666@st.phenikaa-uni.edu.vn</span>
          </li>
        </ul>
        <h2>THỜI GIAN LÀM VIỆC</h2>
        <ul>
          <li>
            Sáng: 08:00 - 12:00 (Thứ hai - thứ bảy)
          </li>
          <li>
            Chiều: 13:30 - 17:30 (Thứ hai - thứ bảy)
          </li>
          <li>
            Tối: 18:00 - 21:45 (Thứ hai - thứ bảy)
          </li>
        </ul>
      </div>
      <div id="footer-fanpage-cover">
        <h2>FANPAGE CHÍNH THỨC</h2>
        <a href="" target="_blank">
          <div class="footer-fanpage">
            <div class="footer-cover-image-fanpage">
              <img src="../../public/img/logo-01.png" width="100%">
            </div>
            <h2>Rocket to moon</h2>
            <p>50.000 người theo dõi</p>
          </div>
        </a>
        <a href="" target="_blank">
          <div class="footer-fanpage">
            <div class="footer-cover-image-fanpage">
              <img src="../../public/img/logo-01.png" width="100%">
            </div>
            <h2>Rocket to moon</h2>
            <p>50.000 người theo dõi</p>
          </div>
        </a>
      </div>
    </div>
    <div id="footer-bottom">
      Copyright 2024 © Rocket
    </div>
  </footer>
</template>

<style scoped>
footer{
  width: 100vw;
}

.title > h1{
  color: white;
  font-weight: bold;
  font-size: 25px;
}

#footer-form-img>img{
  height: auto;
  width: 100%;
  border: none;
  display: block;
}

#footer-form>form , #cover-require{
  background-color: #333333;
}

#footer-form>form input, select{
  width: 100%;
  height: 40px;
  margin-bottom: 10px;
  padding-left: 10px;
  background-color: #333333;
  color: rgba(255, 255, 255, 0.8);
  border-top: none;
  border-right: none;
  border-bottom: white 1px solid;
  border-left: white 1px solid;
  font-size: 16px;
}

#footer-form>form input::placeholder{
  color: rgba(255, 255, 255, 0.8);
}

#footer-form>form #cover-require{
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-between;
}

#cover-require input{
  width: 49% !important;
}

#footer-form>form button{
  width: 100%;
  background-color: rgb(255, 112, 60);
  color: white;
  font-weight: bold;
  height: 40px;
  font-size: 16px;
}

#footer-question h1{
  margin-bottom: 20px;
  font-weight: bold;
  background-color: transparent;
}

#footer-question ul, #footer-question ul li a, #footer-question ul li p{
  background-color: transparent !important;
}

#footer-question ul li p:last-child{
  font-weight: bold;
  margin-top: 25px;
}

#footer-question ul li p:last-child>a{
  font-weight: bold;
  color: rgb(223, 79, 27);
}

#footer-question ul li{
  list-style-type: none;
  background-color: transparent !important;
  margin-bottom: 10px;
}


#footer-question ul li h3 i{
  padding-right: 7px;
}

#footer-question ul li h3{
  height: auto;
  background-color: rgb(255, 255, 255);
  padding: 15px;
  margin-bottom: 20px;
  font-weight: bold;
  color: black;
  font-size: 18px;
}

/* footer 3 */
#footer-main{
  width: 100%;
  background-color: #333333;
  display: flex;
  box-sizing: border-box;
  padding-left: 10vw;
  padding-right: 10vw;
  padding-top: 30px;
  padding-bottom: 10px;
  justify-content: space-between;
}

#footer-main h2, #footer-main button, #footer-main li,
#footer-main span, #footer-main i, #footer-main p{
  color: white;
  margin-bottom: 20px;
}

#footer-main li{
  list-style-type: none;
}

#footer-main b{
  color: white;
  font-weight: bold;
}

#footer-main h2{
  font-weight: bold;
  margin-bottom: 20px;
}

#footer-main button{
  width: 100%;
  height: 40px;
  background-color: orangered;
  font-size: large;
  margin-top: 20px;
}

#footer-base, #footer-contact,#footer-fanpage-cover{
  width: 30%;
}

#footer-contact i{
  margin-right: 15px;
}

#footer-fanpage-cover a{
  display: block;
  margin-bottom: 20px;
  box-shadow: 0 8px 10px rgba(0, 0, 0, 0.5);
}

.footer-fanpage{
  height: 74px;
  width: 100%;
  position: relative;
}

.footer-fanpage>h2{
  position: absolute;
  top: 10px;
  left: 80px;
  font-weight: lighter !important;
}
.footer-fanpage>p{
  position: absolute;
  top: 40px;
  left: 80px;
  font-size: 10px
}

.footer-cover-image-fanpage{
  width: 50px;
  height: 50px;
  border: 2px solid white;
  display: flex;
  align-items: center;
  position: absolute;
  top: 10px;
  left: 15px;
}

/* footer 4 */
#footer-bottom{
  width: 100%;
  height: 40px;
  background-color: #7a7a7a;
  color: white;
  display: flex;
  align-items: center;
  padding-left: 10vw;
}
</style>

<script>
export default {
  name: 'FooterPage',
  methods: {
    gotoBase(){
      this.$router.push('/base').then(() => {
        window.scrollTo({ top: 0, behavior: 'smooth' });
      });
    }
  }
};
</script>