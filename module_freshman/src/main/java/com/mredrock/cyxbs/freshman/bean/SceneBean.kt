package com.mredrock.cyxbs.freshman.bean

/**
 * Created by tk on 2019/8/9
 */
class SceneBean {

    /**
     * code : 200
     * info : ok
     * text : {"title":"重邮2D地图","photo":"...","message":[{"name":"八十万","photo":"...."},{"name":"仰望八教","photo":"...."}]}
     */

    var code: Int = 0
    var info: String? = null
    var text: TextBean? = null

    class TextBean {
        /**
         * title : 重邮2D地图
         * photo : ...
         * message : [{"name":"八十万","photo":"...."},{"name":"仰望八教","photo":"...."}]
         */

        var title: String? = null
        var photo: String? = null
        var message: List<MessageBean>? = null

        class MessageBean {
            /**
             * name : 八十万
             * photo : ....
             */

            var name: String? = null
            var photo: String? = null
        }
    }
}
