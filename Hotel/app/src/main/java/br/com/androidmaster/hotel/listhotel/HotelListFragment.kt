package br.com.androidmaster.hotel.listhotel

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.ListFragment
import br.com.androidmaster.hotel.data.Hotel
import br.com.androidmaster.hotel.data.sources.HotelRepository

class HotelListFragment: ListFragment(), HotelListView {

    private val presenter = HotelListPresenter(this, HotelRepository)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.searchHotels("")
        listView.setOnItemClickListener { parent, view, position, id ->
            HotelRepository.hotelById(position.toLong()) {
                if (it != null) {
                    Toast.makeText(requireContext(), it.id.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun showHotels(hotels: List<Hotel>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, hotels)
        listAdapter = adapter
    }

    override fun showHotelDetail(hotel: Hotel) {
        TODO("Not yet implemented")
    }
}