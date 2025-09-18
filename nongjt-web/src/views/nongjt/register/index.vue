<template>
  <div class="app-container">
    <!-- 1. 搜索查询区域：基础条件+折叠低频条件 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="auto">
      <!-- 基础高频查询条件（第二个页面保留的4个） -->
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
      <el-form-item label="是否会员" prop="memberFlag">
        <el-select v-model="queryParams.memberFlag" placeholder="请选择是否会员" clearable style="width: 200px">
          <el-option label="是" :value="1" />
          <el-option label="否" :value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="用户状态" prop="userStatus">
        <el-select v-model="queryParams.userStatus" placeholder="请选择用户状态" clearable style="width: 200px">
          <el-option label="正常" :value="1" />
          <el-option label="冻结" :value="0" />
        </el-select>
      </el-form-item>

      <!-- 折叠面板：隐藏第一个页面的低频查询条件（默认折叠，需用时展开） -->
      <el-form-item>
        <el-collapse v-model="activeCollapse" accordion style="width: 100%">
          <el-collapse-item title="更多查询条件" name="moreQuery">
            <el-form :model="queryParams" :inline="true" label-width="auto">
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
              <el-form-item label="信息是否完善" prop="perfectFlag">
                <el-select v-model="queryParams.perfectFlag" placeholder="请选择信息完善状态" clearable style="width: 200px">
                  <el-option label="已完善" :value="1" />
                  <el-option label="未完善" :value="0" />
                </el-select>
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
              <el-form-item label="删除标记" prop="delFlag">
                <el-select v-model="queryParams.delFlag" placeholder="请选择删除标记" clearable style="width: 200px">
                  <el-option label="未删除" :value="0" />
                  <el-option label="已删除" :value="1" />
                </el-select>
              </el-form-item>
            </el-form>
          </el-collapse-item>
        </el-collapse>
      </el-form-item>

      <!-- 搜索/重置按钮 -->
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 2. 功能操作栏：恢复新增按钮（保留权限控制） -->
    <el-row :gutter="10" class="mb8">
      <!-- 恢复第一个页面的“新增”按钮，按需显示（通过权限+业务开关控制） -->
      <el-col :span="1.5" v-if="showAddButton">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['nongjt:register:add']"
        >新增</el-button>
      </el-col>
      <!-- 保留第二个页面的修改/删除/导出按钮 -->
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

    <!-- 3. 数据表格：保留第二个页面的优化样式（彩色标签+固定操作列） -->
    <el-table v-loading="loading" :data="registerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="主体名称" align="center" prop="companyName" width="200px"/>
      <el-table-column label="注册手机号" align="center" prop="mobilePhone" width="120px"/>
      <el-table-column label="注册时间" align="center" prop="registerTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.registerTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否会员" align="center" prop="memberFlag" >
        <template #default="scope">
          <el-tag v-if="scope.row.memberFlag===1" type="success">是</el-tag>
          <el-tag v-if="scope.row.memberFlag===0" type="danger">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="信息是否完善" align="center" prop="perfectFlag" width="120" >
        <template #default="scope">
          <el-tag v-if="scope.row.perfectFlag===1" type="success">已完善</el-tag>
          <el-tag v-if="scope.row.perfectFlag===0" type="danger">未完善</el-tag>
        </template>
      </el-table-column>
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
      <el-table-column label="用户状态" align="center" prop="userStatus" >
        <template #default="scope">
          <el-tag v-if="scope.row.userStatus===1" type="success">正常</el-tag>
          <el-tag v-if="scope.row.userStatus===0" type="danger">冻结</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" fixed="right" width="130" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['nongjt:register:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['nongjt:register:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 4. 分页组件：与原页面逻辑一致 -->
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 5. 新增/修改对话框：复用第一个页面的完整字段（补全低频字段） -->
    <!-- 添加或修改用户注册信息对话框 -->
<el-dialog :title="title" v-model="open" width="800px" append-to-body>
  <el-form ref="registerRef" :model="form" :rules="rules" label-width="100px">
    <el-form-item label="主体名称" prop="companyName">
      <el-input v-model="form.companyName" placeholder="请输入主体名称" />
    </el-form-item>
    <el-form-item label="注册手机号" prop="mobilePhone">
      <el-input v-model="form.mobilePhone" placeholder="请输入注册手机号" />
    </el-form-item>
    <el-form-item label="注册时间" prop="registerTime">
      <el-date-picker clearable disabled
        v-model="form.registerTime"
        type="datetime"
        value-format="YYYY-MM-DD HH:mm:ss"
        placeholder="请选择注册时间">
      </el-date-picker>
    </el-form-item>

    <el-form-item label="是否会员" prop="memberFlag">
      <el-radio-group v-model="form.memberFlag">
        <el-radio-button :label="1">是</el-radio-button>
        <el-radio-button :label="0">否</el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="信息是否完善" prop="perfectFlag">
      <el-radio-group v-model="form.perfectFlag">
        <el-radio-button :label="1">是</el-radio-button>
        <el-radio-button :label="0">否</el-radio-button>
      </el-radio-group>
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
    <el-form-item label="用户状态" prop="userStatus">
      <el-radio-group v-model="form.userStatus">
        <el-radio-button :label="1">正常</el-radio-button>
        <el-radio-button :label="0">冻结</el-radio-button>
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
  }, `用户注册信息_${new Date().getTime()}.xlsx`)
}

getList()
</script>
