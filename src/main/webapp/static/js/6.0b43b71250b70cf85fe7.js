webpackJsonp([6],{K31e:function(t,s,e){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var i={name:"Login",data:function(){return{username:"",password:""}},methods:{register:function(){this.$router.push("/register.html")},submit:function(t){var s=this;if(this.username)if(this.password){var e=this.$loading({text:"登录中"});this.$store.dispatch("userLogin",{loginName:this.username,password:this.password}).then(function(){e.close(),s.$router.push("/select.html")},function(t){e.close(),alert("登录失败")})}else alert("密码不能为空");else alert("用户名不能为空")}}},a={render:function(){var t=this,s=t.$createElement,i=t._self._c||s;return i("div",{staticClass:"login"},[i("img",{staticClass:"login-bg",attrs:{src:e("NsbF"),alt:""}}),t._v(" "),i("div",{staticClass:"login-center"},[i("img",{staticClass:"login-logo",attrs:{src:e("j42S"),alt:"logo"}}),t._v(" "),i("form",{attrs:{action:"#/system",method:"post"},on:{submit:function(s){s.preventDefault(),t.submit(s)}}},[i("div",{staticClass:"login-panel"},[i("div",{staticClass:"login-headline"},[t._v("登 录")]),t._v(" "),i("div",{staticClass:"login-username"},[i("input",{directives:[{name:"model",rawName:"v-model.trim",value:t.username,expression:"username",modifiers:{trim:!0}}],staticClass:"login-input",attrs:{name:"username",type:"text",placeholder:"用户名 :",required:"required"},domProps:{value:t.username},on:{input:function(s){s.target.composing||(t.username=s.target.value.trim())},blur:function(s){t.$forceUpdate()}}})]),t._v(" "),i("div",{staticClass:"login-pwd"},[i("input",{directives:[{name:"model",rawName:"v-model.trim",value:t.password,expression:"password",modifiers:{trim:!0}}],staticClass:"login-input",attrs:{name:"password",type:"password",placeholder:"密   码 :",required:"required"},domProps:{value:t.password},on:{input:function(s){s.target.composing||(t.password=s.target.value.trim())},blur:function(s){t.$forceUpdate()}}})]),t._v(" "),i("div",{staticClass:"login-submit"},[i("input",{staticClass:"login-button",attrs:{type:"submit",value:"登录"}}),t._v(" "),i("input",{staticClass:"login-button",attrs:{type:"button",value:"注册"},on:{click:t.register}})])])])])])},staticRenderFns:[]};var n=e("Z0/y")(i,a,!1,function(t){e("jFYr")},null,null);s.default=n.exports},NsbF:function(t,s,e){t.exports=e.p+"static/img/login.b0fd35d.png"},j42S:function(t,s,e){t.exports=e.p+"static/img/system.2a15edc.png"},jFYr:function(t,s){}});