webpackJsonp([16],{GY0D:function(e,t){},NHnr:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});n("wW0W");var o=n("PlDa"),r=n.n(o),a=n("VCXJ"),c=n("9rMa"),i=(n("2sCs"),n("2sCs"));n("pqRC");function s(e,t,n){i(e).then(function(e){e.data?"ok"==e.data.status?t(e.data):(n(null,e.data.message||"系统错误"),console.log(e.data.message||"系统错误")):(n(null,"未收到返回数据"),console.log("未收到返回数据"))}).catch(function(e){console.log(e),n(e,e.Message)})}function u(e,t,n){s({method:"post",url:e.url,data:e.data},t,n)}i.defaults.timeout=3e3,i.defaults.responseType="json",i.defaults.validateStatus=function(e){return e>=200&&e<300};var d=n("rVsN"),l=n.n(d),p={state:{analyze:[],analyzeDefault:[{name:"添加项目",url:"/analyze/addProject",id:-1},{name:"消息",id:-2,url:"/analyze/message"}],recommend:[],recommendDefault:[{name:"添加项目",url:"/recommend/addProject",id:-1},{name:"消息",id:-2,url:"/recommend/message"}]},getters:{getProjectByMenuId:function(e,t,n,o){return function(t){for(var o=e[n.system].length-1;o>=0;o--){var r=e[n.system][o];if(t==r.id)return r}}}},mutations:{},actions:{updateMenu:function(e){e.state;return"analyze"==e.rootState.system?this.dispatch("getAnalyzeMenu"):this.dispatch("getRecommendMenu")},getAnalyzeMenu:function(e,t){var n=e.state,o=e.rootState,r=this;return new l.a(function(e,t){u({url:"ProjectManager",data:{module:"project_list_by_user",project_type:1,user_id:r.state.user.id}},function(t){n.analyze=[],o.project.list=[];for(var r=0;r<t.data.length;r++){var a=t.data[r];n.analyze.push({name:a.project_name,id:a.project_id,url:"/analyze/project/"+a.project_id}),o.project.list.push({name:a.project_name,id:a.project_id,url:"/analyze/project/"+a.project_id,brand:a.project_brand,goods:a.project_product,desc:a.project_info,keyWords:a.project_key_words,source:a.project_source,start:a.start_time,end:a.end_time,create:a.project_create_time,status:a.project_status})}n.analyze=n.analyze.concat(n.analyzeDefault),o.system="analyze",e(n.analyze)},function(e,n){t({err:e,message:n})})})},getRecommendMenu:function(e,t){var n=e.state,o=e.rootState,r=this;return new l.a(function(e,t){u({url:"ProjectManager",data:{module:"project_list_by_user",project_type:2,user_id:r.state.user.id}},function(t){n.recommend=[],o.project.list=[];for(var r=0;r<t.data.length;r++){var a=t.data[r];n.recommend.push({name:a.project_name,id:a.project_id,url:"/recommend/project/"+a.project_id}),o.project.list.push({name:a.project_name,id:a.project_id,url:"/analyze/project/"+a.project_id,brand:a.project_brand,goods:a.project_product,desc:a.project_info,keyWords:a.project_key_words,source:a.project_source,start:a.start_time,end:a.end_time,create:a.project_create_time,status:a.project_status})}n.recommend=n.recommend.concat(n.recommendDefault),o.system="recommend",e(n.recommend)},function(e,n){t({err:e,message:n})})})}}},m={state:{all:[],unread:[]},getters:{},mutations:{},actions:{getMessage:function(e,t){var n=e.state,o=(e.rootState,this);return new l.a(function(e,t){u({url:"MsgManager",data:{module:"msg_list_by_user",userid:o.state.user.id}},function(t){n.unread=[];for(var o=0;o<t.data.length;o++){var r=t.data[o];0==r.be_read&&n.unread.push(r)}n.all=t.data,e()},function(e,n){t(n||e)})})},setMessageRead:function(e,t){e.state,e.rootState;return new l.a(function(e,n){u({url:"MsgManager",data:{module:"read_msg",msg_id:t.msgId}},function(t){e()},function(e,t){n(t||e)})})}}},f={state:{avatar:"",loginName:"登录名称",level:"",id:0,status:""},getters:{},mutations:{setDefaultUser:function(e,t){e.avatar=t.user_pic,e.loginName=t.login_name,e.loginNlevelame=t.user_level,e.status=t.user_status,e.id=t.user_id}},actions:{userLogin:function(e,t){e.state,e.rootState;var n=this;return new l.a(function(e,o){u({url:"login",data:{login_name:t.loginName,password:md5(t.password)}},function(t){n.isLogin=!0,n.commit("setDefaultUser",t.data),e()},function(e,t){o(t||e)})})},userRegister:function(e,t){e.state,e.rootState;return new l.a(function(e,n){u({url:"UserManager",data:{module:"insert",login_name:t.loginName,password:md5(t.password),user_level:1,user_status:1}},function(t){e()},function(e,t){n(t||e)})})},userCheck:function(e,t){e.state,e.rootState;var n=this;return new l.a(function(e,t){1!=n.isLogin?u({url:"login",data:{}},function(t){n.isLogin=!0,n.commit("setDefaultUser",t.data),e()},function(e,n){t(n||e)}):e()})},userLogout:function(e,t){e.state,e.rootState;return new l.a(function(e,t){u({url:"login",data:{logout:1}},function(t){e()},function(e,n){t({err:e,message:n})})})}}},_={state:{list:[]},getters:{getProjectById:function(e,t,n,o){return function(t){for(var n=e.list.length-1;n>=0;n--){var o=e.list[n];if(t==o.id)return o}}},getComparisonProjectOptions:function(e,t,n,o){return function(t){for(var n=[{value:!1,label:"请选择"}],o=0;o<e.list.length;o++){var r=e.list[o];t!=r.id&&n.push({value:r.id,label:r.name})}return n}},getSourceId:function(e,t,n,o){return function(e,t,n,o){var r="";return e&&(r+="1"),t&&(r+="2"),n&&(r+="3"),o&&(r+="4"),r=r.split("").join("+")}}},mutations:{},actions:{addProject:function(e,t){e.state,e.rootState;var n=this;return new l.a(function(e,o){u({url:"ProjectManager",data:{module:"insert",project_type:n.getters.systemType,project_name:t.project_name,project_brand:t.project_brand,project_product:t.project_product,project_info:t.project_info,project_key_words:t.project_key_words,project_source:t.project_source,start_time:t.start_time,end_time:t.end_time}},function(t){e(t)},function(e,t){o(t||e)})})},getDescFromBaidu:function(e,t){e.state,e.rootState;return new l.a(function(e,n){u({url:"ProjectManager",data:{module:"query_project_info_from_baike",project_brand:t.project_brand,project_product:t.project_product}},function(t){e(t)},function(e,t){n(t||e)})})},editProject:function(e,t){e.state,e.rootState;var n=this;return new l.a(function(e,o){u({url:"ProjectManager",data:{module:"update_by_owner",project_type:n.getters.systemType,project_name:t.project_name,project_brand:t.project_brand,project_product:t.project_product,project_info:t.project_info,project_key_words:t.project_key_words,project_source:t.project_source,start_time:t.start_time,end_time:t.end_time,project_id:t.project_id}},function(o){n.getters.getProjectByMenuId(t.project_id).name=t.project_name,n.getters.getProjectById(t.project_id).name=t.project_name,e(o)},function(e,t){o(t||e)})})},voiceSearch:function(e,t){e.state,e.rootState;return new l.a(function(e,n){u({url:"Analysis",data:{module:"sound",project_id:t.project_id,source_id:t.source_id,start_time:t.start_time,end_time:t.end_time}},function(t){e(t.data)},function(e,t){n(t||e)})})},voiceTagSearch:function(e,t){e.state,e.rootState;return new l.a(function(e,n){u({url:"Analysis",data:{module:"tags",project_id:t.project_id}},function(t){e(t.data)},function(e,t){n(t||e)})})},wordSearch:function(e,t){e.state,e.rootState;return new l.a(function(e,n){u({url:"Analysis",data:{module:"topic_words",project_id:t.id}},function(t){e(t.data)},function(e,t){n({err:e,message:t})})})},vSearch:function(e,t){e.state,e.rootState;return new l.a(function(e,n){u({url:"Analysis",data:{module:"rank",project_id:t.id,source_id:t.source_id}},function(t){e(t.data)},function(e,t){n({err:e,message:t})})})}}};a.default.use(c.a);var g=new c.a.Store({modules:{user:f,menu:p,project:_,message:m},state:{system:"analyze",isLogin:!1},getters:{systemType:function(e,t,n,o){return console.log(e.systemType),"analyze"==e.system?1:2}},mutations:{},actions:{}}),j=n("zO6J"),h=function(){return n.e(6).then(n.bind(null,"K31e"))},y=function(){return n.e(8).then(n.bind(null,"6l+9"))},v=function(){return n.e(1).then(n.bind(null,"6PCn"))},b=function(){return n.e(4).then(n.bind(null,"P+bd"))},w=function(){return n.e(9).then(n.bind(null,"Lm7O"))},z=function(){return n.e(10).then(n.bind(null,"sYLj"))};a.default.use(j.a);var S=new j.a({routes:[{path:"/analyze/",base:"/analyze/",alias:"/analyze.html",name:"analyzeSystem",component:function(){return Promise.all([n.e(0),n.e(3)]).then(n.bind(null,"6xKu"))},meta:{login:!0},beforeEnter:function(e,t,n){S.app.$store.state.menu.analyze.length>0&&n(),S.app.$store.dispatch("getAnalyzeMenu").then(function(e){n(S.app.$store.state.menu.analyze[0].url)},function(e){console.log(e)})},children:[{path:"project/:projectId",base:"project/:projectId",redirect:"/analyze/project/:projectId/voice",component:y,children:[{path:"word",component:w},{path:"v",component:v},{path:"edit",component:function(){return n.e(11).then(n.bind(null,"yyA2"))}},{path:"voice",component:b}]},{path:"addProject",component:function(){return n.e(12).then(n.bind(null,"L9dS"))}},{path:"message",component:z}]},{path:"/recommend/",base:"/recommend/",alias:"/recommend.html",name:"recommendSystem",component:function(){return Promise.all([n.e(0),n.e(2)]).then(n.bind(null,"OHxz"))},meta:{login:!0},beforeEnter:function(e,t,n){S.app.$store.state.menu.recommend.length>0>0&&n(),S.app.$store.dispatch("getRecommendMenu").then(function(e){n(S.app.$store.state.menu.recommend[0].url)},function(e){console.log(e)})},children:[{path:"project/:projectId",base:"project/:projectId",redirect:"/recommend/project/:projectId/v",component:y,children:[{path:"word",component:w},{path:"v",component:v},{path:"edit",component:function(){return n.e(13).then(n.bind(null,"XjR3"))}},{path:"voice",component:b}]},{path:"addProject",component:function(){return n.e(14).then(n.bind(null,"P5Zs"))}},{path:"message",component:z}]},{path:"select",alias:"/select.html",name:"Select",component:function(){return Promise.all([n.e(0),n.e(5)]).then(n.bind(null,"NQ1r"))},meta:{login:!0}},{path:"register",alias:"/register.html",name:"Register",component:function(){return n.e(7).then(n.bind(null,"q3Wa"))}},{path:"login",alias:"/login.html",name:"Login",component:h},{path:"*",name:"Error",component:h}]});S.beforeEach(function(e,t,n){"Login"!==e.name&&"Register"!==e.name?S.app.$store.state.isLogin?n():S.app.$store.dispatch("userCheck").then(function(e){n()},function(e){n("/login.html")}):n()});var M=S,P={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{directives:[{name:"loading",rawName:"v-loading",value:this.loading,expression:"loading"}],staticClass:"content",attrs:{"element-loading-text":"拼命加载中","element-loading-spinner":"el-icon-loading loading-size-big","element-loading-background":"rgba(0, 0, 0, 0.8)"}},[t("router-view")],1)},staticRenderFns:[]};var k=n("Z0/y")({name:"App",data:function(){return{loading:!1}},mounted:function(){}},P,!1,function(e){n("GY0D")},null,null).exports;a.default.use(r.a),a.default.config.productionTip=!1,a.default.config.devtools=!1;new a.default({el:"#app",store:g,computed:{},router:M,components:{App:k},template:"<App />"})},pqRC:function(e,t){e.exports={baseurl:"http://127.0.0.1:1234",login:"/login",register:"/register"}},wW0W:function(e,t){}},["NHnr"]);