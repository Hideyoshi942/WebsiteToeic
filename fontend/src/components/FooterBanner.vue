<template>
    <div class="bottom-banner">
        <div class="scrolling-text">
            Khi gặp vấn đề về Test Online (file không chạy, không chuyển sang câu tiếp theo, v.v.), 
            vui lòng kiểm tra kết nối hoặc liên hệ với bộ phận hỗ trợ.
        </div>
    </div>
</template>

<style>
.bottom-banner {
    position: fixed;
    bottom: 0;
    width: 100%;
    background-color: #ff3b3b; 
    text-align: center;
    overflow: hidden; 
    height: 30px;
    line-height: 30px;
    z-index: 1000;
}

.scrolling-text {
    display: inline-block;
    white-space: nowrap; 
    padding-left: 100%; 
    animation: scroll-text 20s linear infinite;
    color: white;
}

@keyframes scroll-text {
    0% {
        transform: translateX(0%);
    }
    100% {
        transform: translateX(-100%);
    }
}

</style>

<script>
export default{
    name: "FooterBanner"
}
</script>