<template>
    <div class="div-title">
        <h1>HỆ THỐNG CƠ SỞ</h1>
    </div>
    <main class="location-system">
        <div class="content-container">
            <div class="location-list">
            <div class="location-item" v-for="location in locations" :key="location.title">
                <h3>{{ location.title }}</h3>
                <p>{{ location.address }}</p>
                <p>📞 037 760 2075</p>
            </div>
            </div>
            <div class="map-container">
            <div id="map"></div>
            </div>
        </div>
        </main>
</template>
  
<script>
export default {
  name: "BaseView",
  data() {
    return {
      locations: [
        { title: "Cơ sở 1 – Kim Mã", address: "Số 22A/25, ngõ 629 Kim Mã, phường Ngọc Khánh, Ba Đình, Hà Nội", lat: 21.0307, lng: 105.8114 },
        { title: "Cơ sở 2 – Hai Bà Trưng", address: "Số 5, ngõ 128 phố Vọng, Hai Bà Trưng, Hà Nội", lat: 21.0057, lng: 105.8490 },
      ]
    };
  },
  mounted() {
    this.loadScript().then(this.initMap).catch((error) => {
      console.error("Google Maps API failed to load:", error);
    });
  },
  methods: {
    loadScript() {
      return new Promise((resolve, reject) => {
        if (window.google) {
          resolve();
        } else {
          const script = document.createElement("script");
          script.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyDaOulQACiJzBfqumbsqg_-vKha8fCnL-s`;
          script.async = true;
          script.onload = resolve;
          script.onerror = reject;
          document.head.appendChild(script);
        }
      });
    },
    initMap() {
      const map = new window.google.maps.Map(document.getElementById("map"), {
        center: { lat: 21.0285, lng: 105.8542 },
        zoom: 13,
      });

      this.locations.forEach((location) => {
        new window.google.maps.Marker({
          position: { lat: location.lat, lng: location.lng },
          map: map,
          title: location.title,
        });
      });
    },
  },
};
</script>
  
<style scoped>
.location-system {
    margin-top: 20px;
    font-family: Arial, sans-serif;
    color: #333;
    padding-left: 12%;
    padding-right: 12%;
    padding-bottom: 30px;
}

.title {
    color: #2C3E50;
    text-align: center;
    margin-bottom: 20px;
}

.search-container {
    display: flex;
    justify-content: center;
    gap: 10px;
    margin-bottom: 20px;
}

.search-bar {
    padding: 8px;
    width: 200px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.filter-btn, .search-btn {
    padding: 8px 12px;
    background-color: #0056b3;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    cursor: pointer;
}

.filter-btn:hover, .search-btn:hover {
    background-color: #003d80;
}

.content-container {
    display: flex;
    gap: 20px;
}

.location-list {
    flex: 1;
    max-width: 300px;
    border-right: 1px solid #ddd;
    padding-right: 10px;
}

.location-list h2 {
    font-size: 1.2rem;
    margin-bottom: 10px;
}

.location-item {
    padding: 10px 0;
    border-bottom: 1px solid #ddd;
}

.location-item h3 {
    font-size: 1rem;
    color: #0056b3;
    margin-bottom: 5px;
}

.location-item p {
    font-size: 0.9rem;
    color: #555;
    margin: 3px 0;
}

.map-container {
    flex: 2;
    padding-left: 10px;
}

#map {
    width: 100%;
    height: 500px;
    border-radius: 5px;
}
</style>
  