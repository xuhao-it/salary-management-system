<script setup lang="ts">
import { useLogin } from './userLogin'

const {
  loading,
  loginError,
  systemError,
  form,
  isLogin,
  registerType,
  registerForm,
  backgroundImage,
  handleLogin,
  handleRegister
} = useLogin()
</script>

<template>
  <div class="login-container" :style="{ backgroundImage: `url(${backgroundImage})` }">
    <div class="login-box">
      <div class="logo-container">
        <img src="/logo/app_logo.png" alt="Logo" class="logo">
        <h1 class="title">企业工资管理系统</h1>
      </div>
      
      <el-form :model="isLogin ? form : registerForm" class="login-form">
        <el-form-item v-if="isLogin">
          <el-input
            v-model="form.username"
            placeholder="用户名"
            prefix-icon="User"
          />
        </el-form-item>
        
        <el-form-item v-if="isLogin">
          <el-input
            v-model="form.password"
            type="password"
            placeholder="密码"
            prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        
        <el-form-item v-if="!isLogin">
          <el-input
            v-model="registerForm.username"
            placeholder="用户名"
            prefix-icon="User"
          />
        </el-form-item>
        
        <el-form-item v-if="!isLogin">
          <el-input
            v-model="registerForm.password"
            type="password"
            placeholder="密码"
            prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        
        <el-form-item v-if="!isLogin">
          <el-input
            v-model="registerForm.confirmPassword"
            type="password"
            placeholder="确认密码"
            prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        
        <el-form-item v-if="!isLogin && registerType === 'company'">
          <el-input
            v-model="registerForm.companyName"
            placeholder="公司名称"
            prefix-icon="OfficeBuilding"
          />
        </el-form-item>
        
        <el-form-item>
          <el-button
            type="primary"
            :loading="loading"
            class="login-button"
            @click="isLogin ? handleLogin : handleRegister"
          >
            {{ isLogin ? '登录' : '注册' }}
          </el-button>
        </el-form-item>
        
        <el-form-item>
          <el-link @click="isLogin = !isLogin">
            {{ isLogin ? '没有账号？点击注册' : '已有账号？点击登录' }}
          </el-link>
        </el-form-item>
        
        <el-form-item v-if="!isLogin">
          <el-radio-group v-model="registerType">
            <el-radio :value="'individual'">个体注册</el-radio>
            <el-radio :value="'company'">企业注册</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style >
@import './login.css';
</style>
