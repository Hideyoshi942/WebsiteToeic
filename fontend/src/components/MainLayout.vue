<template>
  <AppHeader /> 
  <router-view @toggleForm="toggleFormVisibility"/> 
  <AppFooter /> 
  <Sidebar @toggleForm="toggleFormVisibility"/>
  <FormAdvise :isHidden="isFormHidden" @closeForm="toggleFormVisibility"/>
</template>

<script>
import AppHeader from './Header.vue';
import AppFooter from './Footer.vue';
import Sidebar from './Sidebar.vue';
import FormAdvise from './FormAdvise.vue';

export default {
  name: 'MainLayout',
  components: {
      AppHeader,
      AppFooter,
      Sidebar,
      FormAdvise,
  },
  data() {
    return {
      isFormHidden: true
    };
  },
  methods: {
    toggleFormVisibility() {
      this.isFormHidden = !this.isFormHidden;
    },
    hideForm() {
      this.isFormHidden = true; 
    }
  }
};
</script>

  