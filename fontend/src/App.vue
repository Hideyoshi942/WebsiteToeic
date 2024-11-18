<template>
  <router-view /> 
</template>

<script>
export default {
  name: 'App'
};
</script>

<style>
* {
    margin: 0;
    padding: 0;
    background-color: transparent;
    color: rgb(0, 0, 0);
    box-sizing: border-box;
    font-family: 'Gilroy', sans-serif;
    font-weight: 500;
    line-height: 1.5;
}

.background-blue{
    background-color: rgb(20, 78, 165);
}

.hidden{
    display: none !important;
}

.show{
    display: block;
}

.text-white{
    color: white;
}

.cover-image{
    overflow: hidden;
}

.flex{
    display: flex;
}

button{
    box-shadow: 0 8px 10px rgba(0, 0, 0, 0.5);
}

.div-full-weight{
    width: 100%;
}
</style>