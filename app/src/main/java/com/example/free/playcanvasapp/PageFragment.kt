package com.example.free.playcanvasapp

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub

/**
 * Created by zhufree on 2018/1/22.
 *
 */

class PageFragment : Fragment() {
    @LayoutRes var titleRes: Int = 0
    @LayoutRes var layoutRes: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_page, container, false)

        val layoutStub = view.findViewById(R.id.layoutStub) as ViewStub
        layoutStub.layoutResource = layoutRes
        layoutStub.inflate()

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            titleRes = args.getInt("titleRes")
            layoutRes = args.getInt("layoutRes")
        }
    }

    companion object {

        fun newInstance(@StringRes titleRes: Int, @LayoutRes layoutRes: Int): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt("titleRes", titleRes)
            args.putInt("layoutRes", layoutRes)
            fragment.arguments = args
            return fragment
        }
    }
}
