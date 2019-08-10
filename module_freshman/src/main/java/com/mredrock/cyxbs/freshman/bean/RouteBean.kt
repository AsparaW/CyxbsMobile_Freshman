package com.mredrock.cyxbs.freshman.bean

/**
 * Created by tk on 2019/8/8
 */
class RouteBean {

    /**
     * code : 200
     * info : ok
     * text_1 : {"title":"重庆邮电大学地址","message":"重庆市南岸区南山街道崇文路2号重庆邮电大学"}
     * text_2 : {"title":"推荐路线","message":[{"name":"江北机场","route":["江北机场乘坐三号线→南坪站下车从5号出口出站→乘坐346/347路公交车→邮电大学站下车","乘坐机场大巴→上清寺后下车乘坐108路公交车→南坪站转乘346/347路公交车→邮电大学站下车"]},{"name":"重庆火车北站北广场/南广场","route":["乘坐323/119/354路公交车→南坪站下车转乘346/347路公交车→邮电大学站下车"]},{"name":"菜园坝火车站","route":["在菜园坝广场乘坐347路公交车→邮电大学站下车"]},{"name":"朝天门码头","route":["乘车至南坪公交站转乘346/347路公交车→邮电大学站下车"]},{"name":"重庆西站","route":["乘坐325/426路公交车至南坪→在南坪转乘346/347路公交车→邮电大学站下车"]}]}
     */

    var code: Int = 0
    var info: String? = null
    var text_1: Text1Bean? = null
    var text_2: Text2Bean? = null

    class Text1Bean {
        /**
         * title : 重庆邮电大学地址
         * message : 重庆市南岸区南山街道崇文路2号重庆邮电大学
         */

        var title: String? = null
        var message: String? = null
    }

    class Text2Bean {
        /**
         * title : 推荐路线
         * message : [{"name":"江北机场","route":["江北机场乘坐三号线→南坪站下车从5号出口出站→乘坐346/347路公交车→邮电大学站下车","乘坐机场大巴→上清寺后下车乘坐108路公交车→南坪站转乘346/347路公交车→邮电大学站下车"]},{"name":"重庆火车北站北广场/南广场","route":["乘坐323/119/354路公交车→南坪站下车转乘346/347路公交车→邮电大学站下车"]},{"name":"菜园坝火车站","route":["在菜园坝广场乘坐347路公交车→邮电大学站下车"]},{"name":"朝天门码头","route":["乘车至南坪公交站转乘346/347路公交车→邮电大学站下车"]},{"name":"重庆西站","route":["乘坐325/426路公交车至南坪→在南坪转乘346/347路公交车→邮电大学站下车"]}]
         */

        var title: String? = null
        var message: List<MessageBean>? = null

        class MessageBean {
            /**
             * name : 江北机场
             * route : ["江北机场乘坐三号线→南坪站下车从5号出口出站→乘坐346/347路公交车→邮电大学站下车","乘坐机场大巴→上清寺后下车乘坐108路公交车→南坪站转乘346/347路公交车→邮电大学站下车"]
             */

            var name: String? = null
            var route: List<String>? = null
        }
    }
}
