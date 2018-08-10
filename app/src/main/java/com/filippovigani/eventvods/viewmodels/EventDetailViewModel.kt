package com.filippovigani.eventvods.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.filippovigani.eventvods.models.Event
import com.filippovigani.eventvods.services.EventvodsRepository
import android.app.Application
import android.support.annotation.NonNull



class EventDetailViewModel(eventSlug: String) : ViewModel() {

	var event: LiveData<Event> = EventvodsRepository.getEvent(eventSlug)

	class Factory(private val eventSlug: String) : ViewModelProvider.NewInstanceFactory() {

		@Suppress("UNCHECKED_CAST")
		override fun <T : ViewModel> create(modelClass: Class<T>): T {
			return EventDetailViewModel(eventSlug) as T
		}
	}
}