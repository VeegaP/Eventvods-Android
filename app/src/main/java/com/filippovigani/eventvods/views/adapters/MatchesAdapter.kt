package com.filippovigani.eventvods.views.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import com.filippovigani.eventvods.R
import com.filippovigani.eventvods.binding.*
import com.filippovigani.eventvods.models.*
import io.doist.recyclerviewext.sticky_headers.StickyHeaders

class MatchesAdapter(private val context: Context, items: List<Match>? = null) : RecyclerViewAdapter<Any>(items), StickyHeaders, StickyHeaders.ViewSetup{

	override fun isStickyHeader(position: Int): Boolean {
		return items?.get(position) !is Match
	}
	override fun setupStickyHeaderView(stickyHeader: View?) {
		stickyHeader?.translationZ = context.resources.getDimension(R.dimen.header_elevation)
	}

	override fun teardownStickyHeaderView(stickyHeader: View?) {
		stickyHeader?.translationZ = 0f
	}

	override fun getViewModel(position: Int) = items?.get(position)

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