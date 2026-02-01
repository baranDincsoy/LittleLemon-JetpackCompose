import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.Dish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<Dish>>(emptyList())
    val cartItems: StateFlow<List<Dish>> = _cartItems

    val cartCount: StateFlow<Int> = _cartItems
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    val totalPrice: StateFlow<Double> = _cartItems
        .map { items -> items.sumOf { it.price } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    fun addToCart(dish: Dish) {
        _cartItems.value = _cartItems.value + dish
    }
}