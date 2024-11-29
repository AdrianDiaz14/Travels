package com.example.travels

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.ViewSwitcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ViewSwitcher.ViewFactory {
    //Listado con cada elemento de imagen
    val destinos = listOf<Tarjeta> (
        Tarjeta(R.drawable.image1),
        Tarjeta(R.drawable.image2),
        Tarjeta(R.drawable.image3),
        Tarjeta(R.drawable.image4),
        Tarjeta(R.drawable.image5),
        Tarjeta(R.drawable.image6),
        Tarjeta(R.drawable.image7),
        Tarjeta(R.drawable.image8),
        Tarjeta(R.drawable.image9),
        Tarjeta(R.drawable.image10)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initRecyclerView()

        // Configuración del ImageSwitcher
        val imageSwitcher = findViewById<ImageSwitcher>(R.id.imageswitcher)
        imageSwitcher.setFactory(this)
        imageSwitcher.inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        imageSwitcher.outAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerDestinos)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // En el adapter pasamos al imageSwitcher la referencia de la imagen seleccionada para que la pueda mostrar
        val adapter = TarjetaAdapter(destinos) { view ->
            val imagenDestino = view.findViewById<ImageView>(R.id.imagenDestino)
            imageSwitcher.setImageDrawable(imagenDestino.drawable)
        }
        recyclerView.adapter = adapter
    }

    override fun makeView(): View {
        val imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER
        imageView.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return imageView
    }

    fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerDestinos)
        // Indicamos que LinearLayoutManager sea horizontal para visualizar el carrusel de elementos de esta manera
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adapter = TarjetaAdapter(destinos) { view ->
            val imageSwitcher = findViewById<ImageSwitcher>(R.id.imageswitcher)
            val imagenDestino = view.findViewById<ImageView>(R.id.imagenDestino).drawable
            imageSwitcher.setImageDrawable(imagenDestino)
        }
        recyclerView.adapter = adapter
    }
}