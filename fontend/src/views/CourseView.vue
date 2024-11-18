<template>
<main>
    <div class="main-div main-div-3">
        <div>
            <div>
                <h1>Lộ trình luyện thi TOEIC<br>
                    theo đúng trình độ nền của cá nhân bạn</h1>
            </div>
            <div> 
                <h3>Hãy lựa chọn trình độ hiện tại của bạn</h3>
                <div>
                    <ul>
                        <li>
                            <label for="">TOEIC: 1 - 295
                                Mất gốc LR</label>
                        </li>
                        <li>
                            <label for="">TOEIC: 300 - 595
                                Cơ bản LR</label>
                        </li>
                        <li>
                            <label for="">TOEIC: 600 - 650
                                Có nền tảng LR</label>
                        </li>
                    </ul>
                </div>
                <h3>Chọn tiếp mục tiêu bạn muốn chinh phục</h3>
                <div>
                    <ul>
                        <li>
                            <label for="">TOEIC: 300
                                Cơ bản</label>
                        </li>
                        <li>
                            <label for="">TOEIC: 600
                                Khá</label>
                        </li>
                        <li>
                            <label for="">TOEIC: 800+
                                Xuất sắc</label>   
                        </li>
                    </ul>
                </div>
            </div>
            <div>
                <button @click="gotoTestOnline">Làm bài kiểm tra đầu vào</button>
            </div>
        </div>
    </div>
    <div class="main-div main-div-2">
        <div>
            <div class="main-div-2-header">
                <h1>TOEIC Roadmap At Rocket</h1>
                <p>Hãy xem lộ trình học TOEIC bên dưới, từ những lớp MẤT GỐC đến các lớp TOEIC 4 kỹ năng chuyên nghiệp.</p>
            </div>
            <div class="main-div-2-header">
                <h2>KIỂM TRA TRÌNH ĐỘ NỀN MIỄN PHÍ</h2>
            </div>
            <div class="main-div-2-header">
                <h2>TỪ MẤT GỐC - 450+</h2>
            </div>
            <div class="main-div-2-header">
                <h2>TỪ 450 - 700+ TOEIC</h2>
            </div>
            <div class="main-div-2-header">
                <h2>TOEIC 4 KỸ NĂNG</h2>
            </div>
            <div class="main-div-2-header">
                <h2>TÀI LIỆU HỌC</h2>
            </div>
        </div>
    </div>
    <div id="main-div-course">
        <div>
            <ul>
                <li>
                    <h1>+30000</h1>
                    <p>Người đã được tạo lộ trình học</p>
                </li>
                <li>
                    <h1>+5000</h1>
                    <p>Học viên thi đỗ TOEIC quốc tế</p>
                </li>
                <li>
                    <h1>TOP 1</h1>
                    <p>Đào tạo Toeic tại Hà Nội</p>
                </li>
                <li>
                    <h1>TOP 1</h1>
                    <p>Đào tạo TOEIC 4 Kỹ năng tại Hà Nội</p>
                </li>
            </ul>
        </div>
        <div>
            <h1>CÁC KHÓA HỌC</h1>
            <p>Với sự đa dạng các khóa học offline & online, mỗi học viên sẽ có cơ hội trải nghiệm phương pháp và lộ trình học tiếng Anh độc đáo, chỉ duy nhất tại Zenlish. Học viên sẽ dễ dàng tiếp nhận kiến thức, đạt mục tiêu TOEIC chỉ sau 1 lần học.</p>
            <div>
                <div class="cover-image">
                    <img src="../../public/img/zenlish-1.jpg" alt="">
                </div>
                <div class="cover-image">
                    <img src="../../public/img/zenlish-2.jpg" alt="">
                </div>
                <div class="cover-image">
                    <img src="../../public/img/zenlish-3.jpg" alt="">
                </div>
                <div class="cover-image">
                    <img src="../../public/img/zenlish-4.jpg" alt="">
                </div>
                <div class="cover-image">
                    <img src="../../public/img/zenlish-5.jpg" alt="">
                </div>
            </div>
        </div>
    </div>
</main>
</template>

<script>
export default{
    name: 'CourseView',
    methods: {
        gotoTestOnline() {
            this.$router.push('/test-online');
        }
    }
}
</script>

<style scoped>
#main-div-course{
    padding: 50px 20%;
}
#main-div-course div:nth-child(1) {
    margin-bottom: 80px;
}
#main-div-course div:nth-child(1) ul{
    display: flex;
    justify-content: space-between;
}
#main-div-course div:nth-child(1) ul li{
    list-style: none;
}
#main-div-course div:nth-child(2){
    text-align: center
}
#main-div-course div:nth-child(2) h1{
    font-weight: bold;
    margin-bottom: 20px;
}
#main-div-course div:nth-child(2) p{
    line-height: 1.5;
    margin-bottom: 50px;
}
#main-div-course div:nth-child(2) img{
    width: 100%;
    height: auto;
    object-fit: contain;
}
#main-div-course div:nth-child(2) div{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
#main-div-course div:nth-child(2) div div{
    width: 30%;
    height: 180px;
    padding: 0px;
    margin: 10px;
}
</style>