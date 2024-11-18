<template>
<main>
    <div class="exam-detail">
        <div class="tags">
        <span class="tag">#IELTS Academic</span>
        <span class="tag">#Listening</span>
        </div>
        <h1 class="exam-title">IELTS Simulation Listening test 1</h1>
        <button class="info-button" @click="handleStartTest">Làm bài</button>
        <div class="exam-info">
            <p>
                <i class="fas fa-clock"></i> Thời gian làm bài: 40 phút | 4 phần thi | 40 câu hỏi | 2161 bình luận
            </p>
            <p>
                <i class="fas fa-user"></i> 710976 người đã luyện tập đề thi này
            </p>
        </div>
        <p class="note">
        Chú ý: Đăng nhập để bình luận.
        </p>
    </div>
    <!-- Bình luận -->
    <div class="comments-section">
      <h2>Bình luận</h2>
      <div class="comment-input">
        <input type="text" placeholder="Chia sẻ cảm nghĩ của bạn ..." />
        <button>Gửi</button>
      </div>

      <div class="comments-list">
        <!-- Mỗi comment -->
        <div class="comment">
          <div class="avatar">L</div>
          <div class="comment-content">
            <span class="comment-author">ledatledat20</span> - <span class="comment-date">Tháng 11. 05, 2024</span>
            <p>muốn luyện speaking thì sao đây ạ</p>
            <a href="#" class="reply-link">Trả lời</a>
          </div>
        </div>

        <div class="comment">
          <div class="avatar">N</div>
          <div class="comment-content">
            <span class="comment-author">nguyenuyenen</span> - <span class="comment-date">Tháng 11. 05, 2024</span>
            <p>Chào mọi người, mình cần tìm partner speaking...</p>
            <a href="#" class="reply-link">Trả lời</a>
          </div>
        </div>

        <button class="load-more-button">Xem thêm</button>
      </div>
    </div>
</main>
</template>

<script>
export default{
  name: "ExamDetail",
  methods: {
    handleStartTest() {
      this.$router.push('/test-online');
    }
  }
}
</script>

<style scoped>
main{
    padding-top: 40px;
    padding-bottom: 40px;
}

.exam-detail {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  max-width: 1200px;
  margin: auto;
  background-color: #fff;
}

.tags {
  display: flex;
  gap: 8px;
  margin-bottom: 10px;
}

.tag {
  background-color: #f0f0f0;
  color: #333;
  padding: 5px 10px;
  border-radius: 4px;
  font-size: 12px;
}

.exam-title {
  font-size: 24px;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
}

.info-button {
  background-color: #e7f3ff;
  color: #333;
  padding: 8px 16px;
  border: none;
  border-radius: 20px;
  font-size: 14px;
  margin-bottom: 20px;
  cursor: pointer;
}

.exam-info {
  font-size: 14px;
  color: #666;
  margin-bottom: 20px;
}

.exam-info i {
  margin-right: 8px;
  color: #666;
}

.note {
  color: #d9534f;
  font-size: 14px;
}

/* Css bình luận */
.comments-section {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  max-width: 1200px;
  margin: auto;
  background-color: #fff;
  margin-top: 20px;
}

.comments-section h2 {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
}

.comment-input {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

.comment-input input {
  flex: 1;
  padding: 8px 12px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

.comment-input button {
  background-color: #007bff;
  color: #fff;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.comments-list {
  max-height: 400px; 
  overflow-y: auto; 
  margin-bottom: 20px;
  position: relative;
}

.comment {
  display: flex;
  align-items: start;
  gap: 10px;
  margin-bottom: 20px;
}

.avatar {
  width: 40px;
  height: 40px;
  background-color: #ddd;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  color: #666;
  font-size: 16px;
}

.comment-content {
  flex: 1;
}

.comment-author {
  font-weight: bold;
  color: #333;
}

.comment-date {
  color: #999;
  font-size: 12px;
}

.reply-link {
  color: #007bff;
  font-size: 12px;
  cursor: pointer;
  display: inline-block;
  margin-top: 5px;
}

.load-more-button {
  background-color: #007bff;
  color: #fff;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  text-align: center;
  display: block;
  margin: 0 auto;
  width: 100px;
}
</style>