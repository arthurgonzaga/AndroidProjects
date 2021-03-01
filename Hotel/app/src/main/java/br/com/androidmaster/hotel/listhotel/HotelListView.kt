package br.com.androidmaster.hotel.listhotel

import br.com.androidmaster.hotel.data.Hotel

interface HotelListView {
    fun showHotels(hotels: List<Hotel>)
    fun showHotelDetail(hotel: Hotel)
}