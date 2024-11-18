<template>
    <div class="news-card">
        <img :src="imageSrc" alt="news image" class="news-card-image" />
        <div class="news-card-content">
            <h3>{{ title }}</h3>
            <p>{{ content }}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: "NewsCard",
    props: {
        imageSrc: {
            type: String,
            required: true,
        },
        title: {
            type: String,
            required: true,
        },
        content: {
            type: String,
            required: true,
        },
    },
};
</script>

<style scoped>
.news-card {
    display: flex;
    align-items: flex-start;
    margin: 10px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    overflow: hidden;
    background-color: white;
    text-decoration: none;
}

.news-card-image {
    width: 150px;
    height: 120px;
    object-fit: cover;
}

.news-card-content {
    padding: 10px 15px;
}

.news-card-content h3 {
    font-size: 20px;
    font-weight: bold;
    color: black;
    margin-bottom: 5px;
}

.news-card-content p {
    font-size: 16px;
    color: #0056b3;
    margin: 0;
}
</style>
