package br.com.androidmaster.hotel.listhotel

import br.com.androidmaster.hotel.data.Hotel
import br.com.androidmaster.hotel.data.sources.HotelRepository

class HotelListPresenter(
    private val view: HotelListView,
    private val repository: HotelRepository
){

    fun searchHotels(term: String) {
        repository.search(term){
            view.showHotels(it)
        }
    }

    fun showHotelsDetail(hotel: Hotel){
        view.showHotelDetail(hotel)
    }
}