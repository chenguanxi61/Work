<!-- src/views/nongjt/pos/index.vue -->
<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="90px">
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="省" prop="province">
            <el-input
                v-model="queryParams.province"
                placeholder="请输入省"
                clearable
                @keyup.enter="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="市" prop="city">
            <el-input
                v-model="queryParams.city"
                placeholder="请输入市"
                clearable
                @keyup.enter="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="区/县" prop="area">
            <el-input
                v-model="queryParams.area"
                placeholder="请输入区/县"
                clearable
                @keyup.enter="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="乡镇/街道" prop="country">
            <el-input
                v-model="queryParams.country"
                placeholder="请输入乡镇/街道"
                clearable
                @keyup.enter="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="服务点名称" prop="posName">
            <el-input
                v-model="queryParams.posName"
                placeholder="请输入服务点名称"
                clearable
                @keyup.enter="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="序列号" prop="serialCode">
            <el-input
                v-model="queryParams.serialCode"
                placeholder="请输入序列号"
                clearable
                @keyup.enter="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="服务点状态" clearable style="width: 200px">
              <el-option
                  v-for="dict in sys_normal_disable"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="Plus"
            @click="handleAdd"
            v-hasPermi="['ytj:pos:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['ytj:pos:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['ytj:pos:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['ytj:pos:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="posList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="省" align="center" prop="province" />
      <el-table-column label="市" align="center" prop="city" />
      <el-table-column label="区/县" align="center" prop="area" />
      <el-table-column label="乡镇/街道" align="center" prop="country" />
      <el-table-column label="详细地址" align="center" prop="detailAddress" />
      <el-table-column label="服务点名称" align="center" prop="posName" />
      <el-table-column label="序列号" align="center" prop="serialCode" />
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="sys_normal_disable" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['ytj:pos:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['ytj:pos:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改服务点对话框 -->
    <el-dialog :title="title" v-model="open" width="700px" append-to-body>
      <el-form ref="posRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="省" prop="province">
              <el-input v-model="form.province" placeholder="请输入省" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="市" prop="city">
              <el-input v-model="form.city" placeholder="请输入市" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="区/县" prop="area">
              <el-input v-model="form.area" placeholder="请输入区/县" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="乡镇/街道" prop="country">
              <el-input v-model="form.country" placeholder="请输入乡镇/街道" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="详细地址" prop="detailAddress">
          <el-input v-model="form.detailAddress" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="服务点名称" prop="posName">
          <el-input v-model="form.posName" placeholder="请输入服务点名称" />
        </el-form-item>
        <el-form-item label="序列号" prop="serialCode">
          <el-input v-model="form.serialCode" placeholder="请输入序列号" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
                v-for="dict in sys_normal_disable"
                :key="dict.value"
                :value="dict.value"
            >{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="YtjPos">
import { listPos, getPos, addPos, updatePos, delPos, exportPos } from "@/api/nongjt/pos"

const { proxy } = getCurrentInstance()
const { sys_normal_disable } = proxy.useDict("sys_normal_disable")

const posList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    province: undefined,
    city: undefined,
    area: undefined,
    country: undefined,
    posName: undefined,
    serialCode: undefined,
    status: undefined
  },
  rules: {
    province: [{ required: true, message: "省不能为空", trigger: "blur" }],
    city: [{ required: true, message: "市不能为空", trigger: "blur" }],
    area: [{ required: true, message: "区/县不能为空", trigger: "blur" }],
    country: [{ required: true, message: "乡镇/街道不能为空", trigger: "blur" }],
    posName: [{ required: true, message: "服务点名称不能为空", trigger: "blur" }],
    serialCode: [{ required: true, message: "序列号不能为空", trigger: "blur" }],
    status: [{ required: true, message: "状态不能为空", trigger: "change" }]
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询服务点列表 */
function getList() {
  loading.value = true
  listPos(queryParams.value).then(response => {
    posList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

/** 取消按钮 */
function cancel() {
  open.value = false
  reset()
}

/** 表单重置 */
function reset() {
  form.value = {
    id: undefined,
    province: undefined,
    city: undefined,
    area: undefined,
    country: undefined,
    detailAddress: undefined,
    posName: undefined,
    serialCode: undefined,
    status: "0"
  }
  proxy.resetForm("posRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加服务点"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const id = row.id || ids.value
  getPos(id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改服务点"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["posRef"].validate(valid => {
    if (valid) {
      if (form.value.id != undefined) {
        updatePos(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addPos(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const posIds = row.id || ids.value
  proxy.$modal.confirm('是否确认删除服务点编号为"' + posIds + '"的数据项？').then(function() {
    return delPos(posIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download("ytj/pos/export", {
    ...queryParams.value
  }, `post_${new Date().getTime()}.xlsx`)
}

getList()
</script>
