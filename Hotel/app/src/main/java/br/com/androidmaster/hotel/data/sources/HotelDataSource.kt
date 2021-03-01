package br.com.androidmaster.hotel.data.sources

import br.com.androidmaster.hotel.data.Hotel

interface HotelDataSource {
    fun save(hotel: Hotel)
    fun remove(vararg hotels: Hotel)
    fun hotelById(id: Long, callback: (Hotel?) -> Unit)
    fun search(term: String, callback: (List<Hotel>) -> Unit)
}