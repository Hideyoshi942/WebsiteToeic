<template>
  <main>
    <div class="div-title">
      <h1>TIN TỨC</h1>
    </div>
    <div class="mainNew">
      <div class="new-main">
        <!-- Hiển thị danh sách Tin tức -->
        <NewsCard
            v-for="(news) in paginatedNewsItems"
            :key="news.postid"
            :postid="news.postid"
            :imageSrc="news.postimage"
            :title="news.title"
            :content="news.content"
        />
        <!-- Điều hướng phân trang -->
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
      <div class="document">
        <div class="cover-document">
          <div>
            <h2>TÀI LIỆU HỌC TẬP</h2>
          </div>
          <div>
            <DocumentCard
                v-for="(document, index) in documents"
                :key="index"
                :imageSrc="document.imageSrc"
                :title="document.title"
            />
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import DocumentCard from "@/components/DocumentCard.vue";
import NewsCard from "@/components/NewsCard.vue";
import axios from "axios";

export default {
  name: "NewsView",
  components: {
    NewsCard,
    DocumentCard,
  },
  data() {
    return {
      newsItems: [], // Dữ liệu tin tức từ API
      documents: [
        {
          imageSrc: "..//..//public//img//zenlish-3.jpg",
          title: "Bộ tài liệu tiếng Anh đầu vào từ mất gốc dành riêng cho sinh viên K65 HUC",
        },
        {
          imageSrc: "..//..//public//img//zenlish-3.jpg",
          title: "Bộ tài liệu tiếng Anh đầu vào từ mất gốc dành riêng cho sinh viên K65 HUC",
        },
      ],
      currentPage: 1,
      itemsPerPage: 5,
      errorMessage: "",
    };
  },
  computed: {
    paginatedNewsItems() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.newsItems.slice(start, end);
    },
    totalPages() {
      return Math.ceil(this.newsItems.length / this.itemsPerPage);
    },
  },
  methods: {
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
    },
    async fetchNews() {
      try {
        const { data } = await axios.get("http://localhost:8080/api/admin/news/list");
        // Xử lý dữ liệu từ API
        this.newsItems = data.map((news) => ({
          postid: news.postid,
          title: news.title,
          content: news.content,
          postimage: `http://localhost:8080${news.image}`, // Đường dẫn ảnh đầy đủ
        }));
      } catch (error) {
        this.errorMessage = "Lỗi khi tải dữ liệu từ API";
        console.error("Error fetching news:", error);
      }
    },
  },
  mounted() {
    this.fetchNews();
  },
};
</script>

<style scoped>
.mainNew {
  margin-top: 30px;
  padding-left: 15%;
  padding-right: 15%;
  display: flex;
  flex-wrap: nowrap;
}
.new-main {
  width: 60%;
}
.cover-document > div:first-child {
  border-top: 2px blue solid;
  border-left: 2px blue solid;
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
}
.cover-document > div > h2 {
  color: rgba(2, 2, 224, 0.951);
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
