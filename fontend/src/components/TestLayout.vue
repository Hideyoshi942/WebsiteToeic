<template>
<HeaderTest @toggle-sidebar="toggleSidebar" />
<router-view />
<FooterBanner />
</template>

<script>
import FooterBanner from './FooterBanner.vue';
import HeaderTest from './HeaderTest.vue';


export default{
    name: "TestLayout",
    components: {
        HeaderTest,
        FooterBanner,
    },
    data() {
        return {
            isSidebarHidden: true,
        };
    },
    methods: {
        toggleSidebar() {
        this.isSidebarHidden = !this.isSidebarHidden;
        }
    }
}
</script>