<style>
html {
  background-color: skyblue;
}
.login {
    width: 40%;
    height: 40%; 
    margin: 0 auto;
    margin-top: 200px;
}
</style>
<template>
<div class="login">
<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="用户名" prop="username">
    <el-input type="account" v-model="ruleForm.account" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="password">
    <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
</div>
</template>

<script type="less" scoped>
  export default {
    data() {
      return {
        ruleForm: {
          account: '',
          password: ''
        }
      };
    },
    methods: {
      //登录功能
      submitForm() {
        this.$axios.post('/login',{'account':this.ruleForm.account,'password':this.ruleForm.password}).then(res=>{
          console.log(res)
        })
      }
    }
  }
</script>