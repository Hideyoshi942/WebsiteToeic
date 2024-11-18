<template>
<main>
    <div class="div-title">
        <h1>TIN TỨC</h1>
    </div>
    <div class="mainNew">
        <div class="new-main">
            <NewsCard
                v-for="(news, index) in paginatedNewsItems"
                :key="index"
                :imageSrc="news.imageSrc"
                :title="news.title"
                :content="news.content"
            />
            <!-- Pagination Controls -->
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

export default {
    name: "NewsView",
    components: {
        NewsCard,
        DocumentCard
    },
    data() {
        return {
            newsItems: [
                {
                    imageSrc: "..//..//public//img//zenlish-1.jpg",
                    title: "Lộ trình đổi B1 ra trường đúng hạn chỉ trong 5 tháng",
                    content: "1. Trình độ tiếng Anh B1 là gì? Trình độ tiếng Anh B1 là cấp độ thứ ba trong sáu bậc của khung năng lực ngoại ngữ...",
                },
                {
                    imageSrc: "..//..//public//img//zenlish-2.jpg",
                    title: "Cách để đạt B1 trong thời gian ngắn",
                    content: "Chương trình giúp bạn đạt trình độ B1 với các phương pháp học hiệu quả và hỗ trợ tối đa từ đội ngũ giảng viên...",
                },
                {
                    imageSrc: "..//..//public//img//zenlish-3.jpg",
                    title: "Cách để đạt B1 trong thời gian ngắn",
                    content: "Chương trình giúp bạn đạt trình độ B1 với các phương pháp học hiệu quả và hỗ trợ tối đa từ đội ngũ giảng viên...",
                },
                {
                    imageSrc: "..//..//public//img//zenlish-3.jpg",
                    title: "Cách để đạt B1 trong thời gian ngắn",
                    content: "Chương trình giúp bạn đạt trình độ B1 với các phương pháp học hiệu quả và hỗ trợ tối đa từ đội ngũ giảng viên...",
                },
                {
                    imageSrc: "..//..//public//img//zenlish-3.jpg",
                    title: "Cách để đạt B1 trong thời gian ngắn",
                    content: "Chương trình giúp bạn đạt trình độ B1 với các phương pháp học hiệu quả và hỗ trợ tối đa từ đội ngũ giảng viên...",
                },
                {
                    imageSrc: "..//..//public//img//zenlish-3.jpg",
                    title: "Cách để đạt B1 trong thời gian ngắn",
                    content: "Chương trình giúp bạn đạt trình độ B1 với các phương pháp học hiệu quả và hỗ trợ tối đa từ đội ngũ giảng viên...",
                },
            ],
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
        }
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
        }
    }
};
</script>

<style scoped>
.mainNew{
    margin-top: 30px;
    padding-left: 15%;
    padding-right: 15%;
    display: flex;
    flex-wrap: nowrap;
}
.new-main{
    width: 60%;
}
.cover-document > div:first-child{
    border-top: 2px blue solid ;
    border-left: 2px blue solid;
    display: flex;
    justify-content: center;
    margin-bottom: 10px;
}
.cover-document > div > h2{
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