<template>
<main>
    <div class="test-container">
        <div class="question-section">
            <h2>PART 2</h2>
            <p>Câu hỏi </p>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque non risus vitae ipsum sollicitudin tristique.</p>
        </div>
        <div class="answer-section">
            <div class="score-box">
                <span>0 / 200</span>
            </div>
            <div class="answer-options">
                <h3>16.</h3>
                <label><input type="radio" name="q16" /> A. Tem</label>
                <label><input type="radio" name="q16" /> B. Current</label>
                <label><input type="radio" name="q16" /> C. Humunity</label>
            </div>
        </div>
    </div>
    <div class="movable-timer" id="timerContainer" @mousedown="startDragging">
      <div class="timer-display">{{ timeDisplay }}</div>
      <button class="submit-button" @click="submitExam">Nộp bài</button>
    </div>
</main>
</template>

<style scoped>
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

main{
    padding: 20px;
}

.test-container {
    display: flex;
    height: 81vh;
    overflow: hidden;
    border: 1px solid #ddd;
}

.question-section {
    flex: 3; 
    padding: 20px;
    overflow-y: auto;
    background-color: #f9f9f9;
}

.answer-section {
    box-sizing: border-box;
    flex: 2;
    padding: 20px;
    overflow-y: auto;
    background-color: #ffffff;
    border-left: 1px solid #ddd;
    position: relative;
}

.score-box {
    position: absolute;
    top: 20px;
    right: 20px;
    background-color: #ff6f3d;
    padding: 10px 20px;
    border-radius: 5px;
}
.score-box > span{
    color: white;
    font-weight: bold;
}

.answer-options {
    padding-top: 50px;
}

h2, h3 {
    color: #2a5078;
    margin-bottom: 15px;
}

label {
    display: block;
    margin: 10px 0;
    font-size: 15px;
}

input[type="radio"] {
    margin-right: 10px;
}

/* Phần đếm giờ */
.movable-timer {
    position: absolute;
    top: 20px;
    right: 280px;
    width: 200px;
    padding: 20px;
    background-color: #f0f0f0;
    border: 1px solid #ccc;
    border-radius: 8px;
    text-align: center;
    cursor: move;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
}

.timer-display {
    font-size: 24px;
    margin-bottom: 10px;
    font-weight: bold;
    color: #333;
}

.submit-button {
    padding: 10px 10px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.submit-button:hover {
    background-color: #45a049;
}
</style>

<script>
export default {
  name: "TestExam",
  data() {
    return {
      timeLeft: 2 * 60 * 60, 
      intervalId: null,
      isDragging: false,
      startX: 0,
      startY: 0,
      initialX: 0,
      initialY: 0,
    };
  },
  computed: {
    timeDisplay() {
      const hours = String(Math.floor(this.timeLeft / 3600)).padStart(2, "0");
      const minutes = String(Math.floor((this.timeLeft % 3600) / 60)).padStart(2, "0");
      const seconds = String(this.timeLeft % 60).padStart(2, "0");
      return `${hours}:${minutes}:${seconds}`;
    },
  },
  mounted() {
    this.startTimer();
    document.addEventListener("mousemove", this.onMouseMove);
    document.addEventListener("mouseup", this.stopDragging);
  },
  methods: {
    startTimer() {
      this.intervalId = setInterval(() => {
        if (this.timeLeft > 0) {
          this.timeLeft--;
        } else {
          clearInterval(this.intervalId);
          alert("Thời gian đã hết!");
        }
      }, 1000);
    },
    submitExam() {
      clearInterval(this.intervalId);
      alert("Bài đã được nộp!");
    },
    startDragging(e) {
      this.isDragging = true;
      this.startX = e.clientX;
      this.startY = e.clientY;
      const container = document.getElementById("timerContainer");
      this.initialX = container.offsetLeft;
      this.initialY = container.offsetTop;
    },
    onMouseMove(e) {
      if (this.isDragging) {
        const container = document.getElementById("timerContainer");
        const dx = e.clientX - this.startX;
        const dy = e.clientY - this.startY;
        container.style.left = this.initialX + dx + "px";
        container.style.top = this.initialY + dy + "px";
      }
    },
    stopDragging() {
      this.isDragging = false;
    },
  },
  beforeUnmount() {
    clearInterval(this.intervalId);
    document.removeEventListener("mousemove", this.onMouseMove);
    document.removeEventListener("mouseup", this.stopDragging);
  }
};
</script>