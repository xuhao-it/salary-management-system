import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { login } from '@/api/auth'

export const useLogin = () => {
  const router = useRouter()
  const loading = ref(false)
  const loginError = ref(false)
  const systemError = ref(false)

  const form = ref({
    username: 'admin',  // 设置默认用户名
    password: '123456'   // 设置默认密码
  })

  const isLogin = ref(true)
  const registerType = ref('individual')

  const registerForm = ref({
    username: '',
    password: '',
    confirmPassword: '',
    companyName: ''
  })

  const backgroundImage = computed(() => {
    if (loginError.value) return '/images/red_login.jpg'
    if (systemError.value) return '/images/yellow_login.jpg'
    return '/images/cyan_login.jpg'
  })

  const handleLogin = async () => {
    if (!form.value.username || !form.value.password) {
      ElMessage.warning('请输入用户名和密码')
      return
    }

    loading.value = true
    loginError.value = false
    systemError.value = false

    try {
      console.log('尝试登录:', { username: form.value.username })
      const result = await login({
        username: form.value.username.trim(),
        password: form.value.password
      })
      
      if (result.code === 200) {  // 修改为实际的成功状态码
        // 添加类型检查
        if (result.data) {
          localStorage.setItem('token', result.data.token)
          localStorage.setItem('userInfo', JSON.stringify(result.data.userInfo))
        }
        ElMessage.success('登录成功')
        await router.push('/')
      } else {
        throw new Error(result.message || '登录失败')
      }
    } catch (error: any) {
      console.error('登录错误:', error)
      loginError.value = true
      if (error.response?.status === 500) {
        systemError.value = true
        ElMessage.error('服务器错误，请联系管理员')
      } else {
        ElMessage.error(error.response?.data?.message || error.message || '登录失败')
      }
    } finally {
      loading.value = false
    }
  }

  const handleRegister = async () => {
    if (!registerForm.value.username || !registerForm.value.password || !registerForm.value.confirmPassword) {
      ElMessage.warning('请填写所有必填项')
      return
    }
    if (registerForm.value.password !== registerForm.value.confirmPassword) {
      ElMessage.warning('两次输入的密码不一致')
      return
    }

    loading.value = true
    try {
      // TODO: 实现注册逻辑
      await new Promise(resolve => setTimeout(resolve, 1000))
      ElMessage.success('注册成功')
      isLogin.value = true
    } catch (error) {
      ElMessage.error('注册失败')
    } finally {
      loading.value = false
    }
  }

  return {
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
  }
}
