<template>
  <el-row :gutter="10">
    <el-col :span="6">
      <el-select v-model="prov" placeholder="请选择省" @change="loadCity">
        <el-option
            v-for="p in provinceList"
            :key="p.code"
            :label="p.name"
            :value="p.code"
        />
      </el-select>
    </el-col>

    <el-col :span="6">
      <el-select v-model="city" placeholder="请选择市" @change="loadDistrict">
        <el-option
            v-for="c in cityList"
            :key="c.code"
            :label="c.name"
            :value="c.code"
        />
      </el-select>
    </el-col>

    <el-col :span="6">
      <el-select v-model="dist" placeholder="请选择区/县" @change="loadStreet">
        <el-option
            v-for="d in districtList"
            :key="d.code"
            :label="d.name"
            :value="d.code"
        />
      </el-select>
    </el-col>

    <el-col :span="6">
      <el-select v-model="street" placeholder="请选择街道">
        <el-option
            v-for="s in streetList"
            :key="s.code"
            :label="s.name"
            :value="s.code"
        />
      </el-select>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref } from 'vue'
import { listArea } from '@/api/nongjt/area'

const prov   = ref('')
const city   = ref('')
const dist   = ref('')
const street = ref('')

const provinceList = ref([])
const cityList     = ref([])
const districtList = ref([])
const streetList   = ref([])

// 取 rows 并过滤空对象 & code 为 null/undefined
function normalizeList(res) {
  const arr = (res && res.rows) || []
  return arr.filter(item => item && item.code != null)
}

// 初始加载省级
onMounted(async () => {
  const res = await listArea('0')
  console.log('原始返回', res)
  provinceList.value = normalizeList(res)
  console.log('省级下拉数组', provinceList.value)
})

async function loadCity(code) {
  city.value = ''
  dist.value = ''
  street.value = ''
  const res = await listArea(code)
  cityList.value = normalizeList(res)
}

async function loadDistrict(code) {
  dist.value = ''
  street.value = ''
  const res = await listArea(code)
  districtList.value = normalizeList(res)
}

async function loadStreet(code) {
  street.value = ''
  const res = await listArea(code)
  streetList.value = normalizeList(res)
}
</script>
