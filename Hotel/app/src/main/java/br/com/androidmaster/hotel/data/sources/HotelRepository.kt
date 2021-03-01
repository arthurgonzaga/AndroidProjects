package br.com.androidmaster.hotel.data.sources

import br.com.androidmaster.hotel.data.Hotel
import java.util.*

object HotelRepository: HotelDataSource {
    private var nextId = 0L
    private val hotelList = mutableListOf<Hotel>()

    init {
        save(Hotel(0, "New Beach Hotel", "Av. Boa Viagem", 4.5f))
        save(Hotel(0, "Recife Hotel", "Av. Boa Viagem", 4.0f))
        save(Hotel(0, "Canario Hotel", "Rua dos Navegantes", 3.0f))
        save(Hotel(0, "Byanca Beach Hotel", "Rua Mamanguape", 4.0f))
        save(Hotel(0, "Grand Hotel Dor", "Av. Bernardo", 3.5f))
        save(Hotel(0, "Hotel Cool", "Av. Conselheiro Aguiar", 4.0f))
        save(Hotel(0, "Hotel Infinito", "Rua Ribeiro de Brito", 5.0f))
        save(Hotel(0, "Hotel Tulipa", "Av. Boa Viagem", 5.0f))
    }
    override fun save(hotel: Hotel) {
        if(hotel.id == 0L){
            hotel.id = nextId++
            hotelList.add(hotel)
        }else{
            val index = hotelList.indexOfFirst { it.id == hotel.id}
            if(index != -1){
                hotelList[index] = hotel
            }else{
                hotelList.add(hotel)
            }
        }
    }

    override fun hotelById(id: Long, callback: (Hotel?) -> Unit) {
        val hotel = hotelList.find{id == it.id}
        callback(hotel)
    }

    override fun search(term: String, callback: (List<Hotel>) -> Unit) {
        callback(
            if (term.isEmpty()) hotelList
            else hotelList.filter {
                    it.name.toUpperCase(Locale.ROOT)
                    .contains(term.toUpperCase(Locale.ROOT))
                }
        )
    }

    override fun remove(vararg hotels: Hotel) {
        hotelList.removeAll(hotels)
    }
}