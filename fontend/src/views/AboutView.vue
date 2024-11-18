<template>
  <main>
    <div id="main-about">
        <div class="div-title">
            <h1>VỀ CHÚNG TÔI</h1>
        </div>
        <div id="div-about-1">
            <div>
                <h1>ROCKET</h1>
                <p>Được thành lập vào ngày 04/05/2014, đến nay đã hoạt động được hơn 10 năm, Zenlish tự hào khi đào tạo được hơn 30.000 học viên tại 5 cơ sở Kim Mã, Hai Bà Trưng, Hà Đông, Tây Sơn và Nam Từ Liêm.</p>
                <p>“Zenlish là trung tâm duy nhất tại Hà Nội có lộ trình đào tạo TOEIC Học 1 Lần Là Đạt, tạo ra sự tiến bộ từng ngày cho những học viên đã từng sợ, học đi học lại nhiều lần nhưng không đạt, những người mong muốn học TOEIC ra trường hay đạt trên 800 TOEIC từ lần đầu học để sử dụng thành thạo Tiếng Anh, tạo ra lợi thế khác biệt trong môi trường cạnh tranh việc làm khốc liệt”.
                </p>
            </div>
            <div>
                <iframe src="https://www.youtube.com/embed/o3F3hH-WTus?start=20" 
                        title="YouTube video player" frameborder="0" 
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                        allowfullscreen>
                </iframe>
            </div>
        </div>
        <div>
            <img src="../../public/img/about.jpg" alt="" width="50%">
            <div>
                <h1>TẦM NHÌN</h1>
                <p>Trở thành trung tâm được khách hàng LỰA CHỌN ĐẦU TIÊN khi học về TOEIC 4 KỸ NĂNG tại Việt Nam.</p>
                <p>Lọt TOP 1 trung tâm tiếng Anh có số lượng học sinh nhiều nhất tại Hà Nội vào năm 2024</p>
                <p>TOP 1 trung tâm đào tạo chất lượng, có nhiều học viên sở hữu chứng chỉ TOEIC quốc tế nhất.</p>
                <h1>SỨ MỆNH</h1>
                <p>Nâng cao chất lượng lao động thế hệ trẻ thông qua năng lực sử dụng tiếng Anh.</p>
                <h1>GIÁ TRỊ CỐT LÕI</h1>
                <p>Zenlish đang đi theo 5 giá trị cốt lõi:</p>
                <ul>
                    <li>Liên tục học hỏi</li>
                    <li>Tận tâm</li>
                    <li>Bền bỉ</li>
                    <li>Hợp lực</li>
                    <li>Hướng tới sự xuất sắc</li>
                </ul>
            </div>
        </div>
        <div>
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
    </div>
</main>
</template>


<style>
#div-about-1{
    padding: 50px 20%;
    display: flex;
    box-sizing: border-box;
}

#div-about-1 div:nth-child(1){
    width: 50%;
    padding-right: 50px;
}
#div-about-1 div:nth-child(1) h1{
    margin-bottom: 10px;
    color: blue;
    font-weight: bold;
}

#div-about-1 div:nth-child(1) p{
    margin-bottom: 30px;
    line-height: 1.5;
}

#div-about-1 div:nth-child(2){
    width: 50%;
    height: cover;
}

#div-about-1 div:nth-child(2) iframe{
    width: 100%;
    height: 400px;
}

#main-about div:nth-child(3)>div{
    width: 50%;
    background-color: blue;
    padding: 60px;
}
#main-about div:nth-child(3){
    display: flex;
}

#main-about div:nth-child(3) div h1, 
#main-about div:nth-child(3) div p,
#main-about div:nth-child(3) div ul li{
    color: white;
}
#main-about div:nth-child(3) div h1{
    margin-bottom: 15px;
    font-weight: bold;
}
#main-about div:nth-child(3) div p{
    line-height: 1.5;
    margin-bottom: 10px;
}
#main-about div:nth-child(3) div ul li{
    margin-left: 26px;
    list-style: number;
    margin-bottom: 10px;
    font-weight: bold;
}

#main-about div:nth-child(4){
    padding: 50px 20%;
}
#main-about div:nth-child(4) div:nth-child(1) {
    margin-bottom: 80px;
}
#main-about div:nth-child(4) div:nth-child(1) ul{
    display: flex;
    justify-content: space-between;
}
#main-about div:nth-child(4) div:nth-child(1) ul li{
    list-style: none;
}
#main-about div:nth-child(4) div:nth-child(2){
    text-align: center
}
#main-about div:nth-child(4) div:nth-child(2) h1{
    font-weight: bold;
    margin-bottom: 20px;
}
#main-about div:nth-child(4) div:nth-child(2) p{
    line-height: 1.5;
    margin-bottom: 50px;
}
#main-about div:nth-child(4) div:nth-child(2) img{
    width: 100%;
    height: auto;
    object-fit: contain;
}
#main-about div:nth-child(4) div:nth-child(2) div{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
#main-about div:nth-child(4) div:nth-child(2) div div{
    width: 30%;
    height: 180px;
    padding: 0px;
    margin: 10px;
}
</style>
