package com.filippovigani.eventvods.views.adapters

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filippovigani.eventvods.R
import com.filippovigani.eventvods.binding.*
import com.filippovigani.eventvods.models.*
import com.filippovigani.eventvods.viewmodels.MatchViewModel
import com.filippovigani.eventvods.views.MatchViewHolder
import io.doist.recyclerviewext.sticky_headers.StickyHeaders
import kotlinx.android.synthetic.main.match_list_content.view.*
import java.util.*

class MatchesAdapter(private val context: Context, items: List<Match>? = null) : RecyclerViewAdapter<Any>(items), StickyHeaders, StickyHeaders.ViewSetup{

	private val viewModels : WeakHashMap<Match, MatchViewModel> = WeakHashMap()

	override fun isStickyHeader(position: Int): Boolean {
		return items?.get(position) !is Match
	}
	override fun setupStickyHeaderView(stickyHeader: View?) {
		stickyHeader?.translationZ = context.resources.getDimension(R.dimen.header_elevation)
	}

	override fun teardownStickyHeaderView(stickyHeader: View?) {
		stickyHeader?.translationZ = 0f
	}
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
			MatchViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), viewType, parent, false))

	override fun getViewModel(position: Int) : Any? {
		val match = items?.get(position)
		if (match !is Match) return match // header
		if (viewModels[match] == null) {
			viewModels[match] = MatchViewModel(match)
		}
		return viewModels[match]
	}


	override fun getItemCount() = items?.size ?: 0

	override fun getLayoutIdForPosition(position: Int) = if (isStickyHeader(position)) R.layout.matches_module_header else R.layout.match_list_content

	override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
		val matchViewModel = getViewModel(position)?.apply {
			holder.bind(this)
		}

		holder.itemView.apply {
			tag = matchViewModel
		}
	}

}