<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="主体名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入主体名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册手机号" prop="mobilePhone">
        <el-input
          v-model="queryParams.mobilePhone"
          placeholder="请输入注册手机号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="queryParams.password"
          placeholder="请输入密码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册时间" prop="registerTime">
        <el-date-picker clearable
          v-model="queryParams.registerTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择注册时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否会员" prop="memberFlag">
        <el-input
          v-model="queryParams.memberFlag"
          placeholder="请输入是否会员"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="信息是否完善" prop="perfectFlag">
        <el-input
          v-model="queryParams.perfectFlag"
          placeholder="请输入信息是否完善"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="信息完善时间" prop="perfectTime">
        <el-date-picker clearable
          v-model="queryParams.perfectTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择信息完善时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="试用到期时间" prop="probationTime">
        <el-date-picker clearable
          v-model="queryParams.probationTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择试用到期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="购买会员时间" prop="memberTime">
        <el-date-picker clearable
          v-model="queryParams.memberTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择购买会员时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="会员过期时间" prop="memberExpiredTime">
        <el-date-picker clearable
          v-model="queryParams.memberExpiredTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择会员过期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['nongjt:register:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['nongjt:register:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['nongjt:register:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['nongjt:register:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="registerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="主体名称" align="center" prop="companyName" />
      <el-table-column label="注册手机号" align="center" prop="mobilePhone" />
      <el-table-column label="密码" align="center" prop="password" />
      <el-table-column label="注册时间" align="center" prop="registerTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.registerTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否会员" align="center" prop="memberFlag" />
      <el-table-column label="信息是否完善" align="center" prop="perfectFlag" />
      <el-table-column label="信息完善时间" align="center" prop="perfectTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.perfectTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="试用到期时间" align="center" prop="probationTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.probationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="购买会员时间" align="center" prop="memberTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.memberTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="会员过期时间" align="center" prop="memberExpiredTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.memberExpiredTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户状态" align="center" prop="userStatus" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['nongjt:register:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['nongjt:register:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户注册信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="registerRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="主体名称" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入主体名称" />
        </el-form-item>
        <el-form-item label="注册手机号" prop="mobilePhone">
          <el-input v-model="form.mobilePhone" placeholder="请输入注册手机号" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="注册时间" prop="registerTime">
          <el-date-picker clearable
            v-model="form.registerTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择注册时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否会员" prop="memberFlag">
          <el-input v-model="form.memberFlag" placeholder="请输入是否会员" />
        </el-form-item>
        <el-form-item label="信息是否完善" prop="perfectFlag">
          <el-input v-model="form.perfectFlag" placeholder="请输入信息是否完善" />
        </el-form-item>
        <el-form-item label="信息完善时间" prop="perfectTime">
          <el-date-picker clearable
            v-model="form.perfectTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择信息完善时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="试用到期时间" prop="probationTime">
          <el-date-picker clearable
            v-model="form.probationTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择试用到期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="购买会员时间" prop="memberTime">
          <el-date-picker clearable
            v-model="form.memberTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择购买会员时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="会员过期时间" prop="memberExpiredTime">
          <el-date-picker clearable
            v-model="form.memberExpiredTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择会员过期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="删除标记" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标记" />
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

<script setup name="Register">
import { listRegister, getRegister, delRegister, addRegister, updateRegister } from "@/api/nongjt/register"

const { proxy } = getCurrentInstance()

const registerList = ref([])
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
    companyName: null,
    mobilePhone: null,
    password: null,
    registerTime: null,
    memberFlag: null,
    perfectFlag: null,
    perfectTime: null,
    probationTime: null,
    memberTime: null,
    memberExpiredTime: null,
    userStatus: null,
  },
  rules: {
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询用户注册信息列表 */
function getList() {
  loading.value = true
  listRegister(queryParams.value).then(response => {
    registerList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    companyName: null,
    mobilePhone: null,
    password: null,
    registerTime: null,
    memberFlag: null,
    perfectFlag: null,
    perfectTime: null,
    probationTime: null,
    memberTime: null,
    memberExpiredTime: null,
    delFlag: null,
    userStatus: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  }
  proxy.resetForm("registerRef")
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

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加用户注册信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getRegister(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改用户注册信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["registerRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateRegister(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addRegister(form.value).then(response => {
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
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除用户注册信息编号为"' + _ids + '"的数据项？').then(function() {
    return delRegister(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('nongjt/register/export', {
    ...queryParams.value
  }, `register_${new Date().getTime()}.xlsx`)
}

getList()
</script>
