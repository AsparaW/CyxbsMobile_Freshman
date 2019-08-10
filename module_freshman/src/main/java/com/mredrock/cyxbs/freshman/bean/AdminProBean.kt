package com.mredrock.cyxbs.freshman.bean

/**
 * Created by tk on 2019/8/8
 */
class AdminProBean {

    /**
     * code : 200
     * info : ok
     * text : [{"title":"报到时间","message":"9月5-6日","photo":"...","detail":""},{"title":"报到","message":"持通知书、身份证....","photo":"...","detail":"持通知书、身份证...."}]
     */

    var code: Int = 0
    var info: String? = null
    var text: List<TextBean>? = null

    class TextBean {
        /**
         * title : 报到时间
         * message : 9月5-6日
         * photo : ...
         * detail :
         */

        var title: String? = null
        var message: String? = null
        var photo: String? = null
        var detail: String? = null
    }
}
