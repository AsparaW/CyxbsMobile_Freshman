package com.mredrock.cyxbs.freshman.bean

/**
 * Created by tk on 2019/8/10
 */
class CollegeNumBean {

    /**
     * code : 200
     * info : ok
     * text : [{"name":"学院","data":"11111"},{"name":"学院","data":"11111"}]
     */

    var code: Int = 0
    var info: String? = null
    var text: List<TextBean>? = null

    class TextBean {
        /**
         * name : 学院
         * data : 11111
         */

        var name: String? = null
        var data: String? = null
    }
}
