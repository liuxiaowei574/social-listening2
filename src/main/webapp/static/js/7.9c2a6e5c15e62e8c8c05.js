webpackJsonp([7],{"4g7I":function(e,t){},"6l+9":function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=i("VCXJ"),n=[{name:"项目编辑",isActive:!1,href:"/edit"},{name:"大V",isActive:!1,href:"/v"},{name:"热词",isActive:!1,href:"/word"},{name:"声量",isActive:!0,href:"/voice"}],a=[{name:"项目编辑",isActive:!1,href:"/edit"},{name:"大V",isActive:!1,href:"/v"}],s={data:function(){for(var e=0;e<n.length;e++)this.activeName==n[e].name?n[e].isActive=!0:n[e].isActive=!1;return{tab:n,name:name}},computed:{project:function(){return this.$store.getters.getProjectById(this.$route.params.projectId)}},created:function(){this.init()},watch:{$router:"init"},props:{activeName:{type:String,required:!0}},methods:{init:function(){if("analyze"!=this.$store.state.system){this.tab=a;for(var e=0;e<a.length;e++)this.activeName==a[e].name?a[e].isActive=!0:a[e].isActive=!1}},changeTab:function(e){for(var t="",i=0;i<n.length;i++)e==n[i].name?(n[i].isActive=!0,t=n[i].href):n[i].isActive=!1;this.$router.push("/"+this.$store.state.system+"/project/"+this.$route.params.projectId+t)}}},c={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"project-header"},[e._l(e.tab,function(t){return[i("div",{staticClass:"project-tab",class:{"project-tab-active":t.isActive},on:{click:function(i){e.changeTab(t.name)}}},[e._v(e._s(t.name))])]}),e._v(" "),i("div",{staticClass:"project-name"},[e._v(e._s(e.project.name))])],2)},staticRenderFns:[]};var o=i("Z0/y")(s,c,!1,function(e){i("4g7I")},null,null).exports;r.default.component("project-header",o);var v={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"project"},[t("router-view")],1)},staticRenderFns:[]};var u=i("Z0/y")({name:"projectShow"},v,!1,function(e){i("dyeK")},null,null);t.default=u.exports},dyeK:function(e,t){}});