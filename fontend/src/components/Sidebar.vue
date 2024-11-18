<template>
    <div id="side-bar">
        <div id="side-bar-zalo" class="side-bar-cover">
            <i class="fas fa-comments"></i>
        </div>
        <div id="side-bar-mess" class="side-bar-cover">
            <i class="fab fa-facebook-messenger"></i>
        </div>
        <div id="side-bar-phone" class="side-bar-cover">
            <i class="fas fa-phone"></i>
            <div id="phone">
                0353-546-276
            </div>
        </div>
        <div id="side-bar-instal" class="side-bar-cover">
            <i class="fab fa-instagram"></i>
        </div>
        <div id="side-bar-form" class="side-bar-cover" @click="toggleForm">
            <i class="fas fa-file-alt"></i>
        </div>
    </div>    
</template>

<style scoped>
#side-bar{
    position: fixed;
    bottom: 0px;
    right: 10px;
}

#side-bar-phone{
    position: relative;
}

#phone{
    position: fixed;
    top: 0px;
    right: 50px;
    width: 150px;
    background-color: #444343;
    height: 50px;
    color: white;
    border-radius: 20px;
    display: none;
}

#side-bar-phone:hover > #phone{
    display: flex;
    justify-content: center;
    align-items: center;
}


.side-bar-cover{
    animation: shake 2s infinite; 
    height: 40px;
    width: 40px;
    background-color: #444343;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 15px;
    border-radius: 50%;
}

.side-bar-cover>i{
    font-size: 19px;
    color: white;
}

@keyframes shake {
  0%, 20% { transform: translate(1px, 0px) rotate(0deg); }
  10% { transform: translate(3px, -2px) rotate(10deg); }
  15% { transform: translate(3px, -2px) rotate(-20deg); }
  20% { transform: translate(-3px, 2px) rotate(-0deg); }
  15% { transform: translate(3px, -2px) rotate(20deg); }
  30% { transform: translate(1px, -1px) rotate(10deg); }
  35% { transform: translate(1px, -1px) rotate(0deg); }
  100% { transform: translate(0, 0) rotate(0deg); }
}

</style>

<script>
export default {
  name: 'SideBar',
  methods: {
    toggleForm() {
      this.$emit('toggleForm'); 
    }
  }
};
</script>