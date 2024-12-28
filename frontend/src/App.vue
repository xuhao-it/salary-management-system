<template>
  <el-container class="app-wrapper">
    <el-aside width="200px">
      <el-menu
        :router="true"
        class="sidebar-menu"
        background-color="#304156"
        text-color="#bfcbd9">
        <el-menu-item index="/dashboard">
          <el-icon><Monitor /></el-icon>
          <span>工作台</span>
        </el-menu-item>
        <el-menu-item index="/employees">
          <el-icon><User /></el-icon>
          <span>员工管理</span>
        </el-menu-item>
        <el-menu-item index="/salary">
          <el-icon><Money /></el-icon>
          <span>工资管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <h2>工资管理系统</h2>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { Monitor, User, Money } from '@element-plus/icons-vue'
</script>

<style lang="scss">
.app-wrapper {
  height: 100vh;
  .el-aside {
    background-color: #304156;
  }
  .el-header {
    background-color: #fff;
    border-bottom: 1px solid #ddd;
    display: flex;
    align-items: center;
  }
}
</style>
