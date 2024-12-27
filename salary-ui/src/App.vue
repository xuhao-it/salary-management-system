<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>

<template>
  <router-view></router-view>
</template>

<style>
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  font-family: Arial, sans-serif;
}

#app {
  height: 100vh;
  width: 100%;
  display: flex;
  flex-direction: column;
}

:root {
  --el-color-primary: #409eff;
}
</style>
