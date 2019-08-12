package com.mredrock.cyxbs.freshman.bean

/**
 * Created by tk on 2019/8/11
 */
class ActivityBean {

    /**
     * code : 200
     * info : ok
     * text : [{"name":"学长学姐帮帮忙","photo":"....","message":"....","QR":"...."},{"name":"学长学姐帮帮忙","photo":"....","message":"....","QR":"...."}]
     */

    var code: Int = 0
    var info: String? = null
    var text: List<TextBean>? = null

    class TextBean {
        /**
         * name : 学长学姐帮帮忙
         * photo : ....
         * message : ....
         * QR : ....
         */

        var name: String? = null
        var photo: String? = null
        var message: String? = null
        var qr: String? = null
    }
}
