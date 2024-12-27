<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import type { Employee } from '@/types/employee'

const employees = ref<Employee[]>([])
const loading = ref(false)

const fetchEmployees = async () => {
  loading.value = true
  try {
    // TODO: 实现获取员工列表的 API 调用
    // const { data } = await axios.get('/api/employees')
    // employees.value = data
  } catch (error) {
    ElMessage.error('获取员工列表失败')
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchEmployees()
})
</script>

<template>
  <div class="employee-list">
    <el-card class="box-card"></el-card>
      <template #header>
        <div class="card-header">
          <span>员工列表</span>
          <el-button type="primary">添加员工</el-button>
        </div>
      </template>
      
      <el-table
        v-loading="loading"
        :data="employees"
        style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="姓名" width="120" />
        <el-table-column prop="position" label="职位" width="120" />
        <el-table-column prop="department" label="部门" width="120" />
        <el-table-column prop="salary" label="薪资" width="120" />
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button-group>
              <el-button type="primary" size="small">编辑</el-button>
              <el-button type="danger" size="small">删除</el-button>
            </el-button-group>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<style scoped>
.employee-list {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
