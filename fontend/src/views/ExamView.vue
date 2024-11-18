<template>
<main>
    <div class="div-title">
        <h1>Thư viện đề thi</h1>
    </div>
    <div class="main_exams">
        <div class="catergory_exams">
            <ul>
                <li>Tấ cả</li>
                <li>IELTS Academic</li>
                <li>IELTS General</li>
                <li>TOEIC</li>
                <li>HSK 1</li>
                <li>HSK 2</li>
                <li>HSK 3</li>
                <li>HSK 4</li>
                <li>HSK 5</li>
                <li>HSK 6</li>
                <li>TOPIK I</li>
                <li>TOPIK II</li>
                <li>Toán THPTQG</li>
                <li>Tiếng Anh THPTQG</li>
                <li>Sinh học THPTQG</li>
            </ul>
        </div>
        <div id="exams_search">
            <form action="" id="search_form">
                <input type="text" placeholder="Nhập từ khóa bạn muốn tìm kiếm: tên sách, dạng câu hỏi ...">
                <button type="submit">Tìm kiếm</button>
            </form>
        </div>
        <div class="exams_cover_card">
            <ExamCard
                v-for="(exam, index) in paginatedExams"
                :key="index"
                :title="exam.title"
                :subtitle="exam.subtitle"
                :duration="exam.duration"
                :participants="exam.participants"
                :comments="exam.comments"
                :sections="exam.sections"
                :questions="exam.questions"
                :tags="exam.tags"
                @view-details="handleViewDetails(index)"
            />
        </div>
        <div class="pagination">
            <button @click="previousPage" :disabled="currentPage === 1">Previous</button>
            <button 
            v-for="page in totalPages" 
            :key="page" 
            @click="goToPage(page)"
            :class="{ active: page === currentPage }"
            >
            {{ page }}
            </button>
            <button @click="nextPage" :disabled="currentPage === totalPages">Next</button>
        </div>
    </div>
</main>
</template>

<script>
import ExamCard from '@/components/ExamCard.vue';

export default {
  name: 'ExamView',
  components: {
    ExamCard,
  },
  data() {
    return {
      exams: [
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 1',
          duration: '40 phút',
          participants: 697092,
          comments: 2114,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
        {
          title: 'IELTS Simulation',
          subtitle: 'Listening test 2',
          duration: '40 phút',
          participants: 702123,
          comments: 2200,
          sections: '4 phần thi',
          questions: 40,
          tags: ['#IELTS Academic', '#Listening'],
        },
      ],
      currentPage: 1,
      pageSize: 12,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.exams.length / this.pageSize);
    },
    paginatedExams() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.exams.slice(start, end);
    }
  },
  methods: {
    handleViewDetails(index) {
      console.log(`Chi tiết cho bài kiểm tra: ${this.exams[index].title}`);
      this.$router.push({ name: 'ExamDetail'});
    },
    goToPage(page) {
        this.currentPage = page;
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    }
  }
};
</script>

<style scoped>
.main_exams{
    padding-left: 12%;
    padding-right: 12%;
    padding-top: 40px;
}

.catergory_exams ul li{
    list-style: none;
    padding: 10px;
    padding-left: 20px;
    padding-right: 20px;
    border-radius: 20px;
    background-color: #4e4e4e;
    color: white;
    font-weight: bolder;
    margin-left: 10px;
    margin-bottom: 10px;
}
.catergory_exams ul li:hover{
    background-color: orangered;
}
.catergory_exams ul{
    display: flex;
    flex-wrap: wrap;
}
#exams_search{
    margin-top: 20px;
    margin-bottom: 50px;
}
#search_form input{
    width: 88%;
    height: 40px;
    padding-left: 20px;
    padding-right: 20px;
    border-radius: 10px;
    margin-right: 1%;
}
#search_form button{
    width: 10%;
    height: 40px;
    color: white;
    background-color: #e74f0e;
    font-weight: bolder;
    font-size: 15px;
}

.exams_cover_card {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.pagination {
    margin-top: 20px;
    display: flex;
    gap: 10px;
    justify-content: center;
    margin-bottom: 50px;
}

.pagination button {
    padding: 5px 10px;
    border: none;
    background-color: #373737;
    color: white;
    cursor: pointer;
    border-radius: 5px;
}

.pagination button[disabled] {
    background-color: #ccc;
    cursor: not-allowed;
}

.pagination button.active {
    background-color: #ee3e04;
    font-weight: bold;
}
</style>