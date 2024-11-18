<template>
<div class="document-card">
    <img :src="imageSrc" alt="Document image" class="document-image" />
    <div class="document-content">
        <h3 class="document-title">{{ title }}</h3>
    </div>
</div>
</template>

<script>
export default {
    name: "DocumentCard",
    props: {
        imageSrc: {
            type: String,
            required: true,
        },
        title: {
            type: String,
            required: true,
        },
    },
};
</script>

<style scoped>
.document-card {
    display: flex;
    align-items: flex-start;
    gap: 10px;
    max-width: 300px;
}

.document-image {
    width: 80px;
    height: 80px;
    object-fit: cover;
}

.document-content {
    flex: 1;    
}

.document-title {
    margin: 0;
    color: black;
}
</style>
