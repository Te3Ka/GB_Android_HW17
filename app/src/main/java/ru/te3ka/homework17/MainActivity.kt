package ru.te3ka.homework17

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.te3ka.homework17.adapter.PhotoPagedAdapter
import ru.te3ka.homework17.databinding.ActivityMainBinding
import ru.te3ka.homework17.network.MarsRoverApi
import ru.te3ka.homework17.repository.PhotoRepository
import ru.te3ka.homework17.viewmodel.PhotoViewModel
import ru.te3ka.homework17.viewmodel.PhotoViewModelFactory

private const val API_KEY = "8YUqAUV51sYsAmFs84QN6m1UtYgdSpTGuyUaF3Tg"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PhotoViewModel by viewModels {
        PhotoViewModelFactory(PhotoRepository(MarsRoverApi.create(), API_KEY))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PhotoPagedAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.getPhotos(13).collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        adapter.setOnItemClickListener { photo ->
            val intent = Intent(this@MainActivity, PhotoDetailActivity::class.java)
            intent.putExtra("IMAGE_URL", photo.imgSrc)
            startActivity(intent)
        }
    }
}