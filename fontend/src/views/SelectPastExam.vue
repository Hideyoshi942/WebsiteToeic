<template>
<main>
    <div class="cover_test">
        <div>
          <div class="exam-selection">
            <div class="options">
              <label>
                <input type="checkbox" />
                Chọn từng part
              </label>
              <div class="time-select">
                <label for="time">Chọn thời gian làm bài:</label>
                <select id="time">
                  <option>2 giờ</option>
                  <option>1.5 giờ</option>
                  <option>1 giờ</option>
                </select>
              </div>
            </div>

            <div class="section">
              <h3>LISTENING</h3>
              <table>
                <tr>
                  <td><input type="checkbox" checked /></td>
                  <td>PART 1</td>
                  <td>6 CÂU</td>
                </tr>
                <tr>
                  <td><input type="checkbox" checked /></td>
                  <td>PART 2</td>
                  <td>25 CÂU</td>
                </tr>
                <tr>
                  <td><input type="checkbox" checked /></td>
                  <td>PART 3</td>
                  <td>39 CÂU</td>
                </tr>
                <tr>
                  <td><input type="checkbox" checked /></td>
                  <td>PART 4</td>
                  <td>30 CÂU</td>
                </tr>
              </table>
            </div>

            <div class="section">
              <h3>READING</h3>
              <table>
                <tr>
                  <td><input type="checkbox" checked /></td>
                  <td>PART 5</td>
                  <td>30 CÂU</td>
                </tr>
                <tr>
                  <td><input type="checkbox" checked /></td>
                  <td>PART 6</td>
                  <td>16 CÂU</td>
                </tr>
                <tr>
                  <td><input type="checkbox" checked /></td>
                  <td>PART 7</td>
                  <td>54 CÂU</td>
                </tr>
              </table>
            </div>

            <div class="start-button">
              <button @click="gotoTestExam">BẮT ĐẦU</button>
            </div>
          </div>
        </div>
    </div>
</main>
</template>

<script>
export default {
  name: "SelectPastExam",
  methods: {
    gotoTestExam(){
      this.$router.push('/test-online/test');
    }
  }
}
</script>

<style scoped>
.cover_test {
  padding: 20px;
}

.cover_test > div:first-child{
  border-radius: 15px;
  background-color: rgb(251, 241, 228);
  display: flex;
  padding-top: 25px;
  padding-bottom: 25px;
}

.exam-selection {
  width: 60%;
  margin: 0 auto;
  font-family: Arial, sans-serif;
}

.options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.options label {
  font-weight: bold;
  font-size: 16px;
}

.time-select label {
  margin-right: 10px;
}

.time-select select {
  padding: 5px;
  font-size: 16px;
}

.section h3 {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  margin-top: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

table tr {
  border: 1px solid #f0b27a;
}

table td {
  padding: 10px;
  text-align: left;
  font-size: 16px;
}

table td:first-child {
  width: 5%;
  text-align: center;
}

table td:nth-child(2) {
  width: 70%;
  background-color: #f9e1cc;
}

table td:last-child {
  width: 25%;
  text-align: center;
}

.start-button {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.start-button button {
  background-color: #f5b041;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 18px;
  font-weight: bold;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.start-button button:hover {
  background-color: #e67e22;
}

</style>
