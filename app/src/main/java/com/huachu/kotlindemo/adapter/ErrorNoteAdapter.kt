package com.huachu.kotlindemo.adapter

import android.content.Context

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huachu.kotlindemo.R
import com.huachu.kotlindemo.bean.CuoTiBen

class ErrorNoteAdapter(layoutResId: Int, data: List<CuoTiBen.ResultBean.DataListBean>, private val mContext: Context) : BaseQuickAdapter<CuoTiBen.ResultBean.DataListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: CuoTiBen.ResultBean.DataListBean) {
        //helper.setText(R.id.subject, item.getSubjectName())
    }

}