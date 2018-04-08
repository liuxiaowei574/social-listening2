webpackJsonp([11],{"0Xly":function(e,r){},yyA2:function(e,r,t){"use strict";Object.defineProperty(r,"__esModule",{value:!0});var o={data:function(){return{form:{name:"",status:"",brand:"",goods:"",start:"",end:"",source:["1","2","3","4"],keyWords:"",desc:""},rules:{name:[{required:!0,message:"请输入活动名称",trigger:"blur"},{min:1,max:10,message:"长度在 1 到 10 个字符",trigger:"blur"}],brand:[{required:!0,message:"请输入品牌名称",trigger:"blur"},{min:1,max:15,message:"长度在 1 到 15 个字符",trigger:"blur"}],goods:[{required:!0,message:"请输入商品名称",trigger:"blur"},{min:1,max:15,message:"长度在 1 到 15 个字符",trigger:"blur"}],start:[{required:!0,message:"请选择开始日期"}],end:[{required:!0,message:"请选择结束日期"}],source:[{type:"array",required:!0,message:"请至少选择一个信息源",trigger:"change"}],keyWords:[{required:!1,message:"请填写关键词",trigger:"blur"}],desc:[{required:!0,message:"请填写项目说明,填写品牌和商品后,点击查询可以自动获取",trigger:"blur"}]}}},mounted:function(){this.updateData()},watch:{$route:"updateData"},methods:{updateData:function(){var e=this.$store.getters.getProjectById(this.$route.params.projectId);this.form.name=e.name,this.form.status={0:"拒绝",1:"提交",2:"接受",3:"分析",4:"完成"}[e.status]||"未知",this.form.brand=e.brand,this.form.goods=e.goods,this.form.start=e.start,this.form.end=e.end,this.form.source=e.source.split("+"),this.form.desc=e.desc,this.form.keyWords=e.keyWords},submitForm:function(e){var r=this,t=this;this.$store.getters.getProjectById(this.$route.params.projectId).status>1?alert("项目已经开始,不能修改"):this.$refs[e].validate(function(e){if(!e)return console.log("error submit!!"),!1;t.$store.dispatch("editProject",{project_name:t.form.name,project_brand:t.form.brand,project_product:t.form.goods,project_info:t.form.desc,project_key_words:t.form.keyWords,project_source:t.form.source.join("+"),start_time:t.form.start,end_time:t.form.end,project_id:r.$route.params.projectId}).then(function(){alert("修改成功")},function(e){console.log(e),alert("修改失败")})})},resetForm:function(e){this.$refs[e].resetFields()},getDescFromBaidu:function(){var e=this;e.$store.dispatch("getDescFromBaidu",{project_brand:e.form.brand,project_product:e.form.goods}).then(function(r){console.log(r),e.form.desc=r.data},function(e){console.log(error)})}}},s={render:function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",[t("project-header",{attrs:{projectId:this.$route.params.projectId,activeName:"项目编辑"}}),e._v(" "),t("div",{staticClass:"project-show"},[t("el-form",{ref:"form",staticClass:"project-search",attrs:{model:e.form,rules:e.rules,"label-width":"100px"}},[t("el-form-item",{attrs:{label:"名称",prop:"name"}},[t("el-input",{model:{value:e.form.name,callback:function(r){e.$set(e.form,"name",r)},expression:"form.name"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"项目状态",prop:"status"}},[t("el-input",{attrs:{disabled:!0},model:{value:e.form.status,callback:function(r){e.$set(e.form,"status",r)},expression:"form.status"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"品牌",prop:"brand"}},[t("el-input",{model:{value:e.form.brand,callback:function(r){e.$set(e.form,"brand",r)},expression:"form.brand"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"商品",prop:"goods"}},[t("el-input",{model:{value:e.form.goods,callback:function(r){e.$set(e.form,"goods",r)},expression:"form.goods"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(r){e.getDescFromBaidu()}}},[e._v("查询项目描述")])],1),e._v(" "),t("el-form-item",{attrs:{label:"项目描述",prop:"desc"}},[t("el-input",{attrs:{type:"textarea",rows:5},model:{value:e.form.desc,callback:function(r){e.$set(e.form,"desc",r)},expression:"form.desc"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"时间",required:""}},[t("el-form-item",{staticStyle:{display:"inline-block"},attrs:{prop:"start"}},[t("el-date-picker",{attrs:{type:"date",editable:!1,"value-format":"timestamp",placeholder:"开始日期"},model:{value:e.form.start,callback:function(r){e.$set(e.form,"start",r)},expression:"form.start"}})],1),e._v(" "),t("div",{staticClass:"el-input",staticStyle:{width:"auto"}},[e._v("至")]),e._v(" "),t("el-form-item",{staticStyle:{display:"inline-block"},attrs:{prop:"end"}},[t("el-date-picker",{attrs:{type:"date",editable:!1,"value-format":"timestamp",placeholder:"结束日期"},model:{value:e.form.end,callback:function(r){e.$set(e.form,"end",r)},expression:"form.end"}})],1)],1),e._v(" "),t("el-form-item",{attrs:{label:"信息源",prop:"source"}},[t("el-checkbox-group",{model:{value:e.form.source,callback:function(r){e.$set(e.form,"source",r)},expression:"form.source"}},[t("el-checkbox",{attrs:{label:"1",name:"source"}},[e._v("微博")]),e._v(" "),t("el-checkbox",{attrs:{label:"2",name:"source"}},[e._v("京东达人")]),e._v(" "),t("el-checkbox",{attrs:{label:"3",name:"source"}},[e._v("京东商城")]),e._v(" "),t("el-checkbox",{attrs:{label:"4",name:"source"}},[e._v("天猫")])],1)],1),e._v(" "),t("el-form-item",{attrs:{label:"关键词",prop:"keyWords"}},[t("el-input",{model:{value:e.form.keyWords,callback:function(r){e.$set(e.form,"keyWords",r)},expression:"form.keyWords"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(r){e.submitForm("form")}}},[e._v("保存")]),e._v(" "),t("el-button",{on:{click:function(r){e.resetForm("form")}}},[e._v("重置")])],1)],1)],1)],1)},staticRenderFns:[]};var a=t("Z0/y")(o,s,!1,function(e){t("0Xly")},null,null);r.default=a.exports}});