webpackJsonp([14],{"P+bd":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s={title:{text:"趋势 / 累积"},tooltip:{trigger:"axis",axisPointer:{type:"cross",label:{backgroundColor:"#6a7985"}}},legend:{data:[]},grid:{left:"3%",right:"4%",bottom:"3%",containLabel:!0},xAxis:[{type:"category",boundaryGap:!1,data:[]}],yAxis:[{type:"value"}]};function r(e){if(e){var t=new Date(e);return t.getFullYear()+"-"+(t.getMonth()+1<10?"0"+(t.getMonth()+1):t.getMonth()+1)+"-"+(t.getDate()<10?"0"+t.getDate():t.getDate())}}s.series=[],s.xAxis.data=[],s.legend.data=[],s.series=[];var c={name:"projectVoice",data:function(){return{showSearch:!0,labelPosition:"right",search:{start:"",end:"",comparison:"",weibo:!0,tmall:!1,jd:!1,jd_intelligent:!1}}},computed:{project:function(){return this.$store.getters.getProjectById(this.$route.params.projectId)},comparisonProject:function(){return this.$store.getters.getProjectById(this.search.comparison)}},mounted:function(){var e=this;this.$nextTick(function(){e.initCharts()})},created:function(){this.initPageDate()},watch:{$route:"initPageDate"},methods:{initPageDate:function(){var e=this,t=e.$store.getters.getProjectById(e.$route.params.projectId);if(0==this.search.end){var a=Date.now();this.search.end=a>t.end?t.end:a}0==this.search.start&&(this.search.start=t.start);var s=t.source;s.indexOf("1")>-1&&(e.search.weibo=!0),s.indexOf("2")>-1&&(e.search.jd_intelligent=!0),s.indexOf("3")>-1&&(e.search.jd=!0),s.indexOf("4")>-1&&(e.search.tmall=!0),e.searchData(),this.$store.dispatch("voiceTagSearch",{project_id:e.$route.params.projectId}).then(function(t){var a=echarts.init(e.$el.getElementsByClassName("project-chart")[1]);a.clear();for(var s={xAxis:{axisLabel:{interval:0},type:"category",data:[]},yAxis:{type:"value"},tooltip:{},series:[{data:[],type:"bar"}]},r=0;r<t.length;r++)s.xAxis.data.push({value:t[r].name,textStyle:{fontSize:10,color:"red"}}),s.series[0].data.push({value:t[r].value,itemStyle:{color:"rgb("+[Math.round(255*Math.random()),Math.round(255*Math.random()),Math.round(255*Math.random())].join(",")+")"}});a.setOption(s)},function(e){console.log(e)})},searchData:function(){var e=this;this.search.end-this.search.start>864e5&&(this.$loading(),this.fetchData(e.$route.params.projectId).then(function(t){e.search.comparison?e.fetchData(e.search.comparison).then(function(a){e.initData(t,a)},function(){console.log("获取对比项目数据失败",error)}):e.initData(t)},function(e){console.log(e)}))},fetchData:function(e){var t;return t=this.$store.getters.getSourceId(this.search.weibo,this.search.jd_intelligent,this.search.jd,this.search.tmall),this.$store.dispatch("voiceSearch",{project_id:e,source_id:t,start_time:this.search.start,end_time:this.search.end})},initData:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[],t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:[];if(this.$loading().close(),e=e.sort(function(e,t){return Date.parse(e.name)>Date.parse(t.name)}),t=t.sort(function(e,t){return Date.parse(e.name)>Date.parse(t.name)}),this.search.end-this.search.start>864e5){for(var a=[],c=[],i=[],o=this.search.start,n=this.search.end+864e5;o<n;o+=864e5){var l=r(o);a.push(l)}for(var h=e.shift(),d=t.shift(),p=0;p<a.length;p++){var u=0,v=0;h&&h.name==a[p]&&(u=h.value,h=e.shift()),d&&d.name==a[p]&&(v=d.value,d=t.shift()),c.push(u),i.push(v)}if(s.xAxis[0].data=a,s.legend.data=[this.project.name],s.series=[{name:this.project.name,type:"line",areaStyle:{normal:{}},data:c,smooth:!0}],this.search.comparison){var m=this.$store.getters.getProjectById(this.search.comparison);s.legend.data.push(m.name),s.series.push({name:m.name,type:"line",areaStyle:{normal:{}},data:i,smooth:!0})}this.initCharts()}},initCharts:function(){var e=echarts.init(this.$el.getElementsByClassName("project-chart")[0]);e.clear(),e.setOption(s)}}},i={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("project-header",{attrs:{projectId:e.$route.params.projectId,activeName:"声量"}}),e._v(" "),a("div",{staticClass:"project-show"},[a("div",{staticClass:"project-search"},[a("div",{staticClass:"project-search-btn",on:{click:function(t){e.showSearch=!e.showSearch}}},[e._v("\n              高级筛选 \n              "),a("div",{staticClass:"project-search-btn-arrow",class:[e.showSearch?"el-icon-arrow-up":"el-icon-arrow-down"]})]),e._v(" "),a("el-form",{attrs:{"label-position":"right","label-width":"100px",model:e.search}},[a("el-collapse-transition",[a("div",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],staticClass:"project-search-condition"},[a("el-form-item",{attrs:{label:"时间节点："}},[a("el-date-picker",{attrs:{type:"date","value-format":"timestamp",placeholder:"开始日期"},model:{value:e.search.start,callback:function(t){e.$set(e.search,"start",t)},expression:"search.start"}}),e._v(" "),a("div",{staticClass:"el-input",staticStyle:{width:"auto"}},[e._v("至")]),e._v(" "),a("el-date-picker",{attrs:{type:"date","value-format":"timestamp",placeholder:"结束日期"},model:{value:e.search.end,callback:function(t){e.$set(e.search,"end",t)},expression:"search.end"}}),e._v(" "),a("div",{staticClass:"el-input project-search-source-text"},[e._v("信息源：")]),e._v(" "),a("div",{staticClass:"el-input project-search-source"},[a("div",{staticClass:"project-search-source-base",class:[e.search.weibo?"project-search-source-weibo-active":"project-search-source-weibo"],attrs:{title:"微博"},on:{click:function(t){e.search.weibo=!e.search.weibo}}}),e._v(" "),a("div",{staticClass:"project-search-source-base",class:[e.search.jd_intelligent?"project-search-source-jd_intelligent-active":"project-search-source-jd_intelligent"],attrs:{title:"京东达人"},on:{click:function(t){e.search.jd_intelligent=!e.search.jd_intelligent}}}),e._v(" "),a("div",{staticClass:"project-search-source-base",class:[e.search.jd?"project-search-source-jd-active":"project-search-source-jd"],attrs:{title:"京东商城"},on:{click:function(t){e.search.jd=!e.search.jd}}}),e._v(" "),a("div",{staticClass:"project-search-source-base",class:[e.search.tmall?"project-search-source-tmall-active":"project-search-source-tmall"],attrs:{title:"天猫"},on:{click:function(t){e.search.tmall=!e.search.tmall}}})]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.searchData()}}},[e._v("查询")])],1)],1)]),e._v(" "),a("el-form-item",{staticStyle:{"margin-top":"10px"},attrs:{label:"项目对比："}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:function(t){e.searchData()}},model:{value:e.search.comparison,callback:function(t){e.$set(e.search,"comparison",t)},expression:"search.comparison"}},e._l(e.$store.getters.getComparisonProjectOptions(this.$route.params.projectId),function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1)],1)],1),e._v(" "),a("div",{staticClass:"project-chart"}),e._v(" "),a("div",{staticClass:"project-chart"})])],1)},staticRenderFns:[]};var o=a("Z0/y")(c,i,!1,function(e){a("ZZg7")},null,null);t.default=o.exports},ZZg7:function(e,t){}});