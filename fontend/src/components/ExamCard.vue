<template>
    <div class="card">
      <h3>{{ title }}</h3>
      <h3>{{ subtitle }}</h3>
      <div class="info">
        <span class="icon"><i class="fas fa-clock"></i> {{ duration }}</span>
        <span>|</span>
        <span class="icon"><i class="fas fa-users"></i> {{ participants }}</span>
        <span>|</span>
        <span class="icon"><i class="fas fa-comments"></i> {{ comments }}</span>
      </div>
      <p>{{ sections }} | {{ questions }} câu hỏi</p>
      <div class="tags">
        <span class="tag" v-for="tag in tags" :key="tag">{{ tag }}</span>
      </div>
      <button class="details-btn" @click="viewDetails">Chi tiết</button>
    </div>
  </template>
  
<script>
export default {
    name: 'ExamCard',
    props: {
        title: String,
        subtitle: String,
        duration: String,
        participants: Number,
        comments: Number,
        sections: String,
        questions: Number,
        tags: Array,
    },
    methods: {
        viewDetails() {
            this.$emit('view-details');
        }
    }
};
</script>

<style scoped>
/* Card container */
.exams_cover_card{
    display: flex;
}
.card {
  width: 180px;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  text-align: left;
  font-family: Arial, sans-serif;
  margin-bottom: 25px;
  margin: 20px;
}

.card h3 {
  font-size: 16px;
  font-weight: bold;
  margin: 0 0 10px;
  color: #333;
}

.info {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  font-size: 14px;
  color: #6c757d;
  margin-bottom: 10px;
}

.icon {
  display: flex;
  align-items: center;
  margin-right: 10px;
}

.icon i {
  margin-right: 5px;
}

.card p {
  font-size: 14px;
  color: #555;
  margin: 0 0 10px;
}

.tags {
  margin-bottom: 10px;
}

.tag {
  display: inline-block;
  background-color: #e3f2fd;
  color: #1a73e8;
  padding: 3px 8px;
  border-radius: 12px;
  font-size: 12px;
  margin-right: 5px;
}

.details-btn {
  display: block;
  width: 100%;
  padding: 8px;
  text-align: center;
  color: #1a73e8;
  background-color: transparent;
  border: 1px solid #1a73e8;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.details-btn:hover {
  background-color: #1a73e8;
  color: #fff;
}
</style>
  