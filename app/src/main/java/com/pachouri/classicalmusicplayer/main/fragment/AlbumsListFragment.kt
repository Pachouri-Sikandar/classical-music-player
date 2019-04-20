package com.pachouri.classicalmusicplayer.main.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.firstkotlin.viewmodel.LoadingState
import com.pachouri.classicalmusicplayer.R
import com.pachouri.classicalmusicplayer.infrastructure.api.response.AuthorizationResponse
import com.pachouri.classicalmusicplayer.infrastructure.api.response.albums.Item
import com.pachouri.classicalmusicplayer.infrastructure.dao.SessionSharedPreferences
import com.pachouri.classicalmusicplayer.main.adapter.AlbumsListAdapter
import com.pachouri.classicalmusicplayer.util.AppConstants
import com.pachouri.classicalmusicplayer.util.CommonUtils
import com.pachouri.classicalmusicplayer.util.VerticalListItemDecoration
import com.pachouri.classicalmusicplayer.viewmodel.ViewModelProvider
import com.pachouri.classicalmusicplayer.viewmodel.provider.AlbumsListViewModel
import com.pachouri.classicalmusicplayer.viewmodel.provider.TokenViewModel

/**
 * Created by Ankit Pachouri on 20/04/19.
 */
class AlbumsListFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mLayoutErrorContainer: LinearLayout
    private lateinit var mTextViewErrorTitle: AppCompatTextView
    private lateinit var mTextViewErrorSubTitle: AppCompatTextView
    private lateinit var mButtonTryAgain: Button
    private var mViewModel: AlbumsListViewModel? = null
    private var mTokenViewModel: TokenViewModel? = null
    private var mAdapter: AlbumsListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_albums_list, container, false)
        initView(view)
        initialiseAdapter()
        initTokenViewModel()
        return view
    }

    /**
     * Initialise View
     */
    private fun initView(view: View) {
        mRecyclerView = view.findViewById(R.id.recyclerView)
        mProgressBar = view.findViewById(R.id.progressBar)
        mLayoutErrorContainer = view.findViewById(R.id.layoutErrorContainer)
        mTextViewErrorTitle = view.findViewById(R.id.textViewErrorTitle)
        mTextViewErrorSubTitle = view.findViewById(R.id.textViewErrorSubTitle)
        mButtonTryAgain = view.findViewById(R.id.buttonTryAgain)
        mButtonTryAgain.setOnClickListener {
            makeApiCall()
        }
    }

    /**
     * Initialise Adapter and set to recycler view
     */
    private fun initialiseAdapter() {
        var linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.setLayoutManager(linearLayoutManager)
        mRecyclerView.addItemDecoration(VerticalListItemDecoration(context!!, 10f, 10f))
        mAdapter = AlbumsListAdapter()
        mRecyclerView.setAdapter(mAdapter)
    }

    /**
     * Initialise token view model
     */
    private fun initTokenViewModel() {
        val sessionPreferences = SessionSharedPreferences(context!!)
        if (TextUtils.isEmpty(sessionPreferences.getAuthorizationKey())) {
            val conversionString = AppConstants.APP_CLIENT_ID + ":" + AppConstants.APP_SECRET_KEY
            val authorizationKey = CommonUtils.base64Conversion(conversionString, "Basic")
            sessionPreferences.saveAuthorizationKey(authorizationKey)
            sessionPreferences.saveAuthorizationType(true)

            if (null == mTokenViewModel) {
                mTokenViewModel = ViewModelProvider.getInstance().getTokenViewModel(this)
            }

            mTokenViewModel?.getLoadingState()?.observe(this, Observer { state: LoadingState? ->
                if (state == LoadingState.LOADING_COMPLETED) {
                    showMainLayout()
                } else if (state == LoadingState.LOADING_FAILED) {
                    showSomethingWrongLayout()
                } else {
                    showProgressBar()
                }
            })

            mTokenViewModel?.getAuthorizationObject()?.observe(this, Observer { auth: AuthorizationResponse? ->
                sessionPreferences.saveAuthorizationType(false)
                sessionPreferences.saveAccessToken("Bearer " + auth?.getToken())
                initListViewModel()
            })

            mTokenViewModel?.getAccessToken()

        } else {
            sessionPreferences.saveAuthorizationType(false)
            initListViewModel()
        }
    }

    /**
     * Initialise view model
     */
    private fun initListViewModel() {
        if (null == mViewModel) {
            mViewModel = ViewModelProvider.getInstance().getAlbumsListViewModel(this)
        }
        mViewModel?.getList()?.observe(this, android.arch.lifecycle.Observer { t: List<Item>? ->
            mAdapter?.setList(t as List<Item>)
        })

        mViewModel?.getInitialLoadState()?.observe(this, Observer { state: LoadingState? ->
            if (state == LoadingState.LOADING_COMPLETED) {
                showMainLayout()
            } else if (state == LoadingState.LOADING_FAILED) {
                showSomethingWrongLayout()
            } else {
                showProgressBar()
            }
        })

        makeApiCall()
    }

    /**
     * Method to make the Api call
     */
    private fun makeApiCall() {
        if (CommonUtils.isInternetConnected(context!!)) {
            showProgressBar()
            mViewModel?.refresh()
        } else {
            showNoInternetLayout()
        }
    }

    /**
     * Method to show only progress bar
     */
    private fun showProgressBar() {
        if (activity != null) {
            mProgressBar.visibility = View.VISIBLE
            mRecyclerView.setVisibility(View.GONE)
            mLayoutErrorContainer.visibility = View.GONE
        }
    }

    /**
     * Method to show content layout
     */
    private fun showMainLayout() {
        if (activity != null) {
            mProgressBar.visibility = View.GONE
            mRecyclerView.setVisibility(View.VISIBLE)
            mLayoutErrorContainer.visibility = View.GONE
        }
    }

    /**
     * Method to show no internet available layout
     */
    private fun showNoInternetLayout() {
        if (activity != null) {
            mProgressBar.visibility = View.GONE
            mRecyclerView.setVisibility(View.GONE)
            mLayoutErrorContainer.visibility = View.VISIBLE
            mTextViewErrorTitle.text = getString(R.string.error_oops)
            mTextViewErrorSubTitle.text = getString(R.string.error_message_internet_not_found)
        }
    }

    /**
     * Method to show something went wrong layout
     */
    private fun showSomethingWrongLayout() {
        if (activity != null) {
            mProgressBar.visibility = View.GONE
            mRecyclerView.setVisibility(View.GONE)
            mLayoutErrorContainer.visibility = View.VISIBLE
            mTextViewErrorTitle.text = getString(R.string.error_sorry)
            mTextViewErrorSubTitle.text = getString(R.string.error_message_something_wrong)
        }
    }
}