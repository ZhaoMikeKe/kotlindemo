package com.huachu.kotlindemo.bean

import java.io.Serializable

class CuoTiBen : Serializable {

    /**
     * code : 0
     * msg : success
     * result : {"dataList":[{"id":"51258997-6737-4d22-be8f-36e202269c14","subjectId":"chinese2","subjectName":"高中语文","questionCount":26},{"id":"b3546029-35cb-4ace-9c42-5b3918789e3a","subjectId":"math2","subjectName":"高中数学","questionCount":13}]}
     */

    var code: Int = 0
    var msg: String? = null
    var result: ResultBean? = null

    class ResultBean : Serializable {
        var dataList: List<DataListBean>? = null

        class DataListBean : Serializable {
            /**
             * id : 51258997-6737-4d22-be8f-36e202269c14
             * subjectId : chinese2
             * subjectName : 高中语文
             * questionCount : 26
             */

            var id: String? = null
            var subjectId: String? = null
            var subjectName: String? = null
            var questionCount: Int = 0
        }
    }
}
