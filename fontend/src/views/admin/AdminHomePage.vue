<script setup>
  import AdminHeader from "@/components/admin/AdminHeader.vue";
  import AdminSideBar from "@/components/admin/AdminSideBar.vue";
</script>

<template>
    <AdminHeader/>
    <AdminSideBar/>
    <router-view/>
</template>

<style scoped>

</style>